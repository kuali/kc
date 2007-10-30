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

import static org.kuali.kra.infrastructure.KeyConstants.ERROR_NARRATIVE_TYPE_DUPLICATE;

import java.util.List;

import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule;
import org.kuali.kra.proposaldevelopment.rule.SaveNarrativesRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;


/**
 * Implementation of business rules required for the Key Persons Page of the 
 * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>.
 *
 * @see org.kuali.core.rules.BusinessRule
 * @author $Author: gthomas $
 * @version $Revision: 1.6 $
 */
public class ProposalDevelopmentNarrativeRule extends ResearchDocumentRuleBase implements AddNarrativeRule,SaveNarrativesRule{ 
    private static final String NARRATIVE_TYPE_ALLOWMULTIPLE_NO = "N";
    private static final String NEW_NARRATIVE = "newNarrative";
    private static final String INSTITUTE = "Institute";
    private static final String NEW_INSTITUTE = "newInstitute";
    private static final String DOCUMENT_NARRATIVES = "document.narratives";
    private static final String PROPOSAL = "Proposal";
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentKeyPersonsRule.class);
    /**
     * This method is used to validate narratives and institute proposal attachments before adding.
     * It checks whether the narratives are duplicated for those of which have allowMultiple flag set as false.
     * @see org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule#processAddNarrativeBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent)
     */
    public boolean processAddNarrativeBusinessRules(AddNarrativeEvent narrativeEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)narrativeEvent.getDocument();
        Narrative narrative = narrativeEvent.getNarrative();
        List<Narrative> narrList = document.getNarratives();
        if(isDuplicate(narrList, narrative)){
            String[] param = {PROPOSAL, narrative.getNarrativeType().getDescription()};
            String errorPath=NEW_NARRATIVE;
            if (narrative.getNarrativeType().getNarrativeTypeGroup().equals(Constants.INSTITUTE_NARRATIVE_TYPE_GROUP_CODE)) {
                GlobalVariables.getErrorMap().removeFromErrorPath(errorPath);
                errorPath=NEW_INSTITUTE;
                GlobalVariables.getErrorMap().addToErrorPath(errorPath);
                param[0]=INSTITUTE;
            }
            LOG.debug(ERROR_NARRATIVE_TYPE_DUPLICATE);
            reportError(DOCUMENT_NARRATIVES, ERROR_NARRATIVE_TYPE_DUPLICATE,param);
            return false; 
        }
        return true;
    }
    /**
     * This method is used to validate narratives and institute proposal attachments before saving.
     * It checks whether the narratives are duplicated for those of which have allowMultiple flag set as false.
     * @see org.kuali.kra.proposaldevelopment.rule.SaveNarrativesRule#processSaveNarrativesBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent)
     */
    public boolean processSaveNarrativesBusinessRules(SaveNarrativesEvent saveNarrativesEvent) {
        List<Narrative> narrativeList = saveNarrativesEvent.getNarratives();
        int size = narrativeList.size();
        for (int i = 0; i < size; i++) {
            Narrative narrative = narrativeList.get(i);
            narrativeList.remove(narrative);
            --size;
            if(isDuplicate(narrativeList,narrative)){
                String[] param = {PROPOSAL, narrative.getNarrativeType().getDescription()};
                String errorPath=DOCUMENT_NARRATIVES;
                if (narrative.getNarrativeType().getNarrativeTypeGroup().equals(Constants.INSTITUTE_NARRATIVE_TYPE_GROUP_CODE)) {
                    GlobalVariables.getErrorMap().removeFromErrorPath(errorPath);
                    errorPath=NEW_INSTITUTE;
                    GlobalVariables.getErrorMap().addToErrorPath(errorPath);
                    param[0]=INSTITUTE;
                }
                LOG.debug(ERROR_NARRATIVE_TYPE_DUPLICATE);
                reportError(errorPath,ERROR_NARRATIVE_TYPE_DUPLICATE,param);
            }
//            else{
//                narrativeList.remove(narrative);
//                --size;
//            }
        }
        return false;
    }
    private boolean isDuplicate(List<Narrative> narrativeList, Narrative narrative) {
        if(narrative.getNarrativeType().getAllowMultiple().equalsIgnoreCase(NARRATIVE_TYPE_ALLOWMULTIPLE_NO)){
            for (Narrative narr : narrativeList) {
                if(narr.getProposalNumber().equals(narrative.getProposalNumber()) &&
                        narr.getNarrativeTypeCode().equals(narrative.getNarrativeTypeCode())){
                    return true; 
                }
            }
        }
        return false;
    }
    
}
