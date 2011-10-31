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
<%@ attribute name="idx" required="true" description="Coi disl project list index" %>
<%@ attribute name="disclProject" required="true" type="org.kuali.kra.coi.disclosure.CoiDisclEventProject" %>

<c:set var="coiDisclProjectAttributes" value="${DataDictionary.CoiDisclProject.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<%-- c:set var="readOnly" value="${!KualiForm.personnelHelper.modifyPersonnel}" / --%>
<table cellpadding=0 cellspacing=0 summary="">
    <tr>
        <td>
            <kul:innerTab tabTitle="Award - ${disclProject.eventProjectBo.awardNumber} : ${disclProject.eventProjectBo.title}" parentTab="Award" defaultOpen="false" tabErrorKey="disclosureHelper.newDisclosurePersonUnit.*,document.coiDisclosureList[0].disclosurePersons[0]*" useCurrentTabIndexAsKey="false">
                <div class="innerTab-container" align="left">
                    <table class=tab cellpadding="0" cellspacing="0" summary="">
                        <tbody>
                        <%-- Header --%>
                                 <tr>
                                    <th><div align="center">Award Number</div></th> 
                                    <th><div align="center"></div>Award Title</th> 
                                    <th><div align="center"></div>Award Date</th> 
                                    <th><div align="center">PI</div></th> 
                                </tr>
                        <%-- Header --%>
                        
                         <%-- New data --%>
                        <%-- kra:permission value="${KualiForm.disclosureHelper.modifyPersonnel}" --%>
                <tr>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclEventProjects[${idx}].disclosureFlag" attributeEntry="${coiDisclProjectAttributes.disclosureFlag}" readOnly="${readOnly}" styleClass="selectDisclClass${idx}" /> 
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclEventProjects[${idx}].eventProjectBo.awardNumber" readOnly="true" attributeEntry="${coiDisclProjectAttributes.coiProjectId}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclEventProjects[${idx}].eventProjectBo.title" readOnly="true" attributeEntry="${coiDisclProjectAttributes.coiProjectTitle}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
					    <%-- TODO : not sure what award date is; so use award effectivedate for now --%>
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclEventProjects[${idx}].eventProjectBo.awardEffectiveDate" readOnly="true" attributeEntry="${coiDisclProjectAttributes.coiProjectStartDate}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
				         ${KualiForm.document.coiDisclosureList[0].disclosurePersons[0].reporter.fullName}
					</div>
				  </td>
	            </tr>

                <tr>
                    <td colspan="4">
       <div id="div_FinancialEntity${idx}" class="div_FinancialEntity" style="display:none;">
                <h3>
    		        <span class="subhead-left">Financial Entity</span>
    		        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
                </h3>


             <table id="protocol-table" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
          		<kul:htmlAttributeHeaderCell attributeEntry="${coiDiscDetailAttributes.personFinIntDisclosureId}" scope="col" />
          		<th rowspan="1" colspan="1" scope="col">${KualiForm.disclosureHelper.conflictHeaderLabel}</th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${coiDiscDetailAttributes.comments}" scope="col" />
          		<c:if test="${!readOnly}">
          		    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
          		</c:if>
          	</tr> 
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
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-all.gif' styleClass="conflict" onclick="$j('select.conflictClass${idx}').val('2');return false;" />
							<html:image property="methodToCall.noneConflict.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-none.gif' styleClass="conflict" onclick="$j('select.conflictClass${idx}').val('1');return false;" />
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
            

        	<c:forEach var="disclosureDetail" items="${disclProject.coiDiscDetails}" varStatus="festatus">
	             <tr>
					<th class="infoline">
						<c:out value="${festatus.index+1}" />
					</th>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclEventProjects[${idx}].coiDiscDetails[${festatus.index}].personFinIntDisclosure.entityName" readOnly="true" attributeEntry="${financialEntityAttributes.entityName}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclEventProjects[${idx}].coiDiscDetails[${festatus.index}].entityStatusCode" 
                			readOnly="${readOnly}" attributeEntry="${coiDiscDetailAttributes.entityStatusCode}" styleClass="conflictClass${idx}" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclEventProjects[${idx}].coiDiscDetails[${festatus.index}].comments" 
                			readOnly="${readOnly}" attributeEntry="${coiDiscDetailAttributes.comments}" />
					</div>
				  </td>
					  <td>
						<div align=center>&nbsp;
							<html:image property="methodToCall.viewFinancialEntity.line${festatus.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"/>
							<c:if test="${KualiForm.disclosureHelper.canEditDisclosureFinancialEntity}">		
							    <html:image property="methodToCall.editFinancialEntity.line${festatus.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton"/>
                    	     </c:if>
							<c:if test="${KualiForm.disclosureHelper.canViewDisclosureFeHistory}">		
						        <a class="disclosureFeHistory" id="history${festatus.index}" title="${disclosureDetail.personFinIntDisclosure.entityName} History" href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=showFinancialEntityHistory&status=activecoi&index=${disclosureDetail.personFinIntDisclosure.personFinIntDisclosureId}">
							        <html:image property="methodToCall.historyFinancialEntity.line${festatus.index}.anchor${currentTabIndex}"
									    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-history.gif' styleClass="tinybutton"/>
                    	         </a>
                    	     </c:if>
						</div>
		              </td>
	            </tr>
	            </c:forEach>
	            </table> <%-- fe table --%>
	            </div>
		              </td>
	            </tr>
	            
                        </tbody>
                    </table> <%-- protocol table --%>
                </div>
            </kul:innerTab>
        </td>
    </tr>
</table>


