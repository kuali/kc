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

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.kuali.rice.kns.util.KNSConstants.EMPTY_STRING;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_CLICKED_BUTTON;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.AttachmentFile;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityAttachment;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.bo.NegotiationUnassociatedDetail;
import org.kuali.kra.negotiations.customdata.NegotiationCustomData;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.notifications.NegotiationNotificationService;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * 
 * This class handles the home screen for negotiations.
 */
public class NegotiationNegotiationAction extends NegotiationAction {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(NegotiationNegotiationAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        loadCodeObjects(negotiationForm.getNegotiationDocument().getNegotiation());
        negotiationForm.getMedusaBean().setModuleName("neg");
        negotiationForm.getMedusaBean().setModuleIdentifier(negotiationForm.getDocument().getNegotiation().getNegotiationId());
        negotiationForm.getNegotiationActivityHelper().sortActivities();
        negotiationForm.getNegotiationActivityHelper().generateAllAttachments();
        return actionForward;
    }

    /**
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#reload(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.reload(mapping, form, request, response);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getCustomDataHelper().negotiationCustomData(mapping, negotiationForm, request, response);
        loadCodeObjects(negotiationForm.getNegotiationDocument().getNegotiation());
        docHandler(mapping, form, request, response);
        return actionForward;
    }
    
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        loadCodeObjects(negotiationForm.getNegotiationDocument().getNegotiation());
        Negotiation negotiation = negotiationForm.getNegotiationDocument().getNegotiation();
        Negotiation oldNegotiation = getBusinessObjectService().findBySinglePrimaryKey(Negotiation.class,
                negotiation.getNegotiationId());
        if (negotiation.getNegotiationStatus() != null
                && getNegotiationService().getInProgressStatusCodes().contains(negotiation.getNegotiationStatus().getCode())) {
            // in the in progress status, the end date field is disabled, so this prvents a problem with moving back from
            // completed or suspended to in progress.
            negotiation.setNegotiationEndDate(null);
        }
        else if (oldNegotiation != null && oldNegotiation.getNegotiationStatus() != null
                && getNegotiationService().getInProgressStatusCodes().contains(oldNegotiation.getNegotiationStatus().getCode())
                && negotiation.getNegotiationStatus() != null
                && getNegotiationService().getCompletedStatusCodes().contains(negotiation.getNegotiationStatus().getCode())) {
            if (negotiation.getNegotiationEndDate() != null
                    && negotiationForm.getNegotiationActivityHelper().hasPendingActivities()) {
                StrutsConfirmation question = buildParameterizedConfirmationQuestion(mapping, form, request, response,
                        "changePendingActivitiesKey", KeyConstants.NEGOTIATION_CLOSE_PENDING_ACTIVITIES);
                question.setCaller(((KualiForm) question.getForm()).getMethodToCall());
                if (question.hasQuestionInstAttributeName()
                        && StringUtils.equals(question.getRequest().getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME),
                                question.getQuestionId())) {
                    Object buttonClicked = question.getRequest().getParameter(QUESTION_CLICKED_BUTTON);
                    if (ConfirmationQuestion.YES.equals(buttonClicked)) {
                        negotiationForm.getNegotiationActivityHelper().closeAllPendingActivities();
                    }
                    else {
                        negotiation.setNegotiationStatus(oldNegotiation.getNegotiationStatus());
                        negotiation.setNegotiationStatusId(oldNegotiation.getNegotiationStatusId());
                        return mapping.findForward(Constants.MAPPING_BASIC);
                    }
                }
                else {
                    return performQuestionWithoutInput(question, EMPTY_STRING);
                }
            }
            if (StringUtils.equals(negotiation.getNegotiationStatus().getCode(), getNegotiationService().getCompleteStatusCode())) {
                KraServiceLocator.getService(NegotiationNotificationService.class).sendCloseNotification(
                        negotiationForm.getDocument());
            }
        }
        if (oldNegotiation == null
                || !StringUtils.equals(negotiation.getAssociatedDocumentId(), oldNegotiation.getAssociatedDocumentId())) {
            Collection<Negotiation> otherNegotiations;
            if (negotiation.getNegotiationAssociationType() != null) {
                Map<String, Object> values = new HashMap<String, Object>();
                values.put("associatedDocumentId", negotiation.getAssociatedDocumentId());
                values.put("negotiationAssociationTypeId", negotiation.getNegotiationAssociationType().getId());
                otherNegotiations = getBusinessObjectService().findMatching(Negotiation.class, values);
            } else {
                otherNegotiations = new ArrayList<Negotiation>();
            }
            if (!otherNegotiations.isEmpty()) {
                StrutsConfirmation question = buildParameterizedConfirmationQuestion(mapping, form, request, response,
                        "duplicateLinkedNegotiations", KeyConstants.NEGOTIATION_DUPLICATE_LINKING, negotiation
                                .getNegotiationAssociationType().getDescription());
                question.setCaller(((KualiForm) question.getForm()).getMethodToCall());
                if (question.hasQuestionInstAttributeName()
                        && StringUtils.equals(question.getRequest().getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME),
                                question.getQuestionId())) {
                    Object buttonClicked = question.getRequest().getParameter(QUESTION_CLICKED_BUTTON);
                    if (ConfirmationQuestion.NO.equals(buttonClicked)) {
                        negotiation.setNegotiationAssociationType(oldNegotiation.getNegotiationAssociationType());
                        negotiation.setNegotiationAssociationTypeId(oldNegotiation.getNegotiationAssociationTypeId());
                        negotiation.setAssociatedDocumentId(oldNegotiation.getAssociatedDocumentId());
                        return mapping.findForward(Constants.MAPPING_BASIC);
                    }
                }
                else {
                    return performQuestionWithoutInput(question, EMPTY_STRING);
                }
            }
        }
        copyCustomDataToNegotiation(negotiationForm);
        ActionForward actionForward = super.save(mapping, form, request, response);
        if (negotiation.getUnAssociatedDetail() != null) {
            if (negotiation.getUnAssociatedDetail().getNegotiationId() == null) {
                negotiation.getUnAssociatedDetail().setNegotiationId(negotiation.getNegotiationId());
            }
            NegotiationUnassociatedDetail detail = negotiation.getUnAssociatedDetail();
            this.getBusinessObjectService().save(detail);
            negotiation
                    .setAssociatedDocumentId(negotiation.getUnAssociatedDetail().getNegotiationUnassociatedDetailId().toString());
            this.getBusinessObjectService().save(negotiation);
            detail.refresh();
        }
        if (!negotiationForm.getNegotiationUnassociatedDetailsToDelete().isEmpty()) {
            this.getBusinessObjectService().delete(negotiationForm.getNegotiationUnassociatedDetailsToDelete());
        }
       negotiation.refresh();
       return actionForward;
    }
    
    /**
     * Copy the custom data to the Award so that it can saved.
     * @param form
     */
    public void copyCustomDataToNegotiation(NegotiationForm negotiationForm) {
        negotiationForm.getCustomDataHelper().populateCustomAttributeValuesMap();
        if((negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationCustomDataList().size() == 0)) {
            copyCustomDataToNewNegotiation(negotiationForm);
        }else {
            copyCustomDataToExistingNegotiation(negotiationForm);
        }
    }
    
    /**
     * This method is called when custom data is created on a newly created Award. It initializes the list on Award and sets the values from the form
     * @param awardForm
     */
    private void copyCustomDataToNewNegotiation(NegotiationForm negotiationForm) {
        for (Map.Entry<String, String[]>customAttributeValue: negotiationForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            int customAttributeId = Integer.parseInt(customAttributeValue.getKey().substring(2));         
            NegotiationCustomData negotiationCustomData = new NegotiationCustomData();
            negotiationCustomData.setCustomAttribute(new CustomAttribute());
            negotiationCustomData.getCustomAttribute().setId(customAttributeId);
            negotiationCustomData.setCustomAttributeId((long) customAttributeId);
            negotiationCustomData.setNegotiation(negotiationForm.getNegotiationDocument().getNegotiation());
            if(customAttributeValue.getValue()[0] == null) {
                negotiationCustomData.setValue("");
            }else {
                negotiationCustomData.setValue(customAttributeValue.getValue()[0]);
            }
            negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationCustomDataList().add(negotiationCustomData);
            
        }
    }
    
    /**
     * This method copies the values from the form to the awardCustomDataList on Award.
     * @param awardForm
     */
    private void copyCustomDataToExistingNegotiation(NegotiationForm negotiationForm) {
        for (Map.Entry<String, String[]>customAttributeValue: negotiationForm.getCustomDataHelper().getCustomAttributeValues().entrySet()) {
            int customAttributeId = Integer.parseInt(customAttributeValue.getKey().substring(2));         
            String value = customAttributeValue.getValue()[0];
            for(NegotiationCustomData negotiationCustomData : negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationCustomDataList()) {
                if(customAttributeId == negotiationCustomData.getCustomAttributeId()) {
                    negotiationCustomData.setValue(value);
                }
            }
        }
    }
    
    

    private void loadCodeObjects(Negotiation negotiation) {
        Map primaryKeys = new HashMap();
        negotiation.refreshReferenceObject("negotiationAgreementType");
        negotiation.refreshReferenceObject("negotiationAssociationType");
        negotiation.refreshReferenceObject("negotiationStatus");

        if (negotiation.getUnAssociatedDetail() != null) {
            negotiation.getUnAssociatedDetail().refreshReferenceObject("sponsor");
            negotiation.getUnAssociatedDetail().refreshReferenceObject("primeSponsor");
            negotiation.getUnAssociatedDetail().refreshReferenceObject("subAwardOrganization");
        }

        for (NegotiationActivity activity : negotiation.getActivities()) {
            if (activity.isUpdated()) {
                activity.updateActivity();
            }
        }
    }

    public ActionForward changeAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;

        if (negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationAssociationType() != null) {
            String oldAssociation = negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationAssociationType()
                    .getDescription();
            Long newAssociationTypeId = negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationAssociationTypeId();
            Map params = new HashMap();
            params.put("NEGOTIATION_ASSC_TYPE_ID", newAssociationTypeId);
            NegotiationAssociationType asscType = (NegotiationAssociationType) this.getBusinessObjectService().findByPrimaryKey(
                    NegotiationAssociationType.class, params);
            String newAssociation = asscType != null ? asscType.getDescription() : "nothing";
            if (StringUtils.equals(negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationAssociationType()
                    .getCode(), NegotiationAssociationType.NONE_ASSOCIATION)) {
                newAssociation = newAssociation + ", you will lose any negotiation attributes that have been entered";
            }
            request.setAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE, "methodToCall.changeAssociationRedirector");
            ActionForward confirmAction = confirm(
                    buildParameterizedConfirmationQuestion(mapping, form, request, response, "changeAssociationKey",
                            KeyConstants.NEGOTIATION_CHANGE_ASSOCIATION_TYPE_MESSAGE, oldAssociation, newAssociation),
                    "confirmedChangeAssociation", "resetChangeAssociationType");
            return confirmAction;
        }
        else {
            return confirmedChangeAssociation(mapping, negotiationForm, request, response);
        }
    }

    public ActionForward changeAssociationRedirector(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String buttonClicked = request.getParameter("buttonClicked").toString();
        /**
         * 0 is Yes 1 is no
         */
        if (StringUtils.equals(buttonClicked, "0")) {
            return confirmedChangeAssociation(mapping, form, request, response);
        }
        else {
            return resetChangeAssociationType(mapping, form, request, response);
        }
    }

    public ActionForward confirmedChangeAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        NegotiationForm negotiationForm = (NegotiationForm) form;
        Long newAssociationTypeId = negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationAssociationTypeId();
        Map params = new HashMap();
        params.put("NEGOTIATION_ASSC_TYPE_ID", newAssociationTypeId);
        NegotiationAssociationType asscType = (NegotiationAssociationType) this.getBusinessObjectService().findByPrimaryKey(
                NegotiationAssociationType.class, params);
        negotiationForm.getNegotiationDocument().getNegotiation().setNegotiationAssociationType(asscType);
        negotiationForm.getNegotiationDocument().getNegotiation().setAssociatedDocumentId("");

        if (asscType != null && StringUtils.equalsIgnoreCase(asscType.getCode(), NegotiationAssociationType.NONE_ASSOCIATION)) {
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

    public ActionForward resetChangeAssociationType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        NegotiationForm negotiationForm = (NegotiationForm) form;

        Negotiation negotiation = negotiationForm.getNegotiationDocument().getNegotiation();
        Map params = new HashMap();
        params.put("NEGOTIATION_ID", negotiation.getNegotiationId());

        Negotiation dbNegotiation = (Negotiation) this.getBusinessObjectService().findByPrimaryKey(Negotiation.class, params);

        negotiation.setNegotiationAssociationTypeId(dbNegotiation.getNegotiationAssociationType().getId());
        negotiation.setNegotiationAssociationType(dbNegotiation.getNegotiationAssociationType());
        return actionForward;
    }

    public ActionForward addActivity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getNegotiationActivityHelper().addActivity();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward restrictActivity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getNegotiationActivityHelper().setRestrictedActivity(Boolean.TRUE, getActivityIndex(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward unrestrictActivity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getNegotiationActivityHelper().setRestrictedActivity(Boolean.FALSE, getActivityIndex(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward addAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getNegotiationActivityHelper().addAttachment(getActivityIndex(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getNegotiationActivityHelper().deleteAttachment(getActivityIndex(request), getAttachmentIndex(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward restrictAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getNegotiationActivityHelper().setRestrictedAttachment(Boolean.TRUE, getActivityIndex(request),
                getAttachmentIndex(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward unrestrictAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getNegotiationActivityHelper().setRestrictedAttachment(Boolean.FALSE, getActivityIndex(request),
                getAttachmentIndex(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward viewAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        NegotiationActivityAttachment attachment = negotiationForm.getNegotiationActivityHelper()
                .getActivity(getActivityIndex(request)).getAttachments().get(getAttachmentIndex(request));
        final AttachmentFile file = attachment.getFile();
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);

        return null;
    }

    public ActionForward viewAttachmentFromAllAttachments(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        NegotiationActivityAttachment attachment = negotiationForm.getNegotiationActivityHelper().getAllAttachments()
                .get(getAttachmentIndex(request));
        final AttachmentFile file = attachment.getFile();
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);

        return null;
    }

    protected Integer getActivityIndex(HttpServletRequest request) {
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            return Integer.parseInt(StringUtils.substringBetween(parameterName, ".activityIndex", "."));
        }
        return null;
    }

    protected Integer getAttachmentIndex(HttpServletRequest request) {
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            return Integer.parseInt(StringUtils.substringBetween(parameterName, ".attachmentIndex", "."));
        }
        return null;

    }

    /**
     * Medusa refresh button action.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward refreshView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward sort(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
}
