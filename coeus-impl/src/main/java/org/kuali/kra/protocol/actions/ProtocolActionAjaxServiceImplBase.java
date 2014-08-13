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
package org.kuali.kra.protocol.actions;

import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerType;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collection;

public abstract class ProtocolActionAjaxServiceImplBase implements ProtocolActionAjaxService {

    private BusinessObjectService businessObjectService;
    private CommitteeServiceBase committeeService;
    

    public abstract String getReviewers(String protocolId, String committeeId, String scheduleId);

    public String getReviewerTypes() {
        StringBuffer ajaxList = new StringBuffer();
        Collection<ProtocolReviewerType> reviewerTypes = getReviewerTypesFromDatabase();
        for (ProtocolReviewerType reviewerType : reviewerTypes) {
            ajaxList.append(reviewerType.getReviewerTypeCode() + ";" + reviewerType.getDescription() + ";");
        }
        return clipLastChar(ajaxList);
    }
    @SuppressWarnings("unchecked")
    protected Collection<ProtocolReviewerType> getReviewerTypesFromDatabase() {
        return businessObjectService.findAll(getProtocolReviewerTypeClassHook());
    }
    
    public abstract Class getProtocolReviewerTypeClassHook();
    
    public abstract Class<? extends ProtocolBase> getProtocolClassHook();
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public CommitteeServiceBase getCommitteeService() {
        return committeeService;
    }
    
    public void setCommitteeService(CommitteeServiceBase committeeService) {
        this.committeeService = committeeService;
    }

    /**
     * Clip the last character from the string buffer. The last character, if there is one, is always a separator that must be
     * removed.
     * 
     * @param ajaxList
     * @return
     */
    protected String clipLastChar(StringBuffer ajaxList) {
        if (ajaxList.length() == 0) {
            return ajaxList.toString();
        }
        return ajaxList.substring(0, ajaxList.length() - 1);
    }
}
