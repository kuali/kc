/*
 * Copyright 2008 The Kuali Foundation.
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

import gov.grants.apply.webservices.applicantintegrationservices_v1.GetApplicationListResponse;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetApplicationStatusDetailResponse;
import gov.grants.apply.webservices.applicantintegrationservices_v1.GetOpportunityListResponse;
import gov.grants.apply.webservices.applicantintegrationservices_v1.SubmitApplicationResponse;

import java.util.Map;

import javax.activation.DataHandler;

import org.kuali.kra.s2s.S2SException;

/**
 * 
 * This class will be used to call web services.
 */
public interface GrantsGovConnectorService {
    /**
     * 
     * This method is to get Opportunity List for the given cfda number,opportunity Id and competition Id from the grants guv.
     * 
     * @param cfdaNumber of the opportunity.
     * @param opportunityId parameter for the opportunity.
     * @param competitionId parameter for the opportunity.
     * @return GetOpportunityListResponse available list of opportunities applicable for the given cfda number,opportunity Id and
     *         competition Id.
     * @throws S2SException
     */
    public GetOpportunityListResponse getOpportunityList(String cfdaNumber, String opportunityId, String competitionId)
            throws S2SException;

    /**
     * 
     * This method is to get status of the submitted application.
     * 
     * @param ggTrackingId grants gov tracking id for the proposal.
     * @param proposalNumber Proposal number.
     * @return GetApplicationStatusDetailResponse status of the submitted application.
     * @throws S2SException
     */
    public GetApplicationStatusDetailResponse getApplicationStatusDetail(String ggTrackingId, String proposalNumber)
            throws S2SException;

    /**
     * 
     * This method is to get Application List from grants.gov for opportunityId, cfdaNumber and proposalNumber
     * 
     * @param opportunityId of the opportunity.
     * @param cfdaNumber of the opportunity.
     * @param proposalNumber proposal number.
     * @return GetApplicationListResponse application list.
     * @throws S2SException
     */
    public GetApplicationListResponse getApplicationList(String opportunityId, String cfdaNumber, String proposalNumber)
            throws S2SException;

    /**
     * 
     * This method is to submit a proposal to grants.gov
     * 
     * @param xmlText xml format of the form object.
     * @param attachments attachments of the proposal.
     * @param proposalNumber proposal number.
     * @return SubmitApplicationResponse corresponding to the input parameters passed.
     * @throws S2SException
     */
    public SubmitApplicationResponse submitApplication(String xmlText, Map<String, DataHandler> attachments, String proposalNumber)
            throws S2SException;

}
