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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@SuppressWarnings("serial")
public class ExpeditedReviewCheckListItem extends KraPersistableBusinessObjectBase {

    /**
     * The maximum length of an abbreviated check list description.
     */
    public static final int ABBREV_LENGTH = 250;
    
    private String expeditedReviewCheckListCode;
    private String description;
    private transient boolean checked = false;
    
    public ExpeditedReviewCheckListItem() {
        
    }

    public String getExpeditedReviewCheckListCode() {
        return expeditedReviewCheckListCode;
    }

    public void setExpeditedReviewCheckListCode(String expeditedReviewCheckListCode) {
        this.expeditedReviewCheckListCode = expeditedReviewCheckListCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * This is only used by JSP to obtain an abbreviated check list description.
     * For descriptions are much too long and we can't display the entire text.
     * @return the abbreviated description
     */
    public String getAbbrevDescription() {
        if (description.length() < ABBREV_LENGTH) {
            return description;
        }
        else {
            return description.substring(0, ABBREV_LENGTH) + "...";
        }
    }
    
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    public boolean getChecked() {
        return checked;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("expeditedReviewCheckListCode", getExpeditedReviewCheckListCode());
        map.put("description", getDescription());
        map.put("checked", getChecked());
        return map;
    }
}
