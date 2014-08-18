package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.person.PropAwardPersonRoleValuesFinder;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.view.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProposalPersonRoleValuesFinder extends PropAwardPersonRoleValuesFinder {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PropAwardPersonRoleValuesFinder.class);

    @Override
	protected String getSponsorCodeFromModel(ViewModel model) {
		return ((ProposalDevelopmentDocumentForm) model).getProposalDevelopmentDocument().getDevelopmentProposal().getSponsorCode();
	}

    @Override
    protected boolean piAlreadyExists(ViewModel model, InputField field) {
        try {
            String roleId = (String) PropertyUtils.getProperty(model, field.getBindingInfo().getBindingPath());
            if (roleId != null && roleId.equals(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR)) {
                return false;
            }
        } catch (Exception e) {
            LOG.info("could not retrieve role from the input field ");
        }

        for (ProposalPerson person : ((ProposalDevelopmentDocumentForm) model).getDevelopmentProposal().getProposalPersons()) {
            if (person.getRole().getCode().equals(PropAwardPersonRole.PRINCIPAL_INVESTIGATOR)) {
                return true;
            }
        }
        return false;
    }
}
