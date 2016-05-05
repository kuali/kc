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
Kc.Global = Kc.Global || {};
(function (namespace, $) {
    // set all modals to static behavior (clicking out does not close)
    $.fn.modal.Constructor.DEFAULTS.backdrop = "static";
    $(document).on("ready", function(){
        $.validator.addMethod("kcValidateDate", function(value,element){
            return this.optional(element) || namespace.validateDate(value);
        },"Invalid Date");
        $.validator.addMethod("kcValidateCurrency", function(value,element){
            return this.optional(element) || namespace.validateCurrency(value);
        },"Must be a number, with no more than 12 total digits and 2 digits to the right of the decimal point.");
        $(".uif-dateControl").each(function() {
            //make sure all date inputs check for valid dates, in case they are never focused
            $(this).rules("add",{kcValidateDate: true});
        });
        $(document).on("focus", ".uif-dateControl", function(){
            //if a date input is added after document ready event.
            $(this).rules("add",{kcValidateDate: true});
        });
        $(document).on("focus", ".uif-currencyControl", function(){
            //if a date input is added after document ready event.
            $(this).rules("add",{kcValidateCurrency: true});
        });
      $(document).on("scroll", function(){
        namespace.makeApplicationFooterSticky();
      });
        $(window).on("resize",function(){
            namespace.makeApplicationFooterSticky();
            $(".kc-navMenuDropdown .dropdown-menu").each(function(){
                var windowWidth = $(window).width();
                var menuLeftPosition = $(this).parent().position().left;
                $(this).css("cssText","min-width:0px !important");
                $(this).css("max-width","868px");
                $(this).css("width",windowWidth-menuLeftPosition);
            });
        });
        // date conversion for date fields to full leading 0 - for days and months and to full year dates
        $(document).on("blur", ".uif-dateControl", function(){
            var dateFormat = $.datepicker._defaults.dateFormat;
            var date = $(this).val();
            if (!date) {
                return;
            }

            date = date.replace(/-/g, "/");

            if (date && (date.match(/\//g) || []).length === 2) {

                // find the expected position and value of year in the string based on date format
                var year;
                if (dateFormat.indexOf("y") === 0) {
                    year = date.substr(0, date.indexOf("/"));
                }
                else {
                    year = date.substr(date.lastIndexOf("/") + 1, date.length - 1);
                }

                // when year is length of 2 append the first 2 numbers of the current full year (ie 14 -> 2014)
                if (year.length === 2) {
                    var currentDate = new Date();
                    year = currentDate.getFullYear().toString().substr(0,2) + year;
                }

                if (!namespace.validateDate(date)) {
                    return;
                }

                var dateObj = new Date(date);
                dateObj.setFullYear(year);

                var formattedDate = $.datepicker.formatDate(dateFormat, dateObj);
                $(this).val(formattedDate);
            }
        });
        $(document).on("blur", ".uif-currencyControl", function(){
            var value = $(this).val()
            value = value.replace(/,/g,"");

            if (!namespace.validateCurrency(value)) {
                return;
            }
            $(this).val(Kc.PropDev.Budget.formatMoney(new Number(value),2, '.', ','));
        });
    });
    namespace.validateCurrency = function(value) {
        value = value.replace(/,/g,"");
        var regex = /^-?([0-9]{0,10}\.[0-9]{1,2}|[0-9]{1,10})$/
        if (regex.test(value)) {
            return true;
        }
        return false
    }
    namespace.validateDate = function(value) {
        var dateFormat = $.datepicker._defaults.dateFormat;
        var date = value.replace(/-/g, "/");

        if (date && (date.match(/\//g) || []).length === 2) {
            // find the expected position and value of year in the string based on date format
            var year;
            var month;
            var day;
            if (dateFormat.indexOf("y") === 0) {
                year = date.substr(0, date.indexOf("/"));
                month = "0" + date.substr(date.indexOf("/")+1,date.lastIndexOf("/") - date.indexOf("/")-1)
                day = "0" + date.substr(date.lastIndexOf("/") + 1, date.length - 1);
            }
            else {
                month = "0" +  date.substr(0, date.indexOf("/"));
                day ="0" +  date.substr(date.indexOf("/")+1,date.lastIndexOf("/") - date.indexOf("/")-1)
                year = date.substr(date.lastIndexOf("/") + 1, date.length - 1);
            }

            if (year.length === 2) {
                var currentDate = new Date();
                year = currentDate.getFullYear().toString().substr(0,2) + year;
            }

            var isoDate = year + "-" + month.substr(month.length-2,2) + "-" + day.substr(day.length-2,2);
            if (isNaN(new Date(isoDate))) {
                return false;
            }
            return true
        }
        return false
    }
    namespace.attachRedirectHandler = function() {
        $(window).unbind("message.redirect");
        $(window).on("message.redirect", function (event) {
            if ((typeof event.originalEvent.data === 'string' || event.originalEvent.data instanceof String) && event.originalEvent.data.indexOf("redirect:") === 0) {
                var location = event.originalEvent.data.substr(9);
                window.location.replace(location);
                return false;
            }
        });
    }
    namespace.makeApplicationFooterSticky = function() {
        var docHeight = $(window).height() + $(window).scrollTop();
        var footerHeight = $('#Uif-ApplicationFooter-Wrapper').height();
        var footerTop = $('#Uif-ApplicationFooter-Wrapper').position().top + footerHeight;

        if (footerTop < docHeight) {
            $('#Uif-ApplicationFooter-Wrapper').css('margin-top', (docHeight - footerTop) + 'px');
        }
    }
    namespace.validateAttachmentFile = function(object,maxSize){
        var file =  object.files[0]
        if (file.size > maxSize) {
            alert ("Processing " + file.name + " of type " + file.type + " failed.\nError: File is too large");
            $(object).val("");
        }
    }
})(Kc.Global, jQuery);

KradResponse.prototype.updateComponentHandler = function (content, dataAttr) {
    var id = dataAttr.id;

    var $componentInDom = jQuery("#" + id);

    hideTooltips($componentInDom);

    var component = jQuery("#" + id + "_update", content);

    // remove any already existing matching dialogs from the view
    jQuery('.modal', component).each(function () {
            var existingComponent = jQuery('#' + this.id, jQuery("[data-role='View']"));
            var isPlaceHolder = existingComponent.hasClass(kradVariables.CLASSES.PLACEHOLDER);
            var displayedModal = isDisplayedModal(existingComponent);

            // do not remove placeholders or displayedModals
            if (!isPlaceHolder && !displayedModal) {
                existingComponent.remove();
            }
        }
    );

    jQuery("." + kradVariables.CLASSES.PLACEHOLDER, component).each(function () {
        var existingComponent = jQuery('#' + this.id, jQuery("[data-role='View']"));

        if (existingComponent.length && existingComponent.is(".modal") && isDisplayedModal(existingComponent)) {
            jQuery(this).remove();
        }
        else {
            existingComponent.remove();
        }
    });

    // is the new component now required
    var nowRequired = jQuery(".required", component).size() > 0;

    // get the old label
    var oldLabel = jQuery("#" + id + "_label");

    // if found then remove any required indicators and then add/readd back
    if (oldLabel) {
        oldLabel.find("span." + kradVariables.REQUIRED_MESSAGE_CLASS).remove();
        if (nowRequired) {
            oldLabel.append("<span class='" + kradVariables.REQUIRED_MESSAGE_CLASS + "'>*</span>");
        }
    }

    // special label handling, if any
    var theLabel = jQuery("[data-label_for='" + id + "']", component);
    if (jQuery(".displayWith-" + id).length && theLabel.length) {
        theLabel.addClass("displayWith-" + id);
        jQuery("span.displayWith-" + id).replaceWith(theLabel);

        component.remove("[data-label_for='" + id + "']");
    }

    // remove old stuff
    if (jQuery("#" + id + "_errors").length) {
        jQuery("#" + id + "_errors").remove();
    }

    jQuery("input[data-for='" + id + "']").each(function () {
        jQuery(this).remove();
    });

    // replace component
    if ($componentInDom.length) {
        var wasPlaceholder = $componentInDom.hasClass(kradVariables.CLASSES.PLACEHOLDER);

        var componentContent = component.html();

        // for modal content update, we just want to replace the contents within the component
        var displayedModal = isDisplayedModal($componentInDom);
        if (displayedModal) {
            var innerComponentContent = jQuery(componentContent).html();
            $componentInDom.html(innerComponentContent);
            var relatedContent = jQuery(componentContent).filter("input[data-for='" + id + "']");
            $componentInDom.append(relatedContent);
        }
        else {
            $componentInDom.replaceWith(componentContent);
        }

        // Removes traces of dialog if one was destroyed by the refresh
        ensureDialogBackdropRemoved();

        // move all dialogs to dialog section
        jQuery('.modal').appendTo('#Uif-Dialogs');

        $componentInDom = jQuery("#" + id);

        if ($componentInDom.parent().is("td")) {
            $componentInDom.parent().show();
        }

        var displayWithLabel = jQuery(".displayWith-" + id);
        displayWithLabel.show();
        if (displayWithLabel.parent().is("td") || displayWithLabel.parent().is("th")) {
            displayWithLabel.parent().show();
        }

        // assume this content is open if being refreshed
        var open = $componentInDom.attr("data-open");
        if (open !== undefined && open === "false") {
            $componentInDom.attr("data-open", "true");
            $componentInDom.show();
        }

        // runs scripts on the span or div with id
        runHiddenScripts(id);
        runHiddenScripts("Uif-Dialogs");

        if (displayedModal) {
            writeMessagesForChildGroups(id);
            writeMessagesForGroup(id, getValidationData($componentInDom, true), true);
        }

        // Only for table layout collections. Keeps collection on same page.
        var currentPage = retrieveFromSession(id + ":currentPageRichTable");
        if (currentPage != null) {
            openDataTablePage(id, currentPage);
        }

        $componentInDom.unblock({onUnblock: function () {
            var isDialog = $componentInDom.hasClass(kradVariables.CLASSES.MODAL);

            // if this is the first time the content is being shown, and it is not a dialog, add highlighting
            if (wasPlaceholder && !isDialog) {
                $componentInDom.addClass(kradVariables.PROGRESSIVE_DISCLOSURE_HIGHLIGHT_CLASS);
                $componentInDom.animate({backgroundColor: "transparent"}, 6000);
            }
        }
        });

        $componentInDom.trigger(kradVariables.EVENTS.ADJUST_STICKY);
        $componentInDom.trigger(kradVariables.EVENTS.UPDATE_CONTENT);

        // Perform focus and jumpTo based on the data attributes
        performFocusAndJumpTo(true, $componentInDom.data(kradVariables.FOCUS_ID), $componentInDom.data(kradVariables.JUMP_TO_ID), $componentInDom.data(kradVariables.JUMP_TO_NAME));
    }
}

// finds the page content in the returned content and updates the page, then processes breadcrumbs and hidden
// scripts. While processing, the page contents are hidden
KradResponse.prototype.updatePageHandler = function (content, dataAttr) {
    var pageUpdate = jQuery("#page_update", content);
    var page = jQuery("[data-role='Page']", pageUpdate);
    var viewContent = jQuery("#" + kradVariables.VIEW_CONTENT_WRAPPER);

    // remove any already existing matching dialogs from the view
    jQuery('.modal', page).each(function () {
        var existingComponent = jQuery('#' + this.id, jQuery("[data-role='View']"));
        existingComponent.remove();
    });

    jQuery("." + kradVariables.CLASSES.PLACEHOLDER, page).each(function () {
        var existingComponent = jQuery('#' + this.id, jQuery("[data-role='View']"));

        if (existingComponent.length && existingComponent.is(".modal") && isDisplayedModal(existingComponent)) {
            jQuery(this).remove();
        }
        else {
            existingComponent.remove();
        }
    });

    page.hide();

    // give a selector that will avoid the temporary iframe used to hold ajax responses by the jquery form plugin
    var pageInLayout = "#" + kradVariables.VIEW_CONTENT_WRAPPER + " [data-role='Page']:first";
    hideTooltips(pageInLayout);

    var $pageInLayout = jQuery(pageInLayout);

    // update page contents from response
    viewContent.find("[data-for='" + $pageInLayout.attr("id") + "']").remove();
    $pageInLayout.replaceWith(pageUpdate.find(">*"));
    $pageInLayout = jQuery(pageInLayout);

    // Removes traces of dialog if one was destroyed by the refresh
    ensureDialogBackdropRemoved();

    // move all dialogs to dialog section
    jQuery('.modal').appendTo('#Uif-Dialogs');

    // remove detached dialogs
    jQuery("[data-detached='true']").remove();

    pageValidatorReady = false;
    runHiddenScripts(kradVariables.VIEW_CONTENT_WRAPPER, false, true);
    runHiddenScripts("Uif-Dialogs");

    markActiveMenuLink();

    viewContent.trigger(kradVariables.EVENTS.ADJUST_PAGE_MARGIN);
    $pageInLayout.trigger(kradVariables.EVENTS.UPDATE_CONTENT);

    $pageInLayout.show();

    $pageInLayout.trigger(kradVariables.EVENTS.ADJUST_STICKY);
    $pageInLayout.trigger(kradVariables.EVENTS.PAGE_UPDATE_COMPLETE);

    // Perform focus and jumpTo based on the data attributes
    performFocusAndJumpTo(true, page.data(kradVariables.FOCUS_ID), page.data(kradVariables.JUMP_TO_ID), page.data(kradVariables.JUMP_TO_NAME));
}

/**
 * Validate that a specific field's control defined by the selector/jQuery array passed in.  Also calls dependsOnCheck
 * to validate any dependant fields.
 *
 * @param fieldControl selector/jQuery array that represents the control to validate
 */
function validateFieldValue(fieldControl) {
    // skip validation for add line fields unless there is a value. The add button will handle validation
    if (jQuery(fieldControl).attr('id').match(new RegExp(kradVariables.ID_SUFFIX.ADD_LINE_INPUT_FIELD))
        && !jQuery(fieldControl).val()) {
        return true;
    }

    //remove the ignore class if any due to a bug in the validate
    //plugin for direct validation on certain types
    if (jQuery(fieldControl).attr('id').match(/ID_SUFFIXADD_LINE_INPUT_FIELD/)) {
        jQuery(fieldControl).removeClass("ignoreValid");
        return true;
    }

    var hadIgnore = false;
    if (jQuery(fieldControl).hasClass("ignoreValid")) {
        jQuery(fieldControl).removeClass("ignoreValid");
        hadIgnore = true;
    }
    clientErrorExistsCheck = true;

    // skip fields in hidden dialogs
    if (jQuery(fieldControl).is(kradVariables.DIALOG_SELECTOR + ":hidden [data-role='Control']")) {
        return true;
    }

    //the validation call
    var valid = jQuery(fieldControl).valid();
    dependsOnCheck(fieldControl, new Array());
    clientErrorExistsCheck = false;
    if (hadIgnore) {
        jQuery(fieldControl).addClass("ignoreValid");
    }

    return valid;
}

/**
 * Close an open iframe dialog by using post message to pass a message event.
 */
function closeIframeDialog() {
    window.parent.postMessage(kradVariables.MODAL.MODAL_CLOSE_DIALOG, "*");
    // Fix for persistent loading message in IE
    hideLoading();
}

/** below override to add numeric text sort detection **/

/**
 * retrieve column values for sorting a column marked with sSortDataType:dom-text in aoColumns
 *
 * @param oSettings - an object provided by datatables containing table information and configuration
 * @param iColumn - the column whose values are to be retrieved
 * @return an array of column values - extracted from any surrounding markup
 */
jQuery.fn.dataTableExt.afnSortData['dom-text'] = function (oSettings, iColumn, iVisColumn) {
    var aData = [];
    var numericColumn = true;

    jQuery(oSettings.oApi._fnGetTrNodes(oSettings)).each(function () {
        var td = jQuery('>td:eq(' + iVisColumn + '):first', this);
        var input = jQuery(td).find('input:text');
        var value = "";
        if (input.length != 0) {
            value = input.val();
        } else {
            // check for linkField
            var linkField = jQuery(td).find('.uif-linkField');
            if (linkField.length != 0) {
                value = linkField.text().trim();
            } else {
                // find span for the data or input field and get its text
                var inputField = jQuery(td).find('.uif-field');
                if (inputField.length != 0) {
                    value = jQuery.trim(inputField.text());
                } else {
                    // just use the text within the cell
                    value = jQuery(td).text().trim();
                }
            }
        }

        // strip leading $, if present
        if (value && typeof value === "string" && value.charAt(0) == '$') {
            value = value.substring(1);
        }

        // strip trailing %, if present
        if (value && typeof value === "string" && value.charAt(value.length - 1) == '%') {
            value = value.substring(0, value.length - 1);
        }

        // if value is a comma separated number remove commas
        if (/^([0-9]*,[0-9]*)*$/.test(value)) {
           value = value.replace(/,/g, "");
        }

        // if the column still may be numeric, check to see if this value is not numeric
        if (numericColumn && isNaN(value)) {
            numericColumn = false;
        }

        var additionalDisplaySeparatorIndex = value.indexOf("*-*");
        if (additionalDisplaySeparatorIndex != -1) {
            value = value.substring(0, additionalDisplaySeparatorIndex).trim();
        }
        aData.push(value);
    });

    // force the column to sort numerically if all text values evaluated as numeric
    if (numericColumn) {
        oSettings.aoColumns[iColumn].sType = "numeric";
    }

    return aData;
};