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
package org.kuali.coeus.common.framework.auth;

import java.util.Set;

import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.TransactionalDocumentViewAuthorizerBase;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.stereotype.Service;

@Service("kcKradTransactionalDocumentViewAuthorizerBase")
public class KcKradTransactionalDocumentViewAuthorizerBase extends TransactionalDocumentViewAuthorizerBase implements KcKradTransactionalDocumentViewAuthorizer {

	private static final long serialVersionUID = 1L;

    @Override
    public Set<String> getEditModes(View view, ViewModel model, Person user, Set<String> editModes) {
        return super.getEditModes(view, model, user, getEditModes(((DocumentFormBase) model).getDocument(), user, editModes));
    }

    @Override
	public Set<String> getActionFlags(View view, ViewModel model, Person user, Set<String> actions) { 
        
		Document document = ((DocumentFormBase) model).getDocument();

		if (actions.contains(KcAuthConstants.DocumentActions.DELETE_DOCUMENT) && !canDeleteDocument(document, user)) {
            actions.remove(KcAuthConstants.DocumentActions.DELETE_DOCUMENT);
        }
		return super.getActionFlags(view, model, user, actions);
	}

/*
    The following methods are marked as final as they are not View specific and just delegate to the document authorizer.
    Any custom authorizing logic for these methods should be done in the document authorizer and not in the document
    view authorizer.  Only view specific authorization logic should be in view authorizers.
*/

    @Override
    public final Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
        initializeDocumentAuthorizerIfNecessary(document);
        return getDocumentAuthorizer() instanceof KcKradTransactionalDocumentAuthorizer ? ((KcKradTransactionalDocumentAuthorizer) getDocumentAuthorizer()).getEditModes(document, user, currentEditModes) : currentEditModes;
    }

    @Override
    public final boolean canDeleteDocument(Document document, Person user) {
        initializeDocumentAuthorizerIfNecessary(document);
        return getDocumentAuthorizer() instanceof KcKradTransactionalDocumentAuthorizer && ((KcKradTransactionalDocumentAuthorizer) getDocumentAuthorizer()).canDeleteDocument(document, user);
    }

    @Override
    public final boolean canEdit(Document document, Person user) {
        return super.canEdit(document, user);
    }

    @Override
    public final boolean canAnnotate(Document document, Person user) {
        return super.canAnnotate(document, user);
    }

    @Override
    public final boolean canReload(Document document, Person user) {
        return super.canReload(document, user);
    }

    @Override
    public final boolean canClose(Document document, Person user) {
        return super.canClose(document, user);
    }

    @Override
    public final boolean canSave(Document document, Person user) {
        return super.canSave(document, user);
    }

    @Override
    public final boolean canRoute(Document document, Person user) {
        return super.canRoute(document, user);
    }

    @Override
    public final boolean canCancel(Document document, Person user) {
        return super.canCancel(document, user);
    }

    @Override
    public final boolean canRecall(Document document, Person user) {
        return super.canRecall(document, user);
    }

    @Override
    public final boolean canCopy(Document document, Person user) {
        return super.canCopy(document, user);
    }

    @Override
    public final boolean canPerformRouteReport(Document document, Person user) {
        return super.canPerformRouteReport(document, user);
    }

    @Override
    public final boolean canBlanketApprove(Document document, Person user) {
        return super.canBlanketApprove(document, user);
    }

    @Override
    public final boolean canApprove(Document document, Person user) {
        return super.canApprove(document, user);
    }

    @Override
    public final boolean canDisapprove(Document document, Person user) {
        return super.canDisapprove(document, user);
    }

    @Override
    public final boolean canSendNoteFyi(Document document, Person user) {
        return super.canSendNoteFyi(document, user);
    }

    @Override
    public final boolean canFyi(Document document, Person user) {
        return super.canFyi(document, user);
    }

    @Override
    public final boolean canAcknowledge(Document document, Person user) {
        return super.canAcknowledge(document, user);
    }

    @Override
    public final boolean canEditDocumentOverview(Document document, Person user) {
        return super.canEditDocumentOverview(document, user);
    }

    @Override
    public final boolean canSendAnyTypeAdHocRequests(Document document, Person user) {
        return super.canSendAnyTypeAdHocRequests(document, user);
    }

    @Override
    public final boolean canTakeRequestedAction(Document document, String actionRequestCode, Person user) {
        return super.canTakeRequestedAction(document, actionRequestCode, user);
    }

    @Override
    public final boolean canSuperUserTakeAction(Document document, Person user) {
        return super.canSuperUserTakeAction(document, user);
    }

    @Override
    public final boolean canSuperUserApprove(Document document, Person user) {
        return super.canSuperUserApprove(document, user);
    }

    @Override
    public final boolean canSuperUserDisapprove(Document document, Person user) {
        return super.canSuperUserDisapprove(document, user);
    }
}
