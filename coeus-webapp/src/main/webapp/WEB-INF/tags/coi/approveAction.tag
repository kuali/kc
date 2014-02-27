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
  		    <div class="body" style="text-align:left;color:#FF0000;">			
               <strong>${disclosureHelper.annualCertApprovalErrorMsgForAdmin}</strong>
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

