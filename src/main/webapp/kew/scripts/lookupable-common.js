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
// Lookup.jsp

function clearValues(methodToCall, formname, action) {
  document.forms[formname].methodToCall.value = methodToCall;
  return post_to_action(formname, action);
}

function quick_finder(quickFinderLookupable, formname, action) {
  document.forms[formname].methodToCall.value = 'performLookup';
  document.forms[formname].quickFinderLookupable.value = quickFinderLookupable;
  return post_to_action(formname, action);
}

function quick_finder_with_conversions(quickFinderLookupable, formname, action, customFieldConversions) {
  document.forms[formname].methodToCall.value = 'performLookup';
  document.forms[formname].quickFinderLookupable.value = quickFinderLookupable;
  document.forms[formname].customFieldConversions.value = customFieldConversions;
  return post_to_action(formname, action);
}
