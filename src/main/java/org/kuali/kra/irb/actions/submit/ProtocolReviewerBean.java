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
package org.kuali.kra.irb.actions.submit;

import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.BusinessObjectBase;

/**
 * This class is really just a "form" for the reviewers that
 * are displayed to the user in the Submit for Review Action.
 * It is only displayed from BusinessObject in order to use
 * the Data Dictionary for displaying controls on the web page.
 */
@SuppressWarnings("serial")
public class ProtocolReviewerBean extends BusinessObjectBase {

    private String personId;
    private String fullName;
    private boolean checked = false;
    private String reviewerTypeCode;
    private boolean nonEmployeeFlag;
    
    public String getPersonId() {
        return personId;
    }
    
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public boolean getChecked() {
        return checked;
    }
    
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    public String getReviewerTypeCode() {
        return reviewerTypeCode;
    }
    
    public void setReviewerTypeCode(String reviewerTypeCode) {
        this.reviewerTypeCode = reviewerTypeCode;
    }
    
    public boolean getNonEmployeeFlag() {
        return nonEmployeeFlag;
    }
    
    public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("personId", getPersonId());
        map.put("fullName", getFullName());
        map.put("checked", getChecked());
        map.put("reviewTypeCode", getReviewerTypeCode());
        map.put("nonEmployeeFlag", getNonEmployeeFlag());
        return map;
    }

    public void refresh() {
       
    }
}
