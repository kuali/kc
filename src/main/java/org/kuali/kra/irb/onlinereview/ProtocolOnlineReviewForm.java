/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.onlinereview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.onlinereview.authorization.ProtocolOnlineReviewTask;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewFormBase;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolOnlineReviewForm extends ProtocolOnlineReviewFormBase {
    
    private static org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolOnlineReviewForm.class);
    private static final Map<String,String> ONLINE_REVIEW_APPROVE_BUTTON_MAP;
    
    static {
        ONLINE_REVIEW_APPROVE_BUTTON_MAP = new HashMap<String,String>();
        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.ONLINE_REVIEW_ROUTE_NODE_ADMIN_INITIAL_REVIEW, "buttonsmall_send_review_request.gif");
        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.ONLINE_REVIEW_ROUTE_NODE_ADMIN_REVIEW,"buttonsmall_accept_review_comments.gif");
        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.ONLINE_REVIEW_ROUTE_NODE_ONLINE_REVIEWER, "buttonsmall_approve_this_review.gif");
    }

    public ProtocolOnlineReviewForm() throws Exception {
        super();
    }

    private static final long serialVersionUID = -7633960906991275328L;

    @Override
    protected String getDefaultDocumentTypeName() {
        return "ProtocolOnlineReviewDocument";
    }

    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_PROTOCOL;
    }

    @Override
    public List<ExtraButton> getExtraActionsButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        ProtocolOnlineReviewDocument doc = (ProtocolOnlineReviewDocument)this.getProtocolOnlineReviewDocument();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;

        
        TaskAuthorizationService tas = KraServiceLocator.getService(TaskAuthorizationService.class);
               
        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new ProtocolOnlineReviewTask("rejectProtocolOnlineReview",doc))
                && doc.getDocumentHeader().getWorkflowDocument().isEnroute()
                && ProtocolOnlineReviewStatus.FINAL_STATUS_CD.equals(doc.getProtocolOnlineReview().getProtocolOnlineReviewStatusCode())) {
            String resubmissionImage = KRADServiceLocator.getKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_return_to_reviewer.gif";
            addExtraButton("methodToCall.rejectOnlineReview", resubmissionImage, "Return to reviewer");
        }
        
        return extraButtons;
    }

    @Override
    public boolean getAdminFieldsEditable() {
        return KraServiceLocator.getService(KraAuthorizationService.class).hasPermission(GlobalVariables.getUserSession().getPrincipalId(), getProtocolOnlineReviewDocument().getProtocolOnlineReview().getProtocol(),PermissionConstants.MAINTAIN_ONLINE_REVIEWS);
    }

    @Override
    protected Log getLogHook() {
        return LOG;
    }

    @Override
    protected Map<String, String> getOnlineReviewApproveButtonMapHook() {
        return ONLINE_REVIEW_APPROVE_BUTTON_MAP;
    }

    public boolean getIrbAdminFieldsEditable() {
        return getAdminFieldsEditable();
    }
    
    
 // TODO ********************** commented out during IRB backfit ************************
//    ProtocolOnlineReviewDocument document;
//    private static org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolOnlineReviewForm.class);
//    
//    private static final Map<String,String> ONLINE_REVIEW_APPROVE_BUTTON_MAP;
//    
//    static {
//        ONLINE_REVIEW_APPROVE_BUTTON_MAP = new HashMap<String,String>();
//        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.ONLINE_REVIEW_ROUTE_NODE_ADMIN_INITIAL_REVIEW, "buttonsmall_send_review_request.gif");
//        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.ONLINE_REVIEW_ROUTE_NODE_ADMIN_REVIEW,"buttonsmall_accept_review_comments.gif");
//        ONLINE_REVIEW_APPROVE_BUTTON_MAP.put(Constants.ONLINE_REVIEW_ROUTE_NODE_ONLINE_REVIEWER, "buttonsmall_approve_this_review.gif");
//    }
//    
//    private static final String DEFAULT_APPROVE_BUTTON = "buttonsmall_approve_this_review.gif";
//    
//    
//    public ProtocolOnlineReviewForm() throws Exception {
//        super();
//        this.registerEditableProperty("methodToCall");
//    }
//
//    /**
//     * Gets a {@link ProtocolDocument ProtocolDocument}.
//     * @return {@link ProtocolDocument ProtocolDocument}
//     */
//    public ProtocolOnlineReviewDocument getProtocolOnlineReviewDocument() {
//        return (ProtocolOnlineReviewDocument) super.getDocument();
//    }
//
//    /** {@inheritDoc} */
//    @Override
//    protected String getDefaultDocumentTypeName() {
//        return "ProtocolOnlineReviewDocument";
//    }
//    
//    /*
//     * Override of the set document so we can populate this form
//     * with doucment information outside of a request.
//     */
//    @Override
//    public void setDocument(Document document) {
//        super.setDocument(document);
//        this.setDocTypeName(getDefaultDocumentTypeName());
//    }
//    
//    /**
//     * 
//     * This method is a wrapper method for getting DataDictionary Service using the Service Locator.
//     * @return
//     */
//    protected DataDictionaryService getDataDictionaryService(){
//        return (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
//    }
//
//    @Override
//    public void populate(HttpServletRequest request) {
//        super.populate(request);
//    }
//    
//    @Override
//    public void populateHeaderFields(WorkflowDocument workflowDocument) {
//        super.populateHeaderFields(workflowDocument);
//    }
//
//    /* Reset method
//     * @param mapping
//     * @param request
//     */
//    @Override
//    public void reset(ActionMapping mapping, HttpServletRequest request) {
//        super.reset(mapping, request);
//    }
//    
//    
//    @Override
//    protected void setSaveDocumentControl(Map editMode) {
//      
//    }
//    
//    @Override
//    protected String getLockRegion() {
//        return KraAuthorizationConstants.LOCK_DESCRIPTOR_PROTOCOL;
//    }
//    
//    @Override
//    public String getActionName() {
//        return "protocol";
//    }
//
//    /**
//     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getResearchDocument()
//     */
//    public ResearchDocumentBase getResearchDocument() {
//        return (ResearchDocumentBase) this.getDocument();
//    }
//
//    public PermissionsHelperBase getPermissionsHelper() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public CustomDataHelperBase getCustomDataHelper() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public boolean isAuditActivated() {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    public void setAuditActivated(boolean auditActivated) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    public List<ExtraButton> getExtraActionsButtons() {
//        // clear out the extra buttons array
//        extraButtons.clear();
//        ProtocolOnlineReviewDocument doc = this.getProtocolOnlineReviewDocument();
//        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
//
//        
//        TaskAuthorizationService tas = KraServiceLocator.getService(TaskAuthorizationService.class);
//               
//        if( tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new ProtocolOnlineReviewTask("rejectProtocolOnlineReview",doc))
//                && doc.getDocumentHeader().getWorkflowDocument().isEnroute()
//                && ProtocolOnlineReviewStatus.FINAL_STATUS_CD.equals(doc.getProtocolOnlineReview().getProtocolOnlineReviewStatusCode())) {
//            String resubmissionImage = KRADServiceLocator.getKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_return_to_reviewer.gif";
//            addExtraButton("methodToCall.rejectOnlineReview", resubmissionImage, "Return to reviewer");
//        }
//        
//        return extraButtons;
//    }
//    
//
//    /**
//     * This is a utility method to add a new button to the extra buttons
//     * collection.
//     *   
//     * @param property
//     * @param source
//     * @param altText
//     */ 
//    protected void addExtraButton(String property, String source, String altText){
//        
//        ExtraButton newButton = new ExtraButton();
//        
//        newButton.setExtraButtonProperty(property);
//        newButton.setExtraButtonSource(source);
//        newButton.setExtraButtonAltText(altText);
//        
//        extraButtons.add(newButton);
//    }
//
//    @Override
//    public List<ExtraButton> getExtraButtons() {
//        return getExtraActionsButtons();
//    }
//    
//    public boolean getIrbAdminFieldsEditable() {
//        return KraServiceLocator.getService(KraAuthorizationService.class).hasPermission(GlobalVariables.getUserSession().getPrincipalId(), getProtocolOnlineReviewDocument().getProtocolOnlineReview().getProtocol(),PermissionConstants.MAINTAIN_ONLINE_REVIEWS);
//    }
//    
//    public Set<String> getCurrentRouteNodes() {
//        Set<String> nodes;
//        try {
//            nodes = getDocument().getDocumentHeader().getWorkflowDocument().getNodeNames();
//        } catch (Exception e) {
//            LOG.warn(String.format("Workflow exception thrown while trying to get list of current route nodes. Message:%s",e.getMessage()));
//            nodes = new HashSet<String>();
//        }
//        return nodes;
//    }
//
//    public String getApproveImageName() {
//        //we take the first route node the document is on.
//        Set<String> routeNodes = getCurrentRouteNodes();
//        String routeNodeName = routeNodes.size() == 0? null : routeNodes.iterator().next();
//        if (routeNodeName!=null) {
//            return ONLINE_REVIEW_APPROVE_BUTTON_MAP.get(routeNodeName)!=null?ONLINE_REVIEW_APPROVE_BUTTON_MAP.get(routeNodeName):DEFAULT_APPROVE_BUTTON;
//        } else {
//            return DEFAULT_APPROVE_BUTTON;
//        }
//    }
//    
    
}
