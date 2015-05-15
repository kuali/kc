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
package org.kuali.coeus.propdev.impl.s2s;

import gov.grants.apply.services.applicantwebservices_v2.GetApplicationListResponse;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.s2s.connect.S2sCommunicationException;
import org.kuali.coeus.s2sgen.api.generate.FormGenerationResult;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface S2sSubmissionService {

    /**
     *
     * This method returns the list of forms for a given opportunity
     *
     * @param opportunity
     * @return {@link List}of {@link S2sOppForms} which are included in the
     *         given {@link S2sOpportunity}
     * @throws S2sCommunicationException
     */
    public List<S2sOppForms> parseOpportunityForms(S2sOpportunity opportunity) throws S2sCommunicationException;

    /**
     * This method is used to get the application status details.
     *
     * @param ggTrackingId
     *            grants gov tracking id for the application.
     * @param proposalNumber
     *            Proposal number.
     * @throws S2sCommunicationException
     */
    public String getStatusDetails(String ggTrackingId, String proposalNumber)
            throws S2sCommunicationException;

    /**
     * This method checks if status on grants.gov has changed since last check
     * and returns the status.
     *
     * @param pdDoc
     * @param appSubmission
     * @return status
     * @throws S2sCommunicationException
     */
    public boolean checkForSubmissionStatusChange(
            ProposalDevelopmentDocument pdDoc, S2sAppSubmission appSubmission)
            throws S2sCommunicationException;

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
    public boolean refreshGrantsGov(ProposalDevelopmentDocument pdDoc)
            throws S2sCommunicationException;

    /**
     *
     * This method is used to submit forms to the grants.guv
     *
     * @param pdDoc
     *            Proposal Development Document.
     * @return true if submitted false otherwise.
     * @throws S2sCommunicationException
     */
    public FormGenerationResult submitApplication(ProposalDevelopmentDocument pdDoc)
            throws S2sCommunicationException;

    /**
     * This method is to find the list of available opportunities
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
    public List<S2sOpportunity> searchOpportunity(String providerCode, String cfdaNumber,
                                                  String opportunityId, String competitionId) throws S2sCommunicationException;

    /**
     * Return the file saved to the local filesystem.
     * @param pdDoc
     * @return
     * @throws S2sCommunicationException
     */
    public File getGrantsGovSavedFile(ProposalDevelopmentDocument pdDoc)
            throws IOException;

    GetApplicationListResponse fetchApplicationListResponse(
            ProposalDevelopmentDocument pdDoc) throws S2sCommunicationException;

    void populateAppSubmission(ProposalDevelopmentDocument pdDoc, S2sAppSubmission appSubmission,
                          GetApplicationListResponse.ApplicationInfo ggApplication);

    public void setOpportunityContent(S2sOpportunity opportunity);


    public List<String> setMandatoryForms(DevelopmentProposal proposal, S2sOpportunity s2sOpportunity);
}
