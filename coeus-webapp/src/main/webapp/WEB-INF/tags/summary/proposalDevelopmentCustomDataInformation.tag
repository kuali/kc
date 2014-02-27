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
<c:set var="className"
	value="org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument" />
<kul:tab tabTitle="Custom Data Information" defaultOpen="false" transparentBackground="${transparentBackground }"
	tabErrorKey="">
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Custom Data Information</span>
		</h3>
		<table cellpadding=0 cellspacing="0" summary="">
			<c:forEach items="${KualiForm.document.customAttributeDocuments}"
				var="customAttributeDocument1" varStatus="status">
				<tr>
					<c:set var="customAttributeLabel"
						value="${customAttributeDocument1.value.customAttribute.label}" />
					<c:set var="customAttributeValue"
						value="${customAttributeDocument1.value.customAttribute.value}" />
					<th width="50%"><div align="left">
							<c:out value="${customAttributeLabel}" />
						</div>
					</th>
					<td><div align=center>
							<c:out value="${customAttributeValue}" />
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</kul:tab>


