<%--
 Copyright 2006-2008 The Kuali Foundation
 
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

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="scienceKeywordAttributes" value="${DataDictionary.ScienceKeyword.attributes}" />


<kul:tab tabTitle="Keywords" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Keywords</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.ScienceKeyword" altText="help"/></span>
        </h3>
       
        <table cellpadding=0 cellspacing="0"  summary="">
             <tr>
              	<th><div align="left">&nbsp</div></th>  
				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${scienceKeywordAttributes.description}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
             </tr>
            <tr>
              <th width="10%" class="infoline">Add:</th>
              <td width="70%" class="infoline">${KualiForm.document.institutionalProposal.newDescription}
              		<kul:multipleValueLookup boClassName="org.kuali.kra.bo.ScienceKeyword" 
              		lookedUpCollectionName="institutionalProposalScienceKeywords" 
              		anchor="${tabKey}"/>
			  </td>

              <td width="20%" class="infoline"><div align="center">
              &nbsp;
              </div></td>
            </tr>

            <logic:iterate name="KualiForm" id="proposalKeywords" property="document.institutionalProposal.institutionalProposalScienceKeywords" indexId="ctr">
              <tr>
                <td class="infoline"><div align="center">
                	${ctr+1} 
                </div></td>
                <td>
                	 ${KualiForm.document.institutionalProposal.institutionalProposalScienceKeywords[ctr].scienceKeyword.description}
					<kul:lookup boClassName="org.kuali.kra.bo.ScienceKeyword" 
					fieldConversions="scienceKeywordCode:document.institutionalProposal.institutionalProposalScienceKeywords[${ctr}].scienceKeywordCode,description:document.institutionalProposal.institutionalProposalScienceKeywords[${ctr}].scienceKeyword.description"
					lookupParameters="" hideReturnLink="false" />
                </td>
                <td><div align="center">
                  <kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalScienceKeywords[${ctr}].selectKeyword" attributeEntry="${DataDictionary.PropScienceKeyword.attributes.selectKeyword}" readOnly="${readOnly}" />
                </div></td>
              </tr>
            </logic:iterate>
              <tr>
                <td class="infoline" colspan=2>&nbsp;</td>
                <td nowrap class="infoline"><div align=center>
	            <%--<c:if test="${fn:length(KualiForm.document.institutionalProposal.institutionalProposalScienceKeywords) > 0}">--%>
	                <html:image property="methodToCall.selectAllScienceKeyword.anchor${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="javascript: selectAllInstitutionalProposalKeywords(document);return false" />    
	                <html:image property="methodToCall.deleteSelectedScienceKeyword.anchor${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-deleteselected.gif" title="Delete Selected" alt="Delete Selected" styleClass="tinybutton" />
	            <%--</c:if>--%>
                </div></td>
              </tr>            
        </table>
    </div>
</kul:tab>
