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
package org.kuali.kra.irb.actions.modifysubmission;

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.proccessbillable.ProtocolProccessBillableService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.rice.kns.service.DocumentService;

/**
 * 
 * This class implements the required functions to change and persist a protocol submission.
 */
public class ProtocolModifySubmissionServiceImpl extends ProtocolProccessBillableService implements ProtocolModifySubmissionService  {
    
    private DocumentService documentService;

    /**
     * 
     * @see org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionService#modifySubmisison(org.kuali.kra.irb.actions.submit.ProtocolSubmission, org.kuali.kra.irb.actions.modifysubmission.ProtocolModifySubmissionAction)
     */
    public void modifySubmisison(ProtocolDocument protocolDocument, ProtocolModifySubmissionAction bean) throws Exception {
        protocolDocument.getProtocol().getProtocolSubmission().setSubmissionTypeCode(bean.getSubmissionTypeCode());
        protocolDocument.getProtocol().getProtocolSubmission().setSubmissionTypeQualifierCode(bean.getSubmissionQualifierTypeCode());
        this.proccessBillable(protocolDocument.getProtocol(), bean.isBillable()); 
        documentService.saveDocument(protocolDocument);
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
