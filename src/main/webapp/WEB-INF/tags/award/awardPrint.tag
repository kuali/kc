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
<%-- member of AwardActions.jsp --%>
<script language="javascript" src="dwr/interface/AwardTransactionLookupService.js"></script>
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
	padding-left: 0px;
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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardPrintAttributes" value="${DataDictionary.AwardPrint.attributes}" />
<c:set var="awardPrintNoticeAttributes" value="${DataDictionary.AwardPrintNotice.attributes}" />
<c:set var="awardTransactionSelectorAttributes" value="${DataDictionary.AwardTransactionSelectorBean.attributes}" />
<c:set var="award" value="${KualiForm.document.award}" />
 
<%-- kra:section permission="viewAward" --%>
<kul:tab defaultOpen="false" tabTitle="Print" tabErrorKey="awardTimeAndMoneyTransactionReport.*" >
	<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Print</span>
	  	    <span class="subhead-right"><kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardPrintHelpUrl" altText="help"/></span>      
   		</h3>
	    <table id="print-table" cellpadding="0" cellspacing="0" summary="Print Options">
			<tr>
				<th scope="row" style="width:150px; text-align:left;">Award Notice</th>
				<td style="padding: 5px;">
				 <div class="multi-col-list" id="awardPrintNoticeItems">
				 <dl><dd><ul>
				  <li><kul:htmlControlAttribute property="awardPrintNotice.terms" 
      							attributeEntry="${awardPrintNoticeAttributes.terms}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.terms}" useShortLabel="true" noColon="true" />
      			  </li>
				  <li><kul:htmlControlAttribute property="awardPrintNotice.specialReview" 
      							attributeEntry="${awardPrintNoticeAttributes.specialReview}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.specialReview}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.equipment" 
      							attributeEntry="${awardPrintNoticeAttributes.equipment}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.equipment}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.foreignTravel" 
      							attributeEntry="${awardPrintNoticeAttributes.foreignTravel}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.foreignTravel}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.subAward" 
      							attributeEntry="${awardPrintNoticeAttributes.subAward}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.subAward}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.costShare" 
      							attributeEntry="${awardPrintNoticeAttributes.costShare}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.costShare}" useShortLabel="true" noColon="true" />
      			  </li>
      			 </ul></dd><dd><ul>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.faRates" 
      							attributeEntry="${awardPrintNoticeAttributes.faRates}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.faRates}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.benefitsRates" 
      							attributeEntry="${awardPrintNoticeAttributes.benefitsRates}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.benefitsRates}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.flowThru" 
      							attributeEntry="${awardPrintNoticeAttributes.flowThru}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.flowThru}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.comments" 
      							attributeEntry="${awardPrintNoticeAttributes.comments}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.comments}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.fundingSummary" 
      							attributeEntry="${awardPrintNoticeAttributes.fundingSummary}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.fundingSummary}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.hierarchy" 
      							attributeEntry="${awardPrintNoticeAttributes.hierarchy}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.hierarchy}" useShortLabel="true" noColon="true" />
      			  </li>
      			 </ul></dd><dd><ul>
      			 <li><kul:htmlControlAttribute property="awardPrintNotice.technicalReports" 
      							attributeEntry="${awardPrintNoticeAttributes.technicalReports}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.technicalReports}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.reports" 
      							attributeEntry="${awardPrintNoticeAttributes.reports}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.reports}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.payment" 
      							attributeEntry="${awardPrintNoticeAttributes.payment}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.payment}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.closeout" 
      							attributeEntry="${awardPrintNoticeAttributes.closeout}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.closeout}" useShortLabel="true" noColon="true" />
      			  </li>
      			  <li><kul:htmlControlAttribute property="awardPrintNotice.sponsorContacts" 
      							attributeEntry="${awardPrintNoticeAttributes.sponsorContacts}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.sponsorContacts}" useShortLabel="true" noColon="true" />
      			  </li>
				  <li><kul:htmlControlAttribute property="awardPrintNotice.otherData" 
      							attributeEntry="${awardPrintNoticeAttributes.otherData}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.otherData}" useShortLabel="true" noColon="true" />
      			  </li>
				  <li><kul:htmlControlAttribute property="awardPrintNotice.keywords" 
      							attributeEntry="${awardPrintNoticeAttributes.keywords}" readOnly="false"/>
      				  <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.keywords}" useShortLabel="true" noColon="true" />
      			  </li>
      			 </ul></dd></dl></div>
      			 <div style="clear:both; text-align:center;">
      			  <html:image property="methodToCall.selectAllAwardPrintNoticeItems.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="setAllItemsIn('awardPrintNoticeItems', true);return false;" />
      			  <html:image property="methodToCall.deselectAllAwardPrintNoticeItems.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif" title="Select None" alt="Select None" styleClass="tinybutton" onclick="setAllItemsIn('awardPrintNoticeItems', false);return false;" />
      			  <div style="float:right;">
      			   <kul:htmlAttributeLabel attributeEntry="${awardPrintNoticeAttributes.requireSignature}" useShortLabel="true" />
      			   <kul:htmlControlAttribute property="awardPrintNotice.requireSignature" 
      							attributeEntry="${awardPrintNoticeAttributes.requireSignature}" readOnly="false"/>
      			  </div>  
      			 </div>
				</td>
				<td class="infoline" style="text-align:center;"><html:image property="methodToCall.printNotice"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif' 
						alt="Print Award Notice" styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/></td>
			</tr><tr> 
				<th scope="row" style="width:150px; text-align: left;">Award Modification</th>
				 <td style="padding: 5px;">
					<span style="float:left; width:33.3%; padding: 5px;">
						<kul:htmlAttributeLabel attributeEntry="${awardTransactionSelectorAttributes.awardVersion}" useShortLabel="true" />
						   <kul:htmlControlAttribute property="awardPrintChangeReport.awardVersion" 
									attributeEntry="${awardTransactionSelectorAttributes.awardVersion}" readOnly="false"
									onchange="loadApplicableTransactionIds(jq_escape('awardPrintChangeReport.awardVersion'), jq_escape('awardPrintChangeReport.amountInfoIndex'), '${award.awardNumber}');"/>
					</span>
					<span style="float:left; width:33.3%; text-align:center; padding:5px;">
						<kul:htmlAttributeLabel attributeEntry="${awardTransactionSelectorAttributes.amountInfoIndex}" useShortLabel="true" />
						   <kul:htmlControlAttribute property="awardPrintChangeReport.amountInfoIndex" 
									attributeEntry="${awardTransactionSelectorAttributes.amountInfoIndex}" readOnly="false"/>
					</span>
					<script>
					  jQuery(document).ready(function() {
					 	loadApplicableTransactionIds(jq_escape('awardPrintChangeReport.awardVersion'), jq_escape('awardPrintChangeReport.amountInfoIndex'), '${award.awardNumber}');
					  });
					</script>

					<span style="float: right;">
					   <kul:htmlAttributeLabel attributeEntry="${awardTransactionSelectorAttributes.requireSignature}" useShortLabel="true" />
					   <kul:htmlControlAttribute property="awardPrintChangeReport.requireSignature" 
								attributeEntry="${awardTransactionSelectorAttributes.requireSignature}" readOnly="false"/>
					</span>									
				</td>
				</td>
				<td class="infoline" style="text-align:center;"><html:image property="methodToCall.printChangeReport"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif' 
						alt="Print Award Change Report" styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/></td>			
			</tr><tr>
			 <th colspan="2" style="text-align: left;">
			  Award Hierarchy
			 </th>
			 <td class="infoline" style="text-align:center;"><html:image property="methodToCall.printHierarchy"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif' 
						alt="Print Award Hierarchy" styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/></td>			 
			</tr>
			<!--<tr>
			 <th colspan="2" style="text-align: left;">
			  Budget
			 </th>
			 <td class="infoline" style="text-align:center;"><html:image property="methodToCall.printBudget"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif' 
						alt="Print Budget" styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/></td>			 
			</tr>-->
			<tr>
			 <th colspan="2" style="text-align: left;">
			  Time &amp; Money History
			 </th>
			 <td class="infoline" style="text-align:center;"><html:image property="methodToCall.printTimeMoneyHistory"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif' 
						alt="Print Time &amp; Money History" styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/></td>			 
			</tr><tr>
			 <th style="text-align: left;">
			  Time & Money Transaction Detail
			 </th>
			 <td style="padding: 5px;">
				<span style="float:left; width:33.3%; padding: 5px;">
					<kul:htmlAttributeLabel attributeEntry="${awardTransactionSelectorAttributes.awardVersion}" useShortLabel="true" />
					   <kul:htmlControlAttribute property="awardTimeAndMoneyTransactionReport.awardVersion" 
								attributeEntry="${awardTransactionSelectorAttributes.awardVersion}" readOnly="false"
								onchange="loadApplicableTransactionIds(jq_escape('awardTimeAndMoneyTransactionReport.awardVersion'), jq_escape('awardTimeAndMoneyTransactionReport.amountInfoIndex'), '${award.awardNumber}');"/>
				</span>
				<span style="float:left; width:33.3%; text-align:center; padding:5px;">
					<kul:htmlAttributeLabel attributeEntry="${awardTransactionSelectorAttributes.amountInfoIndex}" useShortLabel="true" />
					   <kul:htmlControlAttribute property="awardTimeAndMoneyTransactionReport.amountInfoIndex" 
								attributeEntry="${awardTransactionSelectorAttributes.amountInfoIndex}" readOnly="false"/>
				</span>
				<script>
				  jQuery(document).ready(function() {
				 	loadApplicableTransactionIds(jq_escape('awardTimeAndMoneyTransactionReport.awardVersion'), jq_escape('awardTimeAndMoneyTransactionReport.amountInfoIndex'), '${award.awardNumber}');
				  });
				</script>
		      												
			</td>			 
			 <td class="infoline" style="text-align:center;"><html:image property="methodToCall.printTransactionDetail"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif' 
						alt="Print Transaction Detail" styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/></td>			 
			</tr>
			 
			   	
    	</table>
	</div>
</kul:tab>
