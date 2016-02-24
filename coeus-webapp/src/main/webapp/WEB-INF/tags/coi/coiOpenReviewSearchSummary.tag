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
<%@ attribute name="discl" required="true" type="org.kuali.kra.coi.CoiDisclosure" %>
<%@ attribute name="showApprove" required="true" %>
<c:set var="disclosureAttributes" value="${DataDictionary.CoiDisclosure.attributes}" />
        
        <table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpadding="0" cellspacing="0" width="50%" align="center">
        	<tr>
        		<th>Event Type</th>
        		<th>Event #</th>
        		<th>Review Status</th>
        		<th>Last Update</th>
        		<th>Submit Date</th>
        		<th>&nbsp;</th>
        	</tr>
        	<tr>
        		<td><c:out value="${discl.coiDisclosureEventType.description}" /></td>
        		<td><c:out value="${discl.coiDisclProjectId}"/></td>
        		<td><c:out value="${discl.coiReviewStatus.description}"/></td>
        		<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${discl.updateTimestamp}"/></td>
        		<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${discl.certificationTimestamp}"/></td>
        		<td><a target="_blank" href="coiDisclosure.do?docId=${discl.coiDisclosureDocument.documentNumber}&docTypeName=CoiDisclosureDocument&methodToCall=printDisclosureCertification&command=displayDocSearchView">
	        		<img src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif' 
						alt="Print Review" class="tinybutton" onclick="excludeSubmitRestriction=true"/></a>
					<c:if test="${showApprove}">
	        			<html:image property="methodToCall.quickApproveDisclosure.disclosureDocNbr${discl.coiDisclosureDocument.documentNumber}" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-approve.gif' 
							alt="Approve Disclosure" styleClass="tinybutton" />
					</c:if>
					</td>
        	</tr>
        </table>
