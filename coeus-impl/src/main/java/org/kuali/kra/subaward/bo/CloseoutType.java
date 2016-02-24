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
