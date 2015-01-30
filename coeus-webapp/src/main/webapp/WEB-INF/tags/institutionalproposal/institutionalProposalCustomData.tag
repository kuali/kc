<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<%@ attribute name="fullName" required="true"%>
<%@ attribute name="fieldCount" required="true"%>

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
		<span class="subhead-left"><c:out value="${displayName}" /></span>
		<span class="subhead-right"><kul:help parameterNamespace="KC-IP" parameterDetailType="Document" parameterName="customData1HelpUrl" altText="help"/></span>
	</h3>
	<div align="left" style="padding:12px;"><strong> Full Group Name: </strong>${fullName}</div>
	<table cellpadding=0 cellspacing="0" class="result-table">
		<c:forEach items="${KualiForm.institutionalProposalCustomDataFormHelper.customAttributeGroups[fullName]}" var="customAttributeDocument" varStatus="status">
			<tr class="datatable">
				<th  align="right">
					<c:if test="${customAttributeDocument.required}">*</c:if>${customAttributeDocument.customAttribute.label}:
				</th>
				<td width="45%">

				<c:forEach var="institutionalProposalCustomData" items="${KualiForm.document.institutionalProposalList[0].institutionalProposalCustomDataList}" > 
				  	<c:if test="${institutionalProposalCustomData.id == customAttributeDocument.id}" >
				  	   <c:set var="customAttributeValue" value="${institutionalProposalCustomData.value}" />
				  	</c:if>
				</c:forEach>
				<c:set var="id" value="institutionalProposalCustomDataFormHelper.customDataValues[${customAttributeDocument.customAttributeId-1}].value" />
				
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
                	<kul:htmlControlAttribute property="${customAttributeId}" attributeEntry="${DataDictionary.InstitutionalProposalCustomData.attributes.value}"/>
						<c:if test="${not empty customAttributeDocument.customAttribute.lookupClass}">
						 <c:choose>
						   <c:when test="${customAttributeDocument.customAttribute.lookupClass eq 'org.kuali.coeus.common.framework.custom.arg.ArgValueLookup'}">
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
		</c:forEach>
	</table>
</div>
