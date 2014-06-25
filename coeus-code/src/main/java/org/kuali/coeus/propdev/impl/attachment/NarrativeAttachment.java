/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.attachment;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.propdev.api.attachment.NarrativeAttachmentContract;

@Entity
@Table(name = "NARRATIVE_ATTACHMENT")
@AttributeOverride(name = "data",  column = @Column(name = "NARRATIVE_DATA"))
@IdClass(Narrative.NarrativeId.class)
public class NarrativeAttachment extends AttachmentDataSource implements NarrativeAttachmentContract {

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
