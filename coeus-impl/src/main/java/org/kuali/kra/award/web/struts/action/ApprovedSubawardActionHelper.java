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
package org.kuali.kra.award.web.struts.action;

import org.kuali.kra.award.AwardDocumentRule;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.approvedsubawards.ApprovedSubawardFormHelper;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRuleEvent;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRuleImpl;

import java.io.Serializable;


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
