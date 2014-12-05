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
package org.kuali.coeus.propdev.impl.s2s;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.api.s2s.S2sUserAttachedFormFileContract;
import org.kuali.coeus.propdev.api.s2s.UserAttachedFormService;
import org.kuali.coeus.propdev.impl.auth.ProposalDevelopmentDocumentAuthorizer;
import org.kuali.coeus.propdev.impl.auth.ProposalDevelopmentDocumentViewAuthorizer;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.propdev.impl.s2s.connect.S2sCommunicationException;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.api.print.FormPrintResult;
import org.kuali.coeus.s2sgen.api.print.FormPrintService;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.controller.ControllerFileUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.KRADUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller
public class ProposalDevelopmentS2SController extends ProposalDevelopmentControllerBase {
    private static final String CONTENT_TYPE_XML = "text/xml";
    private static final String CONTENT_TYPE_PDF = "application/pdf";

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProposalDevelopmentS2SController.class);

    @Autowired
    @Qualifier("s2sSubmissionService")
    private S2sSubmissionService s2sSubmissionService;

    @Autowired
    @Qualifier("s2sUserAttachedFormService")
    private S2sUserAttachedFormService s2sUserAttachedFormService;

    @Autowired
    @Qualifier("userAttachedFormService")
    private UserAttachedFormService userAttachedFormService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("formPrintService")
    private FormPrintService formPrintService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("proposalDevelopmentDocumentViewAuthorizer")
    private ProposalDevelopmentDocumentViewAuthorizer proposalDevelopmentDocumentViewAuthorizer;

    private static final String ERROR_NO_GRANTS_GOV_FORM_SELECTED = "error.proposalDevelopment.no.grants.gov.form.selected";

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=refresh", "refreshCaller=S2sOpportunity-LookupView"})
   public ModelAndView refresh(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
           throws Exception {
       ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
       DevelopmentProposal proposal = document.getDevelopmentProposal();
       if(form.getNewS2sOpportunity() != null 
               && StringUtils.isNotEmpty(form.getNewS2sOpportunity().getOpportunityId())) {

           proposal.setS2sOpportunity(form.getNewS2sOpportunity());
           proposal.getS2sOpportunity().setDevelopmentProposal(proposal);

           //Set default S2S Submission Type
           if (StringUtils.isBlank(form.getNewS2sOpportunity().getS2sSubmissionTypeCode())){
               String defaultS2sSubmissionTypeCode = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.S2S_SUBMISSIONTYPE_APPLICATION);
               proposal.getS2sOpportunity().setS2sSubmissionTypeCode(defaultS2sSubmissionTypeCode);
               getDataObjectService().wrap(proposal.getS2sOpportunity()).fetchRelationship("s2sSubmissionType");
           }

           //Set Opportunity Title and Opportunity ID in the Sponsor & Program Information section
           proposal.setProgramAnnouncementTitle(form.getNewS2sOpportunity().getOpportunityTitle());
           proposal.setProgramAnnouncementNumber(form.getNewS2sOpportunity().getOpportunityId());
           form.setNewS2sOpportunity(new S2sOpportunity());
       }

       S2sOpportunity s2sOpportunity = proposal.getS2sOpportunity();

       try {
           if (s2sOpportunity != null && s2sOpportunity.getSchemaUrl() != null) {
               List<String> missingMandatoryForms = new ArrayList<String>();
               s2sOpportunity.setS2sProvider(getDataObjectService().find(S2sProvider.class, s2sOpportunity.getProviderCode()));
               List<S2sOppForms> s2sOppForms = getS2sSubmissionService().parseOpportunityForms(s2sOpportunity);
               if(s2sOppForms!=null){
                   for(S2sOppForms s2sOppForm:s2sOppForms){
                       if(s2sOppForm.getMandatory() && !s2sOppForm.getAvailable()){
                           missingMandatoryForms.add(s2sOppForm.getFormName());
                       }
                   }
               }
               if(CollectionUtils.isEmpty(missingMandatoryForms)){
                   Collections.sort(s2sOppForms, new Comparator<S2sOppForms>() {
                       public int compare(S2sOppForms arg0, S2sOppForms arg1) {
                           int result = arg0.getMandatory().compareTo(arg1.getMandatory())*-1;
                           if (result == 0) {
                               result = arg0.getFormName().compareTo(arg1.getFormName());
                           }
                           return result;
                       }
                   });
                   s2sOpportunity.setS2sOppForms(s2sOppForms);
               }else{
                   globalVariableService.getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID,s2sOpportunity.getOpportunityId(),StringUtils.join(missingMandatoryForms,","));
                   proposal.setS2sOpportunity(new S2sOpportunity());
               }            
           }
       }catch(S2sCommunicationException ex){
           if(ex.getErrorKey().equals(KeyConstants.ERROR_GRANTSGOV_NO_FORM_ELEMENT)) {
               ex.setMessage(s2sOpportunity.getOpportunityId());
           }
           globalVariableService.getMessageMap().putError(Constants.NO_FIELD, ex.getErrorKey(),ex.getMessageWithParams());
           proposal.setS2sOpportunity(new S2sOpportunity());
       }
       super.save(form,result,request,response);
       return getRefreshControllerService().refresh(form);
   }
   
   @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=clearOpportunity"})
   public ModelAndView clearOpportunity(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
           throws Exception {
       ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
       DevelopmentProposal proposal = document.getDevelopmentProposal();
       getLegacyDataAdapter().delete(proposal.getS2sOpportunity());
       proposal.setS2sOpportunity(null);
       //Reset Opportunity Title and Opportunity ID in the Sponsor & Program Information section
       proposal.setProgramAnnouncementTitle("");
       proposal.setProgramAnnouncementNumber("");
       proposal.setOpportunityIdForGG("");
       return getRefreshControllerService().refresh(form);
   }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=printForms"})
        public ModelAndView printForms(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = getUpdatedDocument(form);

        proposalDevelopmentDocumentViewAuthorizer.initializeDocumentAuthorizerIfNecessary(form.getProposalDevelopmentDocument());

        if (!((ProposalDevelopmentDocumentAuthorizer) proposalDevelopmentDocumentViewAuthorizer.getDocumentAuthorizer()).isAuthorizedToPrint(proposalDevelopmentDocument, globalVariableService.getUserSession().getPerson())) {
            throw new AuthorizationException(globalVariableService.getUserSession().getPrincipalName(), "printForms", "Proposal");
        }

        if(proposalDevelopmentDocument.getDevelopmentProposal().getSelectedS2sOppForms().isEmpty()){
            getGlobalVariableService().getMessageMap().putError("noKey", ERROR_NO_GRANTS_GOV_FORM_SELECTED);
            return getModelAndViewService().getModelAndView(form);
        }
        FormPrintResult formPrintResult = getFormPrintService().printForm(proposalDevelopmentDocument);
        setValidationErrorMessage(formPrintResult.getErrors());
        KcFile attachmentDataSource = formPrintResult.getFile();
        if(attachmentDataSource==null || attachmentDataSource.getData()==null || attachmentDataSource.getData().length==0){
            boolean grantsGovErrorExists = copyAuditErrorsToPage(Constants.GRANTSGOV_ERRORS, "grantsGovFormValidationErrors");
            if(grantsGovErrorExists){
                getGlobalVariableService().getMessageMap().putError("grantsGovFormValidationErrors", KeyConstants.VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION);
            }
            return getModelAndViewService().getModelAndView(form);
        }
        if (proposalDevelopmentDocument.getDevelopmentProposal().getGrantsGovSelectFlag()) {
            File grantsGovXmlDirectoryFile = getS2sSubmissionService().getGrantsGovSavedFile(proposalDevelopmentDocument);
            byte[] bytes = new byte[(int) grantsGovXmlDirectoryFile.length()];
            long size = bytes.length;
            try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
                KRADUtils.addAttachmentToResponse(response, inputStream, "binary/octet-stream", grantsGovXmlDirectoryFile.getName() + ".zip", size);
                response.flushBuffer();
            }
            proposalDevelopmentDocument.getDevelopmentProposal().setGrantsGovSelectFlag(false);
            return getModelAndViewService().getModelAndView(form);
        }
        ControllerFileUtils.streamToResponse(attachmentDataSource, response);
        return getModelAndViewService().getModelAndView(form);
    }

    /**
     * Saves the form, and retrieves the document to update any reference objects before printing s2s forms.  Saves s2s forms
     * selected to be printed, and selects them again after the document has been retrieved.
     * @param form the form to save before retrieving new document
     */
    protected ProposalDevelopmentDocument getUpdatedDocument(ProposalDevelopmentDocumentForm form) throws Exception {
        List<S2sOppForms> selectedS2sOppForms = form.getDevelopmentProposal().getSelectedS2sOppForms();

        super.save(form);
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(form.getDocument().getDocumentNumber());
        for (S2sOppForms s2sOppForm : proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getS2sOppForms()) {
            for (S2sOppForms selectedS2sOppForm : selectedS2sOppForms ) {
                if (s2sOppForm.getS2sOppFormsId().equals(selectedS2sOppForm.getS2sOppFormsId())) {
                    s2sOppForm.setSelectToPrint(true);
                }
            }
        }
        return proposalDevelopmentDocument;
    }

    protected void setValidationErrorMessage(List<org.kuali.coeus.s2sgen.api.core.AuditError> errors) {
        if (errors != null) {
            LOG.info("Error list size:" + errors.size() + errors.toString());
            List<org.kuali.rice.krad.util.AuditError> auditErrors = new ArrayList<>();
            for (org.kuali.coeus.s2sgen.api.core.AuditError error : errors) {
                auditErrors.add(new org.kuali.rice.krad.util.AuditError(error.getErrorKey(),
                        Constants.GRANTS_GOV_GENERIC_ERROR_KEY, error.getLink(),
                        new String[]{error.getMessageKey()}));
            }
            if (!auditErrors.isEmpty()) {
                getGlobalVariableService().getAuditErrorMap().put(
                        "grantsGovAuditErrors",
                        new AuditCluster(Constants.GRANTS_GOV_OPPORTUNITY_PANEL,
                                auditErrors, Constants.GRANTSGOV_ERRORS)
                );
            }
        }
    }

    protected boolean copyAuditErrorsToPage(String auditClusterCategory, String errorkey) {
        boolean auditClusterFound = false;
        Iterator<String> iter = getGlobalVariableService().getAuditErrorMap().keySet().iterator();
        while (iter.hasNext()) {
            String errorKey = (String) iter.next();
            AuditCluster auditCluster = getGlobalVariableService().getAuditErrorMap().get(errorKey);
            if(auditClusterCategory == null || StringUtils.equalsIgnoreCase(auditCluster.getCategory(), auditClusterCategory)){
                auditClusterFound = true;
                for (Object error : auditCluster.getAuditErrorList()) {
                    AuditError auditError = (AuditError)error;
                    getGlobalVariableService().getMessageMap().putError(errorKey == null ? auditError.getErrorKey() : errorKey,
                            auditError.getMessageKey(), auditError.getParams());
                }
            }
        }
        return auditClusterFound;
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=addUserAttachedForm"})
    public ModelAndView addUserAttachedForm(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)
            throws Exception {
        S2sUserAttachedForm s2sUserAttachedForm = form.getS2sUserAttachedForm();
        ProposalDevelopmentDocument proposalDevelopmentDocument = form.getProposalDevelopmentDocument();

        MultipartFile userAttachedFormFile = s2sUserAttachedForm.getNewFormFile();

        s2sUserAttachedForm.setNewFormFileBytes(userAttachedFormFile.getBytes());
        s2sUserAttachedForm.setFormFileName(userAttachedFormFile.getOriginalFilename());
        s2sUserAttachedForm.setProposalNumber(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber());
        try{
            List<S2sUserAttachedForm> userAttachedForms = getS2sUserAttachedFormService().extractNSaveUserAttachedForms(proposalDevelopmentDocument,s2sUserAttachedForm);
            proposalDevelopmentDocument.getDevelopmentProposal().getS2sUserAttachedForms().addAll(userAttachedForms);
            form.setS2sUserAttachedForm(new S2sUserAttachedForm());
        }catch(S2SException ex){
            LOG.error(ex.getMessage(),ex);
            if(ex.getTabErrorKey()!=null){
                if(getGlobalVariableService().getMessageMap().getErrorMessagesForProperty(ex.getTabErrorKey())==null){
                    getGlobalVariableService().getMessageMap().putError(ex.getTabErrorKey(), ex.getErrorKey(),ex.getParams());
                }
            }else{
                getGlobalVariableService().getMessageMap().putError(Constants.NO_FIELD, ex.getErrorKey(),ex.getMessageWithParams());
            }
        }

       return super.save(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=viewUserAttachedFormXML"})
    public ModelAndView viewUserAttachedFormXML( ProposalDevelopmentDocumentForm form, HttpServletResponse response,
        @RequestParam("selectedLine") String selectedLine) throws Exception {

        DevelopmentProposal developmentProposal = form.getDevelopmentProposal();
        List<S2sUserAttachedForm> s2sAttachedForms = developmentProposal.getS2sUserAttachedForms();
        S2sUserAttachedForm selectedForm = s2sAttachedForms.get(Integer.parseInt(selectedLine));

        S2sUserAttachedFormFileContract userAttachedFormFile = getUserAttachedFormService().findUserAttachedFormFile(selectedForm);
        if(userAttachedFormFile!=null){
            ControllerFileUtils.streamToResponse(userAttachedFormFile.getXmlFile().getBytes(), selectedForm.getFormName()+".xml", CONTENT_TYPE_XML, response);
        }else{
            return getModelAndViewService().getModelAndView(form);
        }
        return null;
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=viewUserAttachedFormPDF"})
    public ModelAndView viewUserAttachedFormPDF( ProposalDevelopmentDocumentForm form, HttpServletResponse response,
                                                 @RequestParam("selectedLine") String selectedLine) throws Exception {
        DevelopmentProposal developmentProposal = form.getDevelopmentProposal();
        List<S2sUserAttachedForm> s2sAttachedForms = developmentProposal.getS2sUserAttachedForms();
        S2sUserAttachedForm selectedForm = s2sAttachedForms.get(Integer.parseInt(selectedLine));
        S2sUserAttachedFormFileContract userAttachedFormFile = getUserAttachedFormService().findUserAttachedFormFile(selectedForm);
        if(userAttachedFormFile!=null){
            ControllerFileUtils.streamToResponse(userAttachedFormFile.getFormFile(), selectedForm.getFormFileName(), CONTENT_TYPE_PDF, response);
        }else{
            return getModelAndViewService().getModelAndView(form);
        }
        return null;
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=deleteUserAttachedForm"})
    public ModelAndView deleteUserAttachedForm( ProposalDevelopmentDocumentForm form, HttpServletResponse response,
                                                 @RequestParam("selectedLine") String selectedLine) throws Exception {
        S2sUserAttachedForm deleteForm = form.getDevelopmentProposal().getS2sUserAttachedForms().remove(Integer.parseInt(selectedLine));
        getDataObjectService().delete(deleteForm);
        getS2sUserAttachedFormService().resetFormAvailability(form.getProposalDevelopmentDocument(), deleteForm.getNamespace());
        return getModelAndViewService().getModelAndView(form);
    }


    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=refreshSubmissionDetails"})
    public ModelAndView refreshSubmissionDetails( ProposalDevelopmentDocumentForm form) throws Exception {
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        try{
            getS2sSubmissionService().refreshGrantsGov(document);
        }catch(S2sCommunicationException ex){
            LOG.error(ex.getMessage(),ex);
            getGlobalVariableService().getMessageMap().putError(Constants.NO_FIELD, ex.getErrorKey(),ex.getMessage());
        }
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveUserAttachedForm")
    public ModelAndView saveUserAttachedForm(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        if(form.getEditableCollectionLines().containsKey(selectedCollectionPath)){
            form.getEditableCollectionLines().get(selectedCollectionPath).remove(selectedLine);
        }

        return super.save(form);
    }

    public S2sSubmissionService getS2sSubmissionService() {
        return s2sSubmissionService;
    }

    public void setS2sSubmissionService(S2sSubmissionService s2sSubmissionService) {
        this.s2sSubmissionService = s2sSubmissionService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public FormPrintService getFormPrintService() {
        return formPrintService;
    }

    public void setFormPrintService(FormPrintService formPrintService) {
        this.formPrintService = formPrintService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public S2sUserAttachedFormService getS2sUserAttachedFormService() {
        return s2sUserAttachedFormService;
    }

    public void setS2sUserAttachedFormService(S2sUserAttachedFormService s2sUserAttachedFormService) {
        this.s2sUserAttachedFormService = s2sUserAttachedFormService;
    }

    public UserAttachedFormService getUserAttachedFormService() {
        return userAttachedFormService;
    }

    public void setUserAttachedFormService(UserAttachedFormService userAttachedFormService) {
        this.userAttachedFormService = userAttachedFormService;
    }

    public ProposalDevelopmentDocumentViewAuthorizer getProposalDevelopmentDocumentViewAuthorizer() {
        return proposalDevelopmentDocumentViewAuthorizer;
    }

    public void setProposalDevelopmentDocumentViewAuthorizer(ProposalDevelopmentDocumentViewAuthorizer proposalDevelopmentDocumentViewAuthorizer) {
        this.proposalDevelopmentDocumentViewAuthorizer = proposalDevelopmentDocumentViewAuthorizer;
    }
}

