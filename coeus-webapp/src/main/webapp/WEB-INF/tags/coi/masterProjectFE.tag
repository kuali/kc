<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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
<%@ attribute name="disclProjectBean" required="true" type="org.kuali.kra.coi.disclosure.CoiDisclosureProjectBean" description="A List of active or inactive FE" %>
<%@ attribute name="projectDivNamePrefix" required="true" description="name for project related div" %>
<%@ attribute name="idx" required="true" description="detail index" %>
<%@ attribute name="projectListName" required="true" description="Project list name in master bean" %>
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<c:set var="financialEntityAttributes" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<c:set var="canEditDisclosure" value="${KualiForm.disclosureHelper.canEditDisclosureFinancialEntity}" />
<c:set var="isMasterDisclosure" value="${KualiForm.disclosureHelper.coiDisclosure.approvedDisclosure}" />
                                  
<%-- New data --%>
            
<%-- Existing data --%>

<div class="financialentity" <c:if test="${disclProjectBean.excludeFE}">style="display:none"</c:if>>
    <div class="h2-container" style="height:20px; vertical-align:middle; background-color:#999; border-top:none; border-bottom:none; color:#FFF; font-weight:bold;">
        <span class="subhead-left">
      	    &nbsp;
            <a href="#" id ="${projectDivNamePrefix}Control${idx}" class="financialEntitySubpanel"><img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> 
            Financial Entities
        </span>
    	<span style="float: right;text-align: right;"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
    </div>
    
    <div  id="${projectDivNamePrefix}Details${idx}" style="margin-bottom:15px;">
        <div  id="${projectDivNamePrefix}Content${idx}" class="financialEntitySubpanelContent" style="margin-bottom:15px;">
            <table class=tab cellpadding="0" cellspacing="0" summary="">
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
                        <c:if test="${not empty disclProjectBean.coiDisclProject.coiDiscDetails}">
						<select onchange="jQuery(this).parents('table').first().find('select.related').val(jQuery(this).val());">
							<c:forEach items="${krafn:getOptionList('org.kuali.kra.coi.disclosure.CoiDispositionStatusValuesFinder', null)}" var="option">
		                        <option value="${option.key}">${option.value}</option>
				            </c:forEach>
						</select>
						</c:if>
                        </div>
                    </td>
                    <td align="left" valign="middle" class="infoline">
                        &nbsp;
                    </td>
                </tr>
                </c:if>
        	    <c:forEach var="disclosureDetail" items="${disclProjectBean.coiDisclProject.coiDiscDetails}" varStatus="festatus">
                    <tr>
        	            <td>
        		            <div align=center>&nbsp;
        		                <c:if test="${KualiForm.disclosureHelper.canViewDisclosureFeHistory}">		
                                    <a class="disclosureFeView" id="viewEntitySummary${festatus.index}" title="${disclosureDetail.personFinIntDisclosure.entityName} Summary" href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=viewFinancialEntity&status=activecoi&index=${disclosureDetail.personFinIntDisclosure.personFinIntDisclosureId}" scrolling="no" noresize>
        					 	        <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton" title="View Entity"/>
                    	            </a>   
                    	        </c:if>         
        			            <c:if test="${!isMasterDisclosure && canEditDisclosure}">		
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
        		                ${disclosureDetail.personFinIntDisclosure.entityName}
        		            </div>
        		        </td>
                        <td align="left" valign="middle">
        		            <div align="left">
        		                <%-- ${disclosureDetail.coiEntityDispositionStatus.description} --%>
                                <kul:htmlControlAttribute property="disclosureHelper.masterDisclosureBean.${projectListName}[${idx}].coiDisclProject.coiDiscDetails[${festatus.index}].entityDispositionCode" readOnly="${readOnly}" attributeEntry="${coiDiscDetailAttributes.entityDispositionCode}" 
                                    readOnlyAlternateDisplay="${disclosureDetail.coiEntityDispositionStatus.description}" styleClass="related"/> 
        		            </div>
        		        </td>
                        <td align="left" valign="middle">
        		            <div align="left">
        		                <%-- ${disclosureDetail.comments} --%>
                                <kul:htmlControlAttribute property="disclosureHelper.masterDisclosureBean.${projectListName}[${idx}].coiDisclProject.coiDiscDetails[${festatus.index}].comments" readOnly="${readOnly}" attributeEntry="${coiDiscDetailAttributes.comments}" /> 
        		            </div>
        		        </td>
                    </tr>
                </c:forEach>
            </table>
        </div> 
    </div>
</div>
    
