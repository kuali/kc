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
package org.kuali.kra.authorization;

import org.kuali.rice.kew.api.document.Document;
import org.kuali.rice.kew.framework.document.security.DocumentSecurityAttribute;

public class KcNotificationSecurityAttribute implements DocumentSecurityAttribute {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3547172238972658576L;

    @Override
    public boolean isAuthorizedForDocument(String principalId, Document document) {
        // always return false, if this method is called back from rice, this means that the current user did not have
        // the unrestricted search permission and therefore must not see any notifications.
        return false;
    }
}