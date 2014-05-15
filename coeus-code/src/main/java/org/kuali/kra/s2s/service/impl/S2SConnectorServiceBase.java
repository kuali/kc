/*
kc * Copyright 2005-2014 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.s2s.ConfigurationConstants;
import org.kuali.kra.s2s.S2SException;
import org.kuali.coeus.propdev.api.location.ProposalSiteService;
import org.kuali.kra.s2s.service.S2SConnectorService;
import org.kuali.kra.s2s.service.S2SUtilService;

import gov.grants.apply.services.applicantwebservices_v2.GetApplicationListRequest;
import gov.grants.apply.services.applicantwebservices_v2.GetApplicationListResponse;
import gov.grants.apply.services.applicantwebservices_v2.GetApplicationStatusDetailRequest;
import gov.grants.apply.services.applicantwebservices_v2.GetApplicationStatusDetailResponse;
import gov.grants.apply.services.applicantwebservices_v2.GetOpportunitiesRequest;
import gov.grants.apply.services.applicantwebservices_v2.GetOpportunitiesResponse;
import gov.grants.apply.services.applicantwebservices_v2.SubmitApplicationRequest;
import gov.grants.apply.services.applicantwebservices_v2.SubmitApplicationResponse;
import gov.grants.apply.services.applicantwebservices_v2_0.ApplicantWebServicesPortType;
import gov.grants.apply.services.applicantwebservices_v2_0.ErrorMessage;
import gov.grants.apply.system.grantscommonelements_v1.ApplicationFilter;
import gov.grants.apply.system.grantscommonelements_v1.Attachment;

import javax.activation.DataHandler;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.*;

/**
 * 
 * This class is used to make web service call to grants.gov
 */
public class S2SConnectorServiceBase implements S2SConnectorService {

    private static final String KEY_OPPORTUNITY_ID = "OpportunityID";
    private static final String KEY_CFDA_NUMBER = "CFDANumber";
    private static final String KEY_SUBMISSION_TITLE = "SubmissionTitle";
    protected static final Log LOG = LogFactory.getLog(S2SConnectorServiceBase.class);

    private S2SUtilService s2SUtilService;
    private S2SConfigurationService s2SConfigurationService;
    private ProposalSiteService proposalSiteService;
    protected S2SCertificateReader s2sCertificateReader;

    protected String serviceHost;
    protected String servicePort;

    /**
     * This method is to get Opportunity List for the given cfda number,opportunity Id and competition Id from the grants guv. It
     * sets the given parameters on {@link GetOpportunitiesRequest} object and passes it to the web service.
     * 
     * @param cfdaNumber of the opportunity.
     * @param opportunityId parameter for the opportunity.
     * @param competitionId parameter for the opportunity.
     * @return GetOpportunityListResponse available list of opportunities applicable for the given cfda number,opportunity Id and
     *         competition Id.
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.S2SConnectorService#getOpportunityList(java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public GetOpportunitiesResponse getOpportunityList(String cfdaNumber, String opportunityId, String competitionId)
            throws S2SException {
        ApplicantWebServicesPortType port = configureApplicantIntegrationSoapPort(null,false);
        GetOpportunitiesRequest getOpportunityListRequest = new GetOpportunitiesRequest();
        
        getOpportunityListRequest.setCFDANumber(cfdaNumber);
        getOpportunityListRequest.setCompetitionID(competitionId);
        getOpportunityListRequest.setFundingOpportunityNumber(opportunityId);
        try {
            return port.getOpportunities(getOpportunityListRequest);
        }catch(SOAPFaultException soapFault){
            LOG.error("Error while getting list of opportunities", soapFault);
            if(soapFault.getMessage().indexOf("Connection refused")!=-1){
                throw new S2SException(KeyConstants.ERROR_GRANTSGOV_OPP_SER_UNAVAILABLE,soapFault.getMessage());
            }else{
                throw new S2SException(KeyConstants.ERROR_S2S_UNKNOWN,soapFault.getMessage());
            }
        }catch (ErrorMessage e) {
            LOG.error("Error while getting list of opportunities", e);
            throw new S2SException(KeyConstants.ERROR_S2S_UNKNOWN,e.getMessage());
        }
    }

    /**
     * This method is to get status of the submitted application.
     * 
     * @param ggTrackingId grants gov tracking id for the proposal.
     * @param proposalNumber Proposal number.
     * @return GetApplicationStatusDetailResponse status of the submitted application.
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.S2SConnectorService#getApplicationStatusDetail(java.lang.String, java.lang.String)
     */
    public GetApplicationStatusDetailResponse getApplicationStatusDetail(String ggTrackingId, String proposalNumber)
            throws S2SException {
        ApplicantWebServicesPortType port = getApplicantIntegrationSoapPort(proposalNumber);
        GetApplicationStatusDetailRequest applicationStatusDetailRequest = new GetApplicationStatusDetailRequest();
        applicationStatusDetailRequest.setGrantsGovTrackingNumber(ggTrackingId);
        try {
            return port.getApplicationStatusDetail(applicationStatusDetailRequest);
        }
        catch (ErrorMessage e) {
            LOG.error("Error while getting proposal submission status details", e);
            throw new S2SException(KeyConstants.ERROR_GRANTSGOV_SERVER_STATUS_REFRESH,e.getMessage());
        }
    }

    /**
     * This method is to get Application List from grants.gov for opportunityId, cfdaNumber and proposalNumber
     * 
     * @param opportunityId of the opportunity.
     * @param cfdaNumber of the opportunity.
     * @param proposalNumber proposal number.
     * @return GetApplicationListResponse application list.
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.S2SConnectorService#getApplicationList(java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public GetApplicationListResponse getApplicationList(String opportunityId, String cfdaNumber, String proposalNumber)
            throws S2SException {
        GetApplicationListRequest applicationListRequest = new GetApplicationListRequest();
        List<ApplicationFilter> filterList = applicationListRequest.getApplicationFilter();
        ApplicationFilter applicationFilter = new ApplicationFilter();
        applicationFilter.setFilter(KEY_OPPORTUNITY_ID);
        applicationFilter.setFilterValue(opportunityId);
        filterList.add(applicationFilter);
        if (cfdaNumber != null) {
            applicationFilter = new ApplicationFilter();
            applicationFilter.setFilter(KEY_CFDA_NUMBER);
            applicationFilter.setFilterValue(cfdaNumber);
            filterList.add(applicationFilter);
        }        
        applicationFilter = new ApplicationFilter();
        applicationFilter.setFilter(KEY_SUBMISSION_TITLE);
        applicationFilter.setFilterValue(proposalNumber);
        filterList.add(applicationFilter);
        ApplicantWebServicesPortType port = getApplicantIntegrationSoapPort(proposalNumber);
        try {
            return port.getApplicationList(applicationListRequest);
        }
        catch (ErrorMessage e) {
            LOG.error("Error occured while fetching application list", e);
            throw new S2SException(KeyConstants.ERROR_GRANTSGOV_SERVER_APPLICATION_LIST_REFRESH,e.getMessage());
        }
    }

    /**
     * This method is to submit a proposal to grants.gov
     * 
     * @param xmlText xml format of the form object.
     * @param attachments attachments of the proposal.
     * @param proposalNumber proposal number.
     * @return SubmitApplicationResponse corresponding to the input parameters passed.
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.S2SConnectorService#submitApplication(java.lang.String, java.util.Map, java.lang.String)
     */
    public SubmitApplicationResponse submitApplication(String xmlText, 
                    Map<String, DataHandler> attachments, String proposalNumber)
            throws S2SException {
        ApplicantWebServicesPortType port = getApplicantIntegrationSoapPort(proposalNumber);
        Iterator<String> it = attachments.keySet().iterator();
        SubmitApplicationRequest request = new SubmitApplicationRequest();

        while (it.hasNext()) {
            String key = it.next();
            String cid = key;
            DataHandler dataHandler = attachments.get(key);
            Attachment attachment = new Attachment();
            attachment.setFileContentId(cid);
            attachment.setFileDataHandler(dataHandler);
            request.getAttachment().add(attachment);
        }
        request.setGrantApplicationXML(xmlText);
        
        try {
            return port.submitApplication(request);
        }catch (ErrorMessage e) {
            LOG.error("Error occured while submitting proposal to Grants Gov", e);
            throw new S2SException(KeyConstants.ERROR_GRANTSGOV_SERVER_SUBMIT_APPLICATION,e.getMessage());
        }catch(SOAPFaultException e){
            LOG.error("Error occured while submitting proposal to Grants Gov", e);
            throw new S2SException(KeyConstants.ERROR_S2S_UNKNOWN,e.getMessage());
        }
    }

    /**
     * 
     * This method is to get Soap Port in case of multicampus
     * 
     * @param proposalNumber Proposal number.
     * @return ApplicantIntegrationPortType Soap port used for applicant integration.
     * @throws S2SException
     */
    protected ApplicantWebServicesPortType getApplicantIntegrationSoapPort(String proposalNumber) throws S2SException {

        Boolean multiCampusEnabled = s2SConfigurationService.getValueAsBoolean(ConfigurationConstants.MULTI_CAMPUS_ENABLED);
        return configureApplicantIntegrationSoapPort(proposalSiteService.getProposalDunsNumber(proposalNumber), multiCampusEnabled);
    }
    

    /**
     * 
     * This method is to get Soap Port
     * 
     * @return ApplicantIntegrationPortType Soap port used for applicant integration.
     * @throws S2SException
     */
    protected ApplicantWebServicesPortType configureApplicantIntegrationSoapPort(String alias,boolean mulitCampusEnabled)
                                                                                throws S2SException {
        System.clearProperty("java.protocol.handler.pkgs");
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress(getS2SSoapHost());
        factory.setServiceClass(ApplicantWebServicesPortType.class);
        // enabling mtom from V2 onwards
        // works for grants.gov but not for research.gov, get a mime related error.
        //Couldn't find MIME boundary: --uuid
        //disable for research.gov. This is not a big deal because submissions with attachments
        // go to grants.gov anyways and not to research.gov
        if (!StringUtils.equalsIgnoreCase(serviceHost, Constants.RESEARCH_GOV_SERVICE_HOST)) {
            Map<String,Object> properties = new HashMap<String, Object>();
            properties.put("mtom-enabled", Boolean.TRUE);
            factory.setProperties(properties);
        }
        
        ApplicantWebServicesPortType applicantWebService = (ApplicantWebServicesPortType)factory.create();
        Client client = ClientProxy.getClient(applicantWebService); 
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(0);
        httpClientPolicy.setReceiveTimeout(0);
        httpClientPolicy.setAllowChunking(false);
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        conduit.setClient(httpClientPolicy);
        TLSClientParameters tlsConfig = new TLSClientParameters();
        setPossibleCypherSuites(tlsConfig);
        configureKeyStoreAndTrustStore(tlsConfig, alias, mulitCampusEnabled);
        conduit.setTlsClientParameters(tlsConfig);
        return applicantWebService;
    }


    protected void setPossibleCypherSuites(TLSClientParameters tlsConfig) {
        FiltersType filters = new FiltersType();
        filters.getInclude().add("SSL_RSA_WITH_RC4_128_MD5");
        filters.getInclude().add("SSL_RSA_WITH_RC4_128_SHA");
        filters.getInclude().add("SSL_RSA_WITH_3DES_EDE_CBC_SHA");
        filters.getInclude().add(".*_EXPORT_.*");
        filters.getInclude().add(".*_EXPORT1024_.*");
        filters.getInclude().add(".*_WITH_DES_.*");
        filters.getInclude().add(".*_WITH_3DES_.*");
        filters.getInclude().add(".*_WITH_RC4_.*");
        filters.getInclude().add(".*_WITH_NULL_.*");
        filters.getInclude().add(".*_DH_anon_.*");

        tlsConfig.setDisableCNCheck(true); 
        tlsConfig.setCipherSuitesFilter(filters);
    }

    /**
     * This method is to confgiure KeyStore and Truststore for Grants.Gov webservice client
     * @param tlsConfig
     * @param alias
     * @param mulitCampusEnabled
     * @throws S2SException
     */
    protected void configureKeyStoreAndTrustStore(TLSClientParameters tlsConfig, String alias, boolean mulitCampusEnabled)
            throws S2SException {
        KeyStore keyStore = s2sCertificateReader.getKeyStore();
        KeyManagerFactory keyManagerFactory;
        try {
            keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            if (alias != null && mulitCampusEnabled) {
                KeyStore keyStoreAlias;
                keyStoreAlias = KeyStore.getInstance(s2sCertificateReader.getJksType());
                Certificate[] certificates = keyStore.getCertificateChain(alias);
                Key key = keyStore.getKey(alias, s2SConfigurationService.getValueAsString(s2sCertificateReader.getKeyStorePassword()).toCharArray());
                keyStoreAlias.load(null, null);
                keyStoreAlias.setKeyEntry(alias, key,
                        s2SConfigurationService.getValueAsString(s2sCertificateReader.getKeyStorePassword()).toCharArray(), certificates);
                keyManagerFactory.init(keyStoreAlias,
                        s2SConfigurationService.getValueAsString(s2sCertificateReader.getKeyStorePassword()).toCharArray());
            }else {
                keyManagerFactory.init(keyStore, s2SConfigurationService.getValueAsString(s2sCertificateReader.getKeyStorePassword()).toCharArray());
            }
            KeyManager[] km = keyManagerFactory.getKeyManagers();
            tlsConfig.setKeyManagers(km);
            KeyStore trustStore = s2sCertificateReader.getTrustStore();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);
            TrustManager[] tm = trustManagerFactory.getTrustManagers();
            tlsConfig.setTrustManagers(tm);
        }catch (NoSuchAlgorithmException e){
            LOG.error(e.getMessage(), e);
            throw new S2SException(KeyConstants.ERROR_KEYSTORE_CONFIG,e.getMessage());
        }catch (KeyStoreException e) {
            LOG.error(e.getMessage(), e);
            throw new S2SException(KeyConstants.ERROR_KEYSTORE_CONFIG,e.getMessage());
        }catch (UnrecoverableKeyException e) {
            LOG.error(e.getMessage(), e);
            throw new S2SException(KeyConstants.ERROR_KEYSTORE_CONFIG,e.getMessage());
        }catch (CertificateException e) {
            LOG.error(e.getMessage(), e);
            throw new S2SException(KeyConstants.ERROR_KEYSTORE_CONFIG,e.getMessage());
        }catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new S2SException(KeyConstants.ERROR_KEYSTORE_CONFIG,e.getMessage());
        }
    }

    /**
     * 
     * This method returns the Host URL for S2S web services
     * 
     * @return {@link String} host URL
     * @throws S2SException if unable to read property file
     */

    protected String getS2SSoapHost() throws S2SException {
        StringBuilder host = new StringBuilder();
        host.append(s2SConfigurationService.getValueAsString(getServiceHost()));
        String port = s2SConfigurationService.getValueAsString(getServicePort());
        if ((!host.toString().endsWith("/")) && (!port.startsWith("/"))) {
            host.append("/");
        }
        host.append(port);
        return host.toString();
    }


    public String getServiceHost() {
        return serviceHost;
    }

    public void setServiceHost(String serviceHost) {
        this.serviceHost = serviceHost;
    }

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }

    public S2SCertificateReader getS2sCertificateReader() {
        return s2sCertificateReader;
    }

    public void setS2sCertificateReader(S2SCertificateReader s2sCertificateReader) {
        this.s2sCertificateReader = s2sCertificateReader;
    }

    public S2SUtilService getS2SUtilService() {
        return s2SUtilService;
    }

    public void setS2SUtilService(S2SUtilService s2SUtilService) {
        this.s2SUtilService = s2SUtilService;
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }

    public ProposalSiteService getProposalSiteService() {
        return proposalSiteService;
    }

    public void setProposalSiteService(ProposalSiteService proposalSiteService) {
        this.proposalSiteService = proposalSiteService;
    }
}
