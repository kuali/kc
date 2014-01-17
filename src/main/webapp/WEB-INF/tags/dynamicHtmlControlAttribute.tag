<%--
 Copyright 2005-2014 The Kuali Foundation

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
<%@ attribute name="property" required="true" description="The property being rendered." %>
<%@ attribute name="attributeEntry" required="true" type="java.util.Map" description="The Map of data dictionary attributes about the property to render a control for." %>
<%@ attribute name="initialReadOnly" required="false" description="Whether this control, on initial page load, should be rendered as read only (ie, not a control but rather text) or not.  If the entire control is read-only, then this is ignored." %>
<%@ attribute name="readOnly" required="false" description="Whether this control should be rendered as read only (ie, not a control but rather text) or not." %>
<%@ attribute name="readOnlyBody" required="false" description="when readOnly, use the tag body instead of a written hidden field.  This allows mixing in module-specific inquiries." %>
<%@ attribute name="errorBlock" required="false" description="Whether this control should be rendered with an error block that displays a message during an error." %>
<%@ attribute name="errorBlockMessage" required="false" description="The error message to display in the error block.  The default is 'Error'" %>
<%@ attribute name="staticOnly" required="false" description="Only use the default static htmlControlAttribute, turning off the elements that enable dynamic mode." %>

<c:choose>
	<c:when test="${!staticOnly}">
		<c:choose>
		    <c:when test="${initialReadOnly || readOnly}">
		        <c:set var="initialReadStyle" value="display: inline" />
		        <c:set var="initialEditStyle" value="display: none" />
		        <c:set var="initialErrorStyle" value="display: none" />
		    </c:when>
		    <c:otherwise> 
		        <c:set var="initialReadStyle" value="display: none" />
		        <c:set var="initialEditStyle" value="display: inline" />
		        <c:set var="initialErrorStyle" value="display: none" />
		    </c:otherwise>
		</c:choose>
		
		<span class="dynamicReadDiv" style="${initialReadStyle}">
		    <kul:htmlControlAttribute property="${property}" 
		                              attributeEntry="${attributeEntry}" 
		                              readOnly="true" 
		                              readOnlyBody="${readOnlyBody}">
		        <jsp:doBody/>
		    </kul:htmlControlAttribute>
		</span>
		
		<span class="dynamicEditDiv" style="${initialEditStyle}">
		    <kul:htmlControlAttribute property="${property}" 
		                              attributeEntry="${attributeEntry}" 
		                              readOnly="${readOnly}" 
		                              readOnlyBody="${readOnlyBody}">
		        <jsp:doBody/>
		    </kul:htmlControlAttribute>
		</span>
		
		<c:if test="${errorBlock}">
		    <c:if test="${empty errorBlockMessage}">
		        <c:set var="errorBlockMessage" value="Error" />
		    </c:if>
			<div class="dynamicErrorDiv" style="${initialErrorStyle}">
			    <span style="color: red;">${errorBlockMessage}</span>
			</div>
		</c:if>
    </c:when>
    <c:otherwise>
        <kul:htmlControlAttribute property="${property}" 
                                  attributeEntry="${attributeEntry}" 
                                  readOnly="${readOnly}" 
                                  readOnlyBody="${readOnlyBody}">
            <jsp:doBody/>
        </kul:htmlControlAttribute>
    </c:otherwise>
</c:choose>