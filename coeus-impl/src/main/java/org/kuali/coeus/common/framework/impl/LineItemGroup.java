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

package org.kuali.coeus.common.framework.impl;

/**
 * A group which contains lineItemObjects, representing a grouping (or heading) within the table.
 * It does not have its own amount.
 *
 * @author Kuali Coeus
 */
public class LineItemGroup extends LineItem {
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
