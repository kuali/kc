<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:set var="actionRequestCodes" value="${RuleForm.actionRequestCodes}"/>
<c:set var="extensionLabels" value="${RuleForm.attributeLabels}"/>
<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
		<c:forEach var="rule" items="${RuleForm.rules}">
			<c:if test="${rule.previousVersionId != null}">
				<tr>
					<td class="thnormal">&nbsp;</td>
					<td class="thnormal">Existing Rule Values</td>
					<td class="thnormal">New Rule Values</td>
				</tr>
			</c:if>
			<tr>
			  	<td class="thnormal" >Document Id</td>
				  <c:if test="${rule.previousVersionId != null}">
						<td class="datacell"><c:out value="${rule.previousVersion.routeHeaderId}" /></td>
					</c:if>
					<td class="datacell"><c:out value="${rule.routeHeaderId}" /></td>			
			</tr>
			<tr>
		  	<td class="thnormal" >Rule Id</td>
				  <c:if test="${rule.previousVersionId != null}">
						<td class="datacell"><c:out value="${rule.previousVersionId}" /></td>
				  </c:if>
				<td class="datacell"><c:out value="${rule.ruleBaseValuesId}" /></td>			
			</tr>
			<td class="thnormal" >Rule Name</td>
                  <c:if test="${rule.name != null}">
                        <td class="datacell"><c:out value="${rule.name}" /></td>
                  </c:if>
                  <c:if test="${rule.previousVersion.name} != null">
                        <td class="datacell"><c:out value="${rule.previousVersion.name}" /></td>
                  </c:if>            
            </tr>
			<tr>
		  	<td class="thnormal" >Active</td>
				  <c:if test="${rule.previousVersionId != null}">
						<td class="datacell"><c:out value="${rule.previousVersion.activeIndDisplay}" /></td>
				  </c:if>
				<td class="datacell"><c:out value="${rule.activeIndDisplay}" /></td>			
			</tr>
			<tr>
		  	<td class="thnormal" >Description</td>
				  <c:if test="${rule.previousVersionId != null}">
						<td class="datacell"><c:out value="${rule.previousVersion.description}" /></td>
				  </c:if>
				<td class="datacell"><c:out value="${rule.description}" /></td>			
			</tr>
			<tr>
		  	<td class="thnormal" >From Date</td>
				  <c:if test="${rule.previousVersionId != null}">
						<td class="datacell"><fmt:formatDate value="${rule.previousVersion.fromDate}" /></td>
				  </c:if>
				<td class="datacell"><fmt:formatDate value="${rule.fromDate}" /></td>			
			</tr>
			<tr>
		  	<td class="thnormal" >To Date</td>
				  <c:if test="${rule.previousVersionId != null}">
						<td class="datacell"><fmt:formatDate value="${rule.previousVersion.toDate}" /></td>
				  </c:if>
				<td class="datacell"><fmt:formatDate value="${rule.toDate}" /></td>
			</tr>
			
			<tr>
		  	<td class="thnormal" >Document Type</td>
				  <c:if test="${rule.previousVersionId != null}">
						<td class="datacell"><c:out value="${rule.previousVersion.docTypeName}" /></td>
				  </c:if>
				<td class="datacell"><c:out value="${rule.docTypeName}" /></td>			
			</tr>
			<tr>
		  	<td class="thnormal" >Ignore Previous</td>
				  <c:if test="${rule.previousVersionId != null}">
						<td class="datacell"><c:out value="${rule.previousVersion.ignorePrevious}" /></td>
				  </c:if>
				<td class="datacell"><c:out value="${rule.ignorePrevious}" /></td>			
			</tr>
			<tr>
		  	<td class="thnormal" >Rule Template</td>
				  <c:if test="${rule.previousVersionId != null}">
						<td class="datacell"><c:out value="${rule.previousVersion.ruleTemplate.name}" /></td>
				  </c:if>
				<td class="datacell"><c:out value="${rule.ruleTemplate.name}" /></td>			
			</tr>
			
			<tr>
		  	<td class="thnormal" >Rule Attributes</td>
				  <c:if test="${rule.previousVersionId != null && rule.previousVersion.ruleExtensions != null}">
						<td class="datacell" valign="top">
							<table align="center" width="90%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
								<c:forEach var="previousRuleExtension" items="${rule.previousVersion.ruleExtensions}">
									<c:forEach var="previousRuleExtensionValue" items="${previousRuleExtension.extensionValues}">
										<tr>
											<td class="thnormal" >
												<c:forEach var="extensionLabel" items="${extensionLabels}" >
									  			<c:if test="${previousRuleExtensionValue.key == extensionLabel.key}" >
										  			<c:out value="${extensionLabel.value}" />&nbsp;
										  		</c:if>
									  		</c:forEach>
											</td>
											<td class="datacell" ><c:out value="${previousRuleExtensionValue.value}"/></td>
										</tr>
									</c:forEach>
									<tr><td class="datacell" colspan="2">&nbsp;</td></tr>
								</c:forEach>
							</table>
							&nbsp;
						</td>
				  </c:if>
				<td class="datacell" valign="top">
					<table align="center" width="90%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
						<c:forEach var="ruleExtension" items="${rule.ruleExtensions}">
							<c:forEach var="ruleExtensionValue" items="${ruleExtension.extensionValues}">
								<tr>
									<td class="thnormal" >
										<c:forEach var="extensionLabel" items="${extensionLabels}" >
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
					&nbsp;
				</td>			
			</tr>
			<tr>
		  	<td class="thnormal" >Rule Responsibility</td>
				  <c:if test="${rule.previousVersionId != null}">
						<td class="datacell" valign="top">
							<table align="center" width="90%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
								<c:forEach var="previousResponsibility" items="${rule.previousVersion.responsibilities}">
									<tr>
										<td class="thnormal" >Type</td>
										<td class="datacell" >
											<c:choose>
											<c:when test="${previousResponsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" >Person</c:when>
											<c:when test="${previousResponsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" >Workgroup</c:when>
											<c:when test="${previousResponsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_ROLE_ID}" >Role</c:when>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td class="thnormal" >Reviewer</td>
										<td class="datacell" >
											<c:choose>
												<c:when test="${previousResponsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" ><c:out value="${previousResponsibility.workflowUser.authenticationUserId}" /></c:when>
												<c:when test="${previousResponsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" ><c:out value="${previousResponsibility.workgroup.groupNameId}" /></c:when>
												<c:when test="${previousResponsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_ROLE_ID}" ><c:out value="${previousResponsibility.role}" /></c:when>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td class="thnormal" >Action Request Code</td>
										<td class="datacell" >
											<c:forEach var="actionRequested" items="${actionRequestCodes}" >
								  			<c:if test="${previousResponsibility.actionRequestedCd == actionRequested.key}" >
									  			<c:out value="${actionRequested.value}" />&nbsp;
									  		</c:if>
								  		</c:forEach>
										</td>
									</tr>
									<tr>
										<td class="thnormal" >Priority</td>
										<td class="datacell" ><c:out value="${previousResponsibility.priority}" /></td>
									</tr>
									
									
							<tr>
								<td class="thnormal" >Rule delegations</td>
								<td class="datacell" >
									<table align="center" width="90%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
										<c:forEach var="delegationPrevious" items="${previousResponsibility.delegationRules}">
											<tr>
												<td class="thnormal" >Delegation Type</td>
												<td class="datacell" ><c:out value="${delegationPrevious.delegationType}" /></td>
											</tr>
												<tr>
												  	<td class="thnormal" >Document Id</td>
														<td class="datacell"><c:out value="${delegationPrevious.delegationRuleBaseValues.routeHeaderId}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Rule Id</td>
													<td class="datacell"><c:out value="${delegationPrevious.delegationRuleBaseValues.ruleBaseValuesId}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Active</td>
													<td class="datacell"><c:out value="${delegationPrevious.delegationRuleBaseValues.activeInd}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Description</td>
													<td class="datacell"><c:out value="${delegationPrevious.delegationRuleBaseValues.description}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >From Date</td>
													<td class="datacell"><fmt:formatDate value="${delegationPrevious.delegationRuleBaseValues.fromDate}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >To Date</td>
													<td class="datacell"><fmt:formatDate value="${delegationPrevious.delegationRuleBaseValues.toDate}" /></td>
												</tr>
												
												<tr>
											  	<td class="thnormal" >Document Type</td>
													<td class="datacell"><c:out value="${delegationPrevious.delegationRuleBaseValues.docTypeName}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Ignore Previous</td>
													<td class="datacell"><c:out value="${delegationPrevious.delegationRuleBaseValues.ignorePrevious}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Rule Template</td>
													<td class="datacell"><c:out value="${delegationPrevious.delegationRuleBaseValues.ruleTemplate.name}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Rule Attributes</td>
													<td class="datacell" valign="top">
														<table align="center" width="90%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
															<c:forEach var="ruleExtensionDelegatePrevious" items="${delegationPrevious.delegationRuleBaseValues.ruleExtensions}">
																<c:forEach var="ruleExtensionValueDelegatePrevious" items="${ruleExtensionDelegatePrevious.extensionValues}">
																	<tr>
																		<td class="thnormal" >
																			<c:forEach var="extensionLabel" items="${extensionLabels}" >
																  			<c:if test="${ruleExtensionValueDelegatePrevious.key == extensionLabel.key}" >
																	  			<c:out value="${extensionLabel.value}" />&nbsp;
																	  		</c:if>
																  		</c:forEach>
																		</td>
																		<td class="datacell" ><c:out value="${ruleExtensionValueDelegatePrevious.value}"/></td>
																	</tr>
																</c:forEach>
																<tr><td class="datacell" colspan="2">&nbsp;</td></tr>
															</c:forEach>
														</table>
														&nbsp;
													</td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Rule Responsibility</td>
													<td class="datacell" valign="top">
														<table align="center" width="90%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
															<c:forEach var="responsibilityDelegatePrevious" items="${delegationPrevious.delegationRuleBaseValues.responsibilities}">
																<tr>
																	<td class="thnormal" >Type</td>
																	<td class="datacell" >
																		<c:choose>
																		<c:when test="${responsibilityDelegatePrevious.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" >Person</c:when>
																		<c:when test="${responsibilityDelegatePrevious.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" >Workgroup</c:when>
																		<c:when test="${responsibilityDelegatePrevious.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_ROLE_ID}" >Role</c:when>
																		</c:choose>
																	</td>
																</tr>
																<tr>
																	<td class="thnormal" >Reviewer</td>
																	<td class="datacell" >
																		<c:choose>
																			<c:when test="${responsibilityDelegatePrevious.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" ><c:out value="${responsibilityDelegatePrevious.workflowUser.authenticationUserId}" /></c:when>
																			<c:when test="${responsibilityDelegatePrevious.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" ><c:out value="${responsibilityDelegatePrevious.workgroup.groupNameId}" /></c:when>
																			<c:when test="${responsibilityDelegatePrevious.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_ROLE_ID}" ><c:out value="${responsibilityDelegatePrevious.role}" /></c:when>
																		</c:choose>
																	</td>
																</tr>
																<tr><td class="datacell" colspan="2">&nbsp;</td></tr>
															</c:forEach>
														</table>
													</td>
												</tr>
										</c:forEach>
									</table>&nbsp;
								</td>
							</tr>
									
									
									
									<tr><td class="datacell" colspan="2">&nbsp;</td></tr>
								</c:forEach>
							</table>
						</td>
				  </c:if>
				<td class="datacell" valign="top">
					<table align="center" width="90%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
						<c:forEach var="responsibility" items="${rule.responsibilities}">
							<tr>
								<td class="thnormal" >Type</td>
								<td class="datacell" >
									<c:choose>
									<c:when test="${responsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" >Person</c:when>
									<c:when test="${responsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" >Workgroup</c:when>
									<c:when test="${responsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_ROLE_ID}" >Role</c:when>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td class="thnormal" >Reviewer</td>
								<td class="datacell" >
									<c:choose>
										<c:when test="${responsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" ><c:out value="${responsibility.workflowUser.authenticationUserId}" /></c:when>
										<c:when test="${responsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" ><c:out value="${responsibility.workgroup.groupNameId}" /></c:when>
										<c:when test="${responsibility.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_ROLE_ID}" ><c:out value="${responsibility.role}" /></c:when>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td class="thnormal" >Action Request Code</td>
								<td class="datacell" >
									<c:forEach var="actionRequested" items="${actionRequestCodes}" >
						  			<c:if test="${responsibility.actionRequestedCd == actionRequested.key}" >
							  			<c:out value="${actionRequested.value}" />&nbsp;
							  		</c:if>
						  		</c:forEach>
								</td>
							</tr>
							<tr>
								<td class="thnormal" >Priority</td>
								<td class="datacell" ><c:out value="${responsibility.priority}" /></td>
							</tr>
							<tr>
								<td class="thnormal" >Rule delegations</td>
								<td class="datacell" >
									<table align="center" width="90%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
										<c:forEach var="delegation" items="${responsibility.delegationRules}">
											<tr>
												<td class="thnormal" >Delegation Type</td>
												<td class="datacell" ><c:out value="${delegation.delegationType}" /></td>
											</tr>
												<tr>
												  	<td class="thnormal" >Document Id</td>
														<td class="datacell"><c:out value="${delegation.delegationRuleBaseValues.routeHeaderId}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Rule Id</td>
													<td class="datacell"><c:out value="${delegation.delegationRuleBaseValues.ruleBaseValuesId}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Active</td>
													<td class="datacell"><c:out value="${delegation.delegationRuleBaseValues.activeInd}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Description</td>
													<td class="datacell"><c:out value="${delegation.delegationRuleBaseValues.description}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >From Date</td>
													<td class="datacell"><fmt:formatDate value="${delegation.delegationRuleBaseValues.fromDate}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >To Date</td>
													<td class="datacell"><fmt:formatDate value="${delegation.delegationRuleBaseValues.toDate}" /></td>
												</tr>
												<tr>
											  	<td class="thnormal" >Document Type</td>
													<td class="datacell"><c:out value="${delegation.delegationRuleBaseValues.docTypeName}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Ignore Previous</td>
													<td class="datacell"><c:out value="${delegation.delegationRuleBaseValues.ignorePrevious}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Rule Template</td>
													<td class="datacell"><c:out value="${delegation.delegationRuleBaseValues.ruleTemplate.name}" /></td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Rule Attributes</td>
													<td class="datacell" valign="top">
														<table align="center" width="90%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
															<c:forEach var="ruleExtensionDelegation" items="${delegation.delegationRuleBaseValues.ruleExtensions}">
																<c:forEach var="ruleExtensionValueDelegation" items="${ruleExtensionDelegation.extensionValues}">
																	<tr>
																		<td class="thnormal" >
																			<c:forEach var="extensionLabel" items="${extensionLabels}" >
																  			<c:if test="${ruleExtensionValueDelegation.key == extensionLabel.key}" >
																	  			<c:out value="${extensionLabel.value}" />&nbsp;
																	  		</c:if>
																  		</c:forEach>
																		</td>
																		<td class="datacell" ><c:out value="${ruleExtensionValueDelegation.value}"/></td>
																	</tr>
																</c:forEach>
																<tr><td class="datacell" colspan="2">&nbsp;</td></tr>
															</c:forEach>
														</table>
														&nbsp;
													</td>			
												</tr>
												<tr>
											  	<td class="thnormal" >Rule Responsibility</td>
													<td class="datacell" valign="top">
														<table align="center" width="90%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
															<c:forEach var="responsibilityDelegation" items="${delegation.delegationRuleBaseValues.responsibilities}">
																<tr>
																	<td class="thnormal" >Type</td>
																	<td class="datacell" >
																		<c:choose>
																		<c:when test="${responsibilityDelegation.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" >Person</c:when>
																		<c:when test="${responsibilityDelegation.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" >Workgroup</c:when>
																		<c:when test="${responsibilityDelegation.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_ROLE_ID}" >Role</c:when>
																		</c:choose>
																	</td>
																</tr>
																<tr>
																	<td class="thnormal" >Reviewer</td>
																	<td class="datacell" >
																		<c:choose>
																			<c:when test="${responsibilityDelegation.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKFLOW_ID}" ><c:out value="${responsibilityDelegation.workflowUser.authenticationUserId}" /></c:when>
																			<c:when test="${responsibilityDelegation.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_WORKGROUP_ID}" ><c:out value="${responsibilityDelegation.workgroup.groupNameId}" /></c:when>
																			<c:when test="${responsibilityDelegation.ruleResponsibilityType == Constants.RULE_RESPONSIBILITY_ROLE_ID}" ><c:out value="${responsibilityDelegation.role}" /></c:when>
																		</c:choose>
																	</td>
																</tr>
																<tr><td class="datacell" colspan="2">&nbsp;</td></tr>
															</c:forEach>
														</table>
													</td>
												</tr>
										</c:forEach>
									</table>&nbsp;
								</td>
							</tr>
							<tr><td class="datacell" colspan="2">&nbsp;</td></tr>
						</c:forEach>
					</table>
				</td>			
			</tr>
		<tr>
			<c:choose>
				<c:when test="${rule.previousVersionId != null}" ><td class="datacell" colspan="3">&nbsp;</td></c:when>
				<c:otherwise><td class="datacell" colspan="2">&nbsp;</td></c:otherwise>
			</c:choose>
        </tr>
		</c:forEach>

<%--
	  <c:if test="${! empty Annotation}">
		  <tr>
			<td class="thnormal">Annotation</td>
			<td class="datacell" colspan="2"><c:out value="${Annotation}" /></td>
		  </tr>
	  </c:if>
--%>
</table>