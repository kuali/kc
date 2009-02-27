/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.ValuableItem;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;

/**
 * This class handles the Award Special Approval for Approved Equipment
 */
public class AwardApprovedForeignTravel extends KraPersistableBusinessObjectBase implements ValuableItem {
    private static final long serialVersionUID = 1039155193608738040L;
    
    private Long approvedForeignTravelId;
    private Person traveler;
    private String travelerName;
    private String destination;
    private Date startDate;
    private Date endDate;
    private KualiDecimal amount;
    
    private String awardNumber;
    private Integer sequenceNumber;
    private Award award;
    
    /**
     * Constructs a AwardApprovedForeignTravel
     */
    public AwardApprovedForeignTravel() {
    }
    
    /**
     * Constructs a AwardApprovedForeignTravel
     */
    public AwardApprovedForeignTravel(Person traveler, String destination, Date startDate, Date endDate, double amount) {
        this();
        this.traveler = traveler;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = new KualiDecimal(amount);
    }

    /**
     * Gets the approvedForeignTravelId attribute. 
     * @return Returns the approvedForeignTravelId.
     */
    public Long getApprovedForeignTravelId() {
        return approvedForeignTravelId;
    }

    /**
     * Gets the award attribute. 
     * @return Returns the award.
     */
    public Award getAward() {
        return award;
    }

    /**
     * Gets the traveler name attribute. 
     * @return Returns the traveler name.
     */
    public String getTravelerName() {
        return travelerName;
    }

    /**
     * This method returns the traveler
     * @return
     */
    public Person getTraveler() {
        return traveler;
    }
    
    /**
     * This method returns the destination
     * @return The destination
     */
    public String getDestination() {
        return destination;
    }
    
    /**
     * Gets the start date attribute. 
     * @return Returns the start date.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date attribute. 
     * @return Returns the end date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Gets the amount attribute. 
     * @return Returns the amount.
     */
    public KualiDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the approvedForeignTravelId attribute value.
     * @param approvedEquipmentId The approvedForeignTravelId to set.
     */
    public void setApprovedForeignTravelId(final Long approvedForeignTravelId) {
        this.approvedForeignTravelId = approvedForeignTravelId;
    }

    /**
     * Sets the award attribute value.
     * @param award The award to set.
     */
    public void setAward(Award award) {
        this.award = award;
        if(award != null) {
            setSequenceNumber(award.getSequenceNumber());
            setAwardNumber(award.getAwardNumber());
        } else {
            setSequenceNumber(0);
            setAwardNumber("");
        }
    }

    /**
     * Sets the traveler attribute value.
     * @param traveler The Person to set.
     */
    public void setTraveler(final Person traveler) {
        this.traveler = traveler;
    }
    
    /**
     * This method sets the traveler name
     * @param travelerName
     */
    public void setTravelerName(final String travelerName) {
        this.travelerName = travelerName;
    }

    /**
     * Sets the destination attribute value.
     * @param destination  The destination  to set.
     */
    public void setDestination (final String destination ) {
        this.destination = destination;
    }

    /**
     * Sets the start date attribute value.
     * @param startDate The start date to set.
     */
    public void setModel(final Date startDate ) {
        this.startDate = startDate;
    }
    
    /**
     * Sets the end date attribute value.
     * @param endDate The end date to set.
     */
    public void setEndDate(final Date endDate ) {
        this.endDate = endDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * Sets the amount attribute value.
     * @param amount The amount to set.
     */
    public void setAmount(final KualiDecimal amount) {
        this.amount = amount;
    }
    
    /**
     * Convenience method
     * @param amount
     */
    public void setAmount(final double amount) {
        this.amount = new KualiDecimal(amount);
    }

    /**
     * @return
     * @see org.kuali.kra.award.bo.Award#getAwardNumber()
     */
    public String getAwardNumber() {
        return awardNumber;
    }
    
    /**
     * @return
     * @see org.kuali.kra.award.bo.Award#getSequenceNumber()
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @param awardNumber
     * @see org.kuali.kra.award.bo.Award#setAwardNumber(java.lang.String)
     */
    public void setAwardNumber(final String awardNumber) {
        this.awardNumber = awardNumber;
    }
    
    /**
     * @param sequenceNumber
     * @see org.kuali.kra.award.bo.Award#setSequenceNumber(java.lang.Integer)
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    
    /**
     * Note: This was modified from the Eclipse generated version so that Traveler Name is only 
     * used if the Traveler reference is null
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((destination == null) ? 0 : destination.hashCode());
        result = PRIME * result + ((startDate == null) ? 0 : startDate.hashCode());
        if(traveler == null) {
            result = PRIME * result + ((travelerName == null) ? 0 : travelerName.hashCode());
        } else {
            result = PRIME * result + traveler.hashCode();
        }
        
        return result;
    }

    /**
     * Note: This was modified from the Eclipse generated version so that Traveler Name is only 
     * checked if both object's have null Traveler references
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AwardApprovedForeignTravel)) {
            return false;
        }
        AwardApprovedForeignTravel other = (AwardApprovedForeignTravel) obj;
        if (destination == null) {
            if (other.destination != null) {
                return false;
            }
        } else if (!destination.equals(other.destination)) {
            return false;
        }
        if (startDate == null) {
            if (other.startDate != null) {
                return false;
            }
        } else if (!startDate.equals(other.startDate)) {
            return false;
        }
        if (traveler == null) {
            if (other.traveler != null) {
                return false;
            } else {
                if (travelerName == null) {
                    if (other.travelerName != null) {
                        return false;
                    }
                } else if (!travelerName.equals(other.travelerName)) {
                    return false;
                }
            }
        } else if (!traveler.equals(other.traveler)) {
            return false;
        }
        
        return true;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("approvedEquipmentId", approvedForeignTravelId);
        map.put("awardNumber", awardNumber);
        map.put("sequenceNumber", sequenceNumber);
        map.put("traveler", traveler);
        map.put("destination", destination);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("amount", amount);
        return map;
    }       
    
}