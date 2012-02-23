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

<%-- Contained in institutionalProposalProjectPersonnel.tag --%>

<c:set var="institutionalProposal" value="${KualiForm.document.institutionalProposalList[0]}" />
<c:set var="investigatorCreditTypes" value="${KualiForm.institutionalProposalCreditSplitBean.investigatorCreditTypes}" />
<c:set var="projectPersonnel" value="${KualiForm.institutionalProposalCreditSplitBean.projectPersons}" />

<c:set var="contactAttributes" value="${DataDictionary.InstitutionalProposalContact.attributes}" />
<c:set var="unitCreditSplitAttributes" value="${DataDictionary.ProposalUnitCreditSplit.attributes}" />
<c:set var="personCreditSplitAttributes" value="${DataDictionary.InstitutionalProposalPersonCreditSplit.attributes}" />
<c:set var="columnWidth" value="${100/(fn:length(investigatorCreditTypes) + 1)}%" />

<div class="tab-container" align="center">
	<h3>
		<span class="subhead-left">Combined Credit Split</span>
	 	<span class="subhead-right"><kul:help parameterNamespace="KC-IP" parameterDetailType="Document" parameterName="combinedCreditSplit1HelpUrl" altText="help"/></span>
    </h3>

    <table cellpadding="0" cellspacing="0" summary="">
    	<%-- Heading with InvestigatorCreditType Description --%>
		<tr>
			<th width="${columnWidth}">&nbsp;</th>
			<c:forEach items="${investigatorCreditTypes}" var="invType" >
            	<th width="${columnWidth}">${invType.description}</th>
			</c:forEach>
		</tr>
		
		<%-- For each project person ... --%>
		<c:forEach items="${projectPersonnel}" var="projectPerson" varStatus="ppStatus">
			<%-- This var is a JSTL hack to get a string that will later be evaluated--%>
			<c:set var="projectPersonProperty" value="document.institutionalProposalList[0].projectPersons[${ppStatus.index}]" />
			
                <tr>
                    <%-- ... show full name --%>
                    <td nowrap class="tab-subhead">
                        <strong>
                            <kul:htmlControlAttribute property="${projectPersonProperty}.fullName" attributeEntry="${contactAttributes.fullName}" readOnly="true" />
                        </strong>
                    </td>
                    
                    <%-- ... show person credit split for each credit split type --%>
                    <c:forEach items="${investigatorCreditTypes}" var="invType">
                        <c:forEach items="${projectPerson.creditSplits}" var="personCreditSplit" varStatus="personSplitStatus" >
                            
                            <%-- This var is a JSTL hack to get a string that will later be evaluated--%>
                            <c:set var="personCreditSplitMacro" value="${projectPersonProperty}.creditSplit[${personSplitStatus.index}]" />
                            
                            <c:if test="${personCreditSplit.invCreditTypeCode == invType.invCreditTypeCode}">
                                <td class="tab-subhead">
                                    <div id="${projectPerson.fullName}_${invType.description}_${personSplitStatus.count}" align="right">
                                        <strong>
                                            <kul:htmlControlAttribute property="${personCreditSplitMacro}.credit" 
                                                                            attributeEntry="${personCreditSplitAttributes.credit}" styleClass="align-right" />
                                        </strong>
                                    </div>
                                </td>
                            </c:if>
                        </c:forEach>
                    </c:forEach>                               
                </tr>
                
            <%-- For each project person unit ... --%>
			<c:forEach items="${projectPerson.units}" var="personUnit" varStatus="unitStatus">
                             
            
         		<tr>
         			<%-- This var is a JSTL hack to get a string that will later be evaluated--%>
					<c:set var="unitProperty" value="${projectPersonProperty}.unit[${unitStatus.index}]" />
					
					<%-- ... show unit number and name --%>
            		<td nowrap>
            			${personUnit.unitNumber} - ${personUnit.unit.unitName}
            		</td>
            		
            		<%-- ... show unit credit split for each credit split type --%>               		
					<c:forEach items="${investigatorCreditTypes}" var="invType">
						<c:forEach items="${personUnit.creditSplits}" var="unitCreditSplit" varStatus="personUnitSplitStatus" >
  							<c:set var="unitCreditSplitMacro" value="${unitProperty}.creditSplit[${personUnitSplitStatus.index}]" />
  							<c:if test="${unitCreditSplit.invCreditTypeCode == invType.invCreditTypeCode}">
							<td>
            					<div align="right">
            						<kul:htmlControlAttribute property="${unitCreditSplitMacro}.credit" 
                                								attributeEntry="${unitCreditSplitAttributes.credit}" 
                                  								styleClass="align-right" />
								</div>
							</td>  
							</c:if>
						</c:forEach>
  					</c:forEach>      					
				</tr>
			</c:forEach>
			
			<%-- Show credit split totals for all person's units by cred split type --%>
			<c:if test="${fn:length(projectPerson.units) > 0}">
				<tr>
            		<td nowrap class="infoline">
            			<strong>Unit Total:</strong>
            		</td>
            		<c:set var="unitTotalMap" value="${KualiForm.institutionalProposalCreditSplitBean.unitTotalsMap[projectPerson.fullName]}" />
					<c:forEach items="${investigatorCreditTypes}" var="invType" >
                		<td class="infoline">
                			<div align="right">
                				<strong>${unitTotalMap[invType.invCreditTypeCode]}</strong>
                			</div>
                		</td>
					</c:forEach>
				</tr>            
			</c:if>
		</c:forEach>
		<tr>
        	<td colspan="${columnWidth}" nowrap class="tab-subhead" >
        		Totals
        	</td>
		</tr>
		
		<%-- Show credit split totals for all persons by cred split type --%>
		<c:if test="${fn:length(projectPersonnel) > 0}">			
            <tr>
				<td nowrap class="infoline">
					<strong>Project Person Total:</strong>
				</td>
  				<bean:define id="personsTotalsMap" name="KualiForm" property="institutionalProposalCreditSplitBean.personsTotalsMap" />
  				<c:forEach items="${investigatorCreditTypes}" var="invType" >
                	<td class="infoline">
                		<div align="right">
                			<strong>${personsTotalsMap[invType.invCreditTypeCode]}</strong>
                		</div>
                	</td>
  				</c:forEach>
			</tr>
		</c:if> 
	</table>
	<br/>
    <%-- <kra:section permission="modifyInstitutionalProposal"> --%>
    	<div align="center">
        	<html:image property="methodToCall.recalculateCreditSplit" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif" title="Recalculate" alt="Recalculate" styleClass="tinybutton"/>        
 		</div>
 	<%-- </kra:section> --%>   	 	
</div>
