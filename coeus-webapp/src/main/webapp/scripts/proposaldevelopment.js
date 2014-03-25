var Kc = Kc || {};
Kc.PropDev = Kc.PropDev || {};
Kc.PropDev.updateSponsorName = function(sponsorCode, nameSelector) {
	jQuery.getJSON(window.location.pathname, 
			{'sponsorCode': sponsorCode, 'methodToCall': 'getSponsor'}, 
			function(json) {
				var sponsorName = null;
				if (json !== null) {
					sponsorName = json['sponsorName'];
				}
				jQuery(nameSelector).html(sponsorName);
			});
};
Kc.PropDev.sponsorSuggestSelect = function(event, ui) {
	jQuery(event.target).val(ui.item.value);
	jQuery(event.target).parent().find('.informationalText').html(ui.item.sponsorName);
};
Kc.PropDev.Personnel = Kc.PropDev.Personnel || {};
(function(namespace, $) {
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
})(Kc.PropDev.Personnel, jQuery);
