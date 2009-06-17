/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.kra.infrastructure.KeyConstants.ERROR_ATTACHMENT_TYPE_NOT_SELECTED;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.PropPerDocType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddPersonnelAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.SavePersonnelAttachmentRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddPersonnelAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SavePersonnelAttachmentEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProposalDevelopmentPersonnelAttachmentRule extends ResearchDocumentRuleBase implements AddPersonnelAttachmentRule, SavePersonnelAttachmentRule {
    public static final String OTHER_DOCUMENT_TYPE_DESCRIPTION = "Other";
    
    private static final String DOC_TYPE_DESCRIPTION = "description";
    private static final String DOCUMENT_TYPE_CODE = "documentTypeCode";
    private static final String FILE_NAME_PARM = "File Name";
    private static final String NEW_PROP_PERSON_BIO_PREFIX = "newPropPersonBio.";
    private static final String PERSONNEL_ATTACHMENT_FILE = "personnelAttachmentFile";
    private static final String PROPOSAL_PERSON_NUMBER = "proposalPersonNumber";
    
    public static String buildErrorPath(String endPath) {
        return NEW_PROP_PERSON_BIO_PREFIX + endPath;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.rule.AddPersonnelAttachmentRule#processAddPersonnelAttachmentBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddPersonnelAttachmentEvent)
     */
    public boolean processAddPersonnelAttachmentBusinessRules(AddPersonnelAttachmentEvent addPersonnelAttachmentEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)addPersonnelAttachmentEvent.getDocument();
        ProposalPersonBiography proposalPersonBiography = addPersonnelAttachmentEvent.getProposalPersonBiography();
        boolean rulePassed = true;

        if(StringUtils.isBlank(proposalPersonBiography.getDocumentTypeCode())){
            rulePassed = false;
            reportError(buildErrorPath(DOCUMENT_TYPE_CODE), ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
        }
        if(proposalPersonBiography.getProposalPersonNumber() == null || StringUtils.isBlank(proposalPersonBiography.getProposalPersonNumber().toString())){
            rulePassed = false;
            reportError(buildErrorPath(PROPOSAL_PERSON_NUMBER), KeyConstants.ERROR_PERSONNEL_ATTACHMENT_PERSON_REQUIRED);
        }
        if (StringUtils.isBlank(proposalPersonBiography.getFileName())) {
            rulePassed = false;
            reportError(buildErrorPath(PERSONNEL_ATTACHMENT_FILE), KeyConstants.ERROR_REQUIRED_FOR_FILE_NAME, FILE_NAME_PARM);
        }
        
        rulePassed &= checkForDescriptionWhenTypeIsOther(proposalPersonBiography);
        
        List<ProposalPersonBiography> existingPersonBiographyList = document.getPropPersonBios();
        if(CollectionUtils.isNotEmpty(existingPersonBiographyList)){
            //Loop thru to filter attachment uploaded by the current user
            for(ProposalPersonBiography personBiography: existingPersonBiographyList) {
                if(personBiography.getProposalPersonNumber().equals(proposalPersonBiography.getProposalPersonNumber())
                        && personBiography.getDocumentTypeCode().equals(proposalPersonBiography.getDocumentTypeCode())){
                    rulePassed = false;
                    reportError(buildErrorPath(DOCUMENT_TYPE_CODE), KeyConstants.ERROR_PERSONNEL_ATTACHMENT_PERSON_DUPLICATE);
                }
            }
        }
        
        return rulePassed;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.rule.SavePersonnelAttachmentRule#processSavePersonnelAttachmentBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.SavePersonnelAttachmentEvent)
     */
    public boolean processSavePersonnelAttachmentBusinessRules(SavePersonnelAttachmentEvent savePersonnelAttachmentEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) savePersonnelAttachmentEvent.getDocument();
        ProposalPersonBiography proposalPersonBiography = savePersonnelAttachmentEvent.getProposalPersonBiography();
        boolean rulePassed = true;
        
        List<ProposalPersonBiography> existingPersonBiographyList = document.getPropPersonBios();
        if(CollectionUtils.isNotEmpty(existingPersonBiographyList)){
            //Loop thru to filter attachment uploaded by the current user
            for(ProposalPersonBiography personBiography: existingPersonBiographyList) {
                if(personBiography.getProposalPersonNumber().equals(proposalPersonBiography.getProposalPersonNumber())
                        && personBiography.getDocumentTypeCode().equals(proposalPersonBiography.getDocumentTypeCode())){
                    rulePassed = false;
                    reportError(buildErrorPath(DOCUMENT_TYPE_CODE), KeyConstants.ERROR_PERSONNEL_ATTACHMENT_PERSON_DUPLICATE);
                }
                
                rulePassed &= checkForDescriptionWhenTypeIsOther(personBiography);
            }
        }

        return rulePassed;
    }
    
    /**
     * This method looks up the "Other" PropPerDocType. Method is protected to allow stubbing/mocking out the class
     * @return
     */
    @SuppressWarnings("unchecked")
    protected PropPerDocType findPropPerDocTypeForOther() {
        Map<String,String> narrativeTypeMap = new HashMap<String,String>();
        narrativeTypeMap.put(DOC_TYPE_DESCRIPTION, OTHER_DOCUMENT_TYPE_DESCRIPTION);
        BusinessObjectService service = getService(BusinessObjectService.class);
        return ((List<PropPerDocType>)service.findMatching(PropPerDocType.class, narrativeTypeMap)).get(0);        
    }
    
    /**
     * This method does what its name says
     * @param proposalPersonBiography
     * @return
     */
    private boolean checkForDescriptionWhenTypeIsOther(ProposalPersonBiography proposalPersonBiography) {
        boolean rulePassed = true;
        
        if(findPropPerDocTypeForOther().getDocumentTypeCode().equalsIgnoreCase(proposalPersonBiography.getDocumentTypeCode())) {
            rulePassed = !StringUtils.isBlank(proposalPersonBiography.getDescription());
            if(!rulePassed) {
                reportError(buildErrorPath(DOC_TYPE_DESCRIPTION), KeyConstants.ERROR_PERSONNEL_ATTACHMENT_DESCRIPTION_REQUIRED);
            }
        }
        
        return rulePassed;
    }
}
