/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.io.Serializable;
import java.sql.Date;
import java.util.Map;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.kra.bo.Contactable;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class handles the Award Special Approval for Approved Equipment
 */
public class AwardApprovedForeignTravel extends AwardAssociate implements Comparable<AwardApprovedForeignTravel>, ValuableItem {

    private static final long serialVersionUID = 1039155193608738040L;

    private Long approvedForeignTravelId;

    private KcPerson personTraveler;

    private String personId;

    private Integer rolodexId;

    private NonOrganizationalRolodex rolodexTraveler;

    //private Serializable genericId; 
    /**
     * Gets the genericId attribute. 
     * @return Returns the genericId.
     */
    //    public Serializable getGenericId() { 
    //        return genericId; 
    //    } 
    /**
     * Sets the genericId attribute value.
     * @param genericId The genericId to set.
     */
    //    public void setGenericId(Serializable genericId) { 
    //        this.genericId = genericId;         
    //    } 
    private String travelerName;

    private String destination;

    private Date startDate;

    private Date endDate;

    private KualiDecimal amount;

    private static int instanceCount;

    // used in tag 
    private int instanceNumber;

    // used in tag 
    private transient KcPersonService kcPersonService;

    /**
     * Constructs a AwardApprovedForeignTravel
     */
    public AwardApprovedForeignTravel() {
        instanceNumber = instanceCount++;
    }

    /**
     * Constructs a AwardApprovedForeignTravel
     */
    public AwardApprovedForeignTravel(Object traveler, String destination, Date startDate, Date endDate, double amount) {
        super();
        if (traveler instanceof KcPerson) {
            setPersonTraveler((KcPerson) traveler);
        } else if (traveler instanceof NonOrganizationalRolodex) {
            setRolodexTraveler((NonOrganizationalRolodex) traveler);
        }
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = new KualiDecimal(amount);
    }

    /**
     * Constructs a AwardApprovedForeignTravel.java.
     * @param tripToCopy
     */
    AwardApprovedForeignTravel(AwardApprovedForeignTravel tripToCopy) {
        setPersonTraveler(tripToCopy.personTraveler);
        setApprovedForeignTravelId(null);
        setDestination(tripToCopy.destination);
        setStartDate(tripToCopy.startDate);
        setEndDate(tripToCopy.endDate);
        setAmount(tripToCopy.amount);
        setAward(tripToCopy.getAward());
    }

    /**
     * Gets the approvedForeignTravelId attribute. 
     * @return Returns the approvedForeignTravelId.
     */
    public Long getApprovedForeignTravelId() {
        return approvedForeignTravelId;
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
    public KcPerson getPersonTraveler() {
        return personTraveler;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public int getInstanceNumber() {
        return instanceNumber;
    }

    public Contactable getTraveler() {
        Contactable contact = personTraveler != null ? personTraveler : (rolodexTraveler != null ? rolodexTraveler : null);
        if (contact == null) {
            contact = loadTraveler();
        }
        return contact;
    }

    /**
     * @return Returns the contactId.
     */
    public Serializable getContactId() {
        return personId != null ? personId : (rolodexId != null ? rolodexId : null);
    }

    /**
     * This method returns the destination
     * @return The destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Gets the end date attribute.
     * @return Returns the end date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return
     */
    public Integer getRolodexId() {
        return rolodexId;
    }

    /**
     * @return
     */
    public NonOrganizationalRolodex getRolodexTraveler() {
        return rolodexTraveler;
    }

    /**
     * Gets the start date attribute.
     * @return Returns the start date.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gets the amount attribute. 
     * @return Returns the amount.
     */
    public KualiDecimal getAmount() {
        return amount;
    }

    /**
     * @return
     */
    public boolean isEmployee() {
        return personId != null;
    }

    /**
     * @return
     */
    public boolean isNonemployee() {
        return rolodexId != null;
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.approvedForeignTravelId = null;
    }

    /**
     * Sets the approvedForeignTravelId attribute value.
     * @param approvedForeignTravelId The approvedForeignTravelId to set.
     */
    public void setApprovedForeignTravelId(final Long approvedForeignTravelId) {
        this.approvedForeignTravelId = approvedForeignTravelId;
    }

    /**
     * Sets the traveler attribute value.
     * @param personTraveler The KcPerson to set.
     */
    public void setPersonTraveler(final KcPerson personTraveler) {
        this.personTraveler = personTraveler;
        if (personTraveler != null) {
            this.travelerName = personTraveler.getFullName();
            this.personId = personTraveler.getPersonId();
            setRolodexTraveler(null);
        } else {
            this.personId = null;
            this.travelerName = rolodexTraveler != null ? rolodexTraveler.getFullName() : null;
        }
    }

    /**
     * @param rolodexId
     */
    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    /**
     * @param rolodexTraveler
     */
    public void setRolodexTraveler(NonOrganizationalRolodex rolodexTraveler) {
        this.rolodexTraveler = rolodexTraveler;
        if (rolodexTraveler != null) {
            this.travelerName = rolodexTraveler.getFullName();
            this.rolodexId = rolodexTraveler.getRolodexId();
            setPersonTraveler(null);
        } else {
            this.rolodexId = null;
            this.travelerName = personTraveler != null ? personTraveler.getFullName() : null;
        }
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
    public void setDestination(final String destination) {
        this.destination = destination;
    }

    /**
     * Sets the end date attribute value.
     * @param endDate The end date to set.
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the start date attribute value.
     * @param startDate The start date to set.
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
        if (personTraveler == null) {
            result = PRIME * result + ((travelerName == null) ? 0 : travelerName.hashCode());
        } else {
            result = PRIME * result + personTraveler.hashCode();
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
        if (personTraveler == null) {
            if (other.personTraveler != null) {
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
        } else {
            return other.personTraveler != null && personTraveler.getFullName().equalsIgnoreCase(other.personTraveler.getFullName());
        }
        return true;
    }

    public String toString() {
        return String.format("********** %s=%s;%s=%s [%d]", "travelerId", personId, "travelerName", travelerName, instanceNumber);
    }

    public int compareTo(AwardApprovedForeignTravel other) {
        int result = startDate != null ? startDate.compareTo(other.startDate) : 0;
        if (result == 0) {
            Contactable thisTraveler = getTraveler();
            Contactable otherTraveler = other.getTraveler();
            if (thisTraveler != null && otherTraveler != null) {
                result = thisTraveler.getLastName().compareToIgnoreCase(otherTraveler.getLastName());
                if (result == 0) {
                    result = thisTraveler.getFirstName().compareToIgnoreCase(otherTraveler.getFirstName());
                }
            }
        }
        return result;
    }

    Contactable loadTraveler() {
        final Contactable contact;
        if (personId != null) {
            contact = getKcPersonService().getKcPersonByPersonId(this.personId);
        } else if (rolodexId != null) {
            Map map = ServiceHelper.getInstance().buildCriteriaMap("rolodexId", rolodexId);
            contact = (Contactable) getBusinessObjectService().findMatching(NonOrganizationalRolodex.class, map).iterator().next();
        } else {
            contact = null;
        }
        return contact;
    }

    BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    //    private int compareTravelerNames(String thisTravelerName, String otherTravelerName) { 
    //        return thisTravelerName.compareToIgnoreCase(otherTravelerName); 
    //    } 
    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }
}
