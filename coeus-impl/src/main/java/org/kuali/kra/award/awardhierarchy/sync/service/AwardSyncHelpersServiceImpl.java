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
package org.kuali.kra.award.awardhierarchy.sync.service;

import org.kuali.kra.award.awardhierarchy.sync.helpers.AwardSyncHelper;

import java.util.Map;

public class AwardSyncHelpersServiceImpl implements AwardSyncHelpersService {

    private Map<String, AwardSyncHelper> syncHelpers;
    
    public AwardSyncHelper getSyncHelper(String className) {
        if (className.contains(".")) {
            return getSyncHelpers().get(className.substring(className.lastIndexOf(".")+1));
        } else { 
            return getSyncHelpers().get(className);
        }
    }

    protected Map<String, AwardSyncHelper> getSyncHelpers() {
        return syncHelpers;
    }

    public void setSyncHelpers(Map<String, AwardSyncHelper> syncHelpers) {
        this.syncHelpers = syncHelpers;
    }

}
