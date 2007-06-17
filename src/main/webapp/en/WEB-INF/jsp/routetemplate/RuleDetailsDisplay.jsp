<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<%-- Parameters: 
  rule - reference to the rule
  ruleProperty - the property name of the rule
--%>
              <c:set var="isDelegateRule" value="${Rule2Form.editingDelegate || ruleDelegation != null}"/>

                            <table width="100%" border=0 cellspacing=0 cellpadding=0>
							<tr>
							  <c:set var="actionVar" value="${ruleProperty}"/>
							  <c:if test="${!empty delegationProperty}">
							    <c:set var="actionVar" value="${delegationProperty}"/>
							  </c:if>
                              <td colspan=2 class="headercell7">Rule Details</td>
                            </tr>
                            <tr>
                              <td class="bord-r-t" width="50%" valign="top">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td class="thnormal" align="right">Rule Template</td>
                              <td class="datacell">
                            	  <c:out value="${rule.ruleTemplateName}"/>
							  </td>
							</tr>
						    <tr>
                              <td class="thnormal" align="right">Document Type</td>
                              <td class="datacell">
                                <c:out value="${rule.docTypeName}"/>
							  </td>
						    </tr>														
                            <tr>
                              <td class="thnormal" align=right>From Date:</td>
                              <td class="datacell">    
                                <c:out value="${rule.fromDateValue}"/>                            
                              </td>
                            </tr>
                            <tr>
                              <td class="thnormal" align=right>To Date:</td>
                              <td class="datacell">
                                <c:out value="${rule.toDateValue}"/>
                              </td>
                            </tr>
                            <tr>
                              <td class="thnormal" align=right>Description:</td>
                              <td class="datacell">
                                <c:out value="${rule.description}"/>
                              </td>
                            </tr>
                            <tr>
                              <td class="thnormal" align=right>Active:</td>
                              <td class="datacell"><c:out value="${rule.activeIndDisplay}"/>
<%--
                                <c:if test="${rule.activeInd}">Yes</c:if>
                                <c:if test="${!rule.activeInd}">No</c:if>
--%>
						      </td>
                            </tr>
                            <tr>
                              <td class="thnormal" align=right>Ignore Previous:</td>
                              <td class="datacell">
                                <c:if test="${rule.ignorePrevious}">Yes</c:if>
                                <c:if test="${!rule.ignorePrevious}">No</c:if>
						      </td>
                            </tr>
                          </table>
                          </td>

<%-- ******************************************************* --%>
<%-- ***************  Rule Extension Values  *************** --%>
<%-- ******************************************************* --%>
      <td class="bord-r-t" valign="top">                            
        <table class="bord-r-t" width="100%" border="0" cellspacing="0" cellpadding="0">
						<c:forEach var="ruleExtension" items="${rule.ruleExtensions}">
							<c:forEach var="ruleExtensionValue" items="${ruleExtension.extensionValues}">
								<tr>
									<td class="thnormal" >
										<c:forEach var="extensionLabel" items="${rule.ruleExtensionValueLabels}" >
							  			<c:if test="${ruleExtensionValue.key == extensionLabel.key}" >
								  			<c:out value="${extensionLabel.value}" />&nbsp;
								  		</c:if>
							  		</c:forEach>
									</td>
									<td class="datacell" ><c:out value="${ruleExtensionValue.value}"/></td>
								</tr>
							</c:forEach>
							<tr><td class="datacell" colspan="2">&nbsp;</td></tr>
						</c:forEach>
					</table>
		</td>
	</tr>
	<c:if test="${Rule2Form.responsibility.ruleResponsibilityKey != null}" >
	    <tr>
	      <td class="headercell7" colspan="2" align="left">Responsibility:</td>
	    </tr>
	    <tr>
	      <td colspan="2">
	        <table border="0" cellpadding="0" cellspacing="0" width="100%">
	          <tr align="center">
                <td class="th1">Rule Reviewer</td>
				<td class="th1">Action Request Code</td>
                <td class="th1">Rule Priority</td>
              </tr>
	      	    <tr>
	      	      <td class="datacell">
	      	        <c:choose>
	      	        <c:when test="${Rule2Form.responsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_ROLE_ID}">
	      	          <c:out value="${Rule2Form.responsibility.ruleResponsibilityName}"/>
	      	          </td>
	      	          <td class="datacell">
										  <c:out value="${Rule2Form.approvePolicyCodes[Rule2Form.responsibility.approvePolicy]}"/>
										  <c:out value="${Rule2Form.actionRequestCodes[Rule2Form.responsibility.actionRequestedCd]}"/>
						        </td>
	      	        </c:when>
	      	        <c:otherwise>
										  <c:out value="${Rule2Form.responsibility.reviewer}"/>
								  </td>
								  <td class="datacell">
					  				<c:out value="${Rule2Form.actionRequestCodes[Rule2Form.responsibility.actionRequestedCd]}"/>
									</td>
								</c:otherwise>
							</c:choose>
				     <td class="datacell">
				       <c:out value="${Rule2Form.responsibility.priority}"/>
				     </td>
				   </tr>
	      	</table>
	      </td>
	    </tr>
	 </c:if>
</table>