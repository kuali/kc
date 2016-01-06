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
package org.kuali.coeus.award.finance;

import org.kuali.coeus.common.framework.person.KcPersonDto;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorTypeDto;

import com.codiform.moo.annotation.Optionality;
import com.codiform.moo.annotation.Property;

public class AwardUnitContactDto {

	@Property(translate = true)
	private UnitAdministratorTypeDto unitAdministratorType;
	@Property(source = "unitAdministratorUnitNumber")
	private String unitNumber;
	@Property(translate = true, optionality = Optionality.OPTIONAL)
	private KcPersonDto person;
	public UnitAdministratorTypeDto getUnitAdministratorType() {
		return unitAdministratorType;
	}
	public void setUnitAdministratorType(UnitAdministratorTypeDto unitAdministratorType) {
		this.unitAdministratorType = unitAdministratorType;
	}
	public String getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	public KcPersonDto getPerson() {
		return person;
	}
	public void setPerson(KcPersonDto person) {
		this.person = person;
	}
}
