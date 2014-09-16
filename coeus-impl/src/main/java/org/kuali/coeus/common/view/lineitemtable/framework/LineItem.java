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

package org.kuali.coeus.common.view.lineitemtable.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * The LineItem abstract class for LineItemTable.  LineItem objects can contain line items themselves.
 */
public abstract class LineItem {
    private List<LineItemObject> lineItems = new ArrayList<LineItemObject>();

    /**
     * The line item objects which are children of this line item, their total summed amounts should equal
     * this line item's amount, as this is not calculated by the table.
     *
     * @return the LineItemObjects of this LineItem
     */
    public List<LineItemObject> getLineItems() {
        return lineItems;
    }

    /**
     * @see #getLineItems()
     */
    public void setLineItems(List<LineItemObject> lineItems) {
        this.lineItems = lineItems;
    }
}
