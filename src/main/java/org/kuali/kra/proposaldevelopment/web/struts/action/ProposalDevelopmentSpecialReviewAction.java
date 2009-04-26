/*
 * Copyright 2006-2008 The Kuali Foundation
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

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.ExemptionType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSpecialReviewEvent;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProposalDevelopmentSpecialReviewAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentSpecialReviewAction.class);
    public ActionForward addSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalSpecialReview newProposalSpecialReview = proposalDevelopmentForm.getNewPropSpecialReview();
        if(getKualiRuleService().applyRules(new AddProposalSpecialReviewEvent(Constants.EMPTY_STRING, proposalDevelopmentForm.getProposalDevelopmentDocument(), newProposalSpecialReview))){
            newProposalSpecialReview.setSpecialReviewNumber(proposalDevelopmentForm.getProposalDevelopmentDocument().getDocumentNextValue(Constants.PROPOSAL_SPECIALREVIEW_NUMBER));
            proposalDevelopmentForm.getProposalDevelopmentDocument().getPropSpecialReviews().add(newProposalSpecialReview);
            if (proposalDevelopmentForm.getDocumentExemptNumbers() == null) {
                proposalDevelopmentForm.setDocumentExemptNumbers(new ArrayList<String[]>());
            }
            proposalDevelopmentForm.getDocumentExemptNumbers().add(proposalDevelopmentForm.getDocumentExemptNumbers().size(), proposalDevelopmentForm.getNewExemptNumbers());
            proposalDevelopmentForm.setNewExemptNumbers(null);
            proposalDevelopmentForm.setNewPropSpecialReview(new ProposalSpecialReview());
        }
        return mapping.findForward("basic");
    }
    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.getProposalDevelopmentDocument().getPropSpecialReviews().remove(getLineToDelete(request));
        proposalDevelopmentForm.getDocumentExemptNumbers().remove(getLineToDelete(request));
        GlobalVariables.getErrorMap().clear();

        return mapping.findForward("basic");
    }

    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO this is to get the key values pair for exempt numbers - any other options
        // put  in service ?
        ExtendedPersistableBusinessObjectValuesFinder finder = new ExtendedPersistableBusinessObjectValuesFinder();
        finder.setBusinessObjectClass(ExemptionType.class);
        finder.setKeyAttributeName("exemptionTypeCode");
        finder.setLabelAttributeName("description");
        ((ProposalDevelopmentForm) form).setExemptNumberList(KraServiceLocator.getService(ProposalDevelopmentService.class).getExemptionTypeKeyValues());
        return super.execute(mapping, form, request, response);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KraServiceLocator.getService(ProposalDevelopmentService.class).populateProposalExempNumbers((ProposalDevelopmentForm)form);
        return super.save(mapping, form, request, response);
    }

    // TODO : move this method up?
    @Override
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }

}
