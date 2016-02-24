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
package org.kuali.coeus.propdev.impl.attachment;

import javax.persistence.*;

import org.kuali.coeus.common.framework.print.KcAttachmentDataSource;
import org.kuali.coeus.propdev.api.attachment.NarrativeAttachmentContract;

@Entity
@Table(name = "NARRATIVE_ATTACHMENT")
@AttributeOverride(name = "data",  column = @Column(name = "NARRATIVE_DATA"))
@IdClass(Narrative.NarrativeId.class)
public class NarrativeAttachment extends KcAttachmentDataSource implements NarrativeAttachmentContract {

    @Id
    @OneToOne(cascade = { CascadeType.REFRESH })
    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER"), @JoinColumn(name = "MODULE_NUMBER", referencedColumnName = "MODULE_NUMBER") })
    private Narrative narrative;

    @Override
    public Integer getModuleNumber() {
        return this.getNarrative().getModuleNumber();
    }

    public void setModuleNumber(Integer moduleNumber) {
        this.getNarrative().setModuleNumber(moduleNumber);
    }

    @Override
    public String getProposalNumber() {
        return this.getNarrative().getProposalNumber();
    }

    public Narrative getNarrative() {
        return narrative;
    }

    public void setNarrative(Narrative narrative) {
        this.narrative = narrative;
    }
}
