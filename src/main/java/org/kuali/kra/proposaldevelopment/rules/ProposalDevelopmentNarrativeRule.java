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

import static org.kuali.kra.infrastructure.KeyConstants.ERROR_ATTACHMENT_STATUS_NOT_SELECTED;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_ATTACHMENT_TYPE_NOT_SELECTED;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_NARRATIVE_TYPE_DESCRITPION_REQUIRED;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_NARRATIVE_TYPE_DUPLICATE;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule;
import org.kuali.kra.proposaldevelopment.rule.SaveNarrativesRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;


/**
 * Implementation of business rules required for the Proposal attachment page of the 
 * <code>{@link org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument}</code>.
 *
 * @see org.kuali.core.rules.BusinessRule
 * @author $Author: gthomas $
 * @version $Revision: 1.8 $
 */
public class ProposalDevelopmentNarrativeRule extends ResearchDocumentRuleBase implements AddNarrativeRule,SaveNarrativesRule{ 
    private static final String NARRATIVE_TYPE_ALLOWMULTIPLE_NO = "N";
    private static final String INSTITUTE = "Institute";
    private static final String NEW_INSTITUTE = "newInstitute";
    private static final String DOCUMENT_NARRATIVES = "document.narratives";
    private static final String PROPOSAL = "Proposal";
    private static final String NARRATIVE_TYPE_CODE = "narrativeTypeCode";

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentKeyPersonsRule.class);
    /**
     * This method is used to validate narratives and institute proposal attachments before adding.
     * It checks whether the narratives are duplicated for those of which have allowMultiple flag set as false.
     * @see org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule#processAddNarrativeBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent)
     */
    public boolean processAddNarrativeBusinessRules(AddNarrativeEvent narrativeEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)narrativeEvent.getDocument();
        Narrative narrative = narrativeEvent.getNarrative();
        boolean rulePassed = true;
        List<Narrative> narrList = document.getNarratives();
        rulePassed &= checkNarrative(narrList, narrative);
        return rulePassed;
    }
    /**
     * This method is used to validate narratives and institute proposal attachments before saving.
     * It checks whether the narratives are duplicated for those of which have allowMultiple flag set as false.
     * @see org.kuali.kra.proposaldevelopment.rule.SaveNarrativesRule#processSaveNarrativesBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent)
     */
    public boolean processSaveNarrativesBusinessRules(SaveNarrativesEvent saveNarrativesEvent) {
        List<Narrative> narrativeList = saveNarrativesEvent.getNarratives();
        int size = narrativeList.size();
        boolean rulePassed = false;
        for (int i = 0; i < size; i++) {
            Narrative narrative = narrativeList.get(i);
            narrativeList.remove(narrative);
            --size;
            rulePassed &= checkNarrative(narrativeList,narrative);
        }
        return rulePassed;
    }
    /**
     * It checks for duplicate narrative types and mandatory description for narrative type 'Other'
     * This method...
     * @param narrativeList
     * @param narrative
     * @return true if rules passed, else false
     */
    private boolean checkNarrative(List<Narrative> narrativeList, Narrative narrative) {
        populateNarrativeType(narrative);
        String[] param = {PROPOSAL, narrative.getNarrativeType().getDescription()};
        String errorPath=DOCUMENT_NARRATIVES;
        boolean rulePassed = true;
        if(narrative.getNarrativeTypeCode()==null){
            rulePassed = false;
            reportError(DOCUMENT_NARRATIVES, ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
        }
        if(narrative.getModuleStatusCode()==null){
            rulePassed = false;
            reportError(DOCUMENT_NARRATIVES, ERROR_ATTACHMENT_STATUS_NOT_SELECTED);
        }
        if (rulePassed) {
            if (narrative.getNarrativeType().getNarrativeTypeGroup().equals(Constants.INSTITUTE_NARRATIVE_TYPE_GROUP_CODE)) {
                GlobalVariables.getErrorMap().removeFromErrorPath(errorPath);
                errorPath = NEW_INSTITUTE;
                GlobalVariables.getErrorMap().addToErrorPath(errorPath);
                param[0] = INSTITUTE;
            }
            if (narrative.getNarrativeType().getAllowMultiple().equalsIgnoreCase(NARRATIVE_TYPE_ALLOWMULTIPLE_NO)) {
                for (Narrative narr : narrativeList) {
                    if (narr.getNarrativeTypeCode().equals(narrative.getNarrativeTypeCode())) {
                        LOG.debug(ERROR_NARRATIVE_TYPE_DUPLICATE);
                        reportError(errorPath, ERROR_NARRATIVE_TYPE_DUPLICATE, param);
                        rulePassed = false;
                    }
                }
            }else if (StringUtils.isBlank(narrative.getModuleTitle())) {
                reportError(errorPath, ERROR_NARRATIVE_TYPE_DESCRITPION_REQUIRED, param);
                rulePassed = false;
            }
        }
        return rulePassed;
    }
    private void populateNarrativeType(Narrative narrative) {
        Map<String,String> narrativeTypeMap = new HashMap<String,String>();
        narrativeTypeMap.put(NARRATIVE_TYPE_CODE, narrative.getNarrativeTypeCode());
        BusinessObjectService service = getService(BusinessObjectService.class);
        NarrativeType narrType = (NarrativeType) service.findByPrimaryKey(NarrativeType.class, narrativeTypeMap);
        if (narrType != null)
            narrative.setNarrativeType(narrType);
        
    }
    
}
