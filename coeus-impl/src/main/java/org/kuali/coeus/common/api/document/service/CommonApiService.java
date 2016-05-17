package org.kuali.coeus.common.api.document.service;

import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.ErrorMessage;

import java.util.List;


public interface CommonApiService {

    public void validatePerson(String personId, Integer rolodexId);

    public Object convertObject(Object input, Class clazz);

    public Document getDocumentFromDocId(Long documentNumber);

    public void routeDocument(Document document);

    public String getValidationErrors();

    public Document saveDocument(Document document) throws WorkflowException;

    public boolean isDocInModifiableState(WorkflowDocument workflowDocument);

    public List<ErrorMessage> getAuditErrors(Document document);

    public void clearErrors();

    }
