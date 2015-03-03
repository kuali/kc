package org.kuali.coeus.common.impl.attachment;

import org.apache.commons.lang3.StringUtils;
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
public class KcAttachmentDataDaoImpl implements KcAttachmentDataDao{

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Override
    public byte[] getData(String id) {
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
        try (Connection connection = getDataSource().getConnection()) {
        	if (StringUtils.isNotBlank(id)) {
        		deleteAttachment(connection, id);
        	}
        	try (PreparedStatement stmt = connection.prepareStatement("insert into file_data (id, data) values (?, ?)")) {
	        	String newId = UUID.randomUUID().toString();
	        	stmt.setString(1, newId);
	        	stmt.setBlob(2, new SerialBlob(attachmentData));
	        	stmt.executeUpdate();
	        	return newId;
        	}
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
    }
    
    public void removeData(String id) {
    	try (Connection conn = dataSource.getConnection()) {
    		deleteAttachment(conn, id);
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
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
