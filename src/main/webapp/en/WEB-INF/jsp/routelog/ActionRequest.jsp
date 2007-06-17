<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:choose>
  <c:when test="${actionRequest.approvePolicy == \"A\"}">
    <c:set var="approvePolicy" value="ALL "/>
  </c:when>
  <c:when test="${actionRequest.approvePolicy == \"F\"}">
    <c:set var="approvePolicy" value="FIRST "/>
  </c:when>
</c:choose>

<c:choose>
  <c:when test="${actionRequest.parentActionRequest != null && actionRequest.parentActionRequest.approvePolicy == null}">
    <c:choose>
      <c:when test="${actionRequest.delegationType == \"S\"}">
        <c:set var="delegation" value="Secondary Delegate"/>
      </c:when>
      <c:when test="${actionRequest.delegationType == \"P\"}">
  	    <c:set var="delegation" value="Primary Delegate"/>
      </c:when>
    </c:choose>
  </c:when>
</c:choose>

<c:choose>
  <c:when test="${level == 0}">
    <c:set var="headerClass" value="headercell4"/>
  </c:when>
  <c:otherwise>
  
    <c:set var="headerClass" value="headercell3-b-l"/>
  </c:otherwise>
</c:choose>
 		                                 <tr>

 		                                <td align="center" align="right" class="<c:out value="${headerClass}"/>">
		                                 	<a id="A<c:out value="${index}" />" onclick="rend(this, false)"><img src="images/tinybutton-show.gif" alt="show" width=45 height=15 border=0 align=absmiddle id="F<c:out value="${index}" />"></a> 
		                                </td>
			                           
			                           <td align="center" class="<c:out value="${headerClass}"/>">
			                             <b><c:out value="${actionRequest.displayStatus}" />
			                             	<c:if test="${actionRequest.displayStatus != null}">
												<br>
											</c:if>
											<c:out value="${actionRequest.actionRequestedLabel}" />
			                           </td>
			                           
			                           <td align="left" class="<c:out value="${headerClass}"/>">
		                              	<c:choose>
		                              		<c:when test="${actionRequest.userRequest}">
	                          					<a style="color:white" href="
													<c:url value="${UrlResolver.userReportUrl}">
														<c:param name="workflowId" value="${actionRequest.workflowId}" />
														<c:param name="methodToCall" value="report" />
														<c:param name="showEdit" value="no" />
													</c:url>"><c:out value="${actionRequest.workflowUser.displayName}" />
												</a>
												<c:if test="${delegation != null}">
													&nbsp;<c:out value="${delegation}" />
												</c:if>
		                              		</c:when>
			                              	<c:when test="${actionRequest.workgroupRequest}">
			                              		<a style="color:white" href="
													<c:url value="${UrlResolver.workgroupReportUrl}">
														<c:param name="workgroupId" value="${actionRequest.workgroupId}" />
														<c:param name="methodToCall" value="report" />
													</c:url>"><c:out value="${actionRequest.workgroup.groupNameId.nameId}" />
												</a>
												<c:if test="${delegation != null}">
													&nbsp;<c:out value="${delegation}" />
												</c:if>
		                              		</c:when>
		                              		<c:otherwise>
									              <c:forEach var="roleRequest" items="${actionRequest.childrenRequests}" varStatus="arStatus">
										              <c:choose>
										              	 <c:when test="${roleRequest.primaryDelegator}">
										              	 	<c:forEach var="primDelegateRequest" items="${roleRequest.primaryDelegateRequests}" varStatus="pDelegateArStatus">
										              	 	<c:if test="${primDelegateRequest.userRequest}">
												              	 <a style="color:white" href="
																		<c:url value="${UrlResolver.userReportUrl}">
																			<c:param name="workflowId" value="${primDelegateRequest.workflowId}" />
																			<c:param name="methodToCall" value="report" />
																			<c:param name="showEdit" value="no" />
																		</c:url>">
																		<c:out value="${primDelegateRequest.workflowUser.displayName}" />
																	</a>
															</c:if>
							                              	<c:if test="${primDelegateRequest.workgroupRequest}">
							                              		<a style="color:white" href="
																	<c:url value="${UrlResolver.workgroupReportUrl}">
																		<c:param name="workgroupId" value="${primDelegateRequest.workgroupId}" />
																		<c:param name="methodToCall" value="report" />
																	</c:url>">
																	<c:out value="${primDelegateRequest.workgroup.groupNameId.nameId}" />
																</a>
   						                              		</c:if>
																	  <c:if test="${!empty primDelegateRequest.qualifiedRoleNameLabel}">
																	  	&nbsp;(<c:out value="${primDelegateRequest.qualifiedRoleNameLabel}" />)
																	  </c:if>
										                      	 <c:if test="${!pDelegateArStatus.last}"><br></c:if>
									                      	</c:forEach>
										              	 </c:when>
										              	 <c:when test="${roleRequest.workgroupRequest}">
										              	   <a style="color:white" href="
															 <c:url value="${UrlResolver.workgroupReportUrl}">
																<c:param name="workgroupId" value="${roleRequest.workgroupId}" />
																		<c:param name="methodToCall" value="report" />
																	</c:url>">
																	<c:out value="${roleRequest.workgroup.groupNameId.nameId}" />
																</a>&nbsp;(<c:out value="${actionRequest.qualifiedRoleNameLabel}" />)
																<c:if test="${!arStatus.last}"><br></c:if>
										              	 </c:when>
    										             <c:otherwise>
											              	 <a style="color:white" href="
																	<c:url value="${UrlResolver.userReportUrl}">
																		<c:param name="workflowId" value="${roleRequest.workflowId}" />
																		<c:param name="methodToCall" value="report" />
																		<c:param name="showEdit" value="no" />
																	</c:url>"><c:out value="${roleRequest.workflowUser.displayName}" />
																</a>&nbsp;(<c:out value="${actionRequest.qualifiedRoleNameLabel}" />)
																<c:if test="${!arStatus.last}"><br></c:if>									                      	 
								                      	 </c:otherwise>
								                      </c:choose>
							                      </c:forEach>
							                    
		                              		</c:otherwise>
		                              	</c:choose>
		                              </td>
		                              <td align="center" class="<c:out value="${headerClass}"/>">
		                              
		                              	<b>&nbsp;<fmt:formatDate value="${actionRequest.createDate}" pattern="${Constants.DEFAULT_DATE_FORMAT_PATTERN}" /></b>
		                              </td>
		                              <td align="left" class="<c:out value="${headerClass}"/>">
		                              	&nbsp;<c:out value="${actionRequest.annotation}" />
		                              	<%-- If we are dealing with a primary delegation, display the annotations for the primary delegations at top level --%>
		                              	<c:if test="${actionRequest.roleRequest}">
		                              		<c:forEach var="roleRequest" items="${actionRequest.childrenRequests}" varStatus="arStatus">
										      <c:if test="${roleRequest.primaryDelegator}">
										       	<c:forEach var="primDelegateRoleRequest" items="${roleRequest.childrenRequests}">
										       		<c:if test="${!empty primDelegateRoleRequest.annotation}">
										       			<br><c:out value="${primDelegateRoleRequest.annotation}"/>
										       		</c:if>
										       	</c:forEach>
										      </c:if>
										    </c:forEach>
		                              	</c:if>
									  </td>
									  
		                            </tr>
		                            <tr id="G<c:out value="${index}" />" style="display: none;">
    		                          <td  align=right class="thnormal">
										<img src="images/pixel_clear.gif" width="<c:out value="60"/>" height="20">
                                      </td>
									  <td colspan="4">
										<jsp:include page="ActionRequests.jsp" flush="true" />
		                              </td>
		                            </tr>