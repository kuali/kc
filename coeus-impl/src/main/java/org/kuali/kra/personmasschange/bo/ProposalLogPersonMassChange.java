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
package org.kuali.kra.personmasschange.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * Defines the fields for a Proposal Log Person Mass Change.
 */
public class ProposalLogPersonMassChange extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 3351381999001017550L;
    
    private long proposalLogPersonMassChangeId;
    private long personMassChangeId;
    private boolean principalInvestigator;
    
    private PersonMassChange personMassChange;

    public long getProposalLogPersonMassChangeId() {
        return proposalLogPersonMassChangeId;
    }

    public void setProposalLogPersonMassChangeId(long proposalLogPersonMassChangeId) {
        this.proposalLogPersonMassChangeId = proposalLogPersonMassChangeId;
    }

    public long getPersonMassChangeId() {
        return personMassChangeId;
    }

    public void setPersonMassChangeId(long personMassChangeId) {
        this.personMassChangeId = personMassChangeId;
    }

    public boolean isPrincipalInvestigator() {
        return principalInvestigator;
    }

    public void setPrincipalInvestigator(boolean principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }
    
    public PersonMassChange getPersonMassChange() {
        return personMassChange;
    }

    public void setPersonMassChange(PersonMassChange personMassChange) {
        this.personMassChange = personMassChange;
    }

    /**
     * Determines whether this Person Mass Change is required.
     * 
     * @return true if any of the fields are true, false otherwise
     */
    public boolean requiresChange() {
        return principalInvestigator;
    }

}
