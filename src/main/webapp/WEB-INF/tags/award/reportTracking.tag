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

<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true"%>
<%@ attribute name="index" description="Index" required="true"%>

<c:set var="awardReportingAttributes" value="${DataDictionary.AwardReporting.attributes}" />
<c:set var="reportTrackingAttributes" value="${DataDictionary.ReportTracking.attributes}" />

<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false"
	tabTitle="Details - Report Tracking (New)"
	useCurrentTabIndexAsKey="true"
	tabErrorKey="document.awardList[0].awardReportTermItems[${index}].awardReportings*, document.awardList[0].awardReportTermItems[${index}].frequencyBaseCode">

	<kra:softError softErrorKey="awardReportTerms-${KualiForm.document.award.awardReportTermItems[index].reportClassCode}-${KualiForm.document.award.awardReportTermItems[index].frequencyCode}-${KualiForm.document.award.awardReportTermItems[index].frequencyBaseCode}-${KualiForm.document.award.awardReportTermItems[index].ospDistributionCode}" />
	<table cellpadding="0" cellspacing="0" summary="">
		<tr>
			<th>Select</th>
			<th> <div align="center">
				<kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes.dueDate}" noColon="true" />
			</div></th>
			<th> <div align="center">
				<kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes.overdue}" noColon="true" />
			</div></th>
			<th> <div align="center">
				<kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes.preparerId}" noColon="true" />
			</div></th>
			<th> <div align="center">
				<kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes.statusCode}" noColon="true" />
			</div></th>
			<th> <div align="center">
				<kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes.activityDate}" noColon="true" />
			</div></th>
			<th> <div align="center">
				<kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes.comments}" noColon="true" />
			</div></th>
			<th> <div align="center">
				Last Update
			</div></th>
		</tr>
		<c:forEach var="reportTracking" items="${KualiForm.document.award.awardReportTermItems[index].reportTrackings}" varStatus="status">
			<tr>
				<td>
					<input type="checkbox" name="dummyForNow"/>
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].dueDate" 
						attributeEntry="${reportTrackingAttributes.dueDate}" readOnly="${true }"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].overdue" 
						attributeEntry="${reportTrackingAttributes.overdue}"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerId" 
						attributeEntry="${reportTrackingAttributes.preparerId}"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].statusCode" 
						attributeEntry="${reportTrackingAttributes.statusCode}"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].activityDate" 
						attributeEntry="${reportTrackingAttributes.activityDate}"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].comments" 
						attributeEntry="${reportTrackingAttributes.comments}"  />
				</td>
				<td>
					${reportTracking.updateUser } ${reportTracking.updateTimestamp }
				</td>
			</tr>			
		</c:forEach>
	</table>

</kul:innerTab>
