package org.kuali.coeus.common.framework.person;

import java.util.Collection;

public interface PropAwardPersonRoleService {

	public static final String DEFAULT_SPONSOR_HIERARCHY_NAME = "DEFAULT";
	
	PropAwardPersonRole getRole(Long id);
	
	PropAwardPersonRole getRole(String roleCode, String sponsorCode);
	
	Collection<PropAwardPersonRole> getRolesByHierarchy(String sponsorCode);
	
}
