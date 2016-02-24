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

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.datadictionary.parse.BeanTag;
import org.kuali.rice.krad.datadictionary.parse.BeanTagAttribute;
import org.kuali.rice.krad.uif.component.BindingInfo;
import org.kuali.rice.krad.uif.component.DataBinding;
import org.kuali.rice.krad.uif.container.GroupBase;
import org.kuali.rice.krad.uif.lifecycle.ViewLifecycle;
import org.kuali.rice.krad.uif.util.LifecycleElement;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.web.bind.UifCurrencyEditor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The LineItemTable consumes a property containing a list of periods and converts that to a table of line items
 * with Periods as columns and grouped into groups of line items, which show amounts, subTotals per period and
 * subTotals per row.
 *
 * @author Kuali Coeus
 */
@BeanTag(name = "lineItemTable", parent = "Uif-LineItemTable")
public class LineItemTable extends GroupBase implements DataBinding {

    private static final long serialVersionUID = 1677312513807050571L;
    private static final Log LOG = LogFactory.getLog(LineItemTable.class);

    private String propertyName;
    private BindingInfo bindingInfo;

    private List<LineItemRow> rows = new ArrayList<LineItemRow>();
    private List<LineItemRow> flattenedRows = new ArrayList<LineItemRow>();
    private LineItemRow headerRow;

    private String dateRangeFormat;

    private boolean renderRowTotalColumn;
    private int numPeriodColumns;

    private boolean renderDisclosureLinks;
    private boolean renderOpened;

    private int lineIndex;
    private UifCurrencyEditor currencyEditor = new UifCurrencyEditor();

    @Override
    public void performInitialization(Object model) {
        super.performInitialization(model);

        View view = ViewLifecycle.getView();
        bindingInfo.setDefaults(view, getPropertyName());
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Creates the LineItemRows based on the Period data stored within the property given by propertyName.
     */
    @Override
    public void performFinalize(Object model, LifecycleElement parent) {
        super.performFinalize(model, parent);

        this.setOnDocumentReadyScript("Kc.LineItemTable.setupLineItemTable(jQuery('#" + this.getId() + "'));");
        lineIndex = 0;

        try {
            List<Period> periods = (List<Period>) (PropertyUtils.getProperty(model, this.getBindingInfo().getBindingPath()));
            if (periods != null) {
	            headerRow = new LineItemRow();
	            headerRow.getCellContent().add("");
	            int columnNum = 0;
	
	            // Don't show the prev/next buttons if everything is being shown already
	            if (this.getHeader() != null && this.getHeader().getRightGroup() != null &&
	                    numPeriodColumns >= periods.size()) {
	                this.getHeader().getRightGroup().setRender(false);
	            }
	            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateRangeFormat);
	            // Iterate over periods and create the header row and the other LineItemRows
	            for (Period period : periods) {
	                String startDate = "?";
	                String endDate = "?";
	                if (period.getStartDate() != null) {
	                    startDate = simpleDateFormat.format(period.getStartDate());
	                }
	
	                if (period.getEndDate() != null) {
	                    endDate = simpleDateFormat.format(period.getEndDate());
	                }
	
	                headerRow.getCellContent().add(period.getName() + "<span class='uif-period-dateRange'>(" +
	                         startDate + " - " + endDate + ")</span>");
	
	                List<LineItemGroup> lineItemGroups = period.getLineItemGroups();
	                columnNum++;
	
	                // Iterate over each group of the period
	                for (LineItemGroup lineItemGroup : lineItemGroups) {
	                    // If the group row already exists because a previous Period created it, use it, keyed by Group Name
	                    LineItemRow lineItemGroupRow = findRow(rows, lineItemGroup.getGroupName());
	
	                    // Create a new row if one does not exist
	                    if (lineItemGroupRow == null) {
	                        lineItemGroupRow = new LineItemRow(this.getId() + "_" + lineItemGroup.getGroupName() + lineIndex);
	                        lineItemGroupRow.setLineItemId(lineItemGroup.getGroupName());
	                        lineItemGroupRow.getCellContent().add(lineItemGroup.getGroupName());
	                        lineItemGroupRow.setCssClass("uif-lineItemGroup");
	                        lineItemGroupRow.setGroupRow(true);
	
	                        rows.add(lineItemGroupRow);
	                        lineIndex++;
	                    }
	
	                    // Process the sub items of the group
	                    processSubLineItems(lineItemGroup, lineItemGroupRow, columnNum, "uif-lineItem", 0);
	
	                    // Calculate the group sub total
	                    processGroupSubTotal(lineItemGroup, lineItemGroupRow, "uif-groupSubTotal");
	                }
	            }
	
	            // Take all the rows (these represent the top level group rows) and iterate over them
	            // to flatten them into an in-order list that can be iterated over in the ftl
	            for (LineItemRow groupRow : rows) {
	                flattenedRows.add(groupRow);
	                flattenSubLineItemRows(groupRow.getChildRows(), true);
	            }
	
	            // If each row is showing a total column, total the values of that row and add a totals value
	            if (renderRowTotalColumn) {
	                // Add a Totals column
	                //TODO internationalize
	                headerRow.getCellContent().add("Totals");
	                for (LineItemRow row : flattenedRows) {
	                    if (row.getValues() == null || row.getValues().isEmpty()) {
	                        continue;
	                    }
	
	                    ScaleTwoDecimal rowTotal = new ScaleTwoDecimal(0);
	                    for (ScaleTwoDecimal value : row.getValues()) {
	                        rowTotal = rowTotal.add(value);
	                    }
	
	                    currencyEditor.setValue(rowTotal);
	                    row.getCellContent().add(currencyEditor.getAsText());
	                }
	            }
            }
        } catch (Exception e) {
            LOG.error("Exception occurred while trying to get value for LineItemTable: " + this, e);
        }
    }

    /**
     * Find the row by identifier in the list of rows given (this represents a subset, id should be unique for this
     * set)
     *
     * @param rows       the rows to iterate
     * @param identifier the identifier
     * @return the row if found, false otherwise
     */
    private LineItemRow findRow(List<LineItemRow> rows, String identifier) {
        for (LineItemRow row : rows) {
            if (row.getLineItemId().equals(identifier)) {
                return row;
            }
        }

        return null;
    }

    /**
     * Process the sub line items of the LineItem passed in.
     *
     * <p>The resulting rows are appended to the the parentRow object passed in which represents the lineItem
     * parameters row in the table.  This method is called recursively with the sub items to add their children
     * to their rows.  LineItems which do not exist for this period have 0s added to their values list.</p>
     *
     * @param lineItem the parent lineItem
     * @param parentRow the parent LineItemRow which represents a row in the table
     * @param columnNum the column number (period number) currently be processed
     * @param cssClass the css class to apply to this row
     * @param nestingLevel the current nesting level, used for css class
     */
    private void processSubLineItems(LineItem lineItem, LineItemRow parentRow, int columnNum, String cssClass,
                                     int nestingLevel) {
        nestingLevel++;

        for (LineItemObject subLineItem : lineItem.getLineItems()) {
            LineItemRow row = findRow(parentRow.getChildRows(), subLineItem.getLineItemId());

            // Create a new row if one does not exist
            if (row == null) {
                row = new LineItemRow(this.getId() + "_" + subLineItem.getLineItemId() + lineIndex);
                row.setParentRow(parentRow.getId());
                row.setLineItemId(subLineItem.getLineItemId());
                row.setCssClass(cssClass + nestingLevel);

                row.getCellContent().add(subLineItem.getName());
                parentRow.getChildRows().add(row);
                lineIndex++;
            }

            // if this line item didn't exist for some periods, use 0 for those previous values
            while (row.getValues().size() + 1 < columnNum) {
                row.getValues().add(new ScaleTwoDecimal(0));
                currencyEditor.setValue(new ScaleTwoDecimal(0));
                row.getCellContent().add(currencyEditor.getAsText());
            }

            // Add this line item's value to the row
            row.getValues().add(subLineItem.getAmount());
            currencyEditor.setValue(subLineItem.getAmount());

            // Set the formatted total
            row.getCellContent().add(currencyEditor.getAsText());


            // Process children recursively
            if (subLineItem.getLineItems() != null && !subLineItem.getLineItems().isEmpty()) {
                processSubLineItems(subLineItem, row, columnNum, cssClass, nestingLevel);
            }
        }

        for (LineItemRow row : parentRow.getChildRows()) {
            // Skip sub total rows
            if (row.getId().endsWith("_subTotal")) {
                continue;
            }

            // Add 0s to any rows which do not match the current period column number
            while (row.getValues().size() < columnNum) {
                row.getValues().add(new ScaleTwoDecimal(0));

                currencyEditor.setValue(new ScaleTwoDecimal(0));
                row.getCellContent().add(currencyEditor.getAsText());
            }
        }
    }

    /**
     * Calculate the sub total for the lineItemGroup passed in.
     *
     * @param lineItemGroup the group to calculate
     * @param parentRow the row to add the subototal row to
     * @param cssClass the css class to use for this row
     */
    private void processGroupSubTotal(LineItemGroup lineItemGroup, LineItemRow parentRow, String cssClass) {
    	ScaleTwoDecimal total = new ScaleTwoDecimal(0);
        if (!lineItemGroup.isCalculateGroupSubTotal()) {
            return;
        }

        for (LineItemObject subLineItem : lineItemGroup.getLineItems()) {
            total = total.add(subLineItem.getAmount());
        }

        LineItemRow row = findRow(parentRow.getChildRows(), parentRow.getId() + "_subTotal");

        // Create a new row if a group sub total row is not found
        if (row == null) {
            row = new LineItemRow(parentRow.getId() + "_subTotal");
            row.setLineItemId(parentRow.getId() + "_subTotal");
            row.setCssClass(cssClass);
            row.getCellContent().add(lineItemGroup.getGroupName() + " Subtotal");
            parentRow.getChildRows().add(row);
            lineIndex++;
        }

        // Add the total as value for that row
        row.getValues().add(total);
        currencyEditor.setValue(total);
        row.getCellContent().add(currencyEditor.getAsText());
    }

    /**
     * Flatten the rows recursively and add disclosure links as needed.
     *
     * @param rows the rows to process (this is the top level group rows)
     * @param open true if this row should be shown open
     */
    private void flattenSubLineItemRows(List<LineItemRow> rows, boolean open) {

        for (LineItemRow row : rows) {
            if (!open) {
                row.setCssClass(row.getCssClass() + " collapse");
            }

            if (renderDisclosureLinks && !row.getChildRows().isEmpty()) {
                String linkText = row.getCellContent().get(0);
                if (renderOpened) {
                    linkText = "<span class=\"icon-chevron-down\" aria-hidden=\"true\"></span> " + linkText;
                } else {
                    linkText = "<span class=\"icon-chevron-right\" aria-hidden=\"true\"></span> " + linkText;
                }
                row.getCellContent().set(0, "<a class=\"uif-lineItem-disclosure\"> " + linkText + "</a>");
            }

            flattenedRows.add(row);
            flattenSubLineItemRows(row.getChildRows(), renderOpened);
        }
    }

    /**
     * The property name of the LineItemTable, this property MUST be a list of Period objects.
     *
     * @return the propertyName for binding
     * @see org.kuali.coeus.common.framework.impl.Period
     */
    @BeanTagAttribute
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * @see #getPropertyName()
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * The binding info for this LineItemTable.
     *
     * @return the BindingInfo for this component
     */
    @BeanTagAttribute
    public BindingInfo getBindingInfo() {
        return bindingInfo;
    }

    /**
     * @see #getBindingInfo()
     */
    public void setBindingInfo(BindingInfo bindingInfo) {
        this.bindingInfo = bindingInfo;
    }

    /**
     * HeaderRow which contains the the column headers, cannot be set.
     *
     * @return the header row
     */
    public LineItemRow getHeaderRow() {
        return headerRow;
    }

    /**
     * FlattendRows which contain the rows that make up this table in-order, cannot be set.
     *
     * @return the rows generated by this LineItemTable in-order.
     */
    public List<LineItemRow> getFlattenedRows() {
        return flattenedRows;
    }

    /**
     * The format the dates in the date range for the period column should used, such as "MM/dd/yyyy".
     *
     * @return the date format
     */
    @BeanTagAttribute
    public String getDateRangeFormat() {
        return dateRangeFormat;
    }

    /**
     * @see #getDateRangeFormat()
     */
    public void setDateRangeFormat(String dateRangeFormat) {
        this.dateRangeFormat = dateRangeFormat;
    }

    /**
     * True if the total column is being rendered, false otherwise.
     *
     * @return true if the total column is being rendered, false otherwise
     */
    @BeanTagAttribute
    public boolean isRenderRowTotalColumn() {
        return renderRowTotalColumn;
    }

    /**
     * @see #isRenderRowTotalColumn()
     */
    public void setRenderRowTotalColumn(boolean renderRowTotalColumn) {
        this.renderRowTotalColumn = renderRowTotalColumn;
    }

    /**
     * The max number of period columns to show by default in this table.
     *
     * @return max number of period columns
     */
    @BeanTagAttribute
    public int getNumPeriodColumns() {
        return numPeriodColumns;
    }

    /**
     * @see #getNumPeriodColumns()
     */
    public void setNumPeriodColumns(int numPeriodColumns) {
        this.numPeriodColumns = numPeriodColumns;
    }

    /**
     * True if rendering disclosure links for the LineItems which have children.
     *
     * @return true if rendering disclosure links for the LineItems which have children
     */
    @BeanTagAttribute
    public boolean isRenderDisclosureLinks() {
        return renderDisclosureLinks;
    }

    /**
     * @see #isRenderRowTotalColumn()
     */
    public void setRenderDisclosureLinks(boolean renderDisclosureLinks) {
        this.renderDisclosureLinks = renderDisclosureLinks;
    }

    /**
     * True if the disclosure child items are shown and the links are displayed as open.
     *
     * @return true if the disclosure child items are shown and the links are displayed as open
     */
    @BeanTagAttribute
    public boolean isRenderOpened() {
        return renderOpened;
    }

    /**
     * @see #isRenderOpened()
     */
    public void setRenderOpened(boolean renderOpened) {
        this.renderOpened = renderOpened;
    }
}
