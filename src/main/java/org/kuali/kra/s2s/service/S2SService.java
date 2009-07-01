/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;

/**
 * 
 * This class is called for various S2S services such as validate application, Submit application,Print selected forms, search
 * opportunities,Status Details.
 */
public interface S2SService {

    /**
     * This method is to find the list of available opportunities
     * 
     * @param cfdaNumber of the opportunity.
     * @param opportunityId parameter for the opportunity.
     * @param competitionId parameter for the opportunity.
     * @return List<S2sOpportunity> a list containing the available opportunities for the corresponding parameters.
     * @throws S2SException 
     */
    public List<S2sOpportunity> searchOpportunity(String cfdaNumber, String opportunityId, String competitionId) throws S2SException;

    /**
     * 
     * This method returns the list of forms for a given opportunity
     * 
     * @param opportunity
     * @return {@link List}of {@link S2sOppForms} which are included in the given {@link S2sOpportunity}
     */
    public List<S2sOppForms> parseOpportunityForms(S2sOpportunity opportunity);

    /**
     * This method checks for the status of submission for the given {@link ProposalDevelopmentDocument} on Grants.gov
     * 
     * @param pdDoc for which status has to be checked
     * @return boolean, <code>true</code> if status has changed, false otherwise
     * @throws S2SException 
     */
    public boolean refreshGrantsGov(ProposalDevelopmentDocument pdDoc) throws S2SException;

    /**
     * This method is used to get the application status details.
     * 
     * @param ggTrackingId grants gov tracking id for the application.
     * @param proposalNumber Proposal number.
     * @throws S2SException 
     */
    public Object getStatusDetails(String ggTrackingId, String proposalNumber) throws S2SException;

    /**
     * 
     * This method is used to print selected forms.
     * 
     * @param pdDoc Proposal Development Document.
     * @return AttachmentDataSource for the selected form.
     * @throws S2SException 
     */
    public AttachmentDataSource printForm(ProposalDevelopmentDocument pdDoc) throws S2SException;

    /**
     * 
     * This method is used to submit forms to the grants.guv
     * 
     * @param pdDoc Proposal Development Document.
     * @return true if submitted false otherwise.
     * @throws S2SException 
     */
    public boolean submitApplication(ProposalDevelopmentDocument pdDoc) throws S2SException;

    /**
     * 
     * This method is used to validate application before submission.
     * 
     * @param pdDoc Proposal Development Document.
     * @return boolean true if valid false otherwise.
     * @throws S2SException 
     */
    public boolean validateApplication(ProposalDevelopmentDocument pdDoc) throws S2SException;
}
