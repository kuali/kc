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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_DELETE_OPPORTUNITY_CONFIRMATION;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_INST_ATTRIBUTE_NAME;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.WebUtils;

public class ProposalDevelopmentGrantsGovAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentGrantsGovAction.class);  
    private static final String CONFIRM_REMOVE_OPPRTUNITY_KEY = "confirmRemoveOpportunity";
    private static final String EMPTY_STRING = "";
    
    /**
     *  
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)throws Exception{                
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        
        if(proposalDevelopmentDocument.getS2sOpportunity()!=null){
            if(proposalDevelopmentDocument.getS2sOpportunity().getProposalNumber()==null){
                proposalDevelopmentDocument.getS2sOpportunity().setProposalNumber(proposalDevelopmentDocument.getProposalNumber());                
            }            
            if(proposalDevelopmentDocument.getS2sOpportunity().getOpportunityId()!=null){
                proposalDevelopmentDocument.setProgramAnnouncementNumber(proposalDevelopmentDocument.getS2sOpportunity().getOpportunityId());                
            }
            if(proposalDevelopmentDocument.getS2sOpportunity().getOpportunityTitle()!=null){
                proposalDevelopmentDocument.setProgramAnnouncementTitle(proposalDevelopmentDocument.getS2sOpportunity().getOpportunityTitle());                
            }
            if(proposalDevelopmentDocument.getS2sOpportunity().getCfdaNumber()!=null){
                proposalDevelopmentDocument.setCfdaNumber(proposalDevelopmentDocument.getS2sOpportunity().getCfdaNumber());                
            }
        }
        ActionForward actionForward = super.execute(mapping, form, request, response);
        return actionForward;        
    }
    
    /**
     * Upon returning from Grants.gov lookup, this method gets called. It uses the schemaUrl returned from 
     * lookup to retrieve the forms associated with the opportunity
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#refresh(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.refresh(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        Boolean mandatoryFormNotAvailable = false;
        List<S2sOppForms> s2sOppForms = new ArrayList<S2sOppForms>();
        if(proposalDevelopmentDocument.getS2sOpportunity().getSchemaUrl()!=null){
            s2sOppForms = KraServiceLocator.getService(S2SService.class).parseOpportunityForms(proposalDevelopmentDocument.getS2sOpportunity());
            if(s2sOppForms!=null){
                for(S2sOppForms s2sOppForm:s2sOppForms){
                    if(s2sOppForm.getMandatory() && !s2sOppForm.getAvailable()){
                        mandatoryFormNotAvailable = true;
                        break;
                    }
                }
            }
            if(!mandatoryFormNotAvailable){
                proposalDevelopmentDocument.getS2sOpportunity().setS2sOppForms(s2sOppForms);
                proposalDevelopmentDocument.getS2sOpportunity().setVersionNumber(proposalDevelopmentForm.getVersionNumberForS2sOpportunity());
                proposalDevelopmentForm.setVersionNumberForS2sOpportunity(null);                
            }else{
                GlobalVariables.getErrorMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID,proposalDevelopmentDocument.getS2sOpportunity().getOpportunityId());
                proposalDevelopmentDocument.setS2sOpportunity(new S2sOpportunity());
            }            
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method removes/deletes an opportunity from the KRA tables; is called from removeOpportunity method
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmRemoveOpportunity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_REMOVE_OPPRTUNITY_KEY.equals(question)) { 
            ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
            ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
            proposalDevelopmentForm.setVersionNumberForS2sOpportunity(proposalDevelopmentDocument.getS2sOpportunity().getVersionNumber());            
            proposalDevelopmentDocument.getS2sOpportunity().setS2sOppForms(null);
            proposalDevelopmentDocument.setS2sOpportunity(null);
            proposalDevelopmentDocument.setProgramAnnouncementNumber(null);
            proposalDevelopmentDocument.setProgramAnnouncementTitle(null);
            proposalDevelopmentDocument.setCfdaNumber(null);            
        }        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method removes/deletes an opportunity from the KRA tables
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward removeOpportunity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return confirm(buildDeleteOpportunityConfirmationQuestion(mapping, form, request, response), CONFIRM_REMOVE_OPPRTUNITY_KEY, EMPTY_STRING);        
    }
    
    /**
     * 
     * This method builds a Opportunity Delete Confirmation Question as part of the Questions Framework
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildDeleteOpportunityConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        String description = proposalDevelopmentDocument.getS2sOpportunity().getOpportunityId();
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_REMOVE_OPPRTUNITY_KEY, QUESTION_DELETE_OPPORTUNITY_CONFIRMATION, description);
    }
    
    /**
     * 
     * This method is called to print forms
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */    
    public ActionForward printForms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        super.save(mapping, form, request, response);
        boolean grantsGovErrorExists = false;
        boolean errorExists = false;
        boolean warningExists = false;
        AttachmentDataSource attachmentDataSource = KraServiceLocator.getService(S2SService.class).printForm(proposalDevelopmentDocument);
        if(attachmentDataSource==null || attachmentDataSource.getContent()==null){
            for (Iterator iter = GlobalVariables.getAuditErrorMap().keySet().iterator(); iter.hasNext();){     
                AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get(iter.next());
                if(StringUtils.equalsIgnoreCase(auditCluster.getCategory(),Constants.AUDIT_ERRORS)){
                    errorExists=true;
                    break;
                }
                if(StringUtils.equalsIgnoreCase(auditCluster.getCategory(),Constants.GRANTSGOV_ERRORS)){
                    grantsGovErrorExists = true;
                    break;
                }
                if(StringUtils.equalsIgnoreCase(auditCluster.getCategory(),Constants.AUDIT_WARNINGS)){
                    warningExists = true;
                }
            }
            if(grantsGovErrorExists){
                GlobalVariables.getErrorMap().putError("document.noKey", KeyConstants.VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION);
                proposalDevelopmentForm.setAuditActivated(true);
                return mapping.findForward(Constants.MAPPING_PROPOSAL_ACTIONS);
            }
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
            
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream(attachmentDataSource.getContent().length);
            baos.write(attachmentDataSource.getContent());
            WebUtils.saveMimeOutputStreamAsFile(response, attachmentDataSource.getContentType(), baos, attachmentDataSource.getFileName());
        }finally{
            try{
                if(baos!=null){
                    baos.close();
                    baos = null;
                }
            }catch(IOException ioEx){
                LOG.warn(ioEx.getMessage(), ioEx);
            }
        }        
        return null;
    }
    
    public ActionForward refreshSubmissionDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        if(KraServiceLocator.getService(S2SService.class).refreshGrantsGov(proposalDevelopmentDocument)){
            proposalDevelopmentDocument.refreshReferenceObject("s2sAppSubmission");
            return mapping.findForward(Constants.MAPPING_BASIC);
        }else{
            throw new RuntimeException("Refresh Failed");
        }
    }
    
    public ActionForward performLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        if(proposalDevelopmentDocument.getS2sOpportunity()!=null && proposalDevelopmentForm.getVersionNumberForS2sOpportunity()==null){
            proposalDevelopmentForm.setVersionNumberForS2sOpportunity(proposalDevelopmentDocument.getS2sOpportunity().getVersionNumber());            
        }
        return super.performLookup(mapping, form, request, response);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        final ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        final ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        String proposalTypeCodeRevision = getConfigurationService().getParameter(Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                Constants.PARAMETER_COMPONENT_DOCUMENT, 
                KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION).getParameterValue();

        if(proposalDevelopmentDocument.getS2sOpportunity()!= null && proposalDevelopmentDocument.getS2sOpportunity().getOpportunityId()!= null && 
                StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getProposalTypeCode(), proposalTypeCodeRevision) && 
                StringUtils.isBlank(proposalDevelopmentDocument.getS2sOpportunity().getRevisionCode())) { 
            GlobalVariables.getErrorMap().putError("document.s2sOpportunity.revisionCode", KeyConstants.ERROR_REQUIRED_REVISIONTYPE);
            return mapping.findForward(Constants.MAPPING_BASIC);
        } 

        return super.save(mapping, form, request, response);
    }
}
