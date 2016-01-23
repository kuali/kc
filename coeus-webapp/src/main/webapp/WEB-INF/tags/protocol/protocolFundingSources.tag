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

<%@ attribute name="protocolFundingSourceAttributes" required="true" type="java.util.Map" %>
<%@ attribute name="fundingSourceTypeAttributes" required="true" type="java.util.Map" %>
<%@ attribute name="protocolModule" required="false" %>

<c:set var="readOnly" value="${!KualiForm.protocolHelper.modifyFundingSource}" />
<c:set var="allowEditName" value="${KualiForm.protocolHelper.editProtocolFundingSourceName}" />
<c:set var="viewStyle" value="display: block;"/>
<c:set var="enableProtocolProposalDevelopmentLinking" value="${KualiForm.protocolHelper.protocolProposalDevelopmentLinkingEnabled}" />
<c:set var="canCreateProposal" value="false" />
<c:if test="${enableProtocolProposalDevelopmentLinking}">
	<c:set var="canCreateProposal" value="${KualiForm.protocolHelper.canCreateProposalDevelopment}" />
</c:if>

<c:choose>
    <c:when test="${KualiForm.protocolHelper.fundingNumberLookupable}">
        <c:set var="lookupStyle" value="display: inline" />
    </c:when>
    <c:otherwise>
        <c:set var="lookupStyle" value="display: none" />
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${KualiForm.protocolHelper.sourceNameEditable}">
        <c:set var="sourceNameEditStyle" value="display: inline" />
        <c:set var="sourceNameDisplayStyle" value="display: none" />
    </c:when>
    <c:otherwise>
        <c:set var="sourceNameEditStyle" value="display: none" />
        <c:set var="sourceNameDisplayStyle" value="display: inline" />
    </c:otherwise>
</c:choose>
<c:set var="buttonStyle" value="display:none"/>
<c:if test="${canCreateProposal && KualiForm.protocolHelper.newFundingSource.fundingSourceTypeCode == '4'}">
	<c:set var="buttonStyle" value="display:inline"/>
</c:if>

<kul:tab tabTitle="Funding Sources" defaultOpen="false" tabErrorKey="document.protocol.protocolFundingSource*,protocolHelper.newFundingSource*,protocolHelper.newFundingSource.fundingSourceTypeCode*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="document.title" useRiceAuditMode="true">
	<div class="tab-container" align="center">

    	<h3>
    		<span class="subhead-left">Funding Sources</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.iacuc.protocol.funding.IacucProtocolFundingSource" altText="help"/></span>
        </h3>
		
		
		<table id="funding-source-table" cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
                <kul:htmlAttributeHeaderCell attributeEntry="${protocolFundingSourceAttributes.fundingSourceTypeCode}" />
                <kul:htmlAttributeHeaderCell attributeEntry="${protocolFundingSourceAttributes.fundingSourceNumber}" forceRequired="true" />
                <kul:htmlAttributeHeaderCell attributeEntry="${protocolFundingSourceAttributes.fundingSourceName}" />
                <kul:htmlAttributeHeaderCell attributeEntry="${protocolFundingSourceAttributes.fundingSourceTitle}" />
                <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
            </tr>
            <c:if test="${!readOnly}">
            	<tbody class="addline">
	        	<tr>
	        	    <th class="infoline">
	        	       <c:out value="Add:" />
	                </th>  
	                               
	                <td>
	                    <div align="center">
	                        <%-- readOnly is passed as true to fix scope issues in htmlControlAttribute --%>
	                        <kul:htmlControlAttribute property="protocolHelper.newFundingSource.fundingSourceTypeCode" 
	                                                  attributeEntry="${protocolFundingSourceAttributes.fundingSourceTypeCode}" 
	                                                  readOnly="false"
	                                                  onchange="updateSourceNameEditable(
	                                                            'protocolHelper.newFundingSource.fundingSourceTypeCode', 
                                                                'protocolHelper.newFundingSource.fundingSourceNumber',
	                                                            'protocolHelper.newFundingSource.fundingSourceName', 
	                                                            'protocolHelper.newFundingSource.fundingSourceTitle',
	                                                            '${protocolModule}');" />
	                    </div>
	                </td> 
	
	                <td>
	                   <div align="center">
	                       <kul:htmlControlAttribute property="protocolHelper.newFundingSource.fundingSourceNumber" 
	                                                 attributeEntry="${protocolFundingSourceAttributes.fundingSourceNumber}"
													 readOnly="false"
	                                                 onblur="updateSourceNameEditable(
                                                                'protocolHelper.newFundingSource.fundingSourceTypeCode', 
                                                                'protocolHelper.newFundingSource.fundingSourceNumber',
                                                                'protocolHelper.newFundingSource.fundingSourceName', 
                                                                'protocolHelper.newFundingSource.fundingSourceTitle', 
                                                                '${protocolModule}');" />
	
                           <div id="protocolHelper.newFundingSource.fundingSourceNumber.lookup.div" style="${lookupStyle}">
		                       <kra-irb:fundingSourceLookup boClassName="${document.protocolList[0].newFundingSource.fundingSourceType.description}" 
		                                                    fieldConversions="" anchor="${currentTabIndex}"/>
		                   </div>
	                   </div>
	                </td>
	             
	                <td>
	                    <div align="center">
                            <div id="protocolHelper.newFundingSource.fundingSourceName.edit.div" style="${sourceNameEditStyle}">
                                <kul:htmlControlAttribute property="protocolHelper.newFundingSource.fundingSourceName" 
                                                          attributeEntry="${protocolFundingSourceAttributes.fundingSourceName}" 
                                                          readOnly="false" />
                            </div>
                            <div id="protocolHelper.newFundingSource.fundingSourceName.display.div" style="${sourceNameDisplaySyle}">
                                <c:out value="${KualiForm.protocolHelper.newFundingSource.fundingSourceName}" />
                            </div>
                            
                            <div id="protocolHelper.newFundingSource.fundingSourceName.error.div" style="display: none">
                                <span style="color: red;">not found</span>
                            </div>
                        </div>
	                </td>
	                
	                <td>
		                <div align="center">
		                	${kfunc:registerEditableProperty(KualiForm, "protocolHelper.newFundingSource.fundingSourceTitle")}                                    
	                        <input type="hidden" name="protocolHelper.newFundingSource.fundingSourceTitle" value=""/>
	                        <c:out value="${KualiForm.protocolHelper.newFundingSource.fundingSourceTitle}" />
	                    </div>
	                </td>
	
	                <td class="infoline">
	                        <div align="center">
	                            <html:image property="methodToCall.addProtocolFundingSource.anchor${tabKey}"
	                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
	                            title="Add protocol funding source"
	                            styleClass="tinybutton addButton"/>
					            <c:if test="${canCreateProposal}">
       								<div id="protocolHelper.newFundingSource.fundingSourceTypeCode.startproposal.image.div" style="${buttonStyle}">
			                            <html:image property="methodToCall.createProposalDevelopment.anchor${tabKey}"
			                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-startproposal.gif' 
		    	                        title="Create Proposal Development"
		        	                    styleClass="tinybutton"/>
	        	                    </div>
	        	                </c:if>
	                        </div>
	                </td>
	            </tr>
	            </tbody>
            </c:if>
            
            <%-- Existing data --%>
            <c:forEach var="protocolFundingSource" items="${KualiForm.document.protocolList[0].protocolFundingSources}" varStatus="status">
                <tr valign="middle">
                    <th class="infoline">
                        <c:out value="${status.index+1}" />
                    </th>
                    <td align="left">
                        <div style="${viewStyle}">
                            ${protocolFundingSource.fundingSourceType.description}  
                        </div>
                    </td>
                    <td align="left">
                        <div style="${viewStyle}">
                            ${protocolFundingSource.fundingSourceNumber}  
                        </div>
                    </td>
                    <td align="left">
                        <div style="${viewStyle}">
                            ${protocolFundingSource.fundingSourceName}  
                        </div>
                    </td>
                    <td align="left">
                        <div style="${viewStyle}">
                            ${protocolFundingSource.fundingSourceTitle}  
                        </div>
                    </td>
                    <td>
                        <div align=center>&nbsp;
                            <c:if test="${!readOnly}">
                                <html:image property="methodToCall.deleteProtocolFundingSource.line${status.index}.anchor${currentTabIndex}"
                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                            </c:if>
                            <c:if test="${(protocolFundingSource.fundingSourceLookupable)}">
                                <html:image property="methodToCall.viewProtocolFundingSource.line${status.index}.anchor${currentTabIndex}"
                                    alt="view funding source"
                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
                                    onclick="javascript: protocolFundingSourcePop( '${name}', ${KualiForm.formKey}, ${KualiForm.document.sessionDocument}, ${status.index}, ${currentTabIndex}, '${protocolModule}');return false"/>         
                            </c:if> 
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
	</div>	
</kul:tab>		
