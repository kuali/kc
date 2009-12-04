<%--
 Copyright 2007 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="isLookup" required="true" description="(laran) Is the view a lookup? Not sure exactly why it's important." %>
<%@ attribute name="isRequired" required="true" description="Is the field a required field?" %>
<%@ attribute name="isReadOnly" required="true" description="Is the field read only?" %>
<%@ attribute name="cellWidth" required="true" description="How wide should the label cell be?" %>
<%@ attribute name="fieldName" required="true" description="What the Field Name is?" %>
<%@ attribute name="fieldType" required="true" description="What type of field is being displayed?" %>
<%@ attribute name="fieldLabel" required="true" description="What's the label to show for the field?" %>

<c:if test="${isLookup || (!(empty fieldType) && not isLookup)}">
                            
	<th class="grid" style="width:${cellWidth};" align="right">
	<c:if test="${!isReadOnly}">
<label id="${fieldName}.label" for="${fieldName}">
</c:if>
    	
		<%--<c:out value="fieldType is ${fieldType}, isReadOnly is ${isReadOnly}, cellWidth is ${cellWidth}<br/>" escapeXml="false" />--%>

		<c:choose>
    	
			<c:when test="${!(empty fieldLabel)}">
        	
				<c:if test="${isRequired && !isReadOnly}">
                
					${Constants.REQUIRED_FIELD_SYMBOL}&nbsp;
                    
				</c:if>
        	
				<c:out value="${fieldLabel}" />:
            	
			</c:when>
            
			<c:otherwise>
            
				&nbsp;
            	
			</c:otherwise>
            
		</c:choose>

<c:if test="${!isReadOnly}">    	
    	</label>
    	</c:if>
    	
	</th>
    </c:if>
