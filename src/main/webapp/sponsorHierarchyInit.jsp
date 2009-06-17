 <%--
 Copyright 2006-2009 The Kuali Foundation

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

<kul:page lookup="true" 
          docTitle="Copy Sponsor Hierarchy" 
          transactionalDocument="false" 
          htmlFormAction="sponsorHierarchy">
          
    <script language="javascript" src="scripts/kuali_application.js"></script>
    <c:set var="newHierarchyNameStyle" value="" scope="request"/>
    
    <c:if test="${fn:length(ErrorPropertyList) > 0}">
    		<c:set var="newHierarchyNameStyle" value="border-color: red" scope="request"/>
    </c:if>
    <br/>
    <br/>
         <c:if test="${!empty  SponsorHierarchyForm.message}" >
     			    <strong><c:out value="${SponsorHierarchyForm.message}" /> </strong><br/>
        </c:if>
    
    <br/>
<div id="workarea">

    <kul:tab tabTitle="Sponsor Hierarchy" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="newHierarchyName*">
    
    <!-- 
        <div style="text-align:left;width: 98%;background-color: gray" > 
     	<c:if test="${!empty  SponsorHierarchyForm.message}" >
     			    <strong><c:out value="${SponsorHierarchyForm.message}" /> </strong><br/>
        </c:if>
        </div> 
     -->
    
		    <div class="tab-container" align="center">
		        <div class="h2-container">
		            <span class="subhead-left"><h2>Sponsor Hierarchy</h2></span>
		        </div>  
            <table  cellpadding="0" cellspacing="0" class="grid" summary="">
              <tr>
                <th class="grid"><div align="right">Sponsor Hierarchy:</div></th>
                <td nowrap class="info">
          		       <html:select property="selectedSponsorHierarchy" >
        					<c:forEach var="sponsorHierarchy" items="${SponsorHierarchyForm.hierarchyNameList}" varStatus="status">
								<c:choose>
									<c:when test="${sponsorHierarchy eq SponsorHierarchyForm.selectedSponsorHierarchy}">
 		                    				<option value="${sponsorHierarchy}" SELECTED>${sponsorHierarchy}</option>
									</c:when> 
									<c:otherwise>
 		                    				<option value="${sponsorHierarchy}">${sponsorHierarchy}</option>
									</c:otherwise>								
								</c:choose>
  		                    </c:forEach>
	  			        </html:select>
                </td>
                <th class="grid"><div align="right">New Sponsor Hierarchy Name:</div></th>
                <td class="info" >
                    <input id="newHierarchyName" type="text" name="newHierarchyName" value='${SponsorHierarchyForm.newHierarchyName}' style="${newHierarchyNameStyle}"/>
                
                </td>
              </tr>
            </table>
            <br>
            <html:image property="methodToCall.newSponsorHierarchy" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-createnew.gif" title="Add Sponsor Hierarchy" alt="Add Sponsor Hierarchy" styleClass="tinybutton"/>
            <html:image property="methodToCall.maintSponsorHierarchy" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-maint.gif" title="Maintain Sponsor Hierarchy" alt="Maintain Sponsor Hierarchy" styleClass="tinybutton"/>
            <html:image property="methodToCall.copySponsorHierarchy" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-copy2.gif" title="Copy Sponsor Hierarchy" alt="Copy Sponsor Hierarchy" styleClass="tinybutton"/>
            <html:image property="methodToCall.deleteSponsorHierarchy" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" title="Delete Sponsor Hierarchy" alt="Delete Sponsor Hierarchy" styleClass="tinybutton"/>
            <html:image property="methodToCall.cancelSponsorHierarchy" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" title="cancel" alt="cancel" styleClass="tinybutton"/>
          </div>
    </kul:tab>

<kul:panelFooter /> 

</kul:page>



