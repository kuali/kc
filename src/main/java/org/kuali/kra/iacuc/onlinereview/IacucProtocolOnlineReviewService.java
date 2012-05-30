/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc.onlinereview;

import java.sql.Date;

import org.kuali.kra.protocol.ProtocolOnlineReviewDocument;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;
import org.kuali.rice.kew.api.exception.WorkflowException;

public interface IacucProtocolOnlineReviewService extends ProtocolOnlineReviewService{
    String IACUC_PROTOCOL_ONLINE_REVIEW_DOCUMENT_TYPE = "IacucProtocolOnlineReviewDocument";
//    static final String ONLINE_REVIEW_DOCUMENT_DESCRIPTION_FORMAT = "%s/Protocol# %s";
//    
//    /**
//     * Document type code for online review.
//     */
//    String PROTOCOL_ONLINE_REVIEW_DOCUMENT_TYPE_CODE = "PTRV";
//    
//    /**
//     * Name of the online review document.
//     */
//    String PROTOCOL_ONLINE_REVIEW_DOCUMENT_TYPE = "ProtocolOnlineReviewDocument";
//
//    List<CommitteeMembership> getAvailableCommitteeMembersForCurrentSubmission(Protocol protocol);
//    List<ProtocolOnlineReviewDocument> getProtocolReviewDocumentsForCurrentSubmission(Protocol protocol);
//    String getProtocolOnlineReviewDocumentDescription( String protocolNumber, String piName );

    public ProtocolOnlineReviewDocument createProtocolOnlineReviewDocument(ProtocolSubmission protocolSubmission,
            ProtocolReviewer protocolReviewer, String documentDescription, String documentExplanation,
            String documentOrganizationDocumentNumber, Date dateRequested, Date dateDue, String principalId)
            throws WorkflowException;
}
