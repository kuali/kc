<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="protocolReferenceAttributes" value="${DataDictionary.ProtocolReference.attributes}" />
<c:set var="protocolReferenceTypeAttributes" value="${DataDictionary.ProtocolReferenceType.attributes}" />  
<c:set var="textAreaFieldName" value="document.protocol.description" />
<c:set var="action" value="protocol" />
<c:set var="className" value="org.kuali.kra.irb.document.ProtocolDocument" />

<c:set var="researchAreasAttributes" value="${DataDictionary.ResearchAreas.attributes}" />


<kul:tab tabTitle="Additional Information" defaultOpen="false" tabErrorKey="newProtocolReference*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
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
                	<kul:htmlControlAttribute property="document.protocol.fdaApplicationNumber" attributeEntry="${protocolAttributes.fdaApplicationNumber}" />
                </td>
                <th width="23%"><div align="right">
					<kul:htmlAttributeLabel attributeEntry="${protocolAttributes.billable}" />
 				  </div></th>
                <td width="18%" align=left valign=middle>
                	<kul:htmlControlAttribute property="document.protocol.billable" attributeEntry="${protocolAttributes.billable}" />
                </td>
              </tr>
              <tr>
                <th><div align="right">
                    ${KualiForm.protocolHelper.referenceId1Label}:
                  </div></th>
                <td align=left valign=middle><span> <span>
                  	<kul:htmlControlAttribute property="document.protocol.referenceNumber1" attributeEntry="${protocolAttributes.referenceNumber1}" />
                 </span></span></td>
                <th><div align="right">
                    ${KualiForm.protocolHelper.referenceId2Label}:
                  </div></th>
                <td align=left valign=middle><span>
                  <kul:htmlControlAttribute property="document.protocol.referenceNumber2" attributeEntry="${protocolAttributes.referenceNumber2}" />
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
                                <kul:htmlControlAttribute property="document.protocol.description" attributeEntry="${protocolAttributes.description}" />
                            </td>
                            <td style="border:none; width:20px; vertical-align:bottom;">
                                <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolAttributes.description.label}" />
                            </td>
                        </tr>
                    </table>

                </td>
              </tr>
            </table>
       
       <%-- Other Identifiers--%>
    	<h3>
    		<span class="subhead-left">Other Identifiers</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.bo.ProtocolReferenceType" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 class="datatable" summary="View/edit protocol other identifiers">
        
        	<%-- Header --%>
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${protocolReferenceAttributes.protocolReferenceTypeCode}" scope="col" />
				<kul:htmlAttributeHeaderCell attributeEntry="${protocolReferenceAttributes.referenceKey}" scope="col" />
				<kul:htmlAttributeHeaderCell attributeEntry="${protocolReferenceAttributes.applicationDate}" scope="col" />
				<kul:htmlAttributeHeaderCell attributeEntry="${protocolReferenceAttributes.approvalDate}" scope="col" />
				<%--<c:if test="${not readOnly}">--%>
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				<%--</c:if>--%>
			</tr>
			<%-- Header --%>
			
            <%-- New data --%>
        	<kra:section permission="modifyProtocol">
	            <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>
	
	            <td align="left" valign="middle" class="infoline">
	               	<div align="center">
	               		<kul:htmlControlAttribute property="newProtocolReference.protocolReferenceTypeCode" attributeEntry="${protocolReferenceAttributes.protocolReferenceTypeCode}" />
	            	</div>
				</td>
				
	            <td align="left" valign="middle" class="infoline">
	               	<div align="center">
	               		<kul:htmlControlAttribute property="newProtocolReference.referenceKey" attributeEntry="${protocolReferenceAttributes.referenceKey}" />
	            	</div>
				</td>
								
	            <td align="left" valign="middle" class="infoline">
	               	<div align="center">
	               		<kul:htmlControlAttribute property="newProtocolReference.applicationDate" attributeEntry="${protocolReferenceAttributes.applicationDate}" datePicker="true" />
	            	</div>
				</td>

	            <td align="left" valign="middle" class="infoline">
	               	<div align="center">
	               		<kul:htmlControlAttribute property="newProtocolReference.approvalDate" attributeEntry="${protocolReferenceAttributes.approvalDate}" datePicker="true" />
	            	</div>
				</td>
	
				<td align="left" valign="middle" class="infoline">
					<div align=center>
						<html:image property="methodToCall.addProtocolReference.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
	               </td>
	            </tr>
            </kra:section>
			<%-- New data --%>
			
			<%-- Existing data --%>
        	<c:forEach var="protocolParticipant" items="${KualiForm.document.protocol.protocolReferences}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.protocol.protocolReferences[${status.index}].protocolReferenceType.description" 
	                													readOnly="true"	attributeEntry="${protocolReferenceTypeAttributes.description}"  /> </div>
					</td>

	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.protocol.protocolReferences[${status.index}].referenceKey" 
	                													readOnly="true"	attributeEntry="${protocolReferenceAttributes.referenceKey}"  /> </div>
					</td>

	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.protocol.protocolReferences[${status.index}].applicationDate" 
	                													readOnly="true"	attributeEntry="${protocolReferenceAttributes.applicationDate}"  /> </div>
					</td>

	                <td align="left" valign="middle">
	                	<div align="center"> 
	                	<kul:htmlControlAttribute property="document.protocol.protocolReferences[${status.index}].approvalDate" 
	                													readOnly="true"	attributeEntry="${protocolReferenceAttributes.approvalDate}"  /> </div>
					</td>

					<td>
						<div align=center>&nbsp;
							<kra:section permission="modifyProtocol">  
								<html:image property="methodToCall.deleteProtocolReference.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</kra:section>  
						</div>
	                </td>
	            </tr>
        	</c:forEach>
			<%-- Existing data --%>
	        				
        </table>       
        
        <%--End of Other Identifiers --%>

		<%--Area of Research --%>
    	<h3>
    		<span class="subhead-left">Area of Research</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.ResearchAreas" altText="help"/></span>
        </h3>
       
        <table cellpadding=0 cellspacing="0"  summary="">
             <tr>
              	<th><div align="left">&nbsp</div></th>  
				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${researchAreasAttributes.description}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
             </tr>
            <tr>
              <th width="10%" class="infoline">Add:</th>
              <td width="70%" class="infoline">${KualiForm.document.protocol.newDescription}
              		<kul:multipleValueLookup boClassName="org.kuali.kra.bo.ResearchAreas" 
              		lookedUpCollectionName="protocolResearchAreas"
              		anchor="${tabKey}"/>
			  </td>

              <td width="20%" class="infoline"><div align="center">
              &nbsp;
              </div></td>
            </tr>

            <logic:iterate name="KualiForm" id="protocolResearchAreas" property="document.protocol.protocolResearchAreas" indexId="ctr">
              <tr>
                <td class="infoline"><div align="center">
                	${ctr+1} 
                </div></td>
                <td>
                	 ${KualiForm.document.protocol.protocolResearchAreas[ctr].researchAreas.description}
                </td>
                <td><div align="center">
                  <kul:htmlControlAttribute property="document.protocol.protocolResearchAreas[${ctr}].selectResearchArea" attributeEntry="${DataDictionary.ProtocolResearchAreas.attributes.selectResearchArea}" readOnly="${readOnly}" />
                </div></td>
              </tr>
            </logic:iterate>
            
            <kra:section permission="modifyProtocol">
              <tr>
                <td class="infoline" colspan=2>&nbsp;</td>
                <td nowrap class="infoline"><div align=center>
                <c:if test="${fn:length(KualiForm.document.protocol.protocolResearchAreas) > 0}">
	                <html:image property="methodToCall.selectAllProtocolDocument.anchor${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="javascript: selectAllResearchAreas(document);return false" />    
	                <html:image property="methodToCall.deleteSelectedProtocolDocument.anchor${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-deleteselected.gif" title="Delete Selected" alt="Delete Selected" styleClass="tinybutton" />
	            </c:if>
                </div></td>
              </tr>
             </kra:section> 

        </table>
        <%--End of Area of Research --%> 
                           
    </div>
</kul:tab>
