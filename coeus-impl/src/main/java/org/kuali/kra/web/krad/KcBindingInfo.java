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
package org.kuali.kra.web.krad;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.krad.uif.component.BindingInfo;

public class KcBindingInfo extends BindingInfo {

    public static String getParentBindingInfo(BindingInfo bindingInfo) {
        String formedBindingPath = "";

        if (!bindingInfo.isBindToForm() && StringUtils.isNotBlank(bindingInfo.getBindingObjectPath())) {
            formedBindingPath = bindingInfo.getBindingObjectPath();
        }

        if (StringUtils.isNotBlank(bindingInfo.getBindByNamePrefix())) {
            if (!bindingInfo.getBindByNamePrefix().startsWith("[") && StringUtils.isNotBlank(formedBindingPath)) {
                formedBindingPath += ".";
            }
            formedBindingPath += bindingInfo.getBindByNamePrefix();
        }

        return formedBindingPath;
    }
}
