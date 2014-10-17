/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
            protected List findObjectsWithSingleKey(Class clazz,String key, Object value){
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
