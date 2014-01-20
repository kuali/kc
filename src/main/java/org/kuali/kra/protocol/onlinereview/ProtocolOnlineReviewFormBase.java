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
package org.kuali.kra.protocol.onlinereview;

import org.apache.commons.logging.Log;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.customattributes.CustomDataHelperBase;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsForm;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.document.Document;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public abstract class ProtocolOnlineReviewFormBase extends KraTransactionalDocumentFormBase implements PermissionsForm, Auditable  {
    
    private static final long serialVersionUID = -7633960906991275328L;
    
    private static final String DEFAULT_APPROVE_BUTTON = "buttonsmall_approve_this_review.gif";
    
    
    public ProtocolOnlineReviewFormBase() throws Exception {
        super();
        this.registerEditableProperty("methodToCall");
    }

    /**
     * Gets a {@link ProtocolDocumentBase ProtocolDocumentBase}.
     * @return {@link ProtocolDocumentBase ProtocolDocumentBase}
     */
    public ProtocolOnlineReviewDocumentBase getProtocolOnlineReviewDocument() {
        return (ProtocolOnlineReviewDocumentBase) super.getDocument();
    }

    
    protected abstract String getDefaultDocumentTypeName(); 
    
    /*
     * Override of the set document so we can populate this form
     * with doucment information outside of a request.
     */
    @Override
    public void setDocument(Document document) {
        super.setDocument(document);
        this.setDocTypeName(getDefaultDocumentTypeName());
    }

    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
    }
    
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
    }

    /* Reset method
     * @param mapping
     * @param request
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
    }
    
    
    @Override
    protected void setSaveDocumentControl(Map editMode) {
      
    }
    
    
    protected abstract String getLockRegion();
    
    @Override
    public String getActionName() {
        return "protocol";
    }

    /**
     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getResearchDocument()
     */
    public ResearchDocumentBase getResearchDocument() {
        return (ResearchDocumentBase) this.getDocument();
    }

    public PermissionsHelperBase getPermissionsHelper() {
        // TODO Auto-generated method stub
        return null;
    }

    public CustomDataHelperBase getCustomDataHelper() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isAuditActivated() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setAuditActivated(boolean auditActivated) {
        // TODO Auto-generated method stub
        
    }


    public abstract List<ExtraButton> getExtraActionsButtons();
    
    /**
     * This is a utility method to add a new button to the extra buttons
     * collection.
     *   
     * @param property
     * @param source
     * @param altText
     */ 
    @SuppressWarnings("deprecation")
    protected void addExtraButton(String property, String source, String altText){
        
        ExtraButton newButton = new ExtraButton();
        
        newButton.setExtraButtonProperty(property);
        newButton.setExtraButtonSource(source);
        newButton.setExtraButtonAltText(altText);
        
        extraButtons.add(newButton);
    }

    @Override
    public List<ExtraButton> getExtraButtons() {
        return getExtraActionsButtons();
    }
    
    
    public abstract boolean getAdminFieldsEditable();

    public Set<String> getCurrentRouteNodes() {
        Set<String> nodes;
        try {
            nodes = getDocument().getDocumentHeader().getWorkflowDocument().getNodeNames();
        } catch (Exception e) {
            getLogHook().warn(String.format("Workflow exception thrown while trying to get list of current route nodes. Message:%s",e.getMessage()));
            nodes = new HashSet<String>();
        }
        return nodes;
    }

    protected abstract Log getLogHook();

    public String getApproveImageName() {
        //we take the first route node the document is on.
        Set<String> routeNodes = getCurrentRouteNodes();
        String routeNodeName = routeNodes.size() == 0? null : routeNodes.iterator().next();
        if (routeNodeName!=null) {
            return getOnlineReviewApproveButtonMapHook().get(routeNodeName)!=null? getOnlineReviewApproveButtonMapHook().get(routeNodeName):DEFAULT_APPROVE_BUTTON;
        } else {
            return DEFAULT_APPROVE_BUTTON;
        }
    }

    protected abstract Map<String, String> getOnlineReviewApproveButtonMapHook();
    
    
}
