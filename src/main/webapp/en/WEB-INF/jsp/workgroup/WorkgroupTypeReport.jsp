<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
  <head>
    <title>Workgroup Type Report</title>
    <link href="css/screen.css" rel="stylesheet" type="text/css">
    <script language=javascript1.2 src="scripts/en-common.js"></script>
  </head>
  <body>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
      <tr>
        <td>
          <img src="images/wf-logo.gif" alt="Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        	<td width="90%"><a href="Lookup.do?method=search&lookupableImplServiceName=WorkgroupTypeLookup">Workgroup Type Search</a> <!-- | <a href="RuleTemplate.do" >Create new Rule Template</a> --></td>
      </tr>
    </table>
    <br>
    <jsp:include page="../WorkflowMessages.jsp" flush="true" /><br>

  <html-el:form method="post" action="/WorkgroupType.do">
    <html-el:hidden property="workgroupTypeId"/>
  <table border=0 cellpadding=0 cellspacing=0 width="100%">
    <tbody>


			  <tr>
    <td width=20><img src="images/pixel_clear.gif" alt="" height=20 width=20></td>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr valign="top">
          <td>
            <p>&nbsp;</p>


      <table border=0 cellpadding=0 cellspacing=0 width="100%">
        <tbody>
          <tr>
            <td width=12><img src="images/tab-topleft.gif" alt="" height=29 width=12></td>
            <td background="images/tab-back.gif" nowrap=nowrap width=200>
              <table width="100%" border=0 cellspacing=0 cellpadding=0>
                <tr>
                  <td nowrap class="tabtitle">Workgroup Type</td>
                </tr>
              </table>
            </td>
            <td width=15><img src="images/tab-bevel.gif" alt="" height=29 width=15></td>
            <td align=right background="images/tab-rightback.gif" valign=top width="95%"><img src="images/tab-topright.gif" alt="" align=top height=29 width=12></td>
          </tr>
        </tbody>
      </table>
      <table border=0 cellpadding=0 cellspacing=0 width="100%">
        <tbody>
          <tr>
            <td class="bordercell-left2" width=8><img src="images/pixel_clear.gif" alt="" height=8 width=8></td>
            <td>
                <table width="100%" border=0 align=center cellpadding=0 cellspacing=0 class="bord-r-t">
                  <tr>
                    <td colspan=2 class="headercell7">Details</td>
                  </tr>

									<tr>
									  <td width="20%" align=right class="thnormal">Workgroup Type Id:</td>
									  <td width="30%" class="datacell"><c:out value="${WorkgroupTypeForm.workgroupType.workgroupTypeId}" default="N/A"/></td>
									</tr>
									<tr>
									  <td width="20%" align=right class="thnormal">Name:</td>
									  <td width="30%" class="datacell"><c:out value="${WorkgroupTypeForm.workgroupType.name}"/></td>
									</tr>
									<tr>
									  <td width="20%" align=right class="thnormal">Label:</td>
									  <td width="30%" class="datacell"><c:out value="${WorkgroupTypeForm.workgroupType.label}"/></td>
									</tr>
									<tr>
									  <td width="20%" align=right class="thnormal">Description:</td>
									  <td width="30%" class="datacell"><c:out value="${WorkgroupTypeForm.workgroupType.description}" default=""/></td>
									</tr>
                  <tr>
                    <td colspan="2" class="headercell7">Attributes</td>
                  </tr>
					        <c:forEach items="${WorkgroupTypeForm.workgroupType.attributes}"  var="attribute">
					            <tr>
					              <td nowrap class="datacell" colspan="3">
					                <div align="left"><c:out value="${attribute.attribute.name}" /></div>
					              </td>
					            </tr>
					        </c:forEach>
			   <tr>
                 <td colspan="2" align="center" class="thnormal">
                   <html-el:image src="${resourcePath}images/buttonsmall_export.gif" align="absmiddle" property="methodToCall.export" />
                 </td>
               </tr>
              </table>
            </td>
            <td class="bordercell-right2" width=8><img src="images/pixel_clear.gif" alt="" height=8 width=8></td>
          </tr>




        </tbody>
      </table>
      <table background="images/tabfoot-back.gif" border=0 cellpadding=0 cellspacing=0 width="100%">
        <tbody>
          <tr>
            <td>
              <img src="images/tabfoot-left.gif" alt="" height=14 width=12>
            </td>
            <td>&nbsp;</td>
            <td align=right><img src="images/tabfoot-right.gif" alt="" height=14 width=12></td>
          </tr>
        </tbody>
      </table>



          </td>
        </tr>
      </table>
    </td>
    <td width=20><img src="images/pixel_clear.gif" alt="" height=20 width=20></td>
  </tr>

    </tbody>
  </table>
  </html-el:form>
  </body>
</html>