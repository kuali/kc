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
package org.kuali.coeus.propdev.impl.s2s;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "S2S_APPLICATION")
public class S2sApplication extends KcPersistableBusinessObjectBase {

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name = "APPLICATION")
    @Lob
    private String application;

    @OneToMany(targetEntity = S2sAppAttachments.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false)
    private List<S2sAppAttachments> s2sAppAttachmentList;

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * Gets the s2sAttachments attribute. 
     * @return Returns the s2sAttachments.
     */
    public List<S2sAppAttachments> getS2sAppAttachmentList() {
        return s2sAppAttachmentList;
    }

    /**
     * Sets the s2sAttachments attribute value.
     * @param s2sAppAttachmentList The s2sAttachments to set.
     */
    public void setS2sAppAttachmentList(List<S2sAppAttachments> s2sAppAttachmentList) {
        this.s2sAppAttachmentList = s2sAppAttachmentList;
    }
}
