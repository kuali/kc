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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.contact.Contactable;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.SpecialApprovalBean;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.List;

/**
 * This class supports the AwardForm class
 */
public class ApprovedForeignTravelBean extends SpecialApprovalBean {
    private static final long serialVersionUID = 8787570417382374201L;
    
    private AwardApprovedForeignTravel newApprovedForeignTravel;
    private transient KcPersonService kcPersonService;

    /**
     * Constructs a ApprovedEquipmentBean
     * @param parent
     */
    public ApprovedForeignTravelBean(AwardForm form) {
        super(form);
        init();
    }

    /**
     * This method is called when adding a new apprved equipment item
     * @param formHelper
     * @return
     */
    public boolean addApprovedForeignTravel() {
        AddAwardApprovedForeignTravelRuleEvent event = generateAddEvent();
        boolean success = new AwardApprovedForeignTravelRuleImpl().processAddAwardApprovedForeignTravelBusinessRules(event);
        if(success){
            getAward().add(getNewApprovedForeignTravel());
            init();
        }
        return success;
    }

    /**
     * This method deletes an approved foreign travel trip
     * @param deletedTripIndex
     */
    public void deleteApprovedForeignTravelTrip(int deletedTripIndex) {
        removeCollectionItem(getAward().getApprovedForeignTravelTrips(), deletedTripIndex);
    }


    public Object getData() {
        return getNewApprovedForeignTravel();
    }
    
    /**
     * Gets the newAwardApprovedEquipment attribute. 
     * @return Returns the newAwardApprovedEquipment.
     */
    public AwardApprovedForeignTravel getNewApprovedForeignTravel() {
        return newApprovedForeignTravel;
    }

    public String getSelectedTravelerId() {
        return newApprovedForeignTravel.getContactId().toString();
    }

    /**
     * Initialize subform
     */
    public void init() {
        newApprovedForeignTravel = new AwardApprovedForeignTravel();
    }

    public void refreshTravelers() {
        refreshTraveler(newApprovedForeignTravel);
        for(AwardApprovedForeignTravel trip: getAward().getApprovedForeignTravelTrips()) {
            refreshTraveler(trip);
        }
    }

    private void refreshTraveler(AwardApprovedForeignTravel trip) {
        String travelerId = trip.getPersonId();
        if(travelerId != null) {
            trip.setPersonTraveler(this.getKcPersonService().getKcPersonByPersonId(travelerId));
        }
    }
    
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
    
    /**
     * Sets the newAwardApprovedForeignTravel attribute value.
     * @param newAwardApprovedForeignTravel The newAwardApprovedForeignTravel to set.
     */
    public void setNewAwardApprovedForeignTravel(AwardApprovedForeignTravel newAwardApprovedForeignTravel) {
        this.newApprovedForeignTravel = newAwardApprovedForeignTravel;
    }
    
    AddAwardApprovedForeignTravelRuleEvent generateAddEvent() {        
        AddAwardApprovedForeignTravelRuleEvent event = new AddAwardApprovedForeignTravelRuleEvent(
                                                            "newAwardApprovedForeignTravel",
                                                            getAwardDocument(),
                                                            getAward(),
                                                            getNewApprovedForeignTravel());
        return event;
    }


    public List<KeyValue> getKnownTravelers() {
        return new ApprovedForeignTravelerValuesFinder().getKeyValues();
    }

    /**
     * @param travelerId
     */
    public void setSelectedTravelerId(String travelerId) {
        if(StringUtils.isEmpty(travelerId)) {
            return;
        }
        
        boolean foundKeyPerson = false;
        for(AwardPerson pp: getAward().getProjectPersons()) {
            if(pp.getContact().getIdentifier().toString().equals(travelerId)) {
                if(pp.isEmployee()) {
                    newApprovedForeignTravel.setPersonTraveler(pp.getPerson());
                    newApprovedForeignTravel.setPersonId(pp.getPersonId());
                } else {
                    newApprovedForeignTravel.setRolodexTraveler(pp.getRolodex());
                    newApprovedForeignTravel.setRolodexId(pp.getRolodexId());
                }
                foundKeyPerson = true;
                break;
            }
        }
        if(!foundKeyPerson) {
            for(AwardApprovedForeignTravel aft: getAward().getApprovedForeignTravelTrips()) {
                Contactable traveler = aft.getTraveler();
                if(traveler.getIdentifier().equals(travelerId)) {
                    if(traveler instanceof KcPerson) {
                        KcPerson person = (KcPerson) traveler;
                        newApprovedForeignTravel.setPersonTraveler(person);
                        newApprovedForeignTravel.setPersonId(person.getPersonId());
                    } else {
                        NonOrganizationalRolodex rolodex = (NonOrganizationalRolodex) traveler;
                        newApprovedForeignTravel.setRolodexTraveler(rolodex);
                        newApprovedForeignTravel.setRolodexId(rolodex.getRolodexId());
                    }
                    break;
                }
            }
        }
    }
}
