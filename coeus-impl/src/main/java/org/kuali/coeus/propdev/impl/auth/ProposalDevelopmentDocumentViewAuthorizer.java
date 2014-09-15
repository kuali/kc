package org.kuali.coeus.propdev.impl.auth;

import org.kuali.coeus.common.framework.auth.KcKradTransactionalDocumentViewAuthorizerBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("proposalDevelopmentDocumentViewAuthorizer")
@Scope("prototype")
public class ProposalDevelopmentDocumentViewAuthorizer extends KcKradTransactionalDocumentViewAuthorizerBase {

    @Override
    public Set<String> getEditModes(View view, ViewModel model, Person user, Set<String> editModes) {
        Document document = ((DocumentFormBase) model).getDocument();
        Set currentEditModes = ((ProposalDevelopmentDocumentAuthorizer)getDocumentAuthorizer()).getEditModes(document,user,new HashSet<String>());
        return super.getEditModes(view, model, user, currentEditModes);
    }
}
