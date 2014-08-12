/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.customdata;

import org.kuali.coeus.common.framework.custom.CustomDataHelperBase;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The CustomDataHelper is used to manage the Custom Data tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public abstract class ProtocolCustomDataHelperBase<T extends DocumentCustomData> extends CustomDataHelperBase<T> { 
    


    private static final long serialVersionUID = -3821021799847248950L;

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    protected ProtocolFormBase form;
    private transient BusinessObjectService businessObjectService;
    private transient TaskAuthorizationService taskAuthorizationService;


    /**
     * Constructs a CustomDataHelper.
     * @param form the form
     */
    public ProtocolCustomDataHelperBase(ProtocolFormBase form) {
        this.form = form;
    }

    /*
     * Get the ProtocolBase.
     */
    protected ProtocolBase getProtocol() {
        ProtocolDocumentBase document = form.getProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocumentBase in ProtocolFormBase");
        }
        return document.getProtocol();
    }
    
    /**
     * 
     * This method returns true if the custom data tab should be displayed.
     * @return
     */
    public boolean canDisplayCustomDataTab() {
        boolean localCustomData = this.getCustomAttributeGroups().size() > 0;      
        boolean anyProtocolAttr = areThereAnyProtocolCustomAttributes();
        return localCustomData || anyProtocolAttr;        
    }
    
    private boolean areThereAnyProtocolCustomAttributes() {
        Map fieldValues = new HashMap();
        fieldValues.put("DOCUMENT_TYPE_CODE", getDocumentTypeCode());
        fieldValues.put("ACTIVE_FLAG", "Y");
        Collection<CustomAttributeDocument> documents = getBusinessObjectService().findMatching(CustomAttributeDocument.class, fieldValues);
        return documents.size() > 0;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    /**
     * Can the current user modify the custom data values?
     * @return true if can modify the custom data; otherwise false
     */
    public abstract boolean canModifyCustomData();

    protected TaskAuthorizationService getTaskAuthorizationService() {
        if (taskAuthorizationService == null) {
            taskAuthorizationService =  KcServiceLocator.getService(TaskAuthorizationService.class);
        }
        return taskAuthorizationService;
    }
    
    protected String getDocumentTypeCode() {
        return form.getProtocolDocument().getDocumentTypeCode();
    }
    
    public boolean documentNotRouted() {
        WorkflowDocument doc = form.getProtocolDocument().getDocumentHeader().getWorkflowDocument();
        return doc.isSaved() || doc.isInitiated();
    }
    

}
