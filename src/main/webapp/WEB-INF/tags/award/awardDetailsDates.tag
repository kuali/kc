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
<%-- member of AwardHome.jsp --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="awardAmountInfoAttributes" value="${DataDictionary.AwardAmountInfo.attributes}" />

<kul:tab tabTitle="Details & Dates" defaultOpen="false" tabErrorKey="document.awardList[0].statusCode,document.awardList[0].activityTypeCode,document.awardList[0].awardTypeCode,document.awardList[0].title,document.awardList[0].beginDate,document.awardList[0].awardAmountInfos[0].finalExpirationDate,document.awardList[0].awardEffectiveDate,document.awardList[0].awardExecutionDate,detailsAndDatesFormHelper*">

<!-- Institution -->
<div class="tab-container" align="center">

<h3>
	<span class="subhead-left">Institution</span>
	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.bo.Award" altText="help"/></span>
</h3>
<table cellpAdding="0" cellspacing="0" summary="">
  	<tr>
    	<th><div align="right">*Award ID:</div></th>
    	<td>${KualiForm.awardDocument.award.awardId}&nbsp;</td>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountTypeCode}" /></div>
		</th>
    	<td>
    		<kul:htmlControlAttribute property="document.awardList[0].accountTypeCode" attributeEntry="${awardAttributes.accountTypeCode}" />
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right">*Version:</div>
      	</th>
    	<td>${KualiForm.awardDocument.award.sequenceNumber}</td>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.activityTypeCode}" /></div>
    	</th>
    	<td>
    		<kul:htmlControlAttribute property="document.awardList[0].activityTypeCode" attributeEntry="${awardAttributes.activityTypeCode}" />
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountNumber}" /></div>
      	</th>
    	<td align="left" valign="middle">
    		<kul:htmlControlAttribute property="document.awardList[0].accountNumber" attributeEntry="${awardAttributes.accountNumber}" />
    	</td>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardTypeCode}" /></div>
      	</th>
    	<td>
    		<kul:htmlControlAttribute property="document.awardList[0].awardTypeCode" attributeEntry="${awardAttributes.awardTypeCode}" />
      	</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right">
        		<kul:htmlAttributeLabel attributeEntry="${awardAttributes.statusCode}" />
      		</div>
      	</th>
    	<td>
    		<kul:htmlControlAttribute property="document.awardList[0].statusCode" attributeEntry="${awardAttributes.statusCode}" />
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
            			<kul:htmlControlAttribute property="document.awardList[0].title" attributeEntry="${awardAttributes.title}" />
                    	<kra:expandedTextArea textAreaFieldName="document.awardList[0].title" action="awardHome" textAreaLabel="${awardAttributes.title.label}" />
        			</td>
            	</tr>
        	</table>
    	</td>
  	</tr>
</table>

<!-- Sponsor -->
<h3>
	<span class="subhead-left">Sponsor</span>
	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.Sponsor" altText="help"/></span>
</h3>
<table cellpadding="0" cellspacing="0" summary="">
    <tr>
        <th>
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.sponsorCode}" /></div>
        </th>
        <td>
        	<kul:htmlControlAttribute property="document.awardList[0].sponsorCode" attributeEntry="${awardAttributes.sponsorCode}" onblur="loadSponsorName('document.awardList[0].sponsorCode', 'sponsorName');" />
        	<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.awardList[0].sponsorCode,sponsorName:document.awardList[0].sponsor.sponsorName" anchor="${tabKey}" />
            <kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="document.awardList[0].sponsorCode:sponsorCode" anchor="${tabKey}" />
        	<div id="sponsorName.div" >
            	<c:if test="${!empty KualiForm.document.awardList[0].sponsorCode}">
            		<c:choose>
						<c:when test="${empty KualiForm.document.awardList[0].sponsor}">
	                    	<span style='color: red;'>not found</span>
	               		</c:when>
	                  	<c:otherwise>
							<c:out value="${KualiForm.document.awardList[0].sponsor.sponsorName}" />
						</c:otherwise>
					</c:choose>
            	</c:if>
			</div>
        </td>
        <th>
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.primeSponsorCode}" /></div>
        </th>
        <td>
        	<kul:htmlControlAttribute property="document.awardList[0].primeSponsorCode" attributeEntry="${awardAttributes.primeSponsorCode}" onblur="loadSponsorName('document.awardList[0].primeSponsorCode', 'primeSponsorName');" />
        	<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.awardList[0].primeSponsorCode,sponsorName:document.awardList[0].primeSponsor.sponsorName" anchor="${tabKey}" />
            <kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="document.awardList[0].primeSponsorCode:sponsorCode" anchor="${tabKey}" />
            <div id="primeSponsorName.div">
            	<c:if test="${!empty KualiForm.document.awardList[0].primeSponsorCode}">
            		<c:choose>
						<c:when test="${empty KualiForm.document.awardList[0].primeSponsor}">
	                    	<span style='color: red;'>not found</span>
	               		</c:when>
	                  	<c:otherwise>
							<c:out value="${KualiForm.document.awardList[0].primeSponsor.sponsorName}" />
						</c:otherwise>
					</c:choose>
            	</c:if>
			</div>
        </td>
    </tr>
    <tr>
        <th>
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.sponsorAwardNumber}" /></div>
        </th>
        <td>
        	<kul:htmlControlAttribute property="document.awardList[0].sponsorAwardNumber" attributeEntry="${awardAttributes.sponsorAwardNumber}" />
        </td>
        <th>
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.cfdaNumber}" /></div>
        </th>
        <td>
            <kul:htmlControlAttribute property="document.awardList[0].cfdaNumber" attributeEntry="${awardAttributes.cfdaNumber}" />
		</td>
    </tr>
    <tr>
        <th align="right">
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.modificationNumber}" /></div>
        </th>
        <td align="left" valign="middle">
            <kul:htmlControlAttribute property="document.awardList[0].modificationNumber" attributeEntry="${awardAttributes.modificationNumber}" />
        </td>
        <th align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.nsfCode}" />
        </th>
        <td align="left" valign="middle">
        	<kul:htmlControlAttribute property="document.awardList[0].nsfCode" attributeEntry="${awardAttributes.nsfCode}" />
        </td>
    </tr>
</table>


<!-- Sponsor Funding Transferred -->
<div class="innerTab-head">Sponsor Funding Transferred</div>
<table cellpAdding="0" cellspacing="0" summary="" id="sponsor-funding-transferred-table">
    <tr>
        <th style="text-align:center;">&nbsp; </th>
        <th style="text-align:center;">
            <b>ID/Description</b>
        </th>
        <th style="text-align:center; width:60px;">
            <b>Action</b>
        </th>
    </tr>
    <tr>
        <th>
            <div align="right">
	            <b>Add:</b>
            </div>
        </th>
        <td class="infoline">
        	<kul:htmlControlAttribute property="detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsorCode" attributeEntry="${awardAttributes.sponsorCode}" onblur="loadSponsorName('detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsorCode', 'newAwardTransferringSponsorName');" />
            <kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsorCode,sponsorName:detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsor.sponsorName" anchor="${tabKey}" />
            <kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsorCode:sponsorCode" anchor="${tabKey}" />
            <div id="newAwardTransferringSponsorName.div">
            	<c:if test="${!empty KualiForm.detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsorCode}">
            		<c:choose>
						<c:when test="${empty KualiForm.detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor}">
	                    	<span style='color: red;'>not found</span>
	               		</c:when>
	                  	<c:otherwise>
							<c:out value="${KualiForm.detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsorName}" />
						</c:otherwise>
					</c:choose>
            	</c:if>
			</div>
        </td>
        <td width="10%" class="infoline">
        	<div align="center">&nbsp;
            	<html:image property="methodToCall.addAwardTransferringSponsor.anchor${tabKey}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
			</div>
        </td>
    </tr>
    <c:forEach var="awardTransferringSponsor" items="${KualiForm.document.awardList[0].awardTransferringSponsors}" varStatus="status">
		<tr>
			<th width="5%">
				${status.index + 1}
			</th>
	       	<td>
				${awardTransferringSponsor.sponsorCode} : ${awardTransferringSponsor.sponsor.sponsorName}
			</td>
			<td width="10%">
				<div align="center">&nbsp;
					<html:image property="methodToCall.deleteAwardTransferringSponsor.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
				</div>
	    	</td>
		</tr>
    </c:forEach> 
</table>

<!-- Dates -->
<h3>
	<span class="subhead-left">Dates</span>
</h3>
<table cellpAdding="0" cellspacing="0" summary="">
	<tr>
		<th>
			<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.beginDate}" /></div>
        </th>
        <td align="left" valign="middle">
        	<kul:htmlControlAttribute property="document.awardList[0].beginDate" attributeEntry="${awardAttributes.beginDate}" datePicker="true" />
		</td>
        <th>
			<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardEffectiveDate}" /></div>
        </th>
        <td align="left" valign="middle">
        	<kul:htmlControlAttribute property="document.awardList[0].awardEffectiveDate" attributeEntry="${awardAttributes.awardEffectiveDate}" datePicker="true" />
        </td>
    </tr>
    <tr>
    	<th>
			<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.finalExpirationDate}" /></div>
        </th>
        <td align="left" valign="middle">
        	<kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[0].finalExpirationDate" attributeEntry="${awardAmountInfoAttributes.finalExpirationDate}" datePicker="true" />
        </td>
        <th>
        	<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardExecutionDate}" /></div>
        </th>
        <td align="left" valign="middle">
            <kul:htmlControlAttribute property="document.awardList[0].awardExecutionDate" attributeEntry="${awardAttributes.awardExecutionDate}" datePicker="true" />
        </td>
    </tr>
</table>

</div>

</kul:tab>
