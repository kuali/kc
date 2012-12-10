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
package org.kuali.kra.irb.customdata;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelperBase;

/**
 * The CustomDataHelper is used to manage the Custom Data tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class CustomDataHelper extends ProtocolCustomDataHelperBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3956588282238741445L;
    
// TODO ********************** commented out during IRB backfit ************************    
//    /**
//     * Each Helper must contain a reference to its document form
//     * so that it can access the document.
//     */
//    private ProtocolForm form;
    
    /**
     * Constructs a CustomDataHelper.
     * @param form the form
     */
    public CustomDataHelper(ProtocolForm form) {
        super(form);
    }
    
// TODO ********************** commented out during IRB backfit ************************    
//    /*
//     * Get the Protocol.
//     */
//    private Protocol getProtocol() {
//        ProtocolDocument document = (ProtocolDocument) form.getProtocolDocument();
//        if (document == null || document.getProtocol() == null) {
//            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
//        }
//        return (Protocol) document.getProtocol();
//    }
//    
    /**
     * @see org.kuali.kra.common.customattributes.CustomDataHelperBase#canModifyCustomData()
     */
    @Override
    public boolean canModifyCustomData() {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_OTHERS, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);    
    }

    @Override
    protected String getDocumentTypeCode() {
        return ProtocolDocument.DOCUMENT_TYPE_CODE;
    }

    @Override
    public void prepareView(ProtocolBase protocol) {
        // TODO Auto-generated method stub (temporary for backfit)  This will have to be added after merging back from trunk, its quite possible that we can elevate the IACUC implementation of 
        // this method to the parent class, requiring only hooks to be implemented. 
        
    }

    @Override
    public ActionForward getCustomDataAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        // TODO Auto-generated method stub (temporary for backfit) This will have to be added after merging back from trunk, its quite possible that we can elevate the IACUC implementation of 
        // this method to the parent class, requiring only hooks to be implemented. 
        return null;
    }
    
// TODO ********************** commented out during IRB backfit ************************    
//    /**
//     * 
//     * This method returns true if the custom data tab should be displayed.
//     * @return
//     */
//    public boolean canDisplayCustomDataTab() {
//        boolean localCustomData = this.getCustomAttributeGroups().size() > 0;      
//        boolean anyProtocolAttr = areThereAnyProtocolCustomAttributes();
//        return localCustomData || anyProtocolAttr;        
//    }
//    
//    private boolean areThereAnyProtocolCustomAttributes() {
//        Map fieldValues = new HashMap();
//        fieldValues.put("DOCUMENT_TYPE_CODE", "PROT");
//        fieldValues.put("ACTIVE_FLAG", "Y");
//        Collection<CustomAttributeDocument> documents = getBusinessObjectService().findMatching(CustomAttributeDocument.class, fieldValues);
//        return documents.size() > 0;
//    }
//    
//    
//    private BusinessObjectService getBusinessObjectService() {
//        return KraServiceLocator.getService(BusinessObjectService.class);
//    }
    
}
