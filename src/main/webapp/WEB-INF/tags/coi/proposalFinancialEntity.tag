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
<%@ attribute name="disclProject" required="true" type="org.kuali.kra.coi.CoiDisclProject" %>

<c:set var="coiDisclProjectAttributes" value="${DataDictionary.CoiDisclProject.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<c:set var="readOnly" value="${!KualiForm.disclosureHelper.canEditDisclosureFinancialEntity}" />
                <c:choose>
                    <c:when test="${disclProject.proposalEvent}">
                        <kra-coi:manualProposalHeader disclProject="${disclProject}" idx = "${idx}"/>                    
                    </c:when>
                    <c:when test="${disclProject.awardEvent}">
                        <kra-coi:manualAwardHeader disclProject="${disclProject}" idx = "${idx}"/>                    
                    </c:when>
                    <c:otherwise>
                        <kra-coi:manualProtocolHeader disclProject="${disclProject}" idx = "${idx}"/>                    
                    </c:otherwise>
                
                </c:choose>
       <div id="div_FinancialEntity${idx}M" class="div_FinancialEntity" >
                <h3>
    		        <span class="subhead-left">
                  <c:if test="${not disclProject.complete}">
                    <img src="${ConfigProperties.kra.externalizable.images.url}exclamation.png" style="border:none; width:16px; height:16px; vertical-align:middle;" label="Incomplete Project">
                  </c:if>
    		        Financial Entities (${disclProject.completeMessage})</span>
    		        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
                </h3>


             <table id="newproposal-table" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="Review" scope="col" /> 
          		<kul:htmlAttributeHeaderCell attributeEntry="${coiDiscDetailAttributes.personFinIntDisclosureId}" scope="col" />
          		<th rowspan="1" colspan="1" scope="col">${KualiForm.disclosureHelper.conflictHeaderLabel}</th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${coiDiscDetailAttributes.comments}" scope="col" />
          	</tr> 
			   <c:if test="${!readOnly}">
	             <tr>
					<th class="infoline">
						&nbsp;
					</th>
					<td class="infoline">
						<div align="center">
							<html:image property="methodToCall.newFinancialEntity.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-newfinancialentity.gif' styleClass="tinybutton"/>
						</div>
	                </td>
	
	                <td align="left" valign="middle"  class="infoline">
						<div align="center">
							<html:image property="methodToCall.allConflict.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-all.gif' styleClass="conflict" onclick="$j('select.conflictClass${idx}M').val('2');return false;" />
							<html:image property="methodToCall.noneConflict.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-none.gif' styleClass="conflict" onclick="$j('select.conflictClass${idx}M').val('1');return false;" />
						</div>
	                </td>
	                <td align="left" valign="middle" class="infoline">
	                	&nbsp;
					</td>
	            </tr>
				</c:if>
            
        	<c:forEach var="disclosureDetail" items="${disclProject.coiDiscDetails}" varStatus="festatus">
	             <tr>
					  <td>
						<div align=center>&nbsp;
							<c:if test="${KualiForm.disclosureHelper.canViewDisclosureFeHistory}">		
                                <a class="disclosureFeView" id="viewEntitySummary${festatus.index}" title="${disclosureDetail.personFinIntDisclosure.entityName} Summary" href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=viewFinancialEntity&status=activecoi&index=${disclosureDetail.personFinIntDisclosure.personFinIntDisclosureId}" scrolling="no" noresize>
						 	        <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton" title="View Entity"/>
                    	        </a>   
                    	    </c:if>         
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
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${idx}].coiDiscDetails[${festatus.index}].personFinIntDisclosure.entityName" readOnly="true" attributeEntry="${financialEntityAttributes.entityName}" /> 
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${idx}].coiDiscDetails[${festatus.index}].entityStatusCode" 
                			readOnly="${readOnly}" attributeEntry="${coiDiscDetailAttributes.entityStatusCode}" styleClass="conflictClass${idx}M" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
                		<kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclProjects[${idx}].coiDiscDetails[${festatus.index}].comments" 
                			readOnly="${readOnly}" attributeEntry="${coiDiscDetailAttributes.comments}" />
					</div>
				  </td>
	            </tr>
	            </c:forEach>
	            </table> <%-- fe table --%>
	            </div>
                


