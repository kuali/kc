/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Sponsor;

/**
 * AwardTransferringSponsor business object
 * 
 * @author Kuali Coeus Development Team (kc.dev@kuali.org)
 */
public class AwardTransferringSponsor extends KraPersistableBusinessObjectBase { 
	
    private static final long serialVersionUID = -3642740671361484212L;
    
    private Integer awardTransferringSponsorId; 
	private String awardNumber; 
	private Integer sequenceNumber; 
	private String sponsorCode; 
	
	private Sponsor sponsor; 
	private Award award; 
	
	public AwardTransferringSponsor() { 

	} 
    
    public AwardTransferringSponsor(Award award, Sponsor sponsor) {
        this.setAward(award);
        this.setSponsor(sponsor);
    }
	
	public Integer getAwardTransferringSponsorId() {
		return awardTransferringSponsorId;
	}

	public void setAwardTransferringSponsorId(Integer awardTransferringSponsorId) {
		this.awardTransferringSponsorId = awardTransferringSponsorId;
	}

	public String getAwardNumber() {
		return awardNumber;
	}

	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
        this.award = award;
        if(award == null) {
            sequenceNumber = null;
            awardNumber = null;
        } else {
            sequenceNumber = award.getSequenceNumber();
            awardNumber = award.getAwardNumber();
        }
	}

	@Override 
	protected LinkedHashMap<String,Object> toStringMapper() {
		LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
		hashMap.put("awardTransferringSponsorId", getAwardTransferringSponsorId());
		hashMap.put("awardNumber", getAwardNumber());
		hashMap.put("sequenceNumber", getSequenceNumber());
		hashMap.put("sponsorCode", getSponsorCode());
		return hashMap;
	}

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((awardNumber == null) ? 0 : awardNumber.hashCode());
        result = PRIME * result + ((awardTransferringSponsorId == null) ? 0 : awardTransferringSponsorId.hashCode());
        result = PRIME * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        result = PRIME * result + ((sponsorCode == null) ? 0 : sponsorCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AwardTransferringSponsor other = (AwardTransferringSponsor) obj;
        if (awardNumber == null) {
            if (other.awardNumber != null)
                return false;
        }
        else if (!awardNumber.equals(other.awardNumber))
            return false;
        if (awardTransferringSponsorId == null) {
            if (other.awardTransferringSponsorId != null)
                return false;
        }
        else if (!awardTransferringSponsorId.equals(other.awardTransferringSponsorId))
            return false;
        if (sequenceNumber == null) {
            if (other.sequenceNumber != null)
                return false;
        }
        else if (!sequenceNumber.equals(other.sequenceNumber))
            return false;
        if (sponsorCode == null) {
            if (other.sponsorCode != null)
                return false;
        }
        else if (!sponsorCode.equals(other.sponsorCode))
            return false;
        return true;
    }
    
}
