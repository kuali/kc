package org.kuali.kra.dao.ojb;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.document.DocumentBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.OjbCollectionAware;

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
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    private WorkflowDocument loadWorkflowDocument(DocumentBase document) throws WorkflowException {
        String docNumber = document.getDocumentHeader().getDocumentNumber();
        ResearchDocumentBase doc = (ResearchDocumentBase) getDocumentService().getByDocumentHeaderId(docNumber);
        return doc.getDocumentHeader().getWorkflowDocument();
    }

    private DocumentService getDocumentService() {
        return KraServiceLocator.getService(DocumentService.class);
    }
}
