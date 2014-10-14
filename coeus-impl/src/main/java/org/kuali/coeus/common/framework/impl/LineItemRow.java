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

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

/**
 * LineItemRow used by the LineItemTable to create the UI, its values are set by LineItemTable component code.
 *
 * @author Kuali Coeus
 */
public class LineItemRow {
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
