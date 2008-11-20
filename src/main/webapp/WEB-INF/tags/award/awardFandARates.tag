<!--
/*
 * Copyright 2006-2008 The Kuali Foundation
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

<c:set var="awardIndirectCostRateAttributes" value="${DataDictionary.AwardIndirectCostRate.attributes}" />
<c:set var="action" value="awardTimeAndMoney" />

<kul:tab tabTitle="Rates" defaultOpen="false" tabErrorKey="newAwardIndirectCostRate.*,document.award.awardIndirectCostRate*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> F&A Rates</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.bo.AwardIndirectCostRate" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th width="5%"><div align="center">&nbsp;</div></th>
          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardIndirectCostRateAttributes.applicableIndirectCostRate}" noColon="true" /></div></th>
          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardIndirectCostRateAttributes.idcRateTypeCode}" noColon="true" /></div></th>
          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardIndirectCostRateAttributes.fiscalYear}" noColon="true" /></div></th>
          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardIndirectCostRateAttributes.startDate}" noColon="true" /></div></th>
          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardIndirectCostRateAttributes.endDate}" noColon="true" /></div></th>
          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardIndirectCostRateAttributes.onCampusFlag}" noColon="true" /></div></th>
          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardIndirectCostRateAttributes.underrecoveryOfIndirectCost}" noColon="true" /></div></th>
          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardIndirectCostRateAttributes.sourceAccount}" noColon="true" /></div></th>
          		<th width="9%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardIndirectCostRateAttributes.destinationAccount}" noColon="true" /></div></th>          		
          		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</tr>
             <tr>
				<th width="5%" class="infoline">
					<c:out value="Add:" />
				</th>
                
                <td width="10%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardIndirectCostRate.applicableIndirectCostRate" attributeEntry="${awardIndirectCostRateAttributes.applicableIndirectCostRate}" />&nbsp;%
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardIndirectCostRate.idcRateTypeCode" attributeEntry="${awardIndirectCostRateAttributes.idcRateTypeCode}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardIndirectCostRate.fiscalYear" attributeEntry="${awardIndirectCostRateAttributes.fiscalYear}" />
                	</div>
				</td>
				<td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardIndirectCostRate.startDate" attributeEntry="${awardIndirectCostRateAttributes.startDate}" datePicker="true" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardIndirectCostRate.endDate" attributeEntry="${awardIndirectCostRateAttributes.endDate}" datePicker="true" />
                	</div>
				</td>
				<td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardIndirectCostRate.onCampusFlag" attributeEntry="${awardIndirectCostRateAttributes.onCampusFlag}" />
                	</div>
				</td>
				<td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardIndirectCostRate.underrecoveryOfIndirectCost" attributeEntry="${awardIndirectCostRateAttributes.underrecoveryOfIndirectCost}" />
                	</div>
				</td>
				<td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardIndirectCostRate.sourceAccount" attributeEntry="${awardIndirectCostRateAttributes.sourceAccount}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardIndirectCostRate.destinationAccount" attributeEntry="${awardIndirectCostRateAttributes.destinationAccount}" />
                	</div>
				</td>
				<td class="infoline">
					<div width="10%" align="center">
						<html:image property="methodToCall.addFandARate.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
            </tr>
            <c:set var="total" value="0" />
        	<c:forEach var="awardIndirectCostRate" items="${KualiForm.document.award.awardIndirectCostRate}" varStatus="status">
	             <tr>
					<th width="5%" class="infoline">
						<c:out value="${status.index+1}" />
					</th>	                
	                <td width="9%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardIndirectCostRate[${status.index}].applicableIndirectCostRate" attributeEntry="${awardIndirectCostRateAttributes.applicableIndirectCostRate}" />
					</div>
					</td>
	                <td width="9%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardIndirectCostRate[${status.index}].idcRateTypeCode" attributeEntry="${awardIndirectCostRateAttributes.idcRateTypeCode}" />                		
					</div>
					</td>
	                <td width="9%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardIndirectCostRate[${status.index}].fiscalYear" attributeEntry="${awardIndirectCostRateAttributes.fiscalYear}" />
					</div>
					</td>
					<td width="9%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardIndirectCostRate[${status.index}].startDate" attributeEntry="${awardIndirectCostRateAttributes.startDate}" datePicker="true" />
					</div>
					</td>
	                <td width="9%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardIndirectCostRate[${status.index}].endDate" attributeEntry="${awardIndirectCostRateAttributes.endDate}" datePicker="true" />
					</div>
					</td>
	                <td width="9%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardIndirectCostRate[${status.index}].onCampusFlag" attributeEntry="${awardIndirectCostRateAttributes.onCampusFlag}" />
					</div>
					</td>
	                <td width="9%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardIndirectCostRate[${status.index}].underrecoveryOfIndirectCost" attributeEntry="${awardIndirectCostRateAttributes.underrecoveryOfIndirectCost}" />
					</div>
					</td>
	                <td width="9%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardIndirectCostRate[${status.index}].sourceAccount" attributeEntry="${awardIndirectCostRateAttributes.sourceAccount}" />
					</div>
					</td>
	                <td width="9%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.award.awardIndirectCostRate[${status.index}].destinationAccount" attributeEntry="${awardIndirectCostRateAttributes.destinationAccount}" />
					</div>
					</td>
					<td width="10%" valign="middle">
					<div align="center">
                		<html:image property="methodToCall.deleteFandARate.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
					</td>
					<c:set var="total" value="${total + KualiForm.document.award.awardIndirectCostRate[status.index].underrecoveryOfIndirectCost}" />

	            </tr>
        	</c:forEach>
        	<c:if test="${not empty KualiForm.document.award.awardIndirectCostRate}" >
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
					<td valign="middle" colspan="3" class="infoline">
					<div align="center">
                		&nbsp;
					</div>
					</td>
        		
        		</tr>
        	</c:if>	 
        	
        </table>
        
        <BR><BR>

		<div align="center">
             		<html:image property="methodToCall.recalculateFandARate"
				src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton"/>
		</div>


    </div>
</kul:tab>
