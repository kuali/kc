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
// DelagateRule2.jsp, Rule.jsp, Rule2.jsp

function post_to_action(action, methodToCall) {
  document.forms[0].action = action;
  document.forms[0].methodToCall.value = methodToCall;
  document.forms[0].submit();
  return;
}
function setEdit(action, methodToCall, cnt) {
  if (cnt != null) {
    document.forms[0].editResponsibility.value = cnt;
  }
  return post_to_action(action, methodToCall);
}
function takeAction(action, methodToCall, ruleIndex, respIndex, delRuleIndex, delRespIndex) {
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

function setEditRule(action, methodToCall, cnt) {
  if (cnt != null) {
    document.forms[0].editRule.value = cnt;
  }
  return post_to_action(action, methodToCall);
}

function edit(action, methodToCall, cnt) {
  if (cnt != null) {
    document.forms[0].editIndex.value = cnt;
  }
  return post_to_action(action, methodToCall);
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

    var reviewerRefStyle = "none";
    var personLookupRefStyle = "none";
    var workgroupLookupRefStyle = "none";
    var roleAreaRefStyle = "none";
    if (personRef.checked) {
    	reviewerRefStyle = "inline";
    	personLookupRefStyle = "inline";
    } else if (workgroupRef.checked) {
    	reviewerRefStyle = "inline";
    	workgroupLookupRefStyle = "inline";
    } else if (roleRef.checked) {
    	roleAreaRefStyle = "inline";
    }

    reviewerRef.style.display = reviewerRefStyle;
    personLookupRef.style.display = personLookupRefStyle;
    workgroupLookupRef.style.display = workgroupLookupRefStyle;
    roleAreaRef.style.display = roleAreaRefStyle;
}

function getElement(elementId) {
	if (document.getElementById) {
		return document.getElementById(elementId);
	} else {
		return document.all[elementId];
	}
	return null;
}

function personWorkgroup() {
	if(document.forms[0].personWorkGroup[0].checked){
		if (document.getElementById) {
	        document.getElementById("person").style.display = "inline";
	        document.getElementById("reviewer").style.display = "inline";
	        document.getElementById("workgroup").style.display = "none";
	        document.getElementById("role").style.display = "none";
		} else {
	        document.all["person"].style.display = "inline";
	        document.all["reviewer"].style.display = "inline";
	        document.all["workgroup"].style.display = "none";
	        document.all["role"].style.display = "none";
		}
	} else if(document.forms[0].personWorkGroup[1].checked){
		if (document.getElementById) {
	        document.getElementById("person").style.display = "none";
	        document.getElementById("reviewer").style.display = "inline";
	        document.getElementById("workgroup").style.display = "inline";
	        document.getElementById("role").style.display = "none";
		} else {
	        document.all["person"].style.display = "none";
	        document.all["workgroup"].style.display = "inline";
	        document.all["reviewer"].style.display = "inline";
			document.all["role"].style.display = "none";
		}
	} else if (document.forms[0].personWorkGroup[2].checked){
		if (document.getElementById) {
	        document.getElementById("person").style.display = "none";
	        document.getElementById("workgroup").style.display = "none";
	        document.getElementById("reviewer").style.display = "none";
	        document.getElementById("role").style.display = "block";
		} else {
	        document.all["person"].style.display = "none";
	        document.all["workgroup"].style.display = "none";
			document.all["reviewer"].style.display = "none";
			document.all["role"].style.display = "block";
		}
	}
}

// RoutingReportCriteria.jsp

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

// Rule.jsp

function setRemove(action, methodToCall, cnt) {
  if (cnt != null) {
    document.forms[0].removeResponsibility.value = cnt;
  }
  return post_to_action(action, methodToCall);
}
function setRemoveRule(action, methodToCall, cnt) {
  if (cnt != null) {
    document.forms[0].removeRule.value = cnt;
  }
  return post_to_action(action, methodToCall);
}

// RuleGlobal.jsp

// different from above person workgroup...
function RuleGlobal_jsp_personWorkgroup() {
	if(document.forms[0].personWorkGroup[0].checked){
		if (document.getElementById) {
	        document.getElementById("person").style.display = "inline";
	        document.getElementById("reviewer").style.display = "inline";
	        document.getElementById("workgroup").style.display = "none";
		} else {
	        document.all["person"].style.display = "inline";
	        document.all["reviewer"].style.display = "inline";
	        document.all["workgroup"].style.display = "none";
		}
	} else if(document.forms[0].personWorkGroup[1].checked){
		if (document.getElementById) {
	        document.getElementById("person").style.display = "none";
	        document.getElementById("reviewer").style.display = "inline";
	        document.getElementById("workgroup").style.display = "inline";
		} else {
	        document.all["person"].style.display = "none";
	        document.all["workgroup"].style.display = "inline";
	        document.all["reviewer"].style.display = "inline";
		}
	}
}
