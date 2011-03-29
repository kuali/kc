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
package org.kuali.kra.irb.actions.notification;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.w3c.dom.Element;

/**
 * 
 * This class is the event for Assign to agenda reviewer notification.
 */
public class AssignToAgendaEvent  extends NotificationEventBase {

        public AssignToAgendaEvent() {
        }

        public AssignToAgendaEvent(Protocol protocol) {
            super(protocol);
        }

        /**
         * 
         * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getRecipients(org.w3c.dom.Element)
         */
        public void getRecipients(Element recipients) {
            super.getRecipients(recipients);
        }

        /**
         * 
         * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getTitle()
         */
        public String getTitle() {
            return "Protocol assigned to agenda";
        }

        public String getTemplatePath() {
            return "AssignToAgendaReviewerNotification.xsl";
        }

        /**
         * 
         * @see org.kuali.kra.irb.actions.notification.NotificationEventBase#getActionTypeCode()
         */
        @Override
        public String getActionTypeCode() {
            return ProtocolActionType.ASSIGN_TO_AGENDA;
        }

        @Override
        public boolean isReviewerNotification() {
            return true;    
        }

        @Override
        public boolean isReviewerIncluded() {
            return true;    
        }


}
