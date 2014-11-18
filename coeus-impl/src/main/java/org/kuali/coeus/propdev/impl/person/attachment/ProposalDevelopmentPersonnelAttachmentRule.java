/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.person.attachment;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.kuali.kra.infrastructure.KeyConstants.ERROR_ATTACHMENT_TYPE_NOT_SELECTED;


public class ProposalDevelopmentPersonnelAttachmentRule extends KcTransactionalDocumentRuleBase implements AddPersonnelAttachmentRule, SavePersonnelAttachmentRule, ReplacePersonnelAttachmentRule {
    public static final String OTHER_DOCUMENT_TYPE_DESCRIPTION = "Other";
    
    private static final String DOC_TYPE_DESCRIPTION = "description";
    private static final String DOCUMENT_TYPE_CODE = "documentTypeCode";
    private static final String PERSONNEL_ATTACHMENT_FILE = "multipartFile";
    private static final String PROPOSAL_PERSON_NUMBER = "proposalPersonNumberString";

    private transient KcAttachmentService kcAttachmentService;
    private transient ParameterService parameterService;
    private transient GlobalVariableService globalVariableService;

    @Override
    public boolean processAddPersonnelAttachmentBusinessRules(AddPersonnelAttachmentEvent addPersonnelAttachmentEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)addPersonnelAttachmentEvent.getDocument();
        ProposalPersonBiography proposalPersonBiography = addPersonnelAttachmentEvent.getProposalPersonBiography();
        boolean rulePassed = true;

        if(StringUtils.isBlank(proposalPersonBiography.getDocumentTypeCode())){
            rulePassed = false;
            reportError(DOCUMENT_TYPE_CODE, ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
        }

        if(findPropPerDocTypeForOther().getCode().equalsIgnoreCase(proposalPersonBiography.getDocumentTypeCode())) {
            if(StringUtils.isBlank(proposalPersonBiography.getDescription())) {
                reportError(DOC_TYPE_DESCRIPTION, KeyConstants.ERROR_PERSONNEL_ATTACHMENT_DESCRIPTION_REQUIRED);
                rulePassed = false;
            }
        }

        rulePassed &= checkForInvalidCharacters(proposalPersonBiography);
        rulePassed &= checkForValidFileType(proposalPersonBiography);
        rulePassed &= checkForProposalPerson(proposalPersonBiography);
        
        rulePassed &= checkForDuplicates(proposalPersonBiography,document.getDevelopmentProposal().getPropPersonBios());
        
        return rulePassed;
    }

    @Override
    public boolean processReplacePersonnelAttachmentBusinessRules(ReplacePersonnelAttachmentEvent event) {
        boolean rulePassed = checkForInvalidCharacters(event.getProposalPersonBiography());
        rulePassed &= checkForValidFileType(event.getProposalPersonBiography());
        return rulePassed;
    }

    @Override
    public boolean processSavePersonnelAttachmentBusinessRules(SavePersonnelAttachmentEvent savePersonnelAttachmentEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) savePersonnelAttachmentEvent.getDocument();
        ProposalPersonBiography proposalPersonBiography = savePersonnelAttachmentEvent.getProposalPersonBiography();
        boolean rulePassed = true;
        
        List<ProposalPersonBiography> existingPersonBiographyList = document.getDevelopmentProposal().getPropPersonBios();
        if(CollectionUtils.isNotEmpty(existingPersonBiographyList)){
            //Loop thru to filter attachment uploaded by the current user
            int index=0;
            for(ProposalPersonBiography personBiography: existingPersonBiographyList) {
                if(findPropPerDocTypeForOther().getCode().equalsIgnoreCase(personBiography.getDocumentTypeCode())) {
                    if(StringUtils.isBlank(personBiography.getDescription())) {
                        reportError("document.developmentProposal.propPersonBios", KeyConstants.ERROR_PERSONNEL_ATTACHMENT_DESCRIPTION_REQUIRED);
                        rulePassed = false;
                    }
                }
                getGlobalVariableService().getMessageMap().clearErrorPath();
                getGlobalVariableService().getMessageMap().getErrorPath().add("document.developmentProposal.propPersonBios[" + index +"]");
                rulePassed &= checkForInvalidCharacters(personBiography);
                rulePassed &= checkForValidFileType(personBiography);
                rulePassed &= checkForDuplicates(personBiography,existingPersonBiographyList);
                rulePassed &= checkForProposalPerson(proposalPersonBiography);
                index++;
            }
        }

        return rulePassed;
    }

    private boolean checkForDuplicates(ProposalPersonBiography biography, List<ProposalPersonBiography> existingBiographies) {
        boolean rulePassed = true;
        if(CollectionUtils.isNotEmpty(existingBiographies) && biography.getProposalPersonNumber() != null){
            for(ProposalPersonBiography personBiography: existingBiographies) {
                if(personBiography.getProposalPersonNumber() != null && personBiography.getDocumentTypeCode() != null &&
                        personBiography.getProposalPersonNumber().equals(biography.getProposalPersonNumber())
                        && personBiography.getDocumentTypeCode().equals(biography.getDocumentTypeCode())){

                	if(personBiography.getBiographyNumber().equals(biography.getBiographyNumber())) {
	                    rulePassed = false;
	                    reportError(DOCUMENT_TYPE_CODE, KeyConstants.ERROR_PERSONNEL_ATTACHMENT_PERSON_DUPLICATE);
                	}
                }
            }
        }
        return rulePassed;
    }

    protected boolean  checkForValidFileType(ProposalPersonBiography proposalPersonBiography) {
        if (!Constants.PDF_REPORT_CONTENT_TYPE.equals(proposalPersonBiography.getType())) {
            reportWarning(PERSONNEL_ATTACHMENT_FILE, KeyConstants.INVALID_FILE_TYPE,
                    proposalPersonBiography.getName(), Constants.PDF_REPORT_CONTENT_TYPE);
        }

        return true;
    }

    protected boolean checkForInvalidCharacters(ProposalPersonBiography proposalPersonBiography) {
        KcAttachmentService attachmentService = getKcAttachmentService();
        boolean rulePassed = true;
        // Checking attachment file name for invalid characters.
        String attachmentFileName = proposalPersonBiography.getName();
        String invalidCharacters = attachmentService.getInvalidCharacters(attachmentFileName);
        if (ObjectUtils.isNotNull(invalidCharacters)) {
            String parameter = getParameterService().
                getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.INVALID_FILE_NAME_CHECK_PARAMETER);
            if (Constants.INVALID_FILE_NAME_ERROR_CODE.equals(parameter)) {
                rulePassed &= false;
                reportError(PERSONNEL_ATTACHMENT_FILE, KeyConstants.INVALID_FILE_NAME,
                        attachmentFileName, invalidCharacters);
            } else {
                rulePassed &= true;
                reportWarning(PERSONNEL_ATTACHMENT_FILE, KeyConstants.INVALID_FILE_NAME,
                        attachmentFileName, invalidCharacters);
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
        BusinessObjectService service = getBusinessObjectService();
        return ((List<PropPerDocType>)service.findMatching(PropPerDocType.class, narrativeTypeMap)).get(0);        
    }

    private boolean checkForProposalPerson(ProposalPersonBiography proposalPersonBiography) {
        boolean rulePassed= true;
        if(proposalPersonBiography.getProposalPersonNumber() == null || StringUtils.isBlank(proposalPersonBiography.getProposalPersonNumber().toString())){
            rulePassed = false;
            reportError(PROPOSAL_PERSON_NUMBER, KeyConstants.ERROR_PERSONNEL_ATTACHMENT_PERSON_REQUIRED);
        }
        return rulePassed;
    }
    
    /**
     * This method returns the kc attachment service
     * @return
     */
    protected KcAttachmentService getKcAttachmentService() {
        if(this.kcAttachmentService == null) {
            this.kcAttachmentService = KcServiceLocator.getService(KcAttachmentService.class);
        }
        return this.kcAttachmentService;
    }
    /**
     * Gets the parameter service.
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null ) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }

    public GlobalVariableService getGlobalVariableService() {
        if (this.globalVariableService == null ) {
            this.globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return globalVariableService;
    }
}
