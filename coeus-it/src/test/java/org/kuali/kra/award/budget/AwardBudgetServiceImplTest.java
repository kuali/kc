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
package org.kuali.kra.award.budget;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalBoLite;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.data.DataObjectService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
public class AwardBudgetServiceImplTest extends KcIntegrationTestBase {

	protected Mockery context;
    protected AwardBudgetService awardBudgetService;
    protected String testAwardNumber = "000000-00000";
    protected Long awardId = 1L;
    protected String proposalNumber = "111";
    protected Long proposalId = 2L;
    protected String devProposalNumber = "59";
    protected String devPropDocNumber = "55555";
    protected Long budgetId = 3L;
    
    @Before
    public void setUp() {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    }
    
    @Test
    public void testFindBudgetPeriodsFromLinkedProposal() {
        awardBudgetService = new AwardBudgetServiceImpl(){
            protected List findMatching(Class clazz, String key, Object value){
                return mockedFindObjectsWithSingleKey(clazz, key, value);
            }
        };
        
        final DevelopmentProposal temp = new DevelopmentProposal();
        ProposalDevelopmentDocument doc = new ProposalDevelopmentDocument();
        doc.setDocumentNumber(devPropDocNumber);
        ProposalDevelopmentBudgetExt temp2 = new ProposalDevelopmentBudgetExt();
        temp2.add(new BudgetPeriod());
        temp2.add(new BudgetPeriod());
        temp.setFinalBudget(temp2);
        temp.setProposalDocument(doc);
        
        final DataObjectService doService = context.mock(DataObjectService.class);
        context.checking(new Expectations() {{
            one(doService).find(DevelopmentProposal.class, devProposalNumber);
            will(returnValue(temp));
        }});
        ((AwardBudgetServiceImpl)awardBudgetService).setDataObjectService(doService);
        
        List<BudgetPeriod> periods = awardBudgetService.findBudgetPeriodsFromLinkedProposal(testAwardNumber);
        assertTrue(periods.size() == 2);
        assertEquals(proposalNumber, periods.get(0).getInstitutionalProposalNumber());
    }
    
    @SuppressWarnings("unchecked")
    protected List mockedFindObjectsWithSingleKey(Class clazz, String key, Object value) {
        List result = new ArrayList();
        if (clazz == Award.class) {
            assertEquals(testAwardNumber, value);
            Award temp = new Award();
            temp.setAwardId(awardId);
            result.add(temp);
        } else if (clazz == AwardFundingProposal.class) {
            assertEquals(awardId, value);
            AwardFundingProposal temp = new AwardFundingProposal();
            temp.setActive(true);
            InstitutionalProposalBoLite proposal = new InstitutionalProposalBoLite();
            proposal.setProposalNumber(proposalNumber);
            temp.setProposal(proposal);
            result.add(temp);
            temp = new AwardFundingProposal();
            temp.setActive(false);
            result.add(temp);
        } else if (clazz == InstitutionalProposal.class) {
            assertEquals(proposalNumber, value);
            InstitutionalProposal temp = new InstitutionalProposal();
            temp.setProposalId(proposalId);
            temp.setProposalNumber(proposalNumber);
            temp.setSequenceNumber(1);
            result.add(temp);
        } else if (clazz == ProposalAdminDetails.class) {
            assertEquals(proposalId, value);
            ProposalAdminDetails temp = new ProposalAdminDetails();
            temp.setDevProposalNumber(devProposalNumber);
            result.add(temp);
        }
        return result;
    }
}
