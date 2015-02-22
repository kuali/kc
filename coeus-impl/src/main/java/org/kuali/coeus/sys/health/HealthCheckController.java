package org.kuali.coeus.sys.health;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("healthCheckController")
public class HealthCheckController {
	
	protected static final String DEFAULT_HEALTH_QUERY = "select 1 from dual;";
	
	protected final Log LOG = LogFactory.getLog(HealthCheckController.class);

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("riceDataSource")
	private DataSource riceDataSource;
	
	private String kcHealthQuery = DEFAULT_HEALTH_QUERY;
	private String riceHealthQuery = DEFAULT_HEALTH_QUERY;
	
	@RequestMapping("/")
	public ResponseEntity<String> doHealthCheck() {
		String result = "";
		try (Connection conn = dataSource.getConnection();
				Connection riceConn = riceDataSource.getConnection()) {
			if (conn.createStatement().executeQuery(kcHealthQuery).next()) {
				result += "Client DB Up\n";
			}
			if (riceConn.createStatement().executeQuery(riceHealthQuery).next()) {
				result += "Server DB Up\n";
			}
		} catch (Exception e) {
			LOG.error("Health Check Failed.", e);
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getRiceDataSource() {
		return riceDataSource;
	}

	public void setRiceDataSource(DataSource riceDataSource) {
		this.riceDataSource = riceDataSource;
	}

	public String getKcHealthQuery() {
		return kcHealthQuery;
	}

	public void setKcHealthQuery(String kcHealthQuery) {
		this.kcHealthQuery = kcHealthQuery;
	}

	public String getRiceHealthQuery() {
		return riceHealthQuery;
	}

	public void setRiceHealthQuery(String riceHealthQuery) {
		this.riceHealthQuery = riceHealthQuery;
	}
	
}
