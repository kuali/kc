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
