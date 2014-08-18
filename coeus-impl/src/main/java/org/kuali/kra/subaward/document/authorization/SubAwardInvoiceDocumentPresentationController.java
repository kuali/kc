/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.subaward.document.authorization;


import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kns.document.authorization.DocumentPresentationController;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentPresentationControllerBase;
import org.kuali.rice.krad.document.Document;

/**
 * Determines read-only fields on the Proposal Log maintenance document.
 */
public class SubAwardInvoiceDocumentPresentationController extends MaintenanceDocumentPresentationControllerBase
    implements DocumentPresentationController {

    private static final long serialVersionUID = -5900944810037193950L;

    @Override
    public boolean canEdit(Document document) {
        boolean canEdit = super.canEdit(document);
        if (canEdit) {
            if (document.getDocumentHeader().getWorkflowDocument().getStatus() == DocumentStatus.ENROUTE) {
                canEdit = false;
            }
        }
        return canEdit;
    }
}
