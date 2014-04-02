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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentAction;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyException;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_DELETE_OPPORTUNITY_CONFIRMATION;
import static org.kuali.rice.krad.util.KRADConstants.QUESTION_INST_ATTRIBUTE_NAME;

public class ProposalDevelopmentGrantsGovAction extends ProposalDevelopmentAction {
    private static final String CONFIRM_REMOVE_OPPRTUNITY_KEY = "confirmRemoveOpportunity";
    private static final String EMPTY_STRING = "";


    /**
     *  
     * @see org.kuali.coeus.propdev.impl.core.ProposalDevelopmentAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)throws Exception{                
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        
        // In a Hierarchy Child, the G.g tab is disabled, so this exception should only happen if the app is being hacked.
        if (proposalDevelopmentDocument.getDevelopmentProposal().isChild()) throw new ProposalHierarchyException("Cannot perform Grants.gov tasks on a Proposal Hierarchy child");

        if(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity()!=null){
                proposalDevelopmentForm.setGrantsGovSelectFlag(true);
            if(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getProposalNumber()==null){
                proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().setProposalNumber(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber());                
            }            
            if(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId()!=null){
                proposalDevelopmentDocument.getDevelopmentProposal().setProgramAnnouncementNumber(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId());                
            }
            if(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityTitle()!=null){
                proposalDevelopmentDocument.getDevelopmentProposal().setProgramAnnouncementTitle(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityTitle());                
            }
            if(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getCfdaNumber()!=null){
                proposalDevelopmentDocument.getDevelopmentProposal().setCfdaNumber(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getCfdaNumber());                
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
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Boolean mandatoryFormNotAvailable = false;
        List<S2sOppForms> s2sOppForms = new ArrayList<S2sOppForms>();
        
        if(proposalDevelopmentForm.getNewS2sOpportunity() != null 
                && StringUtils.isNotEmpty(proposalDevelopmentForm.getNewS2sOpportunity().getOpportunityId())) {
            proposalDevelopmentDocument.getDevelopmentProposal().setS2sOpportunity(proposalDevelopmentForm.getNewS2sOpportunity());
            proposalDevelopmentForm.setNewS2sOpportunity(new S2sOpportunity());
        }

        S2sOpportunity s2sOpportunity = proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity();
        try {
            if (s2sOpportunity != null && s2sOpportunity.getSchemaUrl() != null) {
                s2sOppForms = KcServiceLocator.getService(S2SService.class).parseOpportunityForms(s2sOpportunity);
                if(s2sOppForms!=null){
                    for(S2sOppForms s2sOppForm:s2sOppForms){
                        if(s2sOppForm.getMandatory() && !s2sOppForm.getAvailable()){
                            mandatoryFormNotAvailable = true;
                            break;
                        }
                    }
                }
                if(!mandatoryFormNotAvailable){
                    s2sOpportunity.setS2sOppForms(s2sOppForms);
                    s2sOpportunity.setVersionNumber(proposalDevelopmentForm.getVersionNumberForS2sOpportunity());
                    proposalDevelopmentForm.setVersionNumberForS2sOpportunity(null);                
                }else{
                    GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID,proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId());
                    proposalDevelopmentDocument.getDevelopmentProposal().setS2sOpportunity(new S2sOpportunity());
                }            
            }
        }catch(S2SException ex){
            if(ex.getErrorKey().equals(KeyConstants.ERROR_GRANTSGOV_NO_FORM_ELEMENT)) {
                ex.setMessage(s2sOpportunity.getOpportunityId());
            }
            GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, ex.getErrorKey(),ex.getMessageWithParams());
            proposalDevelopmentDocument.getDevelopmentProposal().setS2sOpportunity(new S2sOpportunity());
            return mapping.findForward(Constants.MAPPING_BASIC);
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
            proposalDevelopmentForm.setVersionNumberForS2sOpportunity(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getVersionNumber());            
            proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().setS2sOppForms(null);
            proposalDevelopmentDocument.getDevelopmentProposal().setS2sOpportunity(null);
            proposalDevelopmentDocument.getDevelopmentProposal().setProgramAnnouncementNumber(null);
            proposalDevelopmentDocument.getDevelopmentProposal().setProgramAnnouncementTitle(null);
            proposalDevelopmentDocument.getDevelopmentProposal().setCfdaNumber(null);            
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
        String description = proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId();
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
        return super.printForms(mapping, form, request, response);
    }
    
    public ActionForward refreshSubmissionDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        try{
            if(KcServiceLocator.getService(S2SService.class).refreshGrantsGov(proposalDevelopmentDocument)){
                proposalDevelopmentDocument.getDevelopmentProposal().refreshReferenceObject("s2sAppSubmission");
                return mapping.findForward(Constants.MAPPING_BASIC);
            }else{
                throw new RuntimeException("Refresh Failed");
            }
        }catch(S2SException ex){
            GlobalVariables.getMessageMap().putError(Constants.NO_FIELD, ex.getErrorKey(),ex.getMessage());
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
    
    public ActionForward performLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        if(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity()!=null && proposalDevelopmentForm.getVersionNumberForS2sOpportunity()==null){
            proposalDevelopmentForm.setVersionNumberForS2sOpportunity(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getVersionNumber());            
        }
        return super.performLookup(mapping, form, request, response);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        final ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        final ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        
        if (!((ProposalDevelopmentForm)form).isGrantsGovEnabled()) {
            GlobalVariables.getMessageMap().putWarning(Constants.NO_FIELD, KeyConstants.ERROR_IF_GRANTS_GOV_IS_DISABLED);
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        
        final String proposalTypeCodeRevision = this.getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, 
                KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION);

        if(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity()!= null && proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getOpportunityId()!= null && 
                StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getDevelopmentProposal().getProposalTypeCode(), proposalTypeCodeRevision) && 
                StringUtils.isBlank(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getRevisionCode())) { 
            GlobalVariables.getMessageMap().putError("document.developmentProposalList[0].s2sOpportunity.revisionCode", KeyConstants.ERROR_REQUIRED_REVISIONTYPE);
            return mapping.findForward(Constants.MAPPING_BASIC);
        } 

        return super.save(mapping, form, request, response);
    }
    /**
     * 
     * This method enable the ability to save the generated system to system XML
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveXml(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        proposalDevelopmentDocument.getDevelopmentProposal().setGrantsGovSelectFlag(true);
        proposalDevelopmentForm.setDocument(proposalDevelopmentDocument);
        return super.printForms(mapping, proposalDevelopmentForm, request, response);
       
    }
}
