<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="protocolSummary" required="true" type="org.kuali.kra.irb.summary.ProtocolSummary" %>
<%@ attribute name="prefix" required="true" %>
          
<div class="sequence">
 <table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <th style="text-align:right; width:135px;">Protocol Number:</th>
            <td class="${protocolSummary.protocolNumberChanged ? 'changed' : ''}">${protocolSummary.protocolNumber}&nbsp;</td>
            <th style="text-align:right; width:135px">Application Date:</th>
            <td class="${protocolSummary.applicationDateChanged ? 'changed' : ''}">${protocolSummary.applicationDate}&nbsp;</td>
        </tr>
            
        <tr>
            <th style="text-align:right; width:135px;">Approval Date:</th>
            <td class="${protocolSummary.approvalDateChanged ? 'changed' : ''}">${protocolSummary.approvalDate}&nbsp;</td>
            <th style="text-align:right; width:135px;">Expiration Date:</th>
            <td class="${protocolSummary.expirationDateChanged ? 'changed' : ''}">${protocolSummary.expirationDate}&nbsp;</td>
        </tr>
        
        <tr>
            <th style="text-align:right; width:135px;">Last Approval Date:</th>
            <td class="${protocolSummary.lastApprovalDateChanged ? 'changed' : ''}">${protocolSummary.lastApprovalDate}&nbsp;</td>
            <th style="text-align:right; width:135px">Type:</th>
            <td class="${protocolSummary.typeChanged ? 'changed' : ''}">${protocolSummary.type}&nbsp;</td>
        </tr>
        
        <tr>
            <th style="text-align:right; width:135px;">PI:</th>
            <td class="${protocolSummary.piNameChanged ? 'changed' : ''}">
                ${protocolSummary.piName}&nbsp;
                <input type="hidden" name="${prefix}.piProtocolPersonId"
                       value="${protocolSummary.piProtocolPersonId}" />
                <kul:directInquiry boClassName="org.kuali.kra.irb.personnel.ProtocolPerson"
                                   inquiryParameters="${prefix}.piProtocolPersonId:protocolPersonId" 
                                   anchor="${currentTabIndex}" />
            </td>
            <th style="text-align:right; width:135px">Status:</th>
            <td class="${protocolSummary.statusChanged ? 'changed' : ''}">${protocolSummary.status}&nbsp;</td>
        </tr>
            
        <tr>
            <th style="text-align:right; width:135px;">Title:</th>
            <td  class="${protocolSummary.titleChanged ? 'changed' : ''}" colspan="3">${protocolSummary.title}&nbsp;</td>
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
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="5">Attachments:</td>
        </tr>
        <tr>
            <th style="width: 5%;">&nbsp;</th>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);width:20%">File Name</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);width:20%">Attachment Type</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);width:50%">Description</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);width:5%">Action</td>              
        </tr>
        <c:forEach items="${protocolSummary.attachments}" var="attachment" varStatus="status">
            <tr>
                <th style="width:50px">${status.index + 1}</th>
                <td class="${attachment.fileNameChanged ? 'changed' : ''}">${attachment.fileName}</td>
                <td><div aligh="left">${attachment.attachmentType}</div></td>
                <td><div aligh="left">${attachment.description}</div></td>
                <td style="width:90%">
                    <html:image property="methodToCall.viewAttachmentProtocol.line${status.index}.anchor${currentTabIndex}"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
                                styleClass="tinybutton"
                                alt="View Protocol Attachment" 
                                onclick="excludeSubmitRestriction = true;"/>
                </td>
           </tr>
        </c:forEach>
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
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Funding ID</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Source</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Title</td>              
        </tr>
            
        <c:forEach items="${protocolSummary.fundingSources}" var="fundingSource" varStatus="status">
            <tr>
                <th style="width:50px">${status.index + 1}</th>
                <td class="${fundingSource.fundingTypeChanged ? 'changed' : ''}">${fundingSource.fundingType}</td>
                <td class="${fundingSource.fundingIdChanged ? 'changed' : ''}">${fundingSource.fundingId}</td>
                <td class="${fundingSource.fundingSourceChanged ? 'changed' : ''}">${fundingSource.fundingSource}</td>
                <td class="${fundingSource.titleChanged ? 'changed' : ''}">${fundingSource.title}&nbsp;</td>
           </tr>
        </c:forEach>
    </tbody>
</table>
    
<table cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="3">Participant Types:</td>
        </tr>
        
        <tr>
            <th style="width: 50px;">&nbsp;</th>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Description</td>
            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Count</td>
        </tr>
        
        <c:forEach items="${protocolSummary.participants}" var="participant" varStatus="status">
            <tr>
                <th style="width:50px">${status.index + 1}</th>
                <td class="${participant.descriptionChanged ? 'changed' : ''}">${participant.description}</td>
                <td class="${participant.countChanged ? 'changed' : ''}">${participant.count}</td>
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
                    <kul:directInquiry boClassName="org.kuali.kra.bo.Organization"
                                       inquiryParameters="${prefix}.organizations[${status.index}].organizationId:organizationId" 
                                       anchor="${currentTabIndex}" />
                    <br /><span class="fineprint">${organization.name}</span>
                </td>
                <td class="${organization.typeChanged ? 'changed' : ''}">${organization.type}</td>
                <td class="${organization.contactChanged ? 'changed' : ''}">
                    ${organization.contact}
                    <input type="hidden" name="${prefix}.organizations[${status.index}].contactId"
                           value="${organization.contactId}" />
                    <kul:directInquiry boClassName="org.kuali.kra.bo.Rolodex"
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
            <th style="text-align:right; width:135px;">FDA IND or IDE #:</th>
            <td class="${protocolSummary.additionalInfo.fdaApplicationNumberChanged ? 'changed' : ''}">${protocolSummary.additionalInfo.fdaApplicationNumber}&nbsp;</td>
            <th style="text-align:right; width:135px">Billable:</th>
            <td class="${protocolSummary.additionalInfo.billableChanged ? 'changed' : ''}">${protocolSummary.additionalInfo.billable}&nbsp;</td>
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
</div>
