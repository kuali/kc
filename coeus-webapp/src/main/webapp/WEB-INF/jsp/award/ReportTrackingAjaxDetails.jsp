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
<c:set var="reportTrackingAttributes" value="${DataDictionary.ReportTracking.attributes}" />

<kul:page lookup="true" showDocumentInfo="false"
	headerMenuBar=""
	headerTitle="Lookup Ajax Details" docTitle="" transactionalDocument="false"
	htmlFormAction="reportTrackingLookup" >
	
  	<table cellpadding="0" cellspacing="0" class="Detail">
  		<thead>
  		<tr class="Detail">
  			<c:forEach items="${KualiForm.detailFields}" var="col">
  				<th class="draggableColumn Detail"><kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes[col]}" noColon="true" readOnly="true"/><div style="display:none;"><c:out value="${col}"/></div></th>
  			</c:forEach>
  			<th>&nbsp;</th>
  		</tr>
  		</thead>
  		<tbody>
  			<c:forEach items="${KualiForm.detailResults}" var="detailLine" varStatus="ctr">
  				<tr class="Detail">
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
