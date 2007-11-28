/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.bo;

public class YnqGroupName {
    private String groupName;
    private String truncGroupName;
    private int groupNameMaxLength = 57;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
        /* truncate group name to display in tab */
        if(groupName.length() > groupNameMaxLength) {
            this.truncGroupName = groupName.substring(0, groupNameMaxLength).concat("...");
        }else {
            this.truncGroupName = groupName;
        }
    }

    public String getTruncGroupName() {
        return truncGroupName;
    }

}
