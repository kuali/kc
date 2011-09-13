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
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationAgreementType;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.bo.NegotiationStatus;
import org.kuali.kra.negotiations.bo.NegotiationUnassociatedDetail;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * 
 * This class handles the home screen for negotiations.
 */
public class NegotiationNegotiationAction extends NegotiationAction {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(NegotiationNegotiationAction.class);
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        loadCodeObjects(negotiationForm.getNegotiationDocument().getNegotiation());
        findAndLoadNegotiationUnassociatedDetail(negotiationForm.getNegotiationDocument().getNegotiation(), false);
        return actionForward;
    }
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.reload(mapping, form, request, response);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        loadCodeObjects(negotiationForm.getNegotiationDocument().getNegotiation());
        findAndLoadNegotiationUnassociatedDetail(negotiationForm.getNegotiationDocument().getNegotiation(), true);
        return actionForward;
    }
    
    
    private void findAndLoadNegotiationUnassociatedDetail(Negotiation negotiation, boolean reload) {
        if (negotiation.getNegotiationAssociationType() != null 
                && StringUtils.equalsIgnoreCase(negotiation.getNegotiationAssociationType().getCode(), NegotiationAssociationType.NONE_ASSOCIATION) 
                && StringUtils.isNotEmpty(negotiation.getAssociatedDocumentId())) {
            if (reload || negotiation.getUnAssociatedDetail() == null) {
                Map params = new HashMap();
                params.put("NEGOTIATION_UNASSOC_DETAIL_ID", negotiation.getAssociatedDocumentId());
                NegotiationUnassociatedDetail unAssociatedDetail = (NegotiationUnassociatedDetail) 
                        this.getBusinessObjectService().findByPrimaryKey(NegotiationUnassociatedDetail.class, params);
                negotiation.setUnAssociatedDetail(unAssociatedDetail);
            }
        }
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        loadCodeObjects(negotiationForm.getNegotiationDocument().getNegotiation());
        ActionForward actionForward = super.save(mapping, form, request, response);
        if (negotiationForm.getNegotiationDocument().getNegotiation().getUnAssociatedDetail() != null) {
            Negotiation negotiation = negotiationForm.getNegotiationDocument().getNegotiation();
            if (negotiation.getUnAssociatedDetail().getNegotiationId() == null) {
                negotiation.getUnAssociatedDetail().setNegotiationId(negotiation.getNegotiationId());
            }
            NegotiationUnassociatedDetail detail = negotiation.getUnAssociatedDetail();
            this.getBusinessObjectService().save(detail);
            negotiation.setAssociatedDocumentId(negotiation.getUnAssociatedDetail().getNegotiationUnassociatedDetailId().toString());
            this.getBusinessObjectService().save(negotiation);
            detail.refresh();
        }
        if(!negotiationForm.getNegotiationUnassociatedDetailsToDelete().isEmpty()) {
            this.getBusinessObjectService().delete(negotiationForm.getNegotiationUnassociatedDetailsToDelete());
        }
        negotiationForm.getNegotiationDocument().getNegotiation().refresh();
        return actionForward;
    }
    
    private void loadCodeObjects(Negotiation negotiation) {
        Map primaryKeys = new HashMap();
        if (negotiation.getNegotiationAgreementTypeId() != null) {
            primaryKeys.put("NEGOTIATION_AGRMNT_TYPE_ID", negotiation.getNegotiationAgreementTypeId());
            NegotiationAgreementType type = (NegotiationAgreementType) 
                this.getBusinessObjectService().findByPrimaryKey(NegotiationAgreementType.class, primaryKeys);
            negotiation.setNegotiationAgreementType(type);
        }
        
        if (negotiation.getNegotiationAssociationTypeId() != null) {
            primaryKeys = new HashMap();
            primaryKeys.put("NEGOTIATION_ASSC_TYPE_ID", negotiation.getNegotiationAssociationTypeId());
            NegotiationAssociationType type = (NegotiationAssociationType) 
                this.getBusinessObjectService().findByPrimaryKey(NegotiationAssociationType.class, primaryKeys);
            negotiation.setNegotiationAssociationType(type);
        }
        
        if (negotiation.getNegotiationStatusId() != null) {
            primaryKeys = new HashMap();
            primaryKeys.put("NEGOTIATION_STATUS_ID", negotiation.getNegotiationStatusId());
            NegotiationStatus status = (NegotiationStatus) 
                this.getBusinessObjectService().findByPrimaryKey(NegotiationStatus.class, primaryKeys);
            negotiation.setNegotiationStatus(status);
        }
        
        if (negotiation.getUnAssociatedDetail() != null) {
            if (StringUtils.isNotBlank(negotiation.getUnAssociatedDetail().getSponsorCode())) {
                primaryKeys = new HashMap();
                primaryKeys.put("SPONSOR_CODE", negotiation.getUnAssociatedDetail().getSponsorCode());
                Sponsor sponsor = (Sponsor) this.getBusinessObjectService().findByPrimaryKey(Sponsor.class, primaryKeys);
                negotiation.getUnAssociatedDetail().setSponsor(sponsor);
            }
            if (StringUtils.isNotBlank(negotiation.getUnAssociatedDetail().getPrimeSponsorCode())) {
                primaryKeys = new HashMap();
                primaryKeys.put("SPONSOR_CODE", negotiation.getUnAssociatedDetail().getPrimeSponsorCode());
                Sponsor sponsor = (Sponsor) this.getBusinessObjectService().findByPrimaryKey(Sponsor.class, primaryKeys);
                negotiation.getUnAssociatedDetail().setPrimeSponsor(sponsor);
            }
            if (StringUtils.isNotBlank(negotiation.getUnAssociatedDetail().getSubAwardOrganizationId())) {
                primaryKeys = new HashMap();
                primaryKeys.put("ORGANIZATION_ID", negotiation.getUnAssociatedDetail().getSubAwardOrganizationId());
                Organization org = (Organization) this.getBusinessObjectService().findByPrimaryKey(Organization.class, primaryKeys);
                negotiation.getUnAssociatedDetail().setSubAwardOrganization(org);
            }
        }
        
    }
    
    public ActionForward changeAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        if (negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationAssociationType() != null 
                && StringUtils.equalsIgnoreCase(NegotiationAssociationType.NONE_ASSOCIATION, 
                        negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationAssociationType().getCode())) {
            String message = "negotiation.message.changeAssociationType";
            request.setAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE, "methodToCall.changeAssociationRedirector");
            ActionForward confirmAction = confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, 
                    "changeAssociationKey", message), 
                    "confirmedChangeAssociation", "resetChangeAssociationType");
            return confirmAction;
        } else {
            return confirmedChangeAssociation(mapping, negotiationForm, request, response);
        }
    }
    
    public ActionForward changeAssociationRedirector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String buttonClicked = request.getParameter("buttonClicked").toString();
        /**
         * 0 is Yes
         * 1 is no
         */
        if (StringUtils.equals(buttonClicked, "0")) {
            return confirmedChangeAssociation(mapping, form, request, response);
        } else {
            return resetChangeAssociationType(mapping, form, request, response);
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
        
        if (StringUtils.equalsIgnoreCase(asscType.getCode(), NegotiationAssociationType.NONE_ASSOCIATION)) {
            negotiationForm.getNegotiationDocument().getNegotiation().setUnAssociatedDetail(new NegotiationUnassociatedDetail());
        } else {
            if (negotiationForm.getNegotiationDocument().getNegotiation().getUnAssociatedDetail() != null) {
                negotiationForm.getNegotiationUnassociatedDetailsToDelete().add(
                        negotiationForm.getNegotiationDocument().getNegotiation().getUnAssociatedDetail());
                negotiationForm.getNegotiationDocument().getNegotiation().setUnAssociatedDetail(null);
            }
        }
        negotiationForm.populate(request);
        return actionForward;
    }

    public ActionForward resetChangeAssociationType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        
        Negotiation negotiation =  negotiationForm.getNegotiationDocument().getNegotiation();
        Map params = new HashMap();
        params.put("NEGOTIATION_ID", negotiation.getNegotiationId());
        
        Negotiation dbNegotiation = (Negotiation) this.getBusinessObjectService().findByPrimaryKey(Negotiation.class, params);
        
        negotiation.setNegotiationAssociationTypeId(dbNegotiation.getNegotiationAssociationType().getId());
        negotiation.setNegotiationAssociationType(dbNegotiation.getNegotiationAssociationType());
        return actionForward;
    }
}
