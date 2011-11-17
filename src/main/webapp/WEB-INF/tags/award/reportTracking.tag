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

<script type='text/javascript' src='dwr/interface/KraPersonService.js'></script>

<c:set var="awardReportingAttributes" value="${DataDictionary.AwardReporting.attributes}" />
<c:set var="reportTrackingAttributes" value="${DataDictionary.ReportTracking.attributes}" />
<c:set var="reportTrackingBeanAttributes" value="${DataDictionary.ReportTrackingBean.attributes}" />
<c:set var="readOnly" value="${false }"/>

<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false"
	tabTitle="Details - Report Tracking (New)"
	useCurrentTabIndexAsKey="true"
	tabErrorKey="document.awardList[0].awardReportTermItems[${index}].awardReportings*, document.awardList[0].awardReportTermItems[${index}].frequencyBaseCode">

	<kra:softError softErrorKey="awardReportTerms-${KualiForm.document.award.awardReportTermItems[index].reportClassCode}-${KualiForm.document.award.awardReportTermItems[index].frequencyCode}-${KualiForm.document.award.awardReportTermItems[index].frequencyBaseCode}-${KualiForm.document.award.awardReportTermItems[index].ospDistributionCode}" />
	<table cellpadding="0" cellspacing="0" summary="">
		<tr>
			<th colspan="3"><div align="center">Edit Selected:</div></th>
			<th> <div align="center">
				<kul:htmlAttributeLabel attributeEntry="${reportTrackingBeanAttributes.preparerId}" noColon="true" />
			</div></th>
			<th> <div align="center">
				<kul:htmlAttributeLabel attributeEntry="${reportTrackingBeanAttributes.statusCode}" noColon="true" />
			</div></th>
			<th> <div align="center">
				<kul:htmlAttributeLabel attributeEntry="${reportTrackingBeanAttributes.activityDate}" noColon="true" />
			</div></th>
			<th> <div align="center">
				<kul:htmlAttributeLabel attributeEntry="${reportTrackingBeanAttributes.comments}" noColon="true" />
			</div></th>
			<th> <div align="center">Action</div></th>	
		</tr>
		<tr>
			<th colspan="3">
				<div align="center">
					<html:image property="methodToCall.selectAllMultEdit.AwardReportTermItemsIndex${index}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif' 
										alt="Select All" onclick="" styleClass="tinybutton"/>
					:
					<html:image property="methodToCall.selectNoneMultiEdit.AwardReportTermItemsIndex${index}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif' 
										alt="Deselect All" onclick="" styleClass="tinybutton"/>
				</div>
			</th>
			<td>
			
				<kul:htmlControlAttribute property="reportTrackingBeans[${index}].preparerName" 
						attributeEntry="${reportTrackingBeanAttributes.preparerName}" readOnly="${readOnly }"  />
					<c:if test="${!readOnly}">
	                	<kul:lookup boClassName="org.kuali.kra.bo.KcPerson" 
	                                fieldConversions="userName:reportTrackingBeans[${index}].preparerName,personId:reportTrackingBeans[${index}].preparerId" />
                    </c:if>				
					<kul:htmlControlAttribute property="reportTrackingBeans[${index}].preparerId" 
						attributeEntry="${reportTrackingBeanAttributes.preparerId}" readOnly="${readOnly }"  />
					<br/>
					<span id="multiUpdatePreparerNames[${index}]">
						<c:out value="${reportTrackingBeans[index].preparerFullname}"/>&nbsp;
					</span>
			
			</td>
			<td>
				<kul:htmlControlAttribute property="reportTrackingBeans[${index}].statusCode" 
						attributeEntry="${reportTrackingBeanAttributes.statusCode}" readOnly="${readOnly }"  />
			</td>
			<td>
				<kul:htmlControlAttribute property="reportTrackingBeans[${index}].activityDate" 
						attributeEntry="${reportTrackingBeanAttributes.activityDate}" readOnly="${readOnly }"  />
			</td>
			<td>
				<kul:htmlControlAttribute property="reportTrackingBeans[${index}].comments" 
						attributeEntry="${reportTrackingBeanAttributes.comments}" readOnly="${readOnly }"  />
			</td>
			<td>
				<div align="center">
				<html:image property="methodToCall.updateMultileReportTracking.AwardReportTermItemsIndex${index}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-update.gif' 
										alt="Update Multiple Report Tracking" onclick="" styleClass="tinybutton"/>
				</div>
			</td>
		</tr>
		<tr>
			<th><div align="center">
				<kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes.multiEditSelected}" noColon="true" />
			</div></th>
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
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].multiEditSelected" 
						attributeEntry="${reportTrackingAttributes.multiEditSelected}" readOnly="${readOnly }"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].dueDate" 
						attributeEntry="${reportTrackingAttributes.dueDate}" readOnly="${true }"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].overdue" 
						attributeEntry="${reportTrackingAttributes.overdue}" readOnly="${true }"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerName" 
						attributeEntry="${reportTrackingAttributes.preparerName}" readOnly="${readOnly }"  />
					<c:if test="${!readOnly}">
	                	<kul:lookup boClassName="org.kuali.kra.bo.KcPerson" 
	                                fieldConversions="userName:document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerName,personId:document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerId" />
                    </c:if>				
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerId" 
						attributeEntry="${reportTrackingAttributes.preparerId}" readOnly="${readOnly }"  />
					<br/>
					<span id="preparer[${status.index}]">
						<c:out value="${reportTracking.preparerFullname}"/>&nbsp;
					</span>
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].statusCode" 
						attributeEntry="${reportTrackingAttributes.statusCode}" readOnly="${readOnly }"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].activityDate" 
						attributeEntry="${reportTrackingAttributes.activityDate}" readOnly="${readOnly }"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].comments" 
						attributeEntry="${reportTrackingAttributes.comments}" readOnly="${readOnly }"  />
				</td>
				<td>
					${reportTracking.lastUpdateUser } ${reportTracking.lastUpdateDate }
				</td>
			</tr>			
		</c:forEach>
	</table>

</kul:innerTab>
