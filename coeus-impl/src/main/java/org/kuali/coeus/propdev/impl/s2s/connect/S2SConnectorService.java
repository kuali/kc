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

import gov.grants.apply.services.applicantwebservices_v2.GetApplicationListResponse;
import gov.grants.apply.services.applicantwebservices_v2.GetApplicationStatusDetailResponse;
import gov.grants.apply.services.applicantwebservices_v2.GetOpportunitiesResponse;
import gov.grants.apply.services.applicantwebservices_v2.SubmitApplicationResponse;

import javax.activation.DataHandler;
import java.util.Map;

/**
 * 
 * This class will be used to call web services.
 */
public interface S2SConnectorService {
    /**
     * 
     * This method is to get Opportunity List for the given cfda number,opportunity Id and competition Id from the grants guv.
     * 
     * @param cfdaNumber of the opportunity.
     * @param opportunityId parameter for the opportunity.
     * @param competitionId parameter for the opportunity.
     * @return GetOpportunityListResponse available list of opportunities applicable for the given cfda number,opportunity Id and
     *         competition Id.
     * @throws S2sCommunicationException
     */
    public GetOpportunitiesResponse getOpportunityList(String cfdaNumber, String opportunityId, String competitionId)
            throws S2sCommunicationException;

    /**
     * 
     * This method is to get status of the submitted application.
     * 
     * @param ggTrackingId grants gov tracking id for the proposal.
     * @param proposalNumber Proposal number.
     * @return GetApplicationStatusDetailResponse status of the submitted application.
     * @throws S2sCommunicationException
     */
    public GetApplicationStatusDetailResponse getApplicationStatusDetail(String ggTrackingId, String proposalNumber)
            throws S2sCommunicationException;

    /**
     * 
     * This method is to get Application List from grants.gov for opportunityId, cfdaNumber and proposalNumber
     * 
     * @param opportunityId of the opportunity.
     * @param cfdaNumber of the opportunity.
     * @param proposalNumber proposal number.
     * @return GetApplicationListResponse application list.
     * @throws S2sCommunicationException
     */
    public GetApplicationListResponse getApplicationList(String opportunityId, String cfdaNumber, String proposalNumber)
            throws S2sCommunicationException;

    /**
     * 
     * This method is to submit a proposal to grants.gov
     * 
     * @param xmlText xml format of the form object.
     * @param attachments attachments of the proposal.
     * @param proposalNumber proposal number.
     * @return SubmitApplicationResponse corresponding to the input parameters passed.
     * @throws S2sCommunicationException
     */
    public SubmitApplicationResponse submitApplication(String xmlText, Map<String, DataHandler> attachments, String proposalNumber)
            throws S2sCommunicationException;

}
