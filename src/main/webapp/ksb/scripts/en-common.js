function setMethod(target) {
  document.forms[0].methodToCall.value=target;
}

function setMethodToCallAndSubmit(methodToCall) { 
	document.forms[0].methodToCall.value=methodToCall;
	document.forms[0].submit();
}

function setParamValue(param, paramValue) {
  document.forms[0].elements[param].value = paramValue;
}

function post_to_action(formname, action) {
  document.forms[formname].action = action;
  document.forms[formname].submit();
  return;
}

function export_results(formname, action, format) {
  document.forms[formname].methodToCall.value = 'export';
  return post_to_action(formname, action);
}

function quick_finder(searchTarget, formname, action) {
  document.forms[formname].searchAction.value = "gotoSearch";
  document.forms[formname].searchTarget.value = searchTarget;
  return post_to_action(formname, action);
}

function helpPop(helpId){
  window.open("https://onestart.iu.edu/my/HelpEntry.do?helpId=" + helpId, "_blank", "width=640, height=365");
}

function workflowHelpPop(helpKey){
  window.open("Help.do?methodToCall=getHelpEntry&helpKey=" + helpKey, "_blank", "width=640, height=365, scrollbars=yes");
}

var open_file 	= 'images/tinybutton-hide1.gif';
var closed_file	= 'images/tinybutton-show.gif';
img1 = new Image();
img1.src = closed_file;
img2 = new Image();
img2.src = open_file;

function rend(obj, cc, formFieldId) {

    var len = ((String)(obj.id)).indexOf('-',0)-1;
    if (len == -2)
      len = ((String)(obj.id)).length;
    var index = ((String)(obj.id)).substr(1, len);
    
    
    var subGCount = 0;

	var grpIdx = getElement("G"+index);
    var fldIdx = getElement("F"+index);
    var lnkIdx = getElement("A"+index);      
    if (!document.all && !document.getElementById) {
      alert('This browser is not supported by this tree...');
      return;
    }

    if (grpIdx.style.display == 'none') {
      grpIdx.style.display = '';
      if(cc){
        fldIdx.src = open_file_cc;
      } else {
        fldIdx.src = open_file;
      }
      if (formFieldId) {
      	getElement(formFieldId).value = 'true';
      }
    } else {
      grpIdx.style.display = 'none';
      if(cc){
        fldIdx.src = closed_file_cc;
      } else {
        fldIdx.src = closed_file;
      }
      if (formFieldId) {
      	getElement(formFieldId).value = 'false';
      }
    }
    return;
}

function expandAll(doit, cc) {

  var index = 1;
  while (index > 0) {
    if(document.getElementById) {
      var grpIdx = document.getElementById("G"+index);
      var fldIdx = document.getElementById("F"+index);
    } else if (document.all) {
      var grpIdx = eval("document.all.G"+index);
      var fldIdx = eval("document.all.F"+index);
    }
    
    if (!grpIdx) {
      index = -1;
    } else {
      if (doit == "true") {
        grpIdx.style.display = '';
        if(cc && index == 1){
          fldIdx.src = open_file_cc;
        }else{
          fldIdx.src = open_file;
        }
      } else {
        grpIdx.style.display = 'none';
        if(cc && index == 1){
          fldIdx.src = closed_file_cc;
        }else{
          fldIdx.src = closed_file;
        }
      }
      index++;
    }
  }
}

function appSpecificRouteRecipientLookup(){
  if(document.forms[0].appSpecificRouteRecipientType != null){
	  if(document.forms[0].appSpecificRouteRecipientType[0].checked){
		if (document.getElementById) {
		  document.getElementById("personLookup").style.display = "inline";
		  document.getElementById("workgroupLookup").style.display = "none";
		} else {
		  document.all["personLookup"].style.display = "inline";
		  document.all["workgroupLookup"].style.display = "none";
			}
	  } else if(document.forms[0].appSpecificRouteRecipientType[1].checked){
	    if (document.getElementById) {
		  document.getElementById("personLookup").style.display = "none";
	      document.getElementById("workgroupLookup").style.display = "inline";
	    } else {
		  document.all["personLookup"].style.display = "none";
		  document.all["workgroup"].style.display = "inline";
		}
	  } 
  }
}

function appSpecificRouteRecipientLookupType(type){
	if(type == 'person'){
		document.forms[0].appSpecificRouteRecipientType[0].checked = true;	
	}
	if(type == 'workgroup'){
		document.forms[0].appSpecificRouteRecipientType[1].checked = true;	
	}
}

function getElement(elementId) {
	if (document.getElementById) {
		return document.getElementById(elementId);
	} else {
		return document.all[elementId];
	}
	return null;
}

