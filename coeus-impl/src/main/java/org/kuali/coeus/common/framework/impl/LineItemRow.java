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
import java.util.List;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

/**
 * LineItemRow used by the LineItemTable to create the UI, its values are set by LineItemTable component code.
 *
 * @author Kuali Coeus
 */
public class LineItemRow implements Serializable{

    private static final long serialVersionUID = -811018869493183494L;

    private String id;
    private String lineItemId;
    private String parentRow;
    private List<LineItemRow> childRows = new ArrayList<LineItemRow>();

    private String cssClass;
    private List<String> cellContent = new ArrayList<String>();
    private List<ScaleTwoDecimal> values = new ArrayList<ScaleTwoDecimal>();
    private boolean groupRow;

    public LineItemRow() {
    }

    public LineItemRow(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(String lineItemId) {
        this.lineItemId = lineItemId;
    }

    public String getParentRow() {
        return parentRow;
    }

    public void setParentRow(String parentRow) {
        this.parentRow = parentRow;
    }

    public List<LineItemRow> getChildRows() {
        return childRows;
    }

    public void setChildRows(List<LineItemRow> childRows) {
        this.childRows = childRows;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public List<String> getCellContent() {
        return cellContent;
    }

    public void setCellContent(List<String> cellContent) {
        this.cellContent = cellContent;
    }

    public List<ScaleTwoDecimal> getValues() {
        return values;
    }

    public void setValues(List<ScaleTwoDecimal> values) {
        this.values = values;
    }

    public boolean isGroupRow() {
        return groupRow;
    }

    public void setGroupRow(boolean groupRow) {
        this.groupRow = groupRow;
    }
}
