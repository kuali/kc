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
package org.kuali.coeus.propdev.impl.s2s.connect;

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
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.rice.krad.data.DataObjectService;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.activation.DataHandler;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.ws.WebServiceException;
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

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

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
     * @throws S2sCommunicationException
     * @see org.kuali.coeus.propdev.impl.s2s.connect.S2SConnectorService#getOpportunityList(java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public GetOpportunitiesResponse getOpportunityList(String cfdaNumber, String opportunityId, String competitionId)
            throws S2sCommunicationException {
        ApplicantWebServicesPortType port = configureApplicantIntegrationSoapPort(null,false);
        GetOpportunitiesRequest getOpportunityListRequest = new GetOpportunitiesRequest();
        
        getOpportunityListRequest.setCFDANumber(cfdaNumber);
        getOpportunityListRequest.setCompetitionID(competitionId);
        getOpportunityListRequest.setFundingOpportunityNumber(opportunityId);
        try {
            return port.getOpportunities(getOpportunityListRequest);
        }catch(SOAPFaultException soapFault){
            LOG.error("Error while getting list of opportunities", soapFault);
            if(soapFault.getMessage().contains("Connection refused")){
                throw new S2sCommunicationException(KeyConstants.ERROR_GRANTSGOV_OPP_SER_UNAVAILABLE,soapFault.getMessage());
            }else{
                throw new S2sCommunicationException(KeyConstants.ERROR_S2S_UNKNOWN,soapFault.getMessage());
            }
        }catch (ErrorMessage|WebServiceException e) {
            LOG.error("Error while getting list of opportunities", e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_UNKNOWN, e.getMessage());
        }
    }

    /**
     * This method is to get status of the submitted application.
     * 
     * @param ggTrackingId grants gov tracking id for the proposal.
     * @param proposalNumber Proposal number.
     * @return GetApplicationStatusDetailResponse status of the submitted application.
     * @throws S2sCommunicationException
     * @see org.kuali.coeus.propdev.impl.s2s.connect.S2SConnectorService#getApplicationStatusDetail(java.lang.String, java.lang.String)
     */
    public GetApplicationStatusDetailResponse getApplicationStatusDetail(String ggTrackingId, String proposalNumber)
            throws S2sCommunicationException {
        ApplicantWebServicesPortType port = getApplicantIntegrationSoapPort(proposalNumber);
        GetApplicationStatusDetailRequest applicationStatusDetailRequest = new GetApplicationStatusDetailRequest();
        applicationStatusDetailRequest.setGrantsGovTrackingNumber(ggTrackingId);
        try {
            return port.getApplicationStatusDetail(applicationStatusDetailRequest);
        }
        catch (ErrorMessage|WebServiceException e) {
            LOG.error("Error while getting proposal submission status details", e);
            throw new S2sCommunicationException(KeyConstants.ERROR_GRANTSGOV_SERVER_STATUS_REFRESH,e.getMessage());
        }
    }

    /**
     * This method is to get Application List from grants.gov for opportunityId, cfdaNumber and proposalNumber
     * 
     * @param opportunityId of the opportunity.
     * @param cfdaNumber of the opportunity.
     * @param proposalNumber proposal number.
     * @return GetApplicationListResponse application list.
     * @throws S2sCommunicationException
     * @see org.kuali.coeus.propdev.impl.s2s.connect.S2SConnectorService#getApplicationList(java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public GetApplicationListResponse getApplicationList(String opportunityId, String cfdaNumber, String proposalNumber)
            throws S2sCommunicationException {
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
        catch (ErrorMessage|WebServiceException e) {
            LOG.error("Error occured while fetching application list", e);
            throw new S2sCommunicationException(KeyConstants.ERROR_GRANTSGOV_SERVER_APPLICATION_LIST_REFRESH,e.getMessage());
        }
    }

    /**
     * This method is to submit a proposal to grants.gov
     * 
     * @param xmlText xml format of the form object.
     * @param attachments attachments of the proposal.
     * @param proposalNumber proposal number.
     * @return SubmitApplicationResponse corresponding to the input parameters passed.
     * @throws S2sCommunicationException
     * @see org.kuali.coeus.propdev.impl.s2s.connect.S2SConnectorService#submitApplication(java.lang.String, java.util.Map, java.lang.String)
     */
    public SubmitApplicationResponse submitApplication(String xmlText, 
                    Map<String, DataHandler> attachments, String proposalNumber)
            throws S2sCommunicationException {
        ApplicantWebServicesPortType port = getApplicantIntegrationSoapPort(proposalNumber);
        SubmitApplicationRequest request = new SubmitApplicationRequest();

        for (Map.Entry<String, DataHandler> entry : attachments.entrySet()) {
            DataHandler dataHandler = entry.getValue();
            Attachment attachment = new Attachment();
            attachment.setFileContentId(entry.getKey());
            attachment.setFileDataHandler(dataHandler);
            request.getAttachment().add(attachment);
        }
        request.setGrantApplicationXML(xmlText);
        
        try {
            return port.submitApplication(request);
        }catch (ErrorMessage e) {
            LOG.error("Error occured while submitting proposal to Grants Gov", e);
            throw new S2sCommunicationException(KeyConstants.ERROR_GRANTSGOV_SERVER_SUBMIT_APPLICATION,e.getMessage());
        }catch(WebServiceException e){
            LOG.error("Error occured while submitting proposal to Grants Gov", e);
            throw new S2sCommunicationException(KeyConstants.ERROR_S2S_UNKNOWN,e.getMessage());
        }
    }

    /**
     * 
     * This method is to get Soap Port in case of multicampus
     * 
     * @param proposalNumber Proposal number.
     * @return ApplicantIntegrationPortType Soap port used for applicant integration.
     * @throws S2sCommunicationException
     */
    protected ApplicantWebServicesPortType getApplicantIntegrationSoapPort(String proposalNumber) throws S2sCommunicationException {
        DevelopmentProposal pdDoc = dataObjectService.find(
                DevelopmentProposal.class, proposalNumber);
        Boolean multiCampusEnabled = s2SConfigurationService.getValueAsBoolean(ConfigurationConstants.MULTI_CAMPUS_ENABLED);
        return configureApplicantIntegrationSoapPort(pdDoc.getApplicantOrganization().getOrganization().getDunsNumber(), multiCampusEnabled);
    }
    

    /**
     * 
     * This method is to get Soap Port
     * 
     * @return ApplicantIntegrationPortType Soap port used for applicant integration.
     * @throws S2sCommunicationException
     */
    protected ApplicantWebServicesPortType configureApplicantIntegrationSoapPort(String alias,boolean mulitCampusEnabled)
                                                                                throws S2sCommunicationException {
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
            Map<String,Object> properties = new HashMap<>();
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
        filters.getInclude().add("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
        filters.getInclude().add("TLS_DHE_RSA_WITH_AES_128_CBC_SHA");
        filters.getInclude().add("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256");
        filters.getInclude().add(".*_EXPORT_.*");
        filters.getInclude().add(".*_EXPORT1024_.*");
        filters.getInclude().add(".*_WITH_DES_.*");
        filters.getInclude().add(".*_WITH_3DES_.*");
        filters.getInclude().add(".*_WITH_RC4_.*");
        filters.getInclude().add(".*_WITH_NULL_.*");
        filters.getInclude().add(".*_DH_anon_.*");

        tlsConfig.setDisableCNCheck(true);

        String certAlgorithm = getS2SConfigurationService().getValueAsString("s2s.cert.algorithm");
        if(certAlgorithm!=null){
            tlsConfig.setSecureSocketProtocol(certAlgorithm);
        }

        tlsConfig.setCipherSuitesFilter(filters);
    }

    /**
     * This method is to confgiure KeyStore and Truststore for Grants.Gov webservice client
     * @param tlsConfig
     * @param alias
     * @param mulitCampusEnabled
     * @throws S2sCommunicationException
     */
    protected void configureKeyStoreAndTrustStore(TLSClientParameters tlsConfig, String alias, boolean mulitCampusEnabled)
            throws S2sCommunicationException {
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
        }catch (NoSuchAlgorithmException|KeyStoreException|UnrecoverableKeyException|CertificateException|IOException e){
            LOG.error(e.getMessage(), e);
            throw new S2sCommunicationException(KeyConstants.ERROR_KEYSTORE_CONFIG,e.getMessage());
        }
    }

    /**
     * 
     * This method returns the Host URL for S2S web services
     * 
     * @return {@link String} host URL
     * @throws S2sCommunicationException if unable to read property file
     */

    protected String getS2SSoapHost() throws S2sCommunicationException {
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

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }
}
