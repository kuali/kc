<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true"%>
<%@ attribute name="index" description="Index" required="true"%>

<script type='text/javascript' src='dwr/interface/KraPersonService.js'></script>

<c:set var="reportTrackingAttributes" value="${DataDictionary.ReportTracking.attributes}" />
<c:set var="reportTrackingBeanAttributes" value="${DataDictionary.ReportTrackingBean.attributes}" />
<c:set var="reportTrackingReadOnly" value="${!KualiForm.permissionsHelper.maintainAwardReportTracking }"/>

<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false"
	tabTitle="Details - Report Tracking"
	useCurrentTabIndexAsKey="true"
	tabErrorKey="document.awardList[0].awardReportTermItems[${index}]*,reportTrackingBeans[${index}]*,document.award.awardReportTermItems[${index}]*, document.awardList[0].awardReportTermItems[${index}].frequencyBaseCode">

	<kra:softError softErrorKey="awardReportTerms-${KualiForm.document.award.awardReportTermItems[index].reportClassCode}-${KualiForm.document.award.awardReportTermItems[index].frequencyCode}-${KualiForm.document.award.awardReportTermItems[index].frequencyBaseCode}-${KualiForm.document.award.awardReportTermItems[index].ospDistributionCode}" />
	<table cellpadding="0" cellspacing="0" summary="">
		<c:if test="${!reportTrackingReadOnly}">
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
				<th colspan="2"> <div align="center">Action</div></th>	
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
			
				<kul:htmlControlAttribute property="reportTrackingBeans[${index}].preparerName" readOnly="${reportTrackingReadOnly}" 
				onblur="loadContactPersonName('reportTrackingBeans[${index}].preparerName',
										'reportTrackingBeans.fullName',
										'na',
										'na',
										'na',
										'sub.reportTrackingBeans.div');"
				attributeEntry="${reportTrackingBeanAttributes.preparerName}"/>
				
						<c:if test="${!reportTrackingReadOnly}">
	                	<kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" 
	                                fieldConversions="personId:reportTrackingBeans[${index}].preparerId,userName:reportTrackingBeans[${index}].preparerName" />
                    </c:if>				
				<kul:htmlControlAttribute property="reportTrackingBeans[${index}].preparerId" readOnly="${reportTrackingReadOnly }"
						attributeEntry="${reportTrackingBeanAttributes.preparerId}"   />
                <kul:directInquiry boClassName="org.kuali.coeus.common.framework.person.KcPerson" inquiryParameters="reportTrackingBeans[${index}].preparerId:personId" />
					<br/>
                	<span id="reportTrackingBeans.fullName"> <c:out value="${reportTrackingBeans[$index].preparerFullname}"/>&nbsp;</span>  
                <html:hidden styleId ="sub.reportTrackingBeans.div" property="reportTrackingBeans[${index}].preparerId" />
			</td>
			<td>
				<kul:htmlControlAttribute property="reportTrackingBeans[${index}].statusCode" 
							attributeEntry="${reportTrackingBeanAttributes.statusCode}" readOnly="${reportTrackingReadOnly }"  />
			</td>
			<td>
				<kul:htmlControlAttribute property="reportTrackingBeans[${index}].activityDate" 
							attributeEntry="${reportTrackingBeanAttributes.activityDate}" readOnly="${reportTrackingReadOnly }"  />
			</td>
			<td>
				<kul:htmlControlAttribute property="reportTrackingBeans[${index}].comments" 
							attributeEntry="${reportTrackingBeanAttributes.comments}" readOnly="${reportTrackingReadOnly }"  />
			</td>
				<td colspan="2">
				<div align="center">
				<html:image property="methodToCall.updateMultileReportTracking.AwardReportTermItemsIndex${index}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-update.gif' 
										alt="Update Multiple Report Tracking" onclick="" styleClass="tinybutton"/>
				</div>
			</td>
		</tr>
		</c:if>
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
			<th> <div align="center">Action</div></th>
		</tr>
		<c:forEach var="reportTracking" items="${KualiForm.document.award.awardReportTermItems[index].reportTrackings}" varStatus="status">
			<tr>
				<td>
					<c:if test="${!reportTrackingReadOnly}">
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].multiEditSelected" 
							attributeEntry="${reportTrackingAttributes.multiEditSelected}" readOnly="${reportTrackingReadOnly }"  />
					</c:if>
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].dueDate" 
						attributeEntry="${reportTrackingAttributes.dueDate}" readOnly="${reportTrackingReadOnly}"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].overdue" 
						attributeEntry="${reportTrackingAttributes.overdue}" readOnly="${true }"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerName" 
					onblur="loadContactPersonName('document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerName',
										'preparer[${status.index}]',
										'na',
										'na',
										'na',
										'sub.reportTrackingBeans.div');"	attributeEntry="${reportTrackingAttributes.preparerName}" readOnly="${reportTrackingReadOnly }"  />
					<c:if test="${!reportTrackingReadOnly}">
	                	<kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" 
	                                fieldConversions="userName:document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerName,personId:document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerId" />
                   </c:if>	
                    <kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerId" 
						attributeEntry="${reportTrackingAttributes.preparerId}" readOnly="${reportTrackingReadOnly }"  />
                    <kul:directInquiry boClassName="org.kuali.coeus.common.framework.person.KcPerson" inquiryParameters="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerId:personId" />
                    <html:hidden styleId ="sub.reportTrackingBeans.div" property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].preparerId" />
					<br/>
					<span id="preparer[${status.index}]">
                    &nbsp;
					</span>
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].statusCode" 
						attributeEntry="${reportTrackingAttributes.statusCode}" readOnly="${reportTrackingReadOnly }"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].activityDate" 
						attributeEntry="${reportTrackingAttributes.activityDate}" readOnly="${reportTrackingReadOnly }"  />
				</td>
				<td>
					<kul:htmlControlAttribute property="document.award.awardReportTermItems[${index}].reportTrackings[${status.index}].comments" 
						attributeEntry="${reportTrackingAttributes.comments}" readOnly="${reportTrackingReadOnly }"  />
				</td>
				<td>
					<c:if test="${not empty reportTracking.lastUpdateUser}" >
						${reportTracking.lastUpdateUser } :
						<fmt:formatDate value="${reportTracking.lastUpdateDate}" pattern="MM/dd/yyyy HH:mm:ss"/>
					</c:if>
				</td>
				<td>
					<c:if test="${reportTracking.displayDeleteButton}">
						<html:image property="methodToCall.deleteReportTrackingRecord.awardReportTermItems${index}.line${status.index}.anchor${currentTabIndex}"
				        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
				        onclick="return confirm('Are you sure you want to delete this report tracking detail?  Note, this record may be regenerated on the next save, based on the selected frequency and frequency base.')"/>
			        </c:if>
				</td>
			</tr>			
		</c:forEach>
	</table>

</kul:innerTab>
