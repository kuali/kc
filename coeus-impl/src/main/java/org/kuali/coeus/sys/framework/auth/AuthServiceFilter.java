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

import java.io.IOException;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rest.AuthServiceRestUtilService;
import org.kuali.coeus.sys.framework.rest.RestServiceConstants;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

public class AuthServiceFilter implements Filter {

	private static final String ADMIN_ROLE = "admin";
	private static final String CURRENT_USER_APPEND = "/current";
	public static final String AUTH_SERVICE_FILTER_AUTH_TOKEN_SESSION_ATTR = "AUTH_SERVICE_FILTER_AUTH_TOKEN";
	public static final String AUTH_SERVICE_FILTER_AUTHED_USER_ATTR = "AUTH_SERVICE_FILTER_AUTHED_USER";
	private static final String SECONDS_TO_CACHE_AUTH_TOKEN_RESPONSE_CONFIG = "secondsToCacheAuthTokenResponse";
	private static final String BASIC_AUTH_KC_USERNAME = ADMIN_ROLE;
	private static final long SECONDS_TO_CACHE_AUTH_TOKEN_IN_SESSION_DEFAULT = 300L;
	private static final String ACCESS_DENIED_MESSAGE = "Access Denied";
	private static final String AUTHORIZATION_PREFIX = "Bearer ";
	private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
	private static final String AUTH_TOKEN_COOKIE_NAME = "authToken";
	private static final String AUTH_RETURN_TO = "/auth?return_to=";
	private static final String KC_REST_ADMIN_PASSWORD = "kc.rest.admin.password";
	private static final String KC_REST_ADMIN_USERNAME = "kc.rest.admin.username";
	private static final String REST_API_URLS_PARAM = "auth.rest.urls.regex";
	private static final String ALLOW_MISSING_ADMINS_TO_PROXY_ADMIN_ACCOUNT = "auth.filter.allow.admin.proxy";
	private static final String AUTH_ADMIN_PROXY_USER = "auth.filter.proxy.username";
	private static final String AUTH_IMPERSONATION_LOGGING = "auth.impersonation.logging";
	private static final Log LOG = LogFactory.getLog(AuthServiceFilter.class);
	
	private String authServiceUrl;
	private String authWithReturnTo;
	private String getCurrentUserUrl;
	private String hashedApiAdminBasicAuth;
	private String apiUserName;
	private List<Pattern> restUrlsRegex;
	private Boolean allowAdminProxy = Boolean.FALSE;
	private String adminProxyUsername;
	private long secondsToCacheAuthTokenInSession = SECONDS_TO_CACHE_AUTH_TOKEN_IN_SESSION_DEFAULT;
	
	private ConfigurationService configurationService;
	private RestOperations restTemplate;
	private AuthServiceRestUtilService authServiceRestUtilService;
	private IdentityService identityService;
	private BusinessObjectService businessObjectService;
	private ParameterService parameterService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String secondsToCache = filterConfig.getInitParameter(SECONDS_TO_CACHE_AUTH_TOKEN_RESPONSE_CONFIG);
		if (secondsToCache != null) {
			secondsToCacheAuthTokenInSession = Long.parseLong(secondsToCache);
		}
		
		authServiceUrl = getConfigurationService().getPropertyValueAsString(RestServiceConstants.Configuration.AUTH_BASE_URL);
		authWithReturnTo = authServiceUrl + AUTH_RETURN_TO;
		getCurrentUserUrl = getConfigurationService().getPropertyValueAsString(RestServiceConstants.Configuration.AUTH_USERS_URL) + CURRENT_USER_APPEND;
		allowAdminProxy = getConfigurationService().getPropertyValueAsBoolean(ALLOW_MISSING_ADMINS_TO_PROXY_ADMIN_ACCOUNT);
		adminProxyUsername = getConfigurationService().getPropertyValueAsString(AUTH_ADMIN_PROXY_USER);

		restUrlsRegex = buildRestUrlRegexPatterns(getConfigurationService().getPropertyValueAsString(REST_API_URLS_PARAM));
		
		apiUserName = getConfigurationService().getPropertyValueAsString(KC_REST_ADMIN_USERNAME);
		String apiPassword = getConfigurationService().getPropertyValueAsString(KC_REST_ADMIN_PASSWORD);
		if (StringUtils.isNotBlank(apiUserName) && StringUtils.isNotBlank(apiPassword)) {
			hashedApiAdminBasicAuth = "Basic " + new String(Base64.getEncoder().encode((apiUserName + ":" + apiPassword).getBytes()));
		}
	}
	
	protected List<Pattern> buildRestUrlRegexPatterns(String restUrlPatterns) {
		return Arrays.asList(restUrlPatterns.split(",")).stream().map(Pattern::compile).collect(Collectors.toList());
	}
	
	protected boolean isUrlForRest(String requestUrl) {
		return restUrlsRegex.stream().anyMatch(pattern -> pattern.matcher(requestUrl).matches());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (isUrlForRest(httpRequest.getRequestURI())) {
			authenticateBasedOnAuthorizationHeader(httpRequest.getHeader(AUTHORIZATION_HEADER_NAME), 
					httpRequest, httpResponse, chain);
		} else {
			authenticateWebBasedUser(chain, httpRequest, httpResponse);
		}
	}

	protected void authenticateWebBasedUser(FilterChain chain,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws IOException, ServletException {
		Cookie authToken = httpRequest.getCookies() != null ? Arrays.asList(httpRequest.getCookies()).stream().filter(cookie -> { return cookie.getName().equals(AUTH_TOKEN_COOKIE_NAME); }).findFirst().orElse(null) : null;
		if (authToken == null) {
			redirectToLogin(httpRequest, httpResponse);
		} else {
			validateWebUserLoginToken(authToken, httpRequest, httpResponse, chain);
		}
	}

	protected void validateWebUserLoginToken(Cookie authToken, HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) 
			throws IOException, ServletException {
		final String authTokenValue = authToken.getValue();
		AuthUser authedUser = null;
		try {
			authedUser = validateAuthToken(authTokenValue, httpRequest);
		} catch (Exception e) {
			LOG.warn("Error validating auth token", e);
			redirectToLogin(httpRequest, httpResponse);
			return;
		}
		if (authedUser != null && StringUtils.isNotBlank(authedUser.getUsername())) {
			authedUser = proxyAdminUsers(authedUser);
			chain.doFilter(new AuthServiceRequestWrapper(authedUser.getUsername(), httpRequest), httpResponse);
			return;
		} else {
			redirectToLogin(httpRequest, httpResponse);
			return;
		}
	}

	protected void authenticateBasedOnAuthorizationHeader(String authorizationHeader, HttpServletRequest httpRequest, 
			HttpServletResponse httpResponse, FilterChain chain) 
					throws IOException, ServletException {
		if (hashedApiAdminBasicAuth != null && hashedApiAdminBasicAuth.equals(authorizationHeader)) {
			chain.doFilter(new AuthServiceRequestWrapper(BASIC_AUTH_KC_USERNAME, httpRequest), httpResponse);
			return;
		} else if (authorizationHeader != null && authorizationHeader.startsWith(AUTHORIZATION_PREFIX)) {
			AuthUser authedUser = validateAuthToken(authorizationHeader, httpRequest);
			if (authedUser != null) {
				authedUser = proxyAdminUsers(authedUser);
				chain.doFilter(new AuthServiceRequestWrapper(authedUser.getUsername(), httpRequest), httpResponse);
				return;
			}
		}
		httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, ACCESS_DENIED_MESSAGE);
	}

	protected void redirectToLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		StringBuffer requestUrl = httpRequest.getRequestURL();
		if (StringUtils.isNotBlank(httpRequest.getQueryString())) {
			requestUrl.append("?").append(httpRequest.getQueryString());
		}
		httpResponse.sendRedirect(authWithReturnTo + URLEncoder.encode(requestUrl.toString(), "UTF-8"));
	}

	protected AuthUser validateAuthToken(final String authTokenValue, final HttpServletRequest request) {
		AuthUser authedUser = (AuthUser) request.getSession().getAttribute(AUTH_SERVICE_FILTER_AUTHED_USER_ATTR);
		if (authedUser != null && StringUtils.equals(authedUser.getAuthToken(), authTokenValue) 
				&& authedUser.getLastValidated().plus(secondsToCacheAuthTokenInSession, ChronoUnit.SECONDS).isAfter(Instant.now())) {
			return authedUser;
		} else {
			request.getSession().removeAttribute(AUTH_SERVICE_FILTER_AUTHED_USER_ATTR);
		}
		
		String currentGetUserUrl = getCurrentUserUrl;
		if (!currentGetUserUrl.startsWith("http")) {
			currentGetUserUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + currentGetUserUrl;
		}
		
		ResponseEntity<AuthUser> result = getRestTemplate().exchange(currentGetUserUrl, HttpMethod.GET, 
				new HttpEntity<String>(getAuthServiceRestUtilService().getAuthServiceStyleHttpHeadersForToken(authTokenValue)), AuthUser.class);
		
		authedUser = result.getBody();
		if (authedUser != null) {
			authedUser.setAuthToken(authTokenValue);
			request.getSession().setAttribute(AUTH_SERVICE_FILTER_AUTHED_USER_ATTR, authedUser);
			request.getSession().setAttribute(AUTH_SERVICE_FILTER_AUTH_TOKEN_SESSION_ATTR, authTokenValue);
		}
		logImpersonation(authedUser, request.getRequestURL().toString());
		return authedUser;
	}
	
	public static String getAuthToken(HttpSession session) {
		return ((AuthUser)session.getAttribute(AUTH_SERVICE_FILTER_AUTHED_USER_ATTR)).getAuthToken();
	}
	
	protected AuthUser proxyAdminUsers(AuthUser authUser) {
		if (allowAdminProxy 
			&& ADMIN_ROLE.equals(authUser.getRole())
			&& getPrincipal(authUser.getUsername()) == null) {
			authUser.setActualUser(authUser.getUsername());
			authUser.setUsername(adminProxyUsername);
			LOG.warn("Proxying admin user '" + authUser.getActualUser() + "' to the proxy admin account of '" + adminProxyUsername + "'");			
		}
		return authUser;
	}

	protected void logImpersonation(AuthUser authUser, String requestedUrl) {
		if (authUser.getImpersonatedBy() != null && getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_SYSTEM,
				Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, AUTH_IMPERSONATION_LOGGING)) {
			getBusinessOjectService().save(new CoreImpersonation(authUser, requestedUrl));
			LOG.warn("User in session'" + authUser.getUsername() + "' is being impersonated by'" + authUser.getImpersonatedBy()+ "'" + " as " + authUser.getDisplayName());
		}
	}

	protected Principal getPrincipal(String username) {
		return getIdentityService().getPrincipalByPrincipalName(username);
	}

	@Override
	public void destroy() {

	}
	
	static class AuthServiceRequestWrapper extends HttpServletRequestWrapper {
		
		private String username;
		public AuthServiceRequestWrapper(String username, HttpServletRequest request) {
			super(request);
			this.username = username;
		}
		
		public String getRemoteUser() {
			return username;
		}
	}

	public ConfigurationService getConfigurationService() {
		if (configurationService == null) {
			configurationService = KcServiceLocator.getService(ConfigurationService.class);
		}
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public RestOperations getRestTemplate() {
		if (restTemplate == null) {
			restTemplate = KcServiceLocator.getService(RestOperations.class);
		}
		return restTemplate;
	}

	public void setRestTemplate(RestOperations restTemplate) {
		this.restTemplate = restTemplate;
	}

	public AuthServiceRestUtilService getAuthServiceRestUtilService() {
		if (authServiceRestUtilService == null) {
			authServiceRestUtilService = KcServiceLocator.getService(AuthServiceRestUtilService.class);
		}
		return authServiceRestUtilService;
	}

	public void setAuthServiceRestUtilService(AuthServiceRestUtilService authServiceRestUtilService) {
		this.authServiceRestUtilService = authServiceRestUtilService;
	}

	public IdentityService getIdentityService() {
		if (identityService == null) {
			identityService = KcServiceLocator.getService(IdentityService.class);
		}
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	public BusinessObjectService getBusinessOjectService() {
		if (businessObjectService == null) {
			businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
		}
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	public ParameterService getParameterService() {
		if (parameterService == null) {
			parameterService = KcServiceLocator.getService(ParameterService.class);
		}
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
}
