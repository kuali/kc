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

public class SubAwardStatus extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Integer subAwardStatusCode;

    private String description;


    public SubAwardStatus() {
    }

    /**.
    * This is the Getter Method for subAwardStatusCode
    * @return Returns the subAwardStatusCode.
    */
  public Integer getSubAwardStatusCode() {
   return subAwardStatusCode;
   }

	/**.
	 * This is the Setter Method for subAwardStatusCode
	 * @param subAwardStatusCode The subAwardStatusCode to set.
	 */
	public void setSubAwardStatusCode(Integer subAwardStatusCode) {
		this.subAwardStatusCode = subAwardStatusCode;
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
