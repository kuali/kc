<%--
 Copyright 2006-2009 The Kuali Foundation

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
<%@ attribute name="property" required="true" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="valuesFinder" required="true" %>
<%@ attribute name="readOnly" required="false" %>
<%@ attribute name="tabindex" required="false" %>
<%@ attribute name="styleClass" required="false" 
			  description="When a field has a css class applied to it, make sure that
			  we carry it through."%>

<c:set var="selectOptions" value="" />
                    
<c:forEach items="${krafn:getOptionList(valuesFinder, parameterMap)}" var="option">
	<c:choose>
		<c:when test="${value == option.key}">
			<c:set var="selectOptions" value="${selectOptions}${'<option value=\"'}${option.key}${'\" selected=\"selected\">'}${option.label}${'</option>'}" />
			<c:set var="readOnlyAlternateDisplay" value="${option.label}" />
		</c:when>
		<c:otherwise>
			<c:set var="selectOptions" value="${selectOptions}${'<option value=\"'}${option.key}${'\" >'}${option.label}${'</option>'}" />
		</c:otherwise>
	</c:choose>
</c:forEach> 

 <c:choose>
    <c:when test="${readOnly}">
    	<c:out value="${readOnlyAlternateDisplay}"/>	 
     </c:when>
 	<c:otherwise>
 	<html:select property="${property}" tabindex="${tabindex}" >  
   		${selectOptions}   
	</html:select> 
	</c:otherwise>  
</c:choose>  
