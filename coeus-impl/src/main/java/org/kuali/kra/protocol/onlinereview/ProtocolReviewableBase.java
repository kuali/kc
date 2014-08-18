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
package org.kuali.kra.protocol.onlinereview;

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class is for review comments and attachments
 */
public abstract class ProtocolReviewableBase<CS extends CommitteeScheduleBase<CS, ?, ?, ?>> extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 2401653037114419518L;

    public abstract Long getProtocolId();
    public abstract Long getProtocolOnlineReviewIdFk();
    public abstract String getCreateUser();
    public abstract ProtocolBase getProtocol();
    public abstract void setDisplayReviewerName(boolean displayReviewerName);
    
    public abstract CS getCommitteeSchedule();
    /**
     * 
     * This method is to show whether comment or attachment is private or not
     * @return
     */
    public abstract boolean isPrivate();
    /**
     * 
     * This method indicates if the OLR is finalized or not
     * review comment has a finalflag, but review attachment does not
     * @return
     */
    public abstract boolean isFinal();


    /**
     * 
     * This method to indicate this instance is ReviewComment - CommitteeScheduleMinute
     * @return
     */
    public boolean isReviewComment() {
        return false;
    }

    /**
     * 
     * This method to show whether the OLR is accepted by IRB admin.
     * @return
     */
    public boolean isAccepted() {
        boolean accepted = false;
        if (getProtocolOnlineReviewIdFk() != null) {
            ProtocolOnlineReviewBase protocolOnlineReview = getBusinessObjectService().findBySinglePrimaryKey(getProtocolOnlineReviewBOClassHook(), getProtocolOnlineReviewIdFk());
            if (protocolOnlineReview.isAdminAccepted()) {
                accepted = true;
            }
        } else {
            accepted = true;
        }
        
        return accepted;
    }

    protected abstract Class<? extends ProtocolOnlineReviewBase> getProtocolOnlineReviewBOClassHook();
    
    
    private BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
}
