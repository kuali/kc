/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.award.home;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.bo.Sponsor;

/**
 * AwardTransferringSponsor business object
 * 
 * @author Kuali Coeus Development Team (kc.dev@kuali.org)
 */
public class AwardTransferringSponsor extends AwardAssociate {

    private static final long serialVersionUID = -3642740671361484212L;

    private Integer awardTransferringSponsorId;

    private String sponsorCode;

    private Sponsor sponsor;

    public AwardTransferringSponsor() {
    }

    public AwardTransferringSponsor(Award award, Sponsor sponsor) {
        this.setAward(award);
        this.setSponsor(sponsor);
    }

    public Integer getAwardTransferringSponsorId() {
        return awardTransferringSponsorId;
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardTransferringSponsorId = null;
    }

    public void setAwardTransferringSponsorId(Integer awardTransferringSponsorId) {
        this.awardTransferringSponsorId = awardTransferringSponsorId;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
        if (sponsor == null) {
            sponsorCode = null;
        } else {
            sponsorCode = sponsor.getSponsorCode();
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((getAwardNumber() == null) ? 0 : getAwardNumber().hashCode());
        result = PRIME * result + ((awardTransferringSponsorId == null) ? 0 : awardTransferringSponsorId.hashCode());
        result = PRIME * result + ((getSequenceNumber() == null) ? 0 : getSequenceNumber().hashCode());
        result = PRIME * result + ((sponsorCode == null) ? 0 : sponsorCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final AwardTransferringSponsor OTHER = (AwardTransferringSponsor) obj;
        if (getAwardNumber() == null) {
            if (OTHER.getAwardNumber() != null) return false;
        } else if (!getAwardNumber().equals(OTHER.getAwardNumber())) return false;
        if (awardTransferringSponsorId == null) {
            if (OTHER.awardTransferringSponsorId != null) return false;
        } else if (!awardTransferringSponsorId.equals(OTHER.awardTransferringSponsorId)) return false;
        if (getSequenceNumber() == null) {
            if (OTHER.getSequenceNumber() != null) return false;
        } else if (!getSequenceNumber().equals(OTHER.getSequenceNumber())) return false;
        if (sponsorCode == null) {
            if (OTHER.sponsorCode != null) return false;
        } else if (!sponsorCode.equals(OTHER.sponsorCode)) return false;
        return true;
    }
}
