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
