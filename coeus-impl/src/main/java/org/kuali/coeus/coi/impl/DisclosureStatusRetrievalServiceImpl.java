package org.kuali.coeus.coi.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.coi.framework.DisclosureProjectStatus;
import org.kuali.coeus.coi.framework.DisclosureStatusRetrievalService;
import org.kuali.coeus.sys.framework.auth.AuthConstants;
import org.kuali.coeus.sys.framework.mq.rest.RestRequest;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.util.Collections;
import java.util.List;

@Component("disclosureStatusRetrievalService")
public class DisclosureStatusRetrievalServiceImpl implements DisclosureStatusRetrievalService {
    private static Log LOG = LogFactory.getLog(DisclosureStatusRetrievalServiceImpl.class);

    @Autowired
    @Qualifier("restOperations")
    private RestOperations restOperations;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;


    @Override
    public DisclosureProjectStatus getDisclosureStatusForPerson(String sourceId, String projectId, String personId) {
        final String url = getCoiStandaloneBaseUrl() + sourceId + "/" + projectId + "/" + personId;

        final RestRequest request = new RestRequest();
        final HttpHeaders headers = new HttpHeaders();
        headers.put(Constants.CONTENT_TYPE, Collections.singletonList(Constants.APPLICATION_JSON));
        headers.put(Constants.AUTHORIZATION_HEADER, Collections.singletonList(getAuthToken()));
        final HttpEntity<String> entity = new HttpEntity<>(request.getBody(), headers);

        return isAnnualDisclosuresAvailable(url, entity, org.springframework.http.HttpMethod.GET);

    }

    protected String getAuthToken() {
        return Constants.BEARER_TOKEN + getConfigurationService().getPropertyValueAsString(AuthConstants.AUTH_SYSTEM_TOKEN_PARAM);
    }

    protected String getCoiStandaloneBaseUrl() {
        return getConfigurationService().getPropertyValueAsString(Constants.COI_PROJECTS_DISCLOSURE_STATUS_URL);
    }

    protected DisclosureProjectStatus isAnnualDisclosuresAvailable(String url, HttpEntity<String> entity, HttpMethod method) {
        try {
            ResponseEntity<DisclosureProjectStatus> response = getDisclosureStatusFromCoi(url, entity, method);
            DisclosureProjectStatus projectStatus = response.getBody();
            if (LOG.isDebugEnabled()) {
                LOG.debug(url+ "returned status code "  + response.getStatusCode());
            }

            return projectStatus;
        } catch (UnknownHttpStatusCodeException e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn(url+ "returned status code "  + e.getRawStatusCode());
            }
            throw e;
        } catch (RuntimeException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error(url+ "returned error " + e.getMessage());
            }
            throw e;
        }
    }

    protected ResponseEntity<DisclosureProjectStatus> getDisclosureStatusFromCoi(String url, HttpEntity<String> entity, HttpMethod method) {
        return restOperations.exchange(url, method, entity, DisclosureProjectStatus.class, Collections.<String, List<String>>emptyMap());
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public RestOperations getRestOperations() {
        return restOperations;
    }

    public void setRestOperations(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

}
