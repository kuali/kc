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
package org.kuali.kra.award.web.struts.action;

import org.kuali.kra.award.rule.event.AwardApprovedEquipmentRuleEvent;
import org.kuali.kra.award.rules.AwardApprovedEquipmentRuleImpl;
import org.kuali.kra.award.rules.EquipmentCapitalizationMinimumLoader;
import org.kuali.kra.award.web.struts.form.ApprovedEquipmentFormHelper;

/**
 * This class supports the parent action for Approved Equipment
 */
public class ApprovedEquipmentActionHelper {
    private AwardPaymentReportsAndTermsAction parent;
    
    ApprovedEquipmentActionHelper(AwardPaymentReportsAndTermsAction parent) {
        this.parent = parent;
    }
    
    /**
     * This method is called when adding a new apprved equipment item
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addApprovedEquipmentItem(ApprovedEquipmentFormHelper formHelper) throws Exception {
        EquipmentCapitalizationMinimumLoader helper = new EquipmentCapitalizationMinimumLoader();
        AwardApprovedEquipmentRuleEvent event = new AwardApprovedEquipmentRuleEvent(
                                                            "newAwardApprovedEquipment",
                                                            formHelper.getAwardDocument(),
                                                            formHelper.getNewAwardApprovedEquipment(),
                                                            helper.getMinimumCapitalization() );
        boolean success = new AwardApprovedEquipmentRuleImpl().processNewAwardApprovedEquipmentBusinessRules(event);
        if(success){
            formHelper.getAwardDocument().getAward().add(formHelper.getNewAwardApprovedEquipment());
            formHelper.init();
        }
        return success;
    }

    /**
     * This method is called when adding a new apprved equipment item
     * @param formHelper
     * @return
     * @throws Exception
     */
//    public boolean deleteApprovedEquipmentItem(ApprovedEquipmentFormHelper formHelper) throws Exception {
//        DeleteAwardApprovedEquipmentEvent event = new DeleteAwardApprovedEquipmentEvent("newAwardApprovedEquipment",
//                                                                                            formHelper.getAwardDocument(),
//                                                                                            formHelper.getNewAwardApprovedEquipment());
//        boolean success = parent.getKualiRuleService().applyRules(event);
//        if(success){
//            formHelper.getAwardDocument().getAward().add(formHelper.getNewAwardApprovedEquipment());
//            formHelper.init();
//        }
//        return success;
//    }
    
    
}
