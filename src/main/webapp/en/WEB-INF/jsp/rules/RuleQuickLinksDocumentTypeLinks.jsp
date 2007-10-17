<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<%
String context = (String)request.getAttribute("basePath") + "/";
//	String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+ "/";
%>
<c:if test="${ documentTypeStruct.shouldDisplay }" >
<c:set var="documentType" value="${documentTypeStruct.documentType}" />
	<c:if test="${documentType.currentInd == true && documentType.activeInd == true}">
		<table border="0" width="100%">
			<c:if test="${excludeDocId != documentType.documentTypeId}">
				<tr>
					<td colspan="2"><a href="<c:url value="DocumentType.do">
													<c:param name="docTypeId" value="${documentType.documentTypeId}" />
													<c:param name="methodToCall" value="report"/>
												</c:url>"><c:out value="${documentType.label}" />
											</a>&nbsp;</td>
				</tr>
			</c:if>

		<c:forEach items="${documentTypeStruct.flattenedNodes}" var="routeLevel">

			<c:if test="${routeLevel.routeMethodName != Constants.EXCEPTION_ROUTE_MODULE_NAME &&
				routeLevel.routeMethodName != Constants.ADHOC_ROUTE_MODULE_NAME &&
				routeLevel.flexRM}">

					<tr>
						<td width="20">&nbsp;</td>
						<td>
							<c:out value="${routeLevel.routeNodeName}" />&nbsp;
								<c:choose>
								<c:when test="${RuleQuickLinkForm.useOneStartPortalUrl}">
								    <c:set var="ruleTemplateName" value="${routeLevel.ruleTemplate.encodedName}"/>
									<a href="javascript:focusInOnUrl('<%=context %><c:url value="Rule.do">
												<c:param name="methodToCall" value="createNew" />
												<c:param name="ruleCreationValues.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
												<c:param name="ruleCreationValues.ruleTemplateName" value="${ruleTemplateName}"/>
												<c:param name="ruleCreationValues.docTypeName" value="${documentType.name}"/>
											</c:url>')">Add Rule</a>
									&nbsp;
									<a href="javascript:focusInOnUrl('<%=context %><c:url value="Lookup.do">
												<c:param name="lookupableImplServiceName" value="RuleBaseValuesLookupableImplService"/>s
												<c:param name="ruleTemplate.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
												<c:param name="ruleTemplateName" value="${ruleTemplateName}"/>
												<c:param name="docTypeFullName" value="${documentType.name}"/>
											</c:url>')">Search</a>
									<c:if test="${routeLevel.ruleTemplate.delegationTemplate != null}">
										&nbsp;
										<a href="javascript:focusInOnUrl('<%=context %><c:url value="RuleQuickLinks.do">
										            <c:param name="methodToCall" value="addDelegationRule"/>
													<c:param name="lookupableImplServiceName" value="RuleBaseValuesLookupableImplService"/>
													<c:param name="ruleTemplate.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
													<c:param name="ruleTemplateName" value="${ruleTemplateName}"/>
													<%-- delegationWizard is a constants in EdenConstants --%>
													<c:param name="delegationWizard" value="true"/>
													<c:param name="docTypeFullName" value="${documentType.name}"/>
												</c:url>')">Add Delegation</a>
										&nbsp;
										<a href="javascript:focusInOnUrl('<%=context %><c:url value="Lookup.do">
													<c:param name="lookupableImplServiceName" value="RuleBaseValuesLookupableImplService"/>
													<c:param name="ruleTemplate.ruleTemplateId" value="${routeLevel.ruleTemplate.delegationTemplate.ruleTemplateId}"/>
													<c:param name="ruleTemplateName" value="${routeLevel.ruleTemplate.delegationTemplate.encodedName}"/>
													<%-- delegationWizard is a constants in EdenConstants --%>
													<c:param name="docTypeFullName" value="${documentType.name}"/>
													<c:param name="delegateRuleSearch" value="true"/>
												</c:url>')">Search Delegations</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<a href="<%=context %><c:url value="Rule.do">
												<c:param name="methodToCall" value="createNew" />
												<c:param name="ruleCreationValues.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
												<c:param name="ruleCreationValues.ruleTemplateName" value="${routeLevel.ruleTemplate.name}"/>
												<c:param name="ruleCreationValues.docTypeName" value="${documentType.name}"/>
											</c:url>" target=_blank>Add Rule</a>
									&nbsp;
									<a href="<%=context %><c:url value="Lookup.do">
												<c:param name="lookupableImplServiceName" value="RuleBaseValuesLookupableImplService"/>s
												<c:param name="ruleTemplate.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
												<c:param name="ruleTemplateName" value="${routeLevel.ruleTemplate.name}"/>
												<c:param name="docTypeFullName" value="${documentType.name}"/>
											</c:url>" target=_blank>Search</a>
									<c:if test="${routeLevel.ruleTemplate.delegationTemplate != null}">
										&nbsp;
										<a href="<%=context %><c:url value="RuleQuickLinks.do">
										            <c:param name="methodToCall" value="addDelegationRule"/>
													<c:param name="lookupableImplServiceName" value="RuleBaseValuesLookupableImplService"/>
													<c:param name="ruleTemplate.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
													<c:param name="ruleTemplateName" value="${routeLevel.ruleTemplate.name}"/>
													<%-- delegationWizard is a constants in EdenConstants --%>
													<c:param name="delegationWizard" value="true"/>
													<c:param name="docTypeFullName" value="${documentType.name}"/>
												</c:url>" target=_blank>Add Delegation</a>
										&nbsp;
										<a href="<%=context %><c:url value="Lookup.do">
													<c:param name="lookupableImplServiceName" value="RuleBaseValuesLookupableImplService"/>
													<c:param name="ruleTemplate.ruleTemplateId" value="${routeLevel.ruleTemplate.delegationTemplate.ruleTemplateId}"/>
													<c:param name="ruleTemplateName" value="${routeLevel.ruleTemplate.delegationTemplate.name}"/>
													<%-- delegationWizard is a constants in EdenConstants --%>
													<c:param name="docTypeFullName" value="${documentType.name}"/>
													<c:param name="delegateRuleSearch" value="true"/>
												</c:url>" target=_blank>Search Delegations</a>
									</c:if>
								</c:otherwise>
								</c:choose>
						</td>
					</tr>
			</c:if>
		</c:forEach>

		<c:if test="${! empty documentTypeStruct.childrenDocumentTypes}">
			<tr>
				<td width="20">&nbsp;</td>
				<td>
					<c:forEach items="${documentTypeStruct.childrenDocumentTypes}" var="childDocumentTypeStruct">
						<c:set var="documentTypeStruct" value="${childDocumentTypeStruct}" scope="request"/>
						<c:import url="RuleQuickLinksDocumentTypeLinks.jsp" />
					</c:forEach>
				</td>
			</tr>
		</c:if>
		</table>
	</c:if>
</c:if>