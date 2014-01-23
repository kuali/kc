var ProposalDevelopmentMethods = function() { };
ProposalDevelopmentMethods.unselectCollectionRadioButtons = function(selectedRadio, otherRadioSelector) {
	jQuery(selectedRadio).parents('table:first').find(otherRadioSelector).each(function() {
		var inputField = jQuery(this).parents('div.uif-inputField:first');
		if (inputField[0] !== selectedRadio) {
			jQuery(this).prop('checked', false);
		}
	});
}
ProposalDevelopmentMethods.updateSponsorName = function(sponsorCode, nameSelector) {
	jQuery.getJSON(window.location.pathname, 
			{'sponsorCode': sponsorCode, 'methodToCall': 'getSponsor'}, 
			function(json) {
				var sponsorName = null;
				if (json !== null) {
					sponsorName = json['sponsorName'];
				}
				jQuery(nameSelector).html(sponsorName);
			});
}
ProposalDevelopmentMethods.sponsorSuggestSelect = function(event, ui) {
	jQuery(event.target).val(ui.item.value);
	jQuery(event.target).parent().find('span.informationalText').html(ui.item.sponsorName);
}