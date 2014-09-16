/**
 * Setup the lineItem table's click handler for disclosures
 *
 * @param $lineItemTable the table jQuery object
 */
function setupLineItemTable($lineItemTable) {

    $lineItemTable.on("click", ".uif-lineItem-disclosure", function(){
        var $link = jQuery(this);
        var $lineItemRow = $link.closest("tr");

        var id = $lineItemRow.attr("id");
        var $children = jQuery("[data-parent_row='" + id + "']");

        // Hide or reopen children if they wer open previously
        if ($children.is(":visible")) {
            hideSubItems($children);
            $children.hide(400);
            $lineItemRow.data("open", false);
        }
        else {
            $children.show(400);
            showSubItems($children);
            $lineItemRow.data("open", true);
        }
    });
}

/**
 * Hide the sub items passed in for a lineItem disclosure and its child items also
 *
 * @param $items the items fo the line item to hide
 */
function hideSubItems ($items) {
    $items.each(function(){
        var id = jQuery(this).attr("id");
        var $children = jQuery("[data-parent_row='" + id + "']");

        if ($children.length) {
            hideSubItems($children);
        }
        $children.hide(400);
    });
}

/**
 * Reopen sub items of the line item and its children, if they were in an open state previously
 *
 * @param $items the items of the line item to reopen
 */
function showSubItems ($items) {
    $items.each(function(){
        var id = jQuery(this).attr("id");
        var $children = jQuery("[data-parent_row='" + id + "']");

        if (jQuery(this).data("open")) {
            if ($children.length) {
                showSubItems($children);
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
function prevPeriod(button) {
    // When when scrolling to a previous period make sure next button is enabled
    var nextButton = jQuery(button).next();
    nextButton.prop('disabled', false);
    nextButton.removeClass("disabled");

    // Find the id of the table by using the parent header for this button
    var tableId = jQuery(button).closest("header").data("header_for") + "_table";

    // Period columns represented by the th in thead
    var columns = jQuery("#" + tableId + " thead tr").find(":not(:first-child, .uif-lineItem-rowTotal)");

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
        jQuery(button).prop('disabled', true);
    }

    // Iterate over each row and hide the columns we are hiding by index, and show the columns we are showing by index
    jQuery("#" + tableId + " tbody tr").each(function(){
        var columns = jQuery(this).find(":not(:first-child, .uif-lineItem-rowTotal)");
        columns.eq(hideIndex).hide();
        columns.eq(showIndex).show();
    });
}

/**
 * Scrolls the period columns of the line item table forward to the next period
 *
 * @param button the next period button (it must be in the header of the group)
 */
function nextPeriod(button) {
    // When when scrolling to a next period make sure previous button is enabled
    var prevButton = jQuery(button).prev();
    prevButton.prop('disabled', false);
    prevButton.removeClass("disabled");

    // Find the id of the table by using the parent header for this button
    var tableId = jQuery(button).closest("header").data("header_for") + "_table";

    // Period columns represented by the th in thead
    var columns = jQuery("#" + tableId + " thead tr").find(":not(:first-child, .uif-lineItem-rowTotal)");

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
        jQuery(button).prop('disabled', true);
    }

    // Iterate over each row and hide and show the columns by index discovered above
    jQuery("#" + tableId + " tbody tr").each(function(){
        var columns = jQuery(this).find(":not(:first-child, .uif-lineItem-rowTotal)");
        columns.eq(hideIndex).hide();
        columns.eq(showIndex).show();
    });
}