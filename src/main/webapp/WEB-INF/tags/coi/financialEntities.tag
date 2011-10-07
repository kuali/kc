<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="personFinIntDisclAttribute" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<c:set var="entityContactInfoAttribute" value="${DataDictionary.FinancialEntityContactInfo.attributes}" />

<kul:tab defaultOpen="true" tabTitle="Financial Entities"  transparentBackground="true"
    tabErrorKey="financialEntityHelper.activeFinancialEntities*">

    <div class="tab-container" align="center">

       <%-- <h3>
            <span class="subhead-left"> Financial Entities </span>
            <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.coi.personfinancialentity.FinIntEntityStatus" altText="help"/> </span>
        </h3>
        --%>    
          <table cellpadding=0 cellspacing=0 summary=""> 
              <tr> 
                <td nowrap class="subhead"  width="5%" > &nbsp </td>
                <td nowrap class="subhead" width="25%" >Active Entities</td> 
                <td nowrap class="subhead" width="35%" >Sponsor</td> 
                <td nowrap class="subhead" width="15%" >Last Update</td> 
                <td nowrap class="subhead" width="20%" ><div align="center">Actions</div></td> 
              </tr> 
              <input type="hidden" name="editIndex" id="editIndex" value="${KualiForm.financialEntityHelper.editEntityIndex}" />
              <input type="hidden" name="editType" id="editType" value="${KualiForm.financialEntityHelper.editType}" />

            <%-- Header --%>

             <tr>
                    <td colspan="5" style="padding:0px;">
                        
                        <div>
                            <table id="activeEntities-table" cellpadding=0 cellspacing="0"  style="border-collapse:collapse;" class="tablesorter">
                                <thead>
                                    <tr>
                                        <th width="5%" />
                                        <th width="25%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th width="35%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th width="15%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th class="{sorter: false}" width="20%"> </th>
                                   </tr>
                                </thead>            
                                <tbody>         

            <c:forEach var="financialEntity" items="${KualiForm.financialEntityHelper.activeFinancialEntities}" varStatus="status">
              <tr id="hdrrow${status.index}"> 
                <td nowrap class="tab-subhead1"><a href="#" id="A${status.index}" onclick="rend(this, false)"><img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-show.gif" alt="show/hide this panel" width=45 height=15 border=0 align=absmiddle id="F${status.index}"></a></td> 
                    <td align="left" valign="middle" class="tab-subhead1">
                        <div align="left"> ${financialEntity.entityName} </div>
                    </td>
                    <td align="left" valign="middle" class="tab-subhead1">
                        <div align="left"> ${financialEntity.sponsorName} </div>
                    </td>
                    <td align="left" valign="middle" class="tab-subhead1">
                      <fmt:formatDate value="${KualiForm.financialEntityHelper.activeFinancialEntities[status.index].updateTimestamp}" pattern="MM/dd/yyyy hh:mm a" />
                    <%-- <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].updateTimestamp" 
                                                          attributeEntry="${attributes.updateTimestamp}"
                                                          readOnly="true" /> --%>
                    </td>
                        <td class="tab-subhead1">
                            <div align=center>&nbsp;   
                            
                                             
                                <c:if test="${KualiForm.financialEntityHelper.editEntityIndex != status.index or KualiForm.financialEntityHelper.editType != 'active'}">
                                    <c:choose>
                                         <c:when test="${KualiForm.financialEntityHelper.activeFinancialEntities[status.index].processStatus == 'F'}">
                                    <html:image property="methodToCall.editActiveFinancialEntity.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton" title="Edit"/>
                                         </c:when>
                                         <c:otherwise>
                                    <html:image property="methodToCall.editActiveFinancialEntity.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-resumeedit.gif' styleClass="tinybutton" title="Resume Edit"/>
                                         </c:otherwise>
                                    </c:choose>
                                </c:if>
                                <c:if test="${KualiForm.financialEntityHelper.activeFinancialEntities[status.index].processStatus == 'F'}">
                                <html:image property="methodToCall.inactivateFinancialEntity.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-deactivate.gif' styleClass="tinybutton" title="Deactivate"/>
                                 </c:if>       

                            </div>
                        </td>
              </tr> 
              <tr id="G${status.index}" style="display: none;"> <td colspan="5">
              <table id="active-hist-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
            <%-- tbody id="G${status.index}" style="display: none;" --%> 
              <%-- version--%> 
              
              <%-- replaced by 'history' button
               <tr >
                  <td colspan=5 >
                    <kra-coi:financialEntityVersionHistory  financialEntity="${financialEntity}" />
                 </td>
                </tr>
               --%>   
              <c:if test="${KualiForm.financialEntityHelper.editEntityIndex == status.index and KualiForm.financialEntityHelper.editType == 'active'}">
                <tr> 
                  <td colspan="5">
     <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left"> 
            <a href="#" id ="financialEntityControl" class="financialEntitySubpanel"><img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> Financial Entity </span>
            <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.coi.personfinancialentity.FinIntEntityStatus" altText="help"/> </span>
        </h3>
      <div id="financialEntityContent" class="financialEntitySubpanelContent">                    

                     <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
            <tr>
                <th align="right" valign="middle">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityName}" />
                </th>
                <td align="left" valign="middle"  colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].entityName" 
                                              attributeEntry="${personFinIntDisclAttribute.entityName}" /> 
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityTypeCode}" />
                </th>
                <td align="left" valign="middle"  colspan="3">
                   <%--  <div align="center" class="fixed-size-270-div">--%>
                        <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].entityTypeCode" 
                                              attributeEntry="${personFinIntDisclAttribute.entityTypeCode}" />
                   <%-- </div>  --%> 
                   
                                           
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.sponsorCode}" readOnly="true" />
                </th>
                 <td align="left" valign="middle" >
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].sponsorCode" attributeEntry="${personFinIntDisclAttribute.sponsorCode}" onblur="loadSponsor('financialEntityHelper.activeFinancialEntities[${status.index}].sponsorCode', 'sponsorName${status.index}', 'financialEntityHelper.prevSponsorCode');false" />
                    <kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:financialEntityHelper.activeFinancialEntities[${status.index}].sponsorCode,sponsorName:financialEntityHelper.activeFinancialEntities[${status.index}].sponsor.sponsorName" anchor="${tabKey}" />
                    <kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="financialEntityHelper.activeFinancialEntities[${status.index}].sponsorCode:sponsorCode" anchor="${tabKey}" />
                    <div id="messageBox${status.index}" style="display:none;"></div>
                    <input type="hidden" name="financialEntityHelper.editRolodexId" value="${KualiForm.financialEntityHelper.editRolodexId}" />
                    <input type="hidden" name="financialEntityHelper.prevSponsorCode" value="${KualiForm.financialEntityHelper.prevSponsorCode}"/>
                    <div id="sponsorName${status.index}.div" >
                        <c:if test="${!empty KualiForm.financialEntityHelper.activeFinancialEntities[status.index].sponsorCode}">
                            <c:choose>
                                <c:when test="${empty KualiForm.financialEntityHelper.activeFinancialEntities[status.index].sponsor}">
                                    <span style='color: red;'>not found</span>
                                </c:when>
                                <c:otherwise>
                                        <c:out value="${KualiForm.financialEntityHelper.activeFinancialEntities[status.index].sponsor.sponsorName}" />
                                </c:otherwise>  
                            </c:choose>                        
                        </c:if>
                    </div>
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.relatedToOrganizationFlag}" readOnly="true" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].relatedToOrganizationFlag" 
                                              attributeEntry="${personFinIntDisclAttribute.relatedToOrganizationFlag}" /> 
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.statusCode}" />
                </th>
                <td align="left" valign="middle">
                        <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].statusCode" 
                                              attributeEntry="${personFinIntDisclAttribute.statusCode}" />
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityOwnershipType}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].entityOwnershipType" 
                                              attributeEntry="${personFinIntDisclAttribute.entityOwnershipType}" /> 
                </td>
            </tr>            
            <%-- contact info --%>
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine1}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].finEntityContactInfos[0].addressLine1" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine1}" /> 
                </td>
                <th align="right" valign="middle" rowspan="3">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.relationshipDescription}" />
                </th>
                <td align="left" valign="middle" rowspan="3" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].relationshipDescription" 
                                              attributeEntry="${personFinIntDisclAttribute.relationshipDescription}" /> 
                </td>
            </tr>    
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine2}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].finEntityContactInfos[0].addressLine2" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine2}" /> 
                </td>
            </tr>    
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine3}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].finEntityContactInfos[0].addressLine3" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine3}" /> 
                </td>
            </tr>    
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.city}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].finEntityContactInfos[0].city" 
                                              attributeEntry="${entityContactInfoAttribute.city}" /> 
                </td>
                <th align="right" valign="middle" rowspan="3">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.orgRelationDescription}" />
                </th>
                <td align="left" valign="middle" rowspan="3" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].orgRelationDescription" 
                                              attributeEntry="${personFinIntDisclAttribute.orgRelationDescription}" /> 
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.state}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].finEntityContactInfos[0].state" 
                                              attributeEntry="${entityContactInfoAttribute.state}" /> 
                </td>
            </tr>
            <tr>    
                 <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.countryCode}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].finEntityContactInfos[0].countryCode" 
                                              attributeEntry="${entityContactInfoAttribute.countryCode}" onchange="updateStateCode('financialEntityHelper.activeFinancialEntities[${status.index}].finEntityContactInfos[0].countryCode','');"/> 
                </td>
            </tr>    
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.postalCode}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].finEntityContactInfos[0].postalCode" 
                                              attributeEntry="${entityContactInfoAttribute.postalCode}" /> 
                </td>
               <th align="right" valign="middle" rowspan="3">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.principalBusinessActivity}" />
                </th>
                <td align="left" valign="middle" rowspan="3" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].principalBusinessActivity" 
                                              attributeEntry="${personFinIntDisclAttribute.principalBusinessActivity}" /> 
                </td>
           </tr>  
           <tr>
               <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.webAddress1}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].finEntityContactInfos[0].webAddress1" 
                                              attributeEntry="${entityContactInfoAttribute.webAddress1}" /> 
                </td>
           </tr>          
           <tr>
               <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.webAddress2}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].finEntityContactInfos[0].webAddress2" 
                                              attributeEntry="${entityContactInfoAttribute.webAddress2}" /> 
                </td>
           </tr>          
        </table>
      </div>          
     </div>
                </td>
                </tr>
                
           <%-- data matrix --%>     
           <tr>
             <td colspan="5">
                 <kra-coi:financialEntityRelationshipDetails prop="financialEntityHelper.editRelationDetails" detailList="${KualiForm.financialEntityHelper.editRelationDetails}"
                      methodtocall="methodToCall.submit.line${status.index}"/>
             
             </td>
            </tr>     
                </c:if>
              <%-- /tbody --%>
               </table> </td></tr>
              
           </c:forEach>   
                       </tbody>     
                    </table>
                  </div>
               </td>
            </tr>                           
        </table>

  <%-- Inactive --%>
  
            <table cellpadding=0 cellspacing=0 summary=""> 
              <tr> 
                <td nowrap class="subhead"  width="5%" > &nbsp </td>
                <td nowrap class="subhead" width="25%" >Inactive Entities</td> 
                <td nowrap class="subhead" width="35%" >Sponsor</td> 
                <td nowrap class="subhead" width="15%" >Last Update</td> 
                <td nowrap class="subhead" width="20%" ><div align="center">Actions</div></td> 
              </tr> 

            <%-- Header --%>

             <tr>
                    <td colspan="5" style="padding:0px;">
                        
                        <div>
                            <table id="inActiveEntities-table" cellpadding=0 cellspacing="0"  style="border-collapse:collapse;" class="tablesorter">
                                <thead>
                                    <tr>
                                        <th width="5%" />
                                        <th width="25%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th width="35%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th width="15%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th class="{sorter: false}" width="20%"> </th>
                                   </tr>
                                </thead>            
                                <tbody>         

            <%--  <input type="hidden" name="editIndex" id="editIndex" value="${KualiForm.financialEntityHelper.editEntityIndex}" /> --%>
            <c:forEach var="financialEntity" items="${KualiForm.financialEntityHelper.inactiveFinancialEntities}" varStatus="status">
              <tr id="hdrrow${status.index}i"> 
                <td nowrap class="tab-subhead1"><a href="#" id="A${status.index}i" onclick="rend(this, false)"><img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-show.gif" alt="show/hide this panel" width=45 height=15 border=0 align=absmiddle id="F${status.index}i"></a></td> 
                    <td align="left" valign="middle" class="tab-subhead1">
                        <div align="left"> ${financialEntity.entityName} </div>
                    </td>
                    <td align="left" valign="middle" class="tab-subhead1">
                        <div align="left"> ${financialEntity.sponsorName} </div>
                    </td>
                    <td align="left" valign="middle" class="tab-subhead1">
                      <fmt:formatDate value="${KualiForm.financialEntityHelper.inactiveFinancialEntities[status.index].updateTimestamp}" pattern="MM/dd/yyyy hh:mm a" />
                    <%-- <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].updateTimestamp" 
                                                          attributeEntry="${attributes.updateTimestamp}"
                                                          readOnly="true" /> --%>
                    </td>
                        <td class="tab-subhead1">
                            <div align=center>&nbsp;   
                            
                            
                                   <c:if test="${KualiForm.financialEntityHelper.editEntityIndex != status.index or KualiForm.financialEntityHelper.editType != 'inactive'}">
                                    <c:choose>
                                         <c:when test="${KualiForm.financialEntityHelper.inactiveFinancialEntities[status.index].processStatus == 'F'}">
                                    <html:image property="methodToCall.editInactiveFinancialEntity.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton" title="Edit"/>
                                         </c:when>
                                         <c:otherwise>
                                    <html:image property="methodToCall.editInactiveFinancialEntity.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-resumeedit.gif' styleClass="tinybutton" title="Resume Edit"/>
                                         </c:otherwise>
                                    </c:choose>
                                </c:if>
                                <c:if test="${KualiForm.financialEntityHelper.inactiveFinancialEntities[status.index].processStatus == 'F'}">
                                <html:image property="methodToCall.activateFinancialEntity.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-activate.gif' styleClass="tinybutton" title="Activate"/>
                                 </c:if>       

                            </div>
                        </td>
              </tr> 
              <tr id="G${status.index}i" style="display: none;"> <td colspan="5">
              <table id="inactive-hist-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
              <%-- <tbody id="G${status.index}i" style="display: none;"> --%>
             <%-- 
               <tr>
                  <td colspan=5>
                    <kra-coi:financialEntityVersionHistory  financialEntity="${financialEntity}" />
                 </td>
                </tr>
              --%>
 <%-- edit --%>
 
               <c:if test="${KualiForm.financialEntityHelper.editEntityIndex == status.index and KualiForm.financialEntityHelper.editType == 'inactive'}">
                <tr> 
                  <td colspan="5">
     <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left"> 
            <a href="#" id ="financialEntityControl" class="financialEntitySubpanel"><img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> Financial Entity </span>
            <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.coi.personfinancialentity.FinIntEntityStatus" altText="help"/> </span>
        </h3>
      <div id="financialEntityContent" class="financialEntitySubpanelContent">                    

                     <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
            <tr>
                <th align="right" valign="middle">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityName}" />
                </th>
                <td align="left" valign="middle"  colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].entityName" 
                                              attributeEntry="${personFinIntDisclAttribute.entityName}" /> 
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityTypeCode}" />
                </th>
                <td align="left" valign="middle"  colspan="3">
                   <%--  <div align="center" class="fixed-size-270-div">--%>
                        <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].entityTypeCode" 
                                              attributeEntry="${personFinIntDisclAttribute.entityTypeCode}" />
                   <%-- </div>  --%> 
                   
                                           
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.sponsorCode}" readOnly="true" />
                </th>
                 <td align="left" valign="middle" >
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].sponsorCode" attributeEntry="${personFinIntDisclAttribute.sponsorCode}" onblur="loadSponsor('financialEntityHelper.inactiveFinancialEntities[${status.index}].sponsorCode', 'sponsorName${status.index}', 'financialEntityHelper.prevSponsorCode');false" />
                    <kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:financialEntityHelper.inactiveFinancialEntities[${status.index}].sponsorCode,sponsorName:financialEntityHelper.inactiveFinancialEntities[${status.index}].sponsor.sponsorName" anchor="${tabKey}" />
                    <kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="financialEntityHelper.inactiveFinancialEntities[${status.index}].sponsorCode:sponsorCode" anchor="${tabKey}" />
                    <div id="messageBox${status.index}" style="display:none;"></div>
                    <input type="hidden" name="financialEntityHelper.editRolodexId" value="${KualiForm.financialEntityHelper.editRolodexId}" />
                    <input type="hidden" name="financialEntityHelper.prevSponsorCode" value="${KualiForm.financialEntityHelper.prevSponsorCode}"/>
                    <div id="sponsorName${status.index}.div" >
                        <c:if test="${!empty KualiForm.financialEntityHelper.inactiveFinancialEntities[status.index].sponsorCode}">
                            <c:choose>
                                <c:when test="${empty KualiForm.financialEntityHelper.inactiveFinancialEntities[status.index].sponsor}">
                                    <span style='color: red;'>not found</span>
                                </c:when>
                                <c:otherwise>
                                        <c:out value="${KualiForm.financialEntityHelper.inactiveFinancialEntities[status.index].sponsor.sponsorName}" />
                                </c:otherwise>  
                            </c:choose>                        
                        </c:if>
                    </div>
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.relatedToOrganizationFlag}" readOnly="true" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].relatedToOrganizationFlag" 
                                              attributeEntry="${personFinIntDisclAttribute.relatedToOrganizationFlag}" /> 
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.statusCode}" />
                </th>
                <td align="left" valign="middle">
                        <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].statusCode" 
                                              attributeEntry="${personFinIntDisclAttribute.statusCode}" />
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityOwnershipType}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].entityOwnershipType" 
                                              attributeEntry="${personFinIntDisclAttribute.entityOwnershipType}" /> 
                </td>
            </tr>            
            <%-- contact info --%>
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine1}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].finEntityContactInfos[0].addressLine1" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine1}" /> 
                </td>
                <th align="right" valign="middle" rowspan="3">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.relationshipDescription}" />
                </th>
                <td align="left" valign="middle" rowspan="3" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].relationshipDescription" 
                                              attributeEntry="${personFinIntDisclAttribute.relationshipDescription}" /> 
                </td>
            </tr>    
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine2}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].finEntityContactInfos[0].addressLine2" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine2}" /> 
                </td>
            </tr>    
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.addressLine3}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].finEntityContactInfos[0].addressLine3" 
                                              attributeEntry="${entityContactInfoAttribute.addressLine3}" /> 
                </td>
            </tr>    
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.city}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].finEntityContactInfos[0].city" 
                                              attributeEntry="${entityContactInfoAttribute.city}" /> 
                </td>
                <th align="right" valign="middle" rowspan="3">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.orgRelationDescription}" />
                </th>
                <td align="left" valign="middle" rowspan="3" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].orgRelationDescription" 
                                              attributeEntry="${personFinIntDisclAttribute.orgRelationDescription}" /> 
                </td>
            </tr>
            <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.state}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].finEntityContactInfos[0].state" 
                                              attributeEntry="${entityContactInfoAttribute.state}" /> 
                </td>
            </tr>
            <tr>    
                 <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.countryCode}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].finEntityContactInfos[0].countryCode" 
                                              attributeEntry="${entityContactInfoAttribute.countryCode}" onchange="updateStateCode('financialEntityHelper.inactiveFinancialEntities[${status.index}].finEntityContactInfos[0].countryCode','');"/> 
                </td>
            </tr>    
             <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.postalCode}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].finEntityContactInfos[0].postalCode" 
                                              attributeEntry="${entityContactInfoAttribute.postalCode}" /> 
                </td>
               <th align="right" valign="middle" rowspan="3">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.principalBusinessActivity}" />
                </th>
                <td align="left" valign="middle" rowspan="3" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].principalBusinessActivity" 
                                              attributeEntry="${personFinIntDisclAttribute.principalBusinessActivity}" /> 
                </td>
           </tr>  
           <tr>
               <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.webAddress1}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].finEntityContactInfos[0].webAddress1" 
                                              attributeEntry="${entityContactInfoAttribute.webAddress1}" /> 
                </td>
           </tr>          
           <tr>
               <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${entityContactInfoAttribute.webAddress2}" />
                </th>
                <td align="left" valign="middle" colspan="3">
                    <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].finEntityContactInfos[0].webAddress2" 
                                              attributeEntry="${entityContactInfoAttribute.webAddress2}" /> 
                </td>
           </tr>          
        </table>
      </div>          
     </div>
                </td>
                </tr>
                
           <%-- data matrix --%>     
           <tr>
             <td colspan="5">
                 <kra-coi:financialEntityRelationshipDetails prop="financialEntityHelper.editRelationDetails" detailList="${KualiForm.financialEntityHelper.editRelationDetails}"
                      methodtocall="methodToCall.submit.line${status.index}"/>
             
             </td>
            </tr>     
                </c:if>
 
                
                 
              <%--</tbody> --%> 
                </table> </td></tr>
              
           </c:forEach>   
           
                       </tbody>     
                    </table>
                  </div>
               </td>
            </tr>                           
           
        </table>
        
    </div>

</kul:tab>