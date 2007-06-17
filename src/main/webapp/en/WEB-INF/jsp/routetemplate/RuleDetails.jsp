<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<%-- Parameters:
  rule - reference to the rule
  ruleProperty - the property name of the rule
  ruleDelegation - reference to the rule delegation
  delegationProperty - the property name of the rule delegation
--%>

              <c:set var="fromDateRef" value="${ruleProperty}.fromDateValue"/>
              <c:set var="toDateRef" value="${ruleProperty}.toDateValue"/>
              <c:set var="isDelegateRule" value="${Rule2Form.editingDelegate || ruleDelegation != null}"/>
              <html-el:hidden property="${ruleProperty}.previousVersionId"/>
    	      <html-el:hidden property="${ruleProperty}.currentInd" />
              <html-el:hidden property="${ruleProperty}.versionNbr" />
              <html-el:hidden property="${ruleProperty}.delegateRule" />
              <script language="JavaScript">
                addCalendar("<c:out value="${fromDateRef}"/>", "Select Date", "<c:out value="${fromDateRef}"/>", "Rule2Form");
                addCalendar("<c:out value="${toDateRef}"/>", "Select Date", "<c:out value="${toDateRef}"/>", "Rule2Form");
              </script>
              <table width="100%" border=0 cellspacing=0 cellpadding=0>
							<tr>
							  <c:set var="actionVar" value="${ruleProperty}"/>
							  <c:if test="${!empty delegationProperty}">
							    <c:set var="actionVar" value="${delegationProperty}"/>
							  </c:if>
                              <td colspan=2 class="headercell7"><c:if test="${isDelegateRule}">Delegate </c:if>Rule Details
                              <c:if test="${!Rule2Form.editingDelegate || ruleDelegation != null}">
                               - <a href='javascript:takeIndexedAction("<c:out value="${actionName}" />", "removeRule", "<c:out value="${actionVar}"/>")'>remove</a>
                              </c:if>
                              </td>
                            </tr>
                            <tr>
                              <td class="bord-r-t" width="50%" valign="top">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td class="thnormal" align="right">Rule Template:</td>
                              <td class="datacell">
                              		<c:out value="${rule.ruleTemplateName}"/>
                                  <html-el:hidden property="${ruleProperty}.ruleTemplateId"/>
															</td>
							</tr>
							<html-el:hidden property="${ruleProperty}.docTypeName" />
						    <tr>
                              <td class="thnormal" align="right">Document Type:</td>
                              <td class="datacell">
                                <c:out value="${rule.docTypeName}"/>
							  </td>
						    </tr>

						    <%--<c:if test="${rule.delegateRule && !Rule2Form.editingDelegate}">--%>
						    <c:if test="${delegationProperty != null}">
						    <tr>
						      <td class="thnormal" align=right>*Delegation Type:</td>
                              <td class="datacell">
                                <html-el:radio property="${delegationProperty}.delegationType" value="${Constants.DELEGATION_PRIMARY}"/>Primary Delegation<br>
                                <html-el:radio property="${delegationProperty}.delegationType" value="${Constants.DELEGATION_SECONDARY}"/>Secondary Delegation
                                <html-el:errors property="${ruleProperty}.delegationType"/>
						      </td>
						    </tr>
						    </c:if>

                            <tr>
                              <td class="thnormal" align=right>From Date:</td>
                              <td class="datacell">
                                <html-el:text property="${fromDateRef}" size="10"/>
                                 &nbsp;<a href="javascript:showCal('<c:out value="${fromDateRef}"/>');"><img src="images/cal.gif" alt="Click Here to select the from date" align=middle height=16 width=16></a>
                                 <html-el:errors property="${ruleProperty}.fromDateValue"/>
                                 </td>
                            </tr>
                            <tr>
                              <td class="thnormal" align=right>To Date:</td>
                              <td class="datacell">
                                <html-el:text property="${toDateRef}" size="10"/>
                                 &nbsp;<a href="javascript:showCal('<c:out value="${toDateRef}"/>');"><img src="images/cal.gif" alt="Click Here to select the from date" align=middle height=16 width=16></a>
                                 <html-el:errors property="${ruleProperty}.toDateValue"/>
                                 </td>
                            </tr>
                            <tr>
                              <td class="thnormal" align=right>*Description:</td>

                              <td class="datacell">
                                <html-el:textarea property="${ruleProperty}.description" cols="40" rows="2"/>
                                <html-el:errors property="${ruleProperty}.description"/>
                              </td>
                            </tr>
                            <tr>
                              <td class="thnormal" align=right>*Active:</td>
                              <td class="datacell">
                                <html-el:radio property="${ruleProperty}.activeInd" value="true"/><bean-el:message key="general.label.active"/><br>
    						    <html-el:radio property="${ruleProperty}.activeInd" value="false"/><bean-el:message key="general.label.inactive"/>
                                <html-el:errors property="${ruleProperty}.activeInd"/>
						      </td>
                            </tr>
                            <tr>
                              <td class="thnormal" align=right>*Ignore Previous:</td>
                              <td class="datacell">
								<html-el:radio property="${ruleProperty}.ignorePrevious" value="true" />Yes<br>
								<html-el:radio property="${ruleProperty}.ignorePrevious" value="false" />No
                                <html-el:errors property="${ruleProperty}.ignorePrevious"/>
                              </td>
                            </tr>
                          </table>
                          </td>

<%-- ******************************************************* --%>
<%-- ***************  Rule Extension Values  *************** --%>
<%-- ******************************************************* --%>
                          <td class="bord-r-t" valign="top">

                            <c:if test="${rule.hasExtensionValueErrors}">
                            <div class="headercell6">
                              <html-el:messages property="${ruleProperty}.ruleExtensionValues" id="msg">
                                <font color="red"><c:out value="${msg}"/>&nbsp;&nbsp;</font><br>
                              </html-el:messages>
                            </div>
                            </c:if>

                            <table class="bord-r-t" width="100%" border="0" cellspacing="0" cellpadding="0">

<c:set var="fieldIndex" value="${0}"/>
<c:set var="conversionFields" value=""/>
<c:forEach items="${rule.rows}" var="row">
				<c:set var="drawFirstCell" value="true" />
				<c:forEach items="${row.fields}" var="field">
   				<c:set var="fieldValue" value="${ruleProperty}.field[${fieldIndex}].label"/>
				  <c:if test="${field.hasLookupable && !empty field.defaultLookupableName}">
				    <c:if test="${!empty conversionFields}">
				    	<c:set var="conversionFields" value="${conversionFields},"/>
				    </c:if>
				  	<c:set var="conversionFields" value="${conversionFields}${field.defaultLookupableName}:${fieldValue}"/>
				  </c:if>
				  <html-el:hidden property="${ruleProperty}.field[${fieldIndex}].key" value="${field.propertyName}"/>
					<c:choose>
							<c:when test="${field.fieldType==field.HIDDEN}" >
							    <html-el:hidden property="${ruleProperty}.field[${fieldIndex}].label"/>
							</c:when>
							<c:otherwise>
								<c:if test="${drawFirstCell==true}">
										<tr>
										<c:if test="${IsLookupDisplay != null && IsLookupDisplay == true}">
									        <td height="40" class="thnormal" align="right">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
										</c:if>
										<c:if test="${IsLookupDisplay == null}">
											<td class="thnormal" align="right">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
										</c:if>
									    <td class="datacell">&nbsp;<c:set var="drawFirstCell" value="false" />
								</c:if>
								<c:choose>
										<c:when test="${FieldsReadOnly == true}">
											<c:out value="${rule.fields[fieldIndex].label}" />
										</c:when>
										<c:when test="${field.fieldType==field.TEXT}" >
												<html-el:text property="${ruleProperty}.field[${fieldIndex}].label"/>
										</c:when>
										<c:when test="${field.fieldType==field.DROPDOWN}" >
											  <html-el:select property="${ruleProperty}.field[${fieldIndex}].label" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">

				    										<c:set var="fieldValidValues" value="${field.fieldValidValues}" />
																<html-el:options collection="fieldValidValues" property="key" labelProperty="label" />

												</html-el:select>
										</c:when>
										<c:when test="${field.fieldType==field.DROPDOWN_REFRESH}" >
  										<html-el:select property="${ruleProperty}.field[${fieldIndex}].label" onchange="document.forms[0].methodToCall.value='noOp';document.forms[0].submit();" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
  										<c:set var="fieldValidValues" value="${field.fieldValidValues}" />
  										  <html-el:options collection="fieldValidValues" property="key" labelProperty="label" />
											</html-el:select>

										</c:when>
										<c:when test="${field.fieldType==field.RADIO}" >
												<c:forEach items="${field.fieldValidValues}" var="radio">
												  <html-el:radio property="${ruleProperty}.field[${fieldIndex}].label" value="${radio.key}"/><c:out value="${radio.label}" />

												</c:forEach>
										</c:when>
										<c:when test="${field.fieldType==field.QUICKFINDER}" >
								  	        <c:if test="${field.propertyValue!=null && field.propertyValue!=''}">
												<c:out value="${field.propertyValue}" />
											</c:if>
											   <a href="javascript:lookup('<c:out value="${field.quickFinderClassNameImpl}"/>', '<c:out value="${conversionFields}"/>', '<c:out value="${actionName}" />')"><img src="images/searchicon.gif" alt="search" align="absmiddle"></a>
											   <c:set var="conversionFields" value=""/>
										</c:when>
									</c:choose>
									<c:choose>
										<c:when test="${field.fieldHelpUrl==''}" >
												&nbsp;
										</c:when>
										<c:otherwise>
								             <a href='<c:out value="${field.fieldHelpUrl}" />' target="helpWindow"><img src="images/my_cp_inf.gif" alt="more info" hspace=5 border=0 align=absmiddle></a>
										</c:otherwise>
								</c:choose>
						</c:otherwise>
					</c:choose>
                    <html-el:errors property="${ruleProperty}.field[${fieldIndex}].label"/>
  			  <c:set var="fieldIndex" value="${fieldIndex+1}"/>
			</c:forEach>
		</td>
     </tr>
</c:forEach>
</table>
</td>
</tr>
</table>