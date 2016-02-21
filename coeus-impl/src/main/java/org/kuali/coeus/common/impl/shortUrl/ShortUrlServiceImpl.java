/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.impl.shortUrl;

import org.kuali.coeus.common.framework.shortUrl.ResourceNotFoundException;
import org.kuali.coeus.common.framework.shortUrl.ShortUrlDao;
import org.kuali.coeus.common.framework.shortUrl.ShortUrlService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kew.doctype.bo.DocumentType;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component("shortUrlService")
public class ShortUrlServiceImpl implements ShortUrlService {

    public static final String DOC_ID = "docId";
    public static final String COMMAND = "command";
    public static final String DISPLAY_DOC_SEARCH_VIEW = "displayDocSearchView";
    public static final String VIEW_DOCUMENT = "viewDocument";
    public static final String TRUE = "true";
    public static final String VIEW_ID = "viewId";
    public static final String HREF = "href";
    public static final String RETURN_LOCATION = "returnLocation";
    public static final String METHOD_TO_CALL = "methodToCall";
    public static final String START = "start";
    public static final String KC_KRAD_LANDING_PAGE = "/kc-krad/landingPage";
    public static final String KC_HEADER_IFRAME_VIEW = "Kc-Header-IframeView";
    public static final String KC_LANDING_PAGE_REDIRECT_VIEW = "Kc-LandingPage-RedirectView";
    public static final String TOP = "_TOP";
    public static final String DOC_SEARCH_TARGET = "DOC_SEARCH_TARGET";

    @Autowired
    @Qualifier("routeHeaderService")
    private RouteHeaderService routeHeaderService;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    @Autowired
    @Qualifier("shortUrlDao")
    private ShortUrlDao shortUrlDao;

    @Override
    public String constructUrl(String docId) throws ResourceNotFoundException{
        try {
            DocumentType documentType = getRouteHeaderService().getRouteHeader(docId).getDocumentType();
            if (TOP.equalsIgnoreCase(documentType.getDocSearchTarget().getPolicyStringValue())) {
                return constructUrl(documentType.getResolvedDocumentHandlerUrl(), docId);
            } else {
                return constructIframeUrl(documentType.getResolvedDocumentHandlerUrl(), docId);
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException(e);
        }
    }

    @Override
    public String constructUrl(String id, String table, String column) throws ResourceNotFoundException {
        return constructUrl(getShortUrlDao().getDocId(id, table, column));

    }

    @Override
    public String constructUrlByVersionStatus(String id, String table, String column) throws ResourceNotFoundException {
        return constructUrl(getShortUrlDao().getDocIdByVersionStatus(id, table, column));
    }

    @Override
    public String constructUrlByVersionHistory(String id, String table, String column) throws ResourceNotFoundException {
        return constructUrl(getShortUrlDao().getDocIdByVersionHistory(id, table, column));
    }

    @Override
    public String constructUrlByMaxSequenceNumber(String id, String table, String column) throws ResourceNotFoundException {
        return constructUrl(getShortUrlDao().getDocIdByMaxSequenceNumber(id, table, column));
    }

    private String constructUrl(String baseUrl, String id) {
        Properties parameters = new Properties();
        parameters.put(DOC_ID, id);
        parameters.put(COMMAND, DISPLAY_DOC_SEARCH_VIEW);
        parameters.put(VIEW_DOCUMENT, TRUE);
        return UrlFactory.parameterizeUrl(baseUrl, parameters);
    }

    public String constructIframeUrl(String baseUrl, String id) {
        Properties parameters = new Properties();
        parameters.put(HREF, constructUrl(baseUrl, id));
        parameters.put(RETURN_LOCATION,getRedirectViewUrl());
        parameters.put(METHOD_TO_CALL, START);
        return UrlFactory.parameterizeUrl(getIframeViewUrl(), parameters);
    }

    protected String getLandingPageUrl() {
        return getConfigurationService().getPropertyValueAsString(KRADConstants.ConfigParameters.APPLICATION_URL) +
                KC_KRAD_LANDING_PAGE;
    }

    protected String getIframeViewUrl() {
        Properties parameters = new Properties();
        parameters.put(VIEW_ID, KC_HEADER_IFRAME_VIEW);
        return UrlFactory.parameterizeUrl(getLandingPageUrl(),parameters);
    }

    protected String getRedirectViewUrl() {
        Properties parameters = new Properties();
        parameters.put(VIEW_ID, KC_LANDING_PAGE_REDIRECT_VIEW);
        return UrlFactory.parameterizeUrl(getLandingPageUrl(),parameters);
    }

    public RouteHeaderService getRouteHeaderService() {
        return routeHeaderService;
    }

    public void setRouteHeaderService(RouteHeaderService routeHeaderService) {
        this.routeHeaderService = routeHeaderService;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public ShortUrlDao getShortUrlDao() {
        return shortUrlDao;
    }

    public void setShortUrlDao(ShortUrlDao shortUrlDao) {
        this.shortUrlDao = shortUrlDao;
    }
}
