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
// BranchStates.jsp
function changeBranchValue(button,oIndex,iIndex){

   var rad_val;

   for (var i=0; i < (document.getElementsByName("branchOp["+oIndex+"].value")).length; i++)
   {
    var radioButton=(document.getElementsByName("branchOp["+oIndex+"].value"))[i];
    if (radioButton.checked)
         {
         rad_val = radioButton.value;
         break;
        }
   }

   if(rad_val!=null && rad_val.indexOf("update")>-1){
      var value1=button.value;
      var index1=value1.indexOf("don't");

      var stateIds=document.forms[0].branchStatesDelete.value;
      var keyText=(document.getElementsByName("branche["+oIndex+"].docBranchState["+iIndex+"].key"))[0];
      var valText=(document.getElementsByName("branche["+oIndex+"].docBranchState["+iIndex+"].value"))[0];
      if(index1 > -1){
          button.value=value1.substr(index1+6);
          var value2=value1.substr(index1+13);
          var stateIds=stateIds.replace(value2," ");
          document.forms[0].branchStatesDelete.value=stateIds;
          keyText.disabled=false;
          valText.disabled=false;
      }else{
    	  button.value="don't "+value1;
    	  var value2=value1.substr(7);
    	  stateIds=stateIds+" "+value2;
    	  document.forms[0].branchStatesDelete.value=stateIds;
    	  keyText.disabled=true;
          valText.disabled=true;
      }
    }else{
       alert("please select 'update' radio button first before deleting a certain branch state!");
    }
 }

// DocumentOperation.jsp
function configureLookup(lookupableName, invocationModule, invocationField, invocationIndex){
  document.forms[0].lookupableImplServiceName.value = lookupableName;
  document.forms[0].lookupInvocationModule.value = invocationModule;
  document.forms[0].lookupInvocationField.value = invocationField;
  document.forms[0].lookupInvocationIndex.value = invocationIndex;
  return post_to_action('DocumentOperation.do', 'performLookup');
}

function post_to_action(action, methodToCall) {
  document.forms[0].action = action;
  document.forms[0].methodToCall.value = methodToCall;
  document.forms[0].submit();
  return;
}

function setActionRequestOperation(index, operation){
	var fieldName = 'actionRequestOp[' + index + ']';
	alert("setting " + fieldName + " to " + operation);
	document.forms[0].elements[fieldName].value = operation;
	return;
}

// RouteNodeInstances.jsp
// very similar to changeBranchValue above
function changeValue(button,oIndex,iIndex){

   var rad_val;

   for (var i=0; i < (document.getElementsByName("routeNodeInstanceOp["+oIndex+"].value")).length; i++)
   {
    var radioButton=(document.getElementsByName("routeNodeInstanceOp["+oIndex+"].value"))[i];
    if (radioButton.checked)
         {
         rad_val = radioButton.value;
         break;
        }
   }

   if(rad_val!=null && rad_val.indexOf("update")>-1){
      var value1=button.value;
      var index1=value1.indexOf("don't");

      var stateIds=document.forms[0].nodeStatesDelete.value;
      var keyText=(document.getElementsByName("routeNodeInstance["+oIndex+"].state["+iIndex+"].key"))[0];
      var valText=(document.getElementsByName("routeNodeInstance["+oIndex+"].state["+iIndex+"].value"))[0];
      if(index1 > -1){
          button.value=value1.substr(index1+6);
          var value2=value1.substr(index1+13);
          var stateIds=stateIds.replace(value2," ");
          document.forms[0].nodeStatesDelete.value=stateIds;
          keyText.disabled=false;
          valText.disabled=false;
      }else{
    	  button.value="don't "+value1;
    	  var value2=value1.substr(7);
    	  stateIds=stateIds+" "+value2;
    	  document.forms[0].nodeStatesDelete.value=stateIds;
    	  keyText.disabled=true;
          valText.disabled=true;
      }
    }else{
       alert("please select 'update' radio button first before deleting a certain node state!");
    }
 }
