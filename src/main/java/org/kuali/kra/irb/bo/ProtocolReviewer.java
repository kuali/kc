/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.bo;

import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.BusinessObjectBase;

@SuppressWarnings("serial")
public class ProtocolReviewer extends BusinessObjectBase {

    private String fullName;
    private Boolean checked;
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public Boolean getChecked() {
        return checked;
    }
    
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
    }

    public void refresh() {
        // TODO Auto-generated method stub   
    }
}
