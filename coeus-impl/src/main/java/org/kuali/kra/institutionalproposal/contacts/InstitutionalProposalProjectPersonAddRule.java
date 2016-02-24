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
package org.kuali.kra.institutionalproposal.contacts;

import org.kuali.rice.krad.rules.rule.BusinessRule;


public interface InstitutionalProposalProjectPersonAddRule extends BusinessRule {

    public static final String PROPOSAL_PROJECT_PERSON_LIST_ERROR_KEY = "projectPersonnelBean.contactRoleCode";
    public static final String ERROR_PROPOSAL_PROJECT_PERSON_PI_EXISTS = "error.institutionalProposalProjectPerson.pi.exists";
    public static final String ERROR_PROPOSAL_PROJECT_PERSON_EXISTS = "error.institutionalProposalProjectPerson.person.exists";
    public static final String ERROR_PROPOSAL_PROJECT_PERSON_DUPLICATE_UNITS = "error.institutionalProposalProjectPerson.duplicate.units";
    
    /**
     * This method should be called before adding a new Project Person
     * @param event
     * @return
     */
    boolean processAddInstitutionalProposalProjectPersonBusinessRules(InstitutionalProposalProjectPersonRuleAddEvent event);

}
