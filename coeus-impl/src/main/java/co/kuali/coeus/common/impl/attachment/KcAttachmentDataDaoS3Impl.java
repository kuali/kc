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
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.impl.attachment.KcAttachmentDataDaoImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;


public class KcAttachmentDataDaoS3Impl extends KcAttachmentDataDaoImpl {

    private static final String INSERT_RECORD_ID_ONLY = "insert into file_data (id) values (?)";
    private static Log LOG = LogFactory.getLog(KcAttachmentDataDaoS3Impl.class);

    private KcS3FileService kcS3FileService;
    private ParameterService parameterService;

    @Override
    public byte[] getData(String id) {
        if (!isS3IntegrationEnabled()) {
            return super.getData(id);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Fetching attachment data from S3, existing id: " + id);
        }

        if (StringUtils.isNotBlank(id)) {
            try {
                final Object s3File = kcS3FileService.retrieveFile(id);
                byte[] s3Bytes = null;
                byte[] dbBytes = null;
                if (s3File != null) {
                    if (LOG.isDebugEnabled()) {
                        final Method getFileMetaData = s3File.getClass().getMethod(KcAttachmentDataS3Constants.GET_FILE_META_DATA_METHOD);
                        LOG.debug("data found in S3, existing id: " + id + " metadata: " + getFileMetaData.invoke(s3File));
                    }

                    final Method getFileContents = s3File.getClass().getMethod(KcAttachmentDataS3Constants.GET_FILE_CONTENTS_METHOD);
                    final InputStream fileContents = (InputStream) getFileContents.invoke(s3File);
                    s3Bytes = IOUtils.toByteArray(fileContents);
                }

                if (s3Bytes == null || isS3DualRetrieveEnabled()) {
                    dbBytes = super.getData(id);
                }

                if (LOG.isErrorEnabled() && s3Bytes != null && dbBytes != null) {
                    final String s3MD5 = DigestUtils.md5Hex(s3Bytes);
                    final String dbMD5 = DigestUtils.md5Hex(dbBytes);
                    if (!Objects.equals(s3MD5, dbMD5)) {
                        LOG.error("S3 data MD5: " + s3MD5 + " does not equal DB data MD5: " + dbMD5 + " for id: " + id);
                    }
                }

                return s3Bytes != null ? s3Bytes : dbBytes;
            } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException|IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    @Override
    public String saveData(byte[] attachmentData, String id) {
        if (!isS3IntegrationEnabled()) {
            return super.saveData(attachmentData, id);
        }

        if (ArrayUtils.isEmpty(attachmentData)) {
            throw new IllegalArgumentException("attachmentData is null");
        }
        final String query = isS3DualSaveEnabled() ? INSERT_RECORD : INSERT_RECORD_ID_ONLY;

        try (Connection connection = getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            String newId = saveDataInS3(attachmentData);
            stmt.setString(1, newId);

            if (isS3DualSaveEnabled()) {
                try (ByteArrayInputStream is = new ByteArrayInputStream(attachmentData)) {
                    stmt.setBinaryStream(2,is,attachmentData.length);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            stmt.executeUpdate();

            if (LOG.isDebugEnabled()) {
                LOG.debug("Created attachment data, new id: " + newId);
            }

            if (StringUtils.isNotBlank(id)) {
                boolean deleted = deleteAttachment(connection, id);
                if (deleted) {
                    kcS3FileService.deleteFile(id);
                }
            }

            return newId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected String saveDataInS3(byte[] attachmentData) {
        if (ArrayUtils.isEmpty(attachmentData)) {
            throw new IllegalArgumentException("attachmentData is null");
        }

        try (InputStream stream = new BufferedInputStream(new ByteArrayInputStream(attachmentData))) {
            final Class<?> s3FileClass = Class.forName(KcAttachmentDataS3Constants.S3_FILE_CLASS);
            final Object s3File = s3FileClass.newInstance();

            final Method setFileContents = s3FileClass.getMethod(KcAttachmentDataS3Constants.SET_FILE_CONTENTS_METHOD, InputStream.class);
            setFileContents.invoke(s3File, stream);

            return kcS3FileService.createFile(s3File);

        } catch (NoSuchMethodException|ClassNotFoundException|IllegalAccessException|InstantiationException|InvocationTargetException|IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeData(String id) {
        if (!isS3IntegrationEnabled()) {
            super.removeData(id);
            return;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Removing attachment data from S3, existing id: " + id);
        }

        if (StringUtils.isNotBlank(id)) {
            try (Connection conn = getDataSource().getConnection()) {
                final boolean deleted = deleteAttachment(conn, id);
                if (deleted) {
                    kcS3FileService.deleteFile(id);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected boolean isS3IntegrationEnabled() {
        if (parameterService.parameterExists(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, KcAttachmentDataS3Constants.S3_INTEGRATION_ENABLED)) {
        	return parameterService.getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, KcAttachmentDataS3Constants.S3_INTEGRATION_ENABLED);
        } else {
        	return false;
        }
    }

    protected boolean isS3DualSaveEnabled() {
        return parameterService.getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, KcAttachmentDataS3Constants.S3_DUAL_SAVE_ENABLED);
    }

    protected boolean isS3DualRetrieveEnabled() {
        return parameterService.getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, KcAttachmentDataS3Constants.S3_DUAL_RETRIEVE_ENABLED);
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
}
