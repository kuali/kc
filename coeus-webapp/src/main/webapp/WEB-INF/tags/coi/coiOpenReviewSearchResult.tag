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
<%@ attribute name="reviews" required="true" type="java.util.Collection" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="showApprove" required="true" %>
<c:set var="disclosureAttributes" value="${DataDictionary.CoiDisclosure.attributes}" />

        <h3>
            <span class="subhead-left"><c:out value="${name}"/></span>
        </h3>
        
        <table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpadding="0" cellspacing="0" width="50%" align="center">
	    	<tr>
	    		<th>&nbsp;</th>
	    		<th>Disclosure Number</th>
	    		<th>Owned by</th>
	    		<th>Department</th>
	    		<th>Last Update</th>
	    		<th>Expiration Date</th>
	    	</tr>	    	
	    	<c:forEach items="${reviews}" var="review">
	    		<tr>
	    			<td><a href="#" onclick="jQuery(this).parent().parent().next().find('.disclDetails').toggle(); jQuery(this).find('img').toggle(); return false;">
	    				<img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif" title="show details" alt="show details" class="tinybutton"/>
	    				<img src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" title="hide details" alt="hide details" class="tinybutton" style="display:none;"/></a></td>
	    			<td><a target="_blank" href="coiDisclosure.do?docId=${review.coiDisclosureDocument.documentNumber}&docTypeName=CoiDisclosureDocument&methodToCall=docHandler&command=displayDocSearchView"><c:out value="${review.coiDisclosureNumber}"/></a></td>
					<td><c:out value="${review.disclosurePersons[0].reporter.userName}"/></td>
					<td><c:out value="${review.disclosurePersons[0].reporter.unit.unitName}"/></td>
					<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${review.updateTimestamp}"/></td>
					<td><fmt:formatDate pattern="MM/dd/yyyy" value="${review.expirationDate}"/></td>
				</tr>
				<tr>
					<td colspan="6" style="margin:0; padding:0;"><div style="display:none; text-align: center; padding: 10px;" class="disclDetails"><kra-coi:coiOpenReviewSearchSummary discl="${review}" showApprove="${showApprove}"/></div></td>
				</tr>
			</c:forEach>
		</table>
