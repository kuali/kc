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
package org.kuali.kra.committee.test;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

import java.util.ArrayList;

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
        DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
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
