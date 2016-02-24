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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;


public class KewDocHeaderDaoImpl implements KewDocHeaderDao {
	
    private static final String TIME_AND_MONEY_DOCUMENT = "TimeAndMoneyDocument";
	private static final String DOCUMENT_HEADER_QUERY = "select DOC_HDR_ID, DOC_HDR_STAT_CD from KREW_DOC_HDR_T where DOC_TYP_ID in (select doc_typ_id from KREW_DOC_TYP_T where DOC_TYP_NM = ?)";

	private static final Logger LOG = Logger.getLogger(KewDocHeaderDaoImpl.class.getName());

    private ConnectionDaoService connectionDaoService;
    
    public List<KewDocHeaderStatus> getTimeAndMoneyDocumentHeaderStatus() {
    	Connection connection = connectionDaoService.getRiceConnection();
    	List<KewDocHeaderStatus> documentStatuses = new ArrayList<KewDocHeaderStatus>();
    	
    	try (PreparedStatement stmt = setString(1, TIME_AND_MONEY_DOCUMENT, 
    			connection.prepareStatement(DOCUMENT_HEADER_QUERY));
    		 ResultSet rs = stmt.executeQuery()) {
    		while (rs.next()) {
    			documentStatuses.add(new KewDocHeaderStatus(rs.getString(1), rs.getString(2)));
    		}
    		if (documentStatuses.isEmpty()) {
    			LOG.warning("No " + TIME_AND_MONEY_DOCUMENT + " documents exist.");
    		}
    	} catch (SQLException e) {
    		throw new RuntimeException(e);
    	}
    	return documentStatuses;
    }

	public ConnectionDaoService getConnectionDaoService() {
		return connectionDaoService;
	}

	public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
		this.connectionDaoService = connectionDaoService;
	}

}
