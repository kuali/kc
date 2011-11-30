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

import org.kuali.kra.common.notification.service.KcNotificationModuleRoleService;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.common.notification.web.struts.form.NotificationHelperBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.negotiations.web.struts.form.NegotiationForm;

public class NegotiationNotificationHelper extends NotificationHelperBase<NegotiationCloseNotificationContext> {

    private static final long serialVersionUID = -4964310143806237546L;
    private NegotiationForm negotiationForm;
    
    public NegotiationNotificationHelper(NegotiationForm form) {
        this.negotiationForm = form;
    }
    
    @Override
    public NegotiationCloseNotificationContext getContext() {
        return new NegotiationCloseNotificationContext(negotiationForm.getNegotiationDocument());
    }

}
