/*
 * Copyright 2006-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.web.krad.dto;

import java.math.BigDecimal;

/**
 * A line item object to be used by LineItemTable which represents an amount.  The line item objects are matched
 * by the table for row by lineItemId, so the lineItemId MUST match across Periods to create a row.
 *
 * @author Kuali Coeus
 */
public class LineItemObject extends LineItem {
    private String lineItemId;
    private String name;

    private BigDecimal amount;

    public LineItemObject(String lineItemId, String name, BigDecimal amount) {
        this.lineItemId = lineItemId;
        this.name = name;
        this.amount = amount;
    }

    /**
     * The id of the lineItem, should be unique per a period but match for line items across multiple periods
     *
     * @return the line item id
     */
    public String getLineItemId() {
        return lineItemId;
    }

    /**
     * @see #getLineItemId()
     */
    public void setLineItemId(String lineItemId) {
        this.lineItemId = lineItemId;
    }

    /**
     * The name of the line item that will be displayed
     *
     * @return the name that will be displayed for this line item
     */
    public String getName() {
        return name;
    }

    /**
     * @see #getName()
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The amount in BigDecimal format
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @see #getAmount()
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
