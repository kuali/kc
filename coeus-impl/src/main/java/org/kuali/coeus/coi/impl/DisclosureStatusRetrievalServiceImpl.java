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
        final String url = getCoiApiUrl() + sourceId + "/" + projectId + "/" + personId;

        final RestRequest request = new RestRequest();
        final HttpHeaders headers = new HttpHeaders();
        headers.put(Constants.CONTENT_TYPE, Collections.singletonList(Constants.APPLICATION_JSON));
        headers.put(Constants.AUTHORIZATION_HEADER, Collections.singletonList(getAuthToken()));
        final HttpEntity<String> entity = new HttpEntity<>(request.getBody(), headers);
        return getDisclosureProjectStatus(url, entity, org.springframework.http.HttpMethod.GET);
    }

    protected String getAuthToken() {
        return Constants.BEARER_TOKEN + getConfigurationService().getPropertyValueAsString(AuthConstants.AUTH_SYSTEM_TOKEN_PARAM);
    }

    protected String getCoiApiUrl() {
        return getConfigurationService().getPropertyValueAsString(Constants.COI_PROJECTS_DISCLOSURE_STATUS_URL);
    }

    protected DisclosureProjectStatus getDisclosureProjectStatus(String url, HttpEntity<String> entity, HttpMethod method) {
        DisclosureProjectStatus projectStatus = new DisclosureProjectStatus();
        try {
            ResponseEntity<DisclosureProjectStatus> response = getDisclosureStatusFromCoi(url, entity, method);
            projectStatus = response.getBody();
            if (LOG.isDebugEnabled()) {
                LOG.debug(url+ "returned status code "  + response.getStatusCode());
            }
        } catch (UnknownHttpStatusCodeException e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn(url+ "returned status code "  + e.getRawStatusCode());
            }
        } catch (RuntimeException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error(url+ "returned error " + e.getMessage());
            }
        }
        return projectStatus;
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
