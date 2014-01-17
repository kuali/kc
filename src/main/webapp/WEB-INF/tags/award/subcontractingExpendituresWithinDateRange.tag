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

<c:set var="expendituresInDateRangeAttributes" value="${DataDictionary.SubcontractingExpenditureCategoryAmountsInDateRange.attributes}" />
<div class="tab-container" align="center">
  	<h3>
  		<span class="subhead-left">Within A Date Range</span>
  		<span class="subhead-right">
			<kul:help businessObjectClassName="org.kuali.kra.award.subcontracting.reporting.SubcontractingExpenditureCategoryAmountsByDateRange" altText="help" />
		</span>
	</h3>
	<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="infoline">
				<div align="left">
					From award expenditure details between:
						<kul:htmlAttributeLabel attributeEntry="${expendituresInDateRangeAttributes.rangeStartDate}" />
           		    	<kul:htmlControlAttribute property="rangeStartDate" attributeEntry="${expendituresInDateRangeAttributes.rangeStartDate}" />
              		    	-
           		    	<kul:htmlAttributeLabel attributeEntry="${expendituresInDateRangeAttributes.rangeEndDate}" />
           		    	<kul:htmlControlAttribute property="rangeEndDate" attributeEntry="${expendituresInDateRangeAttributes.rangeEndDate}" />           
	 					<html:image property="methodToCall.regenerateExpenditureDataInDateRange" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-regenerate.gif' styleClass="tinybutton" /> 
				</div>
			</td>
		</tr>
	</table>
</div>