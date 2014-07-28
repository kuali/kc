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
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller
public class ProposalDevelopmentS2SController extends ProposalDevelopmentControllerBase {

    @Autowired
    @Qualifier("s2sSubmissionService")
    private S2sSubmissionService s2sSubmissionService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

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
       s2sOpportunity.setS2sProvider(getDataObjectService().find(S2sProvider.class, s2sOpportunity.getProviderCode()));
       Boolean mandatoryFormNotAvailable = false;
       List<S2sOppForms> s2sOppForms = new ArrayList<S2sOppForms>();
       try {
           if (s2sOpportunity != null && s2sOpportunity.getSchemaUrl() != null) {
               s2sOppForms = getS2sSubmissionService().parseOpportunityForms(s2sOpportunity);
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
       return getRefreshControllerService().refresh(form);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=clearOpportunity"})
   public ModelAndView clearOpportunity(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response)
           throws Exception {
       ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
       DevelopmentProposal proposal = document.getDevelopmentProposal();
       getLegacyDataAdapter().delete(proposal.getS2sOpportunity());
       proposal.setS2sOpportunity(null);
       //Reset Opportunity Title and Opportunity ID in the Sponsor & Program Information section
       proposal.setProgramAnnouncementTitle("");
       proposal.setProgramAnnouncementNumber("");
       return getRefreshControllerService().refresh(form);
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
}
