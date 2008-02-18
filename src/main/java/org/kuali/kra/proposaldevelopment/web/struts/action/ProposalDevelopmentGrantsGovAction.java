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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static org.kuali.RiceConstants.QUESTION_INST_ATTRIBUTE_NAME;
import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_DELETE_OPPORTUNITY_CONFIRMATION;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.web.struts.action.StrutsConfirmation;

public class ProposalDevelopmentGrantsGovAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentGrantsGovAction.class);  
    private static final String CONFIRM_REMOVE_OPPRTUNITY_KEY = "confirmRemoveOpportunity";
    private static final String EMPTY_STRING = "";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)throws Exception{                
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        
        if(proposalDevelopmentDocument.getS2sOpportunity()!=null){                      
            
            if(/*StringUtils.isEmpty(proposalDevelopmentDocument.getS2sOpportunity().getProposalNumber().toString())||*/proposalDevelopmentDocument.getS2sOpportunity().getProposalNumber()==null){
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
    
    public ActionForward removeOpportunity1(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        //proposalDevelopmentDocument.setS2sOpportunity(null);
        
        BusinessObjectService businessObjectService = ((BusinessObjectService) KraServiceLocator.getService(BusinessObjectService.class));
        businessObjectService.delete(proposalDevelopmentDocument.getS2sOpportunity());
        proposalDevelopmentDocument.setS2sOpportunity(null);
        //return mapping.findForward("basic");
        return super.save(mapping, form, request, response); 
    }
    
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.refresh(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();

        S2SService s2SService =((S2SService) KraServiceLocator.getService(S2SService.class));
        if(proposalDevelopmentDocument.getS2sOpportunity().getSchemaUrl()!=null){
            List<S2sOppForms> s2sOppForms = s2SService.parseOpportunityForms(proposalDevelopmentDocument.getS2sOpportunity());
            proposalDevelopmentDocument.getS2sOpportunity().setS2sOppForms(s2sOppForms);
        }
        // check to see if we are coming back from a lookup
/*        if (Constants.MULTIPLE_VALUE.equals(proposalDevelopmentForm.getRefreshCaller())) {
            // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
            // Since URLs have a max length of 2000 chars, field conversions can not be done.
            String lookupResultsSequenceNumber = proposalDevelopmentForm.getLookupResultsSequenceNumber();
            if (StringUtils.isNotBlank(lookupResultsSequenceNumber)) {
                Class lookupResultsBOClass = Class.forName(proposalDevelopmentForm.getLookupResultsBOClassName());
                Collection<PersistableBusinessObject> rawValues = KNSServiceLocator.getLookupResultsService().retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass, GlobalVariables.getUserSession().getUniversalUser().getPersonUniversalIdentifier());
                if(lookupResultsBOClass.isAssignableFrom(ScienceKeyword.class)) {
                    for(Iterator iter = rawValues.iterator(); iter.hasNext(); ) {
                        ScienceKeyword scienceKeyword = (ScienceKeyword) iter.next();
                        PropScienceKeyword propScienceKeyword = new PropScienceKeyword(proposalDevelopmentDocument.getProposalNumber(), scienceKeyword);
                        // ignore / drop duplicates
                        if(!isDuplicateKeyword(propScienceKeyword.getScienceKeywordCode(), proposalDevelopmentDocument.getPropScienceKeywords())) {
                            proposalDevelopmentDocument.addPropScienceKeyword(propScienceKeyword);
                        }
                    }
                }
            }
        }*/
        return mapping.findForward("basic");
    }
    
    public ActionForward confirmRemoveOpportunity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_REMOVE_OPPRTUNITY_KEY.equals(question)) { 
            ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
            ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
            BusinessObjectService businessObjectService = ((BusinessObjectService) KraServiceLocator.getService(BusinessObjectService.class));            
            businessObjectService.delete(proposalDevelopmentDocument.getS2sOpportunity());
            proposalDevelopmentDocument.setS2sOpportunity(null);
            //return mapping.findForward("basic");
            return super.save(mapping, form, request, response);
        }
        
        return mapping.findForward("basic");
    }
    
    public ActionForward removeOpportunity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return confirm(buildDeleteOpportunityConfirmationQuestion(mapping, form, request, response), CONFIRM_REMOVE_OPPRTUNITY_KEY, EMPTY_STRING);
    }
    
    private StrutsConfirmation buildDeleteOpportunityConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Opportunities are stored in a document. We need to document to retrieve the abstract.
        ProposalDevelopmentDocument doc = ((ProposalDevelopmentForm) form).getProposalDevelopmentDocument();

        // Get the description. This will be used as a parameter to build the message for requesting confirmation feedback from the user.
        String description = doc.getS2sOpportunity().getOpportunityId();
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_REMOVE_OPPRTUNITY_KEY, QUESTION_DELETE_OPPORTUNITY_CONFIRMATION, description);
    }
    
    public ActionForward submitToGrantsGov(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocument document = ((ProposalDevelopmentForm) form).getProposalDevelopmentDocument();
        S2SService s2SService = ((S2SService) KraServiceLocator.getService(S2SService.class)); 
        s2SService.submitApplication(document.getProposalNumber());
        return super.save(mapping, form, request, response);
    }

}
