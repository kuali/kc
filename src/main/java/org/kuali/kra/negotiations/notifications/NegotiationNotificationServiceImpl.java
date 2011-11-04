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
package org.kuali.kra.negotiations.notifications;

import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.service.NegotiationService;

/**
 * Negotiation Notification Service.
 */
public class NegotiationNotificationServiceImpl implements NegotiationNotificationService {
    
    private KcNotificationService kcNotificationService;
    private NegotiationService negotiationService;

    @Override
    public void sendCloseNotification(NegotiationDocument document) {
        Negotiable negotiableBo = document.getNegotiation().getAssociatedDocument();
        NegotiationCloseNotificationContext context = new NegotiationCloseNotificationContext(document, negotiableBo);
        getKcNotificationService().sendNotification(context);
    }

    public KcNotificationService getKcNotificationService() {
        return kcNotificationService;
    }

    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }

    public NegotiationService getNegotiationService() {
        return negotiationService;
    }

    public void setNegotiationService(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }

}
