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
