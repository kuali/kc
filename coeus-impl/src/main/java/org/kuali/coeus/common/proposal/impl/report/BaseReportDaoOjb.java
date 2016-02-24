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
package org.kuali.coeus.common.proposal.impl.report;

import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.document.DocumentBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.util.OjbCollectionAware;

/**
 *
 */
public class BaseReportDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware {
    /**
     *
     * @param workflowDocument
     * @return
     */
    protected boolean isInExcludedState(WorkflowDocument workflowDocument) {
        return workflowDocument.isDisapproved() || workflowDocument.isCanceled() || workflowDocument.isException();
    }

    /**
     *
     * @param document
     * @return
     * @throws WorkflowException
     */
    protected boolean shouldDataBeIncluded(DocumentBase document) throws WorkflowException {
        WorkflowDocument workflowDocument = loadWorkflowDocument(document);
        return !isInExcludedState(workflowDocument);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    private WorkflowDocument loadWorkflowDocument(DocumentBase document) throws WorkflowException {
        String docNumber = document.getDocumentHeader().getDocumentNumber();
        KcTransactionalDocumentBase doc = (KcTransactionalDocumentBase) getDocumentService().getByDocumentHeaderId(docNumber);
        return doc.getDocumentHeader().getWorkflowDocument();
    }

    private DocumentService getDocumentService() {
        return KcServiceLocator.getService(DocumentService.class);
    }
}
