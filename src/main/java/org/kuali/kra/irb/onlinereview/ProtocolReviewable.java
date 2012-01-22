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
package org.kuali.kra.irb.onlinereview;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class is for review comments and attachments
 */
public abstract class ProtocolReviewable extends KraPersistableBusinessObjectBase {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2401653037114419518L;

    public abstract Long getProtocolId();
    public abstract Long getProtocolOnlineReviewIdFk();
    public abstract String getCreateUser();
    public abstract Protocol getProtocol();
    public abstract void setDisplayReviewerName(boolean displayReviewerName);
    
    public abstract CommitteeSchedule getCommitteeSchedule();
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
            ProtocolOnlineReview protocolOnlineReview = getBusinessObjectService().findBySinglePrimaryKey(ProtocolOnlineReview.class, getProtocolOnlineReviewIdFk());
            if (protocolOnlineReview.isAdminAccepted()) {
                accepted = true;
            }
        } else {
            accepted = true;
        }
        
        return accepted;
    }

    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
}
