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

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardReportTermsAttributes" value="${DataDictionary.AwardReportTerms.attributes}" />
<c:set var="action" value="awardReportTerms" />

<kul:tab tabTitle="AwardReportTerms" defaultOpen="true" tabErrorKey="" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Award Report Terms</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.bo.AwardReportTerms" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th width="5%"><div align="center">&nbsp;</div></th> 
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.awardReportTermsId}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.awardId}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.mitAwardNumber}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.sequenceNumber}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.reportClassCode}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.reportCode}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.frequencyCode}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.frequencyBaseCode}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.ospDistributionCode}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.contactTypeCode}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.rolodexId}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.dueDate}" noColon="true" /></div></th>
          		<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.numberOfCopies}" noColon="true" /></div></th>
          	</tr> 
          	
             <tr>
				<th width="5%" class="infoline">
					<c:out value="Add:" />
				</th>

                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.awardReportTermsId" attributeEntry="${awardReportTermsAttributes.awardReportTermsId}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.awardId" attributeEntry="${awardReportTermsAttributes.awardId}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.mitAwardNumber" attributeEntry="${awardReportTermsAttributes.mitAwardNumber}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.sequenceNumber" attributeEntry="${awardReportTermsAttributes.sequenceNumber}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.reportClassCode" attributeEntry="${awardReportTermsAttributes.reportClassCode}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.reportCode" attributeEntry="${awardReportTermsAttributes.reportCode}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.frequencyCode" attributeEntry="${awardReportTermsAttributes.frequencyCode}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.frequencyBaseCode" attributeEntry="${awardReportTermsAttributes.frequencyBaseCode}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.ospDistributionCode" attributeEntry="${awardReportTermsAttributes.ospDistributionCode}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.contactTypeCode" attributeEntry="${awardReportTermsAttributes.contactTypeCode}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.rolodexId" attributeEntry="${awardReportTermsAttributes.rolodexId}" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.dueDate" attributeEntry="${awardReportTermsAttributes.dueDate}" datePicker="true" />
                	</div>
				</td>
                <td width="5%" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newAwardReportTerms.numberOfCopies" attributeEntry="${awardReportTermsAttributes.numberOfCopies}" />
                	</div>
				</td>
				<td class="infoline">
					<div width="10%" align="center">
						<html:image property="methodToCall.addAwardReportTerms.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
            </tr>
            
        	<c:forEach var="awardReportTerms" items="${KualiForm.document.awardReportTerms}" varStatus="status">
	             <tr>
					<th width="5%" class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].awardReportTermsId" attributeEntry="${awardReportTermsAttributes.awardReportTermsId}" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].awardId" attributeEntry="${awardReportTermsAttributes.awardId}" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].mitAwardNumber" attributeEntry="${awardReportTermsAttributes.mitAwardNumber}" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].sequenceNumber" attributeEntry="${awardReportTermsAttributes.sequenceNumber}" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].reportClassCode" attributeEntry="${awardReportTermsAttributes.reportClassCode}" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].reportCode" attributeEntry="${awardReportTermsAttributes.reportCode}" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].frequencyCode" attributeEntry="${awardReportTermsAttributes.frequencyCode}" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].frequencyBaseCode" attributeEntry="${awardReportTermsAttributes.frequencyBaseCode}" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].ospDistributionCode" attributeEntry="${awardReportTermsAttributes.ospDistributionCode}" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].contactTypeCode" attributeEntry="${awardReportTermsAttributes.contactTypeCode}" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].rolodexId" attributeEntry="${awardReportTermsAttributes.rolodexId}" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].dueDate" attributeEntry="${awardReportTermsAttributes.dueDate}" datePicker="true" />
					</div>
					</td>
	                <td width="5%" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardReportTerms[${status.index}].numberOfCopies" attributeEntry="${awardReportTermsAttributes.numberOfCopies}" />
					</div>
					</td>

	            </tr>
        	</c:forEach> 
        </table>

    </div>
</kul:tab>
