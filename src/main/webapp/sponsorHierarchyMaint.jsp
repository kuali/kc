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
<title>Sponsor Hierarchy</title>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin: 0;
	padding: 0;
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
<script language="javascript" src="dwr/interface/SponsorService.js"></script>
<script language="JavaScript" type="text/javascript"
	src="scripts/kuali_application.js"></script>
<script type="text/javascript" src="scripts/kra_yui.js"></script>
<script type="text/javascript" src="scripts/sponsorhierarchy_yui.js"></script>
<script type="text/javascript" src="scripts/SHNode.js"></script>
<script type="text/javascript" src="kr/scripts/core.js"></script>

<!--begin custom header content for this example-->
<style>
#treeDiv1 {
	background: #fff;
	margin-top: 1em;
	padding: 1em;
	min-height: 7em;
}
</style>
<!--end custom header content for this example-->

<link rel="stylesheet" type="text/css"
	href="../treeview/assets/css/folders/tree.css"></link>

<!--Additional custom style rules for this example:-->

<!--custom icon for each unit-->
<style type="text/css">
#treewrapper {
	background: #fff;
	position: relative;
}

#treediv {
	position: relative;
	width: 250px;
	background: #fff;
	padding: 1em;
}

.icon-page {
	display: block;
	height: 24px;
	width: 18px;
	padding-left: 20px;
	background: transparent url(static/images/page.gif) 0 0px no-repeat;
}

.icon-page-org {
	display: block;
	height: 22px;
	padding-left: 20px;
	background: transparent url(static/images/page.gif) 0 0px no-repeat;
}

.htmlnodelabel {
	margin-left: 25px;
}	

tbody tr:hover {
   background: #FFFF7A;
   color:black;
   cursor:pointer;	
}
</style>

</head>

<body class=" yui-skin-sam">
<html:form styleId="kualiForm" method="post" action="/sponsorHierarchy.do"
	enctype="" onsubmit="return hasFormAlreadyBeenSubmitted();">

	<div class="headerarea-small" id="headerarea-small">
	<h1 align="center">Sponsor Hierarchy</h1>
	</div>
	<!--  initial data here -->
	<input type="hidden" id="topSponsorHierarchies"
		name="topSponsorHierarchies"
		value="${SponsorHierarchyForm.topSponsorHierarchies}" />
	<input type="hidden" id="selectedSponsorHierarchy"
		name="selectedSponsorHierarchy" 
		value="${SponsorHierarchyForm.selectedSponsorHierarchy}" />
	<input type="hidden" id="hierarchyName"
		name="hierarchyName" 
		value="${SponsorHierarchyForm.hierarchyName}" />
	<input type="hidden" id="actionSelected"
		name="actionSelected" 
		value="${SponsorHierarchyForm.actionSelected}" />
	<input type="hidden" id="sponsorCode" name="sponsorCode" />
	<input type="hidden" id="sqlScripts" name="sqlScripts" />
	<input type="hidden" id="sponsorCodeList" name="sponsorCodeList" value="${SponsorHierarchyForm.sponsorCodeList}" />
	<input type="hidden" id="timestamp" name="timestamp" value="${SponsorHierarchyForm.timestamp}" />
	<input type="hidden" id="numberPerGroup" name="numberPerGroup" value="${SponsorHierarchyForm.numberPerGroup}" />

<div id="wait" style="visibility: hidden; font-size: 14pt; color: red;">Please wait ... </div>

	<!--BEGIN SOURCE CODE FOR Sponsor hierarchy =============================== -->

	<div id="treeDiv1"></div>

        <div id="globalbuttons" class="globalbuttons">
           <!--  still posted -->
            <input
				type="image" name="methodToCall.saveSponsorHierarchy"
				onclick='javascript:return (okToSave() == "true");'
				src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif"
				class="globalbuttons" title="save" alt="save">
            <!-- <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.saveSponsorHierarchy" title="save" alt="save" onclick="return okToSave();return false;" /> -->   
            <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons" property="methodToCall.cancelSponsorHierarchyMaint" title="cancel" alt="cancel" />   
        </div>


	<script type="text/javascript">

		var oTextNodeMap = {};
	  	var oDataToNodeMap = {};
	  	var nodeKey = 0;
	 	var tree;    
		// following is a temp solution to align the action buttons. The buttons is still not 100% align.  need a better solution
	    var table_1 = "<table width=\"800\"><tr><td width=\"60%\">";
	    var widthGap=18;
	    var table_1 = "<table style=\"width:1080px\"><tr><td style=\"width:760px\">";
	    var table_2 = "<table style=\"width:1062px\"><tr><td style=\"width:742px\">";
		var savedMapKey;
		var leafNode;
		var sponsorHierarchyToSave;
		var actionList = {};
		var emptyNodes="";
		var retMsg;
        var sponsorCodeList = document.getElementById("sponsorCodeList").value
        var updatesql = "update sponsor_hierarchy set "; // use mt for testing now
        var insertsql = "insert into sponsor_hierarchy  ";
        var deletesql = "delete from sponsor_hierarchy  ";
        var sqlScripts = "";
   		var hierarchyName = document.getElementById("hierarchyName").value;
   		var subgroup = {};
   		var subgroupNodes = ";";
        var timestampKey = document.getElementById("timestamp").value;
   		var numberPerGroup = document.getElementById("numberPerGroup").value;
   	
   	var sponsorHierarchyReturn = sponsorHierarchy();

	//once the DOM has loaded, we can go ahead and set up our tree:
	//YAHOO.util.Event.onDOMReady(YAHOO.example.treeExample.init, YAHOO.example.treeExample,true)
	YAHOO.util.Event.onDOMReady(sponsorHierarchyReturn.init, sponsorHierarchyReturn,true)

	function setupMaintenanceButtons(sponsorHierarchy, parentNode) {
    // if included in a js file, the reference to image can't be resolved.
       if (sponsorHierarchy.indexOf(":") > 0) {
            var rootNodeDesc = getRootNode(parentNode);
       		var sponsorCode=getUnitNumber(sponsorHierarchy);
			return  "<input type=\"image\" name=\"methodToCall.performInquiry.(!!org.kuali.kra.bo.Sponsor!!).((#sponsorCode:sponsorCode#)).anchorNode\" src=\"kr/static/images/book_open.png\" onclick=\"javascript: setSponsorCode('"+sponsorCode+"');inquiryPop('org.kuali.kra.bo.Sponsor','sponsorCode:sponsorCode'); return false\" class=\"tinybutton\" title=\"Direct Inquiry\" alt=\"Direct Inquiry\">"	+
					"<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif\" styleClass=\"tinybutton\" VALUE=\"Delete Sponsor\" ALT=\"Delete Sponsor\" NAME=\"deletenode\" onClick=\"deleteNode("+nodeKey+");return false;\" >  ";
       } else {
         	if ((parentNode.depth+1) == 0) {
				return  "<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif\" styleClass=\"tinybutton\"  VALUE=\"Add SubGroup\" ALT=\"Add SubGroup\" NAME=\"addnode\" onClick=\"addNode("+nodeKey+");return false;\" > ";
         	} else if ((parentNode.depth+1) == 10) {         	   
				return  "<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif\" styleClass=\"tinybutton\" VALUE=\"Edit Group Name\" ALT=\"Edit Group Name\" NAME=\"editnode\" onClick=\"editNodeLabel("+nodeKey+");return false;\" > " +
				          "<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}upArrow.png\" styleClass=\"tinybutton\"VALUE=\"Move up\" ALT=\"Move up\" NAME=\"moveup\" onClick=\"moveUp("+nodeKey+");return false;\" > " +
				          "<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}downArrow.png\" styleClass=\"tinybutton\" VALUE=\"Move down\" ALT=\"Move down\" NAME=\"movedown\" onClick=\"moveDown("+nodeKey+");return false;\" > " +
				          "<INPUT TYPE=\"button\" SRC=\"button.gif\" VALUE=\"Add Sponsor\" ALT=\"Add Sponsor\" NAME=\"addsponsor\" onClick=\"addSponsor("+nodeKey+");return false;\" > " +
				          "<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif\" styleClass=\"tinybutton\" VALUE=\"Delete Group\" ALT=\"Delete Group\" NAME=\"deletenode\" onClick=\"deleteNode("+nodeKey+");return false;\" >  ";
         	} else {
				return  "<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif\" styleClass=\"tinybutton\" VALUE=\"Add SubGroup\" ALT=\"Add SubGroup\" NAME=\"addnode\" onClick=\"addNode("+nodeKey+");return false;\" > " +
				          "<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif\" styleClass=\"tinybutton\" VALUE=\"Edit Group Name\" ALT=\"Edit Group Name\" NAME=\"editnode\" onClick=\"editNodeLabel("+nodeKey+");return false;\" > " +
				          "<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}upArrow.png\" styleClass=\"tinybutton\"VALUE=\"Move up\" ALT=\"Move up\" NAME=\"moveup\" onClick=\"moveUp("+nodeKey+");return false;\" > " +
				          "<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}downArrow.png\" styleClass=\"tinybutton\" VALUE=\"Move down\" ALT=\"Move down\" NAME=\"movedown\" onClick=\"moveDown("+nodeKey+");return false;\" > " +
				          "<INPUT TYPE=\"button\" SRC=\"button.gif\" VALUE=\"Add Sponsor\" ALT=\"Add Sponsor\" NAME=\"addsponsor\" onClick=\"addSponsor("+nodeKey+");return false;\" > " +
				          "<INPUT TYPE=\"image\" src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif\" styleClass=\"tinybutton\" VALUE=\"Delete Group\" ALT=\"Delete Group\" NAME=\"deletenode\" onClick=\"deleteNode("+nodeKey+");return false;\" >  ";
         	}
       }
       
    }
	



    
</script>


	<!--END SOURCE CODE =============================== -->

</html:form>
</body>
</html>
