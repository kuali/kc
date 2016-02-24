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

<c:set var="attributes" value="${DataDictionary.CoiDispositionStatus.attributes}" />
<c:set var="coiDisclosureStatusAttributes" value="${DataDictionary.CoiDisclosureStatus.attributes}" />
<c:set var="coiDispositionStatusAttributes" value="${DataDictionary.CoiDispositionStatus.attributes}" />
<c:set var="coiDisclosureStatusCode" value="${KualiForm.coiDisclosureStatusCode}"/>  
<c:set var="disclosureHelper" value="${KualiForm.disclosureHelper}" />

<jsp:useBean id="paramMap" class="java.util.HashMap"/>
 
	<div class="tab-container" align="center">
		<h3> 
			<span class="subhead-left">Approval Action</span>
            <span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="coiAdministratorActionHelp" altText="help"/></span>
 		</h3>
	    <c:if test="${disclosureHelper.unresolvedEventsPresent}">
	        <c:set var="readOnlyApprove" value="true" />
            <div class="alert alert-error">
                ${disclosureHelper.annualCertApprovalErrorMsgForAdmin}
  		    </div>
		</c:if>
		<h4><div class="body">Disclosure Status is set to '<c:out value="${KualiForm.disclosureActionHelper.maximumDispositionStatus.description}"/>' based on the Project-Financial Entity conflict status.</div></h4>
		<div style="text-align: center;" class="coiActionButtons">
			<c:choose><c:when test="${KualiForm.disclosureActionHelper.maximumDispositionStatus.coiDisclosureStatusCode == '4'}">
				<html:image property="methodToCall.disapprove.anchor${tabKey}"
								            src='${ConfigProperties.kr.externalizable.images.url}buttonsmall_disapprove.gif' styleClass="tinybutton"/>
			</c:when><c:otherwise>
				<html:image property="methodToCall.approve.anchor${tabKey}"
								            src='${ConfigProperties.kr.externalizable.images.url}buttonsmall_approve.gif' styleClass="tinybutton"/>
			</c:otherwise></c:choose>		
   		</div>
   		<div style="text-align: center; display:none;" class="coiActionButtonsDisabledMessage">After a project-financial entity relationship change you must save before approving.</div>
   		<script>
   			jQuery('select[name$="entityDispositionCode"]').change(function() { jQuery('.coiActionButtons').hide(); jQuery('.coiActionButtonsDisabledMessage').show(); });
   		</script>
   	</div>

