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
package org.kuali.kra.subaward.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
* This class represents closeout type.
*  Here Close out description and corresponding type codes are getting.
 */
public class CloseoutType extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Integer closeoutTypeCode;

    private String description;


    public CloseoutType() {
    }

 /**.
	 * This is the Getter Method for closeoutTypeCode
	 * @return Returns the closeoutTypeCode.
	 */
	public Integer getCloseoutTypeCode() {
		return closeoutTypeCode;
	}

	/**.
	 * This is the Setter Method for closeoutTypeCode
	 * @param closeoutTypeCode The closeoutTypeCode to set.
	 */
	public void setCloseoutTypeCode(Integer closeoutTypeCode) {
		this.closeoutTypeCode = closeoutTypeCode;
	}

	/**.
	 * This is the Getter Method for description
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**.
	 * This is the Setter Method for description
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}


}
