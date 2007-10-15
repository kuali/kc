<%--
 Copyright 2005-2006 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalPersonAttributes" value="${DataDictionary.ProposalPerson.attributes}" />
<c:set var="unitCreditSplitAttributes" value="${DataDictionary.ProposalUnitCreditSplit.attributes}" />
<c:set var="personCreditSplitAttributes" value="${DataDictionary.ProposalPersonCreditSplit.attributes}" />

<c:if test="${!empty creditSplitEnabledFlag}">
<kul:tab tabTitle="Combined Credit Split" defaultOpen="true" tabErrorKey="">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Combined Credit Split</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType" altText="help"/></span>
        </div>

        <table cellpadding="0" cellspacing="0" summary="">
              <tr>
                <th width="20%"></th>
<c:forEach items="${KualiForm.investigatorCreditTypes}" var="invType" >
                <th width="20%">${invType.description}</th>
</c:forEach>
              </tr>
<c:forEach items="${KualiForm.investigators}" var="investigator" varStatus="invStatus">
  <c:set var="investigatorProperty" value="investigators[${invStatus.index}]" />
              <tr>
                <td nowrap class="tab-subhead1"><strong>
                  <kul:htmlControlAttribute property="${investigatorProperty}.fullName" 
                                      attributeEntry="${proposalPersonAttributes.fullName}" readOnly="true" />
                  </strong></td>
  <c:forEach items="${investigator.creditSplits}" varStatus="splitStatus" >
    <c:set var="personCreditSplit" value="${investigatorProperty}.creditSplits[${splitStatus.index}]" />
                <td class="tab-subhead1"><strong>
                  <kul:htmlControlAttribute property="${personCreditSplit}.credit" 
                                      attributeEntry="${personCreditSplitAttributes.credit}" />
                </strong></td>
  </c:forEach> 
             </tr>
  <c:forEach items="${investigator.units}" var="personUnit" varStatus="unitStatus">
             <tr>
    <c:set var="unitProperty" value="${investigatorProperty}.units[${unitStatus.index}]" />
                <td nowrap>${personUnit.unitNumber} - ${personUnit.unit.unitName}</td>

    <c:forEach items="${personUnit.creditSplits}" varStatus="splitStatus" >
      <c:set var="unitCreditSplit" value="${unitProperty}.creditSplits[${splitStatus.index}]" />
    
                <td><kul:htmlControlAttribute property="${unitCreditSplit}.credit" 
                                      attributeEntry="${unitCreditSplitAttributes.credit}" /></td>
    </c:forEach>
              </tr>
  </c:forEach>
  <c:if test="${fn:length(investigator.units) > 0}">
              <tr>
                <td nowrap class="infoline"><strong>Unit Total:
                </strong></td>
  <bean:define id="totalMap" name="KualiForm" property="creditSplitTotals.${investigator.fullName}" />
  <c:forEach items="${KualiForm.investigatorCreditTypes}" var="invType" >
                <td class="infoline"><div align="right"><strong>${totalMap[invType.invCreditTypeCode]}</strong></div></td>
  </c:forEach>
              </tr>            
  </c:if>
</c:forEach>
        </table>
    </div>
</kul:tab>
</c:if>

