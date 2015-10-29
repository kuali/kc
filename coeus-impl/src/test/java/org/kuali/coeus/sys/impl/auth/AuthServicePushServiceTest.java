package org.kuali.coeus.sys.impl.auth;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.auth.AuthServicePushStatus;
import org.kuali.coeus.sys.framework.auth.AuthUser;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.impl.identity.PersonImpl;

public class AuthServicePushServiceTest {
	
	private Person person1;
	private Person person2;
	private Person person3;
	
	@Before
	public void setup() {
		person1 = new PersonMock("test1");
		person2 = new PersonMock("test2");
		person3 = new PersonMock("test3");
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
			protected String getUserAuthSystemRole(Person person) {
				return "admin";
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
			protected void updateUserInAuthService(AuthUser updatedUser, String userId, String userPassword) { }
			
			@Override
			protected String getUserAuthSystemRole(Person person) {
				return "admin";
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
	
	final class PersonMock extends PersonImpl {

		private String userName;
		private String firstName;
		private String lastName;
		private String name;
		private String emailAddress;
		private String phoneNumber;
		
		public PersonMock(String userName, String firstName, String lastName,
				String name, String emailAddress, String phoneNumber) {
			super();
			this.userName = userName;
			this.firstName = firstName;
			this.lastName = lastName;
			this.name = name;
			this.emailAddress = emailAddress;
			this.phoneNumber = phoneNumber;
		}
		
		public PersonMock(String userName, String firstName, String lastName, String phoneNumber) {
			this(userName, firstName, lastName, 
					firstName + " " + lastName, 
					userName = "@kuali.dev", 
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
	}
}