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
    namespace.selectAllIncludedForms = function(document) {
    	var count = 0;
        var include = true;
        $.each(document, function(index, element) {
            if(element.type == 'checkbox') {
    			if (element.name == 'document.developmentProposal.s2sOpportunity.s2sOppForms['+count+'].include') {
    				include = element.checked;
                }
                if (element.name == 'document.developmentProposal.s2sOpportunity.s2sOppForms['+count+'].selectToPrint') {
                    if(element.disabled == true) {
                    	element.checked = false;
                    } else if(include == false) {
                    	element.checked = false;
                    } else {
                    	element.checked = true;
                    }
                    include = true;
                    count++;
                }
            }
    	});
    };
})(Kc.PropDev.s2s, jQuery);
