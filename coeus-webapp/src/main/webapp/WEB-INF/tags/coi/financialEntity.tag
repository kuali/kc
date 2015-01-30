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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="financialEntityList" required="true" type="java.util.List" description="A List of active or inactive FE" %>
<%@ attribute name="activeFlag" required="true" description="whether this is for active FE or inactive FE" %>

<c:set var="personFinIntDisclAttribute" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<c:set var="entityContactInfoAttribute" value="${DataDictionary.FinancialEntityContactInfo.attributes}" />
    <c:choose>
        <c:when test="${activeFlag == 'active'}">
            <c:set var="idsufix" value="" />                                           
            <c:set var="editmethod" value="editActiveFinancialEntity" />                                           
            <c:set var="activemethod" value="inactivateFinancialEntity" />                                           
            <c:set var="activeimage" value="tinybutton-deactivate.gif" />                                           
            <c:set var="activetitle" value="Deactivate" />                      
            <c:set var="prop" value="financialEntityHelper.activeFinancialEntities" />
            <c:set var="errorkey" value="financialEntityHelper.activeFinancialEntities*" />                                            
        </c:when>
        <c:otherwise>
            <c:set var="idsufix" value="i" />                                           
            <c:set var="editmethod" value="editInactiveFinancialEntity" />                                           
            <c:set var="activemethod" value="activateFinancialEntity" />                                           
            <c:set var="activeimage" value="tinybutton-activate.gif" />                                           
            <c:set var="activetitle" value="Activate" />          
            <c:set var="prop" value="financialEntityHelper.inactiveFinancialEntities" />
            <c:set var="errorkey" value="financialEntityHelper.inactiveFinancialEntities*" />                                           
        </c:otherwise>
    </c:choose>
    <c:forEach var="financialEntity" items="${financialEntityList}" varStatus="status">
        <tr id="hdrrow${status.index}${idsufix}"> 
            <td nowrap class="tab-subhead1">
            </td> 
            <td align="left" valign="middle" class="tab-subhead1">
                <div align="left"> ${financialEntity.entityName} </div>
            </td>
            <td align="left" valign="middle" class="tab-subhead1">
                <div align="left"> ${financialEntity.sponsorName} </div>
            </td>
            <td align="left" valign="middle" class="tab-subhead1">
                <fmt:formatDate value="${financialEntity.updateTimestamp}" pattern="MM/dd/yyyy hh:mm a" />
            </td>
            <!-- All the buttons required for viewing summary details and activating/deactivating -->
            <td class="tab-subhead1">
                    <div align=center>&nbsp;                               
                         <a class="iframe" id="viewEntitySummary" title="${financialEntity.entityName} Summary" href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=viewFinancialEntity&status=${activeFlag}&index=${status.index}" scrolling="no" noresize>
						 	<html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton" title="View Entity"/>
                    	 </a>            
                        <c:if test="${KualiForm.financialEntityHelper.editEntityIndex != status.index or KualiForm.financialEntityHelper.editType != activeFlag}">
                            <c:choose>
                                 <c:when test="${financialEntity.processStatus == 'F'}">
                            <html:image property="methodToCall.${editmethod}.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton" title="Edit"/>
                                 </c:when>
                                 <c:otherwise>
                            <html:image property="methodToCall.${editmethod}.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-resumeedit.gif' styleClass="tinybutton" title="Resume Edit"/>
                                 </c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:if test="${financialEntity.processStatus == 'F'}">
                        <html:image property="methodToCall.${activemethod}.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}${activeimage}' styleClass="tinybutton" title="${activetitle}"/>
                         </c:if>    
						 <a class="iframe" id="history" title="${financialEntity.entityName} History" href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=showFinancialEntityHistory&status=${activeFlag}&index=${status.index}">
						 	<html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewhistory.gif' styleClass="tinybutton" title="View History"/>
                    	 </a>
					</div>
            </td>
        </tr> 
           <c:if test="${KualiForm.financialEntityHelper.editEntityIndex == status.index and KualiForm.financialEntityHelper.editType == activeFlag}">     
        	<tr>
            <td colspan="5">
                <table id="active-hist-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">                   
                        <tr> 
                            <td colspan="5">
                            	<kul:tab defaultOpen="true" tabTitle="Financial Entity Being Edited" transparentBackground="true" tabErrorKey="${errorkey}" >
                                	<div class="tab-container" align="center">
                                    	<h3>
	                                        <span class="subhead-left"> 
	                                            <a href="#" id ="financialEntityControl" class="financialEntitySubpanel"><img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> Financial Entity </span>
								            <span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="financialEntity1Help" altText="help"/></span>
                                    	</h3>
                                    	<div id="financialEntityContent" class="financialEntitySubpanelContent">                    

                                        	<table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
                                            	<tr>
	                                                <th align="right" valign="middle">
	                                                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityName}" />
	                                                </th>
	                                                <td align="left" valign="middle"  colspan="3">
	                                                    <kul:htmlControlAttribute property="${prop}[${status.index}].entityName" 
	                                              attributeEntry="${personFinIntDisclAttribute.entityName}" /> 
	                                                </td>
	                                                <th align="right" valign="middle" >
	                                                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityTypeCode}" />
	                                                </th>
	                                                <td align="left" valign="middle"  colspan="3">
	                                                        <kul:htmlControlAttribute property="${prop}[${status.index}].entityTypeCode" 
	                                              attributeEntry="${personFinIntDisclAttribute.entityTypeCode}" />                   
	                                        
	                                                </td>
                                            	</tr>
                                            	<tr>
	                                                <th align="right" valign="middle">
	                                                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.sponsorCode}" readOnly="true" />
	                                                </th>
	                                                 <td align="left" valign="middle" >
	                                                    <kul:htmlControlAttribute property="${prop}[${status.index}].sponsorCode" attributeEntry="${personFinIntDisclAttribute.sponsorCode}" onblur="loadSponsor('${prop}[${status.index}].sponsorCode', 'sponsorName${status.index}', '${prop}[${status.index}].entityName', 'financialEntityHelper.prevSponsorCode');false" />
	                                                    <kul:lookup boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" fieldConversions="sponsorCode:${prop}[${status.index}].sponsorCode,sponsorName:${prop}[${status.index}].sponsor.sponsorName" anchor="${tabKey}" />
	                                                    <kul:directInquiry boClassName="org.kuali.coeus.common.framework.sponsor.Sponsor" inquiryParameters="${prop}[${status.index}].sponsorCode:sponsorCode" anchor="${tabKey}" />
	                                                    <div id="messageBox${status.index}" style="display:none;">
	                                                     </div>
	                                                    <input type="hidden" name="financialEntityHelper.editRolodexId" value="${KualiForm.financialEntityHelper.editRolodexId}" />
	                                                    <input type="hidden" name="financialEntityHelper.prevSponsorCode" value="${KualiForm.financialEntityHelper.prevSponsorCode}"/>
	                                                    <div id="sponsorName${status.index}.div" >
	                                                        <c:if test="${!empty financialEntity.sponsorCode}">
	                                                            <c:choose>
	                                                                <c:when test="${empty financialEntity.sponsor}">
	                                                                    <span style='color: red;'>not found</span>
	                                                                </c:when>
	                                                                <c:otherwise>
	                                                                        <c:out value="${financialEntity.sponsor.sponsorName}" />
	                                                                </c:otherwise>  
	                                                            </c:choose>                        
	                                                        </c:if>
	                                                    </div>
	                                                </td>
	                                                <th align="right" valign="middle" >
	                                                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.relatedToOrganizationFlag}" readOnly="true" />
	                                                </th>
	                                                <td align="left" valign="middle">
	                                                    <kul:htmlControlAttribute property="${prop}[${status.index}].relatedToOrganizationFlag" 
	                                              attributeEntry="${personFinIntDisclAttribute.relatedToOrganizationFlag}" /> 
	                                                </td>
	                                                <th align="right" valign="middle" >
	                                                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.statusCode}" />
	                                                </th>
	                                                <td align="left" valign="middle">
	                                                        ${financialEntity.finIntEntityStatus.description}
	                                                  
	                                                <th align="right" valign="middle" >
	                                                    <kul:htmlAttributeLabel attributeEntry="${personFinIntDisclAttribute.entityOwnershipType}" />
	                                                </th>
	                                                <td align="left" valign="middle">
	                                                    <kul:htmlControlAttribute property="${prop}[${status.index}].entityOwnershipType" 
	                                              attributeEntry="${personFinIntDisclAttribute.entityOwnershipType}" /> 
	                                                </td>
                                            	</tr>            
	                                            <%-- contact info --%>
	                                            <kra-coi:financialEntityContactInfo  prop="${prop}[${status.index}]" />
                                        	</table>
                                    	</div>                                            
                                	</div>
                                	<kra-coi:financialEntityRelationshipDetails prop="financialEntityHelper.editRelationDetails" detailList="${KualiForm.financialEntityHelper.editRelationDetails}"/>
                                </kul:tab>
                                <kra-coi:financialEntityAttachments/>
                                <!-- Tabbed Panel Footer -->    
                                <div class="tab-container" align="center" id="G125" style="display: none;"></div>
                                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
                                    <tr>
                                        <td align="left" class="footer"><img src="static/images/pixel_clear.gif" alt="" width="12" height="14" class="bl3"></td>
                                        <td align="right" class="footer-right"><img src="static/images/pixel_clear.gif" alt="" width="12" height="14" class="br3"></td>
                                    </tr>
                                </table>                               
                            </td>
                        </tr> 
                </table>                    
          </td>
       </tr>
       </c:if>
    </c:forEach>   
