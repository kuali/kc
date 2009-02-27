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

import java.util.List;

import org.kuali.core.service.KualiRuleService;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * This class supports the AwardForm class
 */
public class ApprovedForeignTravelBean {
    private AwardApprovedForeignTravel newApprovedForeignTravel;
    private KualiRuleService ruleService;
    private AwardForm form;
    
    /**
     * Constructs a ApprovedEquipmentBean
     * @param parent
     */
    public ApprovedForeignTravelBean(AwardForm form) {
        this.form = form;
        init();
    }
    
    /**
     * This method is called when adding a new apprved equipment item
     * @param formHelper
     * @return
     */
    public boolean addApprovedForeignTravel() {
        AddAwardApprovedForeignTravelRuleEvent event = generateAddEvent();
        boolean success = getRuleService().applyRules(event);
        if(success){
            getAward().add(getNewApprovedForeignTravel());
            init();
        }
        return success;
    }

    /**
     * This method delets a selected equipment item
     * @param formHelper
     * @param deletedItemIndex
     */
    public void deleteApprovedForeignTravelTrip(int deletedItemIndex) {
        List<AwardApprovedForeignTravel> items = getAward().getApprovedForeignTravelTrips();
        if(deletedItemIndex >= 0 && deletedItemIndex < items.size()) {
            items.remove(deletedItemIndex);
        }        
    }

    /**
     * @return
     */
    public Award getAward() {
        return form.getAwardDocument().getAward();
    }

    /**
     * @return
     */
    public AwardDocument getAwardDocument() {
        return form.getAwardDocument();
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

    /**
     * Sets the newAwardApprovedForeignTravel attribute value.
     * @param newAwardApprovedForeignTravel The newAwardApprovedForeignTravel to set.
     */
    public void setNewAwardApprovedEquipment(AwardApprovedForeignTravel newAwardApprovedForeignTravel) {
        this.newApprovedForeignTravel = newAwardApprovedForeignTravel;
    }
    
    protected KualiRuleService getRuleService() {
        if(ruleService == null) {
            ruleService = (KualiRuleService) KraServiceLocator.getService("kualiRuleService"); 
        }
        return ruleService;
    }
    
    protected void setRuleService(KualiRuleService ruleService) {
        this.ruleService = ruleService;
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
