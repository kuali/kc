<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%
String context = (String)request.getAttribute("basePath") + "/";
//	String context = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+ "/";
%>
<c:if test="${ documentTypeStruct.shouldDisplay }" >
<c:set var="documentType" value="${documentTypeStruct.documentType}" />
	<c:if test="${documentType.currentInd == true && documentType.active == true}">
		  
 			 <div class="tab-container" align=center>
          	 <table width="100%" border=0 cellpadding=0 cellspacing=0
			class="datatable">
			<c:if test="${excludeDocId != documentType.documentTypeId}">
				<tr>
					<kul:htmlAttributeHeaderCell scope="col" align="left" colspan="2">
						<a href="<c:url value="DocumentType.do">
							<c:param name="docTypeId" value="${documentType.documentTypeId}" />
							<c:param name="methodToCall" value="report"/>
							</c:url>">
							<c:out value="${documentType.label}" />
						</a>&nbsp;
				</kul:htmlAttributeHeaderCell>				
				</tr>
			</c:if>

		<c:forEach items="${documentTypeStruct.flattenedNodes}" var="routeLevel">

			<c:if test="${routeLevel.routeMethodName != Constants.EXCEPTION_ROUTE_MODULE_NAME &&
				routeLevel.routeMethodName != Constants.ADHOC_ROUTE_MODULE_NAME &&
				routeLevel.flexRM}">

					<tr nowrap>
						<kul:htmlAttributeHeaderCell  scope="col" align="left">
									<c:out value="${routeLevel.routeNodeName}" />&nbsp;
						</kul:htmlAttributeHeaderCell>		
						<td class="datacell">	<a href="<c:url value="${context}Rule.do">
										<c:param name="methodToCall" value="createNew" />
										<c:param name="ruleCreationValues.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
										<c:param name="ruleCreationValues.ruleTemplateName" value="${routeLevel.ruleTemplate.name}"/>
										<c:param name="ruleCreationValues.docTypeName" value="${documentType.name}"/>
									</c:url>" target=_blank>Add Rule</a>
							&nbsp;
							<a href="<c:url value="${context}Lookup.do">
										<c:param name="lookupableImplServiceName" value="RuleBaseValuesLookupableImplService"/>s
										<c:param name="ruleTemplate.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
										<c:param name="ruleTemplateName" value="${routeLevel.ruleTemplate.name}"/>
										<c:param name="docTypeFullName" value="${documentType.name}"/>
									</c:url>" target=_blank>Search</a>
							<c:if test="${routeLevel.ruleTemplate.delegationTemplate != null}">
								&nbsp;
							<a href="<c:url value="${context}RuleQuickLinks.do">
								            <c:param name="methodToCall" value="addDelegationRule"/>
											<c:param name="lookupableImplServiceName" value="RuleBaseValuesLookupableImplService"/>
											<c:param name="ruleTemplate.ruleTemplateId" value="${routeLevel.ruleTemplate.ruleTemplateId}"/>
											<c:param name="ruleTemplateName" value="${routeLevel.ruleTemplate.name}"/>
											<%-- delegationWizard is a constants in KEWConstants --%>
											<c:param name="delegationWizard" value="true"/>
											<c:param name="docTypeFullName" value="${documentType.name}"/>
										</c:url>" target=_blank>Add Delegation</a>
								&nbsp;
						 <a href="<c:url value="${context}Lookup.do">
											<c:param name="lookupableImplServiceName" value="RuleBaseValuesLookupableImplService"/>
											<c:param name="ruleTemplate.ruleTemplateId" value="${routeLevel.ruleTemplate.delegationTemplate.ruleTemplateId}"/>
											<c:param name="ruleTemplateName" value="${routeLevel.ruleTemplate.delegationTemplate.name}"/>
											<%-- delegationWizard is a constants in KEWConstants --%>
											<c:param name="docTypeFullName" value="${documentType.name}"/>
											<c:param name="delegateRuleSearch" value="true"/>
										</c:url>" target=_blank>Search Delegations</a>
							</c:if>
						</td>
					</tr>
			</c:if>
		</c:forEach>
		<c:if test="${! empty documentTypeStruct.childrenDocumentTypes}">
			<tr>
				<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" align="left"/>
				<td class="datacell">
					<c:forEach items="${documentTypeStruct.childrenDocumentTypes}" var="childDocumentTypeStruct">
						<c:set var="documentTypeStruct" value="${childDocumentTypeStruct}" scope="request"/>
						<c:import url="RuleQuickLinksDocumentTypeLinks.jsp" />
					</c:forEach>
				</td>
			</tr>
		</c:if>
		</div>
  </table>  
	</c:if>
</c:if>