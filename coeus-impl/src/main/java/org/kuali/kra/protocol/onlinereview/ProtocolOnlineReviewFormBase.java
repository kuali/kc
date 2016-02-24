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
package org.kuali.kra.protocol.onlinereview;

import org.apache.commons.logging.Log;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.custom.CustomDataHelperBase;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsForm;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase;
import org.kuali.coeus.sys.framework.validation.Auditable;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.krad.document.Document;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class ProtocolOnlineReviewFormBase extends KcTransactionalDocumentFormBase implements PermissionsForm, Auditable {
    
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

    public KcTransactionalDocumentBase getResearchDocument() {
        return (KcTransactionalDocumentBase) this.getDocument();
    }

    public PermissionsHelperBase getPermissionsHelper() {

        return null;
    }

    public CustomDataHelperBase getCustomDataHelper() {

        return null;
    }

    public boolean isAuditActivated() {

        return false;
    }

    public void setAuditActivated(boolean auditActivated) {

        
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
