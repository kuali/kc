var Kc = Kc || {};
Kc.PropDev.Attachments = Kc.PropDev.Attachments || {};
(function(namespace, $) {
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
})(Kc.PropDev.Attachments, jQuery);