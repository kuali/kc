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
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.*;
import org.kuali.kra.s2s.formmapping.FormMappingInfo;
import org.kuali.kra.s2s.formmapping.FormMappingLoader;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.S2SGeneratorNotFoundException;
import org.kuali.kra.s2s.generator.bo.AttachmentData;
import org.kuali.kra.s2s.service.*;
import org.kuali.kra.s2s.util.GrantApplicationHash;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.kra.s2s.validator.OpportunitySchemaParser;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.namespace.QName;
import java.io.BufferedInputStream;
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
	private BusinessObjectService businessObjectService;
	private DateTimeService dateTimeService;
	private S2SFormGeneratorService s2SFormGeneratorService;
	private S2SProposalValidatorService s2SProposalValidatorService;
	private S2SUtilService s2SUtilService;
	private PrintService printService;
	private S2SValidatorService s2SValidatorService;
	private S2SUserAttachedFormService s2SUserAttachedFormService;	
	private static final String GRANTS_GOV_STATUS_ERROR = "ERROR";
	private static final String KEY_PROPOSAL_NUMBER = "proposalNumber";

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
	    if(isAuthorizedToAccess(proposalNumber)){
	        if (StringUtils.isNotBlank(proposalNumber) && proposalNumber.contains(Constants.COLON)) {
	            proposalNumber = StringUtils.split(proposalNumber, Constants.COLON)[0];
	        }
	        Map<String, String> opportunityMap = new HashMap<String, String>();
	        opportunityMap.put(KEY_PROPOSAL_NUMBER, proposalNumber);
	        S2sOpportunity s2sOpportunity = (S2sOpportunity) businessObjectService
	                .findByPrimaryKey(S2sOpportunity.class, opportunityMap);
	        
	        Object statusDetail = null;
	        GetApplicationStatusDetailResponse applicationStatusDetailResponse;
	        applicationStatusDetailResponse = getS2sConnectorService(s2sOpportunity)
	        .getApplicationStatusDetail(ggTrackingId, proposalNumber);
	        if (applicationStatusDetailResponse != null) {
	            statusDetail = applicationStatusDetailResponse.getDetailedStatus();
	        }
	        return statusDetail.toString();
	    }
	    return StringUtils.EMPTY;
	}

	/**
	 * 
	 * This method returns the list of forms for a given opportunity
	 * 
	 * @param opportunity
	 * @return {@link List} of {@link S2sOppForms} which are included in the
	 *         given {@link S2sOpportunity}
	 * @see org.kuali.kra.s2s.service.S2SService#parseOpportunityForms(org.kuali.kra.s2s.bo.S2sOpportunity)
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
	 * @see org.kuali.kra.s2s.service.S2SService#refreshGrantsGov(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
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
		Map<String, String> opportunityMap = new HashMap<String, String>();
		opportunityMap.put(KEY_PROPOSAL_NUMBER, pdDoc.getDevelopmentProposal()
				.getProposalNumber());
		S2sOpportunity s2sOpportunity = (S2sOpportunity) businessObjectService
				.findByPrimaryKey(S2sOpportunity.class, opportunityMap);
		GetApplicationListResponse applicationListResponse = getS2sConnectorService(s2sOpportunity)
				.getApplicationList(s2sOpportunity.getOpportunityId(),
						s2sOpportunity.getCfdaNumber(), s2sOpportunity
								.getProposalNumber());
		return applicationListResponse;
	}

	/**
	 * 
	 * This method fetches the status from Grants Gov and saves in case status
	 * is modified
	 * 
	 * @param pdDoc
	 *            {@link ProposalDevelopmentDocument}
	 * @param appSubmission
	 *            {@link S2sAppSubmission}
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
	 * {@link ApplicationInformationType}
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
	 * @see org.kuali.kra.s2s.service.S2SService#populateSponsorProposalId(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.s2s.bo.S2sAppSubmission)
	 */
	public void populateSponsorProposalId(ProposalDevelopmentDocument pdDoc, S2sAppSubmission appSubmission) {
	    if (StringUtils.isNotBlank(appSubmission.getAgencyTrackingId())) {
	        if (StringUtils.isBlank(pdDoc.getDevelopmentProposal().getSponsorProposalNumber())) {
	            pdDoc.getDevelopmentProposal().setSponsorProposalNumber(appSubmission.getAgencyTrackingId());
	            getBusinessObjectService().save(pdDoc.getDevelopmentProposal());
	        }
	        
	        //find and populate the inst proposal sponsor proposal id as well
	        Map<String, String> values = new HashMap<String, String>();
	        values.put("devProposalNumber", pdDoc.getDevelopmentProposal().getProposalNumber());
	        Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, values);
	        
	        for(Iterator<ProposalAdminDetails> iter = proposalAdminDetails.iterator(); iter.hasNext();){
	            ProposalAdminDetails pad = iter.next();
	            pad.refreshReferenceObject("institutionalProposal");
	            if (StringUtils.isBlank(pad.getInstitutionalProposal().getSponsorProposalNumber())) {
	                pad.getInstitutionalProposal().setSponsorProposalNumber(appSubmission.getAgencyTrackingId());
	                getBusinessObjectService().save(pad.getInstitutionalProposal());
	            }
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
	 *      java.lang.String, java.lang.String)
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
	    
	    S2sProvider provider = businessObjectService.findBySinglePrimaryKey(S2sProvider.class, providerCode);
	    if (provider == null) {
	        throw new S2SException("An invalid provider code was provided when attempting to search for opportunities.");
	    }
	    S2SConnectorService connectorService = KraServiceLocator.getService(provider.getConnectorServiceName());
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
	 * @see org.kuali.kra.s2s.service.S2SService#submitApplication(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
	 */
	public boolean submitApplication(ProposalDevelopmentDocument pdDoc)
			throws S2SException {
		boolean submissionStatus = false;
		Forms forms = Forms.Factory.newInstance();
		List<AttachmentData> attList = new ArrayList<AttachmentData>();
		if (generateAndValidateForms(forms, attList, pdDoc)) {

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
			submissionStatus = true;
		}
		return submissionStatus;
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
		S2sOpportunity s2sOpportunity = pdDoc.getDevelopmentProposal().getS2sOpportunity();
		s2sOpportunity.refreshNonUpdateableReferences();
		if (s2sOpportunity.getCompetetionId() != null) {
			grantSubmissionHeader.setCompetitionID(s2sOpportunity.getCompetetionId());
		}
		grantSubmissionHeader.setOpportunityID(s2sOpportunity.getOpportunityId());
		grantSubmissionHeader.setSchemaVersion(S2SConstants.FORMVERSION_1_0);
		grantSubmissionHeader.setSubmissionTitle(s2sOpportunity.getProposalNumber());
		
        // set closing date unless null
        Date closingDate = s2sOpportunity.getClosingDate();
        if (closingDate != null) {
            Calendar calClosingDate = Calendar.getInstance();
            calClosingDate.setTime(closingDate);
            grantSubmissionHeader.setClosingDate(calClosingDate);
        }
        
		// set opening date unless null
		Date openingDate = s2sOpportunity.getOpeningDate();
		if (openingDate != null) {
	        Calendar calOpeningDate = Calendar.getInstance();
		    calOpeningDate.setTime(openingDate);
	        grantSubmissionHeader.setOpeningDate(calOpeningDate);
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
			appSubmission.setS2sApplication(s2sApplicationList);
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
//			appList.add(appSubmission);
			// appSubmission.setStatus(S2SConstants.GRANTS_GOV_SUBMISSION_MESSAGE);
			// appSubmission.setComments(S2SConstants.GRANTS_GOV_PROCESSING_MESSAGE);
			// pdDoc.getDevelopmentProposal().setS2sAppSubmission(appList);
//			List<PersistableBusinessObject> saveList = new ArrayList<PersistableBusinessObject>();
//			saveList.add(appSubmission);
//			saveList.add(application);

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
	 * @see org.kuali.kra.s2s.service.S2SService#validateApplication(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
	 */
	public boolean validateApplication(
			ProposalDevelopmentDocument proposalDevelopmentDocument)
			throws S2SException {
		return generateAndValidateForms(null, null,
				proposalDevelopmentDocument, new ArrayList<AuditError>());
	}

	public boolean validateApplication(
			ProposalDevelopmentDocument proposalDevelpmentDocument,
			List<AuditError> auditErrors) throws S2SException {
		return generateAndValidateForms(null, null, proposalDevelpmentDocument,
				auditErrors);
	}

	protected boolean generateAndValidateForms(Forms forms,
			List<AttachmentData> attList, ProposalDevelopmentDocument pdDoc)
			throws S2SException {
		return generateAndValidateForms(forms, attList, pdDoc,
				new ArrayList<AuditError>());
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
	protected boolean generateAndValidateForms(Forms forms,
			List<AttachmentData> attList, ProposalDevelopmentDocument pdDoc,
			List<AuditError> auditErrors) throws S2SException {
		boolean validationSucceeded = true;
		DevelopmentProposal developmentProposal = pdDoc.getDevelopmentProposal();
		List<S2sOppForms> opportunityForms = developmentProposal.getS2sOppForms();
		if (opportunityForms.isEmpty()) {
			developmentProposal.refreshReferenceObject("s2sOppForms");
		}
		if (attList == null) {
		    attList = new ArrayList<AttachmentData>();
		}
	    getS2sUtilService().deleteSystemGeneratedAttachments(pdDoc);
		for (S2sOppForms opportunityForm : opportunityForms) {
			if (!opportunityForm.getInclude()) {
				continue;
			}
			List<AttachmentData> formAttList = new ArrayList<AttachmentData>();
			S2SBaseFormGenerator s2sFormGenerator = null;
            FormMappingInfo info = new FormMappingLoader().getFormInfo(developmentProposal.getProposalNumber(),opportunityForm.getOppNameSpace());
            if(info==null) continue;
			String namespace = info.getNameSpace();
            s2sFormGenerator = (S2SBaseFormGenerator)s2SFormGeneratorService.getS2SGenerator(developmentProposal.getProposalNumber(),namespace);
			try {
			    s2sFormGenerator.setAuditErrors(auditErrors);
			    s2sFormGenerator.setAttachments(formAttList);
			    s2sFormGenerator.setNamespace(namespace);
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
		if (!validationSucceeded || !auditErrors.isEmpty()) {
			setValidationErrorMessage(auditErrors);
		}
		return validationSucceeded;
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
	 * 
	 * This method is to put validation errors on UI
	 * 
	 * @param errors
	 *            List of validation errors which has to be displayed on UI.
	 */

	protected void setValidationErrorMessage(List<AuditError> errors) {
		LOG.info("Error list size:" + errors.size() + errors.toString());
		List<AuditError> auditErrors = new ArrayList<AuditError>();
		for (AuditError error : errors) {
			auditErrors.add(new AuditError(error.getErrorKey(),
					Constants.GRANTS_GOV_GENERIC_ERROR_KEY, error.getLink(),
					new String[] { error.getMessageKey() }));
		}
		if (!auditErrors.isEmpty()) {
			KNSGlobalVariables.getAuditErrorMap().put(
					"grantsGovAuditErrors",
					new AuditCluster(Constants.GRANTS_GOV_OPPORTUNITY_PANEL,
							auditErrors, Constants.GRANTSGOV_ERRORS));
		}
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
	 * @param oppInfoType
	 *            {OpportunityInformationType}
	 * @return OpportunityInfo containing Opportunity information corresponding
	 *         to the OpportunityInformationType object.
	 */
	protected S2sOpportunity convert2S2sOpportunity(
			String providerCode, OpportunityInfo oppInfo) {
	    
		S2sOpportunity s2Opportunity = new S2sOpportunity();
		s2Opportunity.setCfdaNumber(oppInfo.getCFDANumber());
		s2Opportunity
				.setClosingDate(oppInfo.getClosingDate() == null ? null
						: new java.sql.Timestamp(oppInfo.getClosingDate()
								.toGregorianCalendar().getTimeInMillis()));
		
		s2Opportunity.setCompetetionId(oppInfo.getCompetitionID());
		s2Opportunity.setInstructionUrl(oppInfo.getInstructionsURL());
		s2Opportunity
				.setOpeningDate(oppInfo.getOpeningDate() == null ? null
						: new java.sql.Timestamp(oppInfo.getOpeningDate()
								.toGregorianCalendar().getTimeInMillis()));
		
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
	 * 
	 * Setter for {@link S2SProposalValidatorService}
	 * 
	 * @param s2SProposalValidatorService
	 */
	public void setS2SProposalValidatorService(
			S2SProposalValidatorService s2SProposalValidatorService) {
		this.s2SProposalValidatorService = s2SProposalValidatorService;
	}

	/**
	 * Sets the s2sUtilService attribute value.
	 * 
	 * @param generatorUtilService
	 *            The s2sUtilService to set.
	 */
	public void setS2SUtilService(S2SUtilService s2SUtilService) {
		this.s2SUtilService = s2SUtilService;
	}

	/**
	 * 
	 * This method is used to print selected forms.
	 * 
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return AttachmentDataSource for the selected form.
	 * @throws S2SException
	 */
	public AttachmentDataSource printForm(ProposalDevelopmentDocument pdDoc)
			throws S2SException,PrintingException {
		return printService.printForm(pdDoc);
	}

	/**
	 * Gets the printService attribute.
	 * 
	 * @return Returns the printService.
	 */
	public PrintService getPrintService() {
		return printService;
	}

	/**
	 * Sets the printService attribute value.
	 * 
	 * @param printService
	 *            The printService to set.
	 */
	public void setPrintService(PrintService printService) {
		this.printService = printService;
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
	 * Gets the s2SProposalValidatorService attribute.
	 * 
	 * @return Returns the s2SProposalValidatorService.
	 */
	public S2SProposalValidatorService getS2SProposalValidatorService() {
		return s2SProposalValidatorService;
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
	 * Gets the dateTimeService attribute.
	 * 
	 * @return Returns the dateTimeService.
	 */
	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	/**
	 * Sets the dateTimeService attribute value.
	 * 
	 * @param dateTimeService
	 *            The dateTimeService to set.
	 */
	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
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

    /*
     * a utility method to check if dwr/ajax call really has authorization
     * 'updateProtocolFundingSource' also accessed by non ajax call
     */
    
    private boolean isAuthorizedToAccess(String proposalNumber) {
        boolean isAuthorized = true;
        if(proposalNumber.contains(Constants.COLON)){
            if (GlobalVariables.getUserSession() != null) {
                // TODO : this is a quick hack for KC 3.1.1 to provide authorization check for dwr/ajax call. dwr/ajax will be replaced by
                // jquery/ajax in rice 2.0
                String[] invalues = StringUtils.split(proposalNumber, Constants.COLON);
                String docFormKey = invalues[1];
                if (StringUtils.isBlank(docFormKey)) {
                    isAuthorized = false;
                } else {
                    Object formObj = GlobalVariables.getUserSession().retrieveObject(docFormKey);
                    if (formObj == null || !(formObj instanceof ProposalDevelopmentForm)) {
                        isAuthorized = false;
                    } else {
                        Map<String, String> editModes = ((ProposalDevelopmentForm)formObj).getEditingMode();
                        isAuthorized = (BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.FULL_ENTRY))
                        || BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.VIEW_ONLY))
                        || BooleanUtils.toBoolean(editModes.get("modifyProposal")))
                        && BooleanUtils.toBoolean(editModes.get("submitToSponsor"));
                    }
                }
            } else {
                // TODO : it seemed that tomcat has this issue intermittently ?
                LOG.info("dwr/ajax does not have session ");
            }
        }
        return isAuthorized;
    }

    protected S2SConnectorService getS2sConnectorService(S2sOpportunity s2sOpportunity) {
        return KraServiceLocator.getService(s2sOpportunity.getS2sProvider().getConnectorServiceName());
    }


    /**
     * Gets the s2SUserAttachedFormService attribute. 
     * @return Returns the s2SUserAttachedFormService.
     */
    public S2SUserAttachedFormService getS2SUserAttachedFormService() {
        return s2SUserAttachedFormService;
    }

    /**
     * Sets the s2SUserAttachedFormService attribute value.
     * @param s2sUserAttachedFormService The s2SUserAttachedFormService to set.
     */
    public void setS2SUserAttachedFormService(S2SUserAttachedFormService s2sUserAttachedFormService) {
        s2SUserAttachedFormService = s2sUserAttachedFormService;
    }
}
