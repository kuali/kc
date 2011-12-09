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
<c:set var="reportTrackingAttributes" value="${DataDictionary.ReportTracking.attributes}" />

<kul:page lookup="true" showDocumentInfo="false"
	headerMenuBar=""
	headerTitle="Lookup Ajax Details" docTitle="" transactionalDocument="false"
	htmlFormAction="reportTrackingLookup" >
	
  	<table cellpadding="0" cellspacing="0" class="Detail">
  		<thead>
  		<tr>
  			<c:forEach items="${KualiForm.detailFields}" var="col">
  				<th class="draggableColumn Detail"><kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes[col]}" noColon="true" readOnly="true"/><div style="display:none;"><c:out value="${col}"/></div></th>
  			</c:forEach>
  			<th>&nbsp;</th>
  		</tr>
  		</thead>
  		<tbody>
  			<c:forEach items="${KualiForm.detailResults}" var="detailLine" varStatus="ctr">
  				<tr>
  				<c:forEach items="${KualiForm.detailFields}" var="col">
  					<td>
  						<bean:write name="KualiForm" property="detailResults[${ctr.index}].${col}"/>
  					</td>
  				</c:forEach>
  				<td style="text-align: center;">
  				  <html:image property="methodToCall.openAwardReports.awardNumber${KualiForm.detailResults[ctr.index].awardNumber}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-openaward.gif' styleClass="tinybutton"/>
				</td>
  				</tr>	
  			</c:forEach>
  		</tbody>
  	</table>
</kul:page>