<%--
 Copyright 2006-2008 The Kuali Foundation
 
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

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />


<kul:tab tabTitle="Financial" defaultOpen="false" tabErrorKey="">
	<!-- Institution -->

<div class="tab-container" align="center">

<h3>
	<span class="subhead-left">Account Info</span>
	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.institutionalproposal.home.InstitutionalProposal" altText="help"/></span>
</h3>
<table cellpAdding="0" cellspacing="0" summary="">
  	<tr>
    	<th width="400">
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.currentAccountNumber}" /></div>
    	</th>
    	<td>
    		<kul:htmlControlAttribute property="document.institutionalProposal.currentAccountNumber" attributeEntry="${institutionalProposalAttributes.currentAccountNumber}" />
    	</td>
  	</tr>
  </table>
 </div>
 
<div class="tab-container" align="center">

<h3>
	<span class="subhead-left">Project Periods and Amounts</span>
	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.institutionalproposal.home.InstitutionalProposal" altText="help"/></span>
</h3>
<table cellpAdding="0" cellspacing="0" summary="">
	<tr>
		<th width="350" scope="row">&nbsp;</th>
		<th width="300" align="center"><div align="center">Initial Period</div></th>
		<th width="300" align="center"><div align="center">Total Period</div></th>
		<th rowspan="6">
			&nbsp;
		</th>
	</tr>
  	<tr>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.requestedStartDateInitial}" /></div>
    	</th>
    	<td>
    		<div align="right">
				<kul:htmlControlAttribute property="document.institutionalProposal.requestedStartDateInitial" attributeEntry="${institutionalProposalAttributes.requestedStartDateInitial}" datePicker="true"/>
			</div>
		</td>
    	<td>
    		<div align="right">
				<kul:htmlControlAttribute property="document.institutionalProposal.requestedStartDateTotal" attributeEntry="${institutionalProposalAttributes.requestedStartDateTotal}"datePicker="true"/>
			</div>
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.requestedEndDateInitial}" /></div>
    	</th>
    	<td>
    		<div align="right">
				<kul:htmlControlAttribute property="document.institutionalProposal.requestedEndDateInitial" attributeEntry="${institutionalProposalAttributes.requestedEndDateInitial}" datePicker="true"/>
			</div>
		</td>
    	<td>
    		<div align="right">
				<kul:htmlControlAttribute property="document.institutionalProposal.requestedEndDateTotal" attributeEntry="${institutionalProposalAttributes.requestedEndDateTotal}"datePicker="true"/>
			</div>
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.totalDirectCostInitial}" /></div>
    	</th>
    	<td>
    		<div align="right">
    			<kul:htmlControlAttribute property="document.institutionalProposal.totalDirectCostInitial" attributeEntry="${institutionalProposalAttributes.totalDirectCostInitial}" styleClass="amount"/>
			</div>
		</td>
    	<td>
    		<div align="right">
    			<kul:htmlControlAttribute property="document.institutionalProposal.totalDirectCostTotal" attributeEntry="${institutionalProposalAttributes.totalDirectCostTotal}" styleClass="amount"/>
			</div>
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.totalIndirectCostInitial}" /></div>
    	</th>
    	<td>
    		<div align="right">
    			<kul:htmlControlAttribute property="document.institutionalProposal.totalIndirectCostInitial" attributeEntry="${institutionalProposalAttributes.totalIndirectCostInitial}" styleClass="amount"/>
    		</div>	
		</td>
    	<td>
    		<div align="right">
    			<kul:htmlControlAttribute property="document.institutionalProposal.totalIndirectCostTotal" attributeEntry="${institutionalProposalAttributes.totalIndirectCostTotal}" styleClass="amount"/>
    		</div>	
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right">Total All Cost</div></th>
    	</th>
    	<th>
    		<div align="right">
    			$<fmt:formatNumber value="${KualiForm.institutionalProposalDocument.institutionalProposal.totalInitialCost}" type="currency" currencySymbol="" maxFractionDigits="2" />
    		</div>
		</th>
    	<th>
    		<div align="right">
    			$<fmt:formatNumber value="${KualiForm.institutionalProposalDocument.institutionalProposal.totalCost}" type="currency" currencySymbol="" maxFractionDigits="2" />
    		</div>
		</th>
  	</tr>  
</table>
</div>
<div class="tab-container" align="center">
		<html:image property="methodToCall.recalculateTotals.anchor${tabKey}"
		src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/>
   </div>     
</kul:tab>

