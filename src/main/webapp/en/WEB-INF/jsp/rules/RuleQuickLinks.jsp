<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<TITLE>Rule QuickLinks</TITLE>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<c:if test="${RuleQuickLinkForm.useOneStartPortalUrl}">
	<script language="javascript" src="https://docs.onestart.iu.edu/dav/MY/shared/OneStartGlobal.js" ></script>
</c:if>
<script language="JavaScript" src="scripts/cal2.js">
    /*
    Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/) Script
    featured on/available at http://www.dynamicdrive.com/
    This notice must stay intact for use */
</script>
<script language="JavaScript" src="scripts/cal_conf2.js"></script>
<script language="JavaScript" src="scripts/rule-common.js"></script>
</head>
<body>


<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
	<tr>
    	<td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	    <td width="90%">&nbsp;</td>
  </tr>
</table>
<br>
<table width="95%" align="center">
	<tr>
		<td height="30"><strong>Rule QuickLinks</strong></td>
	</tr>
</table>

<table width="95%" border=0 cellspacing=0 cellpadding=0 align="center">
	<c:forEach var="documentTypeStruct" items="${RuleQuickLinkForm.documentTypeQuickLinksStructures}" varStatus="docStatus">
		<c:set var="documentType" value="${documentTypeStruct.documentType}" />
		<tr>
			<c:choose>
				<c:when test="${docStatus.count == 1}">
					<td width=12><img src="images/tab-topleft.gif" alt="" width=12 height=29></td>				
				</c:when>
				<c:otherwise>
					<td width=12><img src="images/tab-topleft1.gif" alt="" width=12 height=27></td>				
				</c:otherwise>
			</c:choose>
            <td width=200 nowrap background="images/tab-back.gif">
              <table width="100%" border=0 cellspacing=0 cellpadding=0>
                <tr>
                	<td nowrap>
                		<c:choose>
                			<c:when test="${renderOpened}">
			                  	<a id="A<c:out value="${docStatus.count}" />" onclick="rend(this, false)">
			                         <img src="images/tinybutton-hide.gif" alt="show" width=45 height=15 border=0 
			                         align=absmiddle id="F<c:out value="${docStatus.count}" />"></a>
			                      &nbsp;
								<a href="<c:url value="DocumentType.do">
								<c:param name="docTypeId" value="${documentType.documentTypeId}" />
								<c:param name="methodToCall" value="report"/>
								</c:url>"><c:out value="${documentType.label}" />
							</c:when>
							<c:otherwise>
								<a id="A<c:out value="${docStatus.count}" />" onclick="rend(this, false)">
			                         <img src="images/tinybutton-show.gif" alt="show" width=45 height=15 border=0 
			                         align=absmiddle id="F<c:out value="${docStatus.count}" />"></a>
			                      &nbsp;
								<a href="<c:url value="DocumentType.do">
								<c:param name="docTypeId" value="${documentType.documentTypeId}" />
								<c:param name="methodToCall" value="report"/>
								</c:url>"><c:out value="${documentType.label}" />
							</c:otherwise>
						</c:choose>
					</a>&nbsp;                       
					</td>
                </tr>
              </table>
            </td>
			<c:choose>
				<c:when test="${docStatus.count == 1}">
        		    <td width=15><img src="images/tab-bevel.gif" alt="" width=15 height=29></td>
					<td width="95%" align=right valign=top background="images/tab-rightback.gif"><img src="images/tab-topright.gif" alt="" width=12 height=29 align=top></td>
				</c:when>
				<c:otherwise>
		            <td width=15><img src="images/tab-bevel1.gif" alt="" width=15 height=27></td>
		            <td width="95%" align=right background="images/tab-rightback1.gif"><img src="images/tab-topright1.gif" alt="" width=12 height=27></td>
				</c:otherwise>
			</c:choose>            
		</tr>
		<c:choose>
			<c:when test="${renderOpened}">
				<tr id="G<c:out value="${docStatus.count}" />">
					<td colspan=4>
						<table width=100% cellspacing=0 cellpadding=0>
							<td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
							<td colspan="4" style="border-style: solid; border-width: thin; ">
								<c:set var="documentTypeStruct" value="${documentTypeStruct}" scope="request"/>
								<c:set var="excludeDocId" value="${documentType.documentTypeId}" scope="request" />
								<c:import url="RuleQuickLinksDocumentTypeLinks.jsp" />
							</td>
		                    <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
						</table>
					</td>
				</tr>		
			</c:when>
			<c:otherwise>
				<tr id="G<c:out value="${docStatus.count}" />" style="display:none">
					<td colspan=4>
						<table width=100% cellspacing=0 cellpadding=0>
							<td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
							<td colspan="4" style="border-style: solid; border-width: thin; ">
								<c:set var="documentTypeStruct" value="${documentTypeStruct}" scope="request"/>
								<c:set var="excludeDocId" value="${documentType.documentTypeId}" scope="request" />
								<c:import url="RuleQuickLinksDocumentTypeLinks.jsp" />
							</td>
		                    <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
						</table>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
		
		<tr>
			<td colspan=4>
				<table width="100%" border=0 cellspacing=0 cellpadding=0>
                  <tr>
                    <td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                    <td class="spacercell">
                      <div align=center>&nbsp;</div>
                    </td>
                    <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
                  </tr>
                </table>	
			 </td>
	     </tr>
	</c:forEach>
	<tr>
		<td colspan=4>
			<table width="100%" border=0 cellspacing=0 cellpadding=0>
		          <tr>
		            <td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
		            <td class="spacercell">
		              <div align=center>&nbsp;</div>
		            </td>
		            <td width=8 class="bordercell-right"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
		          </tr>
		        </table>
				<%-- Page Footer --%>              
		        <table width="100%" border=0 cellpadding=0 cellspacing=0 background="images/tabfoot-back.gif">
		          <tr>
		            <td><img src="images/tabfoot-left.gif" alt="" width=12 height=14></td>
		            <td>&nbsp;</td>
		            <td align=right><img src="images/tabfoot-right.gif" alt="" width=12 height=14></td>
		          </tr>
		        </table>
        </td>
     </tr>
</table>

</body>
</html>