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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="scienceKeywordAttributes" value="${DataDictionary.ScienceKeyword.attributes}" />
<c:set var="textAreaFieldName" value="document.developmentProposalList[0].mailDescription" />
<c:set var="action" value="proposalDevelopmentProposal" />
<c:set var="className" value="org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument" />

<kul:tab tabTitle="Keywords" defaultOpen="false" tabErrorKey="document.developmentProposalList[0].propScienceKeyword">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Keywords</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.common.framework.keyword.ScienceKeyword" altText="help"/></span>
        </h3>
       
        <table id="keywords-table" cellpadding=0 cellspacing="0"  summary="">
             <tr>
              	<th><div align="left">&nbsp;</div></th>  
				<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${scienceKeywordAttributes.description}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
             </tr>
            <kra:section permission="modifyProposal">
            <tr>
              <th width="10%" class="infoline">Add:</th>
              <td width="70%" class="infoline">${KualiForm.document.developmentProposalList[0].newDescription}
              		<kul:multipleValueLookup boClassName="org.kuali.coeus.common.framework.keyword.ScienceKeyword" 
              		lookedUpCollectionName="propScienceKeywords" 
              		anchor="${tabKey}"/>
			  </td>

              <td width="20%" class="infoline"><div align="center">
              &nbsp;
              </div></td>
            </tr>
			</kra:section>            

            <logic:iterate name="KualiForm" id="proposalKeywords" property="document.developmentProposalList[0].propScienceKeywords" indexId="ctr">
              <tr>
                <td class="infoline"><div align="center">
                	${ctr+1} 
                </div></td>
                <td>
                	 ${KualiForm.document.developmentProposalList[0].propScienceKeywords[ctr].scienceKeyword.description}
					<kul:lookup boClassName="org.kuali.coeus.common.framework.keyword.ScienceKeyword" 
					fieldConversions="code:document.developmentProposalList[0].propScienceKeyword[${ctr}].scienceKeywordCode,description:document.developmentProposalList[0].propScienceKeyword[${ctr}].scienceKeyword.description"
					lookupParameters="" hideReturnLink="false" />
                </td>
                <td><div align="center">
                  <kul:htmlControlAttribute property="document.developmentProposalList[0].propScienceKeyword[${ctr}].selectKeyword" attributeEntry="${DataDictionary.PropScienceKeyword.attributes.selectKeyword}" readOnly="${readOnly}" />
                </div></td>
              </tr>
            </logic:iterate>
            
            <kra:section permission="modifyProposal">
              <tr>
                <td class="infoline" colspan=2>&nbsp;</td>
                <td nowrap class="infoline"><div align=center>
                <c:if test="${fn:length(KualiForm.document.developmentProposalList[0].propScienceKeywords) > 0}">
	                <html:image property="methodToCall.selectAllScienceKeyword.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="javascript: selectAllKeywords(document,'document.developmentProposalList[0].propScienceKeyword');return false" />    
	                <html:image property="methodToCall.deleteSelectedScienceKeyword.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-deleteselected.gif" title="Delete Selected" alt="Delete Selected" styleClass="tinybutton" />
	            </c:if>
                </div></td>
              </tr>
             </kra:section> 

             
        </table>

    </div>
</kul:tab>
