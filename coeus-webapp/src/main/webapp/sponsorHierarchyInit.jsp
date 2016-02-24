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

<kul:page lookup="true" 
          docTitle="Copy Sponsor Hierarchy" 
          transactionalDocument="false" 
          htmlFormAction="sponsorHierarchy">

	<div align="left"><kul:help parameterNamespace="KC-M" parameterDetailType="Document" parameterName="sponsorHierarchyCreateNewHelp" altText="help"/>
          
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
            <html:image property="methodToCall.maintSponsorHierarchy" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-maintain.gif" title="Maintain Sponsor Hierarchy" alt="Maintain Sponsor Hierarchy" styleClass="tinybutton"/>
            <html:image property="methodToCall.copySponsorHierarchy" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-copy2.gif" title="Copy Sponsor Hierarchy" alt="Copy Sponsor Hierarchy" styleClass="tinybutton"/>
            <html:image property="methodToCall.deleteSponsorHierarchy" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" title="Delete Sponsor Hierarchy" alt="Delete Sponsor Hierarchy" styleClass="tinybutton"/>
            <html:image property="methodToCall.cancelSponsorHierarchy" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-cancel.gif" title="Cancel Sponsor Hierarchy" alt="Cancel Sponsor Hierarchy" styleClass="tinybutton"/>
          </div>
    </kul:tab>

<kul:panelFooter /> 

</kul:page>



