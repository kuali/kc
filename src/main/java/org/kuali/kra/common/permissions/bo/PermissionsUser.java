/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.common.permissions.bo;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.bo.BusinessObjectBase;

/**
 * A <b>PermissionsUser</b> is used for the form on the Permissions tab
 * web page for entering a new user.  When a user's userName and role are
 * entered on the web page, the data is written to an instance of this
 * class.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@SuppressWarnings("serial")
public class PermissionsUser extends BusinessObjectBase {
    
    private String userName = "";
    private String roleName = "";

    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    @SuppressWarnings("unchecked")
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("userName", getUserName());
        map.put("roleName", getRoleName());
        return map;
    }

    /**
     * @see org.kuali.core.bo.BusinessObject#refresh()
     */
    public void refresh() {
        // do nothing
    }
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!obj.getClass().equals(this.getClass())) return false;
        
        PermissionsUser user = (PermissionsUser) obj;
        return StringUtils.equals(this.userName, user.userName) &&
               StringUtils.equals(this.roleName, user.roleName);
    }
}
