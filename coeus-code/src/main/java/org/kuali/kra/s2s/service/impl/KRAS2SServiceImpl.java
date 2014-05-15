/*
 * Copyright 2005-2014 The Kuali Foundation.
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

import gov.grants.apply.services.applicantwebservices_v2.GetApplicationListResponse;
import gov.grants.apply.services.applicantwebservices_v2.GetApplicationListResponse.ApplicationInfo;
import gov.grants.apply.services.applicantwebservices_v2.GetApplicationStatusDetailResponse;
import gov.grants.apply.services.applicantwebservices_v2.GetOpportunitiesResponse;
import gov.grants.apply.services.applicantwebservices_v2.GetOpportunitiesResponse.OpportunityInfo;
import gov.grants.apply.services.applicantwebservices_v2.SubmitApplicationResponse;
import gov.grants.apply.system.globalV10.HashValueDocument.HashValue;
import gov.grants.apply.system.headerV10.GrantSubmissionHeaderDocument.GrantSubmissionHeader;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication.Forms;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsContract;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsService;
import org.kuali.coeus.instprop.api.sponsor.InstPropSponsorService;
import org.kuali.coeus.propdev.api.s2s.*;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.s2s.S2sApplication;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.s2s.ConfigurationConstants;
import org.kuali.kra.s2s.S2SException;
import org.kuali.coeus.propdev.impl.s2s.*;
import org.kuali.coeus.propdev.api.attachment.NarrativeService;
import org.kuali.kra.s2s.formmapping.FormMappingInfo;
import org.kuali.kra.s2s.formmapping.FormMappingLoader;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.AttachmentData;
import org.kuali.kra.s2s.service.*;
import org.kuali.kra.s2s.util.AuditError;
import org.kuali.kra.s2s.util.GrantApplicationHash;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.kra.s2s.validator.OpportunitySchemaParser;
import org.kuali.rice.krad.service.BusinessObjectService;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.namespace.QName;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

/**
 * 
 * This class is implementation of S2SService
 * 
 * @author Kuali Research Administration Team
 */
public class KRAS2SServiceImpl implements S2SService {
	private static final Log LOG = LogFactory.getLog(KRAS2SServiceImpl.class);
    private ProposalAdminDetailsService proposalAdminDetailsService;
    private InstPropSponsorService instPropSponsorService;
    private S2sOpportunityService s2sOpportunityService;
    private S2sProviderService s2sProviderService;
    private BusinessObjectService businessObjectService;
	private S2SFormGeneratorService s2SFormGeneratorService;
	private S2SUtilService s2SUtilService;
	private S2SValidatorService s2SValidatorService;
	private S2SConfigurationService s2SConfigurationService;
    private NarrativeService narrativeService;
	private static final String GRANTS_GOV_STATUS_ERROR = "ERROR";

	/**
	 * 
	 * This method is used to get the application status details.
	 * 
	 * @param ggTrackingId
	 *            grants gov tracking id for the application.
	 * @param proposalNumber
	 *            Proposal number.
	 * @throws S2SException
	 * @see org.kuali.kra.s2s.service.S2SService#getStatusDetails(java.lang.String,
	 *      java.lang.String)
	 */
	public String getStatusDetails(String ggTrackingId, String proposalNumber)
			throws S2SException {
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
	 *
	 * This method returns the list of forms for a given opportunity
	 *
	 * @param opportunity
	 * @return {@link List} of {@link S2sOppForms} which are included in the
	 *         given {@link S2sOpportunity}
	 * @see org.kuali.kra.s2s.service.S2SService#parseOpportunityForms(org.kuali.coeus.propdev.impl.s2s.S2sOpportunity)
	 */
	public List<S2sOppForms> parseOpportunityForms(S2sOpportunity opportunity) throws S2SException{
        String opportunityContent = getOpportunityContent(opportunity.getSchemaUrl());
        opportunity.setOpportunity(opportunityContent);
		return new OpportunitySchemaParser().getForms(opportunity.getProposalNumber(),opportunity.getSchemaUrl());
	}
    private String getOpportunityContent(String schemaUrl) throws S2SException{
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
            LOG.error(S2SConstants.ERROR_MESSAGE, e);
            throw new S2SException(KeyConstants.ERROR_GRANTSGOV_FORM_SCHEMA_NOT_FOUND,e.getMessage(),schemaUrl);
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
	 * This method checks for the status of submission for the given
	 * {@link ProposalDevelopmentDocument} on Grants.gov
	 *
	 * @param pdDoc
	 *            for which status has to be checked
	 * @return boolean, <code>true</code> if status has changed, false
	 *         otherwise
	 * @throws S2SException
	 * @see org.kuali.kra.s2s.service.S2SService#refreshGrantsGov(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument)
	 */
	public boolean refreshGrantsGov(ProposalDevelopmentDocument pdDoc)
			throws S2SException {
		GetApplicationListResponse applicationListResponse = fetchApplicationListResponse(pdDoc);
		if (applicationListResponse != null) {
			saveGrantsGovStatus(pdDoc, applicationListResponse);
		}
		return true;
	}

	/**
	 * This method fetches the application list from Grants.gov for a given
	 * proposal
	 * 
	 * @param pdDoc
	 * @return {@link GetApplicationListResponse}
	 * @throws S2SException
	 */
	public GetApplicationListResponse fetchApplicationListResponse(
			ProposalDevelopmentDocument pdDoc) throws S2SException {
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
	 * This method fetches the status from Grants Gov and saves in case status
	 * is modified
	 * 
	 * @param pdDoc
	 *            {@link ProposalDevelopmentDocument}
	 * @param applicationListResponse
	 *            {@link GetApplicationListResponse} response from Grants Gov
	 * @throws S2SException
	 */
	protected void saveGrantsGovStatus(ProposalDevelopmentDocument pdDoc,
			GetApplicationListResponse applicationListResponse)
			throws S2SException {
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
				ApplicationInfo ggApplication = applicationListResponse
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
				businessObjectService.save(appSubmission);
			}
		}

	}

	/**
	 * This method checks if status on grants.gov has changed since last check
	 * and returns the status.
	 * 
	 * @param pdDoc
	 * @param appSubmission
	 * @return status
	 * @throws S2SException
	 */
	public boolean checkForSubmissionStatusChange(
			ProposalDevelopmentDocument pdDoc, S2sAppSubmission appSubmission)
			throws S2SException {
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
					: S2SConstants.STATUS_GRANTS_GOV_SUBMISSION_ERROR;
			statusChanged = !appSubmission.getStatus().equalsIgnoreCase(
					statusStr);
			appSubmission.setComments(statusDetail);
			appSubmission.setStatus(statusStr);
		} else {
			appSubmission
					.setComments(S2SConstants.STATUS_NO_RESPONSE_FROM_GRANTS_GOV);
		}
		return statusChanged;
	}

	/**
	 * This method populates the {@link S2sAppSubmission} BO with details from
	 * {@link ProposalDevelopmentDocument}
	 * 
	 * @param appSubmission
	 * @param ggApplication
	 */
	public void populateAppSubmission(ProposalDevelopmentDocument pdDoc, S2sAppSubmission appSubmission,
			ApplicationInfo ggApplication) {
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
					.setComments(S2SConstants.STATUS_AGENCY_TRACKING_NUMBER_ASSIGNED);
			populateSponsorProposalId(pdDoc, appSubmission);
		} else {
			appSubmission.setComments(ggApplication
					.getGrantsGovApplicationStatus().toString());
		}
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
	            instPropSponsorService.updateSponsorProposalNumber(pad.getInstProposalId(), appSubmission.getAgencyTrackingId());
	        }
	        
	    }
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
	 * @return List<S2sOpportunity> a list containing the available
	 *         opportunities for the corresponding parameters.
	 * @throws S2SException
	 * @see org.kuali.kra.s2s.service.S2SService#searchOpportunity(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<S2sOpportunity> searchOpportunity(String providerCode, String cfdaNumber,
			String opportunityId, String competitionId) throws S2SException {
	    
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
	        throw new S2SException("An invalid provider code was provided when attempting to search for opportunities.");
	    }
	    S2SConnectorService connectorService = KcServiceLocator.getService(provider.getConnectorServiceName());
	    if (connectorService == null) {
	        throw new S2SException("The connector service '" + provider.getConnectorServiceName() + "' required by '" + provider.getDescription() + "' S2S provider is not configured.");
	    }
		List<S2sOpportunity> s2sOpportunityList = convertToArrayList(provider.getCode(),
                connectorService.getOpportunityList(cfdaNumber, opportunityId, competitionId));
		return s2sOpportunityList;
	}

	/**
	 * This method is used to submit forms to Grants.gov. It generates forms for
	 * a given {@link ProposalDevelopmentDocument}, validates and then submits
	 * the forms
	 * 
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return true if submitted false otherwise.
	 * @throws S2SException
	 * @see org.kuali.kra.s2s.service.S2SService#submitApplication(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument)
	 */
	public FormActionResult submitApplication(ProposalDevelopmentDocument pdDoc)
			throws S2SException {
		Forms forms = Forms.Factory.newInstance();
		List<AttachmentData> attList = new ArrayList<AttachmentData>();

        final FormActionResult result = generateAndValidateForms(forms, attList, pdDoc);
        if (result.isValid()) {

			Map<String, DataHandler> attachments = new HashMap<String, DataHandler>();
			List<S2sAppAttachments> s2sAppAttachmentList = new ArrayList<S2sAppAttachments>();
			DataHandler attachmentFile;
			for (AttachmentData attachmentData : attList) {
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
			appSubmission.setStatus(S2SConstants.GRANTS_GOV_STATUS_MESSAGE);
			appSubmission.setComments(S2SConstants.GRANTS_GOV_COMMENTS_MESSAGE);
			SubmitApplicationResponse response = null;

            String applicationXml = getGrantApplicationDocument(pdDoc, forms);
            S2sOpportunity s2sOpportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
            S2SConnectorService connectorService = getS2sConnectorService(s2sOpportunity);
            
			response = connectorService.submitApplication(
					applicationXml, attachments, pdDoc
							.getDevelopmentProposal().getProposalNumber());
            appSubmission.setStatus(S2SConstants.GRANTS_GOV_SUBMISSION_MESSAGE);
			saveSubmissionDetails(pdDoc, appSubmission, response,
					applicationXml, s2sAppAttachmentList);
            result.setValid(true);
		}
		return result;
	}

	/**
	 * 
	 * This method populates values for {@link GrantApplicationDocument} for a
	 * given {@link ProposalDevelopmentDocument}
	 * 
	 * @param pdDoc
	 *            {@link ProposalDevelopmentDocument}
	 * @param forms
	 *            {@link Forms} generated XML forms
	 * @return {@link GrantApplicationDocument} populated with forms
	 * @throws S2SException
	 */
	protected String getGrantApplicationDocument(
			ProposalDevelopmentDocument pdDoc, Forms forms) throws S2SException {
		GrantApplicationDocument grantApplicationDocument = GrantApplicationDocument.Factory.newInstance();
		GrantApplication grantApplication = GrantApplication.Factory.newInstance();
		grantApplication.setForms(forms);
		GrantSubmissionHeader grantSubmissionHeader = GrantSubmissionHeader.Factory.newInstance();
		grantSubmissionHeader.setActivityTitle(pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle());
		grantSubmissionHeader.setOpportunityTitle(pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle());
		grantSubmissionHeader.setAgencyName(pdDoc.getDevelopmentProposal().getSponsor().getSponsorName());
		if(pdDoc.getDevelopmentProposal().getCfdaNumber()!=null){
		    grantSubmissionHeader.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber());
		}
		S2sOpportunityContract s2sOpportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
		if (s2sOpportunity.getCompetetionId() != null) {
			grantSubmissionHeader.setCompetitionID(s2sOpportunity.getCompetetionId());
		}
		grantSubmissionHeader.setOpportunityID(s2sOpportunity.getOpportunityId());
		grantSubmissionHeader.setSchemaVersion(S2SConstants.FORMVERSION_1_0);
		grantSubmissionHeader.setSubmissionTitle(s2sOpportunity.getProposalNumber());
		
        // set closing date unless null
        Calendar closingDate = s2sOpportunity.getClosingDate();
        if (closingDate != null) {
            grantSubmissionHeader.setClosingDate(closingDate);
        }
        
		// set opening date unless null
		Calendar openingDate = s2sOpportunity.getOpeningDate();
		if (openingDate != null) {
	        grantSubmissionHeader.setOpeningDate(openingDate);
		}
        String applicationXml = getXmlFromDocument(grantApplication);
        String hashVal = GrantApplicationHash.computeGrantFormsHash(applicationXml);
		
		HashValue hashValue = HashValue.Factory.newInstance();
		hashValue.setHashAlgorithm(S2SConstants.HASH_ALGORITHM);
		hashValue.setStringValue(hashVal);
		grantSubmissionHeader.setHashValue(hashValue);
		grantApplication.setGrantSubmissionHeader(grantSubmissionHeader);
		grantApplicationDocument.setGrantApplication(grantApplication);
		String schemaUrl = s2sOpportunity.getSchemaUrl();
		
        XmlCursor cursor = grantApplicationDocument.newCursor();
        
        cursor.toStartDoc();
        if (cursor.toFirstChild()){
            String defaultNameSpace = cursor.getName().getNamespaceURI();
            String schemaLocation = defaultNameSpace+ " "+schemaUrl;
          cursor.setAttributeText(new QName("http://www.w3.org/2001/XMLSchema-instance","schemaLocation"), schemaLocation);
        }
		
		return getXmlFromDocument(grantApplicationDocument);
	}

    private String getXmlFromDocument(XmlObject grantApplicationDocument) {
        String applicationXmlText = grantApplicationDocument
                .xmlText(s2SFormGeneratorService.getXmlOptionsPrefixes());
        String applicationXml = s2SUtilService.removeTimezoneFactor(applicationXmlText);
        return applicationXml;
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

			businessObjectService.save(appSubmission);
			pdDoc.getDevelopmentProposal().refreshReferenceObject("s2sAppSubmission");
		}
	}

	/**
	 * 
	 * This method is used to validate application before submission.
	 * 
	 * @param proposalDevelopmentDocument
	 *            Proposal Development Document.
	 * @return boolean true if valid false otherwise.
	 * @throws S2SException
	 * @see org.kuali.kra.s2s.service.S2SService#validateApplication(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument)
	 */
	public FormActionResult validateApplication(
			ProposalDevelopmentDocument proposalDevelopmentDocument)
			throws S2SException {
		return generateAndValidateForms(null, null,
				proposalDevelopmentDocument);
	}

	/**
	 * 
	 * This method is to generate and validate the generated forms.
	 * 
	 * @param forms
	 *            Forms
	 * @param attList
	 *            List of attachments.
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return validation result true if valid false otherwise.
	 * @throws S2SException
	 */
	protected FormActionResult generateAndValidateForms(Forms forms,
			List<AttachmentData> attList, ProposalDevelopmentDocument pdDoc) throws S2SException {
		boolean validationSucceeded = true;
		DevelopmentProposal developmentProposal = pdDoc.getDevelopmentProposal();
		List<S2sOppForms> opportunityForms = developmentProposal.getS2sOppForms();

		if (attList == null) {
		    attList = new ArrayList<AttachmentData>();
		}

        List<AuditError> auditErrors = new ArrayList<AuditError>();
        getNarrativeService().deleteSystemGeneratedNarratives(pdDoc.getDevelopmentProposal().getNarratives());
		for (S2sOppFormsContract opportunityForm : opportunityForms) {
			if (!opportunityForm.getInclude()) {
				continue;
			}
			List<AttachmentData> formAttList = new ArrayList<AttachmentData>();
			S2SBaseFormGenerator s2sFormGenerator = null;
			try {
				FormMappingInfo info = new FormMappingLoader().getFormInfo(opportunityForm.getOppNameSpace());
				s2sFormGenerator = (S2SBaseFormGenerator)s2SFormGeneratorService.getS2SGenerator(developmentProposal.getProposalNumber(),info.getNameSpace());
			    s2sFormGenerator.setAuditErrors(auditErrors);
			    s2sFormGenerator.setAttachments(formAttList);
			    s2sFormGenerator.setNamespace(info.getNameSpace());
			} catch (S2SException e) {
				continue;
			}
			try {
				XmlObject formObject = s2sFormGenerator.getFormObject(pdDoc);
				if (s2SValidatorService.validate(formObject, auditErrors)) {
					if (forms != null && attList != null) {
						setFormObject(forms, formObject);
					}
				} else {
					validationSucceeded = false;
				}
				attList.addAll(formAttList);
			} catch (Exception ex) {
				LOG.error(
						"Unknown error from " + opportunityForm.getFormName(),
						ex);
				throw new S2SException("Could not generate form for "
						+ opportunityForm.getFormName(), ex);
			}
		}
        FormActionResult result = new FormActionResult();
        result.setValid(validationSucceeded);
        result.setErrors(auditErrors);
		return result;
	}

    /**
	 * 
	 * This method is to set the formObject to MetaGrants Forms Object. The
	 * xmlbeans Schema compiled with xsd:any does not provide a direct method to
	 * add individual forms to the Forms object. In this method, an XML Cursor
	 * is created from the existing Forms object and use the moveXml method to
	 * add the form object to the Forms object.
	 * 
	 * @param forms
	 *            Forms object to which the grants.gov form is added.
	 * @param formObject
	 *            xml object representing the grants.gov form.
	 */
	protected void setFormObject(Forms forms, XmlObject formObject) {
		// Create a cursor from the grants.gov form
		XmlCursor formCursor = formObject.newCursor();
		formCursor.toStartDoc();
		formCursor.toNextToken();

		// Create a cursor from the Forms object
		XmlCursor metaGrantCursor = forms.newCursor();
		metaGrantCursor.toNextToken();

		// Add the form to the Forms object.
		formCursor.moveXml(metaGrantCursor);
	}

	/**
	 * This method convert GetOpportunityListResponse to ArrayList<OpportunityInfo>
	 * 
	 * @param resList
	 *            {GetOpportunityListResponse}
	 * @return ArrayList<OpportunityInfo> containing all form information
	 */

	protected ArrayList<S2sOpportunity> convertToArrayList(String source,
			GetOpportunitiesResponse resList) {
		ArrayList<S2sOpportunity> convList = new ArrayList<S2sOpportunity>();
		if (resList == null || resList.getOpportunityInfo() == null) {
			return convList;
		}
		for (OpportunityInfo opportunityInfo : resList.getOpportunityInfo()) {
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
			String providerCode, OpportunityInfo oppInfo) {
	    
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
	 * This method is to set businessObjectService
	 * 
	 * @param businessObjectService(BusinessObjectService)
	 */
	public void setBusinessObjectService(
			BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	/**
	 * 
	 * Setter for {@link S2SFormGeneratorService}
	 * 
	 * @param s2SFormGeneratorService
	 */
	public void setS2SFormGeneratorService(
			S2SFormGeneratorService s2SFormGeneratorService) {
		this.s2SFormGeneratorService = s2SFormGeneratorService;
	}

	/**
	 * Sets the s2sUtilService attribute value.
	 * 
	 * @param s2SUtilService
	 *            The s2sUtilService to set.
	 */
	public void setS2SUtilService(S2SUtilService s2SUtilService) {
		this.s2SUtilService = s2SUtilService;
	}

	public File getGrantsGovSavedFile(ProposalDevelopmentDocument pdDoc)
	        throws IOException {
        String loggingDirectory = s2SConfigurationService.getValueAsString(ConfigurationConstants.PRINT_XML_DIRECTORY);
        String saveXmlFolderName = pdDoc.getSaveXmlFolderName();
        if (StringUtils.isNotBlank(loggingDirectory)) {
            File directory = new File(loggingDirectory);
            if(!directory.exists()){
                directory.createNewFile();
            }
            if(!loggingDirectory.endsWith("/")){
                loggingDirectory+="/";
            }
            return new File(loggingDirectory + saveXmlFolderName + ".zip");
        } else {
            return null;
        }

	}

	/**
	 * Gets the s2SFormGeneratorService attribute.
	 * 
	 * @return Returns the s2SFormGeneratorService.
	 */
	public S2SFormGeneratorService getS2SFormGeneratorService() {
		return s2SFormGeneratorService;
	}

	/**
	 * Gets the s2sUtilService attribute.
	 * 
	 * @return Returns the s2sUtilService.
	 */
	public S2SUtilService getS2sUtilService() {
		return s2SUtilService;
	}

	/**
	 * Gets the businessObjectService attribute.
	 * 
	 * @return Returns the businessObjectService.
	 */
	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	/**
	 * Gets the s2SValidatorService attribute.
	 * 
	 * @return Returns the s2SValidatorService.
	 */
	public S2SValidatorService getS2SValidatorService() {
		return s2SValidatorService;
	}

	/**
	 * Sets the s2SValidatorService attribute value.
	 * 
	 * @param validatorService
	 *            The s2SValidatorService to set.
	 */
	public void setS2SValidatorService(S2SValidatorService validatorService) {
		s2SValidatorService = validatorService;
	}

    protected S2SConnectorService getS2sConnectorService(S2sOpportunityContract s2sOpportunity) {
        return KcServiceLocator.getService(s2sOpportunity.getS2sProvider().getConnectorServiceName());
    }

    public NarrativeService getNarrativeService() {
        return narrativeService;
    }

    public void setNarrativeService(NarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    public S2sOpportunityService getS2sOpportunityService() {
        return s2sOpportunityService;
    }

    public void setS2sOpportunityService(S2sOpportunityService s2sOpportunityService) {
        this.s2sOpportunityService = s2sOpportunityService;
    }

    public S2sProviderService getS2sProviderService() {
        return s2sProviderService;
    }

    public void setS2sProviderService(S2sProviderService s2sProviderService) {
        this.s2sProviderService = s2sProviderService;
    }

    public S2SUtilService getS2SUtilService() {
        return s2SUtilService;
    }

    public ProposalAdminDetailsService getProposalAdminDetailsService() {
        return proposalAdminDetailsService;
    }

    public InstPropSponsorService getInstPropSponsorService() {
        return instPropSponsorService;
    }

    public void setProposalAdminDetailsService(ProposalAdminDetailsService proposalAdminDetailsService) {
        this.proposalAdminDetailsService = proposalAdminDetailsService;
    }

    public void setInstPropSponsorService(InstPropSponsorService instPropSponsorService) {
        this.instPropSponsorService = instPropSponsorService;
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }
}
