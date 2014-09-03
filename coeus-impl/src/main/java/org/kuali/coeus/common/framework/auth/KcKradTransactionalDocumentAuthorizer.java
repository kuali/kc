package org.kuali.coeus.common.framework.auth;

import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.TransactionalDocumentAuthorizer;

public interface KcKradTransactionalDocumentAuthorizer extends TransactionalDocumentAuthorizer {
	
	public boolean canDeleteDocument(Document document, Person user);

}
