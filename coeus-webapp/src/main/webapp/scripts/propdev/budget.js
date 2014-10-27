var Kc = Kc || {};
Kc.PropDev = Kc.PropDev || {};
Kc.PropDev.Budget = Kc.PropDev.Budget || {};
(function(namespace, $) {
	namespace.copyBudget = function(budgetId, e) {
		$(e.currentTarget).find("input[name$='originalBudgetId']").val(budgetId);
	};
    namespace.totalUnallocatedFandA = function (values){
        return namespace.calculateTotalFromValues(values, $('#PropBudget-UnrecoveredFandAPage-Group').data('total_unrecovered'));
    };
    namespace.totalUnallocatedCostSharing = function (values){
        return namespace.calculateTotalFromValues(values, $('#PropBudget-CostSharingPage-CollectionGroup').data('total_costsharing'));
    };
    namespace.calculateTotalFromValues = function (values, total) {
        var calcTotal = total * 100;
        for (var i = 0; i < values.length; i++) {
            calcTotal -= (values[i] * 100);
        }
        return (calcTotal / 100).toFixed(2);
    };
    namespace.closeDialogWithoutError = function(dialogId) {
    	if ($("#" + dialogId).find(".uif-hasError").length == 0) {
    		dismissDialog(dialogId);
    		$("body").removeClass("modal-open");
    		$(".modal-backdrop").remove();
    	}
    };
	namespace.formatMoney = function(num, c, d, t){
		var n = num, c = isNaN(c = Math.abs(c)) ? 2 : c, d = d == undefined ? "," : d, t = t == undefined ? "." : t, s = n < 0 ? "-" : "", i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "", j = (j = i.length) > 3 ? j % 3 : 0;
		   return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
	};
	namespace.calculateTotal = function(inputField) {
		var total = 0;
		$(inputField).parents('tr').first().find('.calculateTotal input.form-control').each(function() {
			var fieldValue = $(this).val().replace(/[$,]/g, '');
			if (fieldValue == '') fieldValue = 0;
			total = total + parseFloat(fieldValue);
		});
		if (!isNaN(total)) {
			var formattedTotal = this.formatMoney(new Number(total), 2, '.', ',');
			var amountSpan = $(inputField).parents('tr').first().find('.totalCost');
			if (amountSpan.children('input').length > 0) {
				amountSpan.children('input').first().val(formattedTotal);
			} else {
				amountSpan.html(formattedTotal);
			}
		}
	};
})(Kc.PropDev.Budget, jQuery);

function totalUnallocatedCostSharing (values){
    return Kc.PropDev.Budget.totalUnallocatedCostSharing(values);
}

function totalUnallocatedFandA (values) {
    return Kc.PropDev.Budget.totalUnallocatedFandA(values);
}