package org.kuali.coeus.sys.framework.auth;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

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
import org.apache.commons.net.util.Base64;
import org.kuali.coeus.sys.framework.rest.AuthServiceRestUtilService;
import org.kuali.coeus.sys.framework.rest.RestServiceConstants;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthServiceFilter implements Filter {

	private static final String CURRENT_USER_APPEND = "/current";
	public static final String AUTH_SERVICE_FILTER_AUTH_TOKEN_SESSION_ATTR = "AUTH_SERVICE_FILTER_AUTH_TOKEN";
	public static final String AUTH_SERVICE_FILTER_AUTHED_USER_ATTR = "AUTH_SERVICE_FILTER_AUTHED_USER";
	private static final String SECONDS_TO_CACHE_AUTH_TOKEN_RESPONSE_CONFIG = "secondsToCacheAuthTokenResponse";
	private static final String BASIC_AUTH_KC_USERNAME = "admin";
	private static final long SECONDS_TO_CACHE_AUTH_TOKEN_IN_SESSION_DEFAULT = 300L;
	private static final String ACCESS_DENIED_MESSAGE = "Access Denied";
	private static final String AUTHORIZATION_PREFIX = "Bearer ";
	private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
	private static final String AUTH_TOKEN_COOKIE_NAME = "authToken";
	private static final String AUTH_RETURN_TO = "/auth?return_to=";
	private static final String KC_REST_ADMIN_PASSWORD = "kc.rest.admin.password";
	private static final String KC_REST_ADMIN_USERNAME = "kc.rest.admin.username";
	private static final Log LOG = LogFactory.getLog(AuthServiceFilter.class);
	
	private String authServiceUrl;
	private String authWithReturnTo;
	private String getCurrentUserUrl;
	private String hashedApiAdminBasicAuth;
	private String apiUserName;
	private long secondsToCacheAuthTokenInSession = SECONDS_TO_CACHE_AUTH_TOKEN_IN_SESSION_DEFAULT;
	
	private ConfigurationService configurationService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String secondsToCache = filterConfig.getInitParameter(SECONDS_TO_CACHE_AUTH_TOKEN_RESPONSE_CONFIG);
		if (secondsToCache != null) {
			secondsToCacheAuthTokenInSession = Long.parseLong(secondsToCache);
		}
		
		authServiceUrl = getConfigurationService().getPropertyValueAsString(RestServiceConstants.Configuration.AUTH_BASE_URL);
		authWithReturnTo = authServiceUrl + AUTH_RETURN_TO;
		getCurrentUserUrl = getConfigurationService().getPropertyValueAsString(RestServiceConstants.Configuration.AUTH_USERS_URL) + CURRENT_USER_APPEND;
		
		
		apiUserName = getConfigurationService().getPropertyValueAsString(KC_REST_ADMIN_USERNAME);
		String apiPassword = getConfigurationService().getPropertyValueAsString(KC_REST_ADMIN_PASSWORD);
		if (StringUtils.isNotBlank(apiUserName) && StringUtils.isNotBlank(apiPassword)) {
			hashedApiAdminBasicAuth = "Basic " + new String(Base64.encodeBase64((apiUserName + ":" + apiPassword).getBytes()));
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authorizationHeader = httpRequest.getHeader(AUTHORIZATION_HEADER_NAME);
		if (authorizationHeader != null) {
			authenticateBasedOnAuthorizationHeader(authorizationHeader, httpRequest, httpResponse, chain);
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
		if (hashedApiAdminBasicAuth != null && authorizationHeader.equals(hashedApiAdminBasicAuth)) {
			chain.doFilter(new AuthServiceRequestWrapper(BASIC_AUTH_KC_USERNAME, httpRequest), httpResponse);
		} else if (authorizationHeader.startsWith(AUTHORIZATION_PREFIX)) {
			AuthUser authedUser = validateAuthToken(authorizationHeader, httpRequest);
			chain.doFilter(new AuthServiceRequestWrapper(authedUser.getUsername(), httpRequest), httpResponse);
		} else {
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, ACCESS_DENIED_MESSAGE);
		}
	}

	protected void redirectToLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		httpResponse.sendRedirect(authWithReturnTo + httpRequest.getRequestURL());
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
				new HttpEntity<String>(getAuthServiceRestUtilService().getAuthServiceStyleHttpHeadersForToken(RestServiceConstants.RestApiVersions.VER_1, authTokenValue)), AuthUser.class);
		
		authedUser = result.getBody();
		if (authedUser != null) {
			authedUser.setAuthToken(authTokenValue);
			request.getSession().setAttribute(AUTH_SERVICE_FILTER_AUTHED_USER_ATTR, authedUser);
			request.getSession().setAttribute(AUTH_SERVICE_FILTER_AUTH_TOKEN_SESSION_ATTR, authTokenValue);
		}
		return authedUser;
	}
	
	public static String getAuthToken(HttpSession session) {
		return ((AuthUser)session.getAttribute(AUTH_SERVICE_FILTER_AUTHED_USER_ATTR)).getAuthToken();
	}

	protected RestTemplate getRestTemplate() {
		return KcServiceLocator.getService("consumerRestOperations");
	}
	
	protected AuthServiceRestUtilService getAuthServiceRestUtilService() {
		return KcServiceLocator.getService(AuthServiceRestUtilService.class);
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
}
