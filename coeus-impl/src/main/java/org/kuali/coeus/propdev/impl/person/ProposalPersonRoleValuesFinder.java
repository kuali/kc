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
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.person.PropAwardPersonRoleValuesFinder;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.view.ViewModel;

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
