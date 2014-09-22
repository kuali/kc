package org.kuali.coeus.common.framework.auth;

import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.TransactionalDocumentAuthorizer;

import java.util.Set;

public interface KcKradTransactionalDocumentAuthorizer extends TransactionalDocumentAuthorizer {
	
	boolean canDeleteDocument(Document document, Person user);

    Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes);
}
