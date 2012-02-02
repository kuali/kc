<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolFundingSourceAttributes" value="${DataDictionary.ProtocolFundingSource.attributes}" />
<c:set var="fundingSourceTypeAttributes" value="${DataDictionary.FundingSourceType.attributes}" />
<c:set var="readOnly" value="${!KualiForm.protocolHelper.modifyFundingSource}" />
<c:set var="allowEditName" value="${KualiForm.protocolHelper.editProtocolFundingSourceName}" />
<c:set var="viewStyle" value="display: block;"/>

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

<kul:tab tabTitle="Funding Sources" defaultOpen="false" tabErrorKey="document.protocol.protocolFundingSource*,protocolHelper.newFundingSource*,protocolHelper.newFundingSource.fundingSourceTypeCode*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="document.title" useRiceAuditMode="true">
	<div class="tab-container" align="center">

    	<h3>
    		<span class="subhead-left">Funding Sources</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.protocol.funding.ProtocolFundingSource" altText="help"/></span>
        </h3>
		
		
		<table id="funding-source-table" cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
                <kul:htmlAttributeHeaderCell attributeEntry="${protocolFundingSourceAttributes.fundingSourceTypeCode}" />
                <kul:htmlAttributeHeaderCell attributeEntry="${protocolFundingSourceAttributes.fundingSourceNumber}" forceRequired="true" />
                <kul:htmlAttributeHeaderCell attributeEntry="${protocolFundingSourceAttributes.fundingSourceName}" forceRequired="true" />
                <kul:htmlAttributeHeaderCell attributeEntry="${protocolFundingSourceAttributes.fundingSourceTitle}" />
                <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
            </tr>
            <c:if test="${!readOnly}">
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
	                                                            'protocolHelper.newFundingSource.fundingSourceTitle');" />
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
                                                                'protocolHelper.newFundingSource.fundingSourceTitle');" />
	
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
	                            styleClass="tinybutton"/>
	                        </div>
	                </td>
	            </tr>
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
                                    onclick="javascript: protocolFundingSourcePop( '${name}', ${KualiForm.formKey}, ${KualiForm.document.sessionDocument}, ${status.index}, ${currentTabIndex});return false"/>         
                            </c:if> 
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
	</div>	
</kul:tab>		