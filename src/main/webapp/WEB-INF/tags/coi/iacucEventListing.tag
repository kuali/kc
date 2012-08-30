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

<%@ attribute name="topOfStack" required="false" description="Indicates whether this is the top panel in a stacking, defaults to false" %>
<c:if test="${topOfStack == null}">
	<c:set var="topOfStack" value="false" />
</c:if>

<c:choose>
    <c:when test="${fn:length(KualiForm.disclosureHelper.newIacucProtocols) == 0}" >
        <c:set var="iacucProtocolNotFound" value=" (None found)" />
        <c:set var="iacucProtocolsTabHidden" value="true" />
        <c:set var="iacucProtocolsTabDefaultOpen" value="false" />
    </c:when>
    <c:otherwise>
        <c:set var="iacucProtocolNotFound" value="" />
        <c:set var="iacucProtocolsTabHidden" value="false" />
        <c:set var="iacucProtocolsTabDefaultOpen" value="true" />
    </c:otherwise>
</c:choose>

<kul:tab tabTitle="New IACUC Protocols for disclosure${iacucProtocolNotFound}"
         defaultOpen="${iacucProtocolsTabDefaultOpen}"
         transparentBackground="${topOfStack}"
         useCurrentTabIndexAsKey="true"
         tabErrorKey="notificationTemplates[*">
         
    <div class="tab-container" align="center" id="G100">
        <h3>
            <span class="subhead-left">IACUC Protocol List</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDisclosure" altText="help" /></span>
        </h3>
        
        <table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpAdding="0" cellspacing="0" width="50%" align="center" >
            <tr>
                <th><div align="center">Protocol Number</div></th> 
                <th><div align="center">Protocol Name</div></th> 
                <th><div align="center">Protocol Type</div></th> 
                <th><div align="center">Action</div></th> 
           </tr>
           <c:forEach items="${KualiForm.disclosureHelper.newIacucProtocols}" var="iacucProtocol" varStatus="status">
               <tr>
                   <td align="left" valign="middle">
					   <div align="left">
                		   ${iacucProtocol.protocolNumber}
					   </div>
				   </td>
                   <td align="left" valign="middle">
					   <div align="left">
                		   ${iacucProtocol.title}
					   </div>
				   </td>
                   <td align="left" valign="middle">
					   <div align="left">
                		   ${iacucProtocol.protocolType.description} 
				       </div>
				   </td>
                   <td>
                       <div align="center">
                           <a title="New IACUC Protocol Disclosure" href="coiDisclosure.do?methodToCall=newProjectDisclosure&disclosureHelper.eventTypeCode=4&disclosureHelper.newProjectId=${iacucProtocol.protocolId}&disclosureHelper.newModuleItemKey=${iacucProtocol.protocolNumber}&docTypeName=CoiDisclosureDocument">Report Coi</a>                        
                       </div>
                   </td>
               </tr>
            </c:forEach>
        </table>   
    </div> 
</kul:tab>
