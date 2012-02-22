<%--
 Copyright 2005-2010 The Kuali Foundation
 
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

<c:set var="awardKeywordAttributes" value="${DataDictionary.AwardScienceKeyword.attributes}" />
<c:set var="scienceKeywordAttributes" value="${DataDictionary.ScienceKeyword.attributes}" />
<kul:tab tabTitle="Keywords" defaultOpen="false" useCurrentTabIndexAsKey="false" tabErrorKey="document.awardList[0].keywords">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Keywords</span>
            <span class="subhead-right"><kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardKeywordHelpUrl" altText="help"/></span>
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
              <td width="70%" class="infoline">(select)
              		<kul:multipleValueLookup boClassName="org.kuali.kra.bo.ScienceKeyword" 
              		lookedUpCollectionName="keywords" 
              		anchor="${tabKey}"/>
			  </td>

              <td width="20%" class="infoline"><div align="center">
              &nbsp;
              </div></td>
            </tr>
            </c:if>
			<c:forEach var="awardKeywords" items="${KualiForm.document.award.keywords}" varStatus="status">		
              <tr>
                <td class="infoline"><div align="center">
                	${status.index+1} 
                </div></td>
                <td>
                	 ${KualiForm.document.award.keywords[status.index].scienceKeyword.description}
                	 <c:if test="${!readOnly}">
					<kul:lookup boClassName="org.kuali.kra.bo.ScienceKeyword" 
						fieldConversions="scienceKeywordCode:document.award.keyword[${status.index}].scienceKeywordCode,description:document.award.keyword[${status.index}].scienceKeyword.description"
						lookupParameters="" hideReturnLink="false" />
					</c:if>
                </td>
                <td><div align="center">
                  <kul:htmlControlAttribute property="document.award.keyword[${status.index}].selectKeyword" attributeEntry="${DataDictionary.PropScienceKeyword.attributes.selectKeyword}" readOnly="${readOnly}" />
                </div></td>
              </tr>
            </c:forEach>
            <%-- kra:section should be added by looking at the authorization--%>
            <%-- <kra:section permission="modifyAward">--%>
              <tr>
                <td class="infoline" colspan=2>&nbsp;</td>
                <td nowrap class="infoline"><div align=center>
                <%--<c:if test="${fn:length(KualiForm.document.award.keywords) > 0}">--%>
                <c:if test="${!readOnly}">
	                <html:image property="methodToCall.selectAllScienceKeyword.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="javascript: selectAllAwardKeywords(document);return false" />    
	                <html:image property="methodToCall.deleteSelectedScienceKeyword.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-deleteselected.gif" title="Delete Selected" alt="Delete Selected" styleClass="tinybutton" />
	            </c:if>
	            <%--</c:if>--%>
                </div></td>
              </tr>
             <%--</kra:section>--%> 
        </table>
    </div>
</kul:tab>
