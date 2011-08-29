<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="personFinIntDisclAttribute" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />

<kul:tab defaultOpen="false" tabTitle="Financial Entities"
    tabErrorKey="financialEntityHelper.activeFinancialEntities*">

    <div class="tab-container" align="center">

       <%-- <h3>
            <span class="subhead-left"> Financial Entities </span>
            <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.coi.personfinancialentity.FinIntEntityStatus" altText="help"/> </span>
        </h3>
        --%>    
          <table cellpadding=0 cellspacing=0 summary=""> 
              <tr> 
                <td colspan="2" nowrap class="subhead">Active Entities</td> 
                <td nowrap class="subhead">Relationship to Entity</td> 
                <td nowrap class="subhead">Last Update</td> 
                <td nowrap class="subhead"><div align="center">Actions</div></td> 
              </tr> 
              <input type="hidden" name="editIndex" id="editIndex" value="${KualiForm.financialEntityHelper.editEntityIndex}" />
            <c:forEach var="financialEntity" items="${KualiForm.financialEntityHelper.activeFinancialEntities}" varStatus="status">
              <tr> 
                <td nowrap class="tab-subhead1"><a href="#" id="A${status.index}" onclick="rend(this, false)"><img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-show.gif" alt="show/hide this panel" width=45 height=15 border=0 align=absmiddle id="F${status.index}"></a></td> 
                    <td align="left" valign="middle" class="tab-subhead1">
                        <div align="left"> ${financialEntity.entityName} </div>
                    </td>
                    <td align="left" valign="middle" class="tab-subhead1">
                        <div align="left"> ${financialEntity.relationshipDescription} </div>
                    </td>
                    <td align="left" valign="middle" class="tab-subhead1">
                     <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].updateTimestamp" 
                                                          attributeEntry="${attributes.updateTimestamp}"
                                                          readOnly="true" />
                    </td>
                        <td class="tab-subhead1">
                            <div align=center>&nbsp;                    
                                <c:if test="${KualiForm.financialEntityHelper.editEntityIndex != status.index}">
                                    <html:image property="methodToCall.editFinancialEntity.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton"/>
                                </c:if>
                                <html:image property="methodToCall.inactivateFinancialEntity.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-deactivate.gif' styleClass="tinybutton"/>
                            </div>
                        </td>
              </tr> 
              <tbody id="G${status.index}" style="display: none;"> 
              <%-- TODO : questionnaire not ready yet
                 <tr> 
                  <td align=left  style="padding:0px; border:none">&nbsp;</td> 
                  <td colspan="4" align=left  style="padding:0px; border:none"><table cellpadding=0 cellspacing=0 summary="" style=" width:100%"> 
                      <tbody> 
                        <tr> 
                          <td colspan="5" align=left ><strong>History of Changes</strong></td> 
                        </tr> 
                        <tr> 
                          <th align=left>Questionnaire Name</th> 
                          <th align=left>Questionnaire Description</th> 
                          <th align=left>Last Update</th> 
                          <th align=left>Entity Disclosed Location</th> 
                          <th align=left>Actions</th> 
                        </tr> 
                      </tbody> 
                    </table></td> 
                </tr>
                --%>
              <c:if test="${KualiForm.financialEntityHelper.editEntityIndex == status.index}">
                <tr> 
                  <td colspan="5">
                     <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
            <tr>
                <th align="right" valign="middle">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityName}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].entityName" 
                                              attributeEntry="${personFinIntDisclAttribute.entityName}" /> 
                </td>
                <th align="right" valign="middle">
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.sponsorCode}" readOnly="true" />
                </th>
                 <td align="left" valign="middle" >
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].sponsorCode" attributeEntry="${personFinIntDisclAttribute.sponsorCode}" onblur="loadSponsorName('financialEntityHelper.activeFinancialEntities[${status.index}].sponsorCode', 'sponsorName${status.index}');false" />
                    <kul:lookup boClassName="org.kuali.kra.bo.Sponsor" fieldConversions="sponsorCode:financialEntityHelper.activeFinancialEntities[${status.index}].sponsorCode,sponsorName:financialEntityHelper.activeFinancialEntities[${status.index}].sponsor.sponsorName" anchor="${tabKey}" />
                    <kul:directInquiry boClassName="org.kuali.kra.bo.Sponsor" inquiryParameters="financialEntityHelper.activeFinancialEntities[${status.index}].sponsorCode:sponsorCode" anchor="${tabKey}" />
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
            </tr>
            <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityTypeCode}" />
                </th>
                <td align="left" valign="middle">
                   <%--  <div align="center" class="fixed-size-270-div">--%>
                        <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].entityTypeCode" 
                                              attributeEntry="${personFinIntDisclAttribute.entityTypeCode}" />
                   <%-- </div>  --%>                         
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.relatedToOrganizationFlag}" readOnly="true" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].relatedToOrganizationFlag" 
                                              attributeEntry="${personFinIntDisclAttribute.relatedToOrganizationFlag}" /> 
                </td>
            </tr>            
            <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.statusCode}" />
                </th>
                <td align="left" valign="middle">
                        <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].statusCode" 
                                              attributeEntry="${personFinIntDisclAttribute.statusCode}" />
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.relationshipDescription}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].relationshipDescription" 
                                              attributeEntry="${personFinIntDisclAttribute.relationshipDescription}" /> 
                </td>
            </tr>            
            <tr>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityOwnershipType}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].entityOwnershipType" 
                                              attributeEntry="${personFinIntDisclAttribute.entityOwnershipType}" /> 
                </td>
                <th align="right" valign="middle" >
                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.orgRelationDescription}" />
                </th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="financialEntityHelper.activeFinancialEntities[${status.index}].orgRelationDescription" 
                                              attributeEntry="${personFinIntDisclAttribute.orgRelationDescription}" /> 
                </td>
            </tr>            
                <tr>
                    <td align="center" colspan="4">
                        <div align="center">
                            <html:image property="methodToCall.submit.line${status.index}.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
        </table>
                
                </td>
                </tr>
                </c:if>
                 
              </tbody> 
              
           </c:forEach>   
        </table>

  <%-- Inactive --%>
  
            <table cellpadding=0 cellspacing=0 summary=""> 
              <tr> 
                <td colspan="2" nowrap class="subhead">Inactive Entities</td> 
                <td nowrap class="subhead">Relationship to Entity</td> 
                <td nowrap class="subhead">Last Update</td> 
                <td nowrap class="subhead"><div align="center">Actions</div></td> 
              </tr> 
              <input type="hidden" name="editIndex" id="editIndex" value="${KualiForm.financialEntityHelper.editEntityIndex}" />
            <c:forEach var="financialEntity" items="${KualiForm.financialEntityHelper.inactiveFinancialEntities}" varStatus="status">
              <tr> 
                <td nowrap class="tab-subhead1"><a href="#" id="A${status.index}i" onclick="rend(this, false)"><img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-show.gif" alt="show/hide this panel" width=45 height=15 border=0 align=absmiddle id="F${status.index}i"></a></td> 
                    <td align="left" valign="middle" class="tab-subhead1">
                        <div align="left"> ${financialEntity.entityName} </div>
                    </td>
                    <td align="left" valign="middle" class="tab-subhead1">
                        <div align="left"> ${financialEntity.relationshipDescription} </div>
                    </td>
                    <td align="left" valign="middle" class="tab-subhead1">
                     <kul:htmlControlAttribute property="financialEntityHelper.inactiveFinancialEntities[${status.index}].updateTimestamp" 
                                                          attributeEntry="${attributes.updateTimestamp}"
                                                          readOnly="true" />
                    </td>
                        <td class="tab-subhead1">
                            <div align=center>&nbsp;                    
                                <html:image property="methodToCall.activateFinancialEntity.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-activate.gif' styleClass="tinybutton"/>
                            </div>
                        </td>
              </tr> 
              <tbody id="G${status.index}i" style="display: none;"> 
              
                 <tr> 
                  <td align=left  style="padding:0px; border:none">&nbsp;</td> 
                  <td colspan="4" align=left  style="padding:0px; border:none"><table cellpadding=0 cellspacing=0 summary="" style=" width:100%"> 
                      <tbody> 
                        <tr> 
                          <td colspan="5" align=left ><strong>History of Changes</strong></td> 
                        </tr> 
                        <tr> 
                          <th align=left>Questionnaire Name</th> 
                          <th align=left>Questionnaire Description</th> 
                          <th align=left>Last Update</th> 
                          <th align=left>Entity Disclosed Location</th> 
                          <th align=left>Actions</th> 
                        </tr> 
                      </tbody> 
                    </table></td> 
                </tr>
                
                 
              </tbody> 
              
           </c:forEach>   
        </table>
  
    </div>

</kul:tab>