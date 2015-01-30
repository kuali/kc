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
<c:set var="masterDisclosure" value="${KualiForm.disclosureHelper.masterDisclosureBean}" />
<c:set var="disclosedProjectsPresent" value="${KualiForm.disclosureHelper.disclosedProjectsPresent}" />

<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<c:set var="projectsGroupedByDescription" value="(Grouped by Projects)" />

<c:set var="disclosureGroupedByEvent" value="${KualiForm.disclosureHelper.disclosureGroupedByEvent}" />
<c:set var="entitiesPresent" value="${KualiForm.disclosureHelper.financialEntitiesPresent}" />

<c:if test="${!disclosureGroupedByEvent}">
	<c:set var="projectsGroupedByDescription" value="(Grouped by Financial Entities)" />
</c:if>

	<div class="tab-container" align="center">
	    <h3>
	        <span class="subhead-left">Financial Entity Status Review ${projectsGroupedByDescription}</span>
	        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.coi.CoiDisclosureStatus" altText="help"/> </span>
				<c:if test="${disclosedProjectsPresent}">
			        <span class="subhead-right">
					    <html:image property="methodToCall.viewUndisclosedProjectsByEvent" title="Group by projects"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-projectview.gif' styleClass="tinybutton"/>
			        </span>
					<c:if test="${entitiesPresent}">
				        <span class="subhead-right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				        <span class="subhead-right">
						    <html:image property="methodToCall.viewUndisclosedProjectsByFinancialEntity" title="Group by financial entity"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-financialentityview.gif' styleClass="tinybutton"/>
				        </span>
					</c:if>
				</c:if>
	    </h3>
	    
	    <table id="protocolSubmitted-wrap-table" cellpadding=0 cellspacing=0 class="datatable" summary="Protocol Submitted">
	      	<%-- Header --%>
	       	<tr>
	            <th width="4%" />
				<c:choose>
				    <c:when test="${disclosureGroupedByEvent}">
					    <th><div align="center">Event</div></th> 
				        <th><div align="center">Project Id </div></th> 
				        <th><div align="center">Project Title</div></th> 
				        <th><div align="center">Disposition Status</div></th> 
				        <th><div align="center">Disclosure Status </div></th> 
				    </c:when>
				    <c:otherwise>
					    <th><div align="center">Entity Name</div></th> 
				        <th><div align="center">Entity Number</div></th> 
				    </c:otherwise>
				</c:choose>
			</tr>
			<%-- Header --%>
	
			<%-- Existing data --%>
	    	<c:set var="idx" value="1"/>
	        <c:forEach var="groupedMasterBean" items="${KualiForm.disclosureHelper.allDisclosuresGroupedByProjects}" varStatus="status">
		        <tr>
		            <th class="infoline" align="center">
			            <c:out value="${idx}" />
	    	            <c:set var="idx" value="${idx+1}"/>
		            </th>
					<c:choose>
					    <c:when test="${disclosureGroupedByEvent}">
				            <td align="left" valign="middle">
			                    <div align="left">
			                    <a href="#" id ="projectDiv-Control${idx}" class="projectsFESubpanel"><img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> 
				                ${groupedMasterBean.disclosureEventType} 
				                </div>
			    	        </td>
			    	        <td align="left" valign="middle">
				                <div align="left"> ${groupedMasterBean.projectId} </div>
				            </td>
				            <td align="left" valign="middle">
				                <div align="left"> ${groupedMasterBean.projectTitle} </div>
				            </td>
				            <td align="left" valign="middle">
				                <div align="left"> ${groupedMasterBean.allRelatedProjects[0].coiDisclProject.coiDispositionStatus.description}</div>
				            </td>
				            <td align="left" valign="middle">
				                <div align="left"> ${groupedMasterBean.disclosureStatus} </div>
			    	        </td>
					    </c:when>
					    <c:otherwise>
				            <td align="left" valign="middle">
			                    <div align="left">
			                    <a href="#" id ="projectDiv-Control${idx}" class="projectsFESubpanel"><img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> 
				                ${groupedMasterBean.entityName} 
				                </div>
			    	        </td>
			    	        <td align="left" valign="middle">
				                <div align="left"> ${groupedMasterBean.entityNumber} </div>
				            </td>
					    </c:otherwise>
					</c:choose>
	    	    </tr>
	    	    
	    	    <tr>
	    	    <td colspan="8">
	                <div  id="projectDiv-Details${idx}">
	                <div  id="projectDiv-Content${idx}">
				        <kra-coi:coiProjectEntities masterDisclosureProjects="${groupedMasterBean.allRelatedProjects}" 
				        projectDivNamePrefix="masterProjectDivFE${status.index}" projectListName="allDisclosuresGroupedByProjects[${status.index}].allRelatedProjects" 
				        boLocation="disclosureHelper.allDisclosuresGroupedByProjects[${status.index}].allRelatedProjects"
				        parentTab="Disclosed Projects" disclosureGroupedByEvent="${disclosureGroupedByEvent}"
				        groupedEntityNumber="${groupedMasterBean.entityNumber}"/>
			    	</div>
			    	</div>
	    	    </td>
          		</tr>
	    	    
	        </c:forEach>
		<%-- Existing data --%>
	    </table>
	</div>


