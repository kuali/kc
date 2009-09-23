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
function post_to_action(action, methodToCall) {
  document.forms[0].action = action;
  document.forms[0].methodToCall.value = methodToCall;
  document.forms[0].submit();
  return;
}

function takeRuleAction(action, methodToCall, ruleId) {
  document.forms[0].currentRuleId.value = ruleId;
  return post_to_action(action, methodToCall);
}

function takeAction(action, methodToCall, ruleIndex, respIndex, delRuleIndex, delRespIndex, extraId) {
  if (ruleIndex != null) {
  	document.forms[0].ruleIndex.value = ruleIndex;
  }
  if (respIndex != null) {
  	document.forms[0].responsibilityIndex.value = respIndex;
  }
  if (delRuleIndex != null) {
    document.forms[0].delegationIndex.value = delRuleIndex;
  }
  if (delRespIndex != null) {
    document.forms[0].delegationResponsibilityIndex.value = delRespIndex;
  }
  if (extraId != null) {
  	document.forms[0].extraId.value = extraId;
  }
  return post_to_action(action, methodToCall);
}
function takeIndexedAction(action, methodToCall, indexPath) {
  var indexes = new Array(4);
  var start = 0;
  for (var count = 0; count < 4; count++) {
    var currentPath = indexPath.substring(start, indexPath.length);
	var leftIndex = currentPath.indexOf("[");
	var rightIndex = currentPath.indexOf("]");
	if (leftIndex < 0 || rightIndex < 0) break;
	var indexValue = currentPath.substring(leftIndex+1, rightIndex);
	indexes[count] = indexValue;
	start += rightIndex+1;
  }
  return takeAction(action, methodToCall, indexes[0], indexes[1], indexes[2], indexes[3]);
}
  
function lookup(quickFinderLookupable, conversionFields, action) {
  document.forms[0].lookupableImplServiceName.value = quickFinderLookupable;
  document.forms[0].conversionFields.value = conversionFields;
  return post_to_action(action, 'performLookup');
}

function responsibilityType(ruleIndex, respIndex, delRuleIndex, delRespIndex) {
	var baseId = "rule"+ruleIndex+"resp"+respIndex;
	if (delRuleIndex != null && delRespIndex != null) {
		baseId += "delRule"+delRuleIndex+"delResp"+delRespIndex;
	}
    var personRef = getElement(baseId+"F");
    var workgroupRef = getElement(baseId+"W");
    var roleRef = getElement(baseId+"R");    
    var reviewerRef = getElement(baseId+"REV");
    var personLookupRef = getElement(baseId+"PL");
    var workgroupLookupRef = getElement(baseId+"WL");
    var roleAreaRef = getElement(baseId+"RA");
    //var approvePolicyRowRef = getElement(baseId+"AP");

    var reviewerRefStyle = "none";    
    var personLookupRefStyle = "none";
    var workgroupLookupRefStyle = "none";
    var roleAreaRefStyle = "none";
    //var approvePolicyRowRefStyle = "none"; 
    if (personRef.checked) {
    	reviewerRefStyle = "inline";
    	personLookupRefStyle = "inline";
    } else if (workgroupRef.checked) {
    	reviewerRefStyle = "inline";
    	workgroupLookupRefStyle = "inline";
    } else if (roleRef.checked) {
    	roleAreaRefStyle = "inline";
    	//approvePolicyRowRefStyle = "";
    }
    
    reviewerRef.style.display = reviewerRefStyle;
    personLookupRef.style.display = personLookupRefStyle;
    workgroupLookupRef.style.display = workgroupLookupRefStyle;
    roleAreaRef.style.display = roleAreaRefStyle;
	//approvePolicyRowRef.style.display = approvePolicyRowRefStyle;
}
