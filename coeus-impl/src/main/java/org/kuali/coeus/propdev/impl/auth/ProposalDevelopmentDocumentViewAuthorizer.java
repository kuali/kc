package org.kuali.coeus.propdev.impl.auth;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.auth.KcKradTransactionalDocumentViewAuthorizerBase;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.kim.api.identity.Person;
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

		if (actions.contains(ProposalDevelopmentConstants.PropDevDocumentActions.SUBMIT_TO_SPONSOR) && ! canCreateInstitutionalProposal(document, user)) {
            actions.remove(ProposalDevelopmentConstants.PropDevDocumentActions.SUBMIT_TO_SPONSOR);
        }

        if (actions.contains(KRADConstants.KUALI_ACTION_CAN_EDIT) && !canEditView(view, model, user)) {
            actions.remove(KRADConstants.KUALI_ACTION_CAN_EDIT);
        }

        if (canNotifyProposalPerson(document,user)) {
            actions.add(ProposalDevelopmentConstants.PropDevDocumentActions.NOTIFY_PROPOSAL_PERSONS);
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
       return super.canEditView(view, model, user) && userHasLock(user, pdForm);
    }

    public boolean userHasLock(Person user, ProposalDevelopmentDocumentForm form) {
        String pageRegion = ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).getLockRegionFromPage(form.getPageId());
        for (PessimisticLock lock : form.getDocument().getPessimisticLocks()) {
            if (lock.getLockDescriptor() == null) {
                if (lock.isOwnedByUser(user)) {
                    return true;
                }
            } else {
                final String lockRegion = StringUtils.split(lock.getLockDescriptor(), "-")[1];
                if (lock.isOwnedByUser(user) && lockRegion.equals(pageRegion)) {
                    return true;
                }
            }
        }
        return false;
    }
}
