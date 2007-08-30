<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="scienceKeywordAttributes" value="${DataDictionary.ScienceKeyword.attributes}" />
<c:set var="textAreaFieldName" value="document.mailDescription" />
<c:set var="action" value="proposalDevelopmentProposal" />
<c:set var="className" value="org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument" />

<kul:tab tabTitle="Keywords" defaultOpen="true" tabErrorKey="">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Keywords</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
       
        <table>
             <tr>
              	<th><div align="left">&nbsp</div></th>  
				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${scienceKeywordAttributes.description}" skipHelpUrl="true" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
             </tr>
            <tr>
              <th class="infoline">Add:</th>
              <td class="infoline">${KualiForm.document.newDescription}
					<kul:lookup boClassName="org.kuali.kra.proposaldevelopment.bo.ScienceKeyword" 
					fieldConversions="scienceKeywordCode:document.newScienceKeywordCode,description:document.newDescription"
					lookupParameters="" hideReturnLink="false" />
			  </td>

              <td class="infoline"><div align="center">
			  <html:image property="methodToCall.addScienceKeyword" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Keyword" alt="Add Keyword" styleClass="tinybutton" />
              </div></td>
            </tr>

            <logic:iterate name="KualiForm" id="proposalKeywords" property="document.keywords" indexId="ctr">
              <tr>
                <td class="infoline"><div align="center">
                	${ctr+1} 
                </div></td>
                <td class="infoline">
                	 ${KualiForm.document.keywords[ctr].scienceKeyword.description}
					<kul:lookup boClassName="org.kuali.kra.proposaldevelopment.bo.ScienceKeyword" 
					fieldConversions="scienceKeywordCode:document.keywords[${ctr}].scienceKeywordCode,description:document.keywords[${ctr}].scienceKeyword.description"
					lookupParameters="" hideReturnLink="false" />
                </td>
                <td class="infoline"><div align="center">
                  <kul:htmlControlAttribute property="document.keywords[${ctr}].selectKeyword" attributeEntry="${DataDictionary.PropScienceKeyword.attributes.selectKeyword}" readOnly="false" />
                </div></td>
              </tr>
            </logic:iterate>
              <tr>
                <td class="infoline" colspan=2>&nbsp;</td>
                <td nowrap class="infoline"><div align=center>
                <html:image property="methodToCall.selectAllScienceKeyword" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" />    
                <html:image property="methodToCall.deleteSelectedScienceKeyword" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-deleteselected.gif" title="Delete Selected" alt="Delete Selected" styleClass="tinybutton" />
                </div></td>
              </tr>
             
        </table>

    </div>
</kul:tab>
