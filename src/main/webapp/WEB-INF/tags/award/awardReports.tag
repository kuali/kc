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

<kul:tab tabTitle="Reports" defaultOpen="false"
	tabErrorKey="document.awardList[0].awardReportTermItems,document.award.awardTemplate.REPORTS_TAB"
	auditCluster="reportsAuditErrors"
	tabAuditKey="document.reportTermsAuditRules*" useRiceAuditMode="true">
	<div class="tab-container" align="right">
		<h3>
			<span class="subhead-left">Report Classes</span> <span
				class="subhead-right"> <kul:help
					businessObjectClassName="org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm"
					altText="help" /> </span>
		</h3>

		<c:forEach var="reportClass" items="${KualiForm.reportClasses}"
			varStatus="reportClassIndex">
			<c:if
				test="${KualiForm.reportClassForPaymentsAndInvoices.reportClassCode != reportClass.key}">
				<kra-a:awardReportClasses index="${reportClassIndex.index}"
					reportClassKey="${reportClass.key}"
					reportClassLabel="${reportClass.label}"
					reportCodeLabel="* Report Type" />
			</c:if>
		</c:forEach>
		<br/> <br/>
		<kra-a:awardReportsMiscellaneousProcurementPurchasing />
		<div align="center">

			</br>
			<c:if test="${(!readOnly)}">
				<kra-a:awardSyncButton scopeNames="REPORTS_TAB" tabKey="${tabKey}" />
			</c:if>
		</div>
	</div>

</kul:tab>
