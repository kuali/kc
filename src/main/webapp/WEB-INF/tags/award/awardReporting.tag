<%--
 Copyright 2006-2009 The Kuali Foundation

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

<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true" %>
<%@ attribute name="index" description="Index" required="true" %>

<c:set var="awardReportingAttributes" value="${DataDictionary.AwardReporting.attributes}" />

<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Details" useCurrentTabIndexAsKey="true" tabErrorKey="document.awardList[0].awardReportTermItems[${index}].awardReportings*" overrideToggleTabMethodString="Reporting.awardReportTerm${index}" >

        
        <table cellpadding="0" cellspacing="0" summary="">
          	<%-- Header --%>
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardReportingAttributes.dueDate}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardReportingAttributes.overdueCounter}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardReportingAttributes.reportStatusCode}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardReportingAttributes.activityDate}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardReportingAttributes.comments}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardReportingAttributes.personId}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardReportingAttributes.notificationWindow}" scope="col" /></div></th>
          	</tr> 
          	<%-- Header --%>
          	
            <%-- Existing data --%>
        	<c:forEach var="awardReporting" items="${KualiForm.document.award.awardReportTermItems[index].awardReportings}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>                  
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardReportTermItems[${index}].awardReportings[${status.index}].dueDate" attributeEntry="${awardReportingAttributes.dueDate}" datePicker="true"  readOnly="true" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardReportTermItems[${index}].awardReportings[${status.index}].overdueCounter" attributeEntry="${awardReportingAttributes.overdueCounter}"  readOnly="true" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardReportTermItems[${index}].awardReportings[${status.index}].reportStatusCode" attributeEntry="${awardReportingAttributes.reportStatusCode}"  readOnly="true" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardReportTermItems[${index}].awardReportings[${status.index}].activityDate" attributeEntry="${awardReportingAttributes.activityDate}" datePicker="true"  readOnly="true" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardReportTermItems[${index}].awardReportings[${status.index}].comments" attributeEntry="${awardReportingAttributes.comments}"  readOnly="true" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardReportTermItems[${index}].awardReportings[${status.index}].personId" attributeEntry="${awardReportingAttributes.personId}"  readOnly="true" />
					</div>
				  </td>                  
				  <td class="infoline">
					<div align="center">
						<html:image property="methodToCall.deleteReportScheduleItem.awardReportTerm${index}.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
                  </td>

	            </tr>
        	</c:forEach> 
            <%-- Existing data --%>
        </table>

</kul:innerTab>
