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
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.view.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProposalPersonValueFinder extends UifKeyValuesFinderBase{
    @Override
    public List<KeyValue> getKeyValues(ViewModel model) {
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) model;
        List<KeyValue> keyValues = new ArrayList<>();
        // if a person is in multiple child proposals, then personnel attachments for that
        // person can only be managed at the parent. If however, the person is not in multiple children,
        // then they can only be managed at the child.
        for (ProposalPerson person : form.getDevelopmentProposal().getProposalPersons()) {
            if (form.getDevelopmentProposal().isInHierarchy()) {
                if (renderEditForPersonnelAttachment(person.getPersonId(), person.getRolodexId(), form.getDevelopmentProposal())) {
                    keyValues.add(new ConcreteKeyValue(person.getProposalPersonNumber().toString(), person.getFullName()));
                }
            } else {
                keyValues.add(new ConcreteKeyValue(person.getProposalPersonNumber().toString(), person.getFullName()));
            }
        }
        return keyValues;
    }

    protected boolean renderEditForPersonnelAttachment(String personId, Integer rolodexId, DevelopmentProposal proposal) {
        boolean inMultiple = StringUtils.isNotBlank(personId) ? getProposalHierarchyService().employeePersonInMultipleProposals(personId, proposal)
                : getProposalHierarchyService().nonEmployeePersonInMultipleProposals(rolodexId, proposal);
        return (proposal.isParent()) ? inMultiple : !inMultiple;
    }

    protected ProposalHierarchyService getProposalHierarchyService() {
        return KcServiceLocator.getService(ProposalHierarchyService.class);
    }

}
