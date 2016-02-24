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
package org.kuali.kra.award.home;

import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.award.AwardAssociate;

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

    @Override
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
