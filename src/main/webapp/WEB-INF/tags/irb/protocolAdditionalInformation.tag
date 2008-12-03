<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.title" />
<c:set var="action" value="protocol" />
<c:set var="className" value="org.kuali.kra.irb.document.ProtocolDocument" />

<c:set var="researchAreasAttributes" value="${DataDictionary.ResearchAreas.attributes}" />


<kul:tab tabTitle="Additional Information" defaultOpen="false" tabErrorKey="" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Additional Information</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.bo.ProtocolType" altText="help"/></span>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">

	       <tr>
                <th><div align="right" width="52%"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.fdaApplicationNumber}" /></div></th>
                <td align="left" valign="middle" width="25%" >
                	<kul:htmlControlAttribute property="document.fdaApplicationNumber" attributeEntry="${protocolDocumentAttributes.fdaApplicationNumber}" />
                </td>
                <th><div align="right" width="5%"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.billable}" /></div></th>
                <td align="left" valign="middle" width="18%">
                	<kul:htmlControlAttribute property="document.billable" attributeEntry="${protocolDocumentAttributes.billable}" />
                </td>
            </tr>


            <tr>
                <th><div align="right">${KualiForm.protocolParameters['irb.protocol.referenceID1'].parameterValue}:</div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.referenceNumber1" attributeEntry="${protocolDocumentAttributes.referenceNumber1}" />
                </td>
                <th><div align="right">${KualiForm.protocolParameters['irb.protocol.referenceID2'].parameterValue}:</div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.referenceNumber2" attributeEntry="${protocolDocumentAttributes.referenceNumber2}" />
                </td>
            </tr>

            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolDocumentAttributes.description}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.description" attributeEntry="${protocolDocumentAttributes.description}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolDocumentAttributes.description.label}" />
                </td>
            </tr>

       </table>

    	<h3>
    		<span class="subhead-left">Keywords</span>
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
              <td width="70%" class="infoline">${KualiForm.document.newDescription}
              		<kul:multipleValueLookup boClassName="org.kuali.kra.bo.ResearchAreas" 
              		lookedUpCollectionName="protocolResearchAreas"
              		anchor="${tabKey}"/>
			  </td>

              <td width="20%" class="infoline"><div align="center">
              &nbsp;
              </div></td>
            </tr>

            <logic:iterate name="KualiForm" id="protocolResearchAreas" property="document.protocolResearchAreas" indexId="ctr">
              <tr>
                <td class="infoline"><div align="center">
                	${ctr+1} 
                </div></td>
                <td>
                	 ${KualiForm.document.protocolResearchAreas[ctr].researchAreas.description}
					<kul:lookup boClassName="org.kuali.kra.bo.ResearchAreas" 
					fieldConversions="researchAreaCode:document.protocolResearchAreas[${ctr}].researchAreaCode,description:document.protocolResearchAreas[${ctr}].researchAreas.description"
					lookupParameters="" hideReturnLink="false" />
                </td>
                <td><div align="center">
                  <kul:htmlControlAttribute property="document.protocolResearchAreas[${ctr}].selectResearchArea" attributeEntry="${DataDictionary.ProtocolResearchAreas.attributes.selectResearchArea}" readOnly="${readOnly}" />
                </div></td>
              </tr>
            </logic:iterate>
            
            <kra:section permission="modifyProtocol">
              <tr>
                <td class="infoline" colspan=2>&nbsp;</td>
                <td nowrap class="infoline"><div align=center>
                <c:if test="${fn:length(KualiForm.document.protocolResearchAreas) > 0}">
	                <html:image property="methodToCall.selectAllProtocolDocument.anchor${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="javascript: selectAllResearchAreas(document);return false" />    
	                <html:image property="methodToCall.deleteSelectedProtocolDocument.anchor${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-deleteselected.gif" title="Delete Selected" alt="Delete Selected" styleClass="tinybutton" />
	            </c:if>
                </div></td>
              </tr>
             </kra:section> 

        </table>              
       
    </div>
</kul:tab>
