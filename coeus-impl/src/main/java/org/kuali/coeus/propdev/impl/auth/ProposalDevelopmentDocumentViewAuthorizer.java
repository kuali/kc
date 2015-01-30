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
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.impl.lock.KcPessimisticLockService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.container.Group;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("proposalDevelopmentDocumentViewAuthorizer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProposalDevelopmentDocumentViewAuthorizer extends KcKradTransactionalDocumentViewAuthorizerBase {

    @Autowired
    @Qualifier("kcPessimisticLockService")
    private KcPessimisticLockService kcPessimisticLockService;

    @Override
    public boolean canViewGroup(View view, ViewModel model, Group group, String groupId, Person user) {
        boolean success = super.canViewGroup(view, model, group, groupId, user);
        if (group.getId().contains(ProposalDevelopmentConstants.KradConstants.PERSONNEL_QUESTIONNAIRE)) {
            ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) model;
            ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
            int index = getIndexFromCollectionGroupId(ProposalDevelopmentConstants.KradConstants.PERSONNEL_QUESTIONNAIRE, groupId);
            ProposalPerson proposalPerson = document.getDevelopmentProposal().getProposalPersons().get(index);

            success &= ((ProposalDevelopmentDocumentAuthorizer)getDocumentAuthorizer()).hasCertificationPermissions(document, user, proposalPerson);
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
       ProposalDevelopmentDocumentForm pdForm = ((ProposalDevelopmentDocumentForm)model);
       ProposalDevelopmentViewHelperService pdViewService = (ProposalDevelopmentViewHelperService) pdForm.getViewHelperService();
       pdViewService.setupLockRegions((ProposalDevelopmentDocumentForm) model);
       boolean canEdit = super.canEditView(view, model, user) && userHasLock(user, pdForm);
       establishNarrativeLockForAccessPage(pdForm, user, canEdit);
       return canEdit;
    }

    protected void establishNarrativeLockForAccessPage(ProposalDevelopmentDocumentForm form, Person user, boolean canEdit) {
        if (StringUtils.equals(form.getPageId(), Constants.PROP_DEV_PERMISSIONS_PAGE) &&
                getKcPessimisticLockService().isPessimisticLockNeeded(form.getProposalDevelopmentDocument(), user, canEdit, form.getDevelopmentProposal().getProposalNumber() + "-" + KraAuthorizationConstants.LOCK_DESCRIPTOR_NARRATIVES)) {
            PessimisticLock pessimisticLock = getPessimisticLockService().generateNewLock(form.getDocument().getDocumentNumber(),form.getDevelopmentProposal().getProposalNumber() + "-" + KraAuthorizationConstants.LOCK_DESCRIPTOR_NARRATIVES, user);
            form.getDocument().addPessimisticLock(pessimisticLock);
        }
    }

    public boolean userHasLock(Person user, ProposalDevelopmentDocumentForm form) {
        boolean retVal = false;
        String pageRegion = ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).getLockRegionFromPage(form.getPageId());
        boolean isNarrativeLocked = false;
        for (PessimisticLock lock : form.getDocument().getPessimisticLocks()) {
            if (lock.getLockDescriptor() == null) {
                if (lock.isOwnedByUser(user)) {
                    retVal = true;
                }
            } else {
                final String lockRegion = StringUtils.split(lock.getLockDescriptor(), "-")[1];
                if (StringUtils.equals(lockRegion,KraAuthorizationConstants.LOCK_DESCRIPTOR_NARRATIVES) && !lock.isOwnedByUser(user)) {
                    isNarrativeLocked = true;
                }
                if (lock.isOwnedByUser(user) && lockRegion.equals(pageRegion)) {
                    retVal = true;
                }
            }
        }
        if (StringUtils.equals(form.getPageId(),Constants.PROP_DEV_PERMISSIONS_PAGE) && isNarrativeLocked) {
            retVal = false;
        }
        return retVal;
    }

    public KcPessimisticLockService getKcPessimisticLockService() {
        return kcPessimisticLockService;
    }

    public void setKcPessimisticLockService(KcPessimisticLockService kcPessimisticLockService) {
        this.kcPessimisticLockService = kcPessimisticLockService;
    }
}
