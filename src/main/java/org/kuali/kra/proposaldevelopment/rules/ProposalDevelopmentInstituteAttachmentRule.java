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

import static org.kuali.kra.infrastructure.Constants.INSTITUTE_NARRATIVE_TYPE_GROUP;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_ATTACHMENT_STATUS_NOT_SELECTED;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_ATTACHMENT_TYPE_NOT_SELECTED;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_NARRATIVE_TYPE_DUPLICATE;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddInstituteAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.SaveInstituteAttachmentsRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddInstituteAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveInstituteAttachmentsEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

public class ProposalDevelopmentInstituteAttachmentRule extends ResearchDocumentRuleBase implements AddInstituteAttachmentRule { 
    private static final String NARRATIVE_TYPE_ALLOWMULTIPLE_NO = "N";
    private static final String INSTITUTE = "Institute";
    private static final String NEW_INSTITUTE_ATTACHMENT = "newInstituteAttachment";
    private static final String NARRATIVE_TYPE_CODE = "narrativeTypeCode";
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentInstituteAttachmentRule.class);

    /**
     * This method is to validate :
     *   attachment type code, status code exist. 
     *   If desc/type are entered, then file name is required.
     *   If attachment type does not allow multiple entries, then ensure one entry only for that type.
     * @see org.kuali.kra.proposaldevelopment.rule.AddInstituteAttachmentRule#processAddInstituteAttachmentBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddInstituteAttachmentEvent)
     */
    public boolean processAddInstituteAttachmentBusinessRules(AddInstituteAttachmentEvent addInstituteAttachmentEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)addInstituteAttachmentEvent.getDocument();
        Narrative narrative = addInstituteAttachmentEvent.getNarrative();
        boolean rulePassed = true;
        populateNarrativeType(narrative);
        String errorPath = NEW_INSTITUTE_ATTACHMENT;
        if(narrative.getNarrativeType()==null)
            rulePassed = false;

        if(StringUtils.isBlank(narrative.getNarrativeTypeCode())){
            rulePassed = false;
            reportError(errorPath+".institutionalAttachmentTypeCode", ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
        }
        if(StringUtils.isBlank(narrative.getModuleStatusCode())){
            rulePassed = false;
            reportError(errorPath+".moduleStatusCode", ERROR_ATTACHMENT_STATUS_NOT_SELECTED);
        }
        if (rulePassed) {
            populateNarrativeType(narrative);
            String[] param = {INSTITUTE, narrative.getNarrativeType().getDescription()};
            String instituteNarrativeTypeGroup = getService(KualiConfigurationService.class).getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, INSTITUTE_NARRATIVE_TYPE_GROUP);
            if (narrative.getNarrativeType().getAllowMultiple().equalsIgnoreCase(NARRATIVE_TYPE_ALLOWMULTIPLE_NO)) {
                for (Narrative narr : document.getInstituteAttachments()) {
                    if (narr!=null && StringUtils.equals(narr.getNarrativeTypeCode(),narrative.getNarrativeTypeCode())) {
                        LOG.debug(ERROR_NARRATIVE_TYPE_DUPLICATE);
                        reportError(errorPath+".institutionalAttachmentTypeCode", ERROR_NARRATIVE_TYPE_DUPLICATE, param);
                        rulePassed = false;
                    }
                }
            }
        }
        if (rulePassed && StringUtils.isNotBlank(narrative.getNarrativeTypeCode()) && StringUtils.isNotBlank(narrative.getModuleTitle())) {
            if (StringUtils.isBlank(narrative.getFileName())) {
                rulePassed = false;
                reportError(errorPath+".narrativeFile", KeyConstants.ERROR_REQUIRED_FOR_FILE_NAME, "File Name");
            }
    }

        return rulePassed;
    }
    
    /**
     * 
     * This method is get narrative type reference which will be used to retrieve allow multiple indicator for rule checking.
     * @param narrative
     */
    private void populateNarrativeType(Narrative narrative) {
        Map<String,String> narrativeTypeMap = new HashMap<String,String>();
        narrativeTypeMap.put(NARRATIVE_TYPE_CODE, narrative.getNarrativeTypeCode());
        BusinessObjectService service = getService(BusinessObjectService.class);
        NarrativeType narrType = (NarrativeType) service.findByPrimaryKey(NarrativeType.class, narrativeTypeMap);
        if (narrType != null)
            narrative.setNarrativeType(narrType);
        
    }

}
