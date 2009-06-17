<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />

<kul:tab tabTitle="Post Submission Status" defaultOpen="false">

	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Post Submission Status</span>
    		<span class="subhead-right"><%-- <kul:help businessObjectClassName="FILLMEIN" altText="help"/> --%></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
				<th align="right" valign="middle"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.postSubmissionStatusCode}" /></th>
				<td align="left" valign="middle" width="74%"><kul:htmlControlAttribute property="document.postSubmissionStatusCode" readOnly="${KualiForm.submissionStatusReadOnly}" attributeEntry="${proposalDevelopmentAttributes.postSubmissionStatusCode}" /></td>
			</tr>
        </table>
        
	</div>
</kul:tab>
