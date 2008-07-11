/*
 * Copyright 2006-2007 The Kuali Foundation.
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
package org.kuali.kra.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

/**
 * Class representation of the Degree Type Business Object
 *
 * $Id: Parameter.java,v 1.1 2008-07-11 16:15:26 rmancher Exp $
 */
public class Parameter extends KraPersistableBusinessObjectBase {
	
	private String parameter;
    private Date effectiveDate;
	private String value;
	


	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("parameter", this.getParameter());
		propMap.put("effectiveDate", this.getEffectiveDate());
		propMap.put("value", this.getValue());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}



    public final String getParameter() {
        return parameter;
    }



    public final void setParameter(String parameter) {
        this.parameter = parameter;
    }



    public final Date getEffectiveDate() {
        return effectiveDate;
    }



    public final void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }



    public final String getValue() {
        return value;
    }



    public final void setValue(String value) {
        this.value = value;
    }
}
