var Kc = Kc || {};
Kc.PropDev = Kc.PropDev || {};
Kc.PropDev.Budget = Kc.PropDev.Budget || {};
(function(namespace, $) {
	namespace.copyBudget = function(budgetId, e) {
		$(e.currentTarget).find("input[name$='originalBudgetId']").val(budgetId);
	};
})(Kc.PropDev.Budget, jQuery);