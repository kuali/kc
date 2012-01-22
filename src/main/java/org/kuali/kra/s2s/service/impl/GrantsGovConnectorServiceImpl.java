/*
kc * Copyright 2005-2010 The Kuali Foundation.
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

import gov.grants.apply.webservices.applicantintegrationservices_v1.GetApplicationListRequest;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetApplicationListResponse;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetApplicationStatusDetailRequest;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetApplicationStatusDetailResponse;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetOpportunityListRequest;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetOpportunityListResponse;
import gov.grants.apply.webservices.applicantintegrationservices_v1.SubmitApplicationRequest;
import gov.grants.apply.webservices.applicantintegrationservices_v1.SubmitApplicationResponse;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetApplicationListRequest.ApplicationFilter;
import gov.grants.apply.webservices.applicantintegrationservices_v1_0.ApplicantIntegrationPortType;
import gov.grants.apply.webservices.applicantintegrationservices_v1_0.ErrorMessage;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.attachment.AttachmentImpl;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.AttachmentOutInterceptor;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.message.Attachment;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.service.GrantsGovConnectorService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class is used to make web service call to grants.gov
 */
public class GrantsGovConnectorServiceImpl implements GrantsGovConnectorService {
    private static final String JKS_TYPE = "JKS";
    private static final Log LOG = LogFactory.getLog(GrantsGovConnectorServiceImpl.class);
    private S2SUtilService s2SUtilService;
    private BusinessObjectService businessObjectService;
    private static final String KEY_PROPOSAL_NUMBER = "proposalNumber";
    private static final String MULTI_CAMPUS_ENABLED = "MULTI_CAMPUS_ENABLED";
    private static final String MULTI_CAMPUS_ENABLED_VALUE = "1";
    private static final String KEY_OPPORTUNITY_ID = "OpportunityID";
    private static final String KEY_CFDA_NUMBER = "CFDANumber";
    private static final String KEY_SUBMISSION_TITLE = "SubmissionTitle";
    
    private static final String KEYSTORE_PASSWORD = "s2s.keystore.password";
    public static final String GRANTS_GOV_HOST = "grants.gov.s2s.host";
    public static final String GRANTS_GOV_PORT = "grants.gov.s2s.port";

    /**
     * This method is to get Opportunity List for the given cfda number,opportunity Id and competition Id from the grants guv. It
     * sets the given parameters on {@link GetOpportunityListRequest} object and passes it to the web service.
     * 
     * @param cfdaNumber of the opportunity.
     * @param opportunityId parameter for the opportunity.
     * @param competitionId parameter for the opportunity.
     * @return GetOpportunityListResponse available list of opportunities applicable for the given cfda number,opportunity Id and
     *         competition Id.
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.GrantsGovConnectorService#getOpportunityList(java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public GetOpportunityListResponse getOpportunityList(String cfdaNumber, String opportunityId, String competitionId)
            throws S2SException {
        ApplicantIntegrationPortType port = configureApplicantIntegrationSoapPort(null,false);
        GetOpportunityListRequest getOpportunityListRequest = new GetOpportunityListRequest();
        getOpportunityListRequest.setCFDANumber(cfdaNumber);
        getOpportunityListRequest.setCompetitionID(competitionId);
        getOpportunityListRequest.setOpportunityID(opportunityId);
        try {
            return port.getOpportunityList(getOpportunityListRequest);
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
     * @see org.kuali.kra.s2s.service.GrantsGovConnectorService#getApplicationStatusDetail(java.lang.String, java.lang.String)
     */
    public GetApplicationStatusDetailResponse getApplicationStatusDetail(String ggTrackingId, String proposalNumber)
            throws S2SException {
        ApplicantIntegrationPortType port = getApplicantIntegrationSoapPort(proposalNumber);
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
     * @see org.kuali.kra.s2s.service.GrantsGovConnectorService#getApplicationList(java.lang.String, java.lang.String,
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
        applicationFilter = new ApplicationFilter();
        applicationFilter.setFilter(KEY_CFDA_NUMBER);
        applicationFilter.setFilterValue(cfdaNumber);
        filterList.add(applicationFilter);
        applicationFilter = new ApplicationFilter();
        applicationFilter.setFilter(KEY_SUBMISSION_TITLE);
        applicationFilter.setFilterValue(proposalNumber);
        filterList.add(applicationFilter);
        ApplicantIntegrationPortType port = getApplicantIntegrationSoapPort(proposalNumber);
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
     * @see org.kuali.kra.s2s.service.GrantsGovConnectorService#submitApplication(java.lang.String, java.util.Map, java.lang.String)
     */
    public SubmitApplicationResponse submitApplication(String xmlText, 
                    Map<String, DataHandler> attachments, String proposalNumber)
            throws S2SException {
        ApplicantIntegrationPortType port = getApplicantIntegrationSoapPort(proposalNumber);
        Client client = ClientProxy.getClient(port);
        Iterator<String> it = attachments.keySet().iterator();
        final List<Attachment> atts = new ArrayList<Attachment>();
        while (it.hasNext()) {
            String key = it.next();
            Attachment attachment = new AttachmentImpl(key,attachments.get(key));
            atts.add(attachment);
        }
        List<Interceptor<? extends Message>> outInterceptors = client.getOutInterceptors();
        outInterceptors.add(new AttachmentOutInterceptor(){
            @Override
            public void handleMessage(org.apache.cxf.message.Message message) {
                message.setAttachments(atts);
            };
        });
        SubmitApplicationRequest request = new SubmitApplicationRequest();
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
    protected ApplicantIntegrationPortType getApplicantIntegrationSoapPort(String proposalNumber) throws S2SException {
        Map<String, String> proposalMap = new HashMap<String, String>();
        proposalMap.put(KEY_PROPOSAL_NUMBER, proposalNumber);
        DevelopmentProposal pdDoc = (DevelopmentProposal) businessObjectService.findByPrimaryKey(
                DevelopmentProposal.class, proposalMap);
        String multiCampusEnabledStr = s2SUtilService.getParameterValue(MULTI_CAMPUS_ENABLED);
        boolean mulitCampusEnabled = multiCampusEnabledStr.equals(MULTI_CAMPUS_ENABLED_VALUE) ? true : false;
        return configureApplicantIntegrationSoapPort(pdDoc.getApplicantOrganization().getOrganization().getDunsNumber(),
                mulitCampusEnabled);
    }
    

    /**
     * 
     * This method is to get Soap Port
     * 
     * @return ApplicantIntegrationPortType Soap port used for applicant integration.
     * @throws S2SException
     */
    protected ApplicantIntegrationPortType configureApplicantIntegrationSoapPort(String alias,boolean mulitCampusEnabled)
                                                                                throws S2SException {
        System.clearProperty("java.protocol.handler.pkgs");
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        
        factory.setAddress(getS2SSoapHost());
        factory.setServiceClass(ApplicantIntegrationPortType.class);
        ApplicantIntegrationPortType applicantWebService = (ApplicantIntegrationPortType)factory.create();
      
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

    /**
     * This method...
     * @param tlsConfig
     */
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
        KeyStore keyStore = S2SCertificateReader.getKeyStore();
        KeyManagerFactory keyManagerFactory;
        try {
            keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            if (alias != null && mulitCampusEnabled) {
                KeyStore keyStoreAlias;
                keyStoreAlias = KeyStore.getInstance(JKS_TYPE);
                Certificate[] certificates = keyStore.getCertificateChain(alias);
                Key key = keyStore.getKey(alias, s2SUtilService.getProperty(KEYSTORE_PASSWORD).toCharArray());
                keyStoreAlias.load(null, null);
                keyStoreAlias.setKeyEntry(alias, key, 
                        s2SUtilService.getProperty(KEYSTORE_PASSWORD).toCharArray(), certificates);
                keyManagerFactory.init(keyStoreAlias, 
                        s2SUtilService.getProperty(KEYSTORE_PASSWORD).toCharArray());
            }else {
                keyManagerFactory.init(keyStore, s2SUtilService.getProperty(KEYSTORE_PASSWORD).toCharArray());
            }
            KeyManager[] km = keyManagerFactory.getKeyManagers();
            tlsConfig.setKeyManagers(km);
            KeyStore trustStore = S2SCertificateReader.getTrustStore();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);
            TrustManager[] tm = trustManagerFactory.getTrustManagers();
            tlsConfig.setTrustManagers(tm);
        }catch (NoSuchAlgorithmException e){
            LOG.error(e);
            throw new S2SException(KeyConstants.ERROR_KEYSTORE_CONFIG,e.getMessage());
        }catch (KeyStoreException e) {
            LOG.error(e);
            throw new S2SException(KeyConstants.ERROR_KEYSTORE_CONFIG,e.getMessage());
        }catch (UnrecoverableKeyException e) {
            LOG.error(e);
            throw new S2SException(KeyConstants.ERROR_KEYSTORE_CONFIG,e.getMessage());
        }catch (CertificateException e) {
            LOG.error(e);
            throw new S2SException(KeyConstants.ERROR_KEYSTORE_CONFIG,e.getMessage());
        }catch (IOException e) {
            LOG.error(e);
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
        host.append(s2SUtilService.getProperty(GRANTS_GOV_HOST));
        String port = s2SUtilService.getProperty(GRANTS_GOV_PORT);
        if ((!host.toString().endsWith("/")) && (!port.startsWith("/"))) {
            host.append("/");
        }
        host.append(port);
        return host.toString();
    }

    /**
     * Sets the s2sUtilService attribute value.
     * 
     * @param generatorUtilService The s2sUtilService to set.
     */
    public void setS2SUtilService(S2SUtilService s2SUtilService) {
        this.s2SUtilService = s2SUtilService;
    }

    /**
     * This method is to set businessObjectService
     * 
     * @param businessObjectService(BusinessObjectService)
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Gets the s2SUtilService attribute. 
     * @return Returns the s2SUtilService.
     */
    public S2SUtilService getS2SUtilService() {
        return s2SUtilService;
    }
}
