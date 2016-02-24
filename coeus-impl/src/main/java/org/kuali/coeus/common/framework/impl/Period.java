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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A period which can contain LineItemGroups, represents a column of data in the LineItemTable component.
 *
 * @author Kuali Coeus
 */
public class Period implements Serializable{

    private static final long serialVersionUID = -8093204750065415832L;

    private String name;
    private List<LineItemGroup> lineItemGroups;
    private Date startDate;
    private Date endDate;

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

    /**
     * The start date of the period.
     *
     * @return start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @see #getStartDate()
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * The end date of the period.
     *
     * @return start date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @see #getEndDate()
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
