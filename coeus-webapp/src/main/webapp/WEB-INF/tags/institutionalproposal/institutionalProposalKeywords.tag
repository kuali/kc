<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="scienceKeywordAttributes" value="${DataDictionary.ScienceKeyword.attributes}" />
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />

<kul:tab tabTitle="Keywords" defaultOpen="false" tabErrorKey="document.institutionalProposalList[0].keyword">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Keywords</span>
     		<span class="subhead-right"><kul:help parameterNamespace="KC-IP" parameterDetailType="Document" parameterName="keywords1HelpUrl" altText="help"/></span>
        </h3>
       
        <table cellpadding=0 cellspacing="0"  summary="">
             <tr>
              	<th><div align="left">&nbsp;</div></th>  
				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${scienceKeywordAttributes.description}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
             </tr>
             
            <c:if test="${!readOnly}">
            <tr>
              <th width="10%" class="infoline">Add:</th>
              <td width="70%" class="infoline">${KualiForm.document.institutionalProposal.newDescription}
                    <c:if test="${!readOnly}">
              		    <kul:multipleValueLookup boClassName="org.kuali.coeus.common.framework.keyword.ScienceKeyword" 
              		    lookedUpCollectionName="institutionalProposalScienceKeywords" 
              		    anchor="${tabKey}"/>
              		</c:if>
			  </td>

              <td width="20%" class="infoline"><div align="center">
              &nbsp;
              </div></td>
            </tr>
            </c:if>

            <logic:iterate name="KualiForm" id="proposalKeywords" property="document.institutionalProposal.institutionalProposalScienceKeywords" indexId="ctr">
              <tr>
                <td class="infoline"><div align="center">
                	${ctr+1} 
                </div></td>
                <td>
                	 ${KualiForm.document.institutionalProposal.institutionalProposalScienceKeywords[ctr].scienceKeyword.description}
					<kul:lookup boClassName="org.kuali.coeus.common.framework.keyword.ScienceKeyword" 
					fieldConversions="code:document.institutionalProposal.institutionalProposalScienceKeywords[${ctr}].scienceKeywordCode,description:document.institutionalProposal.institutionalProposalScienceKeywords[${ctr}].scienceKeyword.description"
					lookupParameters="" hideReturnLink="false" />
                </td>
                <td><div align="center">
                  <kul:htmlControlAttribute property="document.institutionalProposal.institutionalProposalScienceKeywords[${ctr}].selectKeyword" attributeEntry="${DataDictionary.PropScienceKeyword.attributes.selectKeyword}" readOnly="${readOnly}" />
                </div></td>
              </tr>
            </logic:iterate>
            
            <c:if test="${!readOnly && fn:length(KualiForm.document.institutionalProposal.institutionalProposalScienceKeywords) > 0}">
              <tr>
                <td class="infoline" colspan=2>&nbsp;</td>
                <td nowrap class="infoline"><div align=center>
	                <html:image property="methodToCall.selectAllScienceKeyword.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="javascript: selectAllInstitutionalProposalKeywords(document);return false" />    
	                <html:image property="methodToCall.deleteSelectedScienceKeyword.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-deleteselected.gif" title="Delete Selected" alt="Delete Selected" styleClass="tinybutton" />
                </div></td>
              </tr>
            </c:if>  
        </table>
    </div>
</kul:tab>
