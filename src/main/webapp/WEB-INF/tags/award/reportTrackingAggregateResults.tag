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

<div id="workarea" class="tab-container reportTrackingResults">			  
	${kfunc:registerEditableProperty(KualiForm, "groupByResultIndex")}
	<c:if test="${not empty KualiForm.groupedByResults}">
		<script>$jq(document).ready(function() { toggleSearchTable($jq('.showHideSearch')); });</script>
		<table cellpadding="0" cellspacing="0" class="aggregateTable">
			<tr>
				<th>&nbsp;</th>
				<c:forEach items="${KualiForm.groupedByDisplayFields}" var="col">
					<th><kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes[col]}" noColon="true" readOnly="true"/></th>
				</c:forEach>
				<th>Count</th>
			</tr>
			<c:forEach items="${KualiForm.groupedByResults}" var="resultLine" varStatus="ctr">
				<tr class="aggregateResult">
			    	<td><a class="showHideLink showLink">show and hide details</a><div style="display:none;" title="none"><c:out value="${ctr.index}"/></div></td>
					<c:forEach items="${KualiForm.groupedByDisplayFields}" var="col">
						<td>
							<bean:write name="KualiForm" property="groupedByResults[${ctr.index}].${col}"/>
						</td>
					</c:forEach>
					<td><c:out value="${resultLine['itemCount']}"/></td>
				</tr>
				<tr class="detailRow" style="display: none;">
			  		<td colspan="${fn:length(KualiForm.groupedByDisplayFields)+2}">
			    		<div></div>
			  		</td>
				</tr>
			</c:forEach>
	</c:if>
</div>