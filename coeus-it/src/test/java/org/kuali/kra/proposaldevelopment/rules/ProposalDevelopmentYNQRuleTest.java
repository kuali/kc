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
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentRule;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
public class ProposalDevelopmentYNQRuleTest extends ProposalDevelopmentRuleTestBase{

    private ProposalDevelopmentDocumentRule rule = null;
    private List<ProposalYnq> proposalYnqs;
    private BusinessObjectService bos;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new ProposalDevelopmentDocumentRule();
        bos = KcServiceLocator.getService(BusinessObjectService.class);
        proposalYnqs = (List)bos.findAll(Ynq.class);
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        bos = null;
        proposalYnqs=null;
        super.tearDown();
    }

    /** 
     * 
     * This method is used to check ynqs are OK before do the real rule test
     */
    @Test
    public void checkCodes() {
        assertNotNull(proposalYnqs);
        assertTrue(proposalYnqs.size()>1);
    }


    /**
     * Test initial proposal ynq.
     * 
     * @throws Exception
     */
    @Test
    public void testInitialProposalYnq() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        assertTrue(rule.processProposalYNQBusinessRule(document, false));
    }

    /**
     * Test routing proposal ynq.
     * 
     * @throws Exception
     */
    @Test
    public void testRoutingProposalYnq() throws Exception {

        ProposalDevelopmentDocument document = getNewProposalDevelopmentDocument();
        document.getDevelopmentProposal().getYnqGroupNames();

        for (ProposalYnq ynq : document.getDevelopmentProposal().getProposalYnqs()) {
            ynq.setAnswer("Y");
            ynq.setExplanation("Because I said so");
            ynq.setReviewDate(new Date(System.currentTimeMillis()));
            
        }

        assertTrue(rule.processProposalYNQBusinessRule(document, true));
    }
}
