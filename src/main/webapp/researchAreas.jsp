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
<script type="text/javascript" src="scripts/yui/treeview.js"></script>

<script type="text/javascript" src="scripts/yui/yahoo-dom-event.js"></script>
<script type="text/javascript" src="scripts/yui/dragdrop.js"></script>
<script language="JavaScript" type="text/javascript" src="dwr/engine.js"></script>
<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>
<script language="javascript" src="dwr/interface/ResearchAreasService.js" ></script>
<script language="JavaScript" type="text/javascript" src="scripts/kuali_application.js"></script>
<script type="text/javascript" src="scripts/researchareas_yui.js" ></script>
<!-- <script type="text/javascript" src="scripts/yui_dd.js" ></script> -->

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
	action="/researchAreas.do" enctype=""
	onsubmit="return hasFormAlreadyBeenSubmitted();"> 

<div class="headerarea-small" id="headerarea-small">	
	<h1 align="center"> Research Areas </h1>
	<div class="lookupcreatenew" title="Create a new record">
	<a href="kr/maintenance.do?businessObjectClassName=org.kuali.kra.bo.ResearchAreas&methodToCall=start">
	<img src="kr/images/tinybutton-createnew.gif" alt="create new" width="70" height="15"/></a>   
	<input type="image"  src="kr/static/images/buttonsmall_search.gif" alt="search" onClick="search(); return false;" width="70" height="15"/></a>   
	<a href="index.jsp">Main</a></div>
</div>



<!--  initial data here -->
<input type="hidden" id = "researchAreas" name="researchAreas"   value="${ResearchAreasForm.researchAreas}"/>

<!--BEGIN SOURCE CODE FOR Research Areas =============================== -->

<div id="treeDiv1"></div>


<script type="text/javascript">

   	var researchAreaReturn = researchArea();
   	var oTextNodeMap = {};
	var nodeKey ;
	var ddKey = 1 ;
	var tree;    
    var table_1 = "<table width=\"800\"><tr><td width=\"60%\">";
    var widthGap=18;
    var table_1 = "<table style=\"width:1080px\"><tr><td style=\"width:760px\">";
    var table_2 = "<table style=\"width:1062px\"><tr><td style=\"width:742px\">";
   	
	//once the DOM has loaded, we can go ahead and set up our tree:
	//YAHOO.util.Event.onDOMReady(YAHOO.example.treeExample.init, YAHOO.example.treeExample,true)
	YAHOO.util.Event.onDOMReady(researchAreaReturn.init, researchAreaReturn,true)

    function setupMaintenanceButtons(researchArea) {
    // if included in a js file, the reference to image can't be resolved.
       var researchAreaCode=getResearchAreaCode(researchArea);
       if (nodeKey > 0) {
		   return  "<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif\" styleClass=\"tinybutton\" VALUE=\"Add SubGroup\" ALT=\"Add SubGroup\" NAME=\"addnode\" onClick=\"addNode("+nodeKey+");return false;\" > " +
				"<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif\" styleClass=\"tinybutton\" VALUE=\"Edit Group Name\" ALT=\"Edit Group Name\" NAME=\"editnode\" onClick=\"editNodeLabel("+nodeKey+");return false;\" > " +
				"<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}move.png\" styleClass=\"tinybutton\"VALUE=\"Move up\" ALT=\"Move\" NAME=\"move\" onClick=\"move("+nodeKey+");return false;\" > "
	   }	
	   return "";		 
    }

</script>


<!--END SOURCE CODE =============================== -->

</html:form>
</body>
</html>
