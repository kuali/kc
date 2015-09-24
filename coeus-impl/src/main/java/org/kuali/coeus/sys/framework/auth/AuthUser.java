package org.kuali.coeus.sys.framework.auth;

import java.time.Instant;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class AuthUser {
	private String id;
	private String name;
	private String username;
	private String email;
	private String role;
	private String displayName;
	
	@JsonIgnore
	private String authToken;
	@JsonIgnore
	private Instant lastValidated;
	
	public AuthUser() { 
		super();
		lastValidated = Instant.now();
	}
	
	public String getId() {
		return id;
	}
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
}
