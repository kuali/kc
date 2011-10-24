 <%--
 Copyright 2005-2010 The Kuali Foundation

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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<c:set var="financialEntityAttributes" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<kul:tab defaultOpen="false" tabTitle="Financial Entity Relationships" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="" useRiceAuditMode="true"
    tabErrorKey="document.coiDisclosureList[0].coiDiscDetails*" >
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Financial Entity</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
        </h3>
        
        <table id="location-table" cellpadding="0" cellspacing="0" summary="">
          	<%-- Header --%>
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
          		<kul:htmlAttributeHeaderCell attributeEntry="${coiDiscDetailAttributes.personFinIntDisclosureId}" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${coiDiscDetailAttributes.entityStatusCode}" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${coiDiscDetailAttributes.comments}" scope="col" />
          		<c:if test="${!readOnly}">
          		    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
          		</c:if>
          	</tr> 
          	<%-- Header --%>
          	
             <%-- New data --%>
        	<%-- <kra:permission value="${KualiForm.protocolHelper.modifyOrganizations}"> --%>
	             <tr>
					<th class="infoline">
						&nbsp;
					</th>
					<th class="infoline">
						&nbsp;
					</th>
	
	                <td align="left" valign="middle"  class="infoline">
						<div align="center">
							<html:image property="methodToCall.allConflict.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-all.gif' styleClass="conflict" onclick="$j('select[name$=entityStatusCode]').val('2');return false;" />
							<html:image property="methodToCall.noneConflict.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-none.gif' styleClass="conflict" onclick="$j('select[name$=entityStatusCode]').val('1');return false;" />
						</div>
	                </td>
	                <td align="left" valign="middle" class="infoline">
	                	&nbsp;
					</td>
					<td class="infoline">
						<div align="center">
							<html:image property="methodToCall.newFinancialEntity.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-newfinancialentity.gif' styleClass="tinybutton"/>
						</div>
	                </td>
	            </tr>
            <%-- </kra:permission> --%>
            <%-- New data --%>
            
            <%-- Existing data --%>
        	<c:forEach var="disclosureDetail" items="${KualiForm.document.coiDisclosureList[0].coiDiscDetails}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDiscDetails[${status.index}].personFinIntDisclosure.entityName" readOnly="true" attributeEntry="${financialEntityAttributes.entityName}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDiscDetails[${status.index}].entityStatusCode" 
                			readOnly="${readOnly}" attributeEntry="${coiDiscDetailAttributes.entityStatusCode}" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDiscDetails[${status.index}].comments" 
                			readOnly="${readOnly}" attributeEntry="${coiDiscDetailAttributes.comments}" />
					</div>
				  </td>
					  <td>
						<div align=center>&nbsp;
							<html:image property="methodToCall.viewFinancialEntity.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"/>
							<c:if test="${KualiForm.disclosureHelper.canEditDisclosureFinancialEntity}">		
							    <html:image property="methodToCall.editFinancialEntity.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton"/>
                    	     </c:if>
							<c:if test="${KualiForm.disclosureHelper.canViewDisclosureFeHistory}">		
						        <a class="disclosureFeHistory" id="history${status.index}" title="${disclosureDetail.personFinIntDisclosure.entityName} History" href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=showFinancialEntityHistory&status=activecoi&index=${disclosureDetail.personFinIntDisclosure.personFinIntDisclosureId}">
							        <html:image property="methodToCall.historyFinancialEntity.line${status.index}.anchor${currentTabIndex}"
									    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-history.gif' styleClass="tinybutton"/>
                    	         </a>
                    	     </c:if>
						</div>
		              </td>
	            </tr>
        	</c:forEach> 
            <%-- Existing data --%>
        </table>

    </div>
</kul:tab>

