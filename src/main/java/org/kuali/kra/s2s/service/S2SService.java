/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.s2s.service;

import gov.grants.apply.webservices.applicantintegrationservices_v1.ApplicationInformationType;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetApplicationListResponse;

import java.util.List;

import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.rice.kns.util.AuditError;

/**
 * 
 * This class is called for various S2S services such as validate application,
 * Submit application,Print selected forms, search opportunities,Status Details.
 */
public interface S2SService {

	/**
	 * This method is to find the list of available opportunities
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
	 */
	public List<S2sOpportunity> searchOpportunity(String cfdaNumber,
			String opportunityId, String competitionId) throws S2SException;

	/**
	 * 
	 * This method returns the list of forms for a given opportunity
	 * 
	 * @param opportunity
	 * @return {@link List}of {@link S2sOppForms} which are included in the
	 *         given {@link S2sOpportunity}
	 * @throws S2SException 
	 */
	public List<S2sOppForms> parseOpportunityForms(S2sOpportunity opportunity) throws S2SException;

	/**
	 * This method checks for the status of submission for the given
	 * {@link ProposalDevelopmentDocument} on Grants.gov
	 * 
	 * @param pdDoc
	 *            for which status has to be checked
	 * @return boolean, <code>true</code> if status has changed, false
	 *         otherwise
	 * @throws S2SException
	 */
	public boolean refreshGrantsGov(ProposalDevelopmentDocument pdDoc)
			throws S2SException;

	/**
	 * This method is used to get the application status details.
	 * 
	 * @param ggTrackingId
	 *            grants gov tracking id for the application.
	 * @param proposalNumber
	 *            Proposal number.
	 * @throws S2SException
	 */
	public String getStatusDetails(String ggTrackingId, String proposalNumber)
			throws S2SException;

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
			throws S2SException,PrintingException;

	/**
	 * 
	 * This method is used to submit forms to the grants.guv
	 * 
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return true if submitted false otherwise.
	 * @throws S2SException
	 */
	public boolean submitApplication(ProposalDevelopmentDocument pdDoc)
			throws S2SException;

	/**
	 * 
	 * This method is used to validate application before submission.
	 * 
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return boolean true if valid false otherwise.
	 * @throws S2SException
	 */
	public boolean validateApplication(ProposalDevelopmentDocument pdDoc)
			throws S2SException;

	/**
	 * 
	 * This method is used to validate application before submission.
	 * 
	 * @param pdDoc
	 *            Proposal Development Document.
	 * @return boolean true if valid false otherwise.
	 * @throws S2SException
	 */
	public boolean validateApplication(ProposalDevelopmentDocument pdDoc,
			List<AuditError> auditErrors) throws S2SException;

	/**
	 * This method populates the {@link S2sAppSubmission} BO with details from
	 * {@link ApplicationInformationType}
	 * 
	 * @param appSubmission
	 * @param ggApplication
	 */
	public void populateAppSubmission(ProposalDevelopmentDocument pdDoc, S2sAppSubmission appSubmission,
			ApplicationInformationType ggApplication);
	
	/**
	 * 
	 * Takes the appSubmission and proposal and if a federal tracking id has been specified, will
	 * set on both the proposal development doc and the related institutional proposal doc
	 * if there is not a sponsor proposal id already.
	 * @param pdDoc
	 * @param appSubmission
	 */
	public void populateSponsorProposalId(ProposalDevelopmentDocument pdDoc, 
	        S2sAppSubmission appSubmission);
	
	/**
	 * This method fetches the application list from Grants.gov for a given
	 * proposal
	 * 
	 * @param pdDoc
	 * @return {@link GetApplicationListResponse}
	 * @throws S2SException
	 */
	public GetApplicationListResponse fetchApplicationListResponse(
			ProposalDevelopmentDocument pdDoc) throws S2SException;

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
			throws S2SException;
}
