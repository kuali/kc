/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package org.kuali.coeus.propdev.impl.person.attachment;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.propdev.api.person.attachment.ProposalPersonBiographyAttachmentContract;

/**
 * 
 * This bo for eps_prop_person_bio_attachment
 */
@Entity
@Table(name = "EPS_PROP_PERSON_BIO_ATTACHMENT")
@AttributeOverride(name = "data",  column = @Column(name = "BIO_DATA"))
@IdClass(ProposalPersonBiography.ProposalPersonBiographyId.class)
public class ProposalPersonBiographyAttachment extends AttachmentDataSource implements ProposalPersonBiographyAttachmentContract {


    @Id
    @OneToOne(cascade = { CascadeType.REFRESH })
    @JoinColumns({ @JoinColumn(name = "PROP_PERSON_NUMBER", referencedColumnName = "PROP_PERSON_NUMBER"), @JoinColumn(name = "BIO_NUMBER", referencedColumnName = "BIO_NUMBER"), @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER") })
    private ProposalPersonBiography proposalPersonBiography;

    public ProposalPersonBiographyAttachment() {
        super();
    }

    @Override
    public Integer getProposalPersonNumber() {
        return proposalPersonBiography.getProposalPersonNumber();
    }

    public void setProposalPersonNumber(Integer proposalPersonNumber) {
        proposalPersonBiography.setProposalPersonNumber(proposalPersonNumber);
    }

    @Override
    public String getProposalNumber() {
        return proposalPersonBiography.getProposalNumber();
    }

    public void setProposalNumber(String proposalNumber) {
        proposalPersonBiography.setProposalNumber(proposalNumber);
    }

    @Override
    public Integer getBiographyNumber() {
        return proposalPersonBiography.getBiographyNumber();
    }

    public void setBiographyNumber(Integer biographyNumber) {
        proposalPersonBiography.setBiographyNumber(biographyNumber);
    }

    public ProposalPersonBiography getProposalPersonBiography() {
        return proposalPersonBiography;
    }

    public void setProposalPersonBiography(ProposalPersonBiography proposalPersonBiography) {
        this.proposalPersonBiography = proposalPersonBiography;
    }

}
