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

