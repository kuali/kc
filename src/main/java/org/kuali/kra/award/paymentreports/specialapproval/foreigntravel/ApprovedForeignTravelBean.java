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

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.SpecialApprovalBean;
import org.kuali.kra.bo.Person;

/**
 * This class supports the AwardForm class
 */
public class ApprovedForeignTravelBean extends SpecialApprovalBean {
    private static final long serialVersionUID = 8787570417382374201L;
    
    private AwardApprovedForeignTravel newApprovedForeignTravel;
    
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

    /**
     * @return
     */
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
        String travelerId = trip.getTravelerId();
        if(travelerId != null) {
            Map<String, Object> pkMap = new HashMap<String, Object>();
            pkMap.put("PERSON_ID", travelerId);
            trip.setTraveler((Person) getBusinessObjectService().findByPrimaryKey(Person.class, pkMap));
        }
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
}
