<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<%-- 
  Displays a tabbed panel for a Rule.
  
  Parameters (request scope):
  rule - the rule to render
  title - the title to display within the tab
--%>

      <c:set var="ruleIdVal" value="${extraId}z${ruleIndex}"/>
      <c:set var="ruleProperty" value="myRules.rule[${ruleIndex}]"/>
            
      <table border=0 cellpadding=0 cellspacing=0 width="100%">
        <tr>
          <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><c:out value="${rule.ruleInstructions}" /></strong></td>
		</tr>
        <tr>
          <td >&nbsp;</td>
		</tr>
	  </table>            
            
      <table border=0 cellpadding=0 cellspacing=0 width="100%">
        <tbody>
          <tr>
            <td width=12><img src="images/tab-topleft.gif" alt="" height=29 width=12></td>
            <td background="images/tab-back.gif" nowrap=nowrap width=200>
              <table width="100%" border=0 cellspacing=0 cellpadding=0>
                <tr>
                  <td nowrap class="tabtitle"><c:out value="${title}" default="Rule"/></td>
                  <td width=100 align=right nowrap>
                    <c:import url="ShowHideButton.jsp">
                      <c:param name="idVal" value="${ruleIdVal}"/>
                    </c:import>
                  </td>
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
              <div id="<c:out value="G${ruleIdVal}"/>" <c:choose><c:when test="${!showHide.children[ruleIndex].show}">style="display:none"</c:when></c:choose>>
                <table border=0 cellpadding=0 cellspacing=0 width="100%">
                  <tbody>
                    <tr>
                      <td class="spacercell" align=right>&nbsp;</td>
                    </tr>
                  </tbody>
                </table>
                
                
                <table width="100%" border=0 align=center cellpadding=0 cellspacing=0 class="bord-r-t">
                  <tr>
                    <td colspan=3 class="headercell7">
                      General Info&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <c:set var="drawBar" value="${false}"/>
                      <c:if test="${!Rule2Form.editingDelegate}">
                        <c:set var="removeActionName" value="${actionName}#rule${ruleIndex-1}"/>
                        <a href='javascript:takeIndexedAction("<c:out value="${removeActionName}" />", "removeRule", "<c:out value="${ruleProperty}"/>")'>remove rule</a>
                        <c:set var="drawBar" value="${true}"/>
                      </c:if>
                      <c:if test="${drawBar}"> | </c:if>
                      <c:set var="addActionName" value="${actionName}#rule${ruleIndex}resp${rule.responsibilitiesSize}"/>
                      <a href='javascript:takeIndexedAction("<c:out value="${addActionName}" />", "addNewResponsibility", "<c:out value="${ruleProperty}"/>")'>add responsibility</a>
											<c:if test="${!Rule2Form.editingDelegate}">
												&nbsp;|&nbsp;                      
	                      <c:set var="copyActionName" value="${actionName}#rule${Rule2Form.myRules.size}"/>
	                      <a href='javascript:takeIndexedAction("<c:out value="${copyActionName}" />", "copyRule", "<c:out value="${ruleProperty}"/>")'>copy this rule</a>
                    	</c:if>
                    </td>
                  </tr>
                  <c:import url="RuleDataEdit.jsp">
                    <c:param name="rule" value="${rule}"/>
                    <c:param name="ruleProperty" value="myRules.rule[${ruleIndex}]"/>
                    <c:param name="delegationProperty" value="${delegationProperty}"/>
                  </c:import>
                </table>
                
<%------------- Responsibilities -------------%>
				<a name="<c:out value="rule${ruleIndex}resp-1"/>"></a>

                <c:forEach var="responsibility" items="${rule.responsibilities}" varStatus="status">
                
                  <c:set var="respIndex" value="${status.index}" scope="request"/>
                  <c:set var="respIdVal" value="${ruleIdVal}z${respIndex}"/>
                  <c:set var="responsibilityProperty" value="${ruleProperty}.responsibility[${respIndex}]"/>
                  <c:set var="responsibility" value="${responsibility}" scope="request"/>
                  <c:set var="displayDelegationRules" value="${responsibility.showDelegations || responsibility.numberOfDelegations <= Rule2Form.delegationLimit}"/>
                  
                  <table class="bord-r-t" border=0 cellpadding=0 cellspacing=0 width="100%">
                    <tbody>
                      <tr>
                        <td colspan=5 class="headercell7">
                          <c:import url="ShowHideButton.jsp">
                            <c:param name="idVal" value="${respIdVal}"/>
                          </c:import>
                          <%--
                        <a id="<c:out value="A${respIdVal}"/>" onclick="rend(this, false)">
                          <img src="images/tinybutton-show.gif" alt="show" id="<c:out value="F${respIdVal}"/>" align=absmiddle border=0 height=15 width=45></a>--%>
                          <strong>
                            Responsibility
                          </strong>
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          <c:set var="drawBar" value="${false}"/>
                           <c:if test="${rule.responsibilitiesSize > 1}">
                             <c:set var="removeActionName" value="${actionName}#rule${ruleIndex}resp${respIndex-1}"/>
						     <a href='javascript:takeAction("<c:out value="${removeActionName}"/>", "removeResponsibility", <c:out value="${ruleIndex}"/>, <c:out value="${respIndex}"/>)'>remove</a>
						     <c:set var="drawBar" value="${true}"/>
						   </c:if>
						   <c:if test="${!Rule2Form.editingDelegate && (responsibility.showDelegations || responsibility.numberOfDelegations <= Rule2Form.delegationLimit)}">
					       <c:if test="${responsibility.hasDelegateRuleTemplate}">
							     <c:if test="${drawBar}"> | </c:if>
							       <c:set var="addActionName" value="${actionName}#rule${ruleIndex}resp${respIndex}del${responsibility.numberOfDelegations}"/>
						    	   <a href='javascript:takeAction("<c:out value="${addActionName}"/>", "delegateResponsibility", <c:out value="${ruleIndex}"/>, <c:out value="${respIndex}"/>)'>add delegate</a>
						     	 </c:if>
						     <c:set var="drawBar" value="${true}"/>
						   </c:if>
                           <c:if test="${!displayDelegationRules}">
                             <c:if test="${drawBar}"> | </c:if>
                             This responsibility has <c:out value="${responsibility.numberOfDelegations}" /> delegations. - <a href="javascript:takeAction('<c:out value="${actionName}"/>', 'showDelegations', <c:out value="${ruleIndex}"/>, <c:out value="${respIndex}"/>)">Show delegations</a>
                           </c:if>
                        </td>
                      </tr>
                      <tr>
                        <td colspan=5 align=right height=0>
                          <div id="<c:out value="G${respIdVal}"/>" <c:choose><c:when test="${!showHide.children[ruleIndex].children[respIndex].show}">style="display:none"</c:when></c:choose>>
                            <c:import url="ResponsibilityDataEdit.jsp">
                              <c:param name="isDelegateResponsibility" value="${false}"/>
                              <c:param name="responsibilityProperty" value="${responsibilityProperty}"/>
                            </c:import>
                  
<%--------------- Delegate Rules -------------%>
				  <a name="<c:out value="rule${ruleIndex}resp${respIndex}del-1"/>"></a>
                  <c:set var="currentRule" value="${rule}"/>
                  <c:if test="${displayDelegationRules}">
                  <c:forEach var="ruleDelegation" items="${responsibility.delegationRules}" varStatus="status">
                  
                    <%-- Delegate Rule Variables --%>
                    <c:set var="delIndex" value="${status.index}" scope="request"/>
                    <c:set var="delIdVal" value="${respIdVal}z${delIndex}"/>
                    <c:set var="ruleDelegation" value="${ruleDelegation}" scope="request"/>
                    <c:set var="rule" value="${ruleDelegation.delegationRuleBaseValues}" scope="request"/>
                    <c:set var="delegationProperty" value="${responsibilityProperty}.delegationRule[${delIndex}]"/>
                    <c:set var="delegationRuleProperty" value="${delegationProperty}.delegationRuleBaseValues"/>

                    <table border=0 cellpadding=0 cellspacing=0 width="100%">
                      <tbody>
                        <tr>
                          <td>
                            <table width="100%" border=0 cellpadding=0 cellspacing=0>
                              <tbody>
                                <tr>
                                  <td width=60 align=right class="thnormal">
                                  <%--<a id="<c:out value="A${delIdVal}"/>" onclick="rend(this, false)"><img src="images/tinybutton-show.gif" alt="show" id="<c:out value="F${delIdVal}"/>" align=middle border=0 height=15 width=45></a>--%>
                                    <c:import url="ShowHideButton.jsp">
                                      <c:param name="idVal" value="${delIdVal}"/>
                                    </c:import>    
                                  </td>
                                  <td class="headercell3-b-l">
                                    <strong>Rule Delegation </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <c:set var="drawBar" value="${false}"/>
                                    <c:if test="${!Rule2Form.editingDelegate}">
                                      <c:set var="removeActionName" value="${actionName}#rule${ruleIndex}resp${respIndex}del${delIndex-1}"/>
                                      <a href='javascript:takeIndexedAction("<c:out value="${removeActionName}" />", "removeRule", "<c:out value="${delegationRuleProperty}"/>")'>remove delegate rule</a>
                                      <c:set var="drawBar" value="${true}"/>
                                    </c:if>
                                    <c:if test="${ruleDelegation.delegationRuleBaseValues.ruleTemplateId != null}">
                                      <c:if test="${drawBar}"> | </c:if>
                                      <c:set var="addActionName" value="${actionName}#rule${ruleIndex}resp${respIndex}del${delIndex}delResp${rule.responsibilitiesSize}"/>
                                      <a href='javascript:takeIndexedAction("<c:out value="${addActionName}" />", "addNewResponsibility", "<c:out value="${delegationRuleProperty}"/>")'>add delegate responsibility</a>
                                    </c:if>
                                  </td>
                                </tr>
                                <tr id="<c:out value="G${delIdVal}"/>" <c:choose><c:when test="${!showHide.children[ruleIndex].children[respIndex].children[delIndex].show}">style="display:none"</c:when></c:choose>>
                                  <td width=60 align=right class="thnormal"><img src="images/pixel_clear.gif" height=20 width=60></td>
                                  <td>
                  
                                    <table border=0 cellpadding=0 cellspacing=0 width="100%">
                                      <tbody>
                                        <%--<c:choose>
                                          <c:when test="${ruleDelegation.delegationRuleBaseValues.ruleTemplateId == null}">
                                            <tr>
	                    	                  <td width="20%" class="thnormal" align="right">Choose Delegation Rule Template:</td>
            	                  	          <td width="30%" class="datacell">
    	    	                                <html-el:hidden property="${delegationRuleProperty}.delegateRule" />
      	              	          	            <c:set var="templateConversion" value="${delegationRuleProperty}.ruleTemplateId"/>
        	                                    <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'RuleTemplateLookupableImplService';document.forms[0].elements['conversionFields'].value = 'ruleTemplate.ruleTemplateId:${templateConversion}';"/>
            	                                <div class="error-message"><html-el:errors property="${delegationRuleProperty}.ruleTemplateId"/></div>
              	                              </td>
                                            </tr>
                                            
                    	                  </c:when>
                    	                  <c:otherwise>--%>
                                          
                                          <c:import url="RuleDataEdit.jsp">
                    						<c:param name="ruleProperty" value="${delegationRuleProperty}"/>
                    						<c:param name="delegationProperty" value="${delegationProperty}"/>
                                          </c:import>
                                      
<%------------------------------------- Delegate Responsibilities --------------------------------------%>
					   			      <a name="<c:out value="rule${ruleIndex}resp${respIndex}del${delIndex}delResp-1"/>"></a>
									  <c:if test="${ruleDelegation.delegationRuleBaseValues.ruleTemplateId == null}">
									    <%-- FIXME: this is kind of weird, there should be a better way to do it --%>
									    <html-el:hidden property="${showHideProperty}.child[${ruleIndex}].child[${respIndex}].child[${delIndex}].child[0].show"/>
									  </c:if>
									  <c:if test="${ruleDelegation.delegationRuleBaseValues.ruleTemplateId != null}">
                                        <c:forEach var="delegateResponsibility" items="${ruleDelegation.delegationRuleBaseValues.responsibilities}" varStatus="status">
                                        
                                          <c:set var="delRespIndex" value="${status.index}" scope="request"/>
                                          <c:set var="delRespIdVal" value="${delIdVal}z${delRespIndex}"/>
                                          <c:set var="delegateResponsibilityProperty" value="${delegationProperty}.delegationRuleBaseValues.responsibility[${delRespIndex}]"/>
                                          <c:set var="responsibility" value="${delegateResponsibility}" scope="request"/>
                                          
                                        <tr>
                                          <td colspan=3>
                                            <table border=0 cellpadding=0 cellspacing=0 width="100%">
                                              <tbody>
                                                <tr>
                                                  <td class="thnormal" align=right width=60><c:import url="ShowHideButton.jsp"><c:param name="idVal" value="${delRespIdVal}"/></c:import></td>
                                                  <td class="headercell3-b-l"><strong>Delegate Rule Responsibility</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						                            <c:if test="${rule.responsibilitiesSize > 1}">
						                              <c:set var="removeActionName" value="${actionName}#rule${ruleIndex}resp${respIndex}del${delIndex}delResp${delRespIndex-1}"/>
												      <a href='javascript:takeAction("<c:out value="${removeActionName}"/>", "removeResponsibility", <c:out value="${ruleIndex}"/>, <c:out value="${respIndex}"/>, <c:out value="${delIndex}"/>, <c:out value="${delRespIndex}"/>)'>remove</a>
						   							</c:if>
                                                  </td>
                                                </tr>
                                                <tr id="<c:out value="G${delRespIdVal}"/>" <c:choose><c:when test="${!showHide.children[ruleIndex].children[respIndex].children[delIndex].children[delRespIndex].show}">style="display:none"</c:when></c:choose>>
                                                  <td width=60 align=right class="thnormal"><img src="images/pixel_clear.gif" height=20 width=60></td>
                                                  <td>
                                                    <c:import url="ResponsibilityDataEdit.jsp">
                                                      <c:param name="isDelegateResponsibility" value="true"/>
                                                      <c:param name="responsibilityProperty" value="${delegateResponsibilityProperty}"/>
                                                    </c:import>
                                                  </td>
                                                </tr>
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                        </c:forEach> 
                                        <c:set var="delRespIndex" value="${null}" scope="request"/>
                                      </c:if>
<%------------------------------------- END delegate responsibilities -------------------------------------%>
                                        
                                        
                                        <%--</c:otherwise>
                                    </c:choose>--%>
                                               
                                    </tbody>
                                  </table>
                                      
                                  </td>
                                </tr>
                              </tbody>
                            </table>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </c:forEach> 
  				  </c:if>
                  <c:set var="delIndex" value="${null}" scope="request"/>
                  <c:set var="rule" value="${currentRule}" scope="request"/>
<%--------------- END delegate rules ---------------%>
                  
                  </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </c:forEach>
                <c:set var="respIndex" value="${null}" scope="request"/>

 <%------------- END responsibilities ------------%>
                

              <table border="0" cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                  <tr>
                    <td class="spacercell">&nbsp;</td>
                  </tr>
                </tbody>
              </table>
              
			        <table width="100%" border=0 align=center cellpadding=0 cellspacing=0 class="bord-r-t">
                  <tr>
                    <td colspan=2 class="headercell7">
                      <c:set var="drawBar" value="${false}"/>
                      <c:if test="${!Rule2Form.editingDelegate}">
                        <c:set var="removeActionName" value="${actionName}#rule${ruleIndex-1}"/>
                        <a href='javascript:takeIndexedAction("<c:out value="${removeActionName}" />", "removeRule", "<c:out value="${ruleProperty}"/>")'>remove rule</a>
                        <c:set var="drawBar" value="${true}"/>
                      </c:if>
                      <c:if test="${drawBar}"> | </c:if>
                      <c:set var="addActionName" value="${actionName}#rule${ruleIndex}resp${rule.responsibilitiesSize}"/>
                      <a href='javascript:takeIndexedAction("<c:out value="${addActionName}" />", "addNewResponsibility", "<c:out value="${ruleProperty}"/>")'>add responsibility</a>
											<c:if test="${!Rule2Form.editingDelegate}">
												&nbsp;|&nbsp;                      
	                      <c:set var="copyActionName" value="${actionName}#rule${ruleIndex-1}"/>
	                      <a href='javascript:takeIndexedAction("<c:out value="${copyActionName}" />", "copyRule", "<c:out value="${ruleProperty}"/>")'>copy this rule</a>
                      </c:if>
                    </td>
                  </tr>
              </table>
              </div>              
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
                
                
                