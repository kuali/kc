<%--
 Copyright 2006-2009 The Kuali Foundation
 
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
<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="awardAmountInfoAttributes" value="${DataDictionary.AwardAmountInfo.attributes}" />

<kul:tab tabTitle="Summary (${KualiForm.document.awardNumber})" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Summary</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.timeandmoney.transactions.PendingTransaction" altText="help"/></span>
        </h3>
        
        <table cellpAdding="0" cellspacing="0" summary="">
		  	<tr>
		    	<th><div align="right">*Award ID:</div></th>
		    	<td>${KualiForm.timeAndMoneyDocument.award.awardId}&nbsp;</td>
		    	<th>
		    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardTypeCode}" /></div>
		      	</th>
		    	<td>
		    		<kul:htmlControlAttribute property="document.award.awardTypeCode" attributeEntry="${awardAttributes.awardTypeCode}" readOnly="true" />
		      	</td>		    	
		  	</tr>
		  	<tr>
		    	<th>
		    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.sponsorCode}" /></div>
		      	</th>
		    	<td><kul:htmlControlAttribute property="document.award.sponsorCode" attributeEntry="${awardAttributes.sponsorCode}" readOnly="true" /></td>
		    	<th>
		    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.activityTypeCode}" /></div>
		    	</th>
		    	<td>
		    		<kul:htmlControlAttribute property="document.award.activityTypeCode" attributeEntry="${awardAttributes.activityTypeCode}" readOnly="true" />
				</td>
		  	</tr>
		  	<tr>
		    	<th>
		    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountNumber}" /></div>
		      	</th>
		    	<td align="left" valign="middle">
		    		<kul:htmlControlAttribute property="document.award.accountNumber" attributeEntry="${awardAttributes.accountNumber}" readOnly="true" />
		    	</td>
		    	<th>
		    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountTypeCode}" /></div>
				</th>
		    	<td>
		    		<kul:htmlControlAttribute property="document.award.accountTypeCode" attributeEntry="${awardAttributes.accountTypeCode}" readOnly="true" />
				</td>
		  	</tr>
		  	<tr>
		    	<th>
		    		<div align="right">
		        		<kul:htmlAttributeLabel attributeEntry="${awardAttributes.statusCode}" />
		      		</div>
		      	</th>
		    	<td>
		    		<kul:htmlControlAttribute property="document.award.statusCode" attributeEntry="${awardAttributes.statusCode}" readOnly="true" />
		      	</td>
		    	<th>&nbsp;</th>
		    	<td align="left" valign="middle">&nbsp;</td>
		  	</tr>
		  	<tr>
		    	<th>
		    		<div align="right">
		        		<kul:htmlAttributeLabel attributeEntry="${awardAttributes.title}" />
		      		</div>
		      	</th>
		    	<td colspan="3">
		        	<table style="border:none; width:100%;">
		        		<tr>
		            		<td style="border:none; width:100%;">
		            			<kul:htmlControlAttribute property="document.award.title" attributeEntry="${awardAttributes.title}"  readOnly="true"/>		                    	
		        			</td>
		            	</tr>
		        	</table>
		    	</td>
		  	</tr>
		</table>
        
        <h3>
    		<span class="subhead-left">Dates & Amounts</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.timeandmoney.transactions.PendingTransaction" altText="help"/></span>
        </h3>
        <table cellpadding="0" cellspacing="0" summary="">
	    	<tr>
	        	<th>
	            	<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.sponsorCode}" /></div>
	        	</th>
	        	<td colspan="3">
	        		<kul:htmlControlAttribute property="document.award.sponsorCode" attributeEntry="${awardAttributes.sponsorCode}" readOnly="true"/>
	        		<c:out value=" - ${KualiForm.document.award.sponsor.sponsorName}" />
	        	</td>	        	       		
	        </tr>	        	
				<th>
					<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.beginDate}" /></div>
        		</th>
        		<td align="left" valign="middle">
        			<kul:htmlControlAttribute property="document.award.beginDate" attributeEntry="${awardAttributes.beginDate}" readOnly="true" />
				</td>
				<th>
					<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.currentFundEffectiveDate}" /></div>
        		</th>
        		<td align="left" valign="middle">
        			<kul:htmlControlAttribute property="document.award.awardAmountInfos[0].currentFundEffectiveDate" attributeEntry="${awardAmountInfoAttributes.currentFundEffectiveDate}" readOnly="true" />
        		</td>
	        <tr>	        	
				<th>
					<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.finalExpirationDate}" /></div>
        		</th>
        		<td align="left" valign="middle">
        			<kul:htmlControlAttribute property="document.award.awardAmountInfos[0].finalExpirationDate" attributeEntry="${awardAmountInfoAttributes.finalExpirationDate}" readOnly="true" />
        		</td>
        		<th>
					<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligationExpirationDate}" /></div>
        		</th>
        		<td align="left" valign="middle">
        			<kul:htmlControlAttribute property="document.award.awardAmountInfos[0].obligationExpirationDate" attributeEntry="${awardAmountInfoAttributes.obligationExpirationDate}" readOnly="true" />
        		</td>	        	
	        </tr>
	        <tr>	        	
	        	<th>
					<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedTotalAmount}" /></div>
        		</th>
        		<td align="left" valign="middle">
        			<kul:htmlControlAttribute property="document.award.awardAmountInfos[0].anticipatedTotalAmount" attributeEntry="${awardAmountInfoAttributes.anticipatedTotalAmount}" readOnly="true" />
        		</td>
        		<th>
					<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.amountObligatedToDate}" /></div>
        		</th>
        		<td align="left" valign="middle">
        			<kul:htmlControlAttribute property="document.award.awardAmountInfos[0].amountObligatedToDate" attributeEntry="${awardAmountInfoAttributes.amountObligatedToDate}" readOnly="true" />
        		</td>
	        </tr>
        </table>	
        
        <h3>
    		<span class="subhead-left">Award Details Recorded</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.timeandmoney.transactions.PendingTransaction" altText="help"/></span>
        </h3>
        
        <h3>
    		<span class="subhead-left">Investigators</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.timeandmoney.transactions.PendingTransaction" altText="help"/></span>
        </h3>
        <table cellpadding="0" cellspacing="0" summary="">
	    	<tr>
	        	<th>
	            	Investigators
	        	</th>
	        	<th >
	        		Units
	        	</th>
	        </tr>
	        <tr>
	        	<td>
	        		<c:if test="${KualiForm.document.award.principalInvestigatorName != null}" >
	        			<c:out value="${KualiForm.document.award.principalInvestigatorName} (Principal Investigator)" />
	        		</c:if>
	        		&nbsp;
	        	</td>
	        	<td>
	        		&nbsp;
	        		<c:forEach var="leadUnit" items="${KualiForm.document.award.principalInvestigator.units}" varStatus="status">
	        			<c:out value="${KualiForm.document.award.principalInvestigator.units[status.index].unit.unitName}" />	        			
        				<c:if test="${KualiForm.document.award.principalInvestigator.units[status.index].leadUnit}" >
        					(Lead Unit)
        				</c:if>
	        		</c:forEach>
	        	</td>
	        </tr>	
	    </table>    	
    </div>    
</kul:tab>
