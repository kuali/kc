<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="scienceKeywordAttributes" value="${DataDictionary.ScienceKeyword.attributes}" />
<c:set var="textAreaFieldName" value="document.mailDescription" />
<c:set var="action" value="proposalDevelopmentProposal" />
<c:set var="className" value="org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument" />

<kul:tab tabTitle="Keywords" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Keywords</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.ScienceKeyword" altText="help"/></span>
        </div>
       
        <table cellpadding=0 cellspacing="0"  summary="">
             <tr>
              	<th><div align="left">&nbsp</div></th>  
				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${scienceKeywordAttributes.description}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
             </tr>
            <tr>
              <th width="10%" class="infoline">Add:</th>
              <td width="70%" class="infoline">${KualiForm.document.newDescription}
              		<kul:multipleValueLookup boClassName="org.kuali.kra.proposaldevelopment.bo.ScienceKeyword" 
              		lookedUpCollectionName="propScienceKeywords" 
              		anchor="${tabKey}"/>
			  </td>

              <td width="20%" class="infoline"><div align="center">
              &nbsp;
              </div></td>
            </tr>

            <logic:iterate name="KualiForm" id="proposalKeywords" property="document.propScienceKeywords" indexId="ctr">
              <tr>
                <td class="infoline"><div align="center">
                	${ctr+1} 
                </div></td>
                <td>
                	 ${KualiForm.document.propScienceKeywords[ctr].scienceKeyword.description}
					<kul:lookup boClassName="org.kuali.kra.proposaldevelopment.bo.ScienceKeyword" 
					fieldConversions="scienceKeywordCode:document.propScienceKeyword[${ctr}].scienceKeywordCode,description:document.propScienceKeyword[${ctr}].scienceKeyword.description"
					lookupParameters="" hideReturnLink="false" />
                </td>
                <td><div align="center">
                  <kul:htmlControlAttribute property="document.propScienceKeyword[${ctr}].selectKeyword" attributeEntry="${DataDictionary.PropScienceKeyword.attributes.selectKeyword}" readOnly="${readOnly}" />
                </div></td>
              </tr>
            </logic:iterate>
            
            <kra:section permission="modifyProposal">
              <tr>
                <td class="infoline" colspan=2>&nbsp;</td>
                <td nowrap class="infoline"><div align=center>
                <c:if test="${fn:length(KualiForm.document.propScienceKeywords) > 0}">
	                <html:image property="methodToCall.selectAllScienceKeyword.anchor${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="javascript: selectAllKeywords(document);return false" />    
	                <html:image property="methodToCall.deleteSelectedScienceKeyword.anchor${tabKey}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-deleteselected.gif" title="Delete Selected" alt="Delete Selected" styleClass="tinybutton" />
	            </c:if>
                </div></td>
              </tr>
             </kra:section> 

             
        </table>

    </div>
</kul:tab>
