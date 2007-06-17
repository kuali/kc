<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:if test="${!Rule2Form.editingDelegate}">
  <table border=0 cellpadding=0 cellspacing=0 width="100%">
    <tbody>
      <tr>
        <td width="20"><img src="images/pixel_clear.gif" alt="" height="20" width="20"></td>
        <td>
          <table border=0 cellpadding=0 cellspacing=0 width="100%">
            <tbody>
              <tr>
                <td width=12><img src="images/tab-topleft.gif" alt="" height=29 width=12></td>
                <td background="images/tab-back.gif" nowrap=nowrap width=200>
                  <table width="100%" border=0 cellspacing=0 cellpadding=0>
                    <tr>
                      <td nowrap class="tabtitle">Create New Rule</td>
                      <td width=100 align=right nowrap>&nbsp;</td>
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
	              <div class="spacercell"><c:out value="${Rule2Form.instructionForCreateNew}" /></div>
	              <table width="100%" align="center" cellpadding=0 cellspacing=0 class="bord-r-t">
		            <tr>
			          <td width="40%" class="thnormal" align="right">Rule Template:</td>			
			          <td class="datacell">
			            <c:out value="${Rule2Form.ruleCreationValues.ruleTemplateName}" />
			            <html-el:hidden property="ruleCreationValues.ruleTemplateId" />
         			    <html-el:hidden property="ruleCreationValues.ruleTemplateName"/>
     	               <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleTemplateLookupableImplService';"/>
			          </td>
		            </tr>
	                <tr>
		              <td class="thnormal" align="right">Doc Type:</td>
		              <td class="datacell">
		                <html-el:hidden property="ruleCreationValues.docTypeName" />
  		                <c:out value="${Rule2Form.ruleCreationValues.docTypeName}" />
                        <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'DocumentTypeLookupableImplService';"/>
		              </td>
	                </tr>
		            <tr>
		              <td class="thnormal" colspan="2" align="center">
		                <c:set var="addActionName" value="Rule.do#rule${Rule2Form.myRules.size}"/>
		                <a href="javascript:post_to_action('<c:out value="${addActionName}"/>', 'createNew')">Create a New Rule</a>
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
                <td><img src="images/tabfoot-left.gif" alt="" height=14 width=12></td>
                <td>&nbsp;</td>
                <td align=right><img src="images/tabfoot-right.gif" alt="" height=14 width=12></td>
              </tr>
            </tbody>
          </table>
	    </td>
	    <td width="20"><img src="images/pixel_clear.gif" alt="" height="20" width="20"></td>
	  </tr>
	</tbody>
  </table>
</c:if>
