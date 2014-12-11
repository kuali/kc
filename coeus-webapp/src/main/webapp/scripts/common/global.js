var Kc = Kc || {};
Kc.Global = Kc.Global || {};
(function (namespace, $) {
    // set all modals to static behavior (clicking out does not close)
    $.fn.modal.Constructor.DEFAULTS.backdrop = "static";

    $(document).on("ready", function(){
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

                var dateObj = new Date(date);
                if (isNaN(dateObj.valueOf())) {
                    // not valid abandon conversion
                    return;
                }

                dateObj.setFullYear(year);

                var formattedDate = $.datepicker.formatDate(dateFormat, dateObj);
                $(this).val(formattedDate);
            }
        });
    });
})(Kc.Global, jQuery);

// Kc krad overrides (request changes from latest rice, response kc fixes):

KradRequest.prototype._submitAjax = function (data) {
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
        jQuery("#" + kradVariables.KUALI_FORM).ajaxSubmit(submitOptions);

    }
    else {
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

        var hasFileInputs = false;
        var inputElements = [];

        jQuery(request.fieldsToSend).each(function (index, value) {
            // Stop iteration if NO_FIELDS_TO_SEND keyword detected
            if (value.toUpperCase() === kradVariables.NO_FIELDS_TO_SEND) {
                return false;
            }

            // Check to see if name ends with a wildcard
            var wildcarded = value.indexOf("*", this.length - 1) !== -1;

            // If fields to send start with a # look inside that field or group for inputs serialize
            var $fields = [];
            if (value.indexOf("#") === 0) {
                $fields = jQuery(value).find("[name]:input");
                dataSerialized = dataSerialized + "&" + $fields.not(":disabled,:file").fieldSerialize();
            } else if (wildcarded) {
                $fields = jQuery("[name^='" + value.substr(0, value.length - 1) + "']:input");
                // Look for inputs which start with name specified before wildcard
                dataSerialized = dataSerialized + "&"
                    + $fields.not(":disabled,:file").fieldSerialize();
            } else {
                $fields = jQuery("[name='" + value + "']:input");
                dataSerialized = dataSerialized + "&" + $fields.not(":disabled,:file").fieldSerialize();
            }

            inputElements = inputElements.concat($fields.filter(":file").get());

            if ($fields.length && $fields.is(":file")) {
                hasFileInputs = true;
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

        // Submit code below mostly borrowed from  jquery.form plugin but altered for usage here
        var $form = jQuery("#" + kradVariables.KUALI_FORM);
        var mp = 'multipart/form-data';
        var multipart = ($form.attr('enctype') == mp || $form.attr('encoding') == mp);

        // Check for features
        var feature = {};
        feature.fileapi = jQuery("<input type='file'/>").get(0).files !== undefined;
        feature.formdata = window.FormData !== undefined;

        var fileAPI = feature.fileapi && feature.formdata;
        var unsupportedBrowser = (hasFileInputs || multipart) && !fileAPI;
        var jqxhr;

        if (unsupportedBrowser) {
            // This is a Non-fileAPI browser (ie non-supported browser), abandon fieldsToSend logic
            // and send everything
            submitOptions.data = data;
            jQuery("#" + kradVariables.KUALI_FORM).ajaxSubmit(submitOptions);
        }
        else if ((hasFileInputs || multipart) && fileAPI) {
            var inputData = this._createInputData(inputElements, feature);
            jqxhr = this._fileUploadXhr(inputData, submitOptions);
        }
        else {
            jqxhr = jQuery.ajax(submitOptions);
        }

        $form.removeData('jqxhr').data('jqxhr', jqxhr);

    }

}

// Code mostly borrowed from  jquery.form plugin but altered for use here
KradRequest.prototype._fileUploadXhr = function (a, options) {
    var formdata = new FormData();
    var part;
    var serializedData = options.data;
    var serialized = serializedData.split('&');

    for (i = 0; i < serialized.length; i++) {
        // undo param space replacement
        serialized[i] = serialized[i].replace(/\+/g, ' ');
        part = serialized[i].split('=');
        formdata.append(decodeURIComponent(part[0]), decodeURIComponent(part[1]));
    }

    for (var i = 0; i < a.length; i++) {
        formdata.append(a[i].name, a[i].value);
    }

    options.data = null;

    var s = jQuery.extend(true, {}, jQuery.ajaxSettings, options, {
        contentType: false,
        processData: false,
        cache: false,
        type: 'POST'
    });

    if (options.uploadProgress) {
        // workaround because jqXHR does not expose upload property
        s.xhr = function () {
            var xhr = jQuery.ajaxSettings.xhr();
            if (xhr.upload) {
                xhr.upload.addEventListener('progress', function (event) {
                    var percent = 0;
                    var position = event.loaded || event.position;
                    /*event.position is deprecated*/
                    var total = event.total;
                    if (event.lengthComputable) {
                        percent = Math.ceil(position / total * 100);
                    }
                    options.uploadProgress(event, position, total, percent);
                }, false);
            }
            return xhr;
        };
    }

    s.data = null;
    var beforeSend = s.beforeSend;
    s.beforeSend = function (xhr, o) {
        o.data = formdata;
        if (beforeSend)
            beforeSend.call(this, xhr, o);
    };
    return jQuery.ajax(s);
}

KradRequest.prototype._createInputData = function (els, feature) {
    var a = [];

    var i, j, n, v, el, max, jmax;
    for (i = 0, max = els.length; i < max; i++) {
        el = els[i];
        n = el.name;
        if (!n || el.disabled) {
            continue;
        }

        v = jQuery.fieldValue(el, true);
        if (v && v.constructor == Array) {
            for (j = 0, jmax = v.length; j < jmax; j++) {
                a.push({name: n, value: v[j]});
            }
        }
        else if (feature.fileapi && el.type == 'file') {
            var files = el.files;
            if (files.length) {
                for (j = 0; j < files.length; j++) {
                    a.push({name: n, value: files[j], type: el.type});
                }
            }
            else {
                a.push({ name: n, value: '', type: el.type });
            }
        }
        else if (v !== null && typeof v != 'undefined') {
            a.push({name: n, value: v, type: el.type, required: el.required});
        }
    }

    return a;
}

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

    markActiveMenuLink();

    viewContent.trigger(kradVariables.EVENTS.ADJUST_PAGE_MARGIN);
    $pageInLayout.trigger(kradVariables.EVENTS.UPDATE_CONTENT);

    $pageInLayout.show();

    $pageInLayout.trigger(kradVariables.EVENTS.ADJUST_STICKY);
    $pageInLayout.trigger(kradVariables.EVENTS.PAGE_UPDATE_COMPLETE);

    // Perform focus and jumpTo based on the data attributes
    performFocusAndJumpTo(true, page.data(kradVariables.FOCUS_ID), page.data(kradVariables.JUMP_TO_ID), page.data(kradVariables.JUMP_TO_NAME));
}