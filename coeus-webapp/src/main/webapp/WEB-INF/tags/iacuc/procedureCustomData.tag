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

<%@ attribute name="collectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all the current Procedure person responsible" %>
<%@ attribute name="collectionProperty" required="true" 
              description="The property name of the collection that holds all the current Procedure person responsible" %>
<%@ attribute name="procedureBeanIndex" required="true" 
              description="The procedure bean index" %>
<%@ attribute name="procedureDetailBeanIndex" required="true" 
              description="The procedure detail bean index" %>
<%@ attribute name="procedureCategoryName" required="true" 
              description="The procedure name" %>

<c:set var="modifyPermission" value="${KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}" />
<c:set var="readOnly" value="${!modifyPermission}" />

<kul:innerTab tabTitle="Custom Data : ${procedureCategoryName}" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">
	<div class="innerTab-container" align="left">
    	<h3>
    		<span class="subhead-left">Custom Data : ${procedureCategoryName}</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="${businessObjectClassName}" altText="help"/></span>
        </h3>
    </div>
   	<table id="included-locations-table" cellpadding=0 cellspacing=0 summary="">
        	<c:forEach var="protocolCustomData" items="${collectionReference}" varStatus="status">
				<c:set var="customAttributeId" value="${collectionProperty}[${status.index}].value" />
				<c:set var="customAttributeValue" value="${protocolCustomData.value}" />
          	    <c:set var="customAttributeErrorStyle" value="" scope="request"/>
				<c:set var="largeText" value="${protocolCustomData.largeText}" />
				<c:set var="textLength" value="${protocolCustomData.iacucProcedureCategoryCustomData.dataLength}" />
				<c:set var="textTitle" value="${protocolCustomData.iacucProcedureCategoryCustomData.label}" />
				<c:set var="textSize" value="100" />
				<tr class="datatable">
					<th  align="right">
						${protocolCustomData.iacucProcedureCategoryCustomData.label}:
					</th>
					<td width="60%">
						<c:choose>
		                	<c:when test="${readOnly}">
		                		<c:out value="${customAttributeValue}" />
		                	</c:when>
		                	<c:otherwise>
		                		<c:if test="${protocolCustomData.iacucProcedureCategoryCustomData.customAttributeDataType.description == 'Date'}">
									<c:set var="textSize" value="10" />
		                		</c:if>
		                	
		                	    ${kfunc:registerEditableProperty(KualiForm, customAttributeId)}
		                        <c:if test="${empty protocolCustomData.iacucProcedureCategoryCustomData.lookupClass}">
									<c:choose>
			                			<c:when test="${largeText}">
											<textarea name="${customAttributeId}" tabindex="1" cols="150" rows="3" onkeyup="textLimit(this, ${textLength});" id="${customAttributeId}" style="" class="" title="${textTitle}">${customAttributeValue}</textarea>
											<input type="image" name="methodToCall.updateTextArea.((`${customAttributeId}:iacucProtocolProcedures:${textTitle}:false:${textLength}`))" src="/kc-dev/kr/static/images/pencil_add.png" onclick="javascript: textAreaPop('${customAttributeId}','iacucProtocolProcedures','${textTitle}','88888888','false','${textLength}');return false" class="tinybutton" title="${textTitle}" alt="Expand Text Area">
			                			</c:when>
			                			<c:otherwise>
				                		    <input id="${customAttributeId}" type="text" name="${customAttributeId}" value='<c:out value="${customAttributeValue}" escapeXml="true" />' style="${customAttributeErrorStyle}" maxlength="${textLength}" size="${textSize}"/>
			                			</c:otherwise>
									</c:choose>
		                        </c:if>
		
								<c:if test="${not empty protocolCustomData.iacucProcedureCategoryCustomData.lookupClass}">
								  <c:out value="${customAttributeValue}" />
								  <c:choose>
								   <c:when test="${protocolCustomData.iacucProcedureCategoryCustomData.lookupClass eq 'org.kuali.coeus.common.framework.custom.arg.ArgValueLookup'}">
										<kul:lookup boClassName="${protocolCustomData.iacucProcedureCategoryCustomData.lookupClass}" 
											lookupParameters="'${protocolCustomData.iacucProcedureCategoryCustomData.lookupReturn}':argumentName"
											readOnlyFields="argumentName"
											fieldConversions="value:${customAttributeId}," 
											fieldLabel="${protocolCustomData.iacucProcedureCategoryCustomData.label}"  anchor="${tabKey}" />		
								   </c:when>
								   <c:otherwise>
									<kul:lookup boClassName="${protocolCustomData.iacucProcedureCategoryCustomData.lookupClass}" fieldConversions="${protocolCustomData.iacucProcedureCategoryCustomData.lookupReturn}:${customAttributeId}," fieldLabel="${protocolCustomData.iacucProcedureCategoryCustomData.label}"  anchor="${tabKey}" />
								   </c:otherwise>
		                          </c:choose>
								  <c:if test="${not empty customAttributeValue}">
		                            <html:image property="methodToCall.clearLookupValue" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" title="Clear Lookup Value" alt="Clear Lookup Value" value="${protocolCustomData.procedureCustomAttributeId}" styleClass="tinybutton"/>
		                          </c:if>
								</c:if>
								
								<c:if test="${protocolCustomData.iacucProcedureCategoryCustomData.customAttributeDataType.description == 'Date'}">
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
</kul:innerTab>
