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
