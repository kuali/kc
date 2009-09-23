<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>

<tr>
  <td class="datacell">
    <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>

      <tr>
        <td width="10%">&nbsp;</td>
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
          
			<%-- KULRICE-2924: The app specific route section has been modified to bear closer resemblance to the AdHoc Recipients tab, and to
					no longer require the use of Javascript for certain important actions. --%>

			<%-- Adhoc routing for a person. --%>
			
			<kul:displayIfErrors keyMatch="appSpecificRouteRecipient.id">
				<tr>
					<th colspan="4">
						<kul:errors keyMatch="appSpecificRouteRecipient.id" />
					</th>
				</tr>
			</kul:displayIfErrors>
			<tr>
				<td colspan="4" class="headercell1" align="center"><strong>AdHoc Person Requests:</strong></td>
			</tr>
			<tr>
				<td class="thnormal" align="center"><strong>*<c:out value="${DataDictionary.AdHocRoutePerson.attributes.actionRequested.label}" /></strong></td>
				<td class="thnormal" colspan="2" align="center"><strong>*<c:out value="${DataDictionary.PersonImpl.attributes.principalName.label}" /></strong></td>
				<td class="thnormal" align="center"><strong><c:out value="Actions" /></strong></td>
			</tr>

			<tr>
				<td class="datacell" align="center">
					<html:hidden property="appSpecificRouteRecipientType"/>
					<%-- Define variable that will hold the Title of the html control --%>
					<c:set var="accessibleTitle" value="${DataDictionary.AdHocRoutePerson.attributes.actionRequested.label}"/>
					<c:set var="accessibleTitle2" value="${DataDictionary.AdHocRouteWorkgroup.attributes.actionRequested.label}"/>
					<c:if test="${(DataDictionary.AdHocRoutePerson.attributes.actionRequested.required == true) && readOnly != true}">
						<c:set var="accessibleTitle" value="${Constants.REQUIRED_FIELD_SYMBOL} ${accessibleTitle}"/>
					</c:if>
					<c:if test="${(DataDictionary.AdHocRouteWorkgroup.attributes.actionRequested.required == true) && readOnly != true}">
						<c:set var="accessibleTitle2" value="${Constants.REQUIRED_FIELD_SYMBOL} ${accessibleTitle2}"/>
					</c:if>
					<html:select title="${accessibleTitle}" property="appSpecificRouteActionRequestCd">
						<c:set var="actionRequestCodes" value="${KewRoutingKualiForm.appSpecificRouteActionRequestCds}"/>
						<html:options collection="actionRequestCodes" property="key" labelProperty="value"/>
					</html:select>
				</td>
				<td class="datacell" colspan="2" align="center">
					<%-- The kul:user tag below maps the person's name to the namespaceCode field only because the tag requires specifying a field to
							map the name to; only the AdHoc group requests actually do any processing with the namespaceCode field (since the group requests
								are the ones that actually use that field for storing real namespace codes). --%>
					<kul:user userIdFieldName="appSpecificRouteRecipient.id" userId="${KewRoutingKualiForm.appSpecificRouteRecipient.id}"
							universalIdFieldName="" universalId="" userNameFieldName="appSpecificRouteRecipient.namespaceCode"
							userName="${KewRoutingKualiForm.appSpecificRouteRecipient.namespaceCode}" readOnly="${displayReadOnly}" renderOtherFields="true"
							fieldConversions="principalName:appSpecificRouteRecipient.id,name:appSpecificRouteRecipient.namespaceCode"
							lookupParameters="appSpecificRouteRecipient.id:principalName" />
				</td>
				<td class="datacell" align="center">
					<html:image property="methodToCall.routeToAppSpecificRecipient.(((${KEWConstants.PERSON})))" src="${resourcePath}images/tinybutton-routerecpt.gif" title="Route to Recipient" alt="Route to Recipient" styleClass="tinybutton"/>
				</td>
			</tr>
			
			<%-- Adhoc routing for a group. --%>
			
			<kul:displayIfErrors keyMatch="appSpecificRouteRecipient2.id">
				<tr>
					<th colspan="4">
						<kul:errors keyMatch="appSpecificRouteRecipient2.id" />
					</th>
				</tr>
			</kul:displayIfErrors>
			<tr>
				<td colspan="4" class="headercell1" align="center"><strong>AdHoc Group Requests:</strong></td>
			</tr>
			<tr>
				<td class="thnormal" align="center"><strong>*<c:out value="${DataDictionary.AdHocRouteWorkgroup.attributes.actionRequested.label}" /></strong></td>
				<td class="thnormal" align="center"><strong>*<c:out value="${DataDictionary.PersonDocumentGroup.attributes.namespaceCode.label}" /></strong></td>
				<td class="thnormal" align="center"><strong>*<c:out value="${DataDictionary.PersonDocumentGroup.attributes.groupName.label}" /></strong></td>
				<td class="thnormal" align="center"><strong><c:out value="Actions" /></strong></td>
			</tr>
			<tr>
				<td class="datacell" align="center">
					<html:select title="${accessibleTitle2}" property="appSpecificRouteActionRequestCd2">
						<c:set var="actionRequestCodes" value="${KewRoutingKualiForm.appSpecificRouteActionRequestCds}"/>
						<html:options collection="actionRequestCodes" property="key" labelProperty="value"/>
					</html:select>
				</td>
				<td class="datacell" align="center">
					<kul:htmlControlAttribute property="appSpecificRouteRecipient2.namespaceCode" attributeEntry="${DataDictionary.PersonDocumentGroup.attributes.namespaceCode}" readOnly="${displayReadOnly}" />
				</td>
				<td class="datacell" align="center">
					<kul:htmlControlAttribute property="appSpecificRouteRecipient2.id" attributeEntry="${DataDictionary.PersonDocumentGroup.attributes.groupName}" readOnly="${displayReadOnly}" />
					<kul:workflowWorkgroupLookup fieldConversions="namespaceCode:appSpecificRouteRecipient2.namespaceCode,groupName:appSpecificRouteRecipient2.id"
						lookupParameters="appSpecificRouteRecipient2.namespaceCode:namespaceCode,appSpecificRouteRecipient2.id:groupName" />
				</td>
				<td class="datacell" align="center">
					<html:image property="methodToCall.routeToAppSpecificRecipient.(((${KEWConstants.WORKGROUP})))" src="${resourcePath}images/tinybutton-routerecpt.gif" title="Route to Recipient" alt="Route to Recipient" styleClass="tinybutton"/>
				</td>
			</tr>
			<tr><td class="headercell1" colspan="4" align="center">&nbsp;</td></tr>
			
			<%-- A list of the current app specific route recipients, if any. --%>
			
			<c:if test="${!empty KewRoutingKualiForm.appSpecificRouteList}">
			<tr>
    		  <td colspan="2" class="datacell">
      			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
  			      <tr>
    		       <td height="15" class="headercell1" colspan="4" align="center"><strong>List of App Specific Route Recipients</strong></td>
  			      </tr>
        		  <tr>
          			<td class="thnormal"><strong>Recipient Type</strong></td>
          			<td class="thnormal"><strong>Principal Name or Group Name</strong></td>
          			<td class="thnormal"><strong>Action Requested</strong></td>
          			<td class="thnormal"><strong>Delete</strong></td>
        		  </tr>

     			  <logic-el:iterate name="KewRoutingKualiForm" id="recipient" property="appSpecificRouteList" indexId="ctr">
          		  <tr>
            		<td class="datacell"><c:out value="${recipient.type}" /></td>
            		<td class="datacell"><c:out value="${recipient.id}" /></td>
            		<td class="datacell"><c:out value="${recipient.actionRequestedValue}" /></td>
            		<td class="datacell"><html-el:image src="${resourcePath}images/tinybutton-remreci.gif" align="absmiddle" property="methodToCall.removeAppSpecificRecipient.(((${ctr})))" styleClass="tinybutton" /></td>
            		<html-el:hidden property="appSpecificRoute[${ctr}].type" />
            		<html-el:hidden property="appSpecificRoute[${ctr}].id" />
            		<html-el:hidden property="appSpecificRoute[${ctr}].actionRequested" />
            		<html-el:hidden property="appSpecificRoute[${ctr}].namespaceCode" />
            		<html-el:hidden property="appSpecificRoute[${ctr}].actionRequestId" />
          		  </tr>
        		  </logic-el:iterate>
        		  <html-el:hidden property="removedAppSpecificRecipient" />
      			</table>
    		  </td>
  		    </tr>
  		  </c:if>

		  </table>
        </td>
        <td width="10%"> &nbsp; </td>
      </tr>

      <tr>
        <td colspan="3"> &nbsp;&nbsp; </td>
      </tr>
    </table>
  </td>
</tr>

<html-el:hidden property="appSpecificRouteRecipient.type" />
<html-el:hidden property="appSpecificRouteRecipient.actionRequested" />
<html-el:hidden property="recipientIndex" />
