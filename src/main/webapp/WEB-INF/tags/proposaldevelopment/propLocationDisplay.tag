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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="index" required="true"%>
<%@ attribute name="locationIter" required="true" type="org.kuali.kra.proposaldevelopment.bo.PropLocation"%>
<c:set var="propLocationAttributes"
	value="${DataDictionary.PropLocation.attributes}" />

<tr>
	<th class="infoline">
		<c:out value="${index+1}" />
        <input type="hidden" name="document.propLocation[${index}].proposalNumber" value="${KualiForm.document.proposalNumber}">
        <input type="hidden" name="document.propLocation[${index}].locationSequenceNumber" value="${index+1}">
	</th>
	<td>
		<kul:htmlControlAttribute
			property="document.propLocation[${index}].location"
			attributeEntry="${propLocationAttributes.location}" />
	</td>
	<td>
		<kul:htmlControlAttribute
			property="document.propLocation[${index}].rolodexId"
			attributeEntry="${propLocationAttributes.rolodexId}" />
		<c:choose>
			<c:when test="${empty locationIter.rolodex.addressLine1}">
				<c:out value="(Select)" />
			</c:when>
			<c:otherwise>
				<c:out value="${locationIter.rolodex.addressLine1}" />
			</c:otherwise>
		</c:choose>
		<c:if test="${!empty locationIter.rolodexId}">
			<input type="hidden"
				name="document.propLocation[${index}].rolodex.rolodexId"
				value="${locationIter.rolodexId}">
		</c:if>
		<kul:lookup boClassName="org.kuali.kra.bo.Rolodex"
			fieldConversions="rolodexId:document.propLocation[${index}].rolodexId
                      ,postalCode:document.propLocation[${index}].rolodex.postalCode
                      ,addressLine1:document.propLocation[${index}].rolodex.addressLine1
                      ,addressLine2:document.propLocation[${index}].rolodex.addressLine2
                      ,addressLine3:document.propLocation[${index}].rolodex.addressLine3
                      ,city:document.propLocation[${index}].rolodex.city
                      ,state:document.propLocation[${index}].rolodex.state" />
		<br>
		<c:if test="${!empty locationIter.rolodex.addressLine2}">
			<c:out value="${locationIter.rolodex.addressLine2}" />
			<br />
		</c:if>
		<c:if test="${!empty locationIter.rolodex.addressLine3}">
			<c:out value="${locationIter.rolodex.addressLine3}" />
			<br />
		</c:if>
		<c:if test="${!empty locationIter.rolodex.city || !empty locationIter.rolodex.state || !empty locationIter.rolodex.postalCode}">
			<c:out value="${locationIter.rolodex.city}," />&nbsp
            <c:out value="${locationIter.rolodex.state}" />&nbsp
            <c:out value="${locationIter.rolodex.postalCode}" />
		</c:if>
	</td>
	<td>
				<html:image property="methodToCall.clearAddress.((#${index}#))"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-clraddress.gif' />
				<html:image property="methodToCall.deleteLocation.((#${index}#))"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
	</td>

</tr>
