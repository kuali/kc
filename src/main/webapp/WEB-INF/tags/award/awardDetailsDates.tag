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
<c:set var="awardCurrentActionCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />
<kul:tab tabTitle="Details & Dates" defaultOpen="true" tabErrorKey="document.awardList[0].awardTransactionTypeCode,document.award.version, document.awardList[0].statusCode,document.awardList[0].activityTypeCode,document.awardList[0].awardTypeCode,document.awardList[0].title,document.awardList[0].beginDate,document.awardList[0].awardAmountInfos[${KualiForm.document.award.indexOfLastAwardAmountInfo}].finalExpirationDate,document.awardList[0].awardAmountInfos[0].finalExpirationDate,document.awardList[0].awardEffectiveDate,document.awardList[0].awardExecutionDate,document.awardList[0].sponsorCode,document.awardList[0].unitNumber, detailsAndDatesFormHelper*,document.awardList[0].awardAmountInfos[0].anticipatedTotalAmount,document.awardList[0].awardEffectiveDate,document.awardList[0].awardAmountInfos[${KualiForm.document.award.indexOfLastAwardAmountInfo}].obligationExpirationDate">

<!-- Institution -->
<div class="tab-container" align="center">

<kra:innerTab parentTab="Details & Dates" tabItemCount="" defaultOpen="true" tabTitle="Current Action" tabErrorKey="" >

<table cellpAdding="0" cellspacing="0" summary="">
  	<tr>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardTransactionTypeCode}" /></div></th>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.noticeDate}" /></div></th>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardCurrentActionCommentAttributes.comments}" /></div></th>
  	</tr>
  	<tr>
   		<td width="150">
   			<div align="center">
    			<kul:htmlControlAttribute property="document.awardList[0].awardTransactionTypeCode" attributeEntry="${awardAttributes.awardTransactionTypeCode}" />
			</div>
		</td>
		<td width="100">
			<div align="center">
    			<kul:htmlControlAttribute property="document.awardList[0].noticeDate" attributeEntry="${awardAttributes.noticeDate}" />
			</div>
		</td>
    	<td width="1000">
    		<div align="center">
    			<kul:htmlControlAttribute property="document.awardList[0].awardCurrentActionComments.comments" attributeEntry="${awardCurrentActionCommentAttributes.comments}" />
			</div>
		</td>
  	</tr>
</table>
</kra:innerTab>	

<kra:innerTab parentTab="Details & Dates" tabItemCount="" defaultOpen="true" tabTitle="Institution" tabErrorKey="" >

<table cellpAdding="0" cellspacing="0" summary="">
  	<tr>
    	<th><div align="right">Award ID:</div></th>
    	<td>${KualiForm.awardDocument.award.awardNumber}&nbsp;</td>

        <c:set var="docInSavedState" value="${KualiForm.awardDocument.saved}" />
        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.unitNumber}" skipHelpUrl="true"/></div></th>
    	<td align="left" valign="middle">
            <c:if test="${!docInSavedState}">
                <kul:htmlControlAttribute property="document.awardList[0].unitNumber" attributeEntry="${awardAttributes.unitNumber}" /> <%-- need AJAX lookup of unit name onblur --%>
                <c:if test="${!readOnly}">
                    <kul:lookup boClassName="org.kuali.kra.bo.Unit" fieldConversions="unitNumber:document.awardList[0].unitNumber"
  			                anchor="${tabKey}" lookupParameters="document.awardList[0].unitNumber:unitNumber"/>
  			    </c:if>
            </c:if>
            <kul:directInquiry boClassName="org.kuali.kra.bo.Unit" inquiryParameters="document.awardList[0].unitNumber:unitNumber" anchor="${tabKey}" />
            <c:if test="${docInSavedState}">
                <html:hidden property="document.awardList[0].unitNumber" />
                <kul:htmlControlAttribute property="document.awardList[0].unitNumber" attributeEntry="${awardAttributes.unitNumber}" readOnly="true" />
                -
                <kul:htmlControlAttribute property="document.awardList[0].unitName" attributeEntry="${awardAttributes.unitName}" readOnly="true" />
            </c:if>
    	</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right">Version:</div>
      	</th>
    	<td>${KualiForm.awardDocument.award.sequenceNumber}</td>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountTypeCode}" /></div>
		</th>
    	<td>
    		<kul:htmlControlAttribute property="document.awardList[0].accountTypeCode" attributeEntry="${awardAttributes.accountTypeCode}" />
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
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.activityTypeCode}" /></div>
    	</th>
    	<td>
    		<kul:htmlControlAttribute property="document.awardList[0].activityTypeCode" attributeEntry="${awardAttributes.activityTypeCode}" />
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
        		<kul:htmlAttributeLabel attributeEntry="${awardAttributes.title}" />
      		</div>
      	</th>
    	<td colspan="3">
        	<table style="border:none; width:100%;">
        		<tr>
            		<td style="border:none; width:100%;">
            			<kul:htmlControlAttribute property="document.awardList[0].title" attributeEntry="${awardAttributes.title}" />
        			</td>
            	</tr>
        	</table>
    	</td>
  	</tr>
</table>
</kra:innerTab>	

<!-- Sponsor -->

<kra:innerTab parentTab="Details & Dates" tabItemCount="" defaultOpen="true" tabTitle="Sponsor" tabErrorKey="" >

<table cellpadding="0" cellspacing="0" summary="">
    <tr>
        <th>
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.sponsorCode}" /></div>
        </th>
        <td>
        	<kul:htmlControlAttribute property="document.awardList[0].sponsorCode" attributeEntry="${awardAttributes.sponsorCode}" onblur="loadSponsorName('document.awardList[0].sponsorCode', 'sponsorName');"/>
        	<c:if test="${!readOnly}">
        	   <kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.awardList[0].sponsorCode,sponsorName:document.awardList[0].sponsor.sponsorName" anchor="${tabKey}" />
            </c:if>
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
        	<c:if test="${!readOnly}">
        	   <kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:document.awardList[0].primeSponsorCode,sponsorName:document.awardList[0].primeSponsor.sponsorName" anchor="${tabKey}" />
            </c:if>
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
        	<kul:htmlControlAttribute property="document.awardList[0].nsfCode" attributeEntry="${awardAttributes.nsfCode}" styleClass="fixed-size-200-select" />
        </td>
    </tr>
</table>
</kra:innerTab>	

<!-- Sponsor Funding Transferred -->

<kra:innerTab parentTab="Details & Dates" tabItemCount="" defaultOpen="false" tabTitle="Sponsor Funding Transferred" tabErrorKey="" >

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
    <c:if test="${!readOnly}">
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
    </c:if>
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
				    <c:if test="${!readOnly}">
					<html:image property="methodToCall.deleteAwardTransferringSponsor.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
			         </c:if>
				</div>
	    	</td>
		</tr>
    </c:forEach> 
</table>
</kra:innerTab>	

<!-- Time & Money -->

<kra:innerTab parentTab="Details & Dates" tabItemCount="" defaultOpen="true" tabTitle="Time & Money" tabErrorKey="" >

<table cellpAdding="0" cellspacing="0" summary="">
				 <tr>
					<th>
						<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.beginDate}" /></div>
			        </th>
			        <td align="left" valign="middle">
			        	<kul:htmlControlAttribute property="document.awardList[0].beginDate" attributeEntry="${awardAttributes.beginDate}" />
					</td>
					
        			<th>
						<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardEffectiveDate}" /></div>
        			</th>
		<c:choose>
			<c:when test="${KualiForm.awardInMultipleNodeHierarchy}">				
        			
       				<td align="left" valign="middle">
       				<div align="left">
        				<c:out value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.document.award.indexOfLastAwardAmountInfo].currentFundEffectiveDate}"  />
        			</div>
        			</td>
    			</tr>
    		</c:when>
	     	<c:otherwise>
	     			<td align="left" valign="middle">
			        	<kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[${KualiForm.document.award.indexOfLastAwardAmountInfo}].currentFundEffectiveDate" attributeEntry="${awardAttributes.awardEffectiveDate}"/>
			        </td>
			    </tr>	
    		</c:otherwise>
		</c:choose>  		
    			<tr>
    				<th>
						<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.finalExpirationDate}" /></div>
        			</th>
        			
        <c:choose>
			<c:when test="${KualiForm.awardInMultipleNodeHierarchy}">	
        			<td align="left" valign="middle">
        			<div align="left">
        				<c:out value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.document.award.indexOfLastAwardAmountInfo].finalExpirationDate}"  />
        			</div>
        			</td>
        			
        	</c:when>
	     	<c:otherwise>
	     			<td align="left" valign="middle">
			        	<kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[${KualiForm.document.award.indexOfLastAwardAmountInfo}].finalExpirationDate" attributeEntry="${awardAmountInfoAttributes.finalExpirationDate}"/>
			        </td>
	     	</c:otherwise>
		</c:choose>		
			        <th>
			        	<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAmountInfoAttributes.obligationExpirationDate}" /></div>
			        </th>
			        
		<c:choose>
			<c:when test="${KualiForm.awardInMultipleNodeHierarchy}">		        
			        <td align="left" valign="middle">
			        <div align="left">
			            <c:out value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.document.award.indexOfLastAwardAmountInfo].obligationExpirationDate}"  />
			        </div>
			        </td>
			    </tr>
			    
		</c:when>
	     	<c:otherwise>
	     		    <td align="left" valign="middle">
			            <kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[${KualiForm.document.award.indexOfLastAwardAmountInfo}].obligationExpirationDate" attributeEntry="${awardAmountInfoAttributes.obligationExpirationDate}"/>
			        </td>
			    </tr>
			</c:otherwise>
		</c:choose>	
			   <tr>
			        <th>
			            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardExecutionDate}" /></div>
			        </th>
			        <td align="left" valign="middle">
			            <kul:htmlControlAttribute property="document.awardList[0].awardExecutionDate" attributeEntry="${awardAttributes.awardExecutionDate}" />
			        </td>
			        <th>
			            &nbsp;
			        </th>
			        <td align="left" valign="middle">
			            &nbsp;
			        </td>
			    </tr>
  	   
			    <tr>
			        <th>
			            <div align="right">Anticipated Amount:</div>
			        </th>
			        <c:choose>
						<c:when test="${KualiForm.awardInMultipleNodeHierarchy}">
				                <td align="left" valign="middle">
			            			<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.indexOfAwardAmountInfoWithHighestTransactionId].anticipatedTotalAmount}"/>
			        			</td>
			        			<th>
			            			<div align="right">Obligated Amount:</div>
			        			</th>
			        			<td align="left" valign="middle">
			           				<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.indexOfAwardAmountInfoWithHighestTransactionId].amountObligatedToDate}"/>
			        			</td>
				        </c:when>
				        <c:otherwise>
								<td align="left" valign="middle">
			            			<kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[${KualiForm.indexOfAwardAmountInfoWithHighestTransactionId}].anticipatedTotalAmount" attributeEntry="${awardAttributes.anticipatedTotal}"/>
			        			</td>
			        			<th>
			            			<div align="right">Obligated Amount:</div>
			        			</th>
			        			<td align="left" valign="middle">
			            			<kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[${KualiForm.indexOfAwardAmountInfoWithHighestTransactionId}].amountObligatedToDate" attributeEntry="${awardAttributes.obligatedTotal}"/>
			        			</td>
						</c:otherwise>
					</c:choose>
			    </tr>
</table>
</kra:innerTab>	

</div>

</kul:tab>
