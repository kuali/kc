package org.kuali.kra.dao.ojb;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.document.DocumentBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.OjbCollectionAware;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 *
 */
public class BaseReportDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware {
    /**
     *
     * @param workflowDocument
     * @return
     */
    protected boolean isInExcludedState(KualiWorkflowDocument workflowDocument) {
        return workflowDocument.stateIsDisapproved() || workflowDocument.stateIsCanceled() || workflowDocument.stateIsException();
    }

    /**
     *
     * @param document
     * @return
     * @throws WorkflowException
     */
    protected boolean shouldDataBeIncluded(DocumentBase document) throws WorkflowException {
        KualiWorkflowDocument workflowDocument = loadWorkflowDocument(document);
        return !isInExcludedState(workflowDocument);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    private KualiWorkflowDocument loadWorkflowDocument(DocumentBase document) throws WorkflowException {
        String docNumber = document.getDocumentHeader().getDocumentNumber();
        ResearchDocumentBase doc = (ResearchDocumentBase) getDocumentService().getByDocumentHeaderId(docNumber);
        return doc.getDocumentHeader().getWorkflowDocument();
    }

    private DocumentService getDocumentService() {
        return KraServiceLocator.getService(DocumentService.class);
    }
}
