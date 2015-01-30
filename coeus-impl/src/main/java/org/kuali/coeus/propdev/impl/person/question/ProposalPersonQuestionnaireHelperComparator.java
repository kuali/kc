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
package org.kuali.coeus.propdev.impl.person.question;

import org.kuali.coeus.common.framework.person.PersonRolodexComparator;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonQuestionnaireHelper;

import java.util.Comparator;

/**
 * 
 * This class runs the comparison of the helpers based on the comparison from personComp for each of the helper's internal person.
 */
public class ProposalPersonQuestionnaireHelperComparator implements Comparator<ProposalPersonQuestionnaireHelper> {

    @Override
    public int compare(ProposalPersonQuestionnaireHelper helper1, ProposalPersonQuestionnaireHelper helper2) {
        PersonRolodexComparator personComp = new PersonRolodexComparator();
        return personComp.compare(helper1.getProposalPerson(), helper2.getProposalPerson());
    }

}
