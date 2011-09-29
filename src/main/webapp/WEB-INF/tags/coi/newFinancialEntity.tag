<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="personFinIntDisclAttribute" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<c:set var="entityContactInfoAttribute" value="${DataDictionary.FinancialEntityContactInfo.attributes}" />

<kul:tab defaultOpen="true" tabTitle="New Financial Entity " transparentBackground="true"
    tabErrorKey="financialEntityHelper.newPersonFinancialEntity.*" >

    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left"> 
            <a href="#" id ="financialEntityControl" class="financialEntitySubpanel"><img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> Financial Entity </span>
            <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.coi.personfinancialentity.FinIntEntityStatus" altText="help"/> </span>
        </h3>
      <div id="financialEntityContent" class="financialEntitySubpanelContent">                    
        <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
            <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityName}" />
                </th>
                <td align="left" valign="middle" colspan="3" >
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.entityName" 
                                              attributeEntry="${personFinIntDisclAttribute.entityName}" /> 
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityTypeCode}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                   <%--  <div align="center" class="fixed-size-270-div">--%>
                        <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.entityTypeCode" 
                                              attributeEntry="${personFinIntDisclAttribute.entityTypeCode}" />
                   <%-- </div>  --%>                         
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.sponsorCode}" readOnly="true" />
                </th>
                 <td align="left" valign="middle">
                    <%-- kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.sponsorCode" attributeEntry="${personFinIntDisclAttribute.sponsorCode}" onblur="loadSponsor('financialEntityHelper.newPersonFinancialEntity.sponsorCode', 'sponsorName', 'financialEntityHelper.newRolodexId');loadEntityContactInfoFromRolodex('financialEntityHelper.newRolodexId','financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0]');false" / --%>
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.sponsorCode" attributeEntry="${personFinIntDisclAttribute.sponsorCode}" onblur="loadSponsor('financialEntityHelper.newPersonFinancialEntity.sponsorCode', 'sponsorName', 'financialEntityHelper.prevNewSponsorCode');false" />
                	<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:financialEntityHelper.newPersonFinancialEntity.sponsorCode,sponsorName:financialEntityHelper.newPersonFinancialEntity.sponsor.sponsorName,postalCode:financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].postalCode,countryCode:financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].countryCode,rolodexId:financialEntityHelper.newRolodexId" anchor="${tabKey}" />
                    <kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="financialEntityHelper.newPersonFinancialEntity.sponsorCode:sponsorCode" anchor="${tabKey}" />
                    <div id="messageBox" style="display:none;"></div>
                    <input type="hidden" name="financialEntityHelper.newRolodexId" value="${KualiForm.financialEntityHelper.newRolodexId}" />
                    <input type="hidden" name="financialEntityHelper.prevNewSponsorCode" value="${KualiForm.financialEntityHelper.prevNewSponsorCode}"/>
                    <div id="sponsorName.div" >
                        <c:if test="${!empty KualiForm.financialEntityHelper.newPersonFinancialEntity.sponsorCode}" >
            				<c:choose>
							    <c:when test="${empty KualiForm.financialEntityHelper.newPersonFinancialEntity.sponsor}">
	                    			<span style='color: red;'>not found</span>
	               				</c:when>
	                  			<c:otherwise>
										<c:out value="${KualiForm.financialEntityHelper.newPersonFinancialEntity.sponsor.sponsorName}" />
								</c:otherwise>  
							</c:choose>                        
                        </c:if>
					</div>
				</td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.relatedToOrganizationFlag}" readOnly="true" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.relatedToOrganizationFlag" 
                                              attributeEntry="${personFinIntDisclAttribute.relatedToOrganizationFlag}" /> 
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.statusCode}" />
                </th>
                <td align="left" valign="middle">
                        <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.statusCode" 
                                              attributeEntry="${personFinIntDisclAttribute.statusCode}" />
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityOwnershipType}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.entityOwnershipType" 
                                              attributeEntry="${personFinIntDisclAttribute.entityOwnershipType}" /> 
                </td>
            </tr>    
            <%-- contact info --%>
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine1}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].addressLine1" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine1}" /> 
                </td>
                <th align="right" valign="middle" rowspan="3">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.relationshipDescription}" />
                </th>
                <td align="left" valign="middle" rowspan="3" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.relationshipDescription" 
                                              attributeEntry="${personFinIntDisclAttribute.relationshipDescription}" /> 
                </td>

            </tr>    
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine2}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].addressLine2" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine2}" /> 
                </td>
            </tr>    
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine3}" />
                </th>
                <td align="left" valign="middle" colspan="3" >
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].addressLine3" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine3}" /> 
                </td>
            </tr>  
            <tr>  
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.city}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].city" 
                                              attributeEntry="${entityContactInfoAttribute.city}" /> 
                </td>
                <th align="right" valign="middle" rowspan="3">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.orgRelationDescription}" />
                </th>
                <td align="left" valign="middle" rowspan="3" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.orgRelationDescription" 
                                              attributeEntry="${personFinIntDisclAttribute.orgRelationDescription}" /> 
                </td>
            </tr>
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.state}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].state" 
                                              attributeEntry="${entityContactInfoAttribute.state}" /> 
                </td>
            </tr>
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.countryCode}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].countryCode" 
                                              attributeEntry="${entityContactInfoAttribute.countryCode}" onchange="updateStateCode('financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].countryCode','');"/> 
                </td>
            </tr>    
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.postalCode}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].postalCode" 
                                              attributeEntry="${entityContactInfoAttribute.postalCode}" /> 
                </td>
                <th align="right" valign="middle" rowspan="3">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.principalBusinessActivity}" />
                </th>
                <td align="left" valign="middle" rowspan="3" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.principalBusinessActivity" 
                                              attributeEntry="${personFinIntDisclAttribute.principalBusinessActivity}" /> 
                </td>
           </tr> 
           <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.webAddress1}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].webAddress1" 
                                              attributeEntry="${entityContactInfoAttribute.webAddress1}" /> 
                </td>
           </tr>     
           <tr>

                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.webAddress2}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.newPersonFinancialEntity.finEntityContactInfos[0].webAddress2" 
                                              attributeEntry="${entityContactInfoAttribute.webAddress2}" /> 
                </td>

           </tr>           
        </table>
       </div> 
    </div>
    <kra-coi:financialEntityRelationshipDetails prop="financialEntityHelper.newRelationDetails" detailList="${KualiForm.financialEntityHelper.newRelationDetails}"
       methodtocall="methodToCall.submit.new"/>
</kul:tab>