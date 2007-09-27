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

        <table  cellpadding="0" cellspacing="0" summary="">
            <tbody>
              <tr>
                <th width="20%"></th>
<c:forEach items="${KualiForm.investigatorCreditTypes}" var="invType" >
                <th width="20%">${invType.description}</th>
</c:forEach>
              </tr>
<c:forEach items="${KualiForm.investigators}" var="investigator" varStatus="invStatus">
              <tr>
  <c:set var="investigatorProperty" value="investigators[${invStatus.index}]" />
                <td nowrap class="tab-subhead1"><strong>
                  <kul:htmlControlAttribute property="${investigatorProperty}.fullName" 
                                      attributeEntry="${proposalPersonAttributes.fullName}" readOnly="true" />
                  </strong></td>
  <c:forEach items="${investigator.creditSplits}" varStatus="splitStatus" >
    <c:set var="personCreditSplit" value="${investigatorProperty}.creditSplits[${splitStatus.index}]" />
                <td class="tab-subhead1><div align="right"><strong>
                  <kul:htmlControlAttribute property="${personCreditSplit}.credit" 
                                      attributeEntry="${personCreditSplitAttributes.credit}" />
                </strong></div></td>
  </c:forEach> 
             </tr>

    <c:forEach items="${investigator.units}" var="personUnit" varStatus="unitStatus">
             <tr>
      <c:set var="unitProperty" value="${investigatorProperty}.units[${unitStatus.index}]" />
                <td nowrap>${personUnit.unitNumber} - ${personUnit.unitNumber}</td>

      <c:forEach items="${personUnit.creditSplits}" varStatus="splitStatus" >
        <c:set var="unitCreditSplit" value="${unitProperty}.creditSplits[${splitStatus.index}]" />
    
                <td><div align="right"><strong>
                  <kul:htmlControlAttribute property="${unitCreditSplit}.credit" 
                                      attributeEntry="${unitCreditSplitAttributes.credit}" />
                </strong></div></td>
      </c:forEach>
              </tr>
    </c:forEach>
              <tr>
                <td nowrap class="infoline"><strong>Unit Total:</strong></td>
                <td class="infoline"><div align="right"><strong>100.0</strong></div></td>
                <td class="infoline"><div align="right"><strong>100.0</strong></div></td>
                <td class="infoline"><div align="right"><strong>100.0</strong></div></td>
                <td class="infoline"><div align="right"><strong>100.0</strong></div></td>
              </tr>            
</c:forEach>
            </tbody>
        </table>
    </div>
</kul:tab>
</c:if>

