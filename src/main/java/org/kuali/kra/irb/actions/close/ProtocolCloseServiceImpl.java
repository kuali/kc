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
package org.kuali.kra.irb.actions.close;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.actions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;

/**
 * The ProtocolExpediteApprovalService implementation.
 */
public class ProtocolCloseServiceImpl implements ProtocolCloseService {

    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private ProtocolActionService protocolActionService;
    private ProtocolVersionService protocolVersionService;

    /**
     * Set the document service.
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Set the Protocol Action Service.
     * @param protocolActionService
     */
    public void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }
    
    /**
     * Inject Protocol Version Service
     * @param protocolVersionService
     */
    public void setProtocolVersionService(ProtocolVersionService protocolVersionService) {
        this.protocolVersionService = protocolVersionService;
    }

   
    public void close(Protocol protocol, ProtocolGenericActionBean actionBean) throws Exception {
        // TODO Auto-generated method stub
        
    }
}
