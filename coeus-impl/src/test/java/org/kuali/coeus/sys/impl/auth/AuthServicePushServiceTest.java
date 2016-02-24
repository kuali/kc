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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.auth.AuthServicePushStatus;
import org.kuali.coeus.sys.framework.auth.AuthUser;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.common.assignee.Assignee;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.impl.identity.PersonImpl;

public class AuthServicePushServiceTest {
	
	private Person person1;
	private Person person2;
	private Person person3;
	
	private List<AuthUser> addedUsers;
	private List<AuthUser> updatedUsers;
	
	@Before
	public void setup() {
		person1 = new PersonMock("test1");
		person2 = new PersonMock("test2");
		person3 = new PersonMock("test3");
		
		addedUsers = new ArrayList<>();
		updatedUsers = new ArrayList<>();
	}	
	
	@Test
	public void testPushAllUsersSame() {
		AuthServicePushServiceImpl service = new AuthServicePushServiceImpl() {
			@Override
			protected List<Person> getAllKIMPeople() {
				List<Person> result = new ArrayList<>();
				result.add(person1);
				result.add(person2);
				result.add(person3);
				return result;
			}
			
			@Override
			protected List<AuthUser> getAllAuthServiceUsers() {
				List<AuthUser> authServiceUserList = new ArrayList<AuthUser>();
				authServiceUserList.add(this.generateAuthUserFromKimPerson(person1));
				authServiceUserList.add(this.generateAuthUserFromKimPerson(person2));
				authServiceUserList.add(this.generateAuthUserFromKimPerson(person3));
				return authServiceUserList;
			}
			
			@Override
			protected List<String> getAdminUsers() {
				return new ArrayList<>(Collections.emptyList());
			}
			
			@Override
			protected boolean useDevPassword() {
				return false;
			}
		};
		AuthServicePushStatus status = service.pushAllUsers();
		assertNotNull(status);
		assertEquals(3, status.getNumberOfUsers());
		assertEquals(3, status.getNumberSame());
		assertEquals(0, status.getNumberAdded());
		assertEquals(0, status.getNumberUpdated());
	}
	
	@Test
	public void testPushAllUsers() {
		AuthServicePushServiceImpl service = new AuthServicePushServiceImpl() {
			@Override
			protected List<Person> getAllKIMPeople() {
				List<Person> result = new ArrayList<>();
				result.add(person1);
				result.add(person2);
				result.add(person3);
				return result;
			}
			
			@Override
			protected List<AuthUser> getAllAuthServiceUsers() {
				List<AuthUser> authServiceUserList = new ArrayList<AuthUser>();
				authServiceUserList.add(this.generateAuthUserFromKimPerson(person1));
				authServiceUserList.add(this.generateAuthUserFromKimPerson(person2));
				authServiceUserList.get(1).setName("Testing Name Change");
				return authServiceUserList;
			}
			
			@Override
			protected void addUserToAuthService(AuthUser newUser, String userPassword) { }
			
			@Override
			protected void updateUserInAuthService(AuthUser updatedUser, String userId) { }
			
			@Override
			protected List<String> getAdminUsers() {
				return new ArrayList<>(Collections.singleton(person3.getPrincipalId()));
			}
			
			@Override
			protected boolean useDevPassword() {
				return false;
			}
		};
		AuthServicePushStatus status = service.pushAllUsers();
		assertNotNull(status);
		assertEquals(3, status.getNumberOfUsers());
		assertEquals(1, status.getNumberSame());
		assertEquals(1, status.getNumberAdded());
		assertEquals(1, status.getNumberUpdated());
	}
	
	@Test
	public void testPushAllUsers_oneAdmin() {
		AuthServicePushServiceImpl service = new AuthServicePushServiceImpl() {
			@Override
			protected List<Person> getAllKIMPeople() {
				List<Person> result = new ArrayList<>();
				result.add(person1);
				result.add(person2);
				result.add(person3);
				return result;
			}
			
			@Override
			protected List<AuthUser> getAllAuthServiceUsers() {
				List<AuthUser> authServiceUserList = new ArrayList<AuthUser>();
				authServiceUserList.add(this.generateAuthUserFromKimPerson(person1));
				authServiceUserList.add(this.generateAuthUserFromKimPerson(person2));
				authServiceUserList.get(1).setName("Testing Name Change");
				return authServiceUserList;
			}
			
			@Override
			protected void addUserToAuthService(AuthUser newUser, String userPassword) {
				addedUsers.add(newUser);
			}
			
			@Override
			protected void updateUserInAuthService(AuthUser updatedUser, String userId) {
				updatedUsers.add(updatedUser);
			}
			
			@Override
			protected List<String> getAdminUsers() {
				return new ArrayList<>(Collections.singleton(person3.getPrincipalId()));
			}
			
			@Override
			protected boolean useDevPassword() {
				return false;
			}
		};
		AuthServicePushStatus status = service.pushAllUsers();
		assertNotNull(status);
		assertEquals(3, status.getNumberOfUsers());
		assertEquals(1, status.getNumberSame());
		assertEquals(1, status.getNumberAdded());
		assertEquals(1, status.getNumberUpdated());
		assertEquals(1, addedUsers.size());
		assertEquals("admin", addedUsers.get(0).getRole());
		assertEquals(0L, updatedUsers.stream().filter(user -> user.getRole().equals("admin")).collect(Collectors.counting()).longValue());
	
	}	
	
	@Test
	public void testGetAdminUsers() {
		AuthServicePushServiceImpl service = new AuthServicePushServiceImpl() {
			@Override
			protected List<Assignee> getAdminAssignees() {
				return Stream.of(Assignee.Builder.create("1", null, Collections.emptyList()).build(),
						Assignee.Builder.create(null, "2", Collections.emptyList()).build()).collect(Collectors.toList());
			}
			
			@Override
			protected List<String> getGroupMembers(Assignee assignee) {
				return Stream.of("3", "4").collect(Collectors.toList());
			}
		};
		
		List<String> adminUsers = service.getAdminUsers();
		assertEquals(3, adminUsers.size());
		assertTrue(adminUsers.contains("1"));
		assertTrue(adminUsers.contains("3"));
		assertTrue(adminUsers.contains("4"));
	}
	
	final class PersonMock extends PersonImpl {

		private String principalId;
		private String userName;
		private String firstName;
		private String lastName;
		private String name;
		private String emailAddress;
		private String phoneNumber;
		
		public PersonMock(String principalId, String userName, String firstName, String lastName,
				String name, String emailAddress, String phoneNumber) {
			super();
			this.principalId = principalId;
			this.userName = userName;
			this.firstName = firstName;
			this.lastName = lastName;
			this.name = name;
			this.emailAddress = emailAddress;
			this.phoneNumber = phoneNumber;
		}
		
		public PersonMock(String userName, String firstName, String lastName, String phoneNumber) {
			this(userName, userName, firstName, lastName, 
					firstName + " " + lastName, 
					userName + "@kuali.dev", 
					phoneNumber);
		}
		
		public PersonMock(String userName) {
			this(userName, userName, userName, "555-555-5555");
		}
		
		@Override
		public String getPrincipalName() {
			return userName;
		}

		@Override
		public String getFirstName() {
			return firstName;
		}

		@Override
		public String getLastName() {
			return lastName;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getEmailAddress() {
			return emailAddress;
		}

		@Override
		public String getPhoneNumber() {
			return phoneNumber;
		}
		
		@Override
		public boolean isActive() {
			return true;
		}

		public String getPrincipalId() {
			return principalId;
		}

		public void setPrincipalId(String principalId) {
			this.principalId = principalId;
		}
	}
}
