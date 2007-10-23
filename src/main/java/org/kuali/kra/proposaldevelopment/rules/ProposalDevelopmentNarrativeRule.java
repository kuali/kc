/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import java.util.List;

import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;
//import org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule;
import org.kuali.kra.rules.ResearchDocumentRuleBase;


/**
 * Implementation of business rules required for the Key Persons Page of the 
 * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>.
 *
 * @see org.kuali.core.rules.BusinessRule
 * @author $Author: shyu $
 * @version $Revision: 1.5 $
 */
public class ProposalDevelopmentNarrativeRule extends ResearchDocumentRuleBase implements AddNarrativeRule{ 
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentKeyPersonsRule.class);

    public boolean processAddNarrativeBusinessRules(AddNarrativeEvent narrativeEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)narrativeEvent.getDocument();
        Narrative narrative = narrativeEvent.getNarrative();
        List<Narrative> narrList = document.getNarratives();
        if(narrative.getNarrativeType().getAllowMultiple().equalsIgnoreCase("N")){
            for (Narrative narr : narrList) {
                if(narr.getProposalNumber().equals(narrative.getProposalNumber()) &&
                        narr.getNarrativeTypeCode().equals(narrative.getNarrativeTypeCode())){
                    String[] param = {"Proposal", narrative.getNarrativeType().getDescription()};
                    String propertyName="newNarrative.narrativeTypeCode";
                    String errorPath="newNarrative";
                    LOG.debug("error.proposalAttachment.narrativeType.allowMulitple");
//                    reportErrorWithPrefix("newNarrative", "narrativeTypeCode", "error.proposalAttachment.narrativeType.allowMulitple",param);
                    if (narrative.getNarrativeType().getNarrativeTypeGroup().equals(Constants.INSTITUTE_NARRATIVE_TYPE_GROUP_CODE)) {
                        GlobalVariables.getErrorMap().removeFromErrorPath(errorPath);
                        errorPath="newInstitute";
                        GlobalVariables.getErrorMap().addToErrorPath(errorPath);
                        param[0]="Institute";
                        propertyName="newInstitute.institutionalAttachmentTypeCode";
                    }
                    reportError("newNarrative.narrativeTypeCode", "error.proposalAttachment.narrativeType.allowMulitple",param);
                    GlobalVariables.getErrorMap().removeFromErrorPath(errorPath);
//                    GlobalVariables.getErrorMap().putError("newNarrative.narrativeTypeCode", "error.proposalAttachment.narrativeType.allowMulitple",param);
                    return false; 
                }
            }
        }
        return true;
    }
    /**
     * @see org.kuali.kra.rules.ResearchDocumentRuleBase#reportError(String, String, String...)
     */
    protected void reportErrorWithPrefix(String errorPathPrefix, String propertyName, String errorKey, String... errorParams) {
        GlobalVariables.getErrorMap().addToErrorPath(errorPathPrefix);
        super.reportError(propertyName, errorKey, errorParams);
        GlobalVariables.getErrorMap().removeFromErrorPath(errorPathPrefix);        
    }
    
}
