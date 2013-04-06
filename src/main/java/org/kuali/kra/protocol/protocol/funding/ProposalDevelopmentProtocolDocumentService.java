/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.protocol.protocol.funding;

import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.specialreview.SpecialReviewHelper;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.protocol.ProtocolDocumentBase;

public interface ProposalDevelopmentProtocolDocumentService <GenericProtocolDocument extends ProtocolDocumentBase> {

    public ProtocolDocumentBase createProtocolDocument(ProposalDevelopmentForm proposalDevelopmentForm) throws Exception;
    public boolean isAuthorizedCreateProtocol(SpecialReviewHelper specialReviewHelper);
    public void populateDocumentOverview(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument);
    public void populateRequiredFields(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument) throws Exception;
    public void populateProtocolPerson_Investigator(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument);
    public void populateProtocolFundingSource(DevelopmentProposal developmentProposal, ProtocolDocumentBase protocolDocument);
    public void initializeAuthorization(ProtocolDocumentBase protocolDocument);
}
