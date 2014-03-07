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
package org.kuali.kra.iacuc.authorizer;

import org.kuali.coeus.sys.framework.auth.task.Task;
import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizerBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;

public class CreateIacucProtocolAuthorizer extends TaskAuthorizerBase {

    /**
     * @see org.kuali.coeus.sys.framework.auth.task.TaskAuthorizer#isAuthorized(java.lang.String, org.kuali.coeus.sys.framework.auth.task.Task)
     */
    public boolean isAuthorized(String userId, Task task) {
        return hasUnitPermission(userId, Constants.MODULE_NAMESPACE_IACUC, PermissionConstants.CREATE_IACUC_PROTOCOL);
    }

}
