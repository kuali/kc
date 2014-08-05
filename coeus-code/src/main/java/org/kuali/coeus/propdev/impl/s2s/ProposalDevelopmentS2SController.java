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


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.s2s.connect.S2sCommunicationException;
import org.kuali.coeus.s2sgen.api.print.FormPrintResult;
import org.kuali.coeus.s2sgen.api.print.FormPrintService;
import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.KRADUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller
public class ProposalDevelopmentS2SController extends ProposalDevelopmentControllerBase {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProposalDevelopmentS2SController.class);

    @Autowired
    @Qualifier("s2sSubmissionService")
    private S2sSubmissionService s2sSubmissionService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("formPrintService")
    private FormPrintService formPrintService;

    private static final String ERROR_NO_GRANTS_GOV_FORM_SELECTED = "error.proposalDevelopment.no.grants.gov.form.selected";

    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=refresh", "refreshCaller=S2sOpportunity-LookupView"})
   public ModelAndView refresh(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
           throws Exception {
       ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
       DevelopmentProposal proposal = document.getDevelopmentProposal();
       if(form.getNewS2sOpportunity() != null 
               && StringUtils.isNotEmpty(form.getNewS2sOpportunity().getOpportunityId())) {
           proposal.setS2sOpportunity(form.getNewS2sOpportunity());
           proposal.getS2sOpportunity().setDevelopmentProposal(proposal);

           //Set Opportunity Title and Opportunity ID in the Sponsor & Program Information section
           proposal.setProgramAnnouncementTitle(form.getNewS2sOpportunity().getOpportunityTitle());
           proposal.setProgramAnnouncementNumber(form.getNewS2sOpportunity().getOpportunityId());
           form.setNewS2sOpportunity(new S2sOpportunity());
       }

       S2sOpportunity s2sOpportunity = proposal.getS2sOpportunity();

       try {
           if (s2sOpportunity != null && s2sOpportunity.getSchemaUrl() != null) {
               Boolean mandatoryFormNotAvailable = false;
               s2sOpportunity.setS2sProvider(getDataObjectService().find(S2sProvider.class, s2sOpportunity.getProviderCode()));
               List<S2sOppForms> s2sOppForms = getS2sSubmissionService().parseOpportunityForms(s2sOpportunity);
               if(s2sOppForms!=null){
                   for(S2sOppForms s2sOppForm:s2sOppForms){
                       if(s2sOppForm.getMandatory() && !s2sOppForm.getAvailable()){
                           mandatoryFormNotAvailable = true;
                           break;
                       }
                   }
               }
               if(!mandatoryFormNotAvailable){
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
                   proposal.setS2sOppForms(s2sOppForms);
               }else{
                   globalVariableService.getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID, s2sOpportunity.getOpportunityId());
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
   
   @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=clearOpportunity"})
   public ModelAndView clearOpportunity(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
           throws Exception {
       ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
       DevelopmentProposal proposal = document.getDevelopmentProposal();
       getLegacyDataAdapter().delete(proposal.getS2sOpportunity());
       proposal.setS2sOpportunity(null);
       proposal.setS2sOppForms(new ArrayList<S2sOppForms>());
       //Reset Opportunity Title and Opportunity ID in the Sponsor & Program Information section
       proposal.setProgramAnnouncementTitle("");
       proposal.setProgramAnnouncementNumber("");
       return getRefreshControllerService().refresh(form);
   }

    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=printForms"})
        public ModelAndView printForms(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = form.getProposalDevelopmentDocument();
        boolean grantsGovErrorExists = false;

        if(proposalDevelopmentDocument.getDevelopmentProposal().getSelectedS2sOppForms().isEmpty()){
            getGlobalVariableService().getMessageMap().putError("noKey", ERROR_NO_GRANTS_GOV_FORM_SELECTED);
            return getModelAndViewService().getModelAndView(form);
        }
        FormPrintResult formPrintResult = getFormPrintService().printForm(proposalDevelopmentDocument);
        setValidationErrorMessage(formPrintResult.getErrors());
        KcFile attachmentDataSource = formPrintResult.getFile();
        if(attachmentDataSource==null || attachmentDataSource.getData()==null || attachmentDataSource.getData().length==0){
            grantsGovErrorExists = copyAuditErrorsToPage(Constants.GRANTSGOV_ERRORS, "grantsGovFormValidationErrors");
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
        streamToResponse(attachmentDataSource, response);
        return getModelAndViewService().getModelAndView(form);
    }

    protected void setValidationErrorMessage(List<org.kuali.coeus.s2sgen.api.core.AuditError> errors) {
        if (errors != null) {
            LOG.info("Error list size:" + errors.size() + errors.toString());
            List<org.kuali.rice.kns.util.AuditError> auditErrors = new ArrayList<>();
            for (org.kuali.coeus.s2sgen.api.core.AuditError error : errors) {
                auditErrors.add(new org.kuali.rice.kns.util.AuditError(error.getErrorKey(),
                        Constants.GRANTS_GOV_GENERIC_ERROR_KEY, error.getLink(),
                        new String[]{error.getMessageKey()}));
            }
            if (!auditErrors.isEmpty()) {
                KNSGlobalVariables.getAuditErrorMap().put(
                        "grantsGovAuditErrors",
                        new AuditCluster(Constants.GRANTS_GOV_OPPORTUNITY_PANEL,
                                auditErrors, Constants.GRANTSGOV_ERRORS)
                );
            }
        }
    }

    protected void streamToResponse(KcFile attachmentDataSource, HttpServletResponse response) throws Exception {
            byte[] data = attachmentDataSource.getData();
            long size = data.length;
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(data)) {
            KRADUtils.addAttachmentToResponse(response,inputStream,attachmentDataSource.getType(),attachmentDataSource.getName(),size);
            response.flushBuffer();
        }
    }

    protected boolean copyAuditErrorsToPage(String auditClusterCategory, String errorkey) {
        boolean auditClusterFound = false;
        Iterator<String> iter = KNSGlobalVariables.getAuditErrorMap().keySet().iterator();
        while (iter.hasNext()) {
            String errorKey = (String) iter.next();
            AuditCluster auditCluster = (AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(errorKey);
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
}
