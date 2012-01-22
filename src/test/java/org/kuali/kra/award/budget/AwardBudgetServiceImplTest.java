/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.budget.bo.ProposalDevelopmentBudgetExt;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.impl.BusinessObjectServiceImpl;

public class AwardBudgetServiceImplTest extends KcUnitTestBase {

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
        
    }
    
    @Test
    public void testFindBudgetPeriodsFromLinkedProposal() {
        awardBudgetService = new AwardBudgetServiceImpl(){
            protected List findObjectsWithSingleKey(Class clazz,String key, Object value){
                return mockedFindObjectsWithSingleKey(clazz, key, value);
            }
        };
        ((AwardBudgetServiceImpl)awardBudgetService).setBusinessObjectService(new MyBOService());
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
            InstitutionalProposal proposal = new InstitutionalProposal();
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
        } else if (clazz == BudgetDocumentVersion.class) {
            assertEquals(devPropDocNumber, value);
            BudgetDocumentVersion temp = new BudgetDocumentVersion();
            BudgetVersionOverview overview = new BudgetVersionOverview();
            overview.setBudgetId(budgetId);
            temp.getBudgetVersionOverviews().add(overview);
            result.add(temp);
        }
        return result;
    }
    
    class MyBOService extends BusinessObjectServiceImpl {
        public MyBOService() { }
        
        @Override
        public <T extends BusinessObject> T findBySinglePrimaryKey(Class<T> clazz, Object value) {
            if (clazz == DevelopmentProposal.class) {
                assertEquals(devProposalNumber, value);
                DevelopmentProposal temp = new DevelopmentProposal();
                ProposalDevelopmentDocument doc = new ProposalDevelopmentDocument();
                doc.setDocumentNumber(devPropDocNumber);
                temp.setProposalDocument(doc);
                return (T) temp;
            } else if (clazz == ProposalDevelopmentBudgetExt.class) {
                assertEquals(budgetId, value);
                ProposalDevelopmentBudgetExt temp = new ProposalDevelopmentBudgetExt();
                temp.add(new BudgetPeriod());
                temp.add(new BudgetPeriod());
                temp.setFinalVersionFlag(true);
                return (T) temp;
            } else {
                return null;
            }
        }
    }
}
