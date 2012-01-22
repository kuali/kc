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
package org.kuali.kra.award.home.fundingproposal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.keywords.AwardScienceKeyword;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalScienceKeyword;
import org.kuali.rice.krad.service.BusinessObjectService;

class KeywordsDataFeedCommand extends ProposalDataFeedCommandBase {
    
    public KeywordsDataFeedCommand(Award award, InstitutionalProposal proposal, FundingProposalMergeType mergeType) {
        super(award, proposal, mergeType);
    }
    

    @Override
    void performDataFeed() {
        if (mergeType != FundingProposalMergeType.NOCHANGE) {
            Collection <InstitutionalProposalScienceKeyword> results =null;
            if(!proposal.getProposalNumber().equals(InstitutionalProposal.PROPOSAL_NUMBER_TEST_DEFAULT_STRING)){
            Map<String, String> criteria = new HashMap<String, String>();
            criteria.put(InstitutionalProposal.PROPOSAL_NUMBER_PROPERTY_STRING, proposal.getProposalNumber());
            results = KraServiceLocator.getService(BusinessObjectService.class).findMatching(InstitutionalProposalScienceKeyword.class, criteria);
            }else{
                results=proposal.getInstitutionalProposalScienceKeywords();    
            }
            if(results!=null){
                for(InstitutionalProposalScienceKeyword ipKeyword: results) {
                    boolean duplicateFound = false;
                    for(AwardScienceKeyword awardKeyword: award.getKeywords()) {
                        if(isIdentical(awardKeyword, ipKeyword)) {
                            duplicateFound = true;
                            break;
                        }                
                    }
                    if(!duplicateFound) {
                        award.addKeyword(copyScienceKeyword(ipKeyword));
                    }
                }
            }
           
        }
    }

    private boolean isIdentical(AwardScienceKeyword awardKeyword, InstitutionalProposalScienceKeyword ipKeyword) {
        return awardKeyword.getScienceKeywordCode().equals(ipKeyword.getScienceKeywordCode());
    }
    
    private ScienceKeyword copyScienceKeyword(InstitutionalProposalScienceKeyword ipKeyWord) {
        ScienceKeyword keyword = ipKeyWord.getScienceKeyword();
        if(keyword == null) {
            keyword = new ScienceKeyword();
            keyword.setScienceKeywordCode(ipKeyWord.getScienceKeywordCode());
            keyword.setDescription(ipKeyWord.getScienceKeywordDescription());
        }
        
        return keyword;
    }
    
}
