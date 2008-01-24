package org.kuali.kra.s2s;


import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.rice.KNSServiceLocator;

import edu.mit.coeus.utils.S2SConstants;
import edu.mit.coeus.utils.dbengine.DBEngineImpl;
import edu.mit.coeus.utils.dbengine.DBException;

public class FindOpportunityTest extends KraTestBase implements S2SConstants{
    private String proposalNumber;
    private DocumentService documentService;
    @Before
	public void setUp() throws Exception {
		super.setUp();
	}
    @After
	public void tearDown() throws Exception {
		super.tearDown();
	}
    private S2SService getS2SService(){
        return getService(S2SService.class);
    }
    @Test
    public void testSearchOpportunity(){
        List l = getS2SService().searchOpportunity("84.264", null, null);
        assertNotNull(l);
        assertTrue(l.size()>0);
        
    }
    public final void testConnectivity() {
		DBEngineImpl db = new DBEngineImpl();
		Connection con = null;
		try {
			con = db.beginTxn();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				db.endTxn(con);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
