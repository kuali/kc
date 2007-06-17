<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
<head>
<TITLE>Rule</TITLE>
<link href="css/screen.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/cal2.js">
    /*
    Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/) Script
    featured on/available at http://www.dynamicdrive.com/
    This notice must stay intact for use */
</script>
<script language="JavaScript" src="scripts/cal_conf2.js"></script>
<script language="javascript" src="scripts/routetemplate-common.js"></script>

</head>
<body onload="javascript:personWorkgroup()">
  <c:set var="actionName" value="Rule2.do" scope="request"/>
<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
	<tr>
    	<td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	    <td width="90%"><a href="Lookup.do?lookupableImplServiceName=RuleBaseValuesLookupableImplService" >Rule search</a> | <a href="Rule2.do" >Create new Rule</a> | <a href="DelegateRule2.do" >Create new Delegation Rule</a></td>
  </tr>
</table>
<br>

<html-el:form method="post" action="/Rule2.do">
<html-el:hidden property="lookupableImplServiceName" />
<html-el:hidden property="choosingTemplate" />
<html-el:hidden property="methodToCall" />
<html-el:hidden property="ruleIndex" value=""/>
<html-el:hidden property="responsibilityIndex" value=""/>
<html-el:hidden property="delegationIndex" value=""/>
<html-el:hidden property="delegationResponsibilityIndex" value=""/>
<html-el:hidden property="conversionFields" />
<html-el:hidden property="editingDelegate" />

<table width="95%" align="center">
	<tr>
		<td height="30"><strong><c:if test="${Rule2Form.editingDelegate}">Delegate </c:if>Rule</strong></td>
	</tr>
	<tr>
	  <td>
	    <html-el:messages name="workflowServiceError" id="msg">
		  <font color="red"><c:out value="${msg}"/>&nbsp;&nbsp;</font><br>
        </html-el:messages>
        <html-el:messages name="exceptionError" id="msg">
	      <font color="red"><c:out value="${msg}"/></font>&nbsp;&nbsp;<html-el:link page="/Feedback.do?exception=${msg}" target="_blank">Contact Us for Assistance</html-el:link>
	      <br>
        </html-el:messages>
        <font color="red"><html-el:errors property="hasErrors"/></font>
     </td>
	</tr>
</table>

  <c:if test="${!Rule2Form.editingDelegate}">
   <table width="95%" align="center">
	    <tr><td><c:out value="${Rule2Form.instructionForCreateNew}" /></td></tr>
   </table>
	<table width="95%" align="center" cellpadding=0 cellspacing=0 class="bord-r-t">
		<tr>
			<td width="40%" class="thnormal" align="right">*Rule Template:</td>			
			<td class="datacell">
			  <c:out value="${Rule2Form.ruleCreationValues.ruleTemplateName}" />
			  <html-el:hidden property="ruleCreationValues.ruleTemplateId" />
			  <html-el:hidden property="ruleCreationValues.ruleTemplateName"/>
     	  <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleTemplateLookupableImplService';"/>
			</td>
		</tr>
	    <tr>
		  <td class="thnormal" align="right">*Doc Type:</td>
		  <td class="datacell">
		    <html-el:hidden property="ruleCreationValues.docTypeName" />
		    <c:out value="${Rule2Form.ruleCreationValues.docTypeName}" />
        <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'DocumentTypeLookupableImplService';"/>
		  </td>
	    </tr>
		<tr>
		  <td class="thnormal" colspan="2" align="center">
		    <a href="javascript:post_to_action('Rule2.do', 'createNew')">Create a New Rule</a>
     </td>
		</tr>
	</table>
	</c:if>
	
<br>
<br>
	<!-- Rules -->
		
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td width="20"><img src="images/pixel_clear.gif" alt="" height="20" width="20"></td>
            <td>
            	<table border="0" cellpadding="0" cellspacing="0" width="100%">
              <c:forEach var="rule" items="${Rule2Form.myRules.rules}" varStatus="rStat">
              <c:set var="index" value="${rStat.index}"/>
              <c:set var="rule" value="${rule}" scope="request"/>
              <c:set var="ruleProperty" value="myRules.rule[${index}]" scope="request"/>
              <c:set var="ruleDelegation" value="${null}" scope="request"/>
  			  <c:set var="delegationProperty" value="${null}" scope="request"/>
  			  	<tr><td colspan=2>&nbsp;</td></tr>
  			  	<tr><td colspan=2>
              <jsp:include page="RuleDetails.jsp"/>
                    </td></tr>        
              <!-- **************************************************** -->                          
							<!-- ***************  Responsibilities  ***************** -->
							<!-- **************************************************** -->  
                       <tr>
                       	<td colspan=2>
                            <table width="100%" border=0 cellpadding=0 cellspacing=0 class="bord-r">
                          <tr><td style="width:20px">&nbsp;</td>
                            <c:url var="addNewRuleUrl" value="Rule2.do">
                              <c:param name="ruleIndex" value="${index}"/>
                            </c:url>
                            <td colspan=2 class="headercell7">Responsibilities - <a href="javascript:post_to_action('<c:out value="${addNewRuleUrl}"/>', 'addNewResponsibility')">Add New</a> 
                              <html-el:errors property="myRules.rule[${index}].responsibilities"/>
                            </td>
                          </tr>

                          <c:forEach var="responsibility" items="${rule.responsibilities}" varStatus="respStat">
                          <c:set var="respIndex" value="${respStat.index}"/>
                          <html-el:hidden property="myRules.rule[${index}].responsibility[${respIndex}].ruleResponsibilityKey" />
                          <html-el:hidden property="myRules.rule[${index}].responsibility[${respIndex}].numberOfDelegations" />
                          <html-el:hidden property="myRules.rule[${index}].responsibility[${respIndex}].responsibilityId" />	
                          <html-el:hidden property="myRules.rule[${index}].responsibility[${respIndex}].showDelegations" />	
                          <tr>
	                          <td style="width:20px">&nbsp;</td>
                            <td colspan=2 class="headercell7">
                            		Responsibility&nbsp;-&nbsp;
                               <c:if test="${rule.responsibilitiesSize > 1}">
												        	<a href='javascript:takeAction("Rule2.do", "removeResponsibility", <c:out value="${index}"/>, <c:out value="${respIndex}"/>)'>remove</a>
												        </c:if>
												        <c:if test="${rule.responsibilitiesSize > 1 && (!Rule2Form.editingDelegate && (responsibility.showDelegations || responsibility.numberOfDelegations <= Rule2Form.delegationLimit))}">
												         | 
												        </c:if>
												        <c:if test="${!Rule2Form.editingDelegate && (responsibility.showDelegations || responsibility.numberOfDelegations <= Rule2Form.delegationLimit)}">
												        	 <a href='javascript:takeAction("Rule2.do", "delegateResponsibility", <c:out value="${index}"/>, <c:out value="${respIndex}"/>)'>add delegate</a>
												        </c:if>
												        &nbsp;	 
                            </td>
                          </tr>

                          <tr><td style="width:20px">&nbsp;</td>
                            <td class="thnormal" align="right" width="20%">*Type:</td>
                            <td valign="middle" class="datacell">
                              <c:set var="responsibilityJs" value="javascript:responsibilityType(${index},${respIndex})"/>
                              <c:set var="respStyleId" value="rule${index}resp${respIndex}"/>
                              <html-el:radio styleId="${respStyleId}F" property="myRules.rule[${index}].responsibility[${respIndex}].ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" onclick="${responsibilityJs}"/>person&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <html-el:radio styleId="${respStyleId}W" property="myRules.rule[${index}].responsibility[${respIndex}].ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" onclick="${responsibilityJs}"/>workgroup&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <c:if test="${! empty Rule2Form.myRules.rules[index].roles }">
							    <html-el:radio styleId="${respStyleId}R" property="myRules.rule[${index}].responsibility[${respIndex}].ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_ROLE_ID}" onclick="${responsibilityJs}"/>role
							  </c:if>
							  <html-el:errors property="myRules.rule[${index}].responsibility[${respIndex}].ruleResponsibilityType"/>
							</td>
							</tr>
							<tr><td style="width:20px">&nbsp;</td>
														<td class="thnormal" align="right" width="20%">*Rule Reviewer:</td>
                            <td valign=middle class="datacell">
                            
                            <span id="<c:out value="${respStyleId}REV"/>" style='<c:out value="${responsibility.reviewerStyle}" />'>
							  <html-el:text property="myRules.rule[${index}].responsibility[${respIndex}].reviewer" />
          					</span>
					        <span id="<c:out value="${respStyleId}PL"/>" style='<c:out value="${responsibility.personLookupStyle}" />'>
					        			<c:set var="userConversion" value="myRules.rule[${index}].responsibility[${respIndex}].reviewer"/>
                              <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService';document.forms[0].elements['conversionFields'].value = 'networkId:${userConversion}';"/>
					        </span>
					        <span id="<c:out value="${respStyleId}WL"/>" style='<c:out value="${responsibility.workgroupLookupStyle}" />'>
					        					   <c:set var="workgroupConversion" value="myRules.rule[${index}].responsibility[${respIndex}].workgroupId"/>
                              <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'WorkGroupLookupableImplService';document.forms[0].elements['conversionFields'].value = 'workgroupId:${workgroupConversion}';"/>
					        </span>
  						    <html-el:errors property="myRules.rule[${index}].responsibility[${respIndex}].reviewer"/>
					        <span id="<c:out value="${respStyleId}RA"/>" style='<c:out value="${responsibility.roleAreaStyle}" />'>
    						<c:set var="roles" value="${Rule2Form.myRules.rules[index].roles}"/>
						      <c:if test="${! empty roles }">
							<html-el:select property="myRules.rule[${index}].responsibility[${respIndex}].roleReviewer">
								<html-el:options collection="roles" property="name" labelProperty="label" />
							</html-el:select>
							<br>
							<html-el:radio property="myRules.rule[${index}].responsibility[${respIndex}].approvePolicy" value="${Constants.APPROVE_POLICY_FIRST_APPROVE}">First Approve</html-el:radio><br>
							<html-el:radio property="myRules.rule[${index}].responsibility[${respIndex}].approvePolicy" value="${Constants.APPROVE_POLICY_ALL_APPROVE}">All Approve</html-el:radio>
							  </c:if>
							<html-el:errors property="myRules.rule[${index}].responsibility[${respIndex}].ruleResponsibilityName"/>
							<html-el:errors property="myRules.rule[${index}].responsibility[${respIndex}].approvePolicy"/>
							</span>
							</td>
              </tr>          
				        	<c:if test="${!Rule2Form.editingDelegate}">
				       <tr><td style="width:20px">&nbsp;</td>
				           <td class="thnormal" align="right" width="20%">*Action Request Code:</td>
				        	
							<td class="datacell">
							  <html-el:select property="myRules.rule[${index}].responsibility[${respIndex}].actionRequestedCd">
						        <c:set var="actionRequestCodes" value="${Rule2Form.actionRequestCodes}"/>
							    <html-el:options collection="actionRequestCodes" property="key" labelProperty="value" />
						      </html-el:select>
						      <html-el:errors property="myRules.rule[${index}].responsibility[${respIndex}].actionRequestedCd"/>
						    </td>
						   </tr><tr><td style="width:20px">&nbsp;</td>             
						    <td class="thnormal" align="right" width="20%">*Rule Priority:</td> 
						    <td class="datacell">
						    <html-el:select property="myRules.rule[${index}].responsibility[${respIndex}].priority">
							  <html-el:option value="1">1</html-el:option>
							  <html-el:option value="2">2</html-el:option>
							  <html-el:option value="3">3</html-el:option>
							  <html-el:option value="4">4</html-el:option>
							  <html-el:option value="5">5</html-el:option>
							  <html-el:option value="6">6</html-el:option>
							  <html-el:option value="7">7</html-el:option>
							  <html-el:option value="8">8</html-el:option>
							  <html-el:option value="9">9</html-el:option>
							  <html-el:option value="10">10</html-el:option>
							  <html-el:option value="11">11</html-el:option>								
						    </html-el:select>
  					        <html-el:errors property="myRules.rule[${index}].responsibility[${respIndex}].priority"/>
					        </td>
					        </tr>
					        </c:if>
                          
<!-- **************************************************** -->                          
<!-- ****************  Delegate Rules ******************* -->
<!-- **************************************************** -->    
                            <tr><td style="width:20px">&nbsp;</td>
                            	<td colspan=2>
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">                    
                          <c:choose>
                            <c:when test="${!responsibility.showDelegations && responsibility.numberOfDelegations > Rule2Form.delegationLimit}">
                            <tr>
                              <td style="width:20px">&nbsp;</td>
                              <td class="headercell7">
                              	This responsibility has <c:out value="${responsibility.numberOfDelegations}" /> delegations. - <a href="javascript:takeAction('Rule2.do', 'showDelegations', <c:out value="${index}"/>, <c:out value="${respIndex}"/>)">Show delegations</a>
                              </td>
                            </tr>
                            <tr>
	                            <td style="width:20px">&nbsp;</td>
                              <td class="thnormal">&nbsp;</td>
                            </tr>
                          </c:when>
						    <c:when test="${!empty responsibility.delegationRules}">
                            <html-el:hidden property="myRules.rule[${index}].responsibility[${respIndex}].delegationRulesMaterialized" value="true"/>
                          <tr><td style="width:20px">&nbsp;</td>
                          <td>
                              <table width="100%" border=0 cellpadding=0 cellspacing=0 class="bord-r">
                                <tr><td class="headercell7" colspan=2>Delegations</td></tr>
                    	        <c:forEach var="ruleDelegation" items="${responsibility.delegationRules}" varStatus="delStat">
                    	        <c:set var="delIndex" value="${delStat.index}"/>
                    	        <c:set var="templateConversion" value="myRules.rule[${index}].responsibility[${respIndex}].delegationRule[${delIndex}].delegationRuleBaseValues.ruleTemplateId"/>
                    	        <html-el:hidden property="${templateConversion}"/>
                    	        <c:set var="ruleProperty" value="myRules.rule[${index}].responsibility[${respIndex}].delegationRule[${delIndex}].delegationRuleBaseValues" scope="request"/>
                       	        <html-el:hidden property="${ruleProperty}.docTypeName"/>
                    	        <c:choose>
                    	          <c:when test="${ruleDelegation.delegationRuleBaseValues.ruleTemplateId == null}">
                                  <tr><td class="headercell7" colspan=2>Delegation Rule Template - &nbsp;&nbsp;<a href='javascript:takeIndexedAction("Rule2.do", "removeRule", "<c:out value="${ruleProperty}"/>")'>remove</a></td></tr>
                    	            <tr>
	                    	            <td class="thnormal" align="right" width="20%">Delegation Rule Template:</td>
  	                  	            <td class="datacell">
    	    	                        <html-el:hidden property="${ruleProperty}.delegateRule" />
      	              	          	<c:set var="templateConversion" value="${ruleProperty}.ruleTemplateId"/>
        	                           <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleTemplateLookupableImplService';document.forms[0].elements['conversionFields'].value = 'ruleTemplate.ruleTemplateId:${templateConversion}';"/>
            	                       <html-el:errors property="${ruleProperty}.ruleTemplateId"/>
              	                    </td>
                                  </tr>
                    	          </c:when>
                    	          <c:otherwise>
                    	            <c:set var="ruleDelegation" value="${ruleDelegation}" scope="request"/>
 									<c:set var="delegationProperty" value="myRules.rule[${index}].responsibility[${respIndex}].delegationRule[${delIndex}]" scope="request"/>                    	          
					                <c:set var="rule" value="${ruleDelegation.delegationRuleBaseValues}" scope="request"/>
					                <tr><td colspan=2>
              						<jsp:include page="RuleDetails.jsp"/>
              						</td></tr>
<!-- **************************************************** -->                          
<!-- ************  Delegate Responsibilities ************ -->
<!-- **************************************************** -->                        						
<tr><td colspan=2>                            
                        <table width="100%" border=0 cellpadding=0 cellspacing=0 class="bord-r">
                          <tr><td style="width:20px">&nbsp;</td>
                            <c:url var="addNewDelRespUrl" value="Rule2.do">
                              <c:param name="ruleIndex" value="${index}"/>
                              <c:param name="responsibilityIndex" value="${respIndex}"/>
                              <c:param name="delegationIndex" value="${delIndex}"/>
                            </c:url>
                            <td colspan=2 class="headercell7">Delegate Responsibilities - <a href="javascript:post_to_action('<c:out value="${addNewDelRespUrl}"/>', 'addNewResponsibility')">Add New</a></td>
                          </tr>
                          
                          <c:forEach var="responsibility" items="${ruleDelegation.delegationRuleBaseValues.responsibilities}" varStatus="delRespStat">
                          <c:set var="delRespIndex" value="${delRespStat.index}"/>
                          <c:set var="respProperty" value="myRules.rule[${index}].responsibility[${respIndex}].delegationRule[${delIndex}].delegationRuleBaseValues.responsibility[${delRespIndex}]"/>
                          <html-el:hidden property="${respProperty}.responsibilityId" />
                          <c:set var="roles" value="${ruleDelegation.delegationRuleBaseValues.roles}"/>
                          <tr>
	                          <td style="width:20px">&nbsp;</td>
                            <td colspan=2 class="headercell7">
                            	Delegate Responsibility
											          <c:if test="${ruleDelegation.delegationRuleBaseValues.responsibilitiesSize > 1}">
											        		&nbsp;-&nbsp;<a href='javascript:takeAction("Rule2.do", "removeResponsibility", <c:out value="${index}"/>, <c:out value="${respIndex}"/>, <c:out value="${delIndex}"/>, <c:out value="${delRespIndex}"/>)'>remove</a>
											          </c:if>
                            </td>
                          </tr>

                          <tr><td style="width:20px">&nbsp;</td>
	                          <td class="thnormal" align="right" width="20%">*Type:</td>
                            <td valign="middle" class="datacell">
                              <c:set var="responsibilityJs" value="javascript:responsibilityType(${index},${respIndex},${delIndex},${delRespIndex})"/>
                              <c:set var="respStyleId" value="rule${index}resp${respIndex}delRule${delIndex}delResp${delRespIndex}"/>
                              <html-el:radio styleId="${respStyleId}F" property="${respProperty}.ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" onclick="${responsibilityJs}"/>person&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <html-el:radio styleId="${respStyleId}W" property="${respProperty}.ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" onclick="${responsibilityJs}"/>workgroup&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <c:if test="${! empty roles }">
							    <html-el:radio styleId="${respStyleId}R" property="${respProperty}.ruleResponsibilityType" value="${Constants.RULE_RESPONSIBILITY_ROLE_ID}" onclick="${responsibilityJs}"/>role
							  </c:if>
							  <html-el:errors property="${respProperty}.ruleResponsibilityType"/>
							</td>
							</tr><tr><td style="width:20px">&nbsp;</td>
							<td class="thnormal" align="right" width="20%">*Rule Reviewer:</td>
                            <td valign=middle class="datacell">
                            
                            <span id="<c:out value="${respStyleId}REV"/>" style='<c:out value="${responsibility.reviewerStyle}" />'>
							  <html-el:text property="${respProperty}.reviewer" />
          					</span>
					        <span id="<c:out value="${respStyleId}PL"/>" style='<c:out value="${responsibility.personLookupStyle}" />'>
					        			<c:set var="userConversion" value="${respProperty}.reviewer"/>
                              <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService';document.forms[0].elements['conversionFields'].value = 'networkId:${userConversion}';"/>
					        </span>
					        <span id="<c:out value="${respStyleId}WL"/>" style='<c:out value="${responsibility.workgroupLookupStyle}" />'>
					        					   <c:set var="workgroupConversion" value="${respProperty}.workgroupId"/>
                              <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'WorkGroupLookupableImplService';document.forms[0].elements['conversionFields'].value = 'workgroupId:${workgroupConversion}';"/>
					        </span>
   						    <html-el:errors property="${respProperty}.reviewer"/>
					        <span id="<c:out value="${respStyleId}RA"/>" style='<c:out value="${responsibility.roleAreaStyle}" />'>
    						
						      <c:if test="${! empty roles }">
							<html-el:select property="${respProperty}.roleReviewer">
								<html-el:options collection="roles" property="name" labelProperty="label" />
							</html-el:select><br>
							<html-el:radio property="${respProperty}.approvePolicy" value="${Constants.APPROVE_POLICY_FIRST_APPROVE}">First Approve</html-el:radio><br>
							<html-el:radio property="${respProperty}.approvePolicy" value="${Constants.APPROVE_POLICY_ALL_APPROVE}">All Approve</html-el:radio>
							  </c:if>
							  <html-el:errors property="${respProperty}.ruleResponsibilityName"/>
							  <html-el:errors property="${respProperty}.approvePolicy"/>
							</span>
							</td>
					      </tr>
					      </c:forEach>
					    </table>
					 </td>
<!-- **************************************************** -->                          
<!-- ********** END Delegate Responsibilities *********** -->
<!-- **************************************************** -->                        						

					

                          </tr>                  	          
                    	          </c:otherwise>
                    	        </c:choose>
                    	        
                    	        
                                </c:forEach>
                              </table>
                            </td>
                          </tr>
                          </c:when>
                        </c:choose>
                        </table>
                        </td>
                        </tr>
                        
                            
                          </c:forEach>
                          </table>
                  </td>
                </tr>
              </c:forEach>
              </table>
                  </td>
                </tr>
              </table>
             
          <c:if test="${!empty Rule2Form.myRules.rules}">
          <br><br>
					<table width="100%" border=0 cellpadding=0 cellspacing=0 class="bord-r-t" >
          <tr>
            <td class="thnormal" align="center" colspan="6">
              <a href="javascript:post_to_action('Rule2.do', 'save')"><img src="images/buttonsmall_route.gif" alt="route" align=absmiddle></a>
	        </td>
	      </tr>
        </table>
	      </c:if>

</html-el:form>
</body>
</html>
