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
package org.kuali.coeus.sys.health;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.core.api.config.property.ConfigurationService;
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
	
	@Autowired
	@Qualifier("kualiConfigurationService")
	private ConfigurationService configurationService;
	
	private String kcHealthQuery = DEFAULT_HEALTH_QUERY;
	private String riceHealthQuery = DEFAULT_HEALTH_QUERY;
	
	@RequestMapping("/")
	public ResponseEntity<HealthResponse> doHealthCheck() {
		HealthResponse result = new HealthResponse();
		result.version = configurationService.getPropertyValueAsString("version");
		try (Connection conn = dataSource.getConnection();
				Connection riceConn = riceDataSource.getConnection()) {
			if (conn.createStatement().executeQuery(kcHealthQuery).next()) {
				result.kcDatabaseUp = true;
			}
			if (riceConn.createStatement().executeQuery(riceHealthQuery).next()) {
				result.riceDatabaseUp = true;
			}
		} catch (Exception e) {
			LOG.error("Health Check Failed.", e);
			return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	static class HealthResponse {
		public String version = "NO VERSION";
		public boolean kcDatabaseUp;
		public boolean riceDatabaseUp;
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

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
}
