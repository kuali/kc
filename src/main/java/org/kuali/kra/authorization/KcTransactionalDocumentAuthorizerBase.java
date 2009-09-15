/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.authorization;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.bo.impl.KimAttributes;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.kns.authorization.BusinessObjectAuthorizerBase;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * Base class for all KC document authorizers.  The document authorizer determines both the
 * edit modes and the document actions.
 */
public abstract class KcTransactionalDocumentAuthorizerBase extends BusinessObjectAuthorizerBase implements TransactionalDocumentAuthorizer{

    public static final String PRE_ROUTING_ROUTE_NAME = "PreRoute";
    public static final String EDIT_MODE_DEFAULT_TRUE_VALUE = "TRUE";
    public static final String USER_SESSION_METHOD_TO_CALL_OBJECT_KEY = "METHOD_TO_CALL_KEYS_METHOD_OBJECT_KEY";
    public static final String USER_SESSION_METHOD_TO_CALL_COMPLETE_OBJECT_KEY = "METHOD_TO_CALL_KEYS_COMPLETE_OBJECT_KEY";

    /**
     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#getDocumentActions(org.kuali.rice.kns.document.Document, org.kuali.rice.kim.bo.Person, java.util.Set)
     */
    public Set<String> getDocumentActions(Document document, Person user, Set<String> oldDocumentActions) {
        return getDocumentActions(document, user);
    }
    
    /**
     * Get the document actions.  This method can be overridden by a subclass, but that is
     * not usually done.  Rather, the subclass should override the necessary "can" methods.
     * @param document the document
     * @param user the person requesting access to the document
     * @return the set of document actions that the user can perform
     */
    protected Set<String> getDocumentActions(Document document, Person user) {
        Set<String> documentActions = new HashSet<String>();
        
        if (canEdit(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_EDIT);
        }
        
        if (canEdit(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_EDIT__DOCUMENT_OVERVIEW);
        }
        
        if (canAnnotate(document, user)){
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_ANNOTATE);
        }
         
        if (canClose(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CLOSE);
        }
         
        if (canSave(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_SAVE);
        }
        
        if (canRoute(document, user)){
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_ROUTE);
        }
         
        if (canCancel(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_CANCEL);
        }
         
        if (canReload(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_RELOAD);
        }
        if (canCopy(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_COPY);
        }
        if (canPerformRouteReport(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_PERFORM_ROUTE_REPORT);
        }
        
        if (canAdHocRoute(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_AD_HOC_ROUTE);
        }
        
        if (canBlanketApprove(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_BLANKET_APPROVE);
        }
        if (canApprove(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_APPROVE);
        }
        if (canDisapprove(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_DISAPPROVE);
        }
        if (canSendAdhocRequests(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_SEND_ADHOC_REQUESTS);
        }
 
        if (canAcknowledge(document, user)) {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_ACKNOWLEDGE);
        }
        
        if (canFYI(document, user))  {
            documentActions.add(KNSConstants.KUALI_ACTION_CAN_FYI);
        }
        
        return documentActions;
    }
    
    /**
     * Can the document be edited?
     * @param document the document
     * @return true if editable; otherwise false
     */
    protected final boolean canEdit(Document document) {
        boolean canEdit = false;
        KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        if (workflowDocument.stateIsInitiated() || workflowDocument.stateIsSaved() || workflowDocument.stateIsEnroute() || workflowDocument.stateIsException()) {
            canEdit = true; 
        }
        
        return canEdit;
    }
    
    /**
     * Can the document be annotated?
     * @param document the document
     * @return true if annotatable; otherwise false
     */
    protected final boolean canAnnotate(Document document) {
        return canEdit(document);
    }
    
    /**
     * Can the document be reloaded from the database?
     * @param document the document
     * @return true if reloadable; otherwise false
     */
    protected final boolean canReload(Document document) {
        KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        return (canEdit(document) && !workflowDocument.stateIsInitiated()) ;
             
    }
    
    /**
     * Can the document be closed?
     * @param document the document
     * @return true if closeable; otherwise false
     */
    protected final boolean canClose(Document document) {
        return true;
    }
    
    /**
     * Can the document be saved to the database?
     * @param document the document
     * @return true if saveable; otherwise false
     */
    protected final boolean canSave(Document document) {
        return canEdit(document);
    }
    
    /**
     * Can the document be routed into workflow?
     * @param document the document
     * @return true if routeable; otherwise false
     */
    protected final boolean canRoute(Document document) {
        boolean canRoute = false;
        KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        if (workflowDocument.stateIsInitiated() || workflowDocument.stateIsSaved()){
             canRoute = true;
        }
        return canRoute;
    }
        
    /**
     * Can the document be canceled?
     * @param document the document
     * @return true if cancelable; otherwise false
     */
    protected final boolean canCancel(Document document) {
        return canEdit(document);
    }
    
    /**
     * Can the document be copied?
     * @param document the document
     * @return true if copyable; otherwise false
     */
    protected final boolean canCopy(Document document) {
         boolean canCopy = false;
         if(document.getAllowsCopy()){
             canCopy = true;
         }
         return canCopy;
    }
    
    /**
     * Can route report be generated for the document?
     * @param document the document
     * @return true if a route report can be generated; otherwise false
     */
    protected final boolean canPerformRouteReport(Document document) {
        KualiConfigurationService kualiConfigurationService = KNSServiceLocator.getKualiConfigurationService();
        return kualiConfigurationService.getIndicatorParameter( KNSConstants.KNS_NAMESPACE, KNSConstants.DetailTypes.DOCUMENT_DETAIL_TYPE, KNSConstants.SystemGroupParameterNames.DEFAULT_CAN_PERFORM_ROUTE_REPORT_IND);
    }
    
    /**
     * Can an AdHoc Route be added to the document?
     * @param document the document
     * @return true if an AdHoc route can be added; otherwise false
     */
    protected final boolean canAdHocRoute(Document document) {
        KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        return (canEdit(document)&& !workflowDocument.stateIsException());
    }
    
    /**
     * Can the document be blanket approved?
     * @param document the document
     * @return true if the document can be blanket approved; otherwise false
     */
    protected final boolean canBlanketApprove(Document document) {
        return canEdit(document);
    }
    
    /**
     * Can AdHoc requests to be to the document?
     * @param document the document
     * @return true if AdHoc requests can be sent; otherwise false
     */
    protected final boolean canSendAdhocRequests(Document document) {
        return canAdHocRoute(document) && document.getDocumentHeader().getWorkflowDocument().stateIsEnroute();
    }
   
    /**
     * Can the user edit the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can edit the document; otherwise false
     */
    public boolean canEdit(Document document, Person user) {
        return canEdit(document);
    }
    
    /**
     * Can the user annotate the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can annotate the document; otherwise false
     */
    protected boolean canAnnotate(Document document, Person user) {
        return canAnnotate(document);
    }
    
    /**
     * Can the user close the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can close the document; otherwise false
     */
    protected boolean canClose(Document document, Person user) {
        return canClose(document);
    }
    
    /**
     * Can the user save the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can save the document; otherwise false
     */
    protected boolean canSave(Document document, Person user) {
        return canEdit(document);
    }
    
    /**
     * Can the user cancel the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can cancel the document; otherwise false
     */
    protected boolean canCancel(Document document, Person user) {
        return canCancel(document);
    }
    
    /**
     * Can the user reload the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can reload the document; otherwise false
     */
    protected boolean canReload(Document document, Person user) {
        return canReload(document);
    }
    
    /**
     * Can the user copy the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can copy the document; otherwise false
     */
    protected boolean canCopy(Document document, Person user) {
        return canCopy(document);
    }
    
    /**
     * Can the user perform route reports for the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can perform route reports; otherwise false
     */
    protected boolean canPerformRouteReport(Document document, Person user) {
        return canPerformRouteReport(document);
    }
    
    /**
     * Can the user approve the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can approve the document; otherwise false
     */
    protected boolean canApprove(Document document, Person user) {
        return true;
    }
    
    /**
     * Can the user disapprove the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can disapprove the document; otherwise false
     */
    protected boolean canDisapprove(Document document, Person user) {
        return true;
    }
    
    /**
     * Can the user send adhoc requests to the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can send adhoc requests; otherwise false
     */
    protected boolean canSendAdhocRequests(Document document, Person user) {
        return canSendAdhocRequests(document);
    }
    
    /**
     * Can the user blanket approve the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can blanket approve the document; otherwise false
     */
    protected boolean canBlanketApprove(Document document, Person user) {
        return canBlanketApprove(document) && 
               isAuthorizedByTemplate(
                        document,
                        KNSConstants.KUALI_RICE_WORKFLOW_NAMESPACE,
                        KimConstants.PermissionTemplateNames.BLANKET_APPROVE_DOCUMENT,
                        user.getPrincipalId());
    }
    
    /**
     * Can the user route the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can route the document; otherwise false
     */
    protected boolean canRoute(Document document, Person user) {
       return canRoute(document) && 
              isAuthorizedByTemplate(document,
                        KNSConstants.KUALI_RICE_WORKFLOW_NAMESPACE,
                        KimConstants.PermissionTemplateNames.ROUTE_DOCUMENT,
                        user.getPrincipalId());
    }
    
    /**
     * Can the user acknowledge the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can acknowledge the document; otherwise false
     */
    protected boolean canAcknowledge(Document document, Person user) { 
        return canTakeRequestedAction(document, KEWConstants.ACTION_REQUEST_ACKNOWLEDGE_REQ, user);
    }
    
    /**
     * Can the user FYI the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can FYI the document; otherwise false
     */
    protected boolean canFYI(Document document, Person user) {
        return canTakeRequestedAction(document, KEWConstants.ACTION_REQUEST_FYI_REQ, user);
    }
    
    /**
     * Can the user approve and disapprove the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can approve and disapprove the document; otherwise false
     */
    protected boolean canApproveAndDisapprove(Document document, Person user) {
         return canTakeRequestedAction(document, KEWConstants.ACTION_REQUEST_APPROVE_REQ, user);
    }
    
    /**
     * Can the user do an adhoc route for given document?
     * @param document the document
     * @param user the user
     * @return true if the user can do an adhoc route; otherwise false
     */
    protected boolean canAdHocRoute(Document document, Person user) {
        return canAdHocRoute(document);   
    }
    
    /**
     * Can the user receive an adhoc route for the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can recieve an adhoc route; otherwise false
     */
    public final boolean canReceiveAdHoc(Document document, Person user, String actionRequestCode) {
        Map<String,String> additionalPermissionDetails = new HashMap<String, String>();
        additionalPermissionDetails.put(KimAttributes.ACTION_REQUEST_CD, actionRequestCode);
        return isAuthorizedByTemplate(document,
                KNSConstants.KUALI_RICE_WORKFLOW_NAMESPACE,
                KimConstants.PermissionTemplateNames.AD_HOC_REVIEW_DOCUMENT,
                user.getPrincipalId(), additionalPermissionDetails, null );
    }
    
    /**
     * Can the user add a note attachment to the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can add a note attachment; otherwise false
     */
    public final boolean canAddNoteAttachment(Document document,
                                              String attachmentTypeCode, Person user) {
        Map<String, String> additionalPermissionDetails = new HashMap<String, String>();
        if (attachmentTypeCode != null) {
            additionalPermissionDetails.put(KimAttributes.ATTACHMENT_TYPE_CODE,
                    attachmentTypeCode);
        }
        return isAuthorizedByTemplate(document, KNSConstants.KNS_NAMESPACE,
                KimConstants.PermissionTemplateNames.ADD_NOTE_ATTACHMENT, user
                        .getPrincipalId(), additionalPermissionDetails, null);
    }
    
    /**
     * Can the user delete a note attachment for the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can delete a note attachment; otherwise false
     */
    public final boolean canDeleteNoteAttachment(Document document,
                            String attachmentTypeCode, String createdBySelfOnly, Person user) {
        Map<String, String> additionalPermissionDetails = new HashMap<String, String>();
        if (attachmentTypeCode != null) {
            additionalPermissionDetails.put(KimAttributes.ATTACHMENT_TYPE_CODE,
                    attachmentTypeCode);
        }
        additionalPermissionDetails.put(KimAttributes.CREATED_BY_SELF_ONLY,
                createdBySelfOnly);
        return isAuthorizedByTemplate(document, KNSConstants.KNS_NAMESPACE,
                KimConstants.PermissionTemplateNames.DELETE_NOTE_ATTACHMENT,
                user.getPrincipalId(), additionalPermissionDetails, null);
    }
    
    /**
     * Can the user view a note attachment for the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can view a note attachment; otherwise false
     */
    public final boolean canViewNoteAttachment(Document document,
                                               String attachmentTypeCode, Person user) {
        Map<String, String> additionalPermissionDetails = new HashMap<String, String>();
        if (attachmentTypeCode != null) {
            additionalPermissionDetails.put(KimAttributes.ATTACHMENT_TYPE_CODE,
                    attachmentTypeCode);
        }
        return isAuthorizedByTemplate(document, KNSConstants.KNS_NAMESPACE,
                KimConstants.PermissionTemplateNames.VIEW_NOTE_ATTACHMENT, user
                        .getPrincipalId(), additionalPermissionDetails, null);
    }
    
    /**
     * Can the user take the requested action against the given document?
     * @param document the document
     * @param user the user
     * @return true if the user can take the requested action; otherwise false
     */
    private boolean canTakeRequestedAction(Document document,
                                           String actionRequestCode, Person user) {
        Map<String, String> additionalPermissionDetails = new HashMap<String, String>();
        additionalPermissionDetails.put(KimAttributes.ACTION_REQUEST_CD,
                actionRequestCode);
        return isAuthorizedByTemplate(document, KNSConstants.KNS_NAMESPACE,
                KimConstants.PermissionTemplateNames.TAKE_REQUESTED_ACTION,
                user.getPrincipalId(), additionalPermissionDetails, null);
    }

    /**
     * @see org.kuali.rice.kns.authorization.BusinessObjectAuthorizerBase#addPermissionDetails(org.kuali.rice.kns.bo.BusinessObject, java.util.Map)
     */
    @Override
    protected void addPermissionDetails(BusinessObject businessObject,
            Map<String, String> attributes) {
        super.addPermissionDetails(businessObject, attributes);
        if (businessObject instanceof Document) {
            addStandardAttributes((Document) businessObject, attributes);
        }
    }

    /**
     * @see org.kuali.rice.kns.authorization.BusinessObjectAuthorizerBase#addRoleQualification(org.kuali.rice.kns.bo.BusinessObject, java.util.Map)
     */
    @Override
    protected void addRoleQualification(BusinessObject businessObject,
            Map<String, String> attributes) {
        super.addRoleQualification(businessObject, attributes);
        if (businessObject instanceof Document) {
            addStandardAttributes((Document) businessObject, attributes);
        }
    }

    private void addStandardAttributes(Document document,
            Map<String, String> attributes) {
        KualiWorkflowDocument wd = document.getDocumentHeader()
                .getWorkflowDocument();
        attributes.put(KimAttributes.DOCUMENT_NUMBER, document
                .getDocumentNumber());
        attributes.put(KimAttributes.DOCUMENT_TYPE_NAME, wd.getDocumentType());
        if (wd.stateIsInitiated() || wd.stateIsSaved()) {
            attributes.put(KimAttributes.ROUTE_NODE_NAME,
                    PRE_ROUTING_ROUTE_NAME);
        } else {
            attributes.put(KimAttributes.ROUTE_NODE_NAME, wd
                    .getCurrentRouteNodeNames());
        }
        attributes.put(KimAttributes.ROUTE_STATUS_CODE, wd.getRouteHeader()
                .getDocRouteStatus());
    }
    

    /**
     * Get the TaskAuthorizationService.
     * @return
     */
    protected final TaskAuthorizationService getTaskAuthorizationService(){
        return (TaskAuthorizationService) KraServiceLocator.getService(TaskAuthorizationService.class);        
    }
    
    /** {@inheritDoc} */
    public boolean canSendAdHocRequests(Document arg0, String arg1, Person arg2) {
        return true;
    }
}
