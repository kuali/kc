var Kc = Kc || {};
Kc.PropDev.s2s = Kc.PropDev.s2s || {};
(function (namespace, $) {

    namespace.revisionToggle = function(object) {
        if ($(object).find('select').find(":selected").val() == "E") {
            $("input[name='document.developmentProposal.s2sOpportunity.revisionOtherDescription']").parent("div").show();
        } else {
            $("input[name='document.developmentProposal.s2sOpportunity.revisionOtherDescription']").parent("div").hide();
            $("input[name='document.developmentProposal.s2sOpportunity.revisionOtherDescription']").val("");
        }
    };
})(Kc.PropDev.s2s, jQuery);
