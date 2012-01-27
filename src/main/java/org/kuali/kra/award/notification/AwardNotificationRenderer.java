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
package org.kuali.kra.award.notification;

import java.util.Map;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.common.notification.NotificationRendererBase;

/**
 * Renders fields for the Award notifications.
 */
public class AwardNotificationRenderer extends NotificationRendererBase {

    private static final long serialVersionUID = -5066268431930093815L;
    
    private Award award;
    
    /**
     * Constructs an Award notification renderer.
     * @param institutionalProposal
     */
    public AwardNotificationRenderer(Award award) {
        this.award = award;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.common.notification.NotificationRenderer#getReplacementParameters()
     */
    public Map<String, String> getDefaultReplacementParameters() {
        return super.getDefaultReplacementParameters();
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }
    
}