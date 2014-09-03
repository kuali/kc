package org.kuali.coeus.common.framework.auth;

import java.util.Set;

import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.TransactionalDocumentViewAuthorizerBase;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("kcKradTransactionalDocumentViewAuthorizerBase")
@Scope("prototype")
public class KcKradTransactionalDocumentViewAuthorizerBase extends TransactionalDocumentViewAuthorizerBase {

	private static final long serialVersionUID = 1L;
	
	public Set<String> getActionFlags(View view, ViewModel model, Person user, Set<String> actions) { 
        
		Document document = ((DocumentFormBase) model).getDocument();
		
		if (actions.contains(KcAuthConstants.DocumentActions.DELETE_DOCUMENT) && ! canDeleteDocument(document, user)) {
            actions.remove(KcAuthConstants.DocumentActions.DELETE_DOCUMENT);
        }
		return super.getActionFlags(view, model, user, actions);
	}
	
	/**
	 * Default canDeleteDocument implementation
	 * @param document
	 * @param user
	 * @return if the document authorizer is an instance of KcKradTransactionalDocumentAuthorizer returns the result from KcKradTransactionalDocumentAuthorizer.canDelete
	 * else false
	 */
    public boolean canDeleteDocument(Document document, Person user) {
    	if(getDocumentAuthorizer() instanceof KcKradTransactionalDocumentAuthorizer) {
    		return ((KcKradTransactionalDocumentAuthorizer)getDocumentAuthorizer()).canDeleteDocument(document, user) ;
    	}
    	else 
    		return false;
    }

}
