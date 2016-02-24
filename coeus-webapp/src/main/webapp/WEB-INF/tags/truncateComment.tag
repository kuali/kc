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
<%@ attribute name="textAreaFieldName" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="textAreaLabel" required="true" %>
<%@ attribute name="textValue" required="true" %>
<%@ attribute name="displaySize" required="true" %>


<table border="0" cellpadding="0" cellspacing="0" style="border:none; background-image: inherit; background-position: center bottom;">
   <tbody style="background-image:inherit; background-position: center bottom;">
    <tr style="background-image:inherit; background-position: center bottom;">
        <td align="left" style="border:none; background-image: inherit; background-position: center bottom;">
            <c:choose>
	            <c:when test="${fn:length(textValue) > displaySize}">
                	  ${fn:substring(textValue,0,displaySize - 1)}...
                </c:when>
	            <c:otherwise>
                	  ${textValue}
                </c:otherwise>
            </c:choose>
        </td>
        <td style="border:none; vertical-align:bottom; background-image: inherit; background-position: center bottom;">
            <div align="right" style="background-image: inherit; background-position: center bottom;">
                <c:if test="${fn:length(textValue) > displaySize}">
                    <%-- so that the JS can grab the value from the opener...got to be a better way to do this...--%>
                    <html:hidden property="${textAreaFieldName}" write="false" styleId="${textAreaFieldName}" />
                    <kul:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${textAreaLabel}"  readOnly="true" />
                </c:if>
            </div>
        </td>
    </tr>
    </tbody>
</table>


