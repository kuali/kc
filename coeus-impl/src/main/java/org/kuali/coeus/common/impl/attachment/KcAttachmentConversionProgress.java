package org.kuali.coeus.common.impl.attachment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("kcAttachmentConversionProgress")
public class KcAttachmentConversionProgress extends RestController {

	private static final String COMPLETE = "complete";

	private static final String REMAINING = "remaining";

	private static final String TOTAL = "total";

	@Resource(name="tableBlobsToWatch")
	private Map<String, String> tableBlobsToWatch;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@RequestMapping(value="/api/v1/attachment-conversions", method=RequestMethod.GET)
	public @ResponseBody Map<String, Map<String, Long>> getCurrentConversionStatus() {
		Map<String, Map<String, Long>> result = new HashMap<>();
		try (Connection conn = dataSource.getConnection()) {
			tableBlobsToWatch.entrySet().stream()
				.forEach(entry -> {
					result.put(entry.getKey(), getNullCount(entry.getKey(), entry.getValue(), conn));
				});
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	protected Map<String, Long> getNullCount(String tableName, String blobColumn, Connection conn) {
		Map<String, Long> result = new HashMap<>();
		String query = "select count(*), count(" + blobColumn + ") from " + tableName;
		try (PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();) {
			if (rs.next()) {
				result.put(TOTAL, rs.getLong(1));
				result.put(REMAINING, rs.getLong(2));
				result.put(COMPLETE, rs.getLong(1)-rs.getLong(2));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public Map<String, String> getTableBlobsToWatch() {
		return tableBlobsToWatch;
	}

	public void setTableBlobsToWatch(Map<String, String> tableBlobsToWatch) {
		this.tableBlobsToWatch = tableBlobsToWatch;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
