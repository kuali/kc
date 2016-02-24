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

<%@ attribute name="protocolSummary" required="true" type="org.kuali.kra.iacuc.summary.IacucProtocolSummary" %>
<%@ attribute name="prefix" required="true" %>
          
<div class="sequence">
 <table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <th style="text-align:right; width:135px;">Protocol Number:</th>
            <td class="${protocolSummary.protocolNumberChanged ? 'changed' : ''}">
            	<c:choose>
	       	       <c:when test="${empty protocolSummary.protocolNumber}">
                        Generated on Save
                    </c:when>
                    <c:otherwise>
                        ${protocolSummary.protocolNumber}&nbsp;
                    </c:otherwise>
	       	   	</c:choose>
            </td>
            <th style="text-align:right; width:135px">Initial Submission Date:</th>
            <td class="${protocolSummary.initialSubmissionDateChanged ? 'changed' : ''}">
                <c:choose>
                   <c:when test="${empty protocolSummary.initialSubmissionDate}">
                        Generated on Initial Submission
                    </c:when>
                    <c:otherwise>
                        ${protocolSummary.initialSubmissionDate}&nbsp;
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
            
        <tr>
            <th style="text-align:right; width:135px;">Initial Approval Date:</th>
            <td class="${protocolSummary.approvalDateChanged ? 'changed' : ''}">
            	<c:choose>
	       	       <c:when test="${empty protocolSummary.approvalDate}">
                        Generated on Approval
                    </c:when>
                    <c:otherwise>
                        ${protocolSummary.approvalDate}&nbsp;
                    </c:otherwise>
	       		</c:choose>
	       	</td>
            <th style="text-align:right; width:135px;">Expiration Date:</th>
            <td class="${protocolSummary.expirationDateChanged ? 'changed' : ''}">
            	<c:choose>
	       	       <c:when test="${empty protocolSummary.expirationDate}">
                        Generated on Approval
                    </c:when>
                    <c:otherwise>
                        ${protocolSummary.expirationDate}&nbsp;
                    </c:otherwise>
	       	   	</c:choose>
	    	</td>
        </tr>
        
        <tr>
            <th style="text-align:right; width:135px;">Last Approval Date:</th>
            <td class="${protocolSummary.lastApprovalDateChanged ? 'changed' : ''}">
            	<c:choose>
	       	       <c:when test="${empty protocolSummary.lastApprovalDate}">
                        Generated on Renewal Approval
                    </c:when>
                    <c:otherwise>
                        ${protocolSummary.lastApprovalDate}&nbsp;
                    </c:otherwise>
	       	   	</c:choose>
            	
            </td>
            <th style="text-align:right; width:135px">Type:</th>
            <td class="${protocolSummary.typeChanged ? 'changed' : ''}">${protocolSummary.type}&nbsp;</td>
        </tr>
        
        <tr>
            <th style="text-align:right; width:135px;">PI:</th>
            <td class="${protocolSummary.piNameChanged ? 'changed' : ''}">
                ${protocolSummary.piName}&nbsp;
                <input type="hidden" name="${prefix}.piProtocolPersonId"
                       value="${protocolSummary.piProtocolPersonId}" />
                <kul:directInquiry boClassName="org.kuali.kra.iacuc.personnel.IacucProtocolPerson"
                                   inquiryParameters="${prefix}.piProtocolPersonId:protocolPersonId" 
                                   anchor="${currentTabIndex}" />
            </td>
            <th style="text-align:right; width:135px">Status:</th>
            <td class="${protocolSummary.statusChanged ? 'changed' : ''}">${protocolSummary.status}&nbsp;</td>
        </tr>
            
        <tr>
            <th style="text-align:right; width:135px;">Title:</th>
            <td  class="${protocolSummary.titleChanged ? 'changed' : ''}">${protocolSummary.title}&nbsp;</td>
            <th style="text-align:right; width:135px;">Project Type:</th>
            <td  class="${protocolSummary.projectTypeChanged ? 'changed' : ''}">${protocolSummary.projectType}&nbsp;</td>
        </tr>
        <tr>
            <th style="text-align:right; width:135px;">Lay Statement 1:</th>
            <td  class="${protocolSummary.layStmt1Changed ? 'changed' : ''}">${protocolSummary.layStmt1}&nbsp;</td>
            <th style="text-align:right; width:135px;">Lay Statement 2:</th>
            <td  class="${protocolSummary.layStmt2Changed ? 'changed' : ''}">${protocolSummary.layStmt2}&nbsp;</td>
        </tr>
    </tbody>                  
</table>
    
<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="5">Personnel:</td>
        </tr>
        
        <tr>
            <th style="width: 50px;">&nbsp;</th>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Name</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Role</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Affiliation</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Unit(s)</td>              
        </tr>
            
        <c:forEach items="${protocolSummary.persons}" var="person" varStatus="status">
            <tr>
                <th style="width:50px">${status.index + 1}</th>
                <td class="${person.nameChanged ? 'changed' : ''}">${person.name}</td>
                <td class="${person.roleNameChanged ? 'changed' : ''}">${person.roleName}</td>
                <td class="${person.affiliationChanged ? 'changed' : ''}">${person.affiliation}&nbsp;</td>
                <td>
                    <c:forEach items="${person.units}" var="unit" varStatus="status">
                       <span class="${unit.changed} ? 'changed' : ''}">${unit.unitNumber} : ${unit.unitName}</span><br />
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    
<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="2">Areas of Research:</td>
        </tr>
        <c:forEach items="${protocolSummary.researchAreas}" var="researchArea" varStatus="status">
            <tr>
                <th style="width:50px">${status.index + 1}</th>
                <td class="${researchArea.changed ? 'changed' : ''}">${researchArea.researchAreaCode} : ${researchArea.description}</td>
           </tr>
        </c:forEach>
    </tbody>
</table>
    
<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="7">Attachments:</td>
        </tr>
        <tr>
            <td style="padding: 0px;">
                <table id="protocolActionSummary-protocolAttachment-table" cellpadding="0" cellspacing="0" class="tablesorter" style="border-collapse:collapse;">
                    <thead>            
				        <tr>
				            <th style="width: 5%;">&nbsp;</th>
				            <th class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);width:20%" valign="middle">File Name<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></td>
				            <th class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);width:20%" valign="middle">Attachment Type<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></td>
				            <th class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);width:30%" valign="middle">Description<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></td>
				            <th class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);width:10%" valign="middle">Last Updated<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></td>
				            <th class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);width:10%" valign="middle">Last Updated By<img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></td>                        
				            <th class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);width:5%" valign="middle">Action</td>              
				        </tr>
			        </thead>
			        <tbody>
				        <c:forEach items="${protocolSummary.attachments}" var="attachment" varStatus="status">
				            <tr>
				                <th style="width:50px">&nbsp;</th>
				                <td class="${attachment.fileNameChanged ? 'changed' : ''}">${attachment.fileName}</td>
				                <td class="${attachment.attachmentTypeChanged ? 'changed' : ''}"><div align="left">${attachment.attachmentType}</div></td>
				                <td class="${attachment.descriptionChanged ? 'changed' : ''}"><div align="left">${attachment.description}</div></td>
				                <td class="${attachment.dateChanged ? 'changed' : ''}"><div align="left"><fmt:formatDate value="${attachment.updateTimestamp}" pattern="MM/dd/yyyy KK:mm a" /></div></td>
				                <td class="${attachment.userChanged ? 'changed' : ''}"><div align="left">${attachment.updateUser}</div></td>
				                <td style="width:90%">
				                <c:choose>
				                    <c:when test="${fn:contains(prefix, 'prevProtocolSummary')}">
				                    <html:image property="methodToCall.viewAttachmentProtocol.prev.line${status.index}.anchor${currentTabIndex}"
				                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
				                                styleClass="tinybutton"
				                                alt="View Protocol Attachment" 
				                                onclick="excludeSubmitRestriction = true;"/>
				                    </c:when>
				                    <c:otherwise>
				                    <html:image property="methodToCall.viewAttachmentProtocol.line${status.index}.anchor${currentTabIndex}"
				                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
				                                styleClass="tinybutton"
				                                alt="View Protocol Attachment" 
				                                onclick="excludeSubmitRestriction = true;"/>
				                    </c:otherwise>
				                </c:choose>
				                </td>
				           </tr>
				        </c:forEach>
			        </tbody>
	           </table>
	        </td>
	    </tr>
    </tbody>
</table>
    
<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="5">Funding Source:</td>
        </tr>
        
        <tr>
            <th style="width: 50px;">&nbsp;</th>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Funding Type</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Funding Number</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Source</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Title</td>              
        </tr>
            
        <c:forEach items="${protocolSummary.fundingSources}" var="fundingSource" varStatus="status">
            <tr>
                <th style="width:50px">${status.index + 1}</th>
                <td class="${fundingSource.fundingSourceTypeChanged ? 'changed' : ''}">${fundingSource.fundingSourceType}</td>
                <td class="${fundingSource.fundingSourceNumberChanged ? 'changed' : ''}">${fundingSource.fundingSourceNumber}</td>
                <td class="${fundingSource.fundingSourceNameChanged ? 'changed' : ''}">${fundingSource.fundingSourceName}</td>
                <td class="${fundingSource.fundingSourceTitleChanged ? 'changed' : ''}">${fundingSource.fundingSourceTitle}&nbsp;</td>
           </tr>
        </c:forEach>
    </tbody>
</table>
    
<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="5">Organization:</td>
        </tr>
        
        <tr>
            <th style="width: 50px;">&nbsp;</th>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Organization ID</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Organization Type</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Contact</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">FWA Number</td>
        </tr>
            
        <c:forEach items="${protocolSummary.organizations}" var="organization" varStatus="status">
            <tr>
                <th style="width:50px">${status.index + 1}</th>
                <td class="${organization.idChanged ? 'changed' : ''}">
                    ${organization.id}
                    <input type="hidden" name="${prefix}.organizations[${status.index}].organizationId"
                           value="${organization.organizationId}" />
                    <kul:directInquiry boClassName="org.kuali.coeus.common.framework.org.Organization"
                                       inquiryParameters="${prefix}.organizations[${status.index}].organizationId:organizationId" 
                                       anchor="${currentTabIndex}" />
                    <br /><span class="fineprint">${organization.name}</span>
                </td>
                <td class="${organization.typeChanged ? 'changed' : ''}">${organization.type}</td>
                <td class="${organization.contactChanged ? 'changed' : ''}">
                    ${organization.contact}
                    <input type="hidden" name="${prefix}.organizations[${status.index}].contactId"
                           value="${organization.contactId}" />
                    <kul:directInquiry boClassName="org.kuali.coeus.common.framework.rolodex.Rolodex"
                                       inquiryParameters="${prefix}.organizations[${status.index}].contactId:rolodexId" 
                                       anchor="${currentTabIndex}" />
                </td>
                <td class="${organization.fwaNumberChanged ? 'changed' : ''}">${organization.fwaNumber}&nbsp;</td>
           </tr>
        </c:forEach>
    </tbody>
</table>
    
<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="8">Special Reviews:</td>
        </tr>
            
         <tr>
            <th style="width: 50px;">&nbsp;</th>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Type</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Approval Status</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Protocol #</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Application Date</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Approval Date</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Expiration Date</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Exemption #</td>
        </tr>
    
        <c:forEach items="${protocolSummary.specialReviews}" var="specialReview" varStatus="status">
            <tr>
                <th rowspan="2" style="width:50px">${status.index + 1}</th>
                <td class="${specialReview.typeChanged ? 'changed' : ''}">${specialReview.type}</td>
                <td class="${specialReview.approvalStatusChanged ? 'changed' : ''}">${specialReview.approvalStatus}</td>
                <td class="${specialReview.protocolNumberChanged ? 'changed' : ''}">${specialReview.protocolNumber}&nbsp;</td>
                <td class="${specialReview.applicationDateChanged ? 'changed' : ''}">${specialReview.applicationDate}&nbsp;</td>
                <td class="${specialReview.approvalDateChanged ? 'changed' : ''}">${specialReview.approvalDate}&nbsp;</td>
                <td class="${specialReview.expirationDateChanged ? 'changed' : ''}">${specialReview.expirationDate}&nbsp;</td>
                <td class="${specialReview.exemptionNumbersChanged ? 'changed' : ''}">${specialReview.exemptionNumbers}&nbsp;</td>
           </tr>
       
           <tr>
               <td class="${specialReview.commentChanged ? 'changed' : ''}" colspan="7">
                   <span class="fineprint">
                       <c:choose>
                           <c:when test="${specialReview.comment == null || specialReviewComment == ''}">
                               Comment: <i>This is no comment for this record.</i>
                           </c:when>
                           <c:otherwise>
                               ${specialReview.comment}
                           </c:otherwise>
                       </c:choose>
                   </span>
               </td>
           </tr>
        </c:forEach>
    </tbody>
</table>
    
<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="5">Additional Information:</td>
        </tr>
        
        <tr>
            <th style="text-align:right; width:135px;">Reference ID1:</th>
            <td class="${protocolSummary.additionalInfo.referenceId1Changed ? 'changed' : ''}">${protocolSummary.additionalInfo.referenceId1}&nbsp;</td>
            <th style="text-align:right; width:135px;">Reference ID2:</th>
            <td class="${protocolSummary.additionalInfo.referenceId2Changed ? 'changed' : ''}">${protocolSummary.additionalInfo.referenceId2}&nbsp;</td>
        </tr>
            
        <tr>
            <th style="text-align:right; width:135px;">Summary/Keywords:</th>
            <td class="${protocolSummary.additionalInfo.descriptionChanged ? 'changed' : ''}" colspan="3">${protocolSummary.additionalInfo.description}&nbsp;</td>
        </tr>
    </tbody>
</table>

<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="3">Three Rs:</td>
        </tr>
        <tr>
            <th style="text-align:right; width:135px;">Reduction:</th>
            <td class="${protocolSummary.threeRsInfo.reductionChanged ? 'changed' : ''}">${protocolSummary.threeRsInfo.reduction}&nbsp;</td>
        </tr>
        <tr>
            <th style="text-align:right; width:135px;">Refinement:</th>
            <td class="${protocolSummary.threeRsInfo.refinementChanged ? 'changed' : ''}">${protocolSummary.threeRsInfo.refinement}&nbsp;</td>
        </tr>
        <tr>
            <th style="text-align:right; width:135px;">Replacement:</th>
            <td class="${protocolSummary.threeRsInfo.replacementChanged ? 'changed' : ''}">${protocolSummary.threeRsInfo.replacement}&nbsp;</td>
        </tr>
        <tr>
            <th style="text-align:right; width:135px;">Search Required:</th>
            <td class="${protocolSummary.threeRsInfo.searchRequiredChanged ? 'changed' : ''}">${protocolSummary.threeRsInfo.searchRequired}&nbsp;</td>
        </tr>
        <tr>
            <th style="text-align:right; width:135px;">Alternate Search:</th>
        	<td padding="0">
				<c:choose>
					<c:when test="${fn:length(protocolSummary.threeRsInfo.alternateSearchSummaries) == 0}" >
            			None
	            	</c:when>
    	        	<c:otherwise>
		        		<table frame=void border="0" cellpadding="0" cellspacing="0">
    						<tbody>
						        <tr>
				        		    <th>Date</td>
						            <th>Databases Searched</td>
						            <th>Years Searched</td>
						            <th>Search Terms/Keywords</td>
				        		    <th>Comments</td>
							    </tr>
								<c:forEach items="${protocolSummary.threeRsInfo.alternateSearchSummaries}" var="alternateSearchSummary" varStatus="status">
    	        					<tr>
				        		        <td class="${alternateSearchSummary.searchDateChanged ? 'changed' : ''}">${alternateSearchSummary.searchDate}</td>
            	   						<td class="${alternateSearchSummary.databasesChanged ? 'changed' : ''}">${alternateSearchSummary.databaseList}</td>
						                <td class="${alternateSearchSummary.yearsSearchedChanged ? 'changed' : ''}">${alternateSearchSummary.yearsSearched}&nbsp;</td>
        		        				<td class="${alternateSearchSummary.keywordsChanged ? 'changed' : ''}">${alternateSearchSummary.keywords}&nbsp;</td>
						       	        <td class="${alternateSearchSummary.commentsChanged ? 'changed' : ''}">${alternateSearchSummary.comments}&nbsp;</td>
	        						</tr>
						        </c:forEach>
    						</tbody>
						</table>
					</c:otherwise>
				</c:choose>
        	</td>        	
    </tbody>
</table>
    
<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="0">Species/Groups:</td>
        </tr>

        <tr>
            <th style="width: 50px;">&nbsp;</th>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Species</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Group</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">USDA Covered</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Strain</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Count</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Count Type</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Pain Category</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Procedure Summary</td>
        </tr>
    
        <c:forEach items="${protocolSummary.speciesSummaries}" var="speciesSummary" varStatus="status">
            <tr>
                <th style="width:50px">${status.index + 1}</th>
                <td class="${speciesSummary.speciesChanged ? 'changed' : ''}">${speciesSummary.species}</td>
                <td class="${speciesSummary.speciesGroupChanged ? 'changed' : ''}">${speciesSummary.speciesGroup}</td>
                <td class="${speciesSummary.usdaCoveredChanged ? 'changed' : ''}">${speciesSummary.usdaCovered}&nbsp;</td>
                <td class="${speciesSummary.strainChanged ? 'changed' : ''}">${speciesSummary.strain}&nbsp;</td>
                <td class="${speciesSummary.speciesCountChanged ? 'changed' : ''}">${speciesSummary.speciesCount}&nbsp;</td>
                <td class="${speciesSummary.speciesCountTypeChanged ? 'changed' : ''}">${speciesSummary.speciesCountType}&nbsp;</td>
                <td class="${speciesSummary.painCategoryChanged ? 'changed' : ''}">${speciesSummary.painCategory}&nbsp;</td>
                <td class="${speciesSummary.procedureSummaryChanged ? 'changed' : ''}">${speciesSummary.procedureSummary}&nbsp;</td>
           </tr>
        </c:forEach>
    </tbody>
</table>

<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="0">Procedures:</td>
        </tr>
        <tr>
            <th style="text-align:right; width:80px;">Overview and Timeline:</th>
            <td class="${protocolSummary.procedureOverviewSummaryChanged ? 'changed' : ''}">${protocolSummary.procedureOverviewSummary}&nbsp;</td>
        </tr>
        <tr>
            <th style="text-align:right;">Procedures:</td>
            <td padding="0">
				<c:choose>
					<c:when test="${fn:length(protocolSummary.procedureSummaries) == 0}" >
            			None
	            	</c:when>
    	        	<c:otherwise>
						<table frame="void" border="0" cellpadding="0" cellspacing="0">
						    </tr>
					    	<c:forEach items="${protocolSummary.procedureSummaries}" var="procedureSummary" varStatus="status">  
								<tr> 
							        <th style="text-align:left;" colspan="0" class="${procedureSummary.procedureCategoryChanged ? 'changed' : ''}">${procedureSummary.procedureCategory}&nbsp;</th>
							    </tr>
							    <tr> 
									<th width="50px">${status.index+1}:</th>
						            <td colspan="3" class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Group/Species</td>
            						<td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Person(s) Responsible</td>
            						<td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Pain Category</td>
            						<td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Count</td>
							    </tr>
							    <tr>    
							        <td>&nbsp;</td>   
			    		            <td colspan="3" class="${procedureSummary.speciesChanged ? 'changed' : ''}">${procedureSummary.species}</td>
		    	        	    	<td class="${procedureSummary.personnelListChanged ? 'changed' : ''}">${procedureSummary.personnelList}</td> 
				                	<td class="${procedureSummary.painCategoryChanged ? 'changed' : ''}">${procedureSummary.painCategory}</td>
				                	<td class="${procedureSummary.countChanged ? 'changed' : ''}">${procedureSummary.count}</td>
       							</tr>
							    <tr>    
									<th>&nbsp;</th>
						            <td colspan="0" class="infoline fineprint sequencetd" style="font-weight: bold; text-align: left; color: rgb(51, 51, 51);">Locations:</td>
       							</tr>
							    <tr>    
									<th>&nbsp;</th>
						            <td width="250px" class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Type</td>
            						<td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Name</td>
            						<td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Room</td>
            						<td colspan="0" class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Description</td>
       							</tr>
       							<c:forEach items="${procedureSummary.locationSummaries}" var="locationSummary" varStatus="lStatus">
								    <tr>    
										<th>&nbsp;</th>
							            <td class="${locationSummary.typeChanged ? 'changed' : ''}">${locationSummary.type}</td>
        	    						<td class="${locationSummary.nameChanged ? 'changed' : ''}">${locationSummary.name}</td>
    	        						<td class="${locationSummary.roomChanged ? 'changed' : ''}">${locationSummary.room}</td>
	            						<td colspan="0" class="${locationSummary.descriptionChanged ? 'changed' : ''}">${locationSummary.description}</td>
								    </tr>    
       							</c:forEach>
							    <tr>    
									<th>&nbsp;</th>
						            <td colspan="0" class="infoline fineprint sequencetd" style="font-weight: bold; text-align: left; color: rgb(51, 51, 51);">Persons Responsible:</td>
       							</tr>
							    <tr>    
									<th>&nbsp;</th>
						            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Name</td>
            						<td colspan="2" class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Training</td>
            						<td colspan="0" class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Description</td>
       							</tr>
       							<c:forEach items="${procedureSummary.personSummaries}" var="personSummary" varStatus="pStatus">
								    <tr>    
										<th>&nbsp;</th>
							            <td class="${personSummary.personNameChanged ? 'changed' : ''}">${personSummary.personName}</td>
            							<td colspan="2" class="${personSummary.personTrainingChanged ? 'changed' : ''}">${personSummary.personTraining}</td>
            							<td colspan="0" class="${personSummary.descriptionChanged ? 'changed' : ''}">${personSummary.description}</td>
								    </tr>    
       							</c:forEach>
							    <tr>    
									<th>&nbsp;</th>
						            <td colspan="0" class="infoline fineprint sequencetd" style="font-weight: bold; text-align: left; color: rgb(51, 51, 51);">Custom Data : ${procedureSummary.procedureCategory}</td>
       							</tr>
       							<c:choose>
									<c:when test="${fn:length(procedureSummary.customDataSummaries) == 0}" >
										<tr>
											<th>&nbsp;</th>
											<td colspan="0">None</td>
										</tr>
	       							</c:when>
	       							<c:otherwise>
	       								<c:forEach items="${procedureSummary.customDataSummaries}" var="customDataSummary" varStatus="pStatus">
										    <tr>    
												<th>&nbsp;</th>
            									<td colspan="2" class="${customDataSummary.customDataTagChanged ? 'changed' : ''}">${customDataSummary.customDataTag}</td>
            									<td colspan="0" class="${customDataSummary.customDataValChanged ? 'changed' : ''}">${customDataSummary.customDataVal}</td>
										    </tr>    
    	   								</c:forEach>
	       							</c:otherwise>
       							</c:choose>
       						</c:forEach>  
						</table>
            		</c:otherwise>
            	</c:choose>
			</td>
        </tr>
    
    </tbody>
</table>

<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="0">Protocol Exceptions:</td>
        </tr>

        <tr>
            <th style="width: 50px;">&nbsp;</th>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Species</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Category</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Description</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Count</td>
        </tr>
    
        <c:forEach items="${protocolSummary.exceptionSummaries}" var="exceptionSummary" varStatus="status">
            <tr>
                <th style="width:50px">${status.index + 1}</th>
                <td class="${exceptionSummary.speciesNameChanged ? 'changed' : ''}">${exceptionSummary.speciesName}</td>
                <td class="${exceptionSummary.exceptionCategoryChanged ? 'changed' : ''}">${exceptionSummary.exceptionCategory}&nbsp;</td>
                <td class="${exceptionSummary.exceptionDescriptionChanged ? 'changed' : ''}">${exceptionSummary.exceptionDescription}&nbsp;</td>
                <td class="${exceptionSummary.exceptionCountChanged ? 'changed' : ''}">${exceptionSummary.exceptionCount}&nbsp;</td>
           </tr>
        </c:forEach>
    </tbody>
</table>

    
<%--
		    <c:if test="${KualiForm.actionHelper.summaryQuestionnaireExist}">
		        <kra-irb:viewSummaryQuestionnaire />
		    </c:if>
 --%>
</div>
