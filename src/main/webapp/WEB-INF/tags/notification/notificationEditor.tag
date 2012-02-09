<%--
 Copyright 2005-2010 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="notificationTypeAttributes" value="${DataDictionary.NotificationType.attributes}"/>
<c:set var="notificationTypeRecipientAttributes" value="${DataDictionary.NotificationTypeRecipient.attributes}"/>

<kul:tabTop tabTitle="Notification Editor" defaultOpen="true" tabErrorKey="notificationHelper.*">
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Notification Recipients</span>
        </h3>
        
        <table id="notification-recipients-table" cellpadding="0" cellspacing="0" summary="">
            <tr>
                <th><div align="left">&nbsp;</div></th> 
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${notificationTypeAttributes.recipient}" noColon="true" /></div></th>
            </tr>     
 
            <tr>
                <th class="infoline">
                    Add:
                </th>
                <td align="left" valign="middle"><div align="center">
                    <table cellpadding="0" cellspacing="0" summary="" border="0" style="border: medium none;">
                        <tbody>
                            <tr>
                                <td width="200" style="border: medium none;">
                                    Role Search
                                </td>
                                <td style="border: medium none;">
                                    <kul:lookup boClassName="org.kuali.rice.kim.impl.role.RoleBo" 
                                                fieldConversions="id:notificationHelper.newRoleId" />
                                </td>
                            </tr>
                            <tr>
                                <td width="200" style="border: medium none;">
                                    Employee Search
                                </td>
                                <td style="border: medium none;">
				                    <kul:lookup boClassName="org.kuali.kra.bo.KcPerson" 
				                                fieldConversions="personId:notificationHelper.newPersonId" />
				                </td>
                            </tr>
                            <tr>
                                <td width="200" style="border: medium none;">
                                    Non-Employee Search
                                </td>
                                <td style="border: medium none;">
                                    <kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" 
                                                fieldConversions="rolodexId:notificationHelper.newRolodexId" />
                                </td>
                            </tr>
				         </tbody>
				    </table>
				    <div id="notificationRecipientFullName" align="left">
					    <kul:htmlControlAttribute property="notificationHelper.newNotificationRecipient.fullName" 
	                                              attributeEntry="${notificationTypeRecipientAttributes.fullName}" 
	                                              readOnly="true" />
                    </div>
                </div></td>
                <td class="infoline"><div align="center">
                    <html:image property="methodToCall.addNotificationRecipient.anchor${tabKey}" 
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
                                styleClass="tinybutton"/>
                </div></td>
            </tr>
            
            <c:forEach items="${KualiForm.notificationHelper.notificationRecipients}" var="recipient" varStatus="status">
                <tr>
                    <th class="infoline">
                       <c:out value="${status.index + 1}" />
                    </th>
                    <td align="left" valign="middle"><div align="left">
                        <kul:htmlControlAttribute property="notificationHelper.notificationRecipients[${status.index}].fullName" 
                                                  attributeEntry="${notificationTypeRecipientAttributes.fullName}" 
                                                  readOnly="true" />
						<c:set var="subQualifierValues" value="${recipient.subQualifierValues}"/>
						<c:if test="${not empty subQualifierValues}">
							<div style="margin-left: 2em;"><kul:htmlAttributeLabel attributeEntry="${notificationTypeRecipientAttributes.roleSubQualifier}"/> 
							<html:select property="notificationHelper.notificationRecipients[${status.index}].roleSubQualifier">
								<c:forEach items="${subQualifierValues}" var="keyValue">
									<html:option value="${keyValue.key}"><c:out value="${keyValue.value}"/></html:option>
								</c:forEach>
							</html:select>
							</div>
						</c:if>
                    </div></td>
                    <td align="left" valign="middle"><div align="center">
                        <html:image property="methodToCall.deleteNotificationRecipient.line${status.index}.anchor${currentTabIndex}"
                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                    </div></td>
                </tr>
            </c:forEach>
        </table>
        
        <h3>
            <span class="subhead-left">Notification Message</span>
        </h3>
        
        <table id="notification-message-table" cellpadding="0" cellspacing="0" summary="">
            <tr>
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${notificationTypeAttributes.subject}" noColon="true" /></div></th>
                <td align="left" valign="middle"><div align="left">
                    <kul:htmlControlAttribute property="notificationHelper.notification.subject" 
                                              attributeEntry="${notificationTypeAttributes.subject}" />
                </div></td>
            </tr>
            <tr>
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${notificationTypeAttributes.message}" noColon="true" /></nobr></div></th>
                <td align="left" valign="middle"><div align="left">
                    <kul:htmlControlAttribute property="notificationHelper.notification.message" 
                                              attributeEntry="${notificationTypeAttributes.message}" />
                </div></td>
            </tr>
        </table>
    </div> 
</kul:tabTop>