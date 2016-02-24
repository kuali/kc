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
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import org.kuali.coeus.common.framework.contact.Contactable;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collections;
import java.util.Map;

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

    private ScaleTwoDecimal amount;

    private static int instanceCount;

    // used in tag 
    private int instanceNumber;

    // used in tag 
    private transient KcPersonService kcPersonService;


    public AwardApprovedForeignTravel() {
        instanceNumber = instanceCount++;
    }


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
        this.amount = new ScaleTwoDecimal(amount);
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


    public Integer getRolodexId() {
        return rolodexId;
    }


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
    public ScaleTwoDecimal getAmount() {
        return amount;
    }


    public boolean isEmployee() {
        return personId != null;
    }


    public boolean isNonemployee() {
        return rolodexId != null;
    }

    @Override
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
    public void setAmount(final ScaleTwoDecimal amount) {
        this.amount = amount;
    }

    /**
     * Convenience method
     * @param amount
     */
    public void setAmount(final double amount) {
        this.amount = new ScaleTwoDecimal(amount);
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
            Map map = Collections.singletonMap("rolodexId", rolodexId);
            contact = (Contactable) getBusinessObjectService().findMatching(NonOrganizationalRolodex.class, map).iterator().next();
        } else {
            contact = null;
        }
        return contact;
    }

    BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
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
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }
}
