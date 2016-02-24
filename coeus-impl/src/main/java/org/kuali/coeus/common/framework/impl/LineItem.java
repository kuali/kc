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

package org.kuali.coeus.common.framework.impl;

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
