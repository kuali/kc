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

<%@ attribute name="businessObjectClassName" required="true" 
              description="The specific per-module business class to use for the help pages" %>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.IacucProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.IacucProtocol.attributes}" />

<c:set var="readOnly" value="${!KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}" />


<kul:tab tabTitle="Overview and Timeline" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="document.protocolList[0].overviewTimeline">
    <div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Overview and Timeline</span>
    		<span class="subhead-right"><kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucProtocolOverviewAndTimelineHelp" altText="Help"/></span>
        </h3>
        
        <table id="protocolOverviewTimelineTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.overviewTimeline}" noColon="true" /></div></th>
          	</tr>     

			<%-- 
        	<kra:permission value="${KualiForm.iacucProtocolProceduresHelper.modifyProtocolProcedures}">            
	        </kra:permission>
	        --%>          
                <tr>
	                <c:set var="textAreaFieldName" value="document.protocolList[0].overviewTimeline" />
		            <td align="left" valign="middle" class="infoline">
		               	<div align="center">
		               		<kul:htmlControlAttribute property="document.protocolList[0].overviewTimeline" 
		               		                          attributeEntry="${protocolAttributes.overviewTimeline}" 
		               		                          readOnly="${readOnly}" />
		            	</div>
					</td>
	            </tr>
        </table>
    </div> 
</kul:tab>

