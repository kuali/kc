package org.kuali.coeus.sys.impl.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.auth.AuthServicePushService;
import org.kuali.coeus.sys.framework.auth.AuthServicePushStatus;
import org.kuali.coeus.sys.framework.auth.AuthUser;
import org.kuali.coeus.sys.framework.rest.AuthServiceRestUtilService;
import org.kuali.coeus.sys.framework.rest.RestServiceConstants;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kim.api.KimConstants;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

@Service("authServicePushService")
public class AuthServicePushServiceImpl implements AuthServicePushService {
	
	private static final String AUTH_USER_PUSH_USERNAME_AS_PASSWORD = "auth.user.push.username.as.password";
	private static final RestServiceConstants.RestApiVersions AUTH_USER_API_VERSION = RestServiceConstants.RestApiVersions.VER_1;
	private static final String USER_ROLE = "user";
	private static final String ADMIN_ROLE = "admin";
	private static final Log LOG = LogFactory.getLog(AuthServicePushServiceImpl.class); 
	
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@Autowired
	@Qualifier("consumerRestOperations")
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

	@Value("#{{'admin', 'kc', 'kr'}}")
	private List<String> ignoredUsers = new ArrayList<>();
	
	@Override
	public AuthServicePushStatus pushAllUsers() {
		AuthServicePushStatus status = new AuthServicePushStatus();
		List<AuthUser> peopleToSync = getAllKIMPeople().stream()
				.filter(person -> { return !ignoredUsers.contains(person.getPrincipalName()); })
				.map(person -> {
					return generateAuthUserFromKimPerson(person);
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
						updateUserInAuthService(person, authServicePerson.getId(), getOrGenerateUserPassword(person));
						status.addNumberUpdated();
					}
				} else if (authServicePerson != null) {
					removeUserFromAuthService(authServicePerson.getId());
					status.addNumberRemoved();
				}
			} catch (Exception e) {
				status.getErrors().add(e.getMessage());
				LOG.error("Error pushing user " + person.getUsername() + " to auth service", e);
			}
		}
		
		return status;
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
		kimAuthUser.setRole(getUserAuthSystemRole(person));
		return kimAuthUser;
	}
	
	protected String getOrGenerateUserPassword(AuthUser person) {
		if (useUserNameAsPassword()) {
			return person.getUsername();
		} else {
			return UUID.randomUUID().toString();
		}
	}

	protected boolean useUserNameAsPassword() {
		return configurationService.getPropertyValueAsBoolean(AUTH_USER_PUSH_USERNAME_AS_PASSWORD);
	}
	
	protected String getUserAuthSystemRole(Person person) {
		if (permissionService.hasPermission(person.getPrincipalId(), KimConstants.NAMESPACE_CODE, KimConstants.PermissionNames.MODIFY_ENTITY)) {
			return ADMIN_ROLE;
		} else {
			return USER_ROLE;
		}
	}
	
	protected List<Person> getAllKIMPeople() {
		return personService.findPeople(Collections.emptyMap());
	}
	
	protected List<AuthUser> getAllAuthServiceUsers() {
		ResponseEntity<List<AuthUser>> result = restOperations.exchange(getUsersApiUrl(), HttpMethod.GET, 
				new HttpEntity<String>(authServiceRestUtilService.getAuthServiceStyleHttpHeadersForUser(AUTH_USER_API_VERSION)), new ParameterizedTypeReference<List<AuthUser>>() { });
		return result.getBody();
	}
	
	protected void addUserToAuthService(AuthUser newUser, String userPassword) {
		newUser.setPassword(userPassword);
		ResponseEntity<String> result = restOperations.exchange(getUsersApiUrl(), HttpMethod.POST, 
				new HttpEntity<AuthUser>(newUser, authServiceRestUtilService.getAuthServiceStyleHttpHeadersForUser(AUTH_USER_API_VERSION)), String.class);
		if (result.getStatusCode() != HttpStatus.CREATED) {
			throw new RestClientException(result.getBody());
		}
	}
	
	protected void updateUserInAuthService(AuthUser updatedUser, String userId, String userPassword) {
		updatedUser.setPassword(userPassword);
		ResponseEntity<String> result = restOperations.exchange(getUsersApiUrl() + userId, HttpMethod.PUT, 
				new HttpEntity<AuthUser>(updatedUser, authServiceRestUtilService.getAuthServiceStyleHttpHeadersForUser(AUTH_USER_API_VERSION)), String.class);
		if (result.getStatusCode() != HttpStatus.OK) {
			throw new RestClientException(result.getBody());
		}
	}
	
	protected void removeUserFromAuthService(String userId) {
		ResponseEntity<String> result = restOperations.exchange(getUsersApiUrl() + userId, HttpMethod.DELETE, 
				new HttpEntity<AuthUser>(authServiceRestUtilService.getAuthServiceStyleHttpHeadersForUser(AUTH_USER_API_VERSION)), String.class);
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

}
