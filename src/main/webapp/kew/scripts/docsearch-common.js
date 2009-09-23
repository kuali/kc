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
// DocumentSearch.jsp
function post_to_action(action, methodToCall) {
  document.forms[0].action = action;
  document.forms[0].methodToCall.value = methodToCall;
  document.forms[0].submit();
  return;
}

function lookup(quickFinderLookupable, action) {
  document.forms[0].lookupableImplServiceName.value = quickFinderLookupable;
  return post_to_action(action, 'performLookup');
}
