<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolFundingSourceAttributes" value="${DataDictionary.ProtocolFundingSource.attributes}" />
<c:set var="fundingSourceTypeAttributes" value="${DataDictionary.FundingSourceType.attributes}" />
<c:set var="readOnly" value="${!KualiForm.protocolHelper.modifyFundingSource}" />
<c:set var="allowEditName" value="${KualiForm.protocolHelper.editProtocolFundingSourceName}" />


<kul:tab tabTitle="Funding Sources" defaultOpen="false" tabErrorKey="document.protocol.protocolFundingSource*,protocolHelper.newFundingSource*,protocolHelper.newFundingSource.fundingSourceTypeCode*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="document.title" useRiceAuditMode="true">
	<div class="tab-container" align="center">

    	<h3>
    		<span class="subhead-left">Funding Sources</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.protocol.bo.ProtocolType" altText="help"/></span>
        </h3>
		
		
		<table cellpadding=0 cellspacing=0 summary="">
        	<tr>
                <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
                <kul:htmlAttributeHeaderCell attributeEntry="${protocolFundingSourceAttributes.fundingSourceTypeCode}" />
                <kul:htmlAttributeHeaderCell attributeEntry="${protocolFundingSourceAttributes.fundingSourceNumber}" />
                <kul:htmlAttributeHeaderCell attributeEntry="${protocolFundingSourceAttributes.fundingSourceName}" />
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
	                 
	                        <input type="hidden" name="protocolHelper.editProtocolFundingSourceName" 
	                               value="${editName}">            
	                    
	                        <kul:htmlControlAttribute property="protocolHelper.newFundingSource.fundingSourceTypeCode" 
	                                                  attributeEntry="${protocolFundingSourceAttributes.fundingSourceTypeCode}" 
	                                                  onchange="updateSourceNameEditable(
	                                                            'protocolHelper.newFundingSource.fundingSourceTypeCode', 
                                                                'protocolHelper.newFundingSource.fundingSource',
                                                                'protocolHelper.newFundingSource.fundingSourceNumber',
	                                                            'protocolHelper.newFundingSource.fundingSourceName', 
	                                                            'protocolHelper.newFundingSource.fundingSourceTitle');" />
	                    </div>
	                </td> 
	
	                <td>
	                   <div align="center">
	                       ${kfunc:registerEditableProperty(KualiForm, "protocolHelper.newFundingSource.fundingSource")}                                    
                           <input type="hidden" name="protocolHelper.newFundingSource.fundingSource" value=""/>   

	                       <kul:htmlControlAttribute property="protocolHelper.newFundingSource.fundingSourceNumber" 
	                                                 attributeEntry="${protocolFundingSourceAttributes.fundingSourceNumber}"
	                                                 onblur="updateSourceNameEditable(
                                                                'protocolHelper.newFundingSource.fundingSourceTypeCode', 
                                                                'protocolHelper.newFundingSource.fundingSource',
                                                                'protocolHelper.newFundingSource.fundingSourceNumber',
                                                                'protocolHelper.newFundingSource.fundingSourceName', 
                                                                'protocolHelper.newFundingSource.fundingSourceTitle');" />
	
                        
	                       <kra-irb:fundingSourceLookup boClassName="${document.protocolList[0].newFundingSource.fundingSourceType.description}" 
	                                                    fieldConversions="" anchor="${currentTabIndex}"/> 
	                   </div>
	                </td>
	             
	                <td>
	                    <div align="center">
	                        <kul:htmlControlAttribute property="protocolHelper.newFundingSource.fundingSourceName" 
                                                      attributeEntry="${protocolFundingSourceAttributes.fundingSourceName}" />
                            
                            <div id="protocolHelper.newFundingSource.fundingSourceName.div">
                                <c:if test="${!empty protocolHelper.newFundingSource.fundingSourceNumber} 
                                             && ${empty protocolHelper.newFundingSource.fundingSourceName}">
                                    <span style='color: red;'>not found</span><br>
                                </c:if>
                            </div>
                        </div>
	                </td>
	                
	                <td>
		                <div align="center">
		                   ${kfunc:registerEditableProperty(KualiForm, "protocolHelper.newFundingSource.fundingSourceTitle")}                                    
	                       <input type="hidden" name="protocolHelper.newFundingSource.fundingSourceTitle" value=""/>
		                   
		                   <div id="protocolHelper.newFundingSource.fundingSourceTitle.div">
			                   <c:choose>
			                      <c:when test="${!empty protocolHelper.newFundingSource.fundingSourceNumber} 
			                                 && ${empty protocolHelper.newFundingSource.fundingSourceTitle}">
			                          <span style='color: red;'>not found</span><br>
			                      </c:when>
			                      <c:otherwise>
			                          <c:out value="${KualiForm.protocolHelper.newFundingSource.fundingSourceTitle}" />
			                      </c:otherwise>
			                   </c:choose>
			               </div>
	                    </div>
	                </td>
	
	                <td class="infoline">
	                        <div align="center">
	                            <html:image property="methodToCall.addProtocolFundingSource.anchor${tabKey}"
	                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
	                            styleClass="tinybutton"/>
	                        </div>
	                </td>
	            </tr>
            </c:if>
            
            <%-- Existing data --%>
            <c:forEach var="protocolFundingSource" items="${KualiForm.document.protocolList[0].protocolFundingSources}" varStatus="status">
                 <tr>
                    <th class="infoline">
                        <c:out value="${status.index+1}" />
                    </th>
                  <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="document.protocolList[0].protocolFundingSources[${status.index}].fundingSourceType.description" 
                                                  readOnly="true" attributeEntry="${fundingSourceTypeAttributes.description}" /> 
                    </div>
                  </td>
                  <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="document.protocolList[0].protocolFundingSources[${status.index}].fundingSourceNumber" 
                                                  readOnly="true" attributeEntry="${protocolFundingSourceAttributes.fundingSourceNumber}" /> 
                    </div>                  
                  </td>
                  <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="document.protocolList[0].protocolFundingSources[${status.index}].fundingSourceName" 
                                                  readOnly="true" attributeEntry="${fundingSourceTypeAttributes.description}" />
                    </div>
                  </td>
                  <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="document.protocolList[0].protocolFundingSources[${status.index}].fundingSourceTitle" 
                                                  readOnly="true" attributeEntry="${fundingSourceTypeAttributes.description}" />
                    </div>
                  </td>
                  <td>
                    <div align=center>&nbsp;
                        <c:if test="${!readOnly}">
                            <html:image property="methodToCall.deleteProtocolFundingSource.line${status.index}.anchor${currentTabIndex}"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                        </c:if>
                        
                        <c:if test="${(protocolFundingSource.viewableFundingSource)}">
   
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