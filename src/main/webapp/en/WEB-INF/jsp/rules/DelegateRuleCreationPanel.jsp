<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

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
                      <td nowrap class="tabtitle">Select Delegation</td>
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
	              <div class="spacercell">
		              <c:choose>
		        	      <c:when test="${Rule2Form.ruleCreationValues.ruleId != null}">
		      	        	Please select a Responsibility.
		    	          </c:when>
		  	            <c:otherwise>
			                Please select the Rule from which you want to delegate.
			              </c:otherwise>
		              </c:choose>
	              </div>
	              <table width="100%" align="center" cellpadding=0 cellspacing=0 class="bord-r-t">
		            <tr>			          
			          <td width="40%" class="thnormal" align="right">Rule:</td>
			          <td class="datacell">
		                <c:out value="${Rule2Form.parentRule.description}"/>
					   				 <html-el:hidden property="ruleCreationValues.ruleId" />
					    			 <html-el:hidden property="delegationSearchOnly" />
		          	    <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleBaseValuesLookupableImplService';document.forms[0].elements['delegationSearchOnly'].value = 'true';"/>
		  	            <div class="error-message"><html-el:errors property="ruleCreationValues.ruleId"/></div>
			          </td>
		            </tr>
		          <c:if test="${Rule2Form.ruleCreationValues.ruleId != null}">
<%--
					<tr>
					  <td class="thnormal" align="right">Delegate Rule Template:</td>
					  <td class="datacell">
					    <c:out value="${Rule2Form.ruleCreationValues.ruleTemplateName}" />
			            <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleTemplateLookupableImplService';"/>
			            <div class="error-message"><html-el:errors property="ruleCreationValues.ruleTemplateId"/></div>
			    	  </td>
				    </tr>
--%>
				    <tr>
				      <td class="headercell7" colspan="2" align="left">Responsibilities:</td>
				    </tr>
				    <tr>
				      <td colspan="2">
				        <table border="0" cellpadding="0" cellspacing="0" width="100%">
				          <tr align="center">
			                <td class="th1">Rule Reviewer</td>
							<td class="th1">Action Request Code</td>
			                <td class="th1">Rule Priority</td>
			              </tr>
			   	        <c:forEach var="responsibility" items="${Rule2Form.parentRule.responsibilities}">
				      	  <tr>
		 	      	        <td class="datacell">
      	      	        	  <html-el:radio property="ruleCreationValues.ruleResponsibilityKey" value="${responsibility.ruleResponsibilityKey}"/>
			      	          <c:choose>
		  	      	            <c:when test="${responsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_ROLE_ID}">
		 	      	              <c:out value="${responsibility.resolvedRoleName}"/>
		      	                  </td>
 				      	          <td class="datacell">
							 	    <c:out value="${Rule2Form.approvePolicyCodes[responsibility.approvePolicy]}"/>
									<c:out value="${Rule2Form.actionRequestCodes[responsibility.actionRequestedCd]}"/>
				      	        </c:when>
				      	        <c:otherwise>
								  <c:out value="${responsibility.reviewer}"/>
								  </td>
								  <td class="datacell">
								    <c:out value="${Rule2Form.actionRequestCodes[responsibility.actionRequestedCd]}"/>
								</c:otherwise>
							  </c:choose>
							</td>							
		   			        <td class="datacell">
					          <c:out value="${responsibility.priority}"/>
						    </td>
						  </tr>
				      	</c:forEach>
	      				</table>
					  </td>
					</tr>
				    <tr>
					  <td class="thnormal" colspan="2" align="center">
					    <a href="javascript:post_to_action('DelegateRule.do', 'createDelegateRule')">Create Delegation Rule</a>
				      </td>
					</tr>
				  </c:if>
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
