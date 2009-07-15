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
package org.kuali.kra.irb.actions.delete;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * The ProtocolDeleteService implementation.
 */
public class ProtocolDeleteServiceImpl implements ProtocolDeleteService {

    private BusinessObjectService businessObjectService;

    /**
     * Set the business object service.
     * @param businessObjectService the business object service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * We never delete documents from the database.  Rather, we simply mark
     * it as deleted.
     * @see org.kuali.kra.irb.actions.delete.ProtocolDeleteService#delete(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.actions.delete.ProtocolDeleteBean)
     */
    public void delete(Protocol protocol, ProtocolDeleteBean deleteBean) {
        protocol.setProtocolStatusCode(ProtocolStatus.DELETED);
        businessObjectService.save(protocol.getProtocolDocument());
    }
}
