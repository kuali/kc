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

import org.kuali.kra.award.commitments.AwardCostShareRuleEvent;
import org.kuali.kra.award.commitments.AwardCostShareRuleImpl;
import org.kuali.kra.award.commitments.CostShareFormHelper;

import java.io.Serializable;


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
