<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="response" required="true" %>
<%@ attribute name="value" required="true" %>
    <table cellspacing="0" cellpadding="0"
					style="width: 100%; border-top: medium none;" class="elementtable">
	   <tbody>
<c:choose>
    <c:when test = "${empty value}">
        <tr>
            <th style="text-align: center; width: 150px;">
                Add
            </th>
            <td style="text-align: center;" class="content_info">
                 Parent Response
            <select name="CustomData" id="parentResponse3">
                <option selected="selected" value="0">
                     select
                </option>
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
            </select>
            </td>
            <td style="text-align: center;" class="content_info">
                 Value:
                <input type="text" size="25">
            </td>
            <td style="width: 65px; text-align: center;" class="content_info">
                <input type="image" alt="add" style="border: medium none;"
	                src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" name="addrequirement"
	                id="addrequirement${qidx}" onClick="addrequirement(${qidx}); return false;">
            </td>
        </tr>
    </c:when>
    <c:otherwise>
        <tr>
		    <th
			    style="text-align: left; border-top: medium none; width: 150px;">Current
					Requirements:</th>
		    <td style="text-align: left; border-top: medium none;">${response}  : ${value} </td>
		    <td
			    style="text-align: center; border-top: medium none; width: 65px;"
			    class="content_white">
			    <input type="image" alt="delete" style="border: medium none;" onClick="clickDeleteResponse(${qidx})"
					src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" id="deletereq${qidx}"></td>
	    </tr>
    </c:otherwise>
</c:choose>
      
		</tbody>
	</table>
    <table cellspacing="0" cellpadding="0"
			style="width: 100%; border-top: medium none;" class="elementtable"
			id="tbl266-${qidx}">
		<tbody></tbody>
	</table>
    