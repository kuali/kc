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
<%@ attribute name="transparentBackground" required="false" %>
<c:set var="proposalDevelopmentAttributes"
	value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="scienceKeywordAttributes"
	value="${DataDictionary.ScienceKeyword.attributes}" />
<c:set var="textAreaFieldName"
	value="document.developmentProposalList[0].mailDescription" />
<c:set var="action" value="proposalDevelopmentApproverView" />
<c:set var="className"
	value="org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument" />

<kul:tab
	tabTitle="Keywords (${fn:length(KualiForm.document.developmentProposalList[0].propScienceKeywords)})" transparentBackground="${transparentBackground }"
	defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Keywords</span>
		</h3>

		<table cellpadding=0 cellspacing="0" summary="">
			<tr>
				<th><div align="left"></div>
				</th>
				<th><div align="left">
						<kul:htmlAttributeLabel
							attributeEntry="${scienceKeywordAttributes.description}"
							noColon="true" />
					</div>
				</th>
			</tr>
			<logic:iterate name="KualiForm" id="proposalKeywords"
				property="document.developmentProposalList[0].propScienceKeywords"
				indexId="ctr">
				<tr>
					<td class="infoline"><div align="center">${ctr+1}</div>
					</td>

					<td>
						<div align="left">${KualiForm.document.developmentProposalList[0].propScienceKeywords[ctr].scienceKeyword.description}&nbsp;</div>
					</td>
				</tr>
			</logic:iterate>

		</table>
	</div>
</kul:tab>





