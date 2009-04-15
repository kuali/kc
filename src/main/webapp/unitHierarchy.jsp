<%--
 Copyright 2006-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Unit Hierarchy</title>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>

<link rel="stylesheet" type="text/css" href="css/yui/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="css/yui/treeview.css" />
<link href="kr/css/kuali.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="scripts/yui/yahoo-min.js"></script>
<script type="text/javascript" src="scripts/yui/event-min.js"></script>
<script type="text/javascript" src="scripts/yui/connection-min.js"></script>
<script type="text/javascript" src="scripts/yui/treeview-min.js"></script>
<script language="JavaScript" type="text/javascript" src="dwr/engine.js"></script>
<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>
<script language="javascript" src="dwr/interface/UnitService.js" ></script>
<script language="JavaScript" type="text/javascript" src="scripts/kuali_application.js"></script>
<script type="text/javascript" src="scripts/kra_yui.js" ></script>

<!--begin custom header content for this example-->
<style>
#treeDiv1 {background: #fff; margin-top:1em; padding:1em; min-height:7em;}
</style>
<!--end custom header content for this example-->


<!--custom icon for each unit-->
<style type="text/css">
    #treewrapper {background: #fff; position:relative;}
	#treediv {position:relative; width:250px; background: #fff; padding:1em;}
    .icon-page { display:block; height: 24px; width: 18px; padding-left: 20px; background: transparent url(static/images/page.gif) 0 0px no-repeat; }
    .icon-page-org { display:block; height: 22px; padding-left: 20px; background: transparent url(static/images/page.gif) 0 0px no-repeat; }
    .htmlnodelabel { margin-left: 25px; }
    tbody tr:hover {
   background: #FFFF7A;
   color:black;
   cursor:pointer;
	}
</style>

</head>

<body class=" yui-skin-sam">
<html:form styleId="kualiForm" method="post"
	action="/unitHierarchy.do" enctype=""
	onsubmit="return hasFormAlreadyBeenSubmitted();"> 

<div class="headerarea-small" id="headerarea-small">	
	<h1 align="center"> Unit Hierarchy </h1>
	<div class="lookupcreatenew" title="Create a new record">
	<a href="kr/maintenance.do?businessObjectClassName=org.kuali.kra.bo.Unit&methodToCall=start">
	<img src="kr/images/tinybutton-createnew.gif" alt="create new" width="70" height="15"/></a>   
	<a href="index.jsp">Main</a></div>
</div>



<!--  initial data here -->
<input type="hidden" id = "units" name="units"   value="${UnitHierarchyForm.units}"/>
<input type="hidden" id = "selectedUnitNumber" name="selectedUnitNumber"  />
<input type="hidden" id = "depth" name = "depth" value="${UnitHierarchyForm.initialUnitDepth}">

<!--BEGIN SOURCE CODE FOR Unit hierarchy =============================== -->

<div id="treeDiv1"></div>


<script type="text/javascript">

   	var unitHierarchyReturn = unitHierarchy();
   	
	//once the DOM has loaded, we can go ahead and set up our tree:
	//YAHOO.util.Event.onDOMReady(YAHOO.example.treeExample.init, YAHOO.example.treeExample,true)
	YAHOO.util.Event.onDOMReady(unitHierarchyReturn.init, unitHierarchyReturn,true)

    function setupMaintenanceButtons(unit) {
    // if included in a js file, the reference to image can't be resolved.
       var unitNumber=getUnitNumber(unit);
		return  "<a href=\"kr/maintenance.do?businessObjectClassName=org.kuali.kra.bo.Unit&unitNumber="+unitNumber+"&methodToCall=edit\" id=\"edit"+unitNumber+"\"> <img  src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif\" styleClass=\"tinybutton\" title=\"edit\" alt=\"edit\" /> </a>"+
		        "<a href=\"kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.bo.Person&homeUnit="+unitNumber+"&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888\"> <img  src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-usermaint.gif\" styleClass=\"tinybutton\" title=\"User Maintenance\" alt=\"User Maintenance\" /> </a>"+
		        "<a href=\"kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.bo.InstituteLaRate&unitNumber="+unitNumber+"&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888\"> <img  src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-larates.gif\" styleClass=\"tinybutton\" title=\"La rate\" alt=\"La Rate\" /> </a>"+
		        "<a href=\"kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.bo.InstituteRate&unitNumber="+unitNumber+"&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888\"> <img  src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-rates.gif\" styleClass=\"tinybutton\" title=\"rate\" alt=\"Rate\" /> </a>"+
				          "<a href=\"kr/maintenance.do?businessObjectClassName=org.kuali.kra.bo.Unit&unitNumber="+unitNumber+"&methodToCall=copy\"> <img  src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-copy2.gif\" styleClass=\"tinybutton\" title=\"copy\" alt=\"copy\" /> </a>";
    }

</script>


<!--END SOURCE CODE =============================== -->

</html:form>
</body>
</html>
