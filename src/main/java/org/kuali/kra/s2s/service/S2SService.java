/*
 * Copyright 2007 The Kuali Foundation.
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
import java.util.Map;

import org.kuali.kra.budget.bo.BudgetSubAwards;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;

public interface S2SService {
    public boolean isS2SCandidate(String proposalNumber);
    public List<S2sOpportunity> searchOpportunity(String cfdaNumber, String opportunityId, String competitionId);
    public List<S2sOppForms> parseOpportunityForms(S2sOpportunity opportunityInfoBean);
    public boolean validateApplication(String proposalNumber);
    public boolean submitApplication(String proposalNumber);
    public Map<String, Boolean> getS2SAuthZRights(String proposalNumber);
    public String getDefaultS2SSubmissionType();
    public void refreshGrantsGov(String proposalNumber);
    public S2sOpportunity getOpportunity(String proposalNumber);
    public Object getStatusDetails(String ggTrackingId,String proposalNumber);
//    public boolean saveOpportunity(S2sOpportunity opportunity);
//    public boolean deleteOpportunity(S2sOpportunity opportunity);
//    public boolean saveGrantsGovData(List objects);
//    public List getData(S2SHeader s2sHeader);
//  public boolean saveFormsNSubmitApplication(List formsList, OpportunityInfoBean oppInfoBean, S2SHeader header);
    public void printForm(List<S2sOppForms> formsList);
    public List<BudgetSubAwards> getXmlFromPureEdge(List<BudgetSubAwards> subAwardList);
}
