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
package org.kuali.kra.institutionalproposal;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class ProposalStatus extends KcPersistableBusinessObjectBase {

    public static final Integer PENDING = 1;

    public static final Integer FUNDED = 2;

    private static final long serialVersionUID = 1L;

    private Integer proposalStatusCode;

    private String description;

    public ProposalStatus() {
    }

    public Integer getProposalStatusCode() {
        return proposalStatusCode;
    }

    public void setProposalStatusCode(Integer proposalStatusCode) {
        this.proposalStatusCode = proposalStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isFunded() {
        return proposalStatusCode.intValue() == FUNDED.intValue();
    }

    public boolean isPending() {
        return proposalStatusCode.intValue() == PENDING.intValue();
    }
}
