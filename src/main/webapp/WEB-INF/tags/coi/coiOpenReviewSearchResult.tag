<%--
 Copyright 2005-2014 The Kuali Foundation

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
