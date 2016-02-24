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
