<%--
 Copyright 2005-2010 The Kuali Foundation

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

<c:set var="goalsAndExpendituresAttributes" value="${DataDictionary.AwardSubcontractingGoalsExpenditures.attributes}" />

<div class="tab-container" align="center" id="awardNumberLookupPanel">
	<h3>
		<span class="subhead-left">
			Award Number Lookup
		</span> 
		<span class="subhead-right">
			<kul:help businessObjectClassName="org.kuali.kra.award.subcontracting.AwardSubcontractingGoalsExpenditures" altText="help" />
		</span>
	</h3>
	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="infoline">
				<div align="center">
					<kul:htmlAttributeLabel	attributeEntry="${goalsAndExpendituresAttributes.awardNumber}" />
					<input type="hidden" name="subPlanFlag" value="Y">
					<kul:htmlControlAttribute property="awardNumber" attributeEntry="${goalsAndExpendituresAttributes.awardNumber}" onchange="submitFormToMethod('kualiForm', 'refresh');" />
					<kul:lookup boClassName="org.kuali.kra.award.home.Award" fieldConversions="awardNumber:awardNumber,awardId:awardId" lookupParameters="subPlanFlag:subPlanFlag" anchor="${tabKey}" />
					<html:hidden property="awardId" />
					<kul:directInquiry boClassName="org.kuali.kra.award.home.Award" inquiryParameters="awardId:awardId" anchor="${tabKey}" />
					
					<noscript>
		            	<html:image property="methodToCall.refresh" 
		                	src="${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif" 
		                 	title="refresh" 
		                 	alt="refresh" 
		                 	styleId="refreshButton"
		                 	styleClass="tinybutton"/>
				    </noscript>
				</div>
			</td>
		</tr>
	</table>
</div>
