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
  showHide - reference to the ShowHideTree for this panel.
  showHideProperty - property value of the ShowHideTree for this panel
--%>

      <c:set var="ruleIdVal" value="${extraId}z${ruleIndex}"/>
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
              <div class="spacercell"><c:out value="${rule.description}"/></div>
              <div id="<c:out value="G${ruleIdVal}"/>" <c:if test="${!showHide.children[ruleIndex].show}">style="display:none"</c:if>>
                <%--<table border=0 cellpadding=0 cellspacing=0 width="100%">
                  <tbody>
                    <tr>
                      <td class="spacercell" align=right>&nbsp;</td>
                    </tr>
                  </tbody>
                </table>--%>
                <table width="100%" border=0 align=center cellpadding=0 cellspacing=0 class="bord-r-t">
                  <tr>
                    <td colspan=3 class="headercell7">General Info</td>
                  </tr>
                  <c:set var="rule" value="${rule}" scope="request"/>
                  <c:set var="topLevelRule" value="${rule}"/>
                  <c:set var="parentRuleId" value="${rule.ruleBaseValuesId}" scope="request"/>
                  <c:set var="ruleDelegation" value="${null}" scope="request"/>
                  <jsp:include page="RuleData.jsp"/>
                </table>
                
                <%-- Responsibilities --%>
                <c:forEach var="responsibility" items="${rule.responsibilities}" varStatus="status">
                  <c:set var="respIndex" value="${status.index}" scope="request"/>
                  <c:set var="respIdVal" value="${ruleIdVal}z${respIndex}"/>
                  <c:set var="delKey" value="${extraId}rule${ruleIndex}resp${respIndex}"/>
                  <% edu.iu.uis.eden.routetemplate.web.Rule2Form ruleForm = (edu.iu.uis.eden.routetemplate.web.Rule2Form)request.getAttribute("Rule2Form");
                     String displayDelegationsValue = (String)ruleForm.getShowDelegationsMap().get(pageContext.getAttribute("delKey"));
                     if (displayDelegationsValue == null) displayDelegationsValue = "true";
                     pageContext.setAttribute("displayDelegationsValue", displayDelegationsValue);
                  %>
                  <c:set var="displayDelegationRules" value="${displayDelegationsValue || responsibility.numberOfDelegations <= Rule2Form.delegationLimit}"/>
                  <html-el:hidden property="showDelegationsMap(${extraId}rule${ruleIndex}resp${respIndex})" />
                  <table class="bord-r-t" border=0 cellpadding=0 cellspacing=0 width="100%">
                    <tbody>
                      <tr>
                        <td colspan=5 class="headercell7"><a id="<c:out value="A${respIdVal}"/>" onclick="rend(this, false)">
                          <c:import url="ShowHideButton.jsp">
                            <c:param name="idVal" value="${respIdVal}"/>
                          </c:import>
                          <strong>Responsibility</strong>
                        </td>
                      </tr>
                      <tr>
                        <td colspan=5 align=right height=0>
                          <div id="<c:out value="G${respIdVal}"/>" <c:if test="${!showHide.children[ruleIndex].children[respIndex].show}">style="display:none"</c:if>>
                            <c:set var="responsibility" value="${responsibility}" scope="request"/>
                            <jsp:include page="ResponsibilityData.jsp"/>
                  
                        <c:if test="${!displayDelegationRules}">
                          <tr>
                            <td width="20%" align=right class="thnormal">Delegations:</td>
                            <td width="30%" class="datacell">
                              This responsibility has <c:out value="${responsibility.numberOfDelegations}" /> delegations. - <a href="javascript:takeAction('<c:out value="${actionName}"/>', '<c:out value="${showDelegationsMethod}"/>', <c:out value="${ruleIndex}"/>, <c:out value="${respIndex}"/>, null, null, <c:out value="'${extraId}'" default="null" escapeXml="false"/>)">Show delegations</a>
                            </td>
                          </tr>
                          
                        </c:if>
                  
                  <%-- Delegate Rules --%>
                  <c:if test="${displayDelegationRules}">
                  <c:forEach var="ruleDelegation" items="${responsibility.delegationRules}" varStatus="status">
                    <c:set var="delIndex" value="${status.index}" scope="request"/>
                    <c:set var="delIdVal" value="${respIdVal}z${delIndex}"/>
                    <table border=0 cellpadding=0 cellspacing=0 width="100%">
                      <tbody>
                        <tr>
                          <td>
                            <table width="100%" border=0 cellpadding=0 cellspacing=0>
                              <tbody>
                                <tr>
                                  <td width=60 align=right class="thnormal">
                                    <c:import url="ShowHideButton.jsp">
                                      <c:param name="idVal" value="${delIdVal}"/>
                                    </c:import>
                                  </td>
                                  <td class="headercell3-b-l"><strong>Rule Delegation </strong></td>
                                </tr>
                                <tr id="<c:out value="G${delIdVal}"/>" <c:if test="${!showHide.children[ruleIndex].children[respIndex].children[delIndex].show}">style="display:none"</c:if>>
                                  <td width=60 align=right class="thnormal"><img src="images/pixel_clear.gif" height=20 width=60></td>
                                  <td>
                                    <table border=0 cellpadding=0 cellspacing=0 width="100%">
                                      <tbody>
                                        <c:set var="rule" value="${ruleDelegation.delegationRuleBaseValues}" scope="request"/>
                                        <c:set var="ruleDelegation" value="${ruleDelegation}" scope="request"/>
                                        <jsp:include page="RuleData.jsp"/>
                                        
                                        <%-- Delegate Responsibilities --%>
                                        <c:forEach var="responsibility" items="${rule.responsibilities}" varStatus="status">
                                          <c:set var="delRespIndex" value="${status.index}" scope="request"/>
                                          <c:set var="delRespIdVal" value="${delIdVal}z${delRespIndex}"/>
                                        <tr>
                                          <td colspan=3>
                                            <table border=0 cellpadding=0 cellspacing=0 width="100%">
                                              <tbody>
                                                <tr>
                                                  <td class="thnormal" align=right width=60>
                                                    <c:import url="ShowHideButton.jsp">
							                          <c:param name="idVal" value="${delRespIdVal}"/>
								                    </c:import>
								                  </td>
                                                  <td class="headercell3-b-l"><strong>Rule Responsibility</strong></td>
                                                </tr>
                                                <tr id="<c:out value="G${delRespIdVal}"/>" <c:if test="${!showHide.children[ruleIndex].children[respIndex].children[delIndex].children[delRespIndex].show}">style="display:none"</c:if>>
                                                  <td width=60 align=right class="thnormal"><img src="images/pixel_clear.gif" height=20 width=60></td>
                                                  <td>
                                                    <c:set var="responsibility" value="${responsibility}" scope="request"/>
                                                    <jsp:include page="ResponsibilityData.jsp"/>
                                                  </td>
                                                </tr>
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                        </c:forEach> <%-- END delegate responsibilities --%>
                                        <c:set var="delRespIndex" value="${null}" scope="request"/>
                                        
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
                  </c:forEach> <%-- END delegate rules --%>
                  <c:set var="delIndex" value="${null}" scope="request"/>
                  </c:if>
                  
                  </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </c:forEach> <%-- END responsibilities --%>
                <c:set var="respIndex" value="${null}" scope="request"/>                
                
              </div>
              <table border="0" cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                  <tr>
                  <%-- show the edit button on the rule panel if it has been indicated and we are looking at the current version --%>
                    <c:choose>
                      
                      <c:when test="${(showPanelEditButton and topLevelRule.currentInd) or showPanelCopyButton}">
                        <td class="spacercell" style="text-align:center;padding-top:10px">
                          <c:if test="${showPanelEditButton and topLevelRule.currentInd}">                      
                            <html-el:image property="methodToCall.edit" src="${resourcePath}images/buttonsmall_edit.gif" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;
                          </c:if>
                          <c:if test="${showPanelCopyButton}">
                            <a href="javascript:takeRuleAction('Rule.do', 'copy', '<c:out value="${topLevelRule.ruleBaseValuesId}"/>')">
                              <img src="<c:out value="${resourcePath}images/buttonsmall_copytonew.gif"/>" style="text-decoration: none" align="absmiddle"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:takeRuleAction('Rule.do', 'export', '<c:out value="${topLevelRule.ruleBaseValuesId}"/>')">
                              <img src="<c:out value="${resourcePath}images/buttonsmall_export.gif"/>" style="text-decoration: none" align="absmiddle"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                          </c:if>
                        </td>
                      </c:when>
                      <c:otherwise>
	                    <td class="spacercell">&nbsp;</td>
                      </c:otherwise>
                    </c:choose>
                  </tr>
                </tbody>
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
                
                
                