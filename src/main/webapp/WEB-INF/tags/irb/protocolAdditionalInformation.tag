<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="protocolReferenceAttributes" value="${DataDictionary.ProtocolReference.attributes}" />
<c:set var="protocolReferenceTypeAttributes" value="${DataDictionary.ProtocolReferenceType.attributes}" />  
<c:set var="textAreaFieldName" value="document.protocolList[0].description" />
<c:set var="textAreaFieldName1" value="newProtocolReference.comments" />
<c:set var="action" value="protocolProtocol" />
<c:set var="className" value="org.kuali.kra.irb.document.ProtocolDocument" />

<c:set var="researchAreasAttributes" value="${DataDictionary.ResearchArea.attributes}" />
<c:set var="readOnly" value="${!KualiForm.protocolHelper.modifyProtocol}" />
<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.PROTOCOL_REFERENCE_COMMENT_LENGTH%>" />

<kul:tab tabTitle="Additional Information" defaultOpen="false" tabErrorKey="document.protocolList[0].fda*,document.protocolList[0].billable*,document.protocolList[0].referenceNumber*,document.protocolList[0].description*,document.protocolList[0].protocolReferences*,newProtocolReference*" auditCluster="" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Additional Information</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.bo.ProtocolType" altText="help"/></span>
        </h3>

			<table cellpadding=0 cellspacing=0 summary="">

              <tr>
                <th width="33%"><div align="right">
                    <kul:htmlAttributeLabel attributeEntry="${protocolAttributes.fdaApplicationNumber}" />
                  </div></th>
                <td width="26%">
                	<kul:htmlControlAttribute property="document.protocolList[0].fdaApplicationNumber" attributeEntry="${protocolAttributes.fdaApplicationNumber}" readOnly="${readOnly}" />
                </td>
                <th width="23%"><div align="right">
                	<c:if test="${KualiForm.protocolHelper.displayBillable}">                	
						<kul:htmlAttributeLabel attributeEntry="${protocolAttributes.billable}" />
					</c:if>
 				  </div></th>
                <td width="18%" align=left valign=middle>
                	<c:if test="${KualiForm.protocolHelper.displayBillable}">
                		<kul:htmlControlAttribute property="document.protocolList[0].billable" attributeEntry="${protocolAttributes.billable}" disabled="${KualiForm.protocolHelper.billableReadOnly}"/>
                	</c:if>
                </td>
              </tr>
              <tr>
                <th><div align="right">
                    ${KualiForm.protocolHelper.referenceId1Label}:
                  </div></th>
                <td align=left valign=middle><span> <span>
                  	<kul:htmlControlAttribute property="document.protocolList[0].referenceNumber1" attributeEntry="${protocolAttributes.referenceNumber1}" readOnly="${readOnly}" />
                 </span></span></td>
                <th><div align="right">
                    ${KualiForm.protocolHelper.referenceId2Label}:
                  </div></th>
                <td align=left valign=middle><span>
                  <kul:htmlControlAttribute property="document.protocolList[0].referenceNumber2" attributeEntry="${protocolAttributes.referenceNumber2}" readOnly="${readOnly}" />
                 </span></td>
              </tr>
              <tr>
                <th><div align="right">
                    <kul:htmlAttributeLabel attributeEntry="${protocolAttributes.description}" />
                  </div></th>
                <td colspan="3" align=left valign=middle>
                	
                    <table style="border:none; width:100%;" cellpadding="0" cellspacing="0">
                        <tr>
                            <td style="border:none;">

								<kul:htmlControlAttribute property="document.protocolList[0].description" attributeEntry="${protocolAttributes.description}" readOnly="${readOnly}" />
                                <c:if test="${!readOnly}">
                                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolAttributes.description.label}" />
                                </c:if>
                            </td>
                        </tr>
                    </table>

                </td>
              </tr>
            </table>
       
       <br>
       <%-- Other Identifiers--%>
    	<h3>
    		<span class="subhead-left">Other Identifiers</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.protocol.ProtocolReferenceType" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="View/edit protocol other identifiers">
        
        	<%-- Header --%>
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${protocolReferenceAttributes.protocolReferenceTypeCode}" />
				<kul:htmlAttributeHeaderCell attributeEntry="${protocolReferenceAttributes.referenceKey}" />
				<kul:htmlAttributeHeaderCell attributeEntry="${protocolReferenceAttributes.applicationDate}" />
				<kul:htmlAttributeHeaderCell attributeEntry="${protocolReferenceAttributes.approvalDate}" />
				<c:if test="${!readOnly}">
				    <kul:htmlAttributeHeaderCell literalLabel="Actions" />
				</c:if>
			</tr>
			<%-- Header --%>
			
            <%-- New data --%>
         	<kra:permission value="${KualiForm.protocolHelper.modifyProtocol}">
                <tr>
                    <th class="infoline" rowspan="2">add:</th>
                    <td class="infoline" style="text-align:center;">
						<kul:htmlControlAttribute property="newProtocolReference.protocolReferenceTypeCode" attributeEntry="${protocolReferenceAttributes.protocolReferenceTypeCode}" />
                    </td>
                    <td class="infoline" style="text-align:center;">
                        <kul:htmlControlAttribute property="newProtocolReference.referenceKey" attributeEntry="${protocolReferenceAttributes.referenceKey}" />
                    </td>
                    <td class="infoline" style="text-align:center;">
						<kul:htmlControlAttribute property="newProtocolReference.applicationDate" attributeEntry="${protocolReferenceAttributes.applicationDate}" datePicker="true" />
                    </td>
                    <td class="infoline" style="text-align:center;">
                        <kul:htmlControlAttribute property="newProtocolReference.approvalDate" attributeEntry="${protocolReferenceAttributes.approvalDate}" datePicker="true" />
                    </td>
                    <td class="infoline" rowspan="2" style="text-align:center;">
                        <div align=center>
							<html:image property="methodToCall.addProtocolReference.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;">Comment:</th>
                    <th colspan="3" style="vertical-align:bottom">
                    
                    	<table style="border:none; width:100%;" cellpadding="0" cellspacing="0">

                            <tr>
                                <td style="border:none; background:none;">
                                	<kul:htmlControlAttribute property="newProtocolReference.comments" attributeEntry="${protocolReferenceAttributes.comments}" />
                                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName1}" action="${action}" textAreaLabel="${protocolReferenceAttributes.comments.label}" />
                                </td>
                            </tr>

                        </table>
                    
                    </th>
                </tr>	            
	            
            </kra:permission>
            
			<%-- End of New data --%>
			
			<%-- Existing data --%>
        	<c:forEach var="protocolReference" items="${KualiForm.document.protocolList[0].protocolReferences}" varStatus="status">
	             <tr>
					<th class="infoline" rowspan="2">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.protocolList[0].protocolReferences[${status.index}].protocolReferenceType.description" 
	                													readOnly="true"	attributeEntry="${protocolReferenceTypeAttributes.description}"  /> </div>
					</td>

	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.protocolList[0].protocolReferences[${status.index}].referenceKey" 
	                													readOnly="true"	attributeEntry="${protocolReferenceAttributes.referenceKey}"  /> </div>
					</td>

	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.protocolList[0].protocolReferences[${status.index}].applicationDate" 
	                													readOnly="true"	attributeEntry="${protocolReferenceAttributes.applicationDate}"  /> </div>
					</td>

	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.protocolList[0].protocolReferences[${status.index}].approvalDate" 
	                													readOnly="true"	attributeEntry="${protocolReferenceAttributes.approvalDate}"  /> </div>
					</td>
					
                    <c:if test="${!readOnly}">
						<td rowspan="2">
							<div align=center>&nbsp;
								<kra:permission value="${KualiForm.protocolHelper.modifyProtocol}">  
									<html:image property="methodToCall.deleteProtocolReference.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
								</kra:permission>  
							</div>
		                </td>
	                </c:if>
	            </tr>
	            
                <tr>
                    
                    <th style="text-align:left;">Comment:</th>
                    <td colspan="3" style="vertical-align:bottom">
                    		                		
                        <kra:truncateComment textAreaFieldName="document.protocolList[0].protocolReferences[${status.index}].comments" action="${action}" textAreaLabel="${protocolReferenceAttributes.comments.label}" textValue="${KualiForm.document.protocolList[0].protocolReferences[status.index].comments}" displaySize="${commentDisplayLength}"/>
	                													                    
                    </td>
                </tr>	            
	            
        	</c:forEach>
			<%-- Existing data --%>
	        				
        </table>       
        
        <%--End of Other Identifiers --%>
		<br>
		
		<%--Area of Research --%>
    	<h3>
    		<span class="subhead-left">Area of Research</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.ResearchAreas" altText="help"/></span>
        </h3>
       
        <table cellpadding=0 cellspacing="0"  summary="">
             <tr>
              	<th><div align="left">&nbsp</div></th>  
				<th><kul:htmlAttributeLabel attributeEntry="${researchAreasAttributes.description}" noColon="true" /></th>
              	<c:if test="${!readOnly}">
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
              	</c:if>
             </tr>
             
             <c:if test="${!readOnly}">
	            <tr>
	              <th width="10%" class="infoline">add:</th>
	              <td width="70%" class="infoline">${KualiForm.document.protocolList[0].newDescription}
	              		<kul:multipleValueLookup boClassName="org.kuali.kra.bo.ResearchArea" 
	              		lookedUpCollectionName="protocolResearchAreas"
	              		anchor="${tabKey}"/>
				  </td>
	
	              <td width="20%" class="infoline"><div align="center">
	              &nbsp;
	              </div></td>
	            </tr>
	        </c:if>

            <logic:iterate name="KualiForm" id="protocolResearchAreas" property="document.protocolList[0].protocolResearchAreas" indexId="ctr" >
              <tr>
                <td class="infoline"><div align="center">
                	<b>${ctr+1}</b>
                </div></td>
                <td>
                	${KualiForm.document.protocolList[0].protocolResearchAreas[ctr].researchAreas.researchAreaCode}:${KualiForm.document.protocolList[0].protocolResearchAreas[ctr].researchAreas.description}
                </td>
                <c:if test="${!readOnly}">
	                <td><div align="center">
						<html:image property="methodToCall.deleteProtocolResearchArea.line${ctr}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>   
	                </div></td>
	            </c:if>        
              </tr>
            </logic:iterate>
            
        </table>
        <%--End of Area of Research --%> 
                           
    </div>
</kul:tab>
