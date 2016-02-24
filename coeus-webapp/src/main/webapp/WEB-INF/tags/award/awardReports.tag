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
<c:set var="reportTrackingReadOnly" value="${!KualiForm.permissionsHelper.maintainAwardReportTracking }"/>
<kul:tab tabTitle="Reports" defaultOpen="false"
	tabErrorKey="document.awardList[0].awardReportTermItems,document.award.awardTemplate.REPORTS_TAB,methodToCall.selectAllMultEdit.AwardReportTermItemsIndex*,methodToCall.selectNoneMultiEdit.AwardReportTermItemsIndex*,awardReportTerm"
	auditCluster="reportsAuditErrors"
	tabAuditKey="document.reportTermsAuditRules*" useRiceAuditMode="true">
	<div class="tab-container" align="right">
		<h3>
			<span class="subhead-left">Report Classes</span>            
			<span class="subhead-right"><kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardReportsHelpUrl" altText="help"/></span>      
		</h3>

		<c:forEach var="reportClass" items="${KualiForm.reportClasses}"
			varStatus="reportClassIndex">
			<c:if
				test="${KualiForm.reportClassForPaymentsAndInvoices.reportClassCode != reportClass.key}">
				<kra-a:awardReportClasses index="${reportClassIndex.index}"
					reportClassKey="${reportClass.key}"
					reportClassLabel="${reportClass.value}"
					reportCodeLabel="* Report Type" />
			</c:if>
		</c:forEach>
		<br/> <br/>
		<kra-a:awardReportsMiscellaneousProcurementPurchasing />
		<div align="center">

			</br>
			<c:if test="${(!readOnly)}">
				<kra-a:awardSyncButton scopeNames="REPORTS_TAB" tabKey="${tabKey}" />
				<br/>
			</c:if>
			<c:if test="${!reportTrackingReadOnly && readOnly}">
				<html:image property="methodToCall.save" 
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-apply.gif' 
					alt="Save Report Tracking" onclick="" styleClass="tinybutton"/>
			</c:if>
		</div>
	</div>

</kul:tab>
