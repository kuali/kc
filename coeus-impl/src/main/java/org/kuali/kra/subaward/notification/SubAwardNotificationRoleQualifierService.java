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
package org.kuali.kra.subaward.notification;

import org.kuali.coeus.common.notification.impl.service.KcNotificationRoleQualifierService;
import org.kuali.kra.subaward.bo.SubAward;

/**
 * Defines the service to fill in module role qualifier information for Award.
 */
public interface SubAwardNotificationRoleQualifierService extends KcNotificationRoleQualifierService {

    /**
     * @return the Award
     */
    SubAward getSubAward();
    
    /**
     * @param award the SubAward to set
     */
    void setSubAward(SubAward subAward);
    
}