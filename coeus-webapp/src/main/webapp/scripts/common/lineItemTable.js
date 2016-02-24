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
var Kc = Kc || {};
Kc.LineItemTable = Kc.LineItemTable || {};
(function(namespace, $) {
    /**
     * Setup the lineItem table's click handler for disclosures
     *
     * @param $lineItemTable the table jQuery object
     */
    namespace.setupLineItemTable = function($lineItemTable) {
        $lineItemTable.on("click", ".uif-lineItem-disclosure", function(){
            var $link = $(this);
            var $lineItemRow = $link.closest("tr");

            var id = $lineItemRow.attr("id");
            var $children = $("[data-parent_row='" + id + "']");

            // Hide or reopen children if they were open previously
            if ($children.is(":visible")) {
                namespace.hideSubItems($children);
                $children.hide(400);
                $lineItemRow.data("open", false);
                $link.find("> .icon-chevron-down").removeClass("icon-chevron-down").addClass("icon-chevron-right");
            }
            else {
                $children.show(400);
                namespace.showSubItems($children);
                $lineItemRow.data("open", true);
                $link.find("> .icon-chevron-right").removeClass("icon-chevron-right").addClass("icon-chevron-down");
            }
        });
    }

    /**
     * Hide the sub items passed in for a lineItem disclosure and its child items also
     *
     * @param $items the items fo the line item to hide
     */
    namespace.hideSubItems = function($items) {
        $items.each(function(){
            var id = $(this).attr("id");
            var $children = $("[data-parent_row='" + id + "']");

            if ($children.length) {
                namespace.hideSubItems($children);
            }
            $children.hide(400);
        });
    }

    /**
     * Reopen sub items of the line item and its children, if they were in an open state previously
     *
     * @param $items the items of the line item to reopen
     */
    namespace.showSubItems = function($items) {
        $items.each(function(){
            var id = $(this).attr("id");
            var $children = $("[data-parent_row='" + id + "']");

            if ($(this).data("open")) {
                if ($children.length) {
                    namespace.showSubItems($children);
                }
                $children.show(400);
            }
        });
    }

    /**
     * Scrolls the period columns of the line item table back one previous period
     *
     * @param button the previous period button (it must be in the header of the group)
     */
    namespace.prevPeriod = function(button) {
        // When when scrolling to a previous period make sure next button is enabled
        var nextButton = $(button).next();
        nextButton.prop('disabled', false);
        nextButton.removeClass("disabled");

        // Find the id of the table by using the parent header for this button
        var tableId = $(button).closest("header").data("header_for") + "_table";

        // Period columns represented by the th in thead
        var columns = $("#" + tableId + " thead tr").find(":not(:first-child, .uif-lineItem-rowTotal)");

        // The last visible column of periods is the one that will be hidden
        var hideColumn = columns.filter(":visible:last");
        hideColumn.hide();
        var hideIndex = columns.index(hideColumn);

        // The column before the first visible column of periods is the one that will be shown
        var showColumn = columns.filter(":visible:first").prev();
        var showIndex = columns.index(showColumn);
        showColumn.show();

        // Disable the previous button if the first column (index 0) of periods is currently showing
        if (columns.eq(0).is(":visible")) {
            $(button).prop('disabled', true);
        }

        // Iterate over each row and hide the columns we are hiding by index, and show the columns we are showing by index
        $("#" + tableId + " tbody tr").each(function(){
            var columns = $(this).find(":not(:first-child, .uif-lineItem-rowTotal)");
            columns.eq(hideIndex).hide();
            columns.eq(showIndex).show();
        });
    }

    /**
     * Scrolls the period columns of the line item table forward to the next period
     *
     * @param button the next period button (it must be in the header of the group)
     */
    namespace.nextPeriod = function (button) {
        // When when scrolling to a next period make sure previous button is enabled
        var prevButton = $(button).prev();
        prevButton.prop('disabled', false);
        prevButton.removeClass("disabled");

        // Find the id of the table by using the parent header for this button
        var tableId = $(button).closest("header").data("header_for") + "_table";

        // Period columns represented by the th in thead
        var columns = $("#" + tableId + " thead tr").find(":not(:first-child, .uif-lineItem-rowTotal)");

        // Hide the first visible column
        var hideColumn = columns.filter(":visible:first");
        var hideIndex = columns.index(hideColumn);
        hideColumn.hide();

        // The column that is the first hidden column of the items after the column we are hiding
        var showColumn = hideColumn.nextAll(":hidden:first");
        var showIndex = columns.index(showColumn);
        showColumn.show();

        // Disable the previous button if the first column (index 0) of periods is currently showing
        if (showColumn.nextAll(":hidden").length === 0) {
            $(button).prop('disabled', true);
        }

        // Iterate over each row and hide and show the columns by index discovered above
        $("#" + tableId + " tbody tr").each(function(){
            var columns = $(this).find(":not(:first-child, .uif-lineItem-rowTotal)");
            columns.eq(hideIndex).hide();
            columns.eq(showIndex).show();
        });
    }

})(Kc.LineItemTable, jQuery);
