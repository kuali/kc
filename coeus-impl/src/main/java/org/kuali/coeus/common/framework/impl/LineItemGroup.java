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

import java.io.Serializable;

/**
 * A group which contains lineItemObjects, representing a grouping (or heading) within the table.
 * It does not have its own amount.
 *
 * @author Kuali Coeus
 */
public class LineItemGroup extends LineItem implements Serializable {

    private static final long serialVersionUID = -2168538625791958767L;

    private String groupName;
    private boolean calculateGroupSubTotal;

    public LineItemGroup(String groupName, boolean calculateGroupSubTotal) {
        this.groupName = groupName;
        this.calculateGroupSubTotal = calculateGroupSubTotal;
    }

    /**
     * The name of the group which will as shown in the table
     *
     * @return
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @see LineItemGroup#getGroupName()
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * True if a subtotal will be automatically calculated for this group, false otherwise
     *
     * @return true if a subtotal will be calcuated, false otherwise
     */
    public boolean isCalculateGroupSubTotal() {
        return calculateGroupSubTotal;
    }

    /**
     * @see LineItemGroup#isCalculateGroupSubTotal()
     */
    public void setCalculateGroupSubTotal(boolean calculateGroupSubTotal) {
        this.calculateGroupSubTotal = calculateGroupSubTotal;
    }
}
