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
		                (select)&nbsp;<kul:multipleValueLookup boClassName="org.kuali.kra.bo.ResearchArea" 
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


