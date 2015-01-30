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

<c:set var="proposalPersonAttributes" value="${DataDictionary.ProposalPerson.attributes}" />
<c:set var="unitCreditSplitAttributes" value="${DataDictionary.ProposalUnitCreditSplit.attributes}" />
<c:set var="personCreditSplitAttributes" value="${DataDictionary.ProposalPersonCreditSplit.attributes}" />
<c:set var="columnWidth" value="${100/(fn:length(KualiForm.document.developmentProposalList[0].investigatorCreditTypes) + 1)}%" />

<kul:tab tabTitle="Combined Credit Split" defaultOpen="true" tabDescription=" " tabErrorKey="document.developmentProposalList[0].investigator*" auditCluster="keyPersonnelAuditErrors" tabAuditKey="document.developmentProposalList[0].creditSplit" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Combined Credit Split</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.common.framework.type.InvestigatorCreditType" altText="help"/></span>
        </h3>

        <table cellpadding="0" cellspacing="0" summary="">
              <tr>
                <th width="${columnWidth}">&nbsp;</th>
				<c:forEach items="${KualiForm.document.developmentProposalList[0].investigatorCreditTypes}" var="invType" >
                	<th width="${columnWidth}">${invType.description}</th>
				</c:forEach>
              </tr>
			<c:forEach items="${KualiForm.document.developmentProposalList[0].investigators}" var="investigator" varStatus="invStatus">
  			<c:set var="investigatorProperty" value="document.developmentProposalList[0].investigator[${invStatus.index}]" />
              <tr>
                <td nowrap class="tab-subhead"><strong>
                  <kul:htmlControlAttribute property="${investigatorProperty}.fullName" 
                                      attributeEntry="${proposalPersonAttributes.fullName}" readOnly="true" />
                  </strong></td>
  
  
  <c:forEach items="${KualiForm.document.developmentProposalList[0].investigatorCreditTypes}" var="invType">
      <c:forEach items="${investigator.creditSplits}" var="personcreditsplit" varStatus="splitStatus" >
          <c:set var="personCreditSplit" value="${investigatorProperty}.creditSplits[${splitStatus.index}]" />
               <c:if test="${personcreditsplit.invCreditTypeCode == invType.code}">
                   <td class="tab-subhead"><div align="right"><strong>
                          <kul:htmlControlAttribute property="${personCreditSplit}.credit" 
                           attributeEntry="${personCreditSplitAttributes.credit}" styleClass="align-right" /> </c:if></strong></div></td>
                                         
        </c:forEach>
  </c:forEach>           
 
 
             </tr>
             
  <c:forEach items="${investigator.units}" var="personUnit" varStatus="unitStatus">
             <tr>
    <c:set var="unitProperty" value="${investigatorProperty}.units[${unitStatus.index}]" />
                <td nowrap>${personUnit.unitNumber} - ${personUnit.unit.unitName}</td>

     <c:forEach items="${KualiForm.document.developmentProposalList[0].investigatorCreditTypes}" var="invType">
    <c:forEach items="${personUnit.creditSplits}" var="unitcreditsplit" varStatus="splitStatus" >
      <c:set var="unitCreditSplit" value="${unitProperty}.creditSplits[${splitStatus.index}]" />
                    <c:if test="${unitcreditsplit.invCreditTypeCode == invType.code}">
                <td><div align="right"><kul:htmlControlAttribute property="${unitCreditSplit}.credit" 
                                      attributeEntry="${unitCreditSplitAttributes.credit}"  styleClass="align-right" /></c:if></div></td>
    </c:forEach>
      </c:forEach>
              </tr>
 
   </c:forEach>
  <c:if test="${fn:length(investigator.units) > 0}">
              <tr>
                <td nowrap class="infoline"><strong>Unit Total:
                </strong></td>
  <bean:define id="totalMap" name="KualiForm" property="creditSplitTotals.${investigator.proposalPersonNumber}" />
  <c:forEach items="${KualiForm.document.developmentProposalList[0].investigatorCreditTypes}" var="invType" >
                <td class="infoline"><div align="right"><strong>${totalMap[invType.code]}</strong></div></td>
  </c:forEach>
              </tr>            
  </c:if>
</c:forEach>
              <tr>
                <td colspan="${columnWidth}" nowrap class="tab-subhead" >Totals</td>
              </tr>
              <tr>
                <td nowrap class="infoline"><strong>Investigator Total:
                </strong></td>
  <bean:define id="totalMap" name="KualiForm" property="creditSplitTotals.investigator" />
  <c:forEach items="${KualiForm.document.developmentProposalList[0].investigatorCreditTypes}" var="invType" >
                <td class="infoline"><div align="right"><strong>${totalMap[invType.code]}</strong></div></td>
  </c:forEach>
              </tr>            
 
        </table>
        <br>
        
        <div align=center>&nbsp;
        <kra:section permission="modifyProposal">
              <html:image property="methodToCall.recalculateCreditSplit" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif" title="Recalculate" alt="Recalculate" styleClass="tinybutton"/>
        </kra:section>
   	 	</div>
   	 	
    </div>
</kul:tab>

