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
package org.kuali.coeus.sys.impl.auth;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.auth.AuthServicePushService;
import org.kuali.coeus.sys.framework.auth.AuthServicePushStatus;
import org.kuali.coeus.sys.framework.auth.AuthUser;
import org.kuali.coeus.sys.framework.rest.AuthServiceRestUtilService;
import org.kuali.coeus.sys.framework.rest.RestServiceConstants;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.common.assignee.Assignee;
import org.kuali.rice.kim.api.group.GroupService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

@Service("authServicePushService")
public class AuthServicePushServiceImpl implements AuthServicePushService {
	
	private static final Integer NUMBER_OF_USERS_LIMIT = 100000;
	private static final String LIMIT_PARAM = "limit";
	private static final String AUTH_USER_PUSH_USE_DEV_PASSWORD = "auth.user.push.use.dev.password";
	private static final String AUTH_USER_PUSH_DEV_PASSWORD = "auth.user.push.dev.password";
	private static final String USER_ROLE = "user";
	private static final String ADMIN_ROLE = "admin";
	private static final Log LOG = LogFactory.getLog(AuthServicePushServiceImpl.class); 
	
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@Autowired
	@Qualifier("restOperations")
	private RestOperations restOperations;
	
	@Autowired
	@Qualifier("authServiceRestUtilService")
	private AuthServiceRestUtilService authServiceRestUtilService;
	
	@Autowired
	@Qualifier("permissionService")
	private PermissionService permissionService;
	
	@Autowired
	@Qualifier("kualiConfigurationService")
	private ConfigurationService configurationService;
	
	@Autowired
	@Qualifier("groupService")
	private GroupService groupService;

	@Value("#{{'admin', 'kc', 'kr', 'guest'}}")
	private List<String> ignoredUsers = new ArrayList<>();
		
	@Override
	public AuthServicePushStatus pushAllUsers() {
		AuthServicePushStatus status = new AuthServicePushStatus();
		final List<String> admins = getAdminUsers();
		
		List<AuthUser> peopleToSync = getAllKIMPeople().stream()
				.filter(person -> { return !ignoredUsers.contains(person.getPrincipalName()); })
				.map(person -> {
					AuthUser authUser = generateAuthUserFromKimPerson(person);
					authUser.setRole(admins.contains(person.getPrincipalId()) ? ADMIN_ROLE : USER_ROLE);
					return authUser;
				}).collect(Collectors.toList());
		status.setNumberOfUsers(peopleToSync.size());
		Map<String, AuthUser> authPersonMap = getAllAuthServiceUsers().stream().collect(Collectors.toMap(AuthUser::getUsername,
				Function.identity(), (v1, v2) -> v1));
		for (AuthUser person : peopleToSync) {
			AuthUser authServicePerson = authPersonMap.get(person.getUsername());
			try {
				if (person.isActive()) {
					if (authServicePerson == null) {
						addUserToAuthService(person, getOrGenerateUserPassword(person));
						status.addNumberAdded();
					} else if (authServicePerson.equals(person)) {
						status.addNumberSame();
					} else {
						updateUserInAuthService(person, authServicePerson.getId());
						status.addNumberUpdated();
					}
				} else if (authServicePerson != null) {
					removeUserFromAuthService(authServicePerson.getId());
					status.addNumberRemoved();
				}
			} catch (HttpClientErrorException e) {
				status.getErrors().add(person.getUsername() + " - " + e.getMessage() + " -- " + e.getResponseBodyAsString());
				LOG.error("Error pushing user " + person.getUsername() + " to auth service - " + e.getMessage() + " -- " + e.getResponseBodyAsString());
			} catch (Exception e) {
				status.getErrors().add(person.getUsername() + " - " + e.getMessage());
				LOG.error("Error pushing user " + person.getUsername() + " to auth service", e);
			}
		}
		StringWriter infoMsg = new StringWriter();
		infoMsg.append("Auth Service Bulk Push: Users Found: ").append(Integer.valueOf(status.getNumberOfUsers()).toString())
			.append(", Users Added: ").append(Integer.valueOf(status.getNumberAdded()).toString())
			.append(", Users Updated: ").append(Integer.valueOf(status.getNumberUpdated()).toString())
			.append(", Users Deleted: ").append(Integer.valueOf(status.getNumberRemoved()).toString())
			.append(", Users Errored: ").append(Integer.valueOf(status.getErrors().size()).toString());
		LOG.info(infoMsg.toString());
		
		return status;
	}

	protected List<String> getAdminUsers() {
		return getAdminAssignees().stream()
				.map(this::getAdminUsersFrom)
				.flatMap(l -> l.stream())
				.collect(Collectors.toList());
	}

	protected List<Assignee> getAdminAssignees() {
		return permissionService.getPermissionAssignees(KimConstants.NAMESPACE_CODE, KimConstants.PermissionNames.MODIFY_ENTITY, Collections.emptyMap());
	}
	
	protected List<String> getAdminUsersFrom(Assignee assignee) {
		if (StringUtils.isNotBlank(assignee.getPrincipalId())) {
			return Collections.singletonList(assignee.getPrincipalId());
		} else if (StringUtils.isNotBlank(assignee.getGroupId())) {
			return getGroupMembers(assignee);
		} else {
			return Collections.emptyList();
		}
	}

	protected List<String> getGroupMembers(Assignee assignee) {
		return groupService.getMemberPrincipalIds(assignee.getGroupId());
	}

	protected AuthUser generateAuthUserFromKimPerson(Person person) {
		AuthUser kimAuthUser = new AuthUser();
		kimAuthUser.setUsername(person.getPrincipalName());
		kimAuthUser.setSchoolId(person.getPrincipalId());
		kimAuthUser.setEmail(person.getEmailAddress());
		kimAuthUser.setName(person.getName());
		kimAuthUser.setFirstName(person.getFirstName());
		kimAuthUser.setLastName(person.getLastName());
		kimAuthUser.setPhone(person.getPhoneNumber());
		kimAuthUser.setActive(person.isActive());
		kimAuthUser.setRole(USER_ROLE);
		return kimAuthUser;
	}
	
	protected String getOrGenerateUserPassword(AuthUser person) {
		if (useDevPassword()) {
			return getDevPassword();
		} else {
			return UUID.randomUUID().toString();
		}
	}

	protected boolean useDevPassword() {
		return configurationService.getPropertyValueAsBoolean(AUTH_USER_PUSH_USE_DEV_PASSWORD);
	}
	
	protected String getDevPassword() {
		return configurationService.getPropertyValueAsString(AUTH_USER_PUSH_DEV_PASSWORD);
	}
	
	protected List<Person> getAllKIMPeople() {
		return personService.findPeople(Collections.emptyMap());
	}
	
	protected List<AuthUser> getAllAuthServiceUsers() {
		String uri = UriComponentsBuilder.fromHttpUrl(getUsersApiUrl()).queryParam(LIMIT_PARAM, NUMBER_OF_USERS_LIMIT).build().encode().toString();
		ResponseEntity<List<AuthUser>> result = restOperations.exchange(uri, HttpMethod.GET, 
				new HttpEntity<String>(authServiceRestUtilService.getAuthServiceStyleHttpHeadersForUser()), new ParameterizedTypeReference<List<AuthUser>>() { });
		return result.getBody();
	}
	
	protected void addUserToAuthService(AuthUser newUser, String userPassword) {
		newUser.setPassword(userPassword);
		ResponseEntity<String> result = restOperations.exchange(getUsersApiUrl(), HttpMethod.POST, 
				new HttpEntity<AuthUser>(newUser, authServiceRestUtilService.getAuthServiceStyleHttpHeadersForUser()), String.class);
		if (result.getStatusCode() != HttpStatus.CREATED) {
			throw new RestClientException(result.getBody());
		}
	}
	
	protected void updateUserInAuthService(AuthUser updatedUser, String userId) {
		ResponseEntity<String> result = restOperations.exchange(getUsersApiUrl() + userId, HttpMethod.PUT, 
				new HttpEntity<AuthUser>(updatedUser, authServiceRestUtilService.getAuthServiceStyleHttpHeadersForUser()), String.class);
		if (result.getStatusCode() != HttpStatus.OK) {
			throw new RestClientException(result.getBody());
		}
	}
	
	protected void removeUserFromAuthService(String userId) {
		ResponseEntity<String> result = restOperations.exchange(getUsersApiUrl() + userId, HttpMethod.DELETE, 
				new HttpEntity<AuthUser>(authServiceRestUtilService.getAuthServiceStyleHttpHeadersForUser()), String.class);
		if (result.getStatusCode() != HttpStatus.NO_CONTENT) {
			throw new RestClientException(result.getBody());
		}
	}
	
	protected String getUsersApiUrl() {
		return configurationService.getPropertyValueAsString(RestServiceConstants.Configuration.AUTH_USERS_URL) + "/";
	}

	public AuthServiceRestUtilService getAuthServiceRestUtilService() {
		return authServiceRestUtilService;
	}

	public void setAuthServiceRestUtilService(AuthServiceRestUtilService authServiceRestUtilService) {
		this.authServiceRestUtilService = authServiceRestUtilService;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public RestOperations getRestOperations() {
		return restOperations;
	}

	public void setRestOperations(RestOperations restOperations) {
		this.restOperations = restOperations;
	}

	public List<String> getIgnoredUsers() {
		return ignoredUsers;
	}

	public void setIgnoredUsers(List<String> ignoredUsers) {
		this.ignoredUsers = ignoredUsers;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

}
