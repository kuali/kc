package org.kuali.kra.s2s;


import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.s2s.service.impl.S2SServiceImpl;

import edu.mit.coeus.utils.S2SConstants;
import edu.mit.coeus.utils.dbengine.DBEngineImpl;
import edu.mit.coeus.utils.dbengine.DBException;

public class FindOpportunityTest extends KraTestBase implements S2SConstants{
    private static final Logger LOG = Logger.getLogger(S2SServiceImpl.class);
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
    @Test
    public void parseOpportunityTest(){
        List<S2sOpportunity> l = getS2SService().searchOpportunity("84.264", "ED-GRANTS-102003-003", null);
        assertNotNull(l);
        assertTrue(l.size()>0);
        S2sOpportunity opp = l.get(0);
        List<S2sOppForms> oppForms = getS2SService().parseOpportunityForms(opp);
        assertNotNull(oppForms);
        assertTrue(oppForms.size()>0);
        LOG.info(oppForms);
    }
    public final void testConnectivity() throws Exception{
		DBEngineImpl db = new DBEngineImpl();
		Connection con = null;
		try {
			con = db.beginTxn();
		}finally{
			db.endTxn(con);
		}
		
	}
}
