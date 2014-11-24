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


/*
 * Copyright 2005-2014 The Kuali Foundation
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

/* ========================================================================
 * Holds configuration for making a server request (ajax and non-ajax) and
 * performs the request action
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 * ========================================================================*/

/**
 * Constructs a new request object with data from the given action object.
 *
 * @param action (optional) reference to the action that triggers the request. If given request
 * attributes will be pulled from the action data
 */
function KradRequest(action) {
    if (!action) {
        return;
    }

    if (action) {
        this.$action = action;
    }

    if (action.data("ajaxsubmit") !== undefined) {
        this.ajaxSubmit = action.data("ajaxsubmit");
    }

    this.additionalData = jQuery.extend({}, action.data(kradVariables.SUBMIT_DATA));

    this.methodToCall = this.additionalData['methodToCall'];

    if (action.data("successcallback") !== undefined) {
        this.successCallback = action.data("successcallback");
    }

    if (action.data("errorcallback") !== undefined) {
        this.errorCallback = action.data("errorcallback");
    }

    if (action.data("presubmitcall") !== undefined) {
        this.preSubmitCall = action.data("presubmitcall");
    }

    if (action.data("confirmdialogid") !== undefined) {
        this.confirmDialogId = action.data("confirmdialogid");
    }

    if (action.data("confirm_prompttext") !== undefined) {
        this.confirmPromptText = action.data("confirm_prompttext");
    }

    if (action.data("dismissdialogoption") !== undefined) {
        this.dismissDialogOption = action.data("dismissdialogoption");
    }

    if (action.data("dismissdialogid") !== undefined) {
        this.dismissDialogId = action.data("dismissdialogid");
    }

    if (action.data("validate") !== undefined) {
        this.validate = action.data("validate");
    }

    if (action.data("loadingmessage") !== undefined) {
        this.loadingMessage = action.data("loadingmessage");
    }

    if (action.data("disableblocking") !== undefined) {
        this.disableBlocking = action.data("disableblocking")
    }

    if (action.data("ajaxreturntype") !== undefined) {
        this.ajaxReturnType = action.data("ajaxreturntype");
    }

    if (action.data("refreshid") !== undefined) {
        this.refreshId = action.data("refreshid");
    }

    if (action.data("dirtyonaction") !== undefined) {
        this.dirtyOnAction = action.data("dirtyonaction");
    }

    if (action.data("cleardirtyonaction") !== undefined) {
        this.clearDirtyOnAction = action.data("cleardirtyonaction");
    }

    if (action.data("fieldstosend") !== undefined) {
        this.fieldsToSend = action.data("fieldstosend");
    }
}

KradRequest.prototype = {
    // name of the controller method to be invoked
    methodToCall: "refresh",

    // jquery action component that is triggering the request (if any)
    $action: null,

    // additional data to send with the request (in addition to form data)
    additionalData: {},

    // only send field inputs specified by name in this array.  A group id or field id with "#" id selector prefix
    // can be used to send all inputs which are nested within them.  Sends only the data specified,
    // along with data required standard by the controller for KRAD.
    fieldsToSend: null,

    // indicates whether the request should be made with ajax or standard browser submit
    ajaxSubmit: true,

    // for ajax requests, specifies how the response should be handled
    ajaxReturnType: "update-page",

    // when the return type is update-component, indicates the id for the component that
    // should be updated
    refreshId: null,

    // indicates whether client side validation should be performed before making
    // the request (see ajaxReturnHandlers)
    validate: false,

    // indicates whether the form should be marked dirty when the action is taken (ex. an add line action)
    dirtyOnAction: false,

    // indicates if the form's dirty state should be cleared when the action is taken (ex. save)
    clearDirtyOnAction: false,

    // when blocking is enabled will display this text with the blocking overlay
    loadingMessage: getMessage(kradVariables.MESSAGE_LOADING),

    // jQuery object that should be blocked while the request is sent, if empty
    // and return type is update-component, the component will be blocked, else the full window
    // will be blocked
    elementToBlock: null,

    // indicates whether blocking should be disabled for the request
    disableBlocking: false,

    // function or script that should be invoked before the request is sent
    // if the function returns false the request is not carried out
    // the function can optionally take the request object and modify any of the
    // request attributes (for example add additional data)
    // Note as well: the preSubmitCall can be given as a string or function object. When given as a string it may
    // optionally take the request by including the parameter 'this'. Other literal parameters may be passed as well
    // (literal on client, but useful for passing server side variables)
    preSubmitCall: null,

    // id for the a dialog that should be used to confirm the action
    // this will trigger after the pre submit call (if successful). If the confirm action is taken on the
    // dialog, the action will then be retriggered
    confirmDialogId: null,

    // text to display in a confirmation dialog for confirming the action. Note this is similar to
    // confirmDialogId, except the dialog is created on the fly using the ok/cancel dialog. To show a custom
    // dialog, the confirmDialogId property should be used
    confirmPromptText: null,

    // when the request needs to dismiss a dialog, when the dialog should be dismissed
    // valid options are AFTERPRESUBMIT (with just returns) or WITHREQUEST
    dismissDialogOption: null,

    // when the request needs to dismiss a dialog, the id of the dialog that should be dismissed
    dismissDialogId: null,

    // function or script that is invoked after a successful ajax request
    // the function may take the response contents as a parameter
    // Note as well: the successCallback can be given as a string or function object. When given as a string it may
    // optionally take the response contents by including the parameter 'responseContents'. Other literal parameters
    // may be passed as well (literal on client, but useful for passing server side variables)
    successCallback: null,

    // function or script that is invoked after an error is encountered from an ajax request
    // (including when an incident page is returned. The function may take the response contents as a parameter
    // Note as well: the successCallback can be given as a string or function object. When given as a string it may
    // optionally take the response contents by including the parameter 'responseContents'. Other literal parameters
    // may be passed as well (literal on client, but useful for passing server side variables)
    errorCallback: null,

    // called to make the request and handle the response
    send: function () {
        var dialogDismissed = this._dismissDialogIfNecessary(kradVariables.DIALOG_DISMISS_OPTIONS.IMMEDIATE);

        // with immediate dialog dismiss the request should not continue
        if (dialogDismissed) {
            return;
        }

        var continueRequest = this._executePreSubmit();

        if (continueRequest) {
            this._continueAfterPreSubmit();
        }
    },

    // executes validation, pre-submit code, and any confirmation before contuining with the request
    _executePreSubmit: function () {
        if (!this._validateBeforeAction()) {
            return false;
        }

        // expose a variable for callbacks
        var kradRequest = this;

        // invoke the preSubmitCall script, if it evaluates to false return
        if (this.preSubmitCall) {
            if (typeof this.preSubmitCall === "string") {
                var preSubmitCode = "(function(){" + this.preSubmitCall + "})();";
                var preSubmitValid = eval(preSubmitCode);
            } else {
                var preSubmitValid = this.preSubmitCall(this);
            }

            if (!preSubmitValid) {
                clearHiddens();

                return false;
            }
        }

        return this._confirmAction();
    },

    // invoke validateForm if validate flag is true, if returns false do not continue
    _validateBeforeAction: function () {
        if (!this.validate) {
            return true;
        }

        valid = validateForm(this.$action);

        if (!valid) {
            clearHiddens();
        }

        return valid;
    },

    // if confirm dialog is or text is configured we need to show it and have the user confirm first
    _confirmAction: function () {
        if (!this.confirmDialogId && !this.confirmPromptText) {
            return true;
        }

        var kradRequest = this;
        var responseHandler = function (event) {
            if (event.response === 'true') {
                kradRequest._continueAfterPreSubmit();
            }
        };

        if (this.confirmDialogId) {
            showDialog(this.confirmDialogId, {responseHandler: responseHandler});
        }
        else {
            var confirmText = this.confirmPromptText;
            var evalIndex = this.confirmPromptText.indexOf('eval(');
            if (evalIndex >= 0) {
                confirmText = this.confirmPromptText.slice(evalIndex + 5, this.confirmPromptText.lastIndexOf(')'));
                confirmText = eval(confirmText);
            }

            confirmDialog(confirmText, undefined, {responseHandler: responseHandler});
        }

        return false;
    },

    // continues the request after the pre-submit checks have passed
    _continueAfterPreSubmit: function () {
        var dialogDismissed = this._dismissDialogIfNecessary(kradVariables.DIALOG_DISMISS_OPTIONS.PRESUBMIT);

        // with presubmit dialog dismiss the request should not continue
        if (dialogDismissed) {
            return;
        }

        //reset dirty form state
        if (this.clearDirtyOnAction) {
            dirtyFormState.reset();
        }

        //increase dirty field count when this flag is true
        if (this.dirtyOnAction) {
            dirtyFormState.incrementDirtyFieldCount();
        }

        // check for non-ajax request
        if (!this.ajaxSubmit) {
            dirtyFormState.reset();

            // submit non ajax call
            this._submitNonAjax();
            clearHiddens();

            return;
        }

        var data = {};

        data.methodToCall = this.methodToCall;
        data.ajaxReturnType = this.ajaxReturnType;
        data.ajaxRequest = this.ajaxSubmit;

        if (this.$action && this.$action.is("[" + kradVariables.ATTRIBUTES.ID + "]")) {
            data.triggerActionId = this.$action.attr(kradVariables.ATTRIBUTES.ID);
        }

        if (this.refreshId) {
            data.updateComponentId = this.refreshId;
        }

        if (this.additionalData) {
            jQuery.extend(data, this.additionalData);
        }

        var jsonViewState = getSerializedViewState();
        if (jsonViewState) {
            jQuery.extend(data, {clientViewState: jsonViewState});
        }

        // check if we still have a dialog to dismiss
        if (this.dismissDialogId) {
            var request = this;

            // to make sure we do an ajax submit when the hide event is triggered, not before
            jQuery("#" + this.dismissDialogId).one(kradVariables.EVENTS.HIDE_MODAL, function (event) {
                request._submitAjax(data);
            });

            this._dismissDialogIfNecessary(kradVariables.DIALOG_DISMISS_OPTIONS.REQUEST);
        } else {
            this._submitAjax(data);
        }
    },

    // handles the request as standard form submit
    _submitNonAjax: function () {
        // write out methodToCall as hidden
        writeHiddenToForm("methodToCall", this.methodToCall);

        // if additional data write out as hiddens
        for (key in this.additionalData) {
            writeHiddenToForm(key, this.additionalData[key]);
        }

        if (this.$action && this.$action.is("[" + kradVariables.ATTRIBUTES.ID + "]")) {
            writeHiddenToForm("triggerActionId", this.$action.attr(kradVariables.ATTRIBUTES.ID));
        }

        // start the loading indicator (will be removed on page load)
        if (!this.disableBlocking) {
            showLoading(this.loadingMessage);
        }

        var jsonViewState = getSerializedViewState();
        if (jsonViewState) {
            writeHiddenToForm("clientViewState", jsonViewState);
        }

        // check for file inputs and set encoding, this is handled for us with the ajax submits (using jqform)
        var fileInputs = jQuery('input[type=file]:enabled[value!=""]', '#kualiForm');

        var hasFileInputs = fileInputs.length > 0;
        if (hasFileInputs) {
            jQuery('#kualiForm').attr('enctype', 'multipart/form-data');
        }

        // submit
        jQuery('#kualiForm').submit();
    },

    // handles the request as an ajax request
    _submitAjax: function (data) {
        // create a reference to the request for ajax callbacks
        var request = this;

        var isAction = false;
        var actionParent;
        if (this.$action) {
            actionParent = jQuery(this.$action).parents("[data-omit_group='true']");
        }

        var updateParent;
        if (request.refreshId) {
            var updateComponent = jQuery('#' + request.refreshId);
            if (updateComponent.is("[data-omit_group='true']")) {
                // if the component being refreshed is a group that is omitted normally, explicitly send its fields
                updateParent = updateComponent;
            }
            else {
                // if the component being refreshed is part of a group that is normally omitted, send the group
                // data also
                updateParent = updateComponent.parents("[data-omit_group='true']");
            }

            // corner case: if the component isn't part of an omitted group but is itself omitted, still send its data
            // when being explicitly targeted by a refresh
            if (updateParent.length === 0 && updateComponent.is("[data-omit='true']")) {
                updateParent = updateComponent;
            }
        }

        // if the fields to send is originally blank
        var fieldToSendBlank = !request.fieldsToSend || request.fieldsToSend.length === 0;
        if (fieldToSendBlank) {
            request.fieldsToSend = [];
        }

        if (updateParent && updateParent.length && fieldToSendBlank) {
            var updateParentId = jQuery(updateParent[0]).attr("id");
            request.fieldsToSend.push('#' + updateParentId);
        }

        if (actionParent && actionParent.length && fieldToSendBlank) {
            var parentId = jQuery(actionParent[0]).attr("id");
            if (request.fieldsToSend.indexOf('#' + parentId) === -1) {
                request.fieldsToSend.push('#' + parentId);
            }
        }

        // if we aren't limiting the fields to send then send the whole form unless a field is explicitly set to omit
        if (!request.fieldsToSend || request.fieldsToSend.length === 0) {
            var submitOptions = {
                data: data,
                success: function (response) {
                    request._processSuccess(response, request);
                },

                error: function (jqXHR, textStatus) {
                    request._processError(jqXHR, textStatus, request);
                },
                beforeSubmit: function (arr, $form, options) {
                    var omitElements = jQuery("[data-role='InputField'][data-omit='true'] [data-role='Control'],[name='script']");

                    jQuery(omitElements).each(function (index, value) {
                        var name = jQuery(value).attr('name');

                        var dataOmit = jQuery(value).attr('data-omit');
                        if (dataOmit && name) {
                            request.fieldsToSend.push(name);
                        }

                        var dataIndex;
                        jQuery(arr).each(function (ind, val) {
                            if (val.name === name) {
                                dataIndex = ind;
                            }
                        });
                        if (dataIndex) {
                            arr.splice(dataIndex, 1);
                        }
                    });
                }
            };

            // if still no fields to send then go ahead and send
            if (!request.fieldsToSend || request.fieldsToSend.length === 0) {
                // Show visual loading/blocking indication
                this._setupBlocking(submitOptions);

                // Submit request
                jQuery("#" + kradVariables.KUALI_FORM).ajaxSubmit(submitOptions);
            }
        }

        if (request.fieldsToSend && request.fieldsToSend.length > 0) {
            // Serialize all the data we wish to send
            // The formInfo and formComplete data is data that is always necessary or added for the controller call
            var dataSerialized = jQuery.param(data, true);
            if (dataSerialized) {
                dataSerialized = dataSerialized + "&" + jQuery("#" + kradVariables.FORM_INFO_ID + " > input,"
                        + " #" + kradVariables.FORM_COMPLETE_ID + "> input").fieldSerialize();
            }
            else {
                dataSerialized = jQuery("#" + kradVariables.FORM_INFO_ID + " > input,"
                        + " #" + kradVariables.FORM_COMPLETE_ID + "> input").fieldSerialize();
            }

            jQuery(request.fieldsToSend).each(function (index, value) {
                // Stop iteration if NO_FIELDS_TO_SEND keyword detected
                if (value.toUpperCase() === kradVariables.NO_FIELDS_TO_SEND) {
                    return false;
                }

                // Check to see if name ends with a wildcard
                var wildcarded = value.indexOf("*", this.length - 1) !== -1;

                // If fields to send start with a # look inside that field or group for inputs serialize
                if (value.indexOf("#") === 0) {
                    dataSerialized = dataSerialized + "&" + jQuery(value).find("[name]").fieldSerialize();
                } else if (wildcarded) {
                    // Look for inputs which start with name specified before wildcard
                    dataSerialized = dataSerialized + "&"
                            + jQuery("[name^='" + value.substr(0, value.length - 1) + "']").fieldSerialize();
                } else {
                    dataSerialized = dataSerialized + "&" + jQuery("[name='" + value + "']").fieldSerialize();
                }
            });

            var submitOptions = {
                url: jQuery("#" + kradVariables.KUALI_FORM).attr("action"),
                type: "POST",
                data: dataSerialized,
                success: function (response) {
                    request._processSuccess(response, request);
                },
                error: function (jqXHR, textStatus) {
                    request._processError(jqXHR, textStatus, request);
                },
                beforeSubmit: function (arr, $form, options) {
                    var omitElements = jQuery("[name='script']");

                    jQuery(omitElements).each(function (index, value) {
                        var name = jQuery(value).attr('name');
                        var dataIndex;
                        jQuery(arr).each(function (ind, val) {
                            if (val.name === name) {
                                dataIndex = ind;
                            }
                        });
                        if (dataIndex) {
                            arr.splice(dataIndex, 1);
                        }
                    });
                }
            };

            // Show visual loading/blocking indication
            this._setupBlocking(submitOptions);

            // Submit request
            jQuery.ajax(submitOptions);
        }
    },

    /**
     * Process sucessful ajax submit by calling the appropriate response handler.
     *
     * @param response data received from the server for this call
     * @param request  original KRAD request object
     * @private
     */
    _processSuccess: function (response, request) {
        var responseContents = document.createElement('div');
        responseContents.innerHTML = response;

        // create a response object to process the response contents
        var kradResponse = new KradResponse(responseContents);
        kradResponse.processResponse();

        var hasError = checkForIncidentReport(response);
        if (!hasError) {
            if (request.successCallback) {
                if (typeof request.successCallback == "string") {
                    eval(request.successCallback);
                } else {
                    request.successCallback(responseContents);
                }
            }
        } else if (request.errorCallback) {
            if (typeof request.errorCallback == "string") {
                eval(request.errorCallback);
            } else {
                request.errorCallback(responseContents);
            }
        }

        clearHiddens();
    },

    /**
     * Process the error callback state of the ajax call.
     *
     * @param jqXHR the jqXHR object
     * @param textStatus the error text status
     * @param request the original KRAD request
     * @private
     */
    _processError: function (jqXHR, textStatus, request) {
        if (request.errorCallback) {
            if (typeof request.errorCallback == "string") {
                eval(request.errorCallback);
            } else {
                request.errorCallback();
            }
        }
        else {
            alert("Request failed: " + textStatus);
        }
    },

    // sets up the component or page blocking for an ajax request
    _setupBlocking: function (options) {

        // initialize element to block if necessary
        if (!this.elementToBlock && !this.disableBlocking &&
                (this.ajaxReturnType == kradVariables.RETURN_TYPE_UPDATE_COMPONENT) && this.refreshId) {
            this.elementToBlock = jQuery("#" + this.refreshId);
        }

        // force full page loading blocking for dialogs being loaded or refreshed
        if ((this.additionalData && this.additionalData.isDialog) ||
                jQuery(this.elementToBlock).is("#" + kradVariables.IDS.DIALOGS + " >, .modal")) {
            this.elementToBlock = null;
        }

        // create a reference to the request for ajax callbacks
        var request = this;

        // adding blocking configuration to ajax options
        var elementBlockingOptions = {
            beforeSend: function () {
                if (nonEmpty(request.elementToBlock) && (request.elementToBlock.is(":hidden, .uif-placeholder"))) {
                    var replaceElement = true;
                    request.elementToBlock.show();
                }

                if (!request.disableBlocking) {
                    showLoading(request.loadingMessage, request.elementToBlock, replaceElement);
                }
            },
            complete: function (jqXHR, textStatus) {
                // note that if you want to unblock simultaneous with showing the new retrieval
                // you must do so in the successCallback
                if (!request.disableBlocking) {
                    hideLoading(request.elementToBlock);
                }

                resetSessionTimers();
            },
            error: function () {
                if (nonEmpty(request.elementToBlock) && request.elementToBlock.hasClass("uif-placeholder")) {
                    request.elementToBlock.hide();
                }
                else if (!request.disableBlocking) {
                    hideLoading(request.elementToBlock);
                }
            },
            statusCode: {403: function (jqXHR, textStatus) {
                if (nonEmpty(request.elementToBlock) && request.elementToBlock.hasClass("uif-placeholder")) {
                    request.elementToBlock.hide();
                }
                else if (!request.disableBlocking) {
                    hideLoading(request.elementToBlock);
                }

                handleAjaxSessionTimeout(jqXHR.responseText);
            }}
        };

        jQuery.extend(options, elementBlockingOptions);
    },

    // checks whether a dialog needs to be dismissed at the given option point, returns true if
    // a dialog was dismissed, false if not
    _dismissDialogIfNecessary: function (dismissOption) {
        if (this.dismissDialogId && (this.dismissDialogOption === dismissOption)) {
            dismissDialog(this.dismissDialogId, this.$action);

            return true;
        }

        return false;
    }
}


/**
 * Processes a response that has been returned from an Ajax call
 *
 * @param contents - the response contents (or body)
 */
function KradResponse(contents) {
    this.responseContents = contents;
}
 
KradResponse.prototype = {
    // full response contents
    responseContents: null,
 
    // maps return types to handler function names
    handlerMapping: {"update-form": "updateFormHandler", "update-page": "updatePageHandler", "update-component": "updateComponentHandler",
        "update-view": "updateViewHandler", "redirect": "redirectHandler",
        "display-lightbox": "displayLightBoxHandler", "update-dialog":"updateDialogHandler"},
 
    // invoked to process the response contents by invoking necessary handlers
    processResponse: function () {
        var responseFn = this;
 
        // iterate over returned contents divs and invoke handler
        jQuery(this.responseContents).children().each(function () {
            var div = jQuery(this);
 
            // get the return type sent by the server
            var returnType = div.data("returntype");
 
            // find the handler function from the mapping
            var functionName = responseFn.handlerMapping[returnType];
            var handlerFunc = responseFn[functionName];
 
            // invoke the handler function
            if (handlerFunc) {
                handlerFunc(div, div.data());
            }
 
            hideEmptyCells();
        });
    },
 
    // finds the page content in the returned content and updates the page, then processes breadcrumbs and hidden
    // scripts. While processing, the page contents are hidden
    updatePageHandler: function (content, dataAttr) {
        var pageUpdate = jQuery("#page_update", content);
        var page = jQuery("[data-role='Page']", pageUpdate);
        var viewContent = jQuery("#" + kradVariables.VIEW_CONTENT_WRAPPER);
 
        // remove any already existing matching dialogs from the view
        jQuery('.modal', page).each(function() {
                    jQuery('#' + this.id, jQuery("[data-role='View']")).remove();
                }
        );
 
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
 
        markActiveMenuLink();
 
        viewContent.trigger(kradVariables.EVENTS.ADJUST_PAGE_MARGIN);
        $pageInLayout.trigger(kradVariables.EVENTS.UPDATE_CONTENT);
 
        $pageInLayout.show();
 
        $pageInLayout.trigger(kradVariables.EVENTS.ADJUST_STICKY);
        $pageInLayout.trigger(kradVariables.EVENTS.PAGE_UPDATE_COMPLETE);
 
        // Perform focus and jumpTo based on the data attributes
        performFocusAndJumpTo(true, page.data(kradVariables.FOCUS_ID), page.data(kradVariables.JUMP_TO_ID), page.data(kradVariables.JUMP_TO_NAME) );
    },
 
 
    // finds the dialog content in the returned content and updates the view
    updateDialogHandler: function (content, dataAttr) {
        var id = dataAttr.updatecomponentid;
        var component = jQuery("#" + id + "_update", content);
 
        // remove old stuff
        if (jQuery("#" + id + "_errors").length) {
            jQuery("#" + id + "_errors").remove();
        }
 
        jQuery("input[data-for='" + id + "']").each(function () {
            jQuery(this).remove();
        });
 
        // replace component
        var $dialog = jQuery("#" + id);
        if ($dialog.length) {
            $dialog.replaceWith(component.html());
        }
        else {
            jQuery('#' + kradVariables.IDS.DIALOGS).append(component.html());
        }
 
        $dialog.trigger(kradVariables.EVENTS.UPDATE_CONTENT);
 
        runHiddenScripts(id);
    },
 
 
    // retrieves the component with the matching id from the server and replaces a matching
    // _refreshWrapper marker span with the same id with the result.  In addition, if the result contains a label
    // and a displayWith marker span has a matching id, that span will be replaced with the label content
    // and removed from the component.  This allows for label and component content separation on fields
    updateComponentHandler: function (content, dataAttr) {
        var id = dataAttr.id;
 
        var $componentInDom = jQuery("#" + id);
 
        hideTooltips($componentInDom);
 
        var component = jQuery("#" + id + "_update", content);
 
        // remove any already existing matching dialogs from the view
        jQuery('.modal', component).each(function () {
                    var tmp = jQuery('#' + this.id, jQuery("[data-role='View']"));
                    var isPlaceHolder = tmp.hasClass(kradVariables.CLASSES.PLACEHOLDER);
                    var displayedModal = isDisplayedModal($componentInDom);
 
                    // do not remove placeholders or displayedModals
                    if (!isPlaceHolder && !displayedModal) {
                        tmp.remove();
                    }
                }
        );
 
        // is the new component now required
        var nowRequired = jQuery(".required", component).size() > 0;
 
        // get the old label
        var oldLabel = jQuery("#" + id + "_label");
 
        // if found then remove any required indicators and then add/readd back
        if(oldLabel) {
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
            performFocusAndJumpTo(true, $componentInDom.data(kradVariables.FOCUS_ID), $componentInDom.data(kradVariables.JUMP_TO_ID), $componentInDom.data(kradVariables.JUMP_TO_NAME) );
        }
    },
 
    // performs a redirect to the URL found in the returned contents
    redirectHandler: function (content, dataAttr) {
        // get url contents between div
        var redirectUrl = jQuery(content).text().trim();
 
        // don't check dirty state on a simple refresh (old url starts with the new one's url text)
        if (redirectUrl.indexOf("performDirtyCheck=false") > -1) {
            dirtyFormState.skipDirtyChecks = true;
        }
 
        // redirect
        window.location.href = redirectUrl;
    },
 
    // replaces the view with the given content and run the hidden scripts
    updateViewHandler: function (content, dataAttr) {
        var app = jQuery("#" + kradVariables.APP_ID);
        app.hide();
 
        var update = jQuery(content);
 
        var appHeaderUpdate = update.find("#" + kradVariables.APPLICATION_HEADER_WRAPPER);
        app.find("#" + kradVariables.APPLICATION_HEADER_WRAPPER).replaceWith(appHeaderUpdate);
 
        var kualiForm = app.find("#kualiForm");
        var kualiFormReplacement = update.find("#kualiForm");
        var view = app.find("[data-role='View']");
        var viewUpdate = update.find("[data-role='View']");
 
        if(kualiForm.length && kualiFormReplacement) {
            kualiForm.replaceWith(kualiFormReplacement);
        }
        else if (kualiForm.length && !kualiFormReplacement.length){
            kualiForm.replaceWith(viewUpdate);
        }
        else if (!kualiForm.length && kualiFormReplacement.length) {
            view.replaceWith(kualiFormReplacement);
        }
        else {
            view.replaceWith(viewUpdate);
        }
 
        // Removes traces of dialog if one was destroyed by the refresh
        ensureDialogBackdropRemoved();
 
        var appFooterUpdate = update.find("#" + kradVariables.APPLICATION_FOOTER_WRAPPER);
        app.find("#" + kradVariables.APPLICATION_FOOTER_WRAPPER).replaceWith(appFooterUpdate);
 
        app.show();
        setupStickyHeaderAndFooter();
        runHiddenScripts(kradVariables.APP_ID);
 
        view.trigger(kradVariables.EVENTS.UPDATE_CONTENT);
    },
 
    // displays the response contents in a lightbox
    displayLightBoxHandler: function (content, dataAttr) {
        showLightboxContent(content);
    },
 
    // replaces the form action with the given content
    updateFormHandler: function (content, dataAttr) {
        var action = jQuery(content).html();
 
        jQuery("form#" + kradVariables.KUALI_FORM).attr('action', jQuery.trim(action));
    }
}
