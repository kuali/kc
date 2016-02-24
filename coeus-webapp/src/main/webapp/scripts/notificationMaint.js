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
$j = jQuery.noConflict();

function populateQualifier(roleSelect, qualifierSelect) {
	var role = jq(roleSelect).attr('value');
	callAjaxByPath('../jqueryAjax.do', 'getNotificationRoleSubQualifiers', role,
			function(data) {
				qualifiers = eval('(' + jq(data).find('#ret_value').html() + ')');
				jq(qualifierSelect).html('');
				if (qualifiers.length == 0) {
					jq(qualifierSelect).attr('disabled', 'disabled');
				} else {
					var options = '';
					for (var i = 0; i < qualifiers.length; i++) {
						var item = qualifiers[i];
						options += "<option value='" + item.key + "'>" + item.value + "</option>";
					}
					jq(qualifierSelect).html(options);
					jq(qualifierSelect).removeAttr('disabled');
				}
			},
			function(error) {
				alert(error);
			}
	);
}

jq(document).ready(function() {
	jq('select[name*="roleName"]').each(function() {
		populateQualifier(this, 
				jq(this).parent().parent().next().find('select[name*="roleSubQualifier"]').first());
	});
	jq('select[name*="roleName"]').change(function() {
		populateQualifier(this, 
				jq(this).parent().parent().next().find('select[name*="roleSubQualifier"]').first());
	});
});
