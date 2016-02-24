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
package org.kuali.coeus.propdev.impl.s2s;

import gov.grants.apply.services.applicantwebservices_v2.GetApplicationListResponse;
import gov.grants.apply.services.applicantwebservices_v2.GetApplicationStatusDetailResponse;
import gov.grants.apply.services.applicantwebservices_v2.GetOpportunitiesResponse;
import gov.grants.apply.services.applicantwebservices_v2.SubmitApplicationResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsContract;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsService;
import org.kuali.coeus.instprop.api.sponsor.InstPropSponsorService;
import org.kuali.coeus.propdev.api.s2s.*;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.s2s.connect.OpportunitySchemaParserService;
import org.kuali.coeus.propdev.impl.s2s.connect.S2SConnectorService;
import org.kuali.coeus.propdev.impl.s2s.connect.S2sCommunicationException;
import org.kuali.coeus.s2sgen.api.generate.FormGenerationResult;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.coeus.s2sgen.api.generate.AttachmentData;
import org.kuali.coeus.s2sgen.api.generate.FormGeneratorService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

@Component("s2sSubmissionService")
public class S2sSubmissionServiceImpl implements S2sSubmissionService {

    private static final Log LOG = LogFactory.getLog(S2sSubmissionServiceImpl.class);
    private static final String GRANTS_GOV_STATUS_ERROR = "ERROR";
    private static final String ERROR_MESSAGE = "Exception Occurred";

    @Autowired
    @Qualifier("proposalAdminDetailsService")
    private ProposalAdminDetailsService proposalAdminDetailsService;

    @Autowired
    @Qualifier("instPropSponsorService")
    private InstPropSponsorService instPropSponsorService;

    @Autowired
    @Qualifier("s2sOpportunityService")
    private S2sOpportunityService s2sOpportunityService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("formGeneratorService")
    private FormGeneratorService s2SService;

    @Autowired
    @Qualifier("s2sProviderService")
    private S2sProviderService s2sProviderService;

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

    @Autowired
    @Qualifier("opportunitySchemaParserService")
    private OpportunitySchemaParserService opportunitySchemaParserService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;
    
    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    /**
     *
     * This method is used to get the application status details.
     *
     * @param ggTrackingId
     *            grants gov tracking id for the application.
     * @param proposalNumber
     *            Proposal number.
     * @throws S2sCommunicationException
     */
    @Override
    public String getStatusDetails(String ggTrackingId, String proposalNumber)
            throws S2sCommunicationException {
        if (StringUtils.isNotBlank(proposalNumber) && proposalNumber.contains(Constants.COLON)) {
            proposalNumber = StringUtils.split(proposalNumber, Constants.COLON)[0];
        }

        S2sOpportunityContract s2sOpportunity = s2sOpportunityService.findS2SOpportunityByProposalNumber(proposalNumber);

        Object statusDetail = null;
        GetApplicationStatusDetailResponse applicationStatusDetailResponse;
        applicationStatusDetailResponse = getS2sConnectorService(s2sOpportunity)
                .getApplicationStatusDetail(ggTrackingId, proposalNumber);
        if (applicationStatusDetailResponse != null) {
            statusDetail = applicationStatusDetailResponse.getDetailedStatus();
        }
        return statusDetail != null ? statusDetail.toString() : StringUtils.EMPTY;
    }

    /**
     * This method checks if status on grants.gov has changed since last check
     * and returns the status.
     *
     * @param pdDoc
     * @param appSubmission
     * @return status
     * @throws S2sCommunicationException
     */
    @Override
    public boolean checkForSubmissionStatusChange(
            ProposalDevelopmentDocument pdDoc, S2sAppSubmission appSubmission)
            throws S2sCommunicationException {
        boolean statusChanged = false;
        GetApplicationStatusDetailResponse applicationStatusDetailResponse = getS2sConnectorService(pdDoc.getDevelopmentProposal().getS2sOpportunity())
                .getApplicationStatusDetail(appSubmission.getGgTrackingId(),
                        pdDoc.getDevelopmentProposal().getProposalNumber());
        if (applicationStatusDetailResponse != null
                && applicationStatusDetailResponse.getDetailedStatus() != null) {
            String statusDetail = applicationStatusDetailResponse
                    .getDetailedStatus().toString();
            String statusStr = statusDetail.toUpperCase().indexOf(
                    GRANTS_GOV_STATUS_ERROR) == -1 ? statusDetail
                    : S2sAppSubmissionConstants.STATUS_GRANTS_GOV_SUBMISSION_ERROR;
            statusChanged = !appSubmission.getStatus().equalsIgnoreCase(
                    statusStr);
            appSubmission.setComments(statusDetail);
            appSubmission.setStatus(statusStr);
        } else {
            appSubmission
                    .setComments(S2sAppSubmissionConstants.STATUS_NO_RESPONSE_FROM_GRANTS_GOV);
        }
        return statusChanged;
    }

    /**
     * This method checks for the status of submission for the given
     * {@link ProposalDevelopmentDocument} on Grants.gov
     *
     * @param pdDoc
     *            for which status has to be checked
     * @return boolean, <code>true</code> if status has changed, false
     *         otherwise
     * @throws S2sCommunicationException
     */
    @Override
    public boolean refreshGrantsGov(ProposalDevelopmentDocument pdDoc)
            throws S2sCommunicationException {
        GetApplicationListResponse applicationListResponse = fetchApplicationListResponse(pdDoc);
        if (applicationListResponse != null) {
            saveGrantsGovStatus(pdDoc, applicationListResponse);
        }
        return true;
    }

    /**
     *
     * This method fetches the status from Grants Gov and saves in case status
     * is modified
     *
     * @param pdDoc
     *            {@link ProposalDevelopmentDocument}
     * @param applicationListResponse
     *            {@link GetApplicationListResponse} response from Grants Gov
     * @throws S2sCommunicationException
     */
    protected void saveGrantsGovStatus(ProposalDevelopmentDocument pdDoc,
                                       GetApplicationListResponse applicationListResponse)
            throws S2sCommunicationException {
        S2sAppSubmission appSubmission = null;
        List<S2sAppSubmission> appSubmissionList = pdDoc
                .getDevelopmentProposal().getS2sAppSubmission();
        int submissionNo = 0;
        for (S2sAppSubmission s2AppSubmission : appSubmissionList) {
            if (s2AppSubmission.getSubmissionNumber() > submissionNo) {
                appSubmission = s2AppSubmission;
                submissionNo = s2AppSubmission.getSubmissionNumber();
            }
        }

        if (appSubmission != null) {
            boolean statusChanged = false;
            if (applicationListResponse.getApplicationInfo() == null
                    || applicationListResponse.getApplicationInfo()
                    .size() == 0) {
                statusChanged = checkForSubmissionStatusChange(pdDoc,
                        appSubmission);
            } else {
                int appSize = applicationListResponse
                        .getApplicationInfo().size();
                GetApplicationListResponse.ApplicationInfo ggApplication = applicationListResponse
                        .getApplicationInfo().get(appSize - 1);
                if (ggApplication != null) {
                    statusChanged = !appSubmission.getStatus()
                            .equalsIgnoreCase(
                                    ggApplication
                                            .getGrantsGovApplicationStatus()
                                            .value());
                    populateAppSubmission(pdDoc, appSubmission, ggApplication);
                }
            }
            if (statusChanged) {
               getDataObjectService().save(appSubmission);
            }
        }

    }

    /**
     * This method populates the {@link S2sAppSubmission} BO with details from
     * {@link ProposalDevelopmentDocument}
     *
     * @param appSubmission
     * @param ggApplication
     */
    @Override
    public void populateAppSubmission(ProposalDevelopmentDocument pdDoc, S2sAppSubmission appSubmission,
                                      GetApplicationListResponse.ApplicationInfo ggApplication) {
        appSubmission.setGgTrackingId(ggApplication
                .getGrantsGovTrackingNumber());
        appSubmission
                .setReceivedDate(new Timestamp(ggApplication
                        .getReceivedDateTime().toGregorianCalendar()
                        .getTimeInMillis()));
        appSubmission.setStatus(ggApplication.getGrantsGovApplicationStatus()
                .toString());
        appSubmission.setLastModifiedDate(new Timestamp(ggApplication
                .getStatusDateTime().toGregorianCalendar().getTimeInMillis()));
        appSubmission.setAgencyTrackingId(ggApplication
                .getAgencyTrackingNumber());
        if (ggApplication.getAgencyTrackingNumber() != null
                && ggApplication.getAgencyTrackingNumber().length() > 0) {
            appSubmission
                    .setComments(S2sAppSubmissionConstants.STATUS_AGENCY_TRACKING_NUMBER_ASSIGNED);
            populateSponsorProposalId(pdDoc, appSubmission);
        } else {
            appSubmission.setComments(ggApplication
                    .getGrantsGovApplicationStatus().toString());
        }
    }

    /**
     * This method fetches the application list from Grants.gov for a given
     * proposal
     *
     * @param pdDoc
     * @return {@link GetApplicationListResponse}
     * @throws S2sCommunicationException
     */
    public GetApplicationListResponse fetchApplicationListResponse(
            ProposalDevelopmentDocument pdDoc) throws S2sCommunicationException {
        S2sOpportunityContract s2sOpportunity =
                s2sOpportunityService.findS2SOpportunityByProposalNumber(pdDoc.getDevelopmentProposal().getProposalNumber());

        GetApplicationListResponse applicationListResponse = getS2sConnectorService(s2sOpportunity)
                .getApplicationList(s2sOpportunity.getOpportunityId(),
                        s2sOpportunity.getCfdaNumber(), s2sOpportunity
                                .getProposalNumber()
                );
        return applicationListResponse;
    }

    /**
     *
     * Takes the appSubmission and proposal and if a federal tracking id has been specified, will
     * set on both the proposal development doc and the related institutional proposal doc
     * if there is not a sponsor proposal id already.
     * @param pdDoc
     * @param appSubmission
     */
    protected void populateSponsorProposalId(ProposalDevelopmentDocument pdDoc, S2sAppSubmission appSubmission) {
        if (StringUtils.isNotBlank(appSubmission.getAgencyTrackingId())) {
            if (StringUtils.isBlank(pdDoc.getDevelopmentProposal().getSponsorProposalNumber())) {
                pdDoc.getDevelopmentProposal().setSponsorProposalNumber(appSubmission.getAgencyTrackingId());
                getBusinessObjectService().save(pdDoc.getDevelopmentProposal());
            }

            //find and populate the inst proposal sponsor proposal id as well
            Collection<? extends ProposalAdminDetailsContract> proposalAdminDetails = proposalAdminDetailsService.findProposalAdminDetailsByPropDevNumber(pdDoc.getDevelopmentProposal().getProposalNumber());

            for(ProposalAdminDetailsContract pad : proposalAdminDetails){
            	if (pad.getInstProposalId() != null) {
            		instPropSponsorService.updateSponsorProposalNumber(pad.getInstProposalId(), appSubmission.getAgencyTrackingId());
            	}
            }

        }
    }

    /**
     * This method is used to submit forms to Grants.gov. It generates forms for
     * a given {@link ProposalDevelopmentDocument}, validates and then submits
     * the forms
     *
     * @param pdDoc
     *            Proposal Development Document.
     * @return true if submitted false otherwise.
     * @throws S2sCommunicationException
     */
    public FormGenerationResult submitApplication(ProposalDevelopmentDocument pdDoc)
            throws S2sCommunicationException {

        final FormGenerationResult result = s2SService.generateAndValidateForms(pdDoc);
        if (result.isValid()) {

            Map<String, DataHandler> attachments = new HashMap<String, DataHandler>();
            List<S2sAppAttachments> s2sAppAttachmentList = new ArrayList<S2sAppAttachments>();
            DataHandler attachmentFile;
            for (AttachmentData attachmentData : result.getAttachments()) {
                attachmentFile = new DataHandler(new ByteArrayDataSource(
                        attachmentData.getContent(), attachmentData
                        .getContentType()));

                attachments.put(attachmentData.getContentId(), attachmentFile);
                S2sAppAttachments appAttachments = new S2sAppAttachments();
                appAttachments.setContentId(attachmentData.getContentId());
                appAttachments.setProposalNumber(pdDoc.getDevelopmentProposal()
                        .getProposalNumber());
                s2sAppAttachmentList.add(appAttachments);
            }
            S2sAppSubmission appSubmission = new S2sAppSubmission();
            appSubmission.setStatus(S2sAppSubmissionConstants.GRANTS_GOV_STATUS_MESSAGE);
            appSubmission.setComments(S2sAppSubmissionConstants.GRANTS_GOV_COMMENTS_MESSAGE);
            SubmitApplicationResponse response = null;

            S2sOpportunity s2sOpportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
            S2SConnectorService connectorService = getS2sConnectorService(s2sOpportunity);

            response = connectorService.submitApplication(
                    result.getApplicationXml(), attachments, pdDoc
                            .getDevelopmentProposal().getProposalNumber());
            appSubmission.setStatus(S2sAppSubmissionConstants.GRANTS_GOV_SUBMISSION_MESSAGE);
            saveSubmissionDetails(pdDoc, appSubmission, response,
                    result.getApplicationXml(), s2sAppAttachmentList);
            result.setValid(true);
        }
        return result;
    }

    /**
     * This method convert GetOpportunityListResponse to ArrayList&lt;OpportunityInfo&gt;
     *
     * @param resList
     *            {GetOpportunityListResponse}
     * @return ArrayList&lt;OpportunityInfo&gt; containing all form information
     */

    protected ArrayList<S2sOpportunity> convertToArrayList(String source,
                                                           GetOpportunitiesResponse resList) {
        ArrayList<S2sOpportunity> convList = new ArrayList<S2sOpportunity>();
        if (resList == null || resList.getOpportunityInfo() == null) {
            return convList;
        }
        for (GetOpportunitiesResponse.OpportunityInfo opportunityInfo : resList.getOpportunityInfo()) {
            convList.add(convert2S2sOpportunity(source, opportunityInfo));
        }
        return convList;
    }

    /**
     * This method convert OpportunityInformationType to OpportunityInfo
     *
     * @param providerCode
     *
     * @return OpportunityInfo containing Opportunity information corresponding
     *         to the OpportunityInformationType object.
     */
    protected S2sOpportunity convert2S2sOpportunity(
            String providerCode, GetOpportunitiesResponse.OpportunityInfo oppInfo) {

        S2sOpportunity s2Opportunity = new S2sOpportunity();
        s2Opportunity.setCfdaNumber(oppInfo.getCFDANumber());
        s2Opportunity
                .setClosingDate(oppInfo.getClosingDate() == null ? null
                        : oppInfo.getClosingDate()
                        .toGregorianCalendar());

        s2Opportunity.setCompetetionId(oppInfo.getCompetitionID());
        s2Opportunity.setInstructionUrl(oppInfo.getInstructionsURL());
        s2Opportunity
                .setOpeningDate(oppInfo.getOpeningDate() == null ? null
                        : oppInfo.getOpeningDate()
                        .toGregorianCalendar());

        s2Opportunity.setOpportunityId(oppInfo.getFundingOpportunityNumber());
        s2Opportunity.setOpportunityTitle(oppInfo.getFundingOpportunityTitle());
        s2Opportunity.setSchemaUrl(oppInfo.getSchemaURL());
        s2Opportunity.setProviderCode(providerCode);
        s2Opportunity.setOfferingAgency(oppInfo.getOfferingAgency());
        s2Opportunity.setAgencyContactInfo(oppInfo.getAgencyContactInfo());
        s2Opportunity.setCfdaDescription(oppInfo.getCFDADescription());
        s2Opportunity.setMultiProject(oppInfo.isIsMultiProject());

        return s2Opportunity;
    }

    /**
     * This method is to find the list of available opportunities for a given
     * cfda number, opportunity ID and competition ID.
     *
     * @param cfdaNumber
     *            of the opportunity.
     * @param opportunityId
     *            parameter for the opportunity.
     * @param competitionId
     *            parameter for the opportunity.
     * @return List&lt;S2sOpportunity&gt; a list containing the available
     *         opportunities for the corresponding parameters.
     * @throws S2sCommunicationException
     */
    @Override
    public List<S2sOpportunity> searchOpportunity(String providerCode, String cfdaNumber,
                                                  String opportunityId, String competitionId) throws S2sCommunicationException {

        //The OpportunityID and CompetitionID element definitions were changed from a string with
        //a length between 1 and 100 (StringMin1Max100Type) to an uppercase alphanumeric value with a maximum length
        //of 40 characters ([A-Z0-9\-]{1,40}).
        opportunityId = StringUtils.upperCase(opportunityId);
        opportunityId = StringUtils.isBlank(opportunityId) ? null : opportunityId;

        cfdaNumber = StringUtils.isBlank(cfdaNumber) ? null : cfdaNumber;

        competitionId = StringUtils.upperCase(competitionId);
        competitionId = StringUtils.isBlank(competitionId) ? null : competitionId;

        S2sProviderContract provider = s2sProviderService.findS2SProviderByCode(providerCode);
        if (provider == null) {
            throw new S2sCommunicationException("An invalid provider code was provided when attempting to search for opportunities.");
        }
        S2SConnectorService connectorService = KcServiceLocator.getService(provider.getConnectorServiceName());
        if (connectorService == null) {
            throw new S2sCommunicationException("The connector service '" + provider.getConnectorServiceName() + "' required by '" + provider.getDescription() + "' S2S provider is not configured.");
        }
        List<S2sOpportunity> s2sOpportunityList = convertToArrayList(provider.getCode(),
                connectorService.getOpportunityList(cfdaNumber, opportunityId, competitionId));
        return s2sOpportunityList;
    }

    /**
     *
     * This method returns the list of forms for a given opportunity
     *
     * @param opportunity
     * @return {@link List} of {@link S2sOppForms} which are included in the
     *         given {@link S2sOpportunity}
     * @throws S2sCommunicationException
     */
    @Override
    public List<S2sOppForms> parseOpportunityForms(S2sOpportunity opportunity) throws S2sCommunicationException{
        setOpportunityContent(opportunity);
        return opportunitySchemaParserService.getForms(opportunity.getProposalNumber(),opportunity.getSchemaUrl());
    }

    public List<String> setMandatoryForms(DevelopmentProposal proposal, S2sOpportunity s2sOpportunity) {
        List<String> missingMandatoryForms = new ArrayList<String>();
        s2sOpportunity.setS2sProvider(getDataObjectService().find(S2sProvider.class, s2sOpportunity.getProviderCode()));
        List<S2sOppForms> s2sOppForms = parseOpportunityForms(s2sOpportunity);
        if (s2sOppForms != null) {
            for (S2sOppForms s2sOppForm : s2sOppForms) {
                if (s2sOppForm.getMandatory() && !s2sOppForm.getAvailable()) {
                    missingMandatoryForms.add(s2sOppForm.getFormName());
                }
            }
        }
        if (CollectionUtils.isEmpty(missingMandatoryForms)) {
            Collections.sort(s2sOppForms, new Comparator<S2sOppForms>() {
                public int compare(S2sOppForms arg0, S2sOppForms arg1) {
                    int result = arg0.getMandatory().compareTo(arg1.getMandatory()) * -1;
                    if (result == 0) {
                        result = arg0.getFormName().compareTo(arg1.getFormName());
                    }
                    return result;
                }
            });
            s2sOpportunity.setS2sOppForms(s2sOppForms);
        }
        return missingMandatoryForms;
    }

    public void setOpportunityContent(S2sOpportunity opportunity) {
        String opportunityContent = getOpportunityContent(opportunity.getSchemaUrl());
        opportunity.setOpportunity(opportunityContent);
    }

    private String getOpportunityContent(String schemaUrl) throws S2sCommunicationException{
        String opportunity = "";
        InputStream is  = null;
        BufferedInputStream br = null;
        try{
            URL url = new URL(schemaUrl);
            is = (InputStream)url.getContent();
            br = new BufferedInputStream(is);
            byte bufContent[] = new byte[is.available()];
            br.read(bufContent);
            opportunity = new String(bufContent);
        }catch (Exception e) {
            LOG.error(ERROR_MESSAGE, e);
            throw new S2sCommunicationException(KeyConstants.ERROR_GRANTSGOV_FORM_SCHEMA_NOT_FOUND,e.getMessage(),schemaUrl);
        }finally{
            try {
                if(is!=null) is.close();
                if(br!=null) br.close();
            }catch (IOException e) {
                LOG.error("Cannot close stream after fetching the content of opportinity schema", e);
            }
        }
        return opportunity;
    }

    /**
     *
     * This method saves the submission details after successful submission of
     * proposal
     *
     * @param pdDoc
     *            {@link ProposalDevelopmentDocument} that was submitted
     * @param appSubmission
     *            {@link S2sAppSubmission} submission details of proposal
     * @param response
     *            {@link SubmitApplicationResponse} submission response from
     *            grants gov
     * @param grantApplicationXml
     *            {@link String} XML content of submission
     * @param s2sAppAttachmentList
     *            {@link S2sAppAttachments} attachments included in submission
     */
    protected void saveSubmissionDetails(ProposalDevelopmentDocument pdDoc,
                                         S2sAppSubmission appSubmission, SubmitApplicationResponse response,
                                         String grantApplicationXml,
                                         List<S2sAppAttachments> s2sAppAttachmentList) {
        if (response != null) {
            String proposalNumber = pdDoc.getDevelopmentProposal()
                    .getProposalNumber();
            S2sApplication application = new S2sApplication();
            application.setApplication(grantApplicationXml);
            application.setProposalNumber(proposalNumber);
            application.setS2sAppAttachmentList(s2sAppAttachmentList);
            List<S2sApplication> s2sApplicationList = new ArrayList<S2sApplication>();
            s2sApplicationList.add(application);

            appSubmission
                    .setGgTrackingId(response.getGrantsGovTrackingNumber());
            appSubmission.setReceivedDate(new Timestamp(response
                    .getReceivedDateTime().toGregorianCalendar()
                    .getTimeInMillis()));
            appSubmission.setS2sApplication(s2sApplicationList.get(0));
            appSubmission.setProposalNumber(proposalNumber);

            List<S2sAppSubmission> appList = pdDoc.getDevelopmentProposal()
                    .getS2sAppSubmission();
            int submissionNumber = 1;
            for (S2sAppSubmission submittedApplication : appList) {
                if (submittedApplication.getSubmissionNumber() >= submissionNumber) {
                    ++submissionNumber;
                }
            }

            appSubmission.setSubmissionNumber(submissionNumber);
            getDataObjectService().save(appSubmission);
            pdDoc.getDevelopmentProposal().refreshReferenceObject("s2sAppSubmission");
        }
    }

	public File getGrantsGovSavedFile(ProposalDevelopmentDocument pdDoc)
            throws IOException {

        String loggingDirectory = s2SConfigurationService.getValueAsString(ConfigurationConstants.PRINT_XML_DIRECTORY);
        String opportunityId = pdDoc.getDevelopmentProposal().getS2sOpportunity().getOpportunityId();
        String proposalnumber = pdDoc.getDevelopmentProposal().getProposalNumber();
        String exportDate = StringUtils.replaceChars((pdDoc.getDevelopmentProposal().getUpdateTimestamp().toString()), ":", "_");
        exportDate = StringUtils.replaceChars(exportDate, " ", ".");

        if (StringUtils.isNotBlank(loggingDirectory)) {
            File directory = new File(loggingDirectory);
            if(!directory.exists()){
                directory.createNewFile();
            }
            return new File(loggingDirectory + opportunityId + "." + proposalnumber + "." + exportDate + ".zip");
        } else {
            return null;
        }

    }

    protected S2SConnectorService getS2sConnectorService(S2sOpportunityContract s2sOpportunity) {
        return KcServiceLocator.getService(s2sOpportunity.getS2sProvider().getConnectorServiceName());
    }

    public S2sOpportunityService getS2sOpportunityService() {
        return s2sOpportunityService;
    }

    public void setS2sOpportunityService(S2sOpportunityService s2sOpportunityService) {
        this.s2sOpportunityService = s2sOpportunityService;
    }

    public ProposalAdminDetailsService getProposalAdminDetailsService() {
        return proposalAdminDetailsService;
    }

    public void setProposalAdminDetailsService(ProposalAdminDetailsService proposalAdminDetailsService) {
        this.proposalAdminDetailsService = proposalAdminDetailsService;
    }

    public InstPropSponsorService getInstPropSponsorService() {
        return instPropSponsorService;
    }

    public void setInstPropSponsorService(InstPropSponsorService instPropSponsorService) {
        this.instPropSponsorService = instPropSponsorService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public FormGeneratorService getS2SService() {
        return s2SService;
    }

    public void setS2SService(FormGeneratorService s2SService) {
        this.s2SService = s2SService;
    }

    public S2sProviderService getS2sProviderService() {
        return s2sProviderService;
    }

    public void setS2sProviderService(S2sProviderService s2sProviderService) {
        this.s2sProviderService = s2sProviderService;
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }

    public OpportunitySchemaParserService getOpportunitySchemaParserService() {
        return opportunitySchemaParserService;
    }

    public void setOpportunitySchemaParserService(OpportunitySchemaParserService opportunitySchemaParserService) {
        this.opportunitySchemaParserService = opportunitySchemaParserService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
