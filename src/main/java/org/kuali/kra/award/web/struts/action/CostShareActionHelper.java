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

import java.io.Serializable;

import org.kuali.kra.award.commitments.AwardCostShareRuleEvent;
import org.kuali.kra.award.commitments.AwardCostShareRuleImpl;
import org.kuali.kra.award.commitments.CostShareFormHelper;

/**
 * This class...
 */
public class CostShareActionHelper implements Serializable {
    
    private static final long serialVersionUID = 8927780321198666093L;

    /**
     * This method is called when adding a new AwardCostShare
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addCostShare(CostShareFormHelper formHelper) throws Exception {
        
        AwardCostShareRuleEvent event = new AwardCostShareRuleEvent(
                                                            "newAwardCostShare",
                                                            formHelper.getAwardDocument(),
                                                            formHelper.getNewAwardCostShare());
        boolean success = new AwardCostShareRuleImpl().processAddCostShareBusinessRules(event);
            if(success){
                formHelper.getAwardDocument().getAward().add(formHelper.getNewAwardCostShare());
                formHelper.init();
            }
            return success;
    }
}
