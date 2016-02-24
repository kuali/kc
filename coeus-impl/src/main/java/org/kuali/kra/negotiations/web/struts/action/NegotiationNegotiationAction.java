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
package org.kuali.kra.negotiations.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.negotiations.bo.*;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.notifications.NegotiationCloseNotificationContext;
import org.kuali.kra.negotiations.notifications.NegotiationNotification;
import org.kuali.kra.negotiations.printing.NegotiationActivityPrintType;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.kuali.rice.krad.util.KRADConstants.EMPTY_STRING;
import static org.kuali.rice.krad.util.KRADConstants.QUESTION_CLICKED_BUTTON;

/**
 * 
 * This class handles the home screen for negotiations.
 */
public class NegotiationNegotiationAction extends NegotiationAction {


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        ActionForward actionForward = super.execute(mapping, form, request, response);
        loadCodeObjects(negotiationForm.getNegotiationDocument().getNegotiation());
        negotiationForm.getMedusaBean().setModuleName("neg");
        negotiationForm.getMedusaBean().setModuleIdentifier(negotiationForm.getNegotiationDocument().getNegotiation().getNegotiationId());
        negotiationForm.getMedusaBean().generateParentNodes();
        negotiationForm.getNegotiationActivityHelper().sortActivities();
        negotiationForm.getNegotiationActivityHelper().generateAllAttachments();
        return actionForward;
    }
    
    public ActionForward negotiation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(Constants.NEGOTIATION_HOME_PAGE);
    }

    /**
     * Should only be used when opening the document from a search and clicking on the medusa link.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward medusa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        if (negotiationForm.getDocument().getDocumentNumber() == null) {
            // if we are entering this from the search results
            // modified to use rice's loadDoc method so that authorization takes place,
            // otherwise people with no permissions can view the document through medusa.
            // This is not a problem in other docs since in those cases, the lookup results are automatically
            // filtered with permissions. If that filter is removed, it will become an issue.
            super.loadDocument(negotiationForm);
        }
        //close the document overview as it can't be default closed via jsp.
        negotiationForm.getTabStates().put("DocumentOverview", "false");
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        negotiationForm.getCustomDataHelper().prepareCustomData();
        loadCodeObjects(negotiationForm.getNegotiationDocument().getNegotiation());
        prepareNegotiation(negotiationForm);
        //docHandler(mapping, form, request, response);
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
        boolean sendCloseNotification = false;
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
                        && StringUtils.equals(question.getRequest().getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME),
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
                sendCloseNotification = true;
            }
        }
        ActionForward actionForward = super.save(mapping, form, request, response);
        
        NegotiationCloseNotificationContext context = new NegotiationCloseNotificationContext(negotiationForm.getNegotiationDocument());
        
        if (sendCloseNotification && GlobalVariables.getMessageMap().getErrorCount() == 0) {
            if (negotiationForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                negotiationForm.getNotificationHelper().initializeDefaultValues(context);
                return mapping.findForward("notificationEditor");
            } else {
                getNotificationService().sendNotificationAndPersist(context, new NegotiationNotification(), negotiation);
            }
        }
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
    
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        Negotiation negotiation = negotiationForm.getNegotiationDocument().getNegotiation();
        Negotiation oldNegotiation = null;
        if (negotiation.getNegotiationId() != null) {
            oldNegotiation = getBusinessObjectService().findBySinglePrimaryKey(Negotiation.class,
                    negotiation.getNegotiationId());
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
                        && StringUtils.equals(question.getRequest().getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME),
                                question.getQuestionId())) {
                    Object buttonClicked = question.getRequest().getParameter(QUESTION_CLICKED_BUTTON);
                    if (ConfirmationQuestion.NO.equals(buttonClicked)) {
                        if (oldNegotiation != null) {
                        negotiation.setAssociatedDocumentId(oldNegotiation.getAssociatedDocumentId());
                        } else {
                            negotiation.setAssociatedDocumentId(null);
                        }
                        return mapping.findForward(Constants.MAPPING_BASIC);
                    }
                }
                else {
                    return performQuestionWithoutInput(question, EMPTY_STRING);
                }
            }
        }
        return super.refresh(mapping, form, request, response);
        
    }
    
    
    @Override
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        loadCodeObjects(negotiationForm.getNegotiationDocument().getNegotiation());
        return super.close(mapping, negotiationForm, request, response);
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
        Negotiation negotiation = negotiationForm.getNegotiationDocument().getNegotiation();
        Long associationTypeId = negotiation.getNegotiationAssociationTypeId();
        if(associationTypeId != null){
            if (negotiation.getNegotiationAssociationType() != null) {
                negotiation.setOldNegotiationAssociationTypeId(negotiation.getNegotiationAssociationType().getId());
                String oldAssociation = negotiation.getNegotiationAssociationType()
                        .getDescription();
                NegotiationAssociationType asscType = (NegotiationAssociationType) this.getBusinessObjectService().findBySinglePrimaryKey(
                        NegotiationAssociationType.class, associationTypeId);
                String newAssociation = asscType != null ? asscType.getDescription() : "nothing";
                if (StringUtils.equals(negotiation.getNegotiationAssociationType()
                        .getCode(), NegotiationAssociationType.NONE_ASSOCIATION)) {
                    newAssociation = newAssociation + ".  You will lose any Negotiation attributes that have been entered";
                }
                request.setAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE, "methodToCall.changeAssociationRedirector");
                ActionForward confirmAction = confirm(
                        buildParameterizedConfirmationQuestion(mapping, form, request, response, "changeAssociationKey",
                                KeyConstants.NEGOTIATION_CHANGE_ASSOCIATION_TYPE_MESSAGE, oldAssociation, newAssociation),
                        "confirmedChangeAssociation", "resetChangeAssociationType");
                return confirmAction;
            }
            else {
                return confirmedChangeAssociation(mapping, negotiationForm, request, response);
            }
        }else{
            negotiation.setAssociatedDocumentId(EMPTY_STRING);
            return mapping.findForward(Constants.MAPPING_BASIC);
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
        if (newAssociationTypeId == null) {
            // we've lost association with Negotiation, probably from user hitting back button, so exit gracefully
            return mapping.findForward(Constants.NEGOTIATION_LOST_PLACE_PAGE);
        }
        NegotiationAssociationType asscType = (NegotiationAssociationType) this.getBusinessObjectService().findBySinglePrimaryKey(
                NegotiationAssociationType.class, newAssociationTypeId);
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
        negotiation.setNegotiationAssociationTypeId(negotiation.getOldNegotiationAssociationTypeId());
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
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            return Integer.parseInt(StringUtils.substringBetween(parameterName, ".activityIndex", "."));
        }
        return null;
    }

    protected Integer getAttachmentIndex(HttpServletRequest request) {
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
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
    /**
     * NegotiationActivities print all button action.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printNegotiationActivity(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {   
        Map<String, Object> reportParameters = new HashMap<String, Object>();        
        NegotiationForm negotiationForm = (NegotiationForm) form;  
        NegotiationDocument negotiationDocument = negotiationForm.getNegotiationDocument();     
        Negotiation negotiation = negotiationDocument.getNegotiation();
        negotiation.setPrintindex(0);
        negotiation.setPrintAll(StringUtils.equals(negotiationForm.getFilterActivities(), negotiationForm.getFilterAllActivities()));
        AttachmentDataSource dataStream = getNegotiationPrintingService().printNegotiationActivityReport
                          (negotiation, NegotiationActivityPrintType.NEGOTIATION_ACTIVITY_REPORT, reportParameters);                                                  
        streamToResponse(dataStream,response);       
        
        return null;
    }    
    
    /**
     * NegotiationActivities print button action.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printActivity(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception { 
        int printindex =getActivityIndex(request);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        NegotiationForm negotiationForm = (NegotiationForm) form;  
        NegotiationDocument negotiationDocument = negotiationForm.getNegotiationDocument();     
        Negotiation negotiation = negotiationDocument.getNegotiation();        
        negotiation.setPrintindex(printindex+1);
        AttachmentDataSource dataStream = getNegotiationPrintingService().printNegotiationActivityReport
                          (negotiation, NegotiationActivityPrintType.NEGOTIATION_ACTIVITY_REPORT, reportParameters);                                                  
        streamToResponse(dataStream,response);
        
        return null;
    }
 
    public ActionForward deleteActivity(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception { 
        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, "deleteActivityKey",
                        KeyConstants.NEGOTIATION_DELETE_ACTIVITY),
                        "confirmDeleteActivity", null);
    }        
    
    public ActionForward confirmDeleteActivity(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        NegotiationForm negotiationForm = (NegotiationForm) form;
        negotiationForm.getNegotiationActivityHelper().deleteActivity(getActivityIndex(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Returns the user directly to the Portal.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward returnToPortal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward(KRADConstants.MAPPING_PORTAL);
    }
}
