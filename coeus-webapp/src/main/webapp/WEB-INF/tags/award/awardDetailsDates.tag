<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   -
   - Copyright 2005-2015 Kuali, Inc.
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
<%-- member of AwardHome.jsp --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="awardExtensionAttributes" value="${DataDictionary.AwardExtension.attributes}"/>
<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="awardAmountInfoAttributes" value="${DataDictionary.AwardAmountInfo.attributes}" />
<c:set var="awardCurrentActionCommentAttributes" value="${DataDictionary.AwardComment.attributes}" />
<kul:tab tabTitle="Details & Dates" defaultOpen="true"
	tabErrorKey="document.awardList[0].awardTransactionTypeCode,document.award.version, document.awardList[0].statusCode,document.awardList[0].activityTypeCode,document.awardList[0].awardTypeCode,document.awardList[0].financialChartOfAccountsCode,document.awardList[0].title,document.awardList[0].beginDate,document.awardList[0].awardExecutionDate,document.awardList[0].sponsorCode,document.awardList[0].unitNumber, detailsAndDatesFormHelper*,document.awardList[0].awardAmountInfos[${KualiForm.document.award.indexOfLastAwardAmountInfo}].*, document.awardList[0].modificationNumber,document.awardList[0].cfdaNumber,document.awardList[0].primeSponsorCode"
	tabAuditKey="document.awardList[0].awardEffectiveDate,document.awardList[0].sponsorCode,document.awardList[0].primeSponsorCode"
	auditCluster="homePageAuditWarnings,homePageAuditErrors" useRiceAuditMode="true">
<c:set var="awardObligatedAndAnticipatedAmountsEditable" value="${KualiForm.awardObligatedAndAnticipatedAmountsEditable}" />
<!-- Institution -->
<div class="tab-container" align="center">
	<h3>
   		<span class="subhead-left">Details and Dates</span>
   		<span class="subhead-right">
   		    <c:if test="${KualiForm.awardDocument.award.sequenceNumber > 1}" >
      	        <a href="${pageContext.request.contextPath}/awardHistory.do?command=redirectAwardHistoryFullViewForPopup&awardDocumentNumber=${KualiForm.document.documentNumber}&awardNumber=${KualiForm.document.awardList[0].awardNumber}&docTypeName=${KualiForm.docTypeName}" target="_blank" >
                    <img align="top" width="80" height="15" alt="View History" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-viewhistory.gif" styleClass="tinybutton" />
                </a>
   		    </c:if>
   			<kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardDetailsDatesHelp" altText="help"/>
		</span>
    </h3>

<kul:innerTab parentTab="Details & Dates" tabItemCount="" defaultOpen="true" tabTitle="Current Action" tabErrorKey="" >

<table cellpAdding="0" cellspacing="0" summary="">
  	<tr>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardTransactionTypeCode}" /></div></th>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.noticeDate}" /></div></th>
    	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardCurrentActionCommentAttributes.comments}" /></div></th>
  	</tr>
  	<tr>
   		<td width="150">
   			<div align="center">
    			<kul:htmlControlAttribute property="document.awardList[0].awardTransactionTypeCode" attributeEntry="${awardAttributes.awardTransactionTypeCode}" readOnlyAlternateDisplay="${KualiForm.awardDocument.award.awardTransactionType.description}" />
			</div>
		</td>

                    <%-- BU MOD changes ends here--%>
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
</kul:innerTab>

<kul:innerTab parentTab="Details & Dates" tabItemCount="" defaultOpen="true" tabTitle="Institution" tabErrorKey="document.awardList[0].accountNumber" >

<table cellpAdding="0" cellspacing="0" summary="">
  	<tr>
    	<th><div align="right">Award ID:</div></th>
    	<td>${KualiForm.awardDocument.award.awardNumber}&nbsp;</td>

        <c:set var="docInSavedState" value="${KualiForm.awardDocument.saved}" />
        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.unitNumber}" skipHelpUrl="true"/></div></th>
    	<td align="left" valign="middle">
            <kul:htmlControlAttribute property="document.awardList[0].unitNumber" attributeEntry="${awardAttributes.unitNumber}" readOnly="${readOnly or docInSavedState}" /> <%-- need AJAX lookup of unit name onblur --%>
            <c:if test="${!docInSavedState}">
                <c:if test="${!readOnly}">
                    <kul:lookup boClassName="org.kuali.coeus.common.framework.unit.Unit" fieldConversions="unitNumber:document.awardList[0].unitNumber"
  			                anchor="${tabKey}" lookupParameters="document.awardList[0].unitNumber:unitNumber"/>
  			    </c:if>
            </c:if>
            <c:if test="${docInSavedState or readOnly}">
                <html:hidden property="document.awardList[0].unitNumber" />
                -
                <kul:htmlControlAttribute property="document.awardList[0].unitName" attributeEntry="${awardAttributes['leadUnit.unitName']}" readOnly="true" />
            </c:if>
            <kul:directInquiry boClassName="org.kuali.coeus.common.framework.unit.Unit" inquiryParameters="document.awardList[0].unitNumber:unitNumber" anchor="${tabKey}" />
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
    		<kul:htmlControlAttribute property="document.awardList[0].accountTypeCode" attributeEntry="${awardAttributes.accountTypeCode}" readOnlyAlternateDisplay="${KualiForm.awardDocument.award.accountTypeDescription}" />
		</td>
  	</tr>
  	<tr>
    	<th>
    		<div align="right">
        		<kul:htmlAttributeLabel attributeEntry="${awardAttributes.statusCode}" />
      		</div>
      	</th>
    	<td>
    		<kul:htmlControlAttribute property="document.awardList[0].statusCode" attributeEntry="${awardAttributes.statusCode}" readOnlyAlternateDisplay="${KualiForm.awardDocument.award.awardStatus.description}" />
      	</td>

    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.activityTypeCode}" /></div>
    	</th>
    	<td>
    		<kul:htmlControlAttribute property="document.awardList[0].activityTypeCode" attributeEntry="${awardAttributes.activityTypeCode}" readOnlyAlternateDisplay="${KualiForm.awardDocument.award.activityType.description}" />
		</td>
  	</tr>
  	<tr>
    	<kra:section permission="viewAccountElement">

      	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.accountNumber}" /></div>
      	</th>
    	<td align="left" valign="middle">
    		<kul:htmlControlAttribute property="document.awardList[0].accountNumber" attributeEntry="${awardAttributes.accountNumber}" />
    	</td>
    	</kra:section>
    	<th>
    		<div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardTypeCode}" /></div>
      	</th>
    	<td>
    		<kul:htmlControlAttribute property="document.awardList[0].awardTypeCode" attributeEntry="${awardAttributes.awardTypeCode}" readOnlyAlternateDisplay="${KualiForm.awardDocument.award.awardType.description}" />
      	</td>
  	</tr>
  	 <%-- BU MOD changes starts here--%>
                <c:if test="${!KualiForm.parentAward}">

                    <tr>
                        <th>
                            <div align="right">
                                <kul:htmlAttributeLabel attributeEntry="${awardExtensionAttributes.childType}"/>
                            </div>
                        </th>
                        <td>
                            <kul:htmlControlAttribute property="document.awardList[0].extension.childType"
                                                      attributeEntry="${awardExtensionAttributes.childType}"
                                                      readOnlyAlternateDisplay="${KualiForm.awardDocument.award.extension.childType}"/>
                        </td>
                        <th>
                            <div align="right"><kul:htmlAttributeLabel
                                    attributeEntry="${awardExtensionAttributes.childDescription}"/></div>
                        </th>
                        <td>
                            <kul:htmlControlAttribute property="document.awardList[0].extension.childDescription"
                                                      attributeEntry="${awardExtensionAttributes.childDescription}"
                                                      readOnlyAlternateDisplay="${KualiForm.awardDocument.award.extension.childDescription}"/>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <th>
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${awardExtensionAttributes.grantNumber}"/>
                        </div>
                    </th>
                    <td>
                        <kul:htmlControlAttribute property="document.awardList[0].extension.grantNumber"
                                                  attributeEntry="${awardExtensionAttributes.grantNumber}" readOnly="true"
                                                  readOnlyAlternateDisplay="${KualiForm.awardDocument.award.extension.grantNumber}"/>
                    </td>
                    <c:choose>
                        <c:when test="${KualiForm.parentAward}">
                            <th>
                                <div align="right">
                                    <kul:htmlAttributeLabel attributeEntry="${awardExtensionAttributes.federalClinicalTrial}"/>
                                </div>
                            </th>
                            <td>
                                <kul:htmlControlAttribute property="document.awardList[0].extension.federalClinicalTrial"
                                                          attributeEntry="${awardExtensionAttributes.federalClinicalTrial}"
                                                          readOnlyAlternateDisplay="${KualiForm.awardDocument.award.extension.federalClinicalTrial? 'Yes' : 'No'}"/>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <th align="right">
                            </th>
                            <td align="left" valign="middle">
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                    <%-- BU MOD changes ends here--%>
  	<!-- Char of Accounts code element viewable only when the fin integration param is on -->
    <kra:section permission="viewChartOfAccountsElement">
    <tr>
    	<th>
    		<div align="right">
        		<kul:htmlAttributeLabel attributeEntry="${awardAttributes.financialChartOfAccountsCode}" />
      		</div>
      	</th>
    	<td colspan="3">
    		<kul:htmlControlAttribute property="document.awardList[0].financialChartOfAccountsCode" attributeEntry="${awardAttributes.financialChartOfAccountsCode}" />
      	</td>
     </tr>
    </kra:section>
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
</kul:innerTab>

<!-- Sponsor -->

<kul:innerTab parentTab="Details & Dates" tabItemCount="" defaultOpen="true" tabTitle="Sponsor" tabErrorKey="" >

<table cellpadding="0" cellspacing="0" summary="">
    <tr>
        <th>
            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.sponsorCode}" /></div>
        </th>
        <td>
        	<kul:htmlControlAttribute property="document.awardList[0].sponsorCode" attributeEntry="${awardAttributes.sponsorCode}"
        	                          onblur="loadSponsorName('document.awardList[0].sponsorCode', 'sponsorName');" readOnly="${readOnly}" />
        	<c:if test="${!readOnly}">
                <kul:lookup boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" fieldConversions="sponsorCode:document.awardList[0].sponsorCode,sponsorName:document.awardList[0].sponsor.sponsorName" anchor="${tabKey}" />
            </c:if>
	        <c:if test="${readOnly}">
	           <html:hidden property="document.awardList[0].sponsorCode" />
	        </c:if>
            <kul:directInquiry boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" inquiryParameters="document.awardList[0].sponsorCode:sponsorCode" anchor="${tabKey}" />
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
        	<kul:htmlControlAttribute property="document.awardList[0].primeSponsorCode" attributeEntry="${awardAttributes.primeSponsorCode}"
        	                          onblur="loadSponsorName('document.awardList[0].primeSponsorCode', 'primeSponsorName');" readOnly="${readOnly}" />
        	<c:if test="${!readOnly}">
                <kul:lookup boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" fieldConversions="sponsorCode:document.awardList[0].primeSponsorCode,sponsorName:document.awardList[0].primeSponsor.sponsorName" anchor="${tabKey}" />
            </c:if>
            <c:if test="${readOnly}">
               <html:hidden property="document.awardList[0].primeSponsorCode" />
            </c:if>
            <c:if test="${!readOnly or !empty KualiForm.document.awardList[0].primeSponsorCode}">
                <kul:directInquiry boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" inquiryParameters="document.awardList[0].primeSponsorCode:sponsorCode" anchor="${tabKey}" />
            </c:if>
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
            <!-- Char of Accounts code element viewable only when the fin integration param is on -->
    		<kra:section permission="viewChartOfAccountsElement">
            <c:if test="${!readOnly}">
    			<kul:lookup boClassName="org.kuali.kra.award.home.CFDA" fieldConversions="cfdaNumber:document.awardList[0].cfdaNumber" anchor="${tabKey}" />
    		</c:if>
    		<c:if test="${!readOnly or !empty KualiForm.document.awardList[0].cfdaNumber}">
    			<kul:directInquiry boClassName="org.kuali.kra.award.home.CFDA" inquiryParameters="document.awardList[0].cfdaNumber:cfdaNumber" anchor="${tabKey}" />
    		</c:if>
    		</kra:section>
		</td>
    </tr>
    <tr>
    <c:choose>
                    <c:when test="${KualiForm.parentAward}">
                        <th align="right">
                            <div align="right"><kul:htmlAttributeLabel
                                    attributeEntry="${awardExtensionAttributes.fain}"/>
                            </div>
                        </th>
                        <td align="left" valign="middle">
                            <kul:htmlControlAttribute property="document.awardList[0].extension.fain"
                                                      attributeEntry="${awardExtensionAttributes.fain}"/>
                        </td>
                    </c:when>
                    <c:otherwise>
                    <th align="right">
                    </th>
                    <td align="left" valign="middle">
                        </c:otherwise>
                        </c:choose>

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
</kul:innerTab>

<!-- Sponsor Funding Transferred -->

<kul:innerTab parentTab="Details & Dates" tabItemCount="" defaultOpen="false" tabTitle="Sponsor Funding Transferred" tabErrorKey="" >

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
    <tbody class="addline">
    <tr>
        <th>
            <div align="right">
	            <b>Add:</b>
            </div>
        </th>
        <td class="infoline">
        	<kul:htmlControlAttribute property="detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsorCode" attributeEntry="${awardAttributes.sponsorCode}" onblur="loadSponsorName('detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsorCode', 'newAwardTransferringSponsorName');" />
            <kul:lookup boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" fieldConversions="sponsorCode:detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsorCode,sponsorName:detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsor.sponsorName" anchor="${tabKey}" />
            <kul:directInquiry boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" inquiryParameters="detailsAndDatesFormHelper.sponsorToBecomeAwardTransferringSponsor.sponsorCode:sponsorCode" anchor="${tabKey}" />
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
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
			</div>
        </td>
    </tr>
    </tbody>
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
</kul:innerTab>
<%-- BU MOD starts here--%>
        <kul:innerTab parentTab="Details & Dates" tabItemCount="" defaultOpen="true" tabTitle="Project" tabErrorKey="">
            <table cellpAdding="0" cellspacing="0" summary="" id="project-table">
                <tr>
                    <th width="25%">
                        <div align="right">
                            <kul:htmlAttributeLabel attributeEntry="${awardExtensionAttributes.majorProject}"/>
                        </div>
                    </th>
                    <td width="25%">
                        <kul:htmlControlAttribute property="document.awardList[0].extension.majorProject"
                                                  attributeEntry="${awardExtensionAttributes.majorProject}"/>
                    </td>
                    <th width="25%">
                        <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardExtensionAttributes.arraCode}"/></div>
                    </th>
                    <td width="25%">
                        <kul:htmlControlAttribute property="document.awardList[0].extension.arraCode"
                                                  attributeEntry="${awardExtensionAttributes.arraCode}"/>
                    </td>
                </tr>
                <tr>
                    <th width="25%">
                        <div align="right"><kul:htmlAttributeLabel
                                attributeEntry="${awardExtensionAttributes.conferenceGrant}"/></div>
                    </th>
                    <td width="25%">
                        <kul:htmlControlAttribute property="document.awardList[0].extension.conferenceGrant"
                                                  attributeEntry="${awardExtensionAttributes.conferenceGrant}"/>
                    </td>
                    <th width="25%">
                        <div align="right">
                        </div>
                    </th>
                    <td width="25%" valign="middle">
                        <div align="left">
                        </div>
                    </td>
                </tr>

            </table>
        </kul:innerTab>
            <%-- BU MOD ends here--%>
<!-- Time &amp; Money -->

<kul:innerTab parentTab="Details & Dates" tabItemCount="" defaultOpen="true" tabTitle="Time & Money" tabErrorKey="" >

<table cellpAdding="0" cellspacing="0" summary="">
				<tr>
					<th>
						<div align="right">
							<kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardEffectiveDate}" />
						</div>
					</th>
					<td align="left" valign="middle">
                        <kul:htmlControlAttribute property="document.awardList[0].awardEffectiveDate" attributeEntry="${awardAttributes.awardEffectiveDate}" />
					</td>
					<c:if test="${not empty awardAmountInfoAttributes}">
						<th>
							<div align="right">
								<kul:htmlAttributeLabel
									attributeEntry="${awardAmountInfoAttributes.currentFundEffectiveDate}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
							</div>
						</th>
						<td align="left" valign="middle">
						<c:choose>
							<c:when
								test="${KualiForm.awardHasAssociatedTandMOrIsVersioned or KualiForm.awardInMultipleNodeHierarchy}">

								<div align="left">
									<fmt:formatDate
										value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.document.award.indexOfAwardAmountInfoForDisplay].currentFundEffectiveDate}"
										pattern="MM/dd/yyyy" />
								</div>

							</c:when>
							<c:otherwise>
								<kul:htmlControlAttribute
									property="document.awardList[0].awardAmountInfos[${KualiForm.document.award.indexOfAwardAmountInfoForDisplay}].currentFundEffectiveDate"
									attributeEntry="${awardAttributes.awardEffectiveDate}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
							</c:otherwise>
						</c:choose>
						</td>
					</c:if>
				</tr>
				<tr>
					<th>
						<div align="right">
							<kul:htmlAttributeLabel
								attributeEntry="${awardAmountInfoAttributes.finalExpirationDate}"/>
						</div>
					</th>

					<c:choose>
						<c:when
							test="${KualiForm.awardHasAssociatedTandMOrIsVersioned or KualiForm.awardInMultipleNodeHierarchy}">
							<td align="left" valign="middle">
								<div align="left">
									<fmt:formatDate
										value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.document.award.indexOfAwardAmountInfoForDisplay].finalExpirationDate}"
										pattern="MM/dd/yyyy" />
								</div>
							</td>

						</c:when>
						<c:otherwise>
							<td align="left" valign="middle">
                                <kul:htmlControlAttribute
									property="document.awardList[0].awardAmountInfos[${KualiForm.document.award.indexOfAwardAmountInfoForDisplay}].finalExpirationDate"
									attributeEntry="${awardAmountInfoAttributes.finalExpirationDate}"/>
							</td>
						</c:otherwise>
					</c:choose>
					<th>
						<div align="right">
							<kul:htmlAttributeLabel
								attributeEntry="${awardAmountInfoAttributes.obligationExpirationDate}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
						</div>
					</th>

					<c:choose>
						<c:when
							test="${KualiForm.awardHasAssociatedTandMOrIsVersioned or KualiForm.awardInMultipleNodeHierarchy}">
							<td align="left" valign="middle">
								<div align="left">
									<fmt:formatDate
										value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.document.award.indexOfAwardAmountInfoForDisplay].obligationExpirationDate}"
										pattern="MM/dd/yyyy" />
								</div>
							</td>

						</c:when>
						<c:otherwise>
							<td align="left" valign="middle">
                                <kul:htmlControlAttribute
									property="document.awardList[0].awardAmountInfos[${KualiForm.document.award.indexOfAwardAmountInfoForDisplay}].obligationExpirationDate"
									attributeEntry="${awardAmountInfoAttributes.obligationExpirationDate}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
 			        <th>
			            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.awardExecutionDate}" /></div>
			        </th>
			        <td align="left" valign="middle">
			            <kul:htmlControlAttribute property="document.awardList[0].awardExecutionDate" attributeEntry="${awardAttributes.awardExecutionDate}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
			        </td>
			        <th>
			            <div align="right"><kul:htmlAttributeLabel attributeEntry="${awardAttributes.beginDate}" /></div>
			        </th>
			        <td align="left" valign="middle">
			            <kul:htmlControlAttribute property="document.awardList[0].beginDate" attributeEntry="${awardAttributes.beginDate}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
			        </td>
			    </tr>
			        <c:choose>
						<c:when test="${KualiForm.awardHasAssociatedTandMOrIsVersioned or KualiForm.awardInMultipleNodeHierarchy}">
							<c:choose>
								<c:when test="${KualiForm.directIndirectViewEnabled == '1'}">
									<tr>
								        <th>
								            <div align="right">Anticipated Direct:</div>
								        </th>
										<td align="left" valign="middle">
					            			<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.indexOfAwardAmountInfoForDisplay].anticipatedTotalDirect}"/>
					        			</td>
					        			<th>
					            			<div align="right">Obligated Direct:</div>
					        			</th>
					        			<td align="left" valign="middle">
					           				<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.indexOfAwardAmountInfoForDisplay].obligatedTotalDirect}"/>
					        			</td>
				        			</tr>
				        			<tr>
					        			<th>
					            			<div align="right">Anticipated F&A:</div>
					        			</th>
					        			<td align="left" valign="middle">
					            			<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.indexOfAwardAmountInfoForDisplay].anticipatedTotalIndirect}"/>
					        			</td>
					        			<th>
					            			<div align="right">Obligated F&A:</div>
					        			</th>
					        			<td align="left" valign="middle">
					           				<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.indexOfAwardAmountInfoForDisplay].obligatedTotalIndirect}"/>
					        			</td>
					        		</tr>
					        		<tr>
					        			<th>
					            			<div align="right">Anticipated Total:</div>
					        			</th>
					        			<th align="left">
						        			<div>
						            			<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].anticipatedTotal}"/>
						        			</div>
					        			</th>
					        			<th>
					            			<div align="right">Obligated Total:</div>
					        			</th>
					        			<th align="left">
						        			<div>
						           				<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].obligatedTotal}"/>
						        			</div>
					        			</th>
				        			</tr>
								</c:when>
								<c:otherwise>
								<tr>
									<th>
				            			<div align="right">Anticipated Amount:</div>
				        			</th>
									<td align="left" valign="middle">
				            			<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.indexOfAwardAmountInfoForDisplay].anticipatedTotalAmount}"/>
				        			</td>
				        			<th>
				            			<div align="right">Obligated Amount:</div>
				        			</th>
				        			<td align="left" valign="middle">
				           				<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].awardAmountInfos[KualiForm.indexOfAwardAmountInfoForDisplay].amountObligatedToDate}"/>
				        			</td>
			        			</tr>
								</c:otherwise>
							</c:choose>
				        </c:when>
				        <c:otherwise>
					        <c:choose>
					        	<c:when test="${KualiForm.directIndirectViewEnabled == '1'}">
					        		<tr>
										<th>
					            			<div align="right">Anticipated Direct:</div>
					        			</th>
										<td align="left" valign="middle">
					            			<kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[${KualiForm.indexOfAwardAmountInfoForDisplay}].anticipatedTotalDirect" attributeEntry="${awardAmountInfoAttributes.anticipatedTotalDirect}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
					        			</td>
					        			<th>
					            			<div align="right">Obligated Direct:</div>
					        			</th>
					        			<td align="left" valign="middle">
					            			<kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[${KualiForm.indexOfAwardAmountInfoForDisplay}].obligatedTotalDirect" attributeEntry="${awardAmountInfoAttributes.obligatedTotalDirect}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
					        			</td>
			        				</tr>
			        				<tr>
										<th>
					            			<div align="right">Anticipated F&A:</div>
					        			</th>
										<td align="left" valign="middle">
					            			<kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[${KualiForm.indexOfAwardAmountInfoForDisplay}].anticipatedTotalIndirect" attributeEntry="${awardAmountInfoAttributes.anticipatedTotalIndirect}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
					        			</td>
					        			<th>
					            			<div align="right">Obligated F&A:</div>
					        			</th>
					        			<td align="left" valign="middle">
					            			<kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[${KualiForm.indexOfAwardAmountInfoForDisplay}].obligatedTotalIndirect" attributeEntry="${awardAmountInfoAttributes.obligatedTotalIndirect}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
					        			</td>
			        				</tr>
			        				<tr>
					        			<th>
					            			<div align="right">Anticipated Total:</div>
					        			</th>
					        			<th align="left">
						        			<div>
						            			<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].anticipatedTotal}"/>
						        			</div>
					        			</th>
					        			<th>
					            			<div align="right">Obligated Total:</div>
					        			</th>
					        			<th align="left">
						        			<div>
						           				<fmt:formatNumber currencySymbol="$" type="currency" value="${KualiForm.document.awardList[0].obligatedTotal}"/>
						        			</div>
					        			</th>
					        		</tr>
		        				</c:when>
								<c:otherwise>
									<tr>
										<th>
                                            <div align="right">Anticipated Amount:</div>
					        			</th>
										<td align="left" valign="middle">
					            			<kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[${KualiForm.indexOfAwardAmountInfoForDisplay}].anticipatedTotalAmount" attributeEntry="${awardAttributes.anticipatedTotal}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
					        			</td>
					        			<th>
					            			<div align="right">Obligated Amount:</div>
					        			</th>
					        			<td align="left" valign="middle">
					            			<kul:htmlControlAttribute property="document.awardList[0].awardAmountInfos[${KualiForm.indexOfAwardAmountInfoForDisplay}].amountObligatedToDate" attributeEntry="${awardAttributes.obligatedTotal}" readOnly="${!awardObligatedAndAnticipatedAmountsEditable}"/>
					        			</td>
			        				</tr>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
 </table>
</kul:innerTab>

</div>

</kul:tab>
