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

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="s2sFormAttributes" value="${DataDictionary.S2sOppForms.attributes}" />
<c:set var="textAreaFieldName" value="document.developmentProposalList[0].programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentGrantsGov" />

<kra:innerTab parentTab="Opportunity Search" defaultOpen="false" tabTitle="Forms">
   <div class="innerTab-container" align="left">
      <table class=tab cellpadding=0 cellspacing="0" summary=""> 
      <tbody id="G1">
          <c:choose>
              <c:when test="${not empty KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms}" >
                  <tr>
                      <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.formName}" noColon="true" /></div></th>
                      <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.mandatory}" noColon="true" /></div></th>
                      <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.include}" noColon="true" /></div></th>
                      <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${s2sFormAttributes.available}" noColon="true" /></div></th>
                      <kra:section permission="printProposal">    
                          <th width="150"><div align="center">
                          Select to Print:
                          <br/>
                          (<html:link href="#" onclick="javascript: selectAllIncludedGGForms(document);return false">All Included</html:link>
                          |
                          <html:link href="#" onclick="javascript: unselectAllGGForms(document);return false">None</html:link>)
                          </div></th>
                      </kra:section>
                  </tr>
        
                  <c:forEach var="form" items="${KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms}" varStatus="status">
                     <tr>                    
                         <td align="left" valign="middle">
                             <kul:htmlControlAttribute property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].formName" attributeEntry="${s2sFormAttributes.formName}" readOnly="true" />
                         </td>    
                                    
                         <td>
                             <div align="center">
                             <kul:htmlControlAttribute property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].mandatory" attributeEntry="${s2sFormAttributes.mandatory}" readOnly="true"/>
                             </div>
                         </td>
                    
                         <td>
                             <div align="center">
                                 <c:set var="isMandatory" value="${KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms[status.index].mandatory}" /> 
                                 <c:choose>
                                     <c:when test="${isMandatory}">                             
                                         <kul:htmlControlAttribute property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].include" attributeEntry="${s2sFormAttributes.include}" readOnly="true"/>
                                     </c:when>
                                     <c:when test="${!KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms[status.index].available}">                             
                                         <kul:htmlControlAttribute property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].include" attributeEntry="${s2sFormAttributes.include}" disabled="true"/>
                                     </c:when>
                                     <c:otherwise>
                                         <kul:htmlControlAttribute property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].include" attributeEntry="${s2sFormAttributes.include}"/>                        
                                     </c:otherwise>
                                 </c:choose>
                             </div>
                        </td>
                    
                        <td align="center" valign="middle">
                            <div align="center">                                                
                                <c:choose>
                                    <c:when test="${KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms[status.index].available}">
                                        <c:out value="Available"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="Not Available"/>
                                     </c:otherwise>
                                </c:choose>
                                <kul:htmlControlAttribute property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].available" attributeEntry="${s2sFormAttributes.available}" readOnlyBody="${readOnly}"/>
                            </div>
                        </td>
                    
                        <kra:section permission="printProposal">
                            <td align="center" valign="middle">
                                <div align="center">
                                    <!--  <html:checkbox property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].selectToPrint" disabled="${!KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms[status.index].available}"/>-->
                                    <kul:htmlControlAttribute property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].selectToPrint" attributeEntry="${s2sFormAttributes.selectToPrint}" disabled="${!KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms[status.index].available}"/>                                                            
                                </div>
                            </td>
                        </kra:section>
                    </tr>        
                    </c:forEach>
                    <kra:section permission="printProposal">
                        <tr>
                            <td colspan="5">
                                <div align="right">
                                    <html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif" styleClass="globalbuttons" property="methodToCall.printForms" alt="Print Selected Forms" onclick="excludeSubmitRestriction=true"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </div>
                            </td>
                        </tr>
                    </kra:section>
                </c:when>        
                <c:when test="${empty KualiForm.document.developmentProposalList[0].s2sOpportunity}" >
                    <tr><td>
                        No Grants.gov opportunity has been selected
                    </td></tr>     
                </c:when>
                <c:when test="${empty KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms}" >
                    <tr><td>
                        No forms are currently available for the Grants.gov opportunity selected.
                    </td></tr> 
               </c:when>    
            </c:choose>               
        </tbody>
        </table>
    </div>    
</kra:innerTab>

