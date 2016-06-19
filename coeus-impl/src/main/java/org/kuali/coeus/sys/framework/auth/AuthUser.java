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
package org.kuali.coeus.sys.framework.auth;

import java.io.Serializable;
import java.time.Instant;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AuthUser implements Serializable {
	
	private String id;
	private String schoolId;
	private String name;
	private String username;
	private String email;
	private String phone;
	private String role;
	private String firstName;
	private String lastName;
	private String password;
	private String impersonatedBy;
	private String displayName;
	
	@JsonIgnore
	private String authToken;
	@JsonIgnore
	private Instant lastValidated;
	@JsonIgnore
	private boolean active = true;
	@JsonIgnore
	private String actualUser;
	
	public AuthUser() { 
		super();
		lastValidated = Instant.now();
	}
	
	@JsonProperty
	public String getPassword() {
		return password;
	}

	@JsonIgnore
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonIgnore
	public String getId() {
		return id;
	}
	
	@JsonProperty
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public Instant getLastValidated() {
		return lastValidated;
	}
	public void setLastValidated(Instant lastValidated) {
		this.lastValidated = lastValidated;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImpersonatedBy() {
		return impersonatedBy;
	}
	public void setImpersonatedBy(String impersonatedBy) {
		this.impersonatedBy = impersonatedBy;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
		return false;
		}
		AuthUser rhs = (AuthUser) obj;
		return new EqualsBuilder()
			.append(schoolId, rhs.schoolId)
			.append(name, rhs.name)
			.append(username, rhs.username)
			.append(email, rhs.email)
			.append(phone, rhs.phone)
			.append(role, rhs.role)
			.append(firstName, rhs.firstName)
			.append(lastName, rhs.lastName)
			.append(impersonatedBy, rhs.impersonatedBy)
			.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append(username)
			.append(schoolId)
			.append(name)
			.append(email)
			.append(phone)
			.append(role)
			.append(firstName)
			.append(lastName)
			.append(lastValidated)
			.append(active)
			.append(impersonatedBy)
			.append(displayName)
			.toString();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getActualUser() {
		return actualUser;
	}

	public void setActualUser(String actualUser) {
		this.actualUser = actualUser;
	}
}
