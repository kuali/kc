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
<%@ attribute name="disclosureGroupedByEvent" required="true" description="Boolean to check if project is grouped by event or entity" %>
<%@ attribute name="groupedEntityNumber" required="true" description="Entity number - valid only if grouped by entity" %>

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
	                <th width="5%"><div align="left">&nbsp;</div></th> 
	                <th width="15%"><div align="center">Active Entities</div></th>
	                <th><div align="center">Comments</div></th>
	                <th width="12%"><div align="center">Last Updated</div></th>
	                <th width="10%"><div align="center">Updated By</div></th>
	                <th width="12%"><div align="center">Recommended Status</div></th>
	            </tr>
				<c:set var="userIndex" value="1"/>
        	    <c:forEach var="disclosureDetail" items="${disclProjectBean.coiDisclProject.coiDiscDetails}" varStatus="festatus">
					<c:set var="entityNumber" value="${disclosureDetail.personFinIntDisclosure.entityNumber}"/>
	        	    <c:if test="${(disclosureGroupedByEvent)  || (groupedEntityNumber eq entityNumber)}">
	                    <tr>
	        				<td>
	        					${userIndex}
	        				</td>
			           		<td style="text-align: left;" valign="middle">
			           			${disclosureDetail.personFinIntDisclosure.entityName}
	           				</td>
			           		<td style="text-align: left;" valign="middle">
			           			${disclosureDetail.comments}
	           				</td>
			           		<td style="text-align: left;" valign="middle">
			           			${disclosureDetail.updateTimestamp}
	           				</td>
			           		<td style="text-align: left;" valign="middle">
			           			${disclosureDetail.updateUser}
	           				</td>
	            	 		<td style="text-align: left;" valign="middle">
	        	   				<kul:htmlControlAttribute property="disclosureHelper.${projectListName}[${idx}].coiDisclProject.coiDiscDetails[${festatus.index}].entityDispositionCode" 
	  								readOnly="${readOnly}" attributeEntry="${coiDiscDetailAttributes.entityDispositionCode}" styleClass="related" 
	  								readOnlyAlternateDisplay="${disclosureDetail.coiEntityDispositionStatus.description}"/>
							</td>
	                    </tr>
			        	<c:set var="userIndex" value="${userIndex+1}" />
    	    	    </c:if>
                </c:forEach>
            </table>
        </div> 
    </div>
</div>
    
