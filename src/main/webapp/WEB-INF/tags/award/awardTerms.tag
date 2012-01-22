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
<c:set var="syncPropertyName" value="awardSponsorTerms" />
<c:set var="action" value="awardTemplateSync" />

<kul:tab tabTitle="Terms" defaultOpen="false" tabErrorKey="document.awardList[0].awardSponsorTerms*,document.award.awardTemplate.TERMS_TAB" auditCluster="termsAuditErrors" tabAuditKey="document.termsAuditRules*" useRiceAuditMode="true">
	<div class="tab-container" align="center">
	    <h3>
            <span class="subhead-left">Terms</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm" altText="help"/></span>
        </h3>  
        <c:forEach var="sponsorTermType" items="${KualiForm.sponsorTermFormHelper.sponsorTermTypes}" varStatus="sponsorTermTypeIndex">        	        	
			<kra-a:awardTermsTypes index="${sponsorTermTypeIndex.index}" sponsorTermTypeKey="${sponsorTermType.key}" sponsorTermTypeLabel="${sponsorTermType.value}" />
		</c:forEach>
		
		<br/>
		
		<c:if test="${!readOnly}">
		<kra-a:awardSyncButton scopeNames="TERMS_TAB" tabKey="${tabKey}"/>
		</c:if>
	</div>
</kul:tab>

