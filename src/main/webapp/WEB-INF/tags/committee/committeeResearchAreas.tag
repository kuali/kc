<%--
 Copyright 2006-2008 The Kuali Foundation
 
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

<kul:tab tabTitle="Research Areas" defaultOpen="true" tabErrorKey="committeeResearchAreas*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Research Areas</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.ResearchArea" altText="help"/></span>
        </h3>
        
        <table id="researchAreaTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp</div></th> 
          		<th><div align="center">Code/Description</div></th>
              	<c:if test="${!readOnly}"> 
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    </c:if>
          	</tr>     
          		
          	<c:if test="${!readOnly}"> 
	            <tr>
		            <th width="10%" class="infoline">Add:</th>
		            <td width="70%" class="infoline">
		                <c:choose>
		                    <c:when test="${empty KualiForm.committeeHelper.newResearchArea.description}">
		                        (select)
		                    </c:when>
		                    <c:otherwise>
		                        <c:out value="${KualiForm.committeeHelper.newResearchArea.description}"/>
		              	    </c:otherwise>
		              	</c:choose>
		              	<kul:lookup boClassName="org.kuali.kra.bo.ResearchArea" 
	                         fieldConversions="researchAreaCode:committeeHelper.newResearchArea.researchAreaCode,description:committeeHelper.newResearchArea.description" />
					</td>
		
		            <td align="left" valign="middle" class="infoline">
						<div align="center">
						    <html:image property="methodToCall.addResearchArea.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
						</div>
	               </td>
	            </tr>
            </c:if>
            
        	<c:forEach var="researchArea" items="${KualiForm.document.committee.committeeResearchAreas}" varStatus="status">
	             <tr>
	                <th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
		            <td align="left" valign="middle">
		               ${researchArea.researchArea.description}
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


