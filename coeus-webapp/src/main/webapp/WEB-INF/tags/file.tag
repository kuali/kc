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

<%@ attribute name="property" required="true" %>

<%-- THIS TAG IS NOT A STANDARD PART OF RICE.  THIS IS A KC EXTENSION --%>

<%-- attachment file error handling logic start--%>
    <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
    <%-- highlighting does not work in firefox but does in ie... --%>
    <c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
<%-- attachment file error handling logic end--%>
                        
<html:file property="${property}" style="${textStyle}" />
