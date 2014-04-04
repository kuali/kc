<%--
 Copyright 2005-2014 The Kuali Foundation

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

<c:set var="subAwardReportAttributes" value="${DataDictionary.SubAwardReports.attributes}" />
<c:set var="action" value="subAwardTemplateInformation" />
<c:set var="reports" value="${KualiForm.document.subAwardList[0].subAwardReportList}"/>

<kul:tab tabTitle="Reports" tabItemCount="${fn:length(reports)}" defaultOpen="false" tabErrorKey="subAwardAttachmentFormBean.newReport*,document.subAwardList[0].subAwardReportList*" transparentBackground="false"  >
	
	<div class="tab-container" align="center">
   		<h3>
   			<span class="subhead-left">Add Report</span>
   			<span class="subhead-right"></span>
       </h3>
        <table cellpadding="0" cellspacing="0" summary="">
         	<tr>
         	    <th>
         	    	&nbsp;
         	    </th>
         		 <th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${subAwardReportAttributes['subAwardReportTypeCode']}" noColon="false"/>
         			</div>
         		</th>
				<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${subAwardReportAttributes.updateTimestamp}" noColon="false" />
         			</div>
         		</th>
         		<th>
         			<div align="center">
         				<kul:htmlAttributeLabel attributeEntry="${subAwardReportAttributes.updateUser}" noColon="false" />
         			</div>
         		</th>
         		<th>
					<div align="center">
						Actions
					</div>
				</th> 
             </tr>
             
                 <c:if test="${!readOnly}">
                <tbody class="addline">
	             <tr>
	                <td align="center" valign="middle" class="infoline">
	                	<div align="center">
	                		Add:
		            	</div>
					</td>
	         		<td class="infoline">
	              		<div align="center">
	            			<kul:htmlControlAttribute property="subAwardAttachmentFormBean.newReport.subAwardReportTypeCode" attributeEntry="${subAwardReportAttributes.subAwardReportTypeCode}"/> 
	              		</div>
	            	</td>
					<td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="subAwardAttachmentFormBean.newReport.updateTimestamp" attributeEntry="${subAwardReportAttributes.updateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	                <td align="left" valign="middle" class="infoline">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="subAwardAttachmentFormBean.newReport.updateUser" attributeEntry="${subAwardReportAttributes.updateUser}" readOnly="true"/>
		            	</div>
					</td> 
					<td align="center" valign="middle" class="infoline">
						<div align="center">
							<html:image property="methodToCall.addReport.anchor${tabKey}"
							src="${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton addButton"/>
						</div>
					</td>
				</tr>
				</tbody>
			 </c:if>
			  
			<c:forEach var="attachment" items="${KualiForm.document.subAwardList[0].subAwardReportList}" varStatus="itrStatus">
				<tr>
	         		<td>
	         			<div align="center">
	                		${itrStatus.index + 1}
		            	</div>
	         		</td>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardReportList[${itrStatus.index}].subAwardReportTypeCode" attributeEntry="${subAwardReportAttributes['subAwardReportTypeCode']}" readOnly="true" readOnlyAlternateDisplay ="${subAwardReportList.typeCode.description}"/>
		            	</div>
					</td>
					<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardReportList[${itrStatus.index}].updateTimestamp" attributeEntry="${subAwardReportAttributes.updateTimestamp}" readOnly="true"/>
		            	</div>
					</td>
	         		<td align="left" valign="middle">
	                	<div align="left">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardReportList[${itrStatus.index}].updateUserName" attributeEntry="${subAwardReportAttributes.updateUser}" readOnly="true"/>
		            	</div>
					</td>
					<td align="center" valign="middle">
						<div align="center">
								<c:if test="${!readOnly}">
								    <html:image property="methodToCall.deleteReport.line${itrStatus.index}.anchor${currentTabIndex}"
									   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"
									   alt="Delete Attachment"/>
							    </c:if>
						 </div>
					</td>
	         	</tr>
			</c:forEach>
		</table> 
     </div>		
</kul:tab>