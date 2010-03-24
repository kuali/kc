/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.lookup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.budget.bo.ProposalDevelopmentBudgetExt;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.form.LookupForm;

import edu.mit.coeus.utils.xml.v2.budget.BUDGETDocument.BUDGET;

/**
 * This class...
 */
public class BudgetPeriodLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    /**
     * 
     * @see org.kuali.core.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map) It calls the
     *      S2sService#searchOpportunity service to look up the opportunity
     */
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        String awardId = fieldValues.get("budgetParentId");
        List<BudgetPeriod> budgetPeriods = findBudgetPeriodsFromLinkedProposal(awardId);
        return budgetPeriods;
    }
    @SuppressWarnings("unchecked")
    private List findObjectsWithSingleKey(Class clazz,String key, Object value){
        Map<String,Object> fieldValues = new HashMap<String,Object>();
        fieldValues.put(key, value);
        return (List)getBusinessObjectService().findMatching(clazz, fieldValues);
    }
    private List<BudgetPeriod> findBudgetPeriodsFromLinkedProposal(String awardId) {
        BusinessObjectService businessObjectService = getBusinessObjectService();
        List<AwardFundingProposal> fundingProposals = findObjectsWithSingleKey(AwardFundingProposal.class, "awardId",awardId);
        List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
        for (AwardFundingProposal fundingProposal : fundingProposals) {
            List<ProposalAdminDetails> proposalAdminDetails = findObjectsWithSingleKey(ProposalAdminDetails.class, 
                                                                            "instProposalId",fundingProposal.getProposalId());
            for (ProposalAdminDetails proposalAdminDetail : proposalAdminDetails) {
                String developmentProposalNumber = proposalAdminDetail.getDevProposalNumber();
                DevelopmentProposal proposalDevelopmentDocument = businessObjectService.findBySinglePrimaryKey(
                                                                        DevelopmentProposal.class, developmentProposalNumber);
                List<BudgetDocumentVersion> budgetDocumentVersions =  findObjectsWithSingleKey(BudgetDocumentVersion.class, 
                                                                            "parentDocumentKey", proposalDevelopmentDocument.getProposalDocument().getDocumentNumber());
                for (BudgetDocumentVersion budgetDocumentVersion : budgetDocumentVersions) {
                    Budget budget = getBusinessObjectService().findBySinglePrimaryKey(ProposalDevelopmentBudgetExt.class, 
                                                                    budgetDocumentVersion.getBudgetVersionOverview().getBudgetId());
                    if(budget.isFinalVersionFlag()){
                        budgetPeriods.addAll(budget.getBudgetPeriods());
                    }
                }
            }
        }
        return budgetPeriods;
    }
}
