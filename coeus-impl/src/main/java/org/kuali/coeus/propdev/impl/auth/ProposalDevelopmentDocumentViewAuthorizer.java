/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.auth;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.KcKradTransactionalDocumentViewAuthorizerBase;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.datadictionary.DocumentEntry;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.container.Group;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("proposalDevelopmentDocumentViewAuthorizer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProposalDevelopmentDocumentViewAuthorizer extends KcKradTransactionalDocumentViewAuthorizerBase {

    @Override
    public boolean canViewGroup(View view, ViewModel model, Group group, String groupId, Person user) {
        boolean success = super.canViewGroup(view, model, group, groupId, user);
        if (group.getId().contains(ProposalDevelopmentConstants.KradConstants.PERSONNEL_QUESTIONNAIRE)) {
            ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) model;
            ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
            int index = getIndexFromCollectionGroupId(ProposalDevelopmentConstants.KradConstants.PERSONNEL_QUESTIONNAIRE, groupId);
            ProposalPerson proposalPerson = document.getDevelopmentProposal().getProposalPersons().get(index);

            success &= ((ProposalDevelopmentDocumentAuthorizer)getDocumentAuthorizer()).canViewCertification(document, user) ||
            		((ProposalDevelopmentDocumentAuthorizer)getDocumentAuthorizer()).hasCertificationPermissions(document, user, proposalPerson);
        }

        return success;
    }

    /**
     * To be used with group collection items.
     * Gets the index of the group that is passed in.
     *
     * @param prefix the predefined id property for the group.  Defined in the view p:id="someGroupIdentifier"
     * @param groupId is in format someGroupIdentifier_line***, where *** is an int 0..n
     * @return parsed int value
     */
    public int getIndexFromCollectionGroupId(String prefix, String groupId){
        String indexStr = groupId.substring(prefix.length() + UifConstants.IdSuffixes.LINE.length());
        return Integer.parseInt(indexStr);
    }

    @Override
	public Set<String> getActionFlags(View view, ViewModel model, Person user, Set<String> actions) {

		Document document = ((DocumentFormBase) model).getDocument();
        DevelopmentProposal developmentProposal = ((ProposalDevelopmentDocument)document).getDevelopmentProposal();

		if (actions.contains(ProposalDevelopmentConstants.PropDevDocumentActions.SUBMIT_TO_SPONSOR) && ! canCreateInstitutionalProposal(document, user)) {
            actions.remove(ProposalDevelopmentConstants.PropDevDocumentActions.SUBMIT_TO_SPONSOR);
        }

        if (actions.contains(KRADConstants.KUALI_ACTION_CAN_EDIT) && !canEditView(view, model, user)) {
            actions.remove(KRADConstants.KUALI_ACTION_CAN_EDIT);
        }

        if (canNotifyProposalPerson(document,user)) {
            actions.add(ProposalDevelopmentConstants.PropDevDocumentActions.NOTIFY_PROPOSAL_PERSONS);
        }

        if (actions.contains(KRADConstants.KUALI_ACTION_CAN_SUPER_USER_APPROVE) && developmentProposal.isChild()) {
            actions.remove(KRADConstants.KUALI_ACTION_CAN_SUPER_USER_APPROVE);
        }

        if (actions.contains(KRADConstants.KUALI_ACTION_CAN_SUPER_USER_DISAPPROVE) && developmentProposal.isChild()) {
            actions.remove(KRADConstants.KUALI_ACTION_CAN_SUPER_USER_DISAPPROVE);
        }

        if (actions.contains(KRADConstants.KUALI_ACTION_CAN_SUPER_USER_TAKE_ACTION) && developmentProposal.isChild()) {
            actions.remove(KRADConstants.KUALI_ACTION_CAN_SUPER_USER_TAKE_ACTION);
        }

		return super.getActionFlags(view, model, user, actions);
	}

    private boolean canCreateInstitutionalProposal(Document document, Person user) {
    	boolean hasPermission = false;
    	initializeDocumentAuthorizerIfNecessary(document);
    	if(getDocumentAuthorizer() instanceof ProposalDevelopmentDocumentAuthorizer) {
    		hasPermission = ((ProposalDevelopmentDocumentAuthorizer)getDocumentAuthorizer()).canCreateInstitutionalProposal(document, user);
    	}
    	return hasPermission;
    }

    private boolean canNotifyProposalPerson(Document document, Person user) {
        boolean hasPermission = false;
        initializeDocumentAuthorizerIfNecessary(document);
        if(getDocumentAuthorizer() instanceof ProposalDevelopmentDocumentAuthorizer) {
            hasPermission = ((ProposalDevelopmentDocumentAuthorizer)getDocumentAuthorizer()).canNotifyProposalPerson(document, user);
        }
        return hasPermission;
    }

    @Override
    public boolean canEditGroup(View view, ViewModel model, Group group, String groupId, Person user) {
        if (((ProposalDevelopmentDocumentForm)model).isViewOnly()){
            return false;
        }
        return super.canEditGroup(view,model,group,groupId,user);
    }

    @Override
    public boolean canEditView(View view, ViewModel model, Person user) {
        ProposalDevelopmentDocumentForm proposalDevelopmentDocumentForm = (ProposalDevelopmentDocumentForm) model;
        if(!proposalDevelopmentDocumentForm.isViewOnly()) {
            return transactionDocumentViewAuthorizerCanEditView(view, model, user) && userHasLock(user, model);
        }

        return false;
    }

    /**
     * canEditView method copied from TransactionalDocumentViewAuthorizerBase with Pessimistic Lock Service.
     */
    public boolean transactionDocumentViewAuthorizerCanEditView(View view, ViewModel model, Person user) {
        boolean canEditView = documentViewAuthorizerCanEditView(view, model, user);

        Map<String, Object> context = view.getContext();
        DocumentEntry documentEntry = (DocumentEntry) context.get(UifConstants.ContextVariableNames.DOCUMENT_ENTRY);

        if (!documentEntry.getUsePessimisticLocking()) {
            return canEditView;
        }

        DocumentFormBase documentForm = (DocumentFormBase) model;
        Document document = documentForm.getDocument();

        if (canEditView) {
            return getPessimisticLockService().establishPessimisticLocks(document, user, canEditView);
        }
        return canEditView;
    }

    /**
     * canEditView method copied from DocumentViewAuthorizerBase.
     */
    public boolean documentViewAuthorizerCanEditView(View view, ViewModel model, Person user) {
        DocumentFormBase documentForm = (DocumentFormBase) model;

        return viewHelperServiceCanEditView(view, model, user) && canEdit(documentForm.getDocument(), user);
    }

    /**
     * canEditView method copied from ViewHelperServiceImpl.
     */
    public boolean viewHelperServiceCanEditView(View view, ViewModel model, Person user) {
        Map<String, String> additionalPermissionDetails = new HashMap<>();
        additionalPermissionDetails.put(KimConstants.AttributeConstants.NAMESPACE_CODE, view.getNamespaceCode());
        additionalPermissionDetails.put(KimConstants.AttributeConstants.VIEW_ID, model.getViewId());

        if (permissionExistsByTemplate(model, KRADConstants.KRAD_NAMESPACE,
                KimConstants.PermissionTemplateNames.EDIT_VIEW, additionalPermissionDetails)) {
            return isAuthorizedByTemplate(model, KRADConstants.KRAD_NAMESPACE,
                    KimConstants.PermissionTemplateNames.EDIT_VIEW, user.getPrincipalId(), additionalPermissionDetails,
                    null);
        }

        return true;
    }

    public boolean userHasLock(Person user, ViewModel model) {
        ProposalDevelopmentDocumentForm form = ((ProposalDevelopmentDocumentForm)model);
        for (PessimisticLock lock : form.getDocument().getPessimisticLocks()) {
                if (lock.isOwnedByUser(user)) {
                    final String lockRegion = StringUtils.split(lock.getLockDescriptor(), "-")[1];
                    if (lock.isOwnedByUser(user) && lockRegion.equals(KraAuthorizationConstants.LOCK_DESCRIPTOR_PROPOSAL)) {
                       return true;
                    }
            }
        }
        return false;
    }
}
