<%--
 Copyright 2005-2013 The Kuali Foundation
 
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

<c:set var="protocolStudyGroups" value="${KualiForm.document.protocol.iacucProtocolStudyGroupBeans}" />


<kra-iacuc:protocolProcedureCategories businessObjectClassName="org.kuali.kra.iacuc.IacucProtocol"
		                            collectionReference="${protocolStudyGroups}"
		                            collectionProperty="document.protocolList[0].iacucProtocolStudyGroupBeans"/>
