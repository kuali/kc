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
package org.kuali.kra.irb.actions.history;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.BusinessObjectBase;

@SuppressWarnings("serial")
public class DateRangeFilter extends BusinessObjectBase {
    
    private Date beginningOn;
    private Date endingOn;

    public Date getBeginningOn() {
        return beginningOn;
    }

    public void setBeginningOn(Date beginningOn) {
        this.beginningOn = beginningOn;
    }

    public Date getEndingOn() {
        return endingOn;
    }

    public void setEndingOn(Date endingOn) {
        this.endingOn = endingOn;
    }
    
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String,Object>();
        map.put("beginningOn", getBeginningOn());
        map.put("endingOn", getEndingOn());
        return map;
    }

    public void refresh() {
        // do nothing
    }
}
