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
package org.kuali.coeus.dc.tm;

import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.setString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;


public class TimeAndMoneyDocumentStatusDaoImpl implements
		TimeAndMoneyDocumentStatusDao {

	private static final Logger LOG = Logger.getLogger(TimeAndMoneyDocumentStatusDaoImpl.class.getName());
	
	private static final String ARCHIVED_STATUS = "ARCHIVED";
	private static final String PENDING_STATUS = "PENDING";
	private static final String CANCELED_STATUS = "CANCELED";
	private static final String ACTIVE_STATUS = "ACTIVE";
	private static final String MAX_ACTIVE_DOCUMENT_QUERY = "select max(CAST(DOCUMENT_NUMBER AS DECIMAL (38,0))) from TIME_AND_MONEY_DOCUMENT where TIME_AND_MONEY_DOC_STATUS = '" +
			ARCHIVED_STATUS + "' group by AWARD_NUMBER";
	private static final String UPDATE_ACTIVE_TIME_AND_MONEY_DOC_STATUS = "update TIME_AND_MONEY_DOCUMENT doc set TIME_AND_MONEY_DOC_STATUS = '"
			+ ACTIVE_STATUS + "' where DOCUMENT_NUMBER = ?";
	private static final String UPDATE_TIME_AND_MONEY_DOC_STATUS = "update TIME_AND_MONEY_DOCUMENT set TIME_AND_MONEY_DOC_STATUS = ? where DOCUMENT_NUMBER = ?";

    private ConnectionDaoService connectionDaoService;
    private KewDocHeaderDao krewDocHeaderDao;
    private List<String> canceledStatuses;
    private List<String> pendingStatuses;
    private List<String> activeStatuses;
	
    public TimeAndMoneyDocumentStatusDaoImpl() {
    	canceledStatuses = new ArrayList<>();
    	canceledStatuses.add("X");
    	canceledStatuses.add("D");
    	pendingStatuses = new ArrayList<>();
    	pendingStatuses.add("I");
    	pendingStatuses.add("S");
    	pendingStatuses.add("R");
    	pendingStatuses.add("E");
    	activeStatuses = new ArrayList<>();
    	activeStatuses.add("P");
    	activeStatuses.add("F");    	
    }
    
	@Override
	public void updateTimeAndMoneyDocumentStatusFromKew() {
		List<KewDocHeaderStatus> krewDocHeaderStatuses = krewDocHeaderDao.getTimeAndMoneyDocumentHeaderStatus();
		Connection connection = connectionDaoService.getCoeusConnection();
		try (PreparedStatement setFromKewStatement = connection.prepareStatement(UPDATE_TIME_AND_MONEY_DOC_STATUS);
			 PreparedStatement maxActiveDocStatement = connection.prepareStatement(MAX_ACTIVE_DOCUMENT_QUERY);
			 PreparedStatement setActiveStatement = connection.prepareStatement(UPDATE_ACTIVE_TIME_AND_MONEY_DOC_STATUS)) {
			for (KewDocHeaderStatus status : krewDocHeaderStatuses) {
				setString(1, getDocumentStatusFromKewStatus(status.documentStatusCode, status.documentHeaderId), 
						setString(2, status.documentHeaderId, setFromKewStatement));
				setFromKewStatement.execute();
			}
			try (ResultSet rs = maxActiveDocStatement.executeQuery()) {
				while (rs.next()) {
					setString(1, rs.getString(1), setActiveStatement).execute();
				}
			}
			LOG.info("Time and Money Document statuses have been updated successfully.");
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getDocumentStatusFromKewStatus(String statusCode, String documentNumber) {
		if (canceledStatuses.contains(statusCode)) {
			return CANCELED_STATUS;
		} else if (pendingStatuses.contains(statusCode)) {
			return PENDING_STATUS;
		} else if (activeStatuses.contains(statusCode)) {
			return ARCHIVED_STATUS;
		} else {
			LOG.warning("Unknown document status code encountered. Document Number = '" + documentNumber + "', Status = '" + statusCode + "'");
			return ARCHIVED_STATUS;
		}
	}

	public ConnectionDaoService getConnectionDaoService() {
		return connectionDaoService;
	}


	public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
		this.connectionDaoService = connectionDaoService;
	}


	public KewDocHeaderDao getKrewDocHeaderDao() {
		return krewDocHeaderDao;
	}


	public void setKewDocHeaderDao(KewDocHeaderDao krewDocHeaderDao) {
		this.krewDocHeaderDao = krewDocHeaderDao;
	}

}
