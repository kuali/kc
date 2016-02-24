/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.person.attachment;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.*;
import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.ATTACHMENT_PERSONNEL_SECTION_ID;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_ATTACHMENT_TYPE_NOT_SELECTED;


public class ProposalDevelopmentPersonnelAttachmentRule extends KcTransactionalDocumentRuleBase implements AddPersonnelAttachmentRule, SavePersonnelAttachmentRule, ReplacePersonnelAttachmentRule {
    public static final String OTHER_DOCUMENT_TYPE_DESCRIPTION = "Other";
    
    private static final String DOC_TYPE_DESCRIPTION = "description";
    private static final String DOCUMENT_TYPE_CODE = "documentTypeCode";
    private static final String PERSONNEL_ATTACHMENT_FILE = "multipartFile";
    private static final String PROPOSAL_PERSON_NUMBER = "proposalPersonNumberString";

    private transient KcAttachmentService kcAttachmentService;
    private transient ParameterService parameterService;
    private transient ProposalPersonBiographyService proposalPersonBiographyService;

    @Override
    public boolean processAddPersonnelAttachmentBusinessRules(AddPersonnelAttachmentEvent addPersonnelAttachmentEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)addPersonnelAttachmentEvent.getDocument();
        ProposalPersonBiography proposalPersonBiography = addPersonnelAttachmentEvent.getProposalPersonBiography();
        boolean rulePassed = true;

        if(StringUtils.isBlank(proposalPersonBiography.getDocumentTypeCode())) {
            rulePassed = false;
            reportError(DOCUMENT_TYPE_CODE, ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
        }

        if(getProposalPersonBiographyService().findPropPerDocTypeForOther().getCode().equalsIgnoreCase(proposalPersonBiography.getDocumentTypeCode())) {
            if(StringUtils.isBlank(proposalPersonBiography.getDescription())) {
                reportError(DOC_TYPE_DESCRIPTION, KeyConstants.ERROR_PERSONNEL_ATTACHMENT_DESCRIPTION_REQUIRED);
                rulePassed = false;
            }
        }

        String attachmentFileName = proposalPersonBiography.getName();
        
        if(StringUtils.isBlank(attachmentFileName )) {
        	rulePassed = false;
            reportError(PERSONNEL_ATTACHMENT_FILE, KeyConstants.ERROR_REQUIRED_FOR_FILE_NAME);     
        } else {
            String invalidCharacters = getKcAttachmentService().getInvalidCharacters(attachmentFileName);
        	if (!checkForInvalidCharacters(invalidCharacters)){
	            String parameter = getParameterService().
	                    getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.INVALID_FILE_NAME_CHECK_PARAMETER);
	            if (Constants.INVALID_FILE_NAME_ERROR_CODE.equals(parameter)) {
	                rulePassed = false;
	                reportError(PERSONNEL_ATTACHMENT_FILE, KeyConstants.INVALID_FILE_NAME,
	                        attachmentFileName, invalidCharacters);
	            } else {
	                rulePassed &= true;
	                reportWarning(PERSONNEL_ATTACHMENT_FILE, KeyConstants.INVALID_FILE_NAME,
	                        attachmentFileName, invalidCharacters);
	            }
        	}
        }

        rulePassed &= getKcAttachmentService().validPDFFile(proposalPersonBiography, getErrorReporter(), PERSONNEL_ATTACHMENT_FILE);

        if (!checkForProposalPerson(proposalPersonBiography)) {
            rulePassed = false;
            reportError(PROPOSAL_PERSON_NUMBER, KeyConstants.ERROR_PERSONNEL_ATTACHMENT_PERSON_REQUIRED);
        }

        if (!checkForDuplicates(proposalPersonBiography,document.getDevelopmentProposal().getPropPersonBios())) {
            rulePassed = false;
            reportError(DOCUMENT_TYPE_CODE, KeyConstants.ERROR_PERSONNEL_ATTACHMENT_PERSON_DUPLICATE);
        }
        
        return rulePassed;
    }

    @Override
    public boolean processReplacePersonnelAttachmentBusinessRules(ReplacePersonnelAttachmentEvent event) {
        boolean retVal = checkForInvalidCharacters(getKcAttachmentService().getInvalidCharacters(event.getProposalPersonBiography().getName()));
        retVal &= getKcAttachmentService().validPDFFile(event.getProposalPersonBiography(), getErrorReporter(), PERSONNEL_ATTACHMENT_FILE);
        return retVal;
    }

    @Override
    public boolean processSavePersonnelAttachmentBusinessRules(SavePersonnelAttachmentEvent savePersonnelAttachmentEvent) {
        boolean retVal = true;
        ProposalPersonBiography biography = savePersonnelAttachmentEvent.getProposalPersonBiography();
        List<ProposalPersonBiography> biographies = ((ProposalDevelopmentDocument)savePersonnelAttachmentEvent.getDocument()).getDevelopmentProposal().getPropPersonBios();
        int index = Integer.parseInt(savePersonnelAttachmentEvent.getErrorPathPrefix());

        if (!checkForDescription(biography)) {
            retVal = false;
            getAuditErrors(ATTACHMENT_PERSONNEL_SECTION_NAME,AUDIT_ERRORS).add(new AuditError(BIOGRAPHIES_KEY, KeyConstants.ERROR_PERSONNEL_ATTACHMENT_DESCRIPTION_REQUIRED,ATTACHMENT_PAGE_ID + "." + ATTACHMENT_PERSONNEL_SECTION_ID));
        }
        if (!checkForInvalidCharacters(getKcAttachmentService().getInvalidCharacters(biography.getName()))) {
            retVal = false;
            String parameter = getParameterService().
                    getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.INVALID_FILE_NAME_CHECK_PARAMETER);
            if (Constants.INVALID_FILE_NAME_ERROR_CODE.equals(parameter)) {
                getAuditErrors(ATTACHMENT_PERSONNEL_SECTION_NAME,AUDIT_ERRORS).add(new AuditError(BIOGRAPHIES_KEY,KeyConstants.INVALID_FILE_NAME,ATTACHMENT_PAGE_ID+"."+ATTACHMENT_PERSONNEL_SECTION_ID));
            } else {
                getAuditErrors(ATTACHMENT_PERSONNEL_SECTION_NAME,AUDIT_WARNINGS).add(new AuditError(BIOGRAPHIES_KEY, KeyConstants.INVALID_FILE_NAME, ATTACHMENT_PAGE_ID + "." + ATTACHMENT_PERSONNEL_SECTION_ID));
            }
        }

        retVal &= getKcAttachmentService().validPDFFile(biography, getErrorReporter(), PERSONNEL_ATTACHMENT_FILE);

        if (!checkForDuplicates(biography,biographies)){
            retVal = false;
            getAuditErrors(ATTACHMENT_PERSONNEL_SECTION_NAME,AUDIT_ERRORS).add(new AuditError(String.format(BIOGRAPHY_TYPE_KEY,index),KeyConstants.ERROR_PERSONNEL_ATTACHMENT_PERSON_DUPLICATE,ATTACHMENT_PAGE_ID + "." + ATTACHMENT_PERSONNEL_SECTION_ID));
        }
        if (!checkForProposalPerson(biography)){
            retVal = false;
            getAuditErrors(ATTACHMENT_PERSONNEL_SECTION_NAME,AUDIT_ERRORS).add(new AuditError(String.format(BIOGRAPHY_PERSON_KEY,index),KeyConstants.ERROR_PERSONNEL_ATTACHMENT_PERSON_REQUIRED,ATTACHMENT_PAGE_ID + "." + ATTACHMENT_PERSONNEL_SECTION_ID));
        }
        return retVal;
    }

    private boolean checkForDescription(ProposalPersonBiography biography){
        boolean valid = true;
        String otherCode = getProposalPersonBiographyService().findPropPerDocTypeForOther().getCode();
        if(StringUtils.equalsIgnoreCase(otherCode,biography.getDocumentTypeCode())) {
            if(StringUtils.isBlank(biography.getDescription())) {
                valid = false;
            }
        }
        return valid;
    }

    private boolean checkForDuplicates(ProposalPersonBiography biography, List<ProposalPersonBiography> existingBiographies) {
        boolean rulePassed = true;
        if(CollectionUtils.isNotEmpty(existingBiographies) && biography.getProposalPersonNumber() != null){
            for(ProposalPersonBiography personBiography: existingBiographies) {
                if(personBiography.getProposalPersonNumber() != null && personBiography.getDocumentTypeCode() != null &&
                        !StringUtils.equalsIgnoreCase(getProposalPersonBiographyService().findPropPerDocTypeForOther().getCode(),personBiography.getDocumentTypeCode()) &&
                        personBiography.getProposalPersonNumber().equals(biography.getProposalPersonNumber())
                        && personBiography.getDocumentTypeCode().equals(biography.getDocumentTypeCode())){
                	if(!personBiography.getBiographyNumber().equals(biography.getBiographyNumber())) {
	                    rulePassed = false;
                	}
                }
            }
        }
        return rulePassed;
    }

    protected boolean checkForInvalidCharacters(String invalidCharacters) {
        boolean rulePassed = true;
        if (StringUtils.isNotEmpty(invalidCharacters)) {
            rulePassed = false;
        }
        return rulePassed;
    }

    private boolean checkForProposalPerson(ProposalPersonBiography proposalPersonBiography) {
        boolean rulePassed= true;
        if(proposalPersonBiography.getProposalPersonNumber() == null || StringUtils.isBlank(proposalPersonBiography.getProposalPersonNumber().toString())){
            rulePassed = false;
        }
        return rulePassed;
    }

    private List<AuditError> getAuditErrors(String sectionName, String severity ) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String clusterKey = ATTACHMENT_PAGE_NAME + "." + sectionName;
        if (!GlobalVariables.getAuditErrorMap().containsKey(clusterKey+severity)) {
            GlobalVariables.getAuditErrorMap().put(clusterKey+severity, new AuditCluster(clusterKey, auditErrors, severity));
        }
        else {
            auditErrors = GlobalVariables.getAuditErrorMap().get(clusterKey+severity).getAuditErrorList();
        }

        return auditErrors;
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

    public ProposalPersonBiographyService getProposalPersonBiographyService() {
        if (this.proposalPersonBiographyService == null ) {
            this.proposalPersonBiographyService = KcServiceLocator.getService(ProposalPersonBiographyService.class);
        }
        return proposalPersonBiographyService;
    }
}
