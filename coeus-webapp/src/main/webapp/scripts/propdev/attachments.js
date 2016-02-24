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
Kc.PropDev.Attachments = Kc.PropDev.Attachments || {};
(function(namespace, $) {
    $(document).on("paste keypress", ".abstractDetails", function(e){
        if (!e.ctrlKey && !e.metaKey) {
            namespace.removeWarning(this);
            namespace.addWarning(this);
        }

    });
    namespace.addWarning = function(element) {
        setTimeout(function () {
            var value = $(element).val().replace(/(\r\n|\n|\r)/g, '--');
            if (value.length >= 49000) {
                $(element).closest("div").prepend("<span class='alert-warning'>Data entry for this field is limited to 49,000 characters (with spaces).</span>");
                $(element).closest("div").addClass("has-warning")
            }
        },10);
    }
    namespace.removeWarning = function(element) {
        $(element).closest("div").find("span.alert-warning").remove();
        $(element).closest("div").removeClass("has-warning")
    }
    namespace.initAttachmentCounts = function(){
        $("#PropDev-AttachmentsPage").ajaxSuccess(function() {
            var proposalAttachmentCount = $("#PropDev-AttachmentsPage-ProposalCollection-Collection").find("tbody").find("tr").size();
            var personnelAttachmentCount = $("#PropDev-AttachmentsPage-PersonnelCollection-Collection").find("tbody").find("tr").size();
            var abstractCount = $(".proposalAbstractCollection").find("tbody").find("tr").size();
            var internalAttachmentCount = $("#PropDev-AttachmentsPage-InternalCollection-Collection").find("tbody").find("tr").size();
            var noteCount = $(".proposalNoteCollection").find("tbody").find("tr").size();

            $(".proposalAttachmentCount").html(proposalAttachmentCount);
            $(".personnelAttachmentCount").html(personnelAttachmentCount);
            $(".abstractCount").html(abstractCount);
            $(".internalAttachmentCount").html(internalAttachmentCount);
            $(".noteCount").html(noteCount);

        });
    };
    namespace.checkforExistingAttachmentsOnParent = function(element) {
        var select = $(element).find("select");
        var previousValue = $(select).data("previousValue");
        if (typeof previousValue  === "undefined") {
            previousValue = "";
        }
        var currentValue = $(select).val();
        var propertyPath = $(select).attr("name");
        ajaxSubmitForm('checkForExistingNarratives',{currentValue:currentValue,previousValue:previousValue,propertyPath:propertyPath});
    }
    namespace.capturePreviousNarrativeType = function(element) {
        var select = $(element).find("select");
        $(select).data("previousValue",$(select).val());
    }
})(Kc.PropDev.Attachments, jQuery);
