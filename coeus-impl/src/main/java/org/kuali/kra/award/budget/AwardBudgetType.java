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
package org.kuali.kra.award.budget;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class AwardBudgetType extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private String awardBudgetTypeCode;

    private String description;

    private AwardBudgetExt awardBudgetExt;

    public AwardBudgetType() {
    }

    public String getAwardBudgetTypeCode() {
        return awardBudgetTypeCode;
    }

    public void setAwardBudgetTypeCode(String awardBudgetTypeCode) {
        this.awardBudgetTypeCode = awardBudgetTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AwardBudgetExt getAwardBudgetExt() {
        return awardBudgetExt;
    }

    public void setAwardBudgetExt(AwardBudgetExt awardBudgetExt) {
        this.awardBudgetExt = awardBudgetExt;
    }
}
