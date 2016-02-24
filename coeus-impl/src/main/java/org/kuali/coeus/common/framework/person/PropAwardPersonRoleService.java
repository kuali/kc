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
package org.kuali.coeus.common.framework.person;

import java.util.Collection;

public interface PropAwardPersonRoleService {

	public static final String DEFAULT_SPONSOR_HIERARCHY_NAME = "DEFAULT";
	
	/**
	 * Gets the PropAwardPersonRole based on the primary identifier
	 * @param id - cannot be null
	 * @return
	 */
	PropAwardPersonRole getRole(Long id);
	
	/**
	 * Gets the PropAwardPersonRole based on the role code and sponsor code. The sponsor code
	 * is used to determine the applicable sponsor hierarchy and the sponsor hierarchy along
	 * with the role code is a unquie key that can identify a specific role entry.
	 * @param roleCode cannot be null
	 * @param sponsorCode cannot be null
	 * @return
	 */
	PropAwardPersonRole getRole(String roleCode, String sponsorCode);
	
	/**
	 * Gets the set of PropAwardPersonRole entries based on the sponsor code and applicable
	 * sponsor hierarchy the sponsor is in
	 * @param sponsorCode
	 * @return
	 */
	Collection<PropAwardPersonRole> getRolesByHierarchy(String sponsorCode);
	
	/**
	 * Checks to see if all sponsors are considered NIH or if the sponsor is in the NIH OSC hierarchy.
	 * @param sponsorCode
	 * @return
	 */
	boolean isNihOtherSignificantContributorEnabledForSponsor(String sponsorCode);
	
	/**
	 * Gets the sponsor hierarchy based on the sponsor code 
	 * @param sponsorCode
	 * @return
	 */
	public String getSponsorHierarchy(String sponsorCode);
	
}
