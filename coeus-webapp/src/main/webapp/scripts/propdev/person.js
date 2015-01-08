var Kc = Kc || {};
Kc.PropDev.Personnel = Kc.PropDev.Personnel || {};
(function(namespace, $) {
	$(document).on("ready", function() {
		$(document).on("shown.bs.tab", "#PropDev-PersonnelPage-Collection [data-type='Uif-TabGroup']", function(e) {
			var selectedTab = e.target;
			var index = $(selectedTab).parent().index();
			var tabContent = $(selectedTab).parents("[data-type='Uif-TabGroup']").find(".tab-content > :eq("+index+")");
			var placeHolder = tabContent.find("> span.uif-placeholder");
			if (placeHolder.length) {
				var id = placeHolder.attr("id");
				retrieveComponent(id);
			}
		});
	})
	namespace.unselectCollectionRadioButtons = function(selectedRadio, otherRadioSelector) {
		$(selectedRadio).parents('table:first').find(otherRadioSelector).each(function() {
			var inputField = $(this).parents('div:first');
			if (inputField[0] !== selectedRadio) {
				$(this).prop('checked', false);
			}
		});
	};
	namespace.verifyCollectionRadioButtons = function(parentId, radioSelector) {
		var selected = 0;
		selected = $('#' + parentId + " " + radioSelector + ":checked").length;
		if (selected != 1) {
			return false;
		} else {
			return true;
		}
	};
	namespace.validateWizard = function(wizardId) {
		messageSummariesShown = true;

		// Doing this custom because this is not functionality built in
		$('#' + wizardId).removeAttr('data-parent');

		var valid = $('#kualiLightboxForm').valid();
		messageSummariesShown = false;
		return valid;
	};
    namespace.selectFirstRadio = function(id) {
        $(id).find('input').not("input[disabled='disabled']").first().prop('checked', true);
    };
})(Kc.PropDev.Personnel, jQuery);
