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
package org.kuali.kra.negotiations.web.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;
import org.kuali.kra.web.struts.action.StrutsConfirmation;

/**
 * 
 * This class handles the home screen for negotiations.
 */
public class NegotiationNegotiationAction extends NegotiationAction {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NegotiationNegotiationAction.class);
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response); 
        return actionForward;
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.save(mapping, form, request, response);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getNegotiationDocument().getNegotiation().refresh();
        return actionForward;
    }
    
    public ActionForward changeAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        if (StringUtils.equalsIgnoreCase(NegotiationAssociationType.NONE_ASSOCIATION, 
                negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationAssociationType().getCode())) {
            String questionId = "foo";
            String configurationId = "negotiation.message.changeAssociationType";
            String params = "";
            StrutsConfirmation question = buildParameterizedConfirmationQuestion(mapping, negotiationForm, request, response,
                    questionId, configurationId, params);
            return confirm(question, "confirmedChangeAssociation", "resetChangeAssociationType");
        } else {
            return confirmedChangeAssociation(mapping, negotiationForm, request, response);
        }
    }
    
    public ActionForward confirmedChangeAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        NegotiationForm negotiationForm = (NegotiationForm) form;        
        Long newAssociationTypeId = negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationAssociationTypeId();
        Map params = new HashMap();
        params.put("NEGOTIATION_ASSC_TYPE_ID", newAssociationTypeId);
        NegotiationAssociationType asscType = (NegotiationAssociationType) 
            this.getBusinessObjectService().findByPrimaryKey(NegotiationAssociationType.class, params);
        negotiationForm.getNegotiationDocument().getNegotiation().setNegotiationAssociationType(asscType);
        negotiationForm.getNegotiationDocument().getNegotiation().setAssociatedDocumentId("");
        
        return actionForward;
    }
    
    public ActionForward resetChangeAssociationType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getNegotiationDocument().getNegotiation().setNegotiationAssociationTypeId(
                negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationAssociationType().getId());
        return actionForward;
    }
}
