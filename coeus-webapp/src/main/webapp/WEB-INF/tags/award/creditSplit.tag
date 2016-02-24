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

<%-- Contained in awardProjectPersonnel.tag --%>

<c:set var="award" value="${KualiForm.document.awardList[0]}" />
<c:set var="investigatorCreditTypes" value="${KualiForm.awardCreditSplitBean.investigatorCreditTypes}" />
<c:set var="projectPersonnel" value="${KualiForm.awardCreditSplitBean.projectPersons}" />

<c:set var="contactAttributes" value="${DataDictionary.AwardContact.attributes}" />
<c:set var="unitCreditSplitAttributes" value="${DataDictionary.ProposalUnitCreditSplit.attributes}" />
<c:set var="personCreditSplitAttributes" value="${DataDictionary.AwardPersonCreditSplit.attributes}" />
<c:set var="columnWidth" value="${100/(fn:length(investigatorCreditTypes) + 1)}%" />
<c:set var="errkey" value=""/>
<c:forEach items="${projectPersonnel}" var="projectPerson" varStatus="ppStatus">
<c:set var="errkey" value="${errkey},document.awardList[0].projectPersons[${ppStatus.index}].cr*,document.awardList[0].projectPersons[${ppStatus.index}].un*"/>
</c:forEach>
<kul:innerTab tabTitle="Combined Credit Split" parentTab="Project Personnel" defaultOpen="true" 
                tabErrorKey="${errkey}" auditCluster="contactsCreditSplitAuditErrors" tabAuditKey="document.awardList[0].projectPersons.awardPerson*">

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
          <c:set var="projectPersonProperty" value="document.awardList[0].projectPersons[${ppStatus.index}]" />
          <c:if test="${!projectPerson.keyPerson || projectPerson.optInUnitStatus}">
            
                <tr>
                    <%-- ... show full name --%>
                    <td nowrap class="tab-subhead">
                        <strong>
                        	<c:choose>
                        		<c:when test="${projectPerson.isRolodexPerson}">
                        			<c:out value="${projectPerson.rolodex.organization}"/>
                        		</c:when>
                        		<c:otherwise>
                        			<kul:htmlControlAttribute property="${projectPersonProperty}.fullName" attributeEntry="${contactAttributes.fullName}" readOnly="true" />
                        		</c:otherwise>
                        	</c:choose>
                        </strong>
                    </td>
                    
                    <%-- ... show person credit split for each credit split type --%>
                    <c:forEach items="${investigatorCreditTypes}" var="invType">
                        <c:forEach items="${projectPerson.creditSplits}" var="personCreditSplit" varStatus="personSplitStatus" >
                            
                            <%-- This var is a JSTL hack to get a string that will later be evaluated--%>
                            <c:set var="personCreditSplitMacro" value="${projectPersonProperty}.creditSplits[${personSplitStatus.index}]" />
                            
                            <c:if test="${personCreditSplit.invCreditTypeCode == invType.code}">
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
                    <c:set var="unitProperty" value="${projectPersonProperty}.units[${unitStatus.index}]" />
                    
                    <%-- ... show unit number and name --%>
                    <td nowrap>
                        ${personUnit.unitNumber} - ${personUnit.unit.unitName}
                    </td>
                    
                    <%-- ... show unit credit split for each credit split type --%>                     
                    <c:forEach items="${investigatorCreditTypes}" var="invType">
                        <c:forEach items="${personUnit.creditSplits}" var="unitCreditSplit" varStatus="personUnitSplitStatus" >
                            <c:set var="unitCreditSplitMacro" value="${unitProperty}.creditSplits[${personUnitSplitStatus.index}]" />
                            <c:if test="${unitCreditSplit.invCreditTypeCode == invType.code}">
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
                    <c:set var="unitTotalMap" value="${KualiForm.awardCreditSplitBean.unitTotalsMap[projectPerson.fullName]}" />
                    <c:forEach items="${investigatorCreditTypes}" var="invType" >
                        <td class="infoline">
                            <div align="right">
                                <strong>${unitTotalMap[invType.code]}</strong>
                            </div>
                        </td>
                    </c:forEach>
                </tr>            
            </c:if>
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
                <bean:define id="personsTotalsMap" name="KualiForm" property="awardCreditSplitBean.personsTotalsMap" />
                <c:forEach items="${investigatorCreditTypes}" var="invType" >
                    <td class="infoline">
                        <div align="right">
                            <strong>${personsTotalsMap[invType.code]}</strong>
                        </div>
                    </td>
                </c:forEach>
            </tr>
        </c:if> 
    </table>
    <br/>
    <%-- <kra:section permission="modifyAward"> --%>
        <div align="center">
            <html:image property="methodToCall.recalculateCreditSplit" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif" title="Recalculate" alt="Recalculate" styleClass="tinybutton"/>        
        </div>
    <%-- </kra:section> --%>        
</kul:innerTab>
