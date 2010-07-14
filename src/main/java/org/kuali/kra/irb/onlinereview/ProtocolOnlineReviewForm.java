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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.common.customattributes.CustomDataForm;
import org.kuali.kra.common.customattributes.CustomDataHelperBase;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsForm;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolOnlineReviewForm extends KraTransactionalDocumentFormBase implements PermissionsForm, CustomDataForm, Auditable  {
    
    private static final long serialVersionUID = -7633960906991275328L;
    
    ProtocolOnlineReviewDocument document;
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolOnlineReviewForm.class);
    
    public ProtocolOnlineReviewForm() throws Exception {
        super();
        this.setDocument(new ProtocolOnlineReviewDocument());
        initialize();
        this.registerEditableProperty("methodToCall");
    }

    /**
     *
     * This method initialize all form variables
     * @throws Exception 
     */
    public void initialize() throws Exception {
        initializeHeaderNavigationTabs(); 
        
    }
    
    /**
     * Gets a {@link ProtocolDocument ProtocolDocument}.
     * @return {@link ProtocolDocument ProtocolDocument}
     */
    @Override
    public ProtocolOnlineReviewDocument getDocument() {
        return (ProtocolOnlineReviewDocument) super.getDocument();
    }

    /**
     * 
     * This method initializes the loads the header navigation tabs.
     */
    protected void initializeHeaderNavigationTabs(){
        DataDictionaryService dataDictionaryService = getDataDictionaryService();
        DocumentEntry docEntry = dataDictionaryService.getDataDictionary()
                                    .getDocumentEntry(org.kuali.kra.irb.ProtocolOnlineReviewDocument.class.getName());
        List<HeaderNavigation> navList = docEntry.getHeaderNavigationList();
        HeaderNavigation[] list = new HeaderNavigation[navList.size()];
        navList.toArray(list);
        super.setHeaderNavigationTabs(list); 
    }
    
    /*
     * Override of the set document so we can populate this form
     * with doucment information outside of a request.
     */
    @Override
    public void setDocument(Document document) {
        super.setDocument(document);
        try {
            this.setDocTypeName(document.getDocumentHeader().getWorkflowDocument().getDocumentType());
        } catch (RuntimeException re) {
            //this can happen when the form is initialized with an empty protocol without a header.
            if (!StringUtils.equals("transient workflowDocument is null - this should never happen", re.getMessage())) {
                LOG.error("Unexpected runtime exception caught setting ProtocolOnlineReviewDocument",re);
            }
        }
    }
    
    /**
     * 
     * This method is a wrapper method for getting DataDictionary Service using the Service Locator.
     * @return
     */
    protected DataDictionaryService getDataDictionaryService(){
        return (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
    }

    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
    }
    
    @Override
    public void populateHeaderFields(KualiWorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
    }

    /* Reset method
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
    }
    
    
    protected void setSaveDocumentControl(Map editMode) {
      
    }
    
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_PROTOCOL;
    }
    
    public String getActionName() {
        return "protocol";
    }

    /**
     * @see org.kuali.kra.web.struts.form.SpecialReviewFormBase#getResearchDocument()
     */
    public ResearchDocumentBase getResearchDocument() {
        return this.getDocument();
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

}
