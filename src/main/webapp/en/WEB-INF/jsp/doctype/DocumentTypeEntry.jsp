<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>
	
	<html>
	<head>
	<title>Document Type</title>
	<link href="css/screen.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="scripts/en-common.js"></script>
  <script language="JavaScript" src="scripts/doctype-common.js"></script>

	</head>
	<body onload="javascript:appSpecificRouteRecipientLookup(); firstVisibility();">
	<c:set var="ActionForm" value="${DocumentTypeForm}" scope="request" />
	<html-el:form action="DocumentType.do">
	<html-el:hidden property="documentType.documentTypeId" />
	<html-el:hidden property="documentType.lockVerNbr" />
	<html-el:hidden property="methodToCall" />
	<html-el:hidden property="documentType.routeLevelsInherited" />
	<html-el:hidden property="editingRouteLevelInd" />
	<html-el:hidden property="routeLevelIndex" />
	<html-el:hidden property="moveRouteLevel" />
	<html-el:hidden property="routeLevelSize" />
    <html-el:hidden property="searchableAttributeIndex" />
    <html-el:hidden property="searchableAttributeSize" />
	<html-el:hidden property="routeLevel.exceptionRoute" />
	<html-el:hidden property="routeLevel.adHocRoute" />
	<html-el:hidden property="routeLevel.routeMethodCode" />
	<html-el:hidden property="docTypeVisible" />
	<html-el:hidden property="policyVisible" />
	<html-el:hidden property="routeLevelVisible" />
    <html-el:hidden property="searchableAttributeVisible" />
	<html-el:hidden property="visibleSelected" />
	<html-el:hidden property="lookupType" />   
	<html-el:hidden property="lookupableImplServiceName" />
    <html-el:hidden property="docId" />
		
	<table width="100%" border=0 cellpadding=0 cellspacing=0>
	  <tr>
	    <td height=100 colspan=3>
	    <jsp:include page="DocumentEntryHeader.jsp" flush="true" />
	      <table width="100%" border=0 cellpadding=0 cellspacing=0 bgcolor="#FFFFFF">
	        <tr>
	          <td width="20"><img src="images-channelglobal/pixel_clear.gif" alt="" width="1" height="1"><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
	          <td>
	            <table width="100%" border=0 cellspacing=0 cellpadding=0>
	              <tr>
	                <td height=30><strong>Document Type</strong></td>
	              </tr>
	              <tr>
	                <td>
	                  <p><c:out value="${DocumentTypeForm.instructionForCreateNew}" /></p>
	                </td>
	              </tr>
	            </table>
	              <div align="right">
	                 <html-el:image property="methodToCall.show" src="images/buttonsmall_expandall.gif" onclick="document.forms[0].visibleSelected.value = 'all'"/>&nbsp;
	              <html-el:image property="methodToCall.hide" src="images/buttonsmall_collapseall.gif" onclick="document.forms[0].visibleSelected.value = 'all'"/>
	
	              </div>
	          </td>
	          <td width="20"><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
	        </tr>
	      </table>
	    </td>
	  </tr>
	  
	  <tr>
	    <td>
		<jsp:include page="../WorkflowMessages.jsp" flush="true" />
		</td>
	  </tr>
	  
	  <tr>
	    <td>    
	      <table width="100%" border=0 cellspacing=0 cellpadding=0>
	        <tr>
	          <td>&nbsp;</td>
	          <td>&nbsp;</td>
	          <td>&nbsp;</td>
	        </tr>
	        <tr>
	          <td width=20><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
	          <td>
			    <jsp:include page="DocumentType.jsp" flush="true" />
				<jsp:include page="DocumentTypePolicy.jsp" flush="true" />
			    <jsp:include page="DocumentTypeSearchableAttributes.jsp" flush="true" />
			    <jsp:include page="DocumentTypeRouteLevels.jsp" flush="true" />
	          </td>
	          <td width=20><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
	        </tr>  
	        <tr>
    		  <td></td>
    		  <td>
    	  		<c:set var="inputLocation" value="DocumentTypeEntry.jsp" scope="request"/>
		  		<jsp:include page="../DocumentEntryButtons.jsp" flush="true" />
			  </td>
     	   	  <td></td>
      		</tr>	
	  	  </table>
	  </html-el:form>
	
	</body>
</html>