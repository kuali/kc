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

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.event.AwardApprovedSubawardRuleEvent;
import org.kuali.kra.award.rules.AwardApprovedSubawardRuleImpl;
import org.kuali.kra.award.rules.AwardDocumentRule;
import org.kuali.kra.award.web.struts.form.ApprovedSubawardFormHelper;

/**
 * This class...
 */
public class ApprovedSubawardActionHelper implements Serializable {
    
    private static final long serialVersionUID = -6683397794718075987L;

    /**
     * This method is called when adding a new AwardApprovedSubaward
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addApprovedSubaward(ApprovedSubawardFormHelper formHelper) throws Exception {
        
        AwardApprovedSubawardRuleEvent event = new AwardApprovedSubawardRuleEvent(
                                                            "newAwardCostShare",
                                                            formHelper.getAwardDocument(),
                                                            formHelper.getNewAwardApprovedSubaward(),
                                                            formHelper.getAwardDocument().
                                                                getAward().getAwardApprovedSubawards());
        boolean success = new AwardApprovedSubawardRuleImpl().processAddApprovedSubawardBusinessRules(event);
            if(success){
                formHelper.getAwardDocument().getAward().add(formHelper.getNewAwardApprovedSubaward());
                formHelper.init();
            }
            return success;
    }

    /**
    /**
     * This method is called when recalculating the total subawards amount
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean recalculateSubawardTotal(ApprovedSubawardFormHelper formHelper) throws Exception {
        AwardDocumentRule rule = new AwardDocumentRule();
        AwardDocument document = formHelper.getAwardDocument();
        boolean success = rule.processApprovedSubawardBusinessRules(document);
        return success;
    }
}
