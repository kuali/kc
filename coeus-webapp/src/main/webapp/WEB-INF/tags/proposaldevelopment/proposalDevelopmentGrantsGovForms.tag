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
<c:set var="s2sFormAttributes" value="${DataDictionary.S2sOppForms.attributes}" />
<c:set var="textAreaFieldName" value="document.developmentProposalList[0].programAnnouncementTitle" />
<c:set var="action" value="proposalDevelopmentGrantsGov" />

<kul:innerTab parentTab="Opportunity Search" defaultOpen="false" tabTitle="Forms" tabErrorKey="grantsGovFormValidationErrors">
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
                                 <%-- the initiallyIncluded field is used by the JavaScript function selectAllIncludedGGForms in kuali_application.js --%>
                                 <input type="hidden" name="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].initiallyIncluded" value="${KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms[status.index].include}"/>
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
                                    <c:when test="${KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms[status.index].available and 
                                    					KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms[status.index].userAttachedForm}">
                                        <c:out value="User Attached Form"/>
                                    </c:when>
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
                                    <kul:htmlControlAttribute property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].selectToPrint" attributeEntry="${s2sFormAttributes.selectToPrint}" disabled="${!KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms[status.index].available}"
                                    readOnly="false"/>
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
                        No S2S opportunity has been selected
                    </td></tr>     
                </c:when>
                <c:when test="${empty KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms}" >
                    <tr><td>
                        No forms are currently available for the S2S opportunity selected.
                    </td></tr> 
               </c:when>    
            </c:choose>               
        </tbody>
        </table>
    </div>    
</kul:innerTab>

