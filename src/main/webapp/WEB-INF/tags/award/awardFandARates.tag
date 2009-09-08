<!--
/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
-->

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="awardFandaRateAttributes" value="${DataDictionary.AwardFandaRate.attributes}" />
<c:set var="awardFandaRateCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />
<c:set var="awardBenefitsRateCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />
<c:set var="action" value="awardTimeAndMoney" />
<kul:tab tabTitle="Rates" defaultOpen="false" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
    <kul:innerTab tabTitle="F&A Rates" parentTab="Rates" defaultOpen="false" tabErrorKey="newAwardFandaRate.*,document.awardList[0].awardFandaRate*">
		<div class="tab-container" align="right">
	    	<h3>
	    		<span class="subhead-left">F&A Rates</span>
	    		<span class="subhead-right">
	    			<kul:help businessObjectClassName="org.kuali.kra.award.home.AwardFandaRate" altText="help"/>						
					<kul:lookup boClassName="org.kuali.kra.award.home.ValidRates" anchor="${tabKey}" autoSearch="yes" 
					            readOnlyFields="yes" hideReturnLink="true" suppressActions="true" />		
				</span>
	        </h3>
	        
	        <table cellpadding="0" cellspacing="0" summary="">
	          	<tr>
	          		<th width="5%"><div align="center">&nbsp;</div></th>
	          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardFandaRateAttributes.applicableFandaRate}" noColon="true" /></div></th>
	          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardFandaRateAttributes.fandaRateTypeCode}" noColon="true" /></div></th>
	          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardFandaRateAttributes.fiscalYear}" noColon="true" /></div></th>
	          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardFandaRateAttributes.startDate}" noColon="true" /></div></th>
	          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardFandaRateAttributes.endDate}" noColon="true" /></div></th>
	          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardFandaRateAttributes.onCampusFlag}" noColon="true" /></div></th>
	          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardFandaRateAttributes.underrecoveryOfIndirectCost}" noColon="true" /></div></th>
	          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardFandaRateAttributes.sourceAccount}" noColon="true" /></div></th>
	          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardFandaRateAttributes.destinationAccount}" noColon="true" /></div></th>          		
	          		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
	          	</tr>
	             <tr>
					<th width="5%" class="infoline">
						<c:out value="Add:" />
					</th>
	                
	                <td width="9%" valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newAwardFandaRate.applicableFandaRate" attributeEntry="${awardFandaRateAttributes.applicableFandaRate}" styleClass="amount"/>%
	                	</div>
					</td>
	                <td width="9%" valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newAwardFandaRate.fandaRateTypeCode" attributeEntry="${awardFandaRateAttributes.fandaRateTypeCode}" />
	                	</div>
					</td>
	                <td width="9%" valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newAwardFandaRate.fiscalYear" attributeEntry="${awardFandaRateAttributes.fiscalYear}" onblur="loadStartAndEndDates('newAwardFandaRate.fiscalYear', 'newAwardFandaRate.startDate','newAwardFandaRate.endDate');"/>                																																																	
	                	</div>
					</td>
					<td width="9%" valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newAwardFandaRate.startDate" attributeEntry="${awardFandaRateAttributes.startDate}" datePicker="true" />
	                	</div>
					</td>
	                <td width="9%" valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newAwardFandaRate.endDate" attributeEntry="${awardFandaRateAttributes.endDate}" datePicker="true" />
	                	</div>
					</td>
					<td width="9%" valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newAwardFandaRate.onCampusFlag" attributeEntry="${awardFandaRateAttributes.onCampusFlag}" />
	                	</div>
					</td>
					<td width="9%" valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newAwardFandaRate.underrecoveryOfIndirectCost" attributeEntry="${awardFandaRateAttributes.underrecoveryOfIndirectCost}" styleClass="amount"/>
	                	</div>
					</td>
					<td width="9%" valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newAwardFandaRate.sourceAccount" attributeEntry="${awardFandaRateAttributes.sourceAccount}" />
	                	</div>
					</td>
	                <td width="9%" valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newAwardFandaRate.destinationAccount" attributeEntry="${awardFandaRateAttributes.destinationAccount}" />
	                	</div>
					</td>
					<td class="infoline">
						<div width="10%" align="center">
							<html:image property="methodToCall.addFandaRate.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>					
	                </td>
	            </tr>
	            <c:set var="total" value="0" />
	        	<c:forEach var="awardFandaRate" items="${KualiForm.document.awardList[0].awardFandaRate}" varStatus="status">
		             <tr>
						<th width="5%" class="infoline">
							<c:out value="${status.index+1}" />
						</th>	                
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardFandaRate[${status.index}].applicableFandaRate" attributeEntry="${awardFandaRateAttributes.applicableFandaRate}" styleClass="amount"/>
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardFandaRate[${status.index}].fandaRateTypeCode" attributeEntry="${awardFandaRateAttributes.fandaRateTypeCode}" />                		
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardFandaRate[${status.index}].fiscalYear" attributeEntry="${awardFandaRateAttributes.fiscalYear}" onblur="loadStartAndEndDates('document.awardList[0].awardFandaRate[${status.index}].fiscalYear', 'document.awardList[0].awardFandaRate[${status.index}].startDate','document.awardList[0].awardFandaRate[${status.index}].endDate');" />
						</div>
						</td>
						<td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardFandaRate[${status.index}].startDate" attributeEntry="${awardFandaRateAttributes.startDate}" datePicker="true" />
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardFandaRate[${status.index}].endDate" attributeEntry="${awardFandaRateAttributes.endDate}" datePicker="true" />
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardFandaRate[${status.index}].onCampusFlag" attributeEntry="${awardFandaRateAttributes.onCampusFlag}" />
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardFandaRate[${status.index}].underrecoveryOfIndirectCost" attributeEntry="${awardFandaRateAttributes.underrecoveryOfIndirectCost}" styleClass="amount"/>
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardFandaRate[${status.index}].sourceAccount" attributeEntry="${awardFandaRateAttributes.sourceAccount}" />
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.awardList[0].awardFandaRate[${status.index}].destinationAccount" attributeEntry="${awardFandaRateAttributes.destinationAccount}" />
						</div>
						</td>
						<td width="10%" valign="middle">
						<div align="center">
	                		<html:image property="methodToCall.deleteFandaRate.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						</div>
						</td>
						<c:set var="total" value="${total + KualiForm.document.awardList[0].awardFandaRate[status.index].underrecoveryOfIndirectCost}" />
	
		            </tr>
	        	</c:forEach>
	        	<c:if test="${not empty KualiForm.document.awardList[0].awardFandaRate}" >
	        		<tr>
	        			<th width="5%" class="infoline">
							<c:out value="Total" />
						</th>
						<td valign="middle" colspan="6" class="infoline">
						<div align="center">
	                		&nbsp;
						</div>
						</td>
						<td valign="middle" class="infoline">
						<div align="center">                		
	                		<fmt:formatNumber type="CURRENCY" value="${total}" />
						</div>
						</td>
						<td valign="middle" colspan="2" class="infoline">
						<div align="center">
	                		&nbsp;
						</div>
						</td>
						<td valign="middle" class="infoline">
						<div align="center">
	             		<html:image property="methodToCall.recalculateFandARate" 
	             					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' 
	             					styleClass="tinybutton"/>
						</div>
						</td>
	        		</tr>        		
	        	</c:if>
	        		<tr>
	        			<th width="100" align="right" scope="row"><div align="center">Comments:</div></th>
	        			<td class="infoline" colspan="10">
	            	 		<div align="left">
	            	  	 		<kul:htmlControlAttribute property="document.award.awardFandaRateComment.comments" attributeEntry="${awardFandaRateCommentAttributes.comments}"/>
	            	  	 		<kra:expandedTextArea textAreaFieldName="document.award.awardFandaRateComment.comments" action="${action}" textAreaLabel="${awardFandaRateCommentAttributes.comments.label}" />
	            	 		</div>
	            		</td>            
	        		</tr>
	        </table>
	        <BR><BR>
	    </div>
    </kul:innerTab>
    
    <kul:innerTab tabTitle="Benefits Rates" parentTab="Rates" defaultOpen="false" tabErrorKey="document.awardList[0].specialEbRateOnCampus,document.awardList[0].specialEbRateOffCampus,document.awardList[0].benefitsRates*,document.awardList[0].awardBenefitsRateComment.comments">
		<div class="tab-container" align="right">
			<h3>
				<span class="subhead-left"> Benefits Rates</span>
				<span class="subhead-right">
					<kul:help businessObjectClassName="org.kuali.kra.award.home.Award" altText="help"/>						
					<kul:lookup boClassName="org.kuali.kra.award.home.ValidRates" fieldConversions="onCampusRate:document.awardList[0].specialEbRateOnCampus,offCampusRate:document.awardList[0].specialEbRateOffCampus" anchor="${tabKey}" />		
				</span>
		    </h3>
		    <table id="Benefits Rates" cellpadding="0" cellspacing="0" summary="Benefits Rates">
		    	<tr>
		        	<th width="100" align="right" scope="row"><div align="right">On Campus</div></th>
		        	<td>
		        	  	<div align="left">
		        	  	 	<kul:htmlControlAttribute property="document.awardList[0].specialEbRateOnCampus" attributeEntry="${awardAttributes.specialEbRateOnCampus}" styleClass="amount"/>&nbsp;%
		        	 	</div>
		        	</td>
		        </tr>
		        <tr>
		        	<th width="100" align="right" scope="row"><div align="right">Off Campus</div></th>
		        	<td>
		        	  	<div align="left">
		        	  	 	<kul:htmlControlAttribute property="document.awardList[0].specialEbRateOffCampus" attributeEntry="${awardAttributes.specialEbRateOffCampus}" styleClass="amount"/>&nbsp;%
		        	 	</div>
		        	</td>
		         </tr>
		         <tr>
		    		<th width="100" align="right" scope="row"><div align="center">Comments:</div></th>
		    		<td class="infoline" colspan="10">
		        	 	<div align="left">
		        	  	 	<kul:htmlControlAttribute property="document.awardList[0].awardBenefitsRateComment.comments" attributeEntry="${awardBenefitsRateCommentAttributes.comments}"/>
		        	  	 	<kra:expandedTextArea textAreaFieldName="document.awardList[0].awardBenefitsRateComment.comments" action="${action}" textAreaLabel="${awardBenefitsRateCommentAttributes.comments.label}" />
		        	 	</div>
		        	</td>            
		    	</tr>
		      </table>
		</div>
    </kul:innerTab>
</kul:tab>
