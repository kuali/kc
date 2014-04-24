package org.kuali.coeus.propdev.impl.core;

import org.kuali.coeus.common.framework.person.PropAwardPersonRoleValuesFinder;
import org.kuali.rice.krad.uif.view.ViewModel;

public class ProposalPersonRoleValuesFinder extends PropAwardPersonRoleValuesFinder {

	@Override
	protected String getSponsorCodeFromModel(ViewModel model) {
		return ((ProposalDevelopmentDocumentForm) model).getProposalDevelopmentDocument().getDevelopmentProposal().getSponsorCode();
	}

}
