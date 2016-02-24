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
<%@ attribute name="response" required="true" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="ruleId" required="true" %>
<%@ attribute name="topQuestion" required="false" %>

    <table cellspacing="0" cellpadding="0"
					style="width: 100%; border-top: medium none;" class="elementtable">
	   <tbody>
<c:choose>
    <c:when test = "${empty value and !KualiForm.readOnly}">
        <tr>
            <th style="text-align: center; width: 150px;">
                Add
            </th>
            <td style="text-align: center;" class="content_info">
               <c:if test="${empty topQuestion or topQuestion == 'false'}" >
                 Parent Response
                 </c:if>
            <select name="CustomData" id="parentResponse3">
                <option selected="selected" value="0">
                     select
                </option>
               <c:if test="${empty topQuestion or topQuestion == 'false'}" >
                <option value="1">
                     Contains text value
                </option>
                <option value="2">
                     Begins with text
                </option>
                <option value="3">
                     Ends with text
                </option>
                <option value="4">
                     Matches text
                </option>
                <option value="5">
                     Less than number
                </option>
                <option value="6">
                     Less than or equals number
                </option>
                <option value="7">
                     Equals number
                </option>
                <option value="8">
                     Not Equal to number
                </option>
                <option value="9">
                     Greater than or equals number
                </option>
                <option value="10">
                     Greater than number
                </option>
                <option value="11">
                     Before date
                </option>
                <option value="12">
                     After date
                </option>
                </c:if>
            </select>
            </td>
            <td style="text-align: center;" class="content_info">
                 Value:
                <input id ="reqVal${qidx}" name ="reqVal${qidx}" type="text" size="25">
            </td>
            <td style="width: 65px; text-align: center;" class="content_info">
                <input type="image" alt="add" style="border: medium none;"
	                src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" name="addrequirement"
	                id="addrequirement${qidx}" onClick="addrequirement(${qidx}); return false;">
            </td>
        </tr>
    </c:when>
    <c:when test = "${!empty value}">
        <tr>
		    <th
			    style="text-align: left; border-top: medium none; width: 150px;">Current
					Requirements:</th>
		    <td colspan="2" style="text-align: left; border-top: medium none;">${response}  : ${value} </td>
		    <td
			    style="text-align: center; border-top: medium none; width: 65px;"
			    class="content_white">
			    <c:if test="${!KualiForm.readOnly}">
			    <input type="image" alt="delete" style="border: medium none;" onClick="clickDeleteResponse(${qidx})"
					src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" id="deletereq${qidx}">
				</c:if>
			</td>
	    </tr>
    </c:when>
</c:choose>
<c:choose>
	<c:when test = "${(empty ruleId or ruleId eq 'null') and !KualiForm.readOnly}" >
        <tr>
          <th style="text-align: center; width: 150px;">
	               Add Rule
   	      </th>
       	  <td colspan="2">
        	<input id ="ruleId${qidx}" name ="ruleId${qidx}" type="text" size="25">
               <a href="#"><img border="0" title="Search Rule"
                            alt="Search Rule" class="tinybutton" name="searchRule${qidx}"
                            id="searchRule${qidx}" src="static/images/searchicon.gif"  onClick="clickSearchRule('ruleId${qidx}')"></a>
           </td>
            <td style="width: 65px; text-align: center;" class="content_info">
                <input type="image" alt="add" style="border: medium none;"
	                src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" name="addRuleRequirement"
	                id="addRuleRequirement${qidx}" onClick="addRuleRequirement(${qidx}); return false;">
            </td>
        </tr>
	</c:when>
	<c:when test = "${!(empty ruleId or ruleId eq 'null')}">
        <tr>
		    <th style="text-align: left; border-top: medium none; width: 150px;">Rule Id:</th>
		    <td colspan="2" style="text-align: left; border-top: medium none;">${ruleId}
		    	<input id ="ruleId${qidx}" name ="ruleId${qidx}" value="${ruleId}" type="hidden"> 
		    </td>
		    <td
			    style="text-align: center; border-top: medium none; width: 65px;"
			    class="content_white">
			    <c:if test="${!KualiForm.readOnly}">
			    <input type="image" alt="delete" style="border: medium none;" onClick="clickDeleteRule(${qidx})"
					src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" id="deleteRule${qidx}">
				</c:if>
			</td>
					
	    </tr>
	</c:when>
</c:choose>
<c:if test="${empty value and (empty ruleId or ruleId eq 'null') and KualiForm.readOnly}">
	<tr>
		<td colspan="3" style="text-align: center;">No Requirements for Display</td>
	</tr>
</c:if>
      
		</tbody>
	</table>
    <table cellspacing="0" cellpadding="0"
			style="width: 100%; border-top: medium none;" class="elementtable"
			id="tbl266-${qidx}">
		<tbody></tbody>
	</table>
    
