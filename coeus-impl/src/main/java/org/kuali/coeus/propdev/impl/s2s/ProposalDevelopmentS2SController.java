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
    @Qualifier("proposalTypeService")
    private ProposalTypeService proposalTypeService;

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
               String defaultS2sSubmissionTypeCode = getProposalTypeService().getDefaultSubmissionTypeCode(proposal.getProposalTypeCode());
               proposal.getS2sOpportunity().setS2sSubmissionTypeCode(defaultS2sSubmissionTypeCode);
               getDataObjectService().wrap(proposal.getS2sOpportunity()).fetchRelationship("s2sSubmissionType");
           }

           final String opportunityTitle = form.getNewS2sOpportunity().getOpportunityTitle();
           String trimmedTitle= StringUtils.substring(opportunityTitle, 0, ProposalDevelopmentConstants.S2sConstants.OPP_TITLE_MAX_LENGTH);
           //Set Opportunity Title and Opportunity ID in the Sponsor & Program Information section
           proposal.setProgramAnnouncementTitle(trimmedTitle);
           proposal.setCfdaNumber(form.getNewS2sOpportunity().getCfdaNumber());
           proposal.setProgramAnnouncementNumber(form.getNewS2sOpportunity().getOpportunityId());
           form.setNewS2sOpportunity(new S2sOpportunity());
       }

       S2sOpportunity s2sOpportunity = proposal.getS2sOpportunity();

       try {
           if (s2sOpportunity != null && s2sOpportunity.getSchemaUrl() != null) {
               List<String> missingMandatoryForms = s2sSubmissionService.setMandatoryForms(proposal, s2sOpportunity);

               if (!CollectionUtils.isEmpty(missingMandatoryForms)) {
                   globalVariableService.getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID,s2sOpportunity.getOpportunityId(), StringUtils.join(missingMandatoryForms, ","));
                   proposal.setS2sOpportunity(null);
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
       ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).clearOpportunity(form.getDevelopmentProposal());
       return getRefreshControllerService().refresh(form);
   }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=printFormsXml"})
    public ModelAndView printFormsXml(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, HttpServletResponse response)
            throws Exception {
        form.getDevelopmentProposal().setGrantsGovSelectFlag(true);
        return printForms(form,response);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=printForms"})
    public ModelAndView printForms(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = form.getProposalDevelopmentDocument();

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
        if(((attachmentDataSource==null || attachmentDataSource.getData()==null || attachmentDataSource.getData().length==0) && !proposalDevelopmentDocument.getDevelopmentProposal().getGrantsGovSelectFlag())
                || CollectionUtils.isNotEmpty(formPrintResult.getErrors())){
            boolean grantsGovErrorExists = copyAuditErrorsToPage(Constants.GRANTSGOV_ERRORS, "grantsGovFormValidationErrors");
            if(grantsGovErrorExists){
                getGlobalVariableService().getMessageMap().putError("grantsGovFormValidationErrors", KeyConstants.VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION);
            }
            proposalDevelopmentDocument.getDevelopmentProposal().setGrantsGovSelectFlag(false);
            return getModelAndViewService().getModelAndView(form);
        }
        if (proposalDevelopmentDocument.getDevelopmentProposal().getGrantsGovSelectFlag()) {
            File grantsGovXmlDirectoryFile = getS2sSubmissionService().getGrantsGovSavedFile(proposalDevelopmentDocument);
            byte[] bytes = new byte[(int) grantsGovXmlDirectoryFile.length()];
            FileInputStream fileInputStream = new FileInputStream(grantsGovXmlDirectoryFile);
            fileInputStream.read(bytes);
            int size = bytes.length;
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(size)) {
                outputStream.write(bytes);
                ControllerFileUtils.streamOutputToResponse(response, outputStream, "binary/octet-stream", grantsGovXmlDirectoryFile.getName(), size);
                response.flushBuffer();
            }
            proposalDevelopmentDocument.getDevelopmentProposal().setGrantsGovSelectFlag(false);
            return getModelAndViewService().getModelAndView(form);
        }

        ControllerFileUtils.streamToResponse(attachmentDataSource, response);
        return getModelAndViewService().getModelAndView(form);
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

    public ProposalTypeService getProposalTypeService() {
        return proposalTypeService;
    }

    public void setProposalTypeService(ProposalTypeService proposalTypeService) {
        this.proposalTypeService = proposalTypeService;
    }

    public ProposalDevelopmentDocumentViewAuthorizer getProposalDevelopmentDocumentViewAuthorizer() {
        return proposalDevelopmentDocumentViewAuthorizer;
    }

    public void setProposalDevelopmentDocumentViewAuthorizer(ProposalDevelopmentDocumentViewAuthorizer proposalDevelopmentDocumentViewAuthorizer) {
        this.proposalDevelopmentDocumentViewAuthorizer = proposalDevelopmentDocumentViewAuthorizer;
    }
}

