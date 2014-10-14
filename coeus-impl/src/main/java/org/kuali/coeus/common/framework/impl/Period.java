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

import java.util.ArrayList;
import java.util.List;

/**
 * A period which can contain LineItemGroups, represents a column of data in the LineItemTable component.
 *
 * @author Kuali Coeus
 */
public class Period {
    private String name;
    private List<LineItemGroup> lineItemGroups;

    public Period(String name) {
        this.name = name;
        lineItemGroups = new ArrayList<LineItemGroup>();
    }

    /**
     * The name of the period, this is what will be displayed in the ui.
     *
     * @return the name
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
     * The LineItemGroups of this period (ex: personnel, non-personnel, totals)
     *
     * @return the LineItemGroups
     */
    public List<LineItemGroup> getLineItemGroups() {
        return lineItemGroups;
    }

    /**
     * @see #getLineItemGroups()
     */
    public void setLineItemGroups(List<LineItemGroup> lineItemGroups) {
        this.lineItemGroups = lineItemGroups;
    }
}
