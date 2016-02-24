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

<%@ attribute name="researchAreaReference" required="true"%>

<c:set var="action" value="committeeCommittee" />
<c:set var="readOnly" value="${!KualiForm.committeeHelper.modifyCommittee}" />

<kul:tab tabTitle="Area of Research" defaultOpen="true" tabErrorKey="document.committeeList[0].committeeResearchAreas.inactive.*,committeeResearchAreas*">
	<div class="tab-container" align="center" id="researchAreaDiv">
    	<h3>
    		<span class="subhead-left">Area of Research</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.committee.bo.CommitteeResearchArea" altText="help"/></span>
        </h3>
        
        <table id="researchAreaTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center">Code/Description</div></th>
              	<c:if test="${!readOnly}"> 
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    </c:if>
          	</tr>     
          		
          	<c:if test="${!readOnly}"> 
	            <tr>
		            <th width="10%" class="infoline">add:</th>
		            <td width="70%" class="infoline">
		                (select)&nbsp;<kul:multipleValueLookup boClassName="${researchAreaReference}" 
	              		lookedUpCollectionName="committeeResearchAreas"
	              		anchor="${tabKey}"/>
				    </td>
		
		            <td class="infoline" />
	            </tr>
            </c:if>
            
            <%-- Set the initial value of the error key prefix that is built up in the following loop based on testing for indices --%>
			<c:set var="inactiveAreasErrorKeyPrefix" value="document.committeeList[0].committeeResearchAreas.inactive." scope="request"/>
        	<c:forEach var="researchArea" items="${KualiForm.document.committee.committeeResearchAreas}" varStatus="status">
	             <tr>
	                <th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
		            <td align="left" valign="middle">
		               	${researchArea.researchArea.researchAreaCode}&nbsp;${researchArea.researchArea.description}
		               	<!--- error handling --> 
		               	<%-- Check if the research area indexed by the current iteration is an error key, and if so show the error icon --%>
						<kul:checkErrors keyMatch="${inactiveAreasErrorKeyPrefix}${status.index}.*" />
						<c:if test="${hasErrors}">
							<%-- display the error icon --%>
							<kul:fieldShowErrorIcon />
							<%-- build up the error key prefix by appending the current index --%>
							<c:set var="inactiveAreasErrorKeyPrefix" value="${inactiveAreasErrorKeyPrefix}${status.index}." scope="request" />
						</c:if>
					</td>
					<c:if test="${!readOnly}">
	                    <td>
						    <div align=center>&nbsp;
								<html:image property="methodToCall.deleteResearchArea.line${status.index}.anchor${currentTabIndex}"
									  src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						    </div>
	                    </td>
	                </c:if>
	            </tr>
	            
            </tr>
        	</c:forEach>
        </table>
    </div> 
</kul:tab>


