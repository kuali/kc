/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Represents the Proposal Investigator Certification <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
 * $Id: ProposalInvestigatorCertification.java,v 1.1 2007-09-03 21:55:00 lprzybyl Exp $
 */
public class ProposalInvestigatorCertification  extends KraPersistableBusinessObjectBase {
    private Integer proposalPersonNumber;
    private Integer proposalNumber;
    private Boolean certified;
    private Date dateCertified;
    private Date dateReceivedByOsp;

    /**
     * Gets the value of proposalPersonNumber
     *
     * @return the value of proposalPersonNumber
     */
    public final Integer getProposalPersonNumber() {
        return this.proposalPersonNumber;
    }

    /**
     * Sets the value of proposalPersonNumber
     *
     * @param argProposalPersonNumber Value to assign to this.proposalPersonNumber
     */
    public final void setProposalPersonNumber(Integer argProposalPersonNumber) {
        this.proposalPersonNumber = argProposalPersonNumber;
    }

    /**
     * Gets the value of proposalNumber
     *
     * @return the value of proposalNumber
     */
    public final Integer getProposalNumber() {
        return this.proposalNumber;
    }

    /**
     * Sets the value of proposalNumber
     *
     * @param argProposalNumber Value to assign to this.proposalNumber
     */
    public final void setProposalNumber(Integer argProposalNumber) {
        this.proposalNumber = argProposalNumber;
    }

    /**
     * Gets the value of certified
     *
     * @return the value of certified
     */
    public final Boolean isCertified() {
        return this.certified;
    }

    /**
     * Sets the value of certified
     *
     * @param argCertified Value to assign to this.certified
     */
    public final void setCertified(Boolean argCertified) {
        this.certified = argCertified;
    }

    /**
     * Gets the value of dateCertified
     *
     * @return the value of dateCertified
     */
    public final Date getDateCertified() {
        return this.dateCertified;
    }

    /**
     * Sets the value of dateCertified
     *
     * @param argDateCertified Value to assign to this.dateCertified
     */
    public final void setDateCertified(Date argDateCertified) {
        this.dateCertified = argDateCertified;
    }

    /**
     * Gets the value of dateReceivedByOsp
     *
     * @return the value of dateReceivedByOsp
     */
    public final Date getDateReceivedByOsp() {
        return this.dateReceivedByOsp;
    }

    /**
     * Sets the value of dateReceivedByOsp
     *
     * @param argDateReceivedByOsp Value to assign to this.dateReceivedByOsp
     */
    public final void setDateReceivedByOsp(Date argDateReceivedByOsp) {
        this.dateReceivedByOsp = argDateReceivedByOsp;
    }

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("proposalPersonNumber", getProposalPersonNumber());
        propMap.put("proposalNumber", getProposalNumber());
        propMap.put("certified", isCertified());
        propMap.put("dateCertified", getDateCertified());
        propMap.put("dateReceivedByOsp", getDateReceivedByOsp());
        return propMap;
    }

}


