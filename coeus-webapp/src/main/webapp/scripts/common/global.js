var Kc = Kc || {};
Kc.Global = Kc.Global || {};
(function (namespace, $) {
    // set all modals to static behavior (clicking out does not close)
    $.fn.modal.Constructor.DEFAULTS.backdrop = "static";
})(Kc.Global, jQuery);

//Temporary overrides of krad functions:

/**
 * Sets up the conditional refresh mechanism in js by adding a change handler to the control
 * which may satisfy the conditional refresh condition passed in.  When the condition is satisfied,
 * refresh the necessary content specified by id by making a server call to retrieve a new instance
 * of that component
 *
 * @param controlName - value for the name attribute for the control the event should be generated for
 * @param refreshId - id for the component that should be refreshed when condition occurs
 * @param condition - function which returns true to refresh, false otherwise
 * @param methodToCall - name of the method that should be invoked for the refresh call (if custom method is needed)
 * @param fieldsToSend (optional) - limit the fields to send to property names defined in an array
 */
function setupRefreshCheck(controlName, refreshId, condition, methodToCall, fieldsToSend) {
    var namespace = ".ref" + refreshId + controlName.replace("[", "-");
    jQuery(document).off("change" + namespace, "[name='" + escapeName(controlName) + "']");
    jQuery(document).on("change" + namespace, "[name='" + escapeName(controlName) + "']", function () {
        // visible check because a component must logically be visible to refresh
        var refreshComp = jQuery("#" + refreshId);
        if (refreshComp.length) {
            if (condition()) {
                retrieveComponent(refreshId, methodToCall, null, null, false, fieldsToSend);
            }
        }
    });
}

/**
 * Sets up the progressive disclosure mechanism in js by adding a change handler to the control
 * which may satisfy the progressive disclosure condition passed in.  When the condition is satisfied,
 * show the necessary content, otherwise hide it.  If the content has not yet been rendered then a server
 * call is made to retrieve the content to be shown.  If alwaysRetrieve is true, the component
 * is always retrieved from the server when disclosed.
 * Do not add check if the component is part of the "old" values on a maintanance document (endswith _c0).
 *
 * @param controlName
 * @param disclosureId
 * @param condition - function which returns true to disclose, false otherwise
 * @param methodToCall - name of the method that should be invoked for the retrieve call (if custom method is needed)
 * @param fieldsToSend (optional) - limit the fields to send to property names defined in an array
 */
function setupProgressiveCheck(controlName, disclosureId, condition, alwaysRetrieve, methodToCall, fieldsToSend) {
    var namespace = ".pref" + disclosureId + controlName.replace("[", "-");
    jQuery(document).off("change" + namespace, "[name='" + escapeName(controlName) + "']");
    jQuery(document).on("change" + namespace, "[name='" + escapeName(controlName) + "']", function () {
        var refreshDisclosure = jQuery("#" + disclosureId);
        if (refreshDisclosure.length) {
            var displayWithId = disclosureId;

            if (condition()) {
                if (refreshDisclosure.data("role") == "placeholder" || alwaysRetrieve) {
                    retrieveComponent(disclosureId, methodToCall, null, null, false, fieldsToSend);
                }
                else {
                    refreshDisclosure.addClass(kradVariables.PROGRESSIVE_DISCLOSURE_HIGHLIGHT_CLASS);
                    refreshDisclosure.show();

                    if (refreshDisclosure.parent().is("td")) {
                        refreshDisclosure.parent().show();
                    }

                    refreshDisclosure.animate({backgroundColor: "transparent"}, 6000);

                    //re-enable validation on now shown inputs
                    hiddenInputValidationToggle(disclosureId);

                    var displayWithLabel = jQuery(".displayWith-" + displayWithId);
                    displayWithLabel.show();
                    if (displayWithLabel.parent().is("td") || displayWithLabel.parent().is("th")) {
                        displayWithLabel.parent().show();
                    }
                }
            }
            else {
                refreshDisclosure.hide();

                // ignore validation on hidden inputs
                hiddenInputValidationToggle(disclosureId);

                var displayWithLabel = jQuery(".displayWith-" + displayWithId);
                displayWithLabel.hide();
                if (displayWithLabel.parent().is("td") || displayWithLabel.parent().is("th")) {
                    displayWithLabel.parent().hide();
                }
            }

            hideEmptyCells();
        }
    });
}

function validateLineFields(controlsToValidate, writePageMessages) {
    var valid = true;
    var invalidId = "";

    // skip completely if client validation is off
    if (!validateClient) {
        return valid;
    }

    jQuery.watermark.hideAll();

    // Turn on this flag to avoid prematurely writing out messages which will cause performance issues if MANY
    // fields have validation errors simultaneously (first we are only checking for errors, not checking and
    // writing simultaneously like normal)
    clientErrorExistsCheck = true;

    // Temporarily turn off this flag to avoid traversing unneeded logic (all messages will be shown at the end)
    var tempMessagesSummariesShown = messageSummariesShown;
    messageSummariesShown = false;

    controlsToValidate.each(function () {
        var control = jQuery(this);
        var fieldId = jQuery(this).closest("div[data-role='InputField']").attr("id");
        var field = jQuery("#" + fieldId);
        var parent = field.data("parent");
        var validValue = true;

        // remove ignoreValid because there are issues with the plugin if it stays on
        control.removeClass("ignoreValid");

        haltValidationMessaging = true;

        if (control.length && !control.prop("disabled") && !control.is("span")) {
            control.valid();
            if (control.hasClass("error")) {
                validValue = false;
            }
        }

        var data = getValidationData(field);
        handleMessagesAtGroup(parent, fieldId, data, true);

        haltValidationMessaging = false;

        //details visibility check
        if (control.not(":visible") && !validValue) {
            cascadeOpen(control);
        }

        if (!validValue) {
            valid = false;
            if (invalidId.length == 0) {
                invalidId = this.id;
            }
        }

        control.addClass("ignoreValid");
    });

    // Toggle the flag back to default
    clientErrorExistsCheck = false;

    // Message summaries are going to be shown
    messageSummariesShown = tempMessagesSummariesShown;

    if (writePageMessages && messageSummariesShown) {
        // Finally, write the result of the validation messages
        writeMessagesForPage();
    }

    jQuery.watermark.showAll();

    if (invalidId.length != 0) {
        jQuery("#" + invalidId).focus();
    }

    return valid;
}

