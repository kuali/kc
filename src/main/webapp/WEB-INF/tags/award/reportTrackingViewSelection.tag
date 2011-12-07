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

<div style="display:none;"><div id="customSelection">
	<html:image property="methodToCall.updateView"
		src="${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif" styleClass="tinybutton"
		onclick="$jq.fancybox.close(); return false;"/><br/>			
	<jsp:useBean id="paramMap" class="java.util.HashMap"/>  
	<c:forEach items="${krafn:getOptionList('org.kuali.kra.award.paymentreports.ReportTrackingViewValuesFinder', paramMap)}" var="option">
		<html:radio property="currentViewIndex" value="${option.key}" onchange="toggleCustomView(this);">${option.label}</html:radio>
	</c:forEach>
	<table id="customViewColumnSelection">
		<tr>
			<th>Columns</th>
			<th>Group</th>
			<th>Detail</th>
		</tr>
		<c:forEach items="${KualiForm.reportTrackingViews.allFields}" var="col" varStatus="ctr">
			<tr>
  				<th><kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes[col]}" noColon="true" readOnly="true"/></th>
				<td><c:set var="propertyName" value="customGroupByFields"/><html:multibox property="${propertyName}"><c:out value="${col}"/></html:multibox>${kfunc:registerEditableProperty(KualiForm, propertyName)}</td>
				<td><c:set var="propertyName" value="customDetailFields"/><html:multibox property="${propertyName}" value="${col}"/>${kfunc:registerEditableProperty(KualiForm, propertyName)}</td>
			</tr>
		</c:forEach>
		<tr>
  			<td colspan="3">
				<html:image property="methodToCall.resetCustomView"
					src="${ConfigProperties.kra.externalizable.images.url}tinybutton-resetcustomview.gif" styleClass="tinybutton"
					onclick="$jq('#onChangeViewClose').attr('name', 'methodToCall.resetCustomView'); $jq.fancybox.close(); return false;"/>
	  		</td>
		</tr>
	</table>
	<html:image property="methodToCall.updateView"
		src="${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif" styleClass="tinybutton"
		onclick="$jq.fancybox.close(); return false;"/> 
</div></div>