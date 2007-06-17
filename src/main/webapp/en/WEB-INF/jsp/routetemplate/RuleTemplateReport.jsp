<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
  <head>
    <title>Rule Template Report</title>
    <link href="css/screen.css" rel="stylesheet" type="text/css">
    <script language=javascript1.2 src="scripts/en-common.js"></script>
    <script language="javascript1.2" src="scripts/rule-common.js"></script>
  </head>
  <body>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="headercell1">
      <tr>
        <td>
          <img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        	<td width="90%"><a href="Lookup.do?method=search&lookupableImplServiceName=RuleTemplateLookupableImplService">Rule Template Search</a> <!-- | <a href="RuleTemplate.do" >Create new Rule Template</a> --></td>
      </tr>
    </table>
    <br>
    <jsp:include page="../WorkflowMessages.jsp" flush="true" /><br>

  <html-el:form method="post" action="/RuleTemplate.do">
    <html-el:hidden property="lookupableImplServiceName" />
    <html-el:hidden property="methodToCall"/>
		<html-el:hidden property="conversionFields" value=""/>
    <html-el:hidden property="currentRuleTemplateId"/>
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
                  <td nowrap class="tabtitle">Rule Template</td>
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
									  <td width="20%" align=right class="thnormal">Rule Template Id:</td>
									  <td width="30%" class="datacell"><c:out value="${RuleTemplateForm.ruleTemplate.ruleTemplateId}" default="N/A"/></td>
									</tr>
									<tr>
									  <td width="20%" align=right class="thnormal">Name:</td>
									  <td width="30%" class="datacell"><c:out value="${RuleTemplateForm.ruleTemplate.name}"/></td>
									</tr>
									<tr>
									  <td width="20%" align=right class="thnormal">Description:</td>
									  <td width="30%" class="datacell"><c:out value="${RuleTemplateForm.ruleTemplate.description}" default=""/></td>
									</tr>
									<c:if test="${RuleTemplateForm.ruleTemplate.delegationTemplate != null}">
										<tr>
										  <td align=right class="thnormal">Rule template delegate:</td>
										  <td class="datacell"><c:out value="${RuleTemplateForm.ruleTemplate.delegationTemplate.name}" default="N/A"/></td>
										</tr>
									</c:if>
                  <tr>
                    <td colspan=2 class="headercell7">Rule Template Attributes</td>
                  </tr>
					        <tr>
					          <th class="thnormal">
					            <div align="center">Attribute Type</div>
					          </th>
					          <th class="thnormal">Required</th>
					        </tr>

					        <logic-el:iterate id="attribute" name="RuleTemplateForm" property="ruleTemplate.ruleTemplateAttributes" indexId="ctr">
					            <tr>
					              <td nowrap class="datacell">
					                <div align="center"><c:out value="${attribute.ruleAttribute.name}" /></div>
					              </td>
					              <td nowrap class="datacell"><c:out value="${attribute.required}" /></td>
					            </tr>
					        </logic-el:iterate>
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