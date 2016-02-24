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
<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="awardAmountInfoAttributes" value="${DataDictionary.AwardAmountInfo.attributes}" />

<kul:tab tabTitle="Summary (${KualiForm.awardForSummaryPanelDisplay.awardNumber})" 
	defaultOpen="${KualiForm.inMultipleNodeHierarchy}" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Summary</span>
     		<span class="subhead-right"><kul:help parameterNamespace="KC-T" parameterDetailType="Document" parameterName="tmSummaryHelpUrl" altText="help"/></span>
        </h3>
        <table cellpAdding="0" cellspacing="0" summary="">
		  	<tr>
		    	<th><div align="right">Award ID:</div></th>
		    	<td>${KualiForm.awardForSummaryPanelDisplay.awardNumber}&nbsp;</td>
		    	<th>
		    		<div align="right">Award Type:</div>
		      	</th>
		    	<td>
		    		<c:out value="${KualiForm.awardForSummaryPanelDisplay.awardType.description}" />
		      	</td>		    	
		  	</tr>
		  	<tr>
		    	<th>
		    		<div align="right">Sponsor Award ID:</div>
		      	</th>
		    	<td>
		    		<c:out value="${KualiForm.awardForSummaryPanelDisplay.sponsorAwardNumber}" />
		    	</td>
		    	<th>
		    		<div align="right">Activity Type</div>
		    	</th>
		    	<td>
		    		<c:out value="${KualiForm.awardForSummaryPanelDisplay.activityType.description}" />
				</td>
		  	</tr>
		  	<tr>
		    	<th>
		    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountNumber}" /></div>
		      	</th>
		    	<td align="left" valign="middle">
		    		<c:out value="${KualiForm.awardForSummaryPanelDisplay.accountNumber}" />
		    	</td>
		    	<th>
		    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountTypeCode}" /></div>
				</th>
		    	<td>
		    		<c:out value="${KualiForm.awardForSummaryPanelDisplay.accountTypeDescription}" />
				</td>
		  	</tr>
		  	<tr>
		    	<th>
		    		<div align="right">
		        		Award Status:
		      		</div>
		      	</th>
		    	<td>
		    		<c:out value="${KualiForm.awardForSummaryPanelDisplay.awardStatus.description}" />
		      	</td>
		    	<th>&nbsp;</th>
		    	<td align="left" valign="middle">&nbsp;</td>
		  	</tr>
		  	<tr>
		    	<th>
		    		<div align="right">
		        		Title:
		      		</div>
		      	</th>
		    	<td colspan="3">
		        	<table style="border:none; width:100%;">
		        		<tr>
		            		<td style="border:none; width:100%;">
		            			<c:out value="${KualiForm.awardForSummaryPanelDisplay.title}" />
		        			</td>
		            	</tr>
		        	</table>
		    	</td>
		  	</tr>
		</table>
        
        <h3>
    		<span class="subhead-left">Dates &amp; Amounts</span>
     		<span class="subhead-right"><kul:help parameterNamespace="KC-T" parameterDetailType="Document" parameterName="tmDatesAmountsHelpUrl" altText="help"/></span>
         </h3>
        <table cellpadding="0" cellspacing="0" summary="">
	    	<tr>
	        	<th>
	            	<div align="right">Sponsor:</div>
	        	</th>
	        	<td colspan="3">
	        		<c:out value="${KualiForm.awardForSummaryPanelDisplay.sponsorCode}" />
	        		<c:out value=" - ${KualiForm.awardForSummaryPanelDisplay.sponsor.sponsorName}" />
	        	</td>	        	       		
	        </tr>	        	
				<th>
					<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardEffectiveDate}" /></div>
        		</th>
        		<td align="left" valign="middle" >
        			<fmt:formatDate pattern="MM/dd/yyyy" value="${KualiForm.awardForSummaryPanelDisplay.awardEffectiveDate}" />
				</td>
				<th>
					<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.currentFundEffectiveDate}" /></div>
        		</th>
        		<td align="left" valign="middle">
        			<fmt:formatDate pattern="MM/dd/yyyy" value="${KualiForm.awardForSummaryPanelDisplay.awardAmountInfos[KualiForm.indexOfAwardAmountInfoForDisplay].currentFundEffectiveDate}" />
        		</td>
	        <tr>	        	
				<th style="text-align: right;">Project End Date:</th>
   				<td>
   					<fmt:formatDate pattern="MM/dd/yyyy" value="${KualiForm.awardForSummaryPanelDisplay.awardAmountInfos[KualiForm.indexOfAwardAmountInfoForDisplay].finalExpirationDate}" />
        		</td>
        		<th>
					<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligationExpirationDate}" /></div>
        		</th>
        		<td align="left" valign="middle">
        			<fmt:formatDate pattern="MM/dd/yyyy" value="${KualiForm.awardForSummaryPanelDisplay.awardAmountInfos[KualiForm.indexOfAwardAmountInfoForDisplay].obligationExpirationDate}" />
        		</td>	        	
	        </tr>
	        <tr>	        	
	        	<th>
					<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.anticipatedTotalAmount}" /></div>
        		</th>
        		<td align="left" valign="middle">
        			<fmt:formatNumber type="currency"  value="${KualiForm.awardForSummaryPanelDisplay.awardAmountInfos[KualiForm.indexOfAwardAmountInfoForDisplay].anticipatedTotalAmount}" />
        		</td>
        		<th>
					<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.amountObligatedToDate}" /></div>
        		</th>
        		<td align="left" valign="middle">
        			<fmt:formatNumber type="currency"  value="${KualiForm.awardForSummaryPanelDisplay.awardAmountInfos[KualiForm.indexOfAwardAmountInfoForDisplay].amountObligatedToDate}" />
        		</td>
	        </tr>
        </table>	
        
        <h3>
    		<span class="subhead-left">Award Details Recorded</span>
   	       	<span class="subhead-right"><kul:help parameterNamespace="KC-T" parameterDetailType="Document" parameterName="tmAwardDetailsRecordedHelpUrl" altText="help"/></span>
        </h3>
        <table cellpadding="0" cellspacing="0" summary="">
	    	<tr>
	        	<th>
	            	<div align="right">Approved Subaward?</div>
	        	</th>
	        	<td>
	        		<div align="left">
	        		<c:choose>
	        			<c:when test="${fn:length(KualiForm.awardForSummaryPanelDisplay.awardApprovedSubawards)==0}">
	        				No
	        			</c:when>
	        			<c:otherwise>
	        				Yes
	        			</c:otherwise>
	        		</c:choose>
	        		</div>
	        	</td>
	        	<th>
					<div align="right">Payment Schedule?</div>
        		</th>
        		<td>
        			<div align="left">
        			<c:choose>
	        			<c:when test="${fn:length(KualiForm.awardForSummaryPanelDisplay.paymentScheduleItems)==0}">
	        				No
	        			</c:when>
	        			<c:otherwise>
	        				Yes
	        			</c:otherwise>
	        		</c:choose>
	        		</div>
	        	</td>	        	       		
	        </tr>	        	
				<th>
	            	<div align="right">Approved Equipment?</div>
	        	</th>
	        	<td>
	        		<div align="left">
					<c:choose>
	        			<c:when test="${fn:length(KualiForm.awardForSummaryPanelDisplay.approvedEquipmentItems)==0}">
	        				No
	        			</c:when>
	        			<c:otherwise>
	        				Yes
	        			</c:otherwise>
	        		</c:choose>
					</div>
	        	</td>
	        	<th>
					<div align="right">Sponsor Funding Transferred?</div>
        		</th>
        		<td>
       				<div align="left">
       				<c:choose>
	        			<c:when test="${fn:length(KualiForm.awardForSummaryPanelDisplay.awardTransferringSponsors)==0}">
	        				No
	        			</c:when>
	        			<c:otherwise>
	        				Yes
	        			</c:otherwise>
	        		</c:choose>
       				</div>
	        	</td>
	        <tr>	        	
				<th>
	            	<div align="right">Approved Foreign Travel?</div>
	        	</th>
	        	<td>
	        		<div align="left">
	        		<c:choose>
	        			<c:when test="${fn:length(KualiForm.awardForSummaryPanelDisplay.approvedForeignTravelTrips)==0}">
	        				No
	        			</c:when>
	        			<c:otherwise>
	        				Yes
	        			</c:otherwise>
	        		</c:choose>
	        		</div>
	        	</td>
	        	<th>
					<div align="right">Cost Share?</div>
        		</th>
        		<td>
        			<div align="left">
        			<c:choose>
	        			<c:when test="${fn:length(KualiForm.awardForSummaryPanelDisplay.awardCostShares)==0}">
	        				No
	        			</c:when>
	        			<c:otherwise>
	        				Yes
	        			</c:otherwise>
	        		</c:choose>
	        		</div>
	        	</td>	        	
	        </tr>
	        <tr>	        	
	        	<th>
	            	<div align="right">F&A?</div>
	        	</th>
	        	<td colspan="3">
	        		<div align="left">
	        		<c:choose>
	        			<c:when test="${fn:length(KualiForm.awardForSummaryPanelDisplay.awardFandaRate)==0}">
	        				No
	        			</c:when>
	        			<c:otherwise>
	        				Yes
	        			</c:otherwise>
	        		</c:choose>
	        		</div>
	        	</td>
	        </tr>
        </table>
        
        <h3>
    		<span class="subhead-left">Investigators</span>
 	       	<span class="subhead-right"><kul:help parameterNamespace="KC-T" parameterDetailType="Document" parameterName="tmInvestigatorsHelpUrl" altText="help"/></span>
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
	        	<td style="text-align: center;">
	        		<c:if test="${KualiForm.awardForSummaryPanelDisplay.principalInvestigatorName != null}" >
	        			<c:out value="${KualiForm.awardForSummaryPanelDisplay.principalInvestigatorName} (Principal Investigator)" />
	        		</c:if>
	        		&nbsp;
	        	</td>
	        	<td style="text-align: center;">
	        		<c:forEach var="leadUnit" items="${KualiForm.awardForSummaryPanelDisplay.principalInvestigator.units}" varStatus="status">
	        			<c:out value="${KualiForm.awardForSummaryPanelDisplay.principalInvestigator.units[status.index].unit.unitName}" />	        			
        				<c:if test="${KualiForm.awardForSummaryPanelDisplay.principalInvestigator.units[status.index].leadUnit}" >        				
        					(Lead Unit)
        				</c:if>
						<c:if test="${KualiForm.awardForSummaryPanelDisplay.principalInvestigator.person != null}" >
						 <c:if test="${KualiForm.awardForSummaryPanelDisplay.principalInvestigator.person.unit != null}" >
						           <br/>
							<c:out value="${KualiForm.awardForSummaryPanelDisplay.principalInvestigator.person.unit.unitName}"></c:out>
						</c:if>
						</c:if>
					</c:forEach>
	        		
	        	</td>
	        </tr>
	        
	        	<c:forEach var="coInvestigator" items="${KualiForm.awardForSummaryPanelDisplay.coInvestigators}" varStatus="status">
	        	<tr>
	        		<td style="text-align: center;">
	        			<c:out value="${KualiForm.awardForSummaryPanelDisplay.coInvestigators[status.index].fullName} (Co-Investigator)" />&nbsp;
        			</td>
        			<td style="text-align: center;">
	        			<c:forEach var="unit" items="${coInvestigator.units}" varStatus="status">
	        				<c:out value="${coInvestigator.units[status.index].unit.unitName}" /><br>	        			
	        			</c:forEach>
        			</td>
	        	</tr>
	        	</c:forEach>
	        	
	        	<c:forEach var="keyPerson" items="${KualiForm.awardForSummaryPanelDisplay.keyPersons}" varStatus="status">
	        	<tr>
	        		<td style="text-align: center;">
	        			<c:out value="${KualiForm.awardForSummaryPanelDisplay.keyPersons[status.index].fullName} (Key Person)" />&nbsp;
        			</td>
        			<td style="text-align: center;">
	        			<c:forEach var="unit" items="${keyPerson.units}" varStatus="status">
	        				<c:out value="${keyPerson.units[status.index].unit.unitName}" /><br>	        			
	        			</c:forEach>
        			</td>
	        	</tr>
	        	</c:forEach>	        	
	        </tr>	
	    </table>    	
   				<html:image property="methodToCall.goToPreviousAward.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-prev.gif' styleClass="tinybutton" disabled="${KualiForm.rootNode}"/>
	
				<html:image property="methodToCall.goToNextAward.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-next.gif' styleClass="tinybutton" disabled="${KualiForm.lastNode}"/>
	
	</div>   
</kul:tab>
