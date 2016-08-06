<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<!DOCTYPE html>
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

<script>var jsContextPath = "${pageContext.request.contextPath}";</script>

<c:forEach items="${fn:split(ConfigProperties.kns.javascript.files, ',')}"
		   var="javascriptFile">
	<c:if test="${fn:length(fn:trim(javascriptFile)) > 0}">
		<script language="JavaScript" type="text/javascript"
				src="${pageContext.request.contextPath}/${javascriptFile}"></script>
	</c:if>
</c:forEach>

<link rel="stylesheet" type="text/css" href="css/yui/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="css/yui/treeview.css" />
<link href="kr/css/kuali.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="scripts/yui/yahoo-min.js"></script>
<script type="text/javascript" src="scripts/yui/event-min.js"></script>
<script type="text/javascript" src="scripts/yui/connection-min.js"></script>
<script type="text/javascript" src="scripts/yui/treeview-min.js"></script>
<script language="javascript" src="dwr/interface/UnitService.js" ></script>
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
	<h1> 
		Unit Hierarchy
		&nbsp; 
		<kul:help parameterNamespace="KC-UNT" parameterDetailType="Document" parameterName="unitHierarchyHelp" altText="help"/>
	</h1>
	<div class="lookupcreatenew" title="Create a new record">
	<a href="kr/maintenance.do?businessObjectClassName=org.kuali.coeus.common.framework.unit.Unit&methodToCall=start">
	<img src="kr/images/tinybutton-createnew.gif" alt="create new" width="70" height="15"/></a>   
	<a href="index.jsp">Main</a></div>
</div>

<br/><br/>
&nbsp;
<html:image property="methodToCall.expandAllUnitHierarchy" src='${ConfigProperties.kr.externalizable.images.url}tinybutton-expandall.gif' styleClass="tinybutton" alt="Expand All"/>
&nbsp;
<html:image property="methodToCall.collapseAllUnitHierarchy" src='${ConfigProperties.kr.externalizable.images.url}tinybutton-collapseall.gif' styleClass="tinybutton" alt="Collapse All"/>

<!--  initial data here -->
<input type="hidden" id = "units" name="units"   value="${UnitHierarchyForm.units}"/>
<input type="hidden" id = "selectedUnitNumber" name="selectedUnitNumber"  />
<c:set var="dept" value="${UnitHierarchyForm.initialUnitDepth}"/>

<input type="hidden" id = "depth" name = "depth" value="${dept}">

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
		return  "<a href=\"kr/maintenance.do?businessObjectClassName=org.kuali.coeus.common.framework.unit.Unit&unitNumber="+unitNumber+"&methodToCall=edit\" id=\"edit"+unitNumber+"\"> <img  src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif\" styleClass=\"tinybutton\" title=\"edit\" alt=\"edit\" /> </a>"+
		        "<a href=\"kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.rice.kim.api.identity.Person&primaryDepartmentCode="+unitNumber+"&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888\"> <img  src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-usermaint.gif\" styleClass=\"tinybutton\" title=\"User Maintenance\" alt=\"User Maintenance\" /> </a>"+
		        "<a href=\"kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.coeus.common.budget.framework.rate.InstituteLaRate&unitNumber="+unitNumber+"&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888\"> <img  src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-larates.gif\" styleClass=\"tinybutton\" title=\"La rate\" alt=\"La Rate\" /> </a>"+
		        "<a href=\"kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.coeus.common.budget.framework.rate.InstituteRate&unitNumber="+unitNumber+"&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888\"> <img  src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-rates.gif\" styleClass=\"tinybutton\" title=\"rate\" alt=\"Rate\" /> </a>"+
				          "<a href=\"kr/maintenance.do?businessObjectClassName=org.kuali.coeus.common.framework.unit.Unit&unitNumber="+unitNumber+"&methodToCall=copy\"> <img  src=\"${ConfigProperties.kra.externalizable.images.url}tinybutton-copy2.gif\" styleClass=\"tinybutton\" title=\"copy\" alt=\"copy\" /> </a>";
    }

</script>


<!--END SOURCE CODE =============================== -->
<kul:csrf />
</html:form>
<span style="display:none;" id="formComplete"/>
</body>
</html>
