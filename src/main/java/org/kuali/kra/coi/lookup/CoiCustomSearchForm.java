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
package org.kuali.kra.coi.lookup;

import org.kuali.rice.kns.web.struts.form.KualiForm;

public class CoiCustomSearchForm extends KualiForm {

    private static final long serialVersionUID = 8609723442875002645L;
    
    private CustomAdminSearchHelper customAdminSearchHelper;
    private boolean canQuickApprove;
    
    public CoiCustomSearchForm() {
        customAdminSearchHelper = new CustomAdminSearchHelper();
    }

    public CustomAdminSearchHelper getCustomAdminSearchHelper() {
        return customAdminSearchHelper;
    }

    public void setCustomAdminSearchHelper(CustomAdminSearchHelper customAdminSearchHelper) {
        this.customAdminSearchHelper = customAdminSearchHelper;
    }

    public boolean isCanQuickApprove() {
        return canQuickApprove;
    }

    public void setCanQuickApprove(boolean canQuickApprove) {
        this.canQuickApprove = canQuickApprove;
    }
}
