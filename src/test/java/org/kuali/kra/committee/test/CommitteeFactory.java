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
package org.kuali.kra.committee.test;

import java.util.ArrayList;

import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

public class CommitteeFactory {

    protected static final String DEFAULT_DOCUMENT_DESCRIPTION = "Committee Web Test";
    protected static final String DEFAULT_TYPE_CODE = "1"; // IRB
    protected static final Integer DEFAULT_MAX_PROTOCOLS = 10;
    protected static final String DEFAULT_HOME_UNIT_NUMBER = "000001";
    protected static final Integer DEFAULT_MIN_MEMBERS_REQUIRED = 3;
    protected static final String DEFAULT_NAME = "Committee Test ";
    protected static final Integer DEFAULT_ADV_SUBMISSION_DAYS_REQUIRED = 1;
    protected static final String DEFAULT_REVIEW_TYPE_CODE = "1"; // FULL
  
    public static CommitteeDocument createCommitteeDocument(String committeeId) throws WorkflowException {
        DocumentService documentService = KraServiceLocator.getService("kraDocumentService");//KRADServiceLocatorWeb.getDocumentService();
        CommitteeDocument committeeDocument = (CommitteeDocument) documentService.getNewDocument("CommitteeDocument");
        setRequiredFields(committeeDocument, committeeId);
        documentService.saveDocument(committeeDocument);
        return committeeDocument;
    }

    private static void setRequiredFields(CommitteeDocument document, String committeeId) {
        Committee committee = document.getCommittee();
        document.getDocumentHeader().setDocumentDescription(DEFAULT_DOCUMENT_DESCRIPTION);
        document.setDocumentNextvalues(new ArrayList<DocumentNextvalue>());
        document.setCommitteeId(committeeId);
        committee.setCommitteeDocument(document);
        committee.setCommitteeId(committeeId);
        committee.setCommitteeTypeCode(DEFAULT_TYPE_CODE);
        committee.setMaxProtocols(DEFAULT_MAX_PROTOCOLS);
        committee.setHomeUnitNumber(DEFAULT_HOME_UNIT_NUMBER);
        committee.setMinimumMembersRequired(DEFAULT_MIN_MEMBERS_REQUIRED);
        committee.setCommitteeName(DEFAULT_NAME + committeeId);
        committee.setAdvancedSubmissionDaysRequired(DEFAULT_ADV_SUBMISSION_DAYS_REQUIRED);
        committee.setReviewTypeCode(DEFAULT_REVIEW_TYPE_CODE);
    }
}
