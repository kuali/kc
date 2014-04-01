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
<%@ attribute name="fullName" required="true"%>
<%@ attribute name="fieldCount" required="true"%>
<%@ attribute name="excludeInactive" required="false" %>
<%@ attribute name="customAttributeGroups" required="true" type="java.util.Map" %>
<%@ attribute name="customDataList" required="true" type="java.util.List" %>
<%@ attribute name="customDataListPrefix" required="true" %>
<%@ attribute name="readOnly" required="false" %>


<c:if test="${empty excludeInactive}" >
	<c:set var="excludeInactive" value="false" />
</c:if>

<c:choose>
		<c:when test="${fn:length(fullName) > 90}">
 					<c:set var="displayName" value="${fn:substring(fullName, 0, 90)}..."/>
		</c:when> 
		<c:otherwise>
 					<c:set var="displayName" value="${fullName}"/>
		</c:otherwise>

</c:choose>

<div class="tab-container" align="center">
	<h3>
		<span class="subhead-left">${displayName}</span>
		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.common.framework.custom.attr.CustomAttribute" altText="help"/></span>
	</h3>
	<div align="left" style="padding:12px;"><strong> Full Group Name: </strong>${fullName}</div>
	<table cellpadding=0 cellspacing="0" class="result-table">
		<c:forEach items="${customAttributeGroups[fullName]}" var="customAttributeDocument" varStatus="status">
		<c:if test="${(excludeInactive eq false) or (excludeInactive eq true && customAttributeDocument.active eq true)}">
			<tr class="datatable">
				<th  align="right">
					<c:if test="${customAttributeDocument.required}">*&nbsp;</c:if>${customAttributeDocument.customAttribute.label}:
				</th>
				<td width="45%">
				<c:forEach var="customAttribute" items="${customDataList}" varStatus="status"> 
					<c:if test="${customAttribute.customAttributeId == customAttributeDocument.customAttributeId}">
						<c:set var="customAttributeIndex" value="${status.index}" />
						<c:set var="customAttributeValue" value="${customAttribute.value}" />
						<c:set var="customAttributeId" value="${customDataListPrefix}[${customAttributeIndex}].value" />
					</c:if>
				</c:forEach>
                        
          	  <c:set var="customAttributeErrorStyle" value="" scope="request"/>
				<c:forEach items="${ErrorPropertyList}" var="key">
				    <c:if test="${key eq customAttributeId}">
					  <c:set var="customAttributeErrorStyle" value="border-color: red" scope="request"/>
				    </c:if>
			     </c:forEach>
					
				<c:if test="${empty customAttributeErrorStyle}" >			
					<c:forEach items="${AuditErrors}" var="cluster">
						<c:forEach items="${cluster.value.auditErrorList}" var="audit">
						    <c:if test="${audit.errorKey eq customAttributeId}">
							  <c:set var="customAttributeErrorStyle" value="border-color: red" scope="request"/>
						    </c:if>
						</c:forEach>
					</c:forEach>
				</c:if>
					
				<c:choose>
                	<c:when test="${readOnly}">
                		<c:out value="${customAttributeValue}" />
                	</c:when>
                	<c:otherwise>
                		${kfunc:registerEditableProperty(KualiForm, customAttributeId)}
                		<input size="60" id="${customAttributeId}" type="text" name="${customAttributeId}" value='${customAttributeValue}' style="${customAttributeErrorStyle}"/>

						<c:if test="${not empty customAttributeDocument.customAttribute.lookupClass}">
						 <c:choose>
						   <c:when test="${customAttributeDocument.customAttribute.lookupClass eq 'org.kuali.kra.bo.ArgValueLookup'}">
							<kul:lookup boClassName="${customAttributeDocument.customAttribute.lookupClass}" 
								lookupParameters="'${customAttributeDocument.customAttribute.lookupReturn}':argumentName"
								readOnlyFields="argumentName"
								fieldConversions="value:${customAttributeId}," 
								fieldLabel="${customAttributeDocument.customAttribute.label}"  anchor="${tabKey}" />
						   </c:when>
						   <c:otherwise>						   
							<kul:lookup boClassName="${customAttributeDocument.customAttribute.lookupClass}" fieldConversions="${customAttributeDocument.customAttribute.lookupReturn}:${customAttributeId}," fieldLabel="${customAttributeDocument.customAttribute.label}"  anchor="${tabKey}"/>
					       </c:otherwise>
					     </c:choose>
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
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:if>
		</c:forEach>
	</table>
</div>
