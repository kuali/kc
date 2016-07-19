/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2016 Kuali, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package co.kuali.coeus.common.impl.attachment;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class KcAttachmentDataToS3ConversionImpl implements KcAttachmentDataToS3Conversion {

    private static final Log LOG = LogFactory.getLog(KcAttachmentDataToS3ConversionImpl.class);
    private static final String QUERY_SQL = "select id, data from file_data where data is not null";
    private static final String UPDATE_SQL = "update file_data set data = null where id = ?";

    private KcS3FileService kcS3FileService;
    private ParameterService parameterService;
    private Integer fetchSize = 5;
    private DataSource dataSource;

    @Override
    public void execute() {
        LOG.info("Starting attachment conversion job for file_data to S3");
        boolean hasResults = true;
        while (hasResults) {
            if (processRecords()) {
                try (Connection conn = dataSource.getConnection();
                     PreparedStatement queryStmt = conn.prepareStatement(QUERY_SQL);
                     PreparedStatement updateStmt = conn.prepareStatement(UPDATE_SQL)) {
                    conn.setAutoCommit(false);
                    hasResults = false;
                    queryStmt.setFetchSize(fetchSize);
                    try (ResultSet rs = queryStmt.executeQuery()) {
                        for (int i = 0; i < fetchSize && rs.next(); i++) {
                            final String fileDataId = rs.getString(1);
                            final byte[] dbBytes = rs.getBytes(2);
                            try {
                                final Object s3File = kcS3FileService.retrieveFile(fileDataId);
                                final byte[] s3Bytes;

                                if (s3File == null) {

                                    final Class<?> s3FileClass = Class.forName(KcAttachmentDataS3Constants.S3_FILE_CLASS);
                                    final Object newS3File = s3FileClass.newInstance();

                                    final Method setId = s3FileClass.getMethod(KcAttachmentDataS3Constants.SET_ID_METHOD, String.class);
                                    setId.invoke(newS3File, fileDataId);

                                    final Method setFileContents = s3FileClass.getMethod(KcAttachmentDataS3Constants.SET_FILE_CONTENTS_METHOD, InputStream.class);
                                    try (InputStream stream = new BufferedInputStream(new ByteArrayInputStream(dbBytes))) {
                                        setFileContents.invoke(newS3File, stream);
                                        kcS3FileService.createFile(newS3File);
                                    }

                                    s3Bytes = getBytesFromS3File(kcS3FileService.retrieveFile(fileDataId));
                                } else {
                                    if (LOG.isDebugEnabled()) {
                                        final Method getFileMetaData = s3File.getClass().getMethod(KcAttachmentDataS3Constants.GET_FILE_META_DATA_METHOD);
                                        LOG.debug("data found in S3, existing id: " + fileDataId + " metadata: " + getFileMetaData.invoke(s3File));
                                    }
                                    s3Bytes = getBytesFromS3File(s3File);
                                }

                                if (s3Bytes != null && dbBytes != null) {
                                    final String s3MD5 = DigestUtils.md5Hex(s3Bytes);
                                    final String dbMD5 = DigestUtils.md5Hex(dbBytes);
                                    if (!Objects.equals(s3MD5, dbMD5)) {
                                        LOG.error("S3 data MD5: " + s3MD5 + " does not equal DB data MD5: " + dbMD5 + " for id: " + fileDataId);
                                    } else {
                                        updateStmt.setString(1, fileDataId);
                                        int numUpdated = updateStmt.executeUpdate();
                                        if (numUpdated != 1) {
                                            LOG.error("Expected to update a single row, but instead updated " + numUpdated + ". Job exiting.");
                                            conn.rollback();
                                            return;
                                        }
                                    }
                                }
                            } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException|IOException|ClassNotFoundException|InstantiationException e) {
                                throw new RuntimeException(e);
                            }
                            hasResults = true;
                        }
                    } catch (SQLException e) {
                        conn.rollback();
                        throw e;
                    }
                    conn.commit();

                    Thread.sleep(500);

                } catch (SQLException e) {
                    LOG.error("Got sql exception in attachment conversion, job exiting.", e);
                    return;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        LOG.info("Finishing attachment conversion job for file_data to S3");
    }

    protected byte[] getBytesFromS3File(Object s3File) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        byte[] s3Bytes;
        final Method getFileContents = s3File.getClass().getMethod(KcAttachmentDataS3Constants.GET_FILE_CONTENTS_METHOD);
        final InputStream fileContents = (InputStream) getFileContents.invoke(s3File);
        s3Bytes = IOUtils.toByteArray(fileContents);
        return s3Bytes;
    }

    protected boolean processRecords() {
        final boolean s3IntegrationEnabled = isS3IntegrationEnabled();
        if (!s3IntegrationEnabled) {
            LOG.info("S3 integration is not enabled.  Records will not be processed");
        }

        final boolean s3DualSaveEnabled = isS3DualSaveEnabled();
        if (s3DualSaveEnabled) {
            LOG.info("S3 dual save is enabled.  Records will not be processed");
        }
        return s3IntegrationEnabled && !s3DualSaveEnabled;
    }

    protected boolean isS3IntegrationEnabled() {
        return parameterService.getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, KcAttachmentDataS3Constants.S3_INTEGRATION_ENABLED);
    }

    protected boolean isS3DualSaveEnabled() {
        return parameterService.getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, KcAttachmentDataS3Constants.S3_DUAL_SAVE_ENABLED);
    }

    public KcS3FileService getKcS3FileService() {
        return kcS3FileService;
    }

    public void setKcS3FileService(KcS3FileService kcS3FileService) {
        this.kcS3FileService = kcS3FileService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public Integer getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(Integer fetchSize) {
        this.fetchSize = fetchSize;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
