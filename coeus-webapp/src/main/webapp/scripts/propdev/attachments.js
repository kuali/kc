var Kc = Kc || {};
Kc.PropDev.Attachments = Kc.PropDev.Attachments || {};
(function(namespace, $) {
    namespace.editAttachment = function(){
    };
    namespace.cancelAttachment = function(){
        $(this).closest("div[data-role='disclosureContent']").toggle();
        $("span[id$='toggle_col']").toggle();
        $("span[id$='toggle_exp']").toggle();
    };
})(Kc.PropDev.Attachments, jQuery);
