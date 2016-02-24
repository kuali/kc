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
package org.kuali.coeus.propdev.impl.person.attachment;

import javax.persistence.*;

import org.kuali.coeus.common.framework.print.KcAttachmentDataSource;
import org.kuali.coeus.propdev.api.person.attachment.ProposalPersonBiographyAttachmentContract;

/**
 * 
 * This bo for eps_prop_person_bio_attachment
 */
@Entity
@Table(name = "EPS_PROP_PERSON_BIO_ATTACHMENT")
@IdClass(ProposalPersonBiography.ProposalPersonBiographyId.class)
public class ProposalPersonBiographyAttachment extends KcAttachmentDataSource implements ProposalPersonBiographyAttachmentContract {


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
