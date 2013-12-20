<%--
 Copyright 2005-2013 The Kuali Foundation

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