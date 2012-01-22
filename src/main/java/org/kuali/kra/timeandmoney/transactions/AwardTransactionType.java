/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.timeandmoney.transactions;

import org.kuali.kra.award.AwardAssociate;

public class AwardTransactionType extends AwardAssociate {

    private static final long serialVersionUID = 1L;

    private Integer awardTransactionTypeCode;

    private String description;

    private boolean showInActionSummary;

    public AwardTransactionType() {
    }

    public Integer getAwardTransactionTypeCode() {
        return awardTransactionTypeCode;
    }

    public void setAwardTransactionTypeCode(Integer awardTransactionTypeCode) {
        this.awardTransactionTypeCode = awardTransactionTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getShowInActionSummary() {
        return showInActionSummary;
    }

    public void setShowInActionSummary(boolean showInActionSummary) {
        this.showInActionSummary = showInActionSummary;
    }

    public void resetPersistenceState() {
    }
}
