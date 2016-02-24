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
package org.kuali.coeus.propdev.impl.person;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.attr.CitizenshipType;

public class CitizenshipTypeServiceTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testGetPersonCitizenshipTypeExternal() {
		ProposalPerson person = new ProposalPerson();
		CitizenshipTypeServiceImpl service = new CitizenshipTypeServiceImpl() {
			@Override 
			protected Boolean isCitizenshipTypeSourceInternal() {
				return false;
			}
		};
		service.getPersonCitizenshipType(person);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetPersonCitizenshipNullPerson() {
		CitizenshipTypeServiceImpl service = new CitizenshipTypeServiceImpl();
		service.getPersonCitizenshipType(null);
	}
	
	@Test
	public void testGetPersonCitizenshipTypeFromProposal() {
		CitizenshipTypeServiceImpl service = new CitizenshipTypeServiceImpl() {
			@Override 
			protected Boolean isCitizenshipTypeSourceInternal() {
				return true;
			}
			@Override
			protected Boolean isAllowCitizenshipTypeOverride() {
				return true;
			}
			@Override
			protected org.kuali.coeus.common.api.person.attr.CitizenshipType getCitizenshipTypeFromCode(String citizenShipCode) {
				if (StringUtils.equals(citizenShipCode, "1")) {
					return org.kuali.coeus.common.api.person.attr.CitizenshipType.PERMANENT_RESIDENT_OF_US;
				} else {
					return null;
				}
			}
		};
		CitizenshipType newType = new CitizenshipType();
		newType.setCode(1);
		ProposalPerson person = new ProposalPerson();
		person.setCitizenshipType(newType);
		assertEquals(org.kuali.coeus.common.api.person.attr.CitizenshipType.PERMANENT_RESIDENT_OF_US, service.getPersonCitizenshipType(person));
	}
	
	@Test
	public void testGetPersonCitizenshipTypeFromPerson() {
		CitizenshipTypeServiceImpl service = new CitizenshipTypeServiceImpl() {
			@Override 
			protected Boolean isCitizenshipTypeSourceInternal() {
				return true;
			}
			@Override
			protected Boolean isAllowCitizenshipTypeOverride() {
				return false;
			}
			@Override
			protected org.kuali.coeus.common.api.person.attr.CitizenshipType getCitizenshipTypeFromCode(String citizenShipCode) {
				if (StringUtils.equals(citizenShipCode, "1")) {
					return org.kuali.coeus.common.api.person.attr.CitizenshipType.PERMANENT_RESIDENT_OF_US;
				} else {
					return null;
				}
			}
		};
		CitizenshipType newType = new CitizenshipType();
		newType.setCode(2);
		ProposalPerson person = new ProposalPerson() {
			@Override
			public KcPerson getPerson() {
				return new KcPerson() {
					@Override
					public Integer getCitizenshipTypeCode() {
						return 1;
					}
				};
			}
		};
		person.setCitizenshipType(newType);
		assertEquals(org.kuali.coeus.common.api.person.attr.CitizenshipType.PERMANENT_RESIDENT_OF_US, service.getPersonCitizenshipType(person));
	}
	
}
