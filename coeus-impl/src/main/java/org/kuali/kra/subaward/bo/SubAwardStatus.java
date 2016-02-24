/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
