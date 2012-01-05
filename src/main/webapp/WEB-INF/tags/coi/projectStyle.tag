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
<%@ attribute name="disclProjectBean" required="true" type="org.kuali.kra.coi.disclosure.CoiDisclosureProjectBean" %>
<%-- This tag is to highligh the project that are associated with the selected master disclosure --%>
		<jsp:doBody/>
        	         <c:choose>
        	             <c:when test="${disclProjectBean.currentProject}">
        	                <c:set var="style" value="color:yellow;" scope="request"/>
        	             </c:when>
        	             <c:otherwise>
        	                <c:set var="style" value="" scope="request"/>
        	             </c:otherwise>
        	         </c:choose>
		