<%--
 Copyright 2007 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="fullName" required="true"%>
<%@ attribute name="fieldCount" required="true"%>

<div class="tab-container" align="center">
	<div class="h2-container">
		<span class="subhead-left"><h2>${fn:substring(fullName, 0, 50)}...</h2></span>
		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
	</div>
	<div align="left" style="padding:12px;"><strong> Full Group Name: </strong>${fullName}</div>
	<table cellpadding=0 cellspacing="0" class="result-table">
		<c:forEach items="${KualiForm.customAttributeGroups[fullName]}" var="customAttributeDocument" varStatus="status">
			<tr class="datatable">
				<th  align="right">
					<c:if test="${customAttributeDocument.required}">*</c:if>${customAttributeDocument.customAttribute.label}*${customAttributeDocument.customAttributeId}
				</th>
				<td >
				<c:forEach var="customAttributeDocument1" items="${KualiForm.document.customAttributeDocuments}" > 
				  	<c:if test="${customAttributeDocument1.key == customAttributeDocument.customAttributeId}" >
				  	   <c:set var="customAttributeValue" value="${customAttributeDocument1.value.customAttribute.value}" />
				  	</c:if>
				</c:forEach>
				<c:set var="customAttributeId" value="customAttributeValues(id${customAttributeDocument.customAttributeId})" />
                
				<input id="${customAttributeId}" type="text" name="${customAttributeId}" value='${customAttributeValue}'>
					<c:if test="${not empty customAttributeDocument.customAttribute.lookupClass}">
						<kul:lookup boClassName="${customAttributeDocument.customAttribute.lookupClass}" fieldConversions="${customAttributeDocument.customAttribute.lookupReturn}:${customAttributeId}," fieldLabel="${customAttributeDocument.customAttribute.label}"  anchor="${tabKey}"/>
					</c:if>
					<c:if test="${customAttributeDocument.customAttribute.customAttributeDataType.description == 'Date'}">
		                <img src="${ConfigProperties.kr.externalizable.images.url}cal.gif" id="${customAttributeId}_datepicker" style="cursor: pointer;"
		                     title="Date selector" alt="Date selector"
		                     onmouseover="this.style.backgroundColor='red';" onmouseout="this.style.backgroundColor='transparent';" />
		                <script type="text/javascript">
		                  Calendar.setup(
		                          {
		                            inputField : "${customAttributeId}", // ID of the input field
		                            ifFormat : "%m/%d/%Y", // the date format
		                            button : "${customAttributeId}_datepicker" // ID of the button
		                          }
		                  );
		               </script>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>