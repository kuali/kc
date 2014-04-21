<%--
 Copyright 2005-2014 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<style type="text/css">
.multi-col-list dl {
 	clear:both;
	margin: .5em;
	width: 100%;
	min-width:38em;
}
.multi-col-list dl dd {
	width: 32%;
	float: left;
	padding-left: 65px;
	margin: 0;
	margin-bottom: 1em;
	padding-right: .2em;
}
.multi-col-list dl dd ul { 
 	list-style: none; 
 	padding: 0;
}
.multi-col-list dl dd ul li {
   list-style: none;
}
</style>
<c:set var="subAwardPrintAgreementAttributes" value="${DataDictionary.SubAwardPrintAgreement.attributes}" />
<c:set var="action" value="SubAwardHomeAction" />
<%--  <c:set var="subaward" value="${KualiForm.document.subaward}" /> --%>

<kul:tab tabTitle="Print" tabItemCount="" defaultOpen="false" tabErrorKey="" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
		<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Print</span>
		</h3>
		<table id="print-table" cellpadding="0" cellspacing="0" summary="Print Options">
			<tr>
				<th rowspan="7" scope="row" style="width:150px; text-align:center;">Agreement/Modification</th>
				</tr>
				<tr>
				<td scope="row" style="width:160px; text-align:center;" nowrap="nowrap">
				<kul:htmlAttributeLabel attributeEntry="${subAwardPrintAgreementAttributes.fundingSource}" useShortLabel="false" noColon="true" /></td>
				<td>
				   <div class="multi-col-list">
						<dl><dd><ul>
						<li><kul:htmlControlAttribute property="subAwardPrintAgreement.fundingSource" 
		      							attributeEntry="${subAwardPrintAgreementAttributes.fundingSource}" readOnly="false"/>
		      				  
		      			</li>
						</ul></dd></dl>
					</div>
						</td>
						<td rowspan="4" class="infoline" style="text-align:center;"><html:image property="methodToCall.printForms"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif' 
						alt="Print Award Notice" styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/></td>
				</tr>
				<tr>
				<td scope="row" style="width:160px; text-align:center;" nowrap="nowrap">FDP Template:</td>
				<td>
						<table id="print-table" cellpadding="0" cellspacing="0">
						<tr>
						<!-- <td style="border-left: 0px; border-bottom: 0px;"> -->
						<div class="multi-col-list" id="awardPrintNoticeItems">
						<dl><dd><ul>
						<li>
				         <html:radio property="subAwardPrintAgreement.fdpType" value="fdpAgreement" />
				         <c:out value="FDP Agreement"/>
		      			</li>
						</ul></dd>
						<dd><ul>
						<li><html:radio property="subAwardPrintAgreement.fdpType" value="fdpModification" />
				         <c:out value="FDP Modification"/>
		      			</li>
						</ul></dd></dl>
						</div>
						<!-- </td> -->
						</tr>
					</table>
				</td>
				</tr>
				<!-- <tr>
				
			   </tr> -->
    	</table>
		</div>
</kul:tab>