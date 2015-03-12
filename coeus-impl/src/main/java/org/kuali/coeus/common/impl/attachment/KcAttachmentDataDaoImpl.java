/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.impl.attachment;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.attachment.KcAttachmentDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component("kcAttachmentDataDao")
public class KcAttachmentDataDaoImpl implements KcAttachmentDataDao {

    private static Log LOG = LogFactory.getLog(KcAttachmentDataDaoImpl.class);

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Override
    public byte[] getData(String id) {
    	if (LOG.isDebugEnabled()) {
            LOG.debug("Fetching attachment data existing id: " + id);
        }

        if (StringUtils.isNotBlank(id)) {
	        try(Connection connection = getDataSource().getConnection();
	        		PreparedStatement stmt = connection.prepareStatement("select data from file_data where id = ?")) {
	        	stmt.setString(1, id);
	        	try (ResultSet rs = stmt.executeQuery()) {
	        		if (rs.next()) {
	        			return rs.getBytes(1);
	        		} else {
	        			return null;
	        		}
	        	}
	        } catch (SQLException e) {
	        	throw new RuntimeException(e);
			}
    	} else {
    		return null;
    	}
    }

    @Override
    public String saveData(byte[] attachmentData, String id) {
        if (ArrayUtils.isEmpty(attachmentData)) {
            throw new IllegalArgumentException("attachmentData is null");
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Saving attachment data, existing id: " + id);
        }

        try (Connection connection = getDataSource().getConnection()) {
        	if (StringUtils.isNotBlank(id)) {
        		deleteAttachment(connection, id);
        	}
        	try (PreparedStatement stmt = connection.prepareStatement("insert into file_data (id, data) values (?, ?)")) {
	        	String newId = UUID.randomUUID().toString();
	        	stmt.setString(1, newId);
	        	stmt.setBlob(2, new SerialBlob(attachmentData));
	        	stmt.executeUpdate();

                if (LOG.isDebugEnabled()) {
                    LOG.debug("Created attachment data, new id: " + newId);
                }

                return newId;
        	}
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
    }

    @Override
    public void removeData(String id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Removing attachment data, existing id: " + id);
        }

        if (StringUtils.isNotBlank(id)) {
            try (Connection conn = dataSource.getConnection()) {
                deleteAttachment(conn, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
        
    protected void deleteAttachment(Connection conn, String id) throws SQLException {
    	try (PreparedStatement stmt = conn.prepareStatement("delete from file_data where id = ?")) {
    		stmt.setString(1, id);
    		stmt.executeUpdate();
    	}
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
