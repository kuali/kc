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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.workflow.AbstractProjectPersonDerivedRoleTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("proposalRoleTypeService")
public class ProposalPersonDerivedRoleTypeServiceImpl extends AbstractProjectPersonDerivedRoleTypeServiceImpl {

    @Autowired
    @Qualifier("proposalPersonService")
	private ProposalPersonService proposalPersonService;
	
	protected List<String> requiredAttributes = new ArrayList<String>();
	{
		requiredAttributes.add(KcKimAttributes.PROPOSAL);
	}

    public ProposalPersonService getProposalPersonService() {
        return proposalPersonService;
    }

    public void setProposalPersonService(ProposalPersonService proposalPersonService) {
        this.proposalPersonService = proposalPersonService;
    }

    @Override
    protected List<? extends AbstractProjectPerson> getProjectPersons(Map<String, String> qualification) {
        String proposalNumber = qualification.get(KcKimAttributes.PROPOSAL);

        if (StringUtils.isNotBlank(proposalNumber)) {
            return getProposalPersonService().getProposalKeyPersonnel(proposalNumber);
        } else {
            return new ArrayList<AbstractProjectPerson>();
        }
    }

}
