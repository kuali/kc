/*
 * Copyright 2007-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// RemoveReplaceEntry.jsp

function selectAllRuleCheckboxes(num) {
	var masterCheckbox = document.getElementById('masterRuleCheckbox');
	if (isCheckbox(masterCheckbox)) {
		for (var i = 0; i < num; i++) {
			var checkbox = document.getElementById('rules[' + i + '].selected');
			if(isCheckbox(checkbox)) {
				checkbox.checked = masterCheckbox.checked;
			}
		}
	}
}

function selectAllWorkgroupCheckboxes(num) {
	var masterCheckbox = document.getElementById('masterWorkgroupCheckbox');
	if (isCheckbox(masterCheckbox)) {
		for (var i = 0; i < num; i++) {
			var checkbox = document.getElementById('workgroups[' + i + '].selected');
			if(isCheckbox(checkbox)) {
				checkbox.checked = masterCheckbox.checked;
			}
		}
	}
}

function isCheckbox(element) {
	return element && element.type && element.type == 'checkbox';
}

function lookup(quickFinderLookupable, conversionFields, action) {
  document.forms[0].lookupableImplServiceName.value = quickFinderLookupable;
  document.forms[0].conversionFields.value = conversionFields;
  return post_to_action(action, 'performLookup');
}
