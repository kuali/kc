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

<c:set var="subAwardTemplateInfoAttributes" value="${DataDictionary.SubAwardTemplateInfo.attributes}" />
<c:set var="attachments" value="${KualiForm.document.subAwardList[0].subAwardTemplateInfo}"/>
<kul:tabTop tabTitle="Template" defaultOpen="true" tabErrorKey="document.subAwardList[0].subAwardTemplateInfo[0].*" >
	<div class="tab-container" align="center">
   		<h3>
   			<span class="subhead-left">Template</span>
   			<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.subaward.bo.SubAwardTemplateInfo"  altText="help"/></span>
   			</h3>
   			<table id="attachments-table" class="tab" cellpadding="4" cellspacing="0" summary="">
   			<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.sowOrSubProposalBudget}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].sowOrSubProposalBudget" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.sowOrSubProposalBudget}" />
               </td>
               <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.subProposalDate}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].subProposalDate" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.subProposalDate}" datePicker="true" />
               </td>
             </tr>
             <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.invoiceOrPaymentContact}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].invoiceOrPaymentContact" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.invoiceOrPaymentContact}" />
               </td>
               <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.finalStmtOfCostscontact}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].finalStmtOfCostscontact" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.finalStmtOfCostscontact}"  />
               </td>
             </tr>
             <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.changeRequestsContact}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].changeRequestsContact" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.changeRequestsContact}" />
               </td>
               <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.terminationContact}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].terminationContact" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.terminationContact}"  />
               </td>
             </tr>
             <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.noCostExtensionContact}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].noCostExtensionContact" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.noCostExtensionContact}" />
               </td>
               <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.perfSiteDiffFromOrgAddr}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].perfSiteDiffFromOrgAddr" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.perfSiteDiffFromOrgAddr}"  />
               </td>
             </tr>
              <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.perfSiteSameAsSubPiAddr}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].perfSiteSameAsSubPiAddr" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.perfSiteSameAsSubPiAddr}" />
               </td>
               <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.subRegisteredInCcr}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].subRegisteredInCcr" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.subRegisteredInCcr}"  />
               </td>
             </tr>
             <tr>
               <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.parentCongressionalDistrict}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].parentCongressionalDistrict" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.parentCongressionalDistrict}" />
               </td>
               <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.parentDunsNumber}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].parentDunsNumber" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.parentDunsNumber}"  />
               </td>
             </tr>
             <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.copyRightType}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].copyRightType" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.copyRightType}" />
               </td>
               <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.exemptFromRprtgExecComp}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].exemptFromRprtgExecComp" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.exemptFromRprtgExecComp}"  />
               </td>
             </tr>
             <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.carryForwardRequestsSentTo}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].carryForwardRequestsSentTo" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.carryForwardRequestsSentTo}" />
               </td>
               <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.automaticCarryForward}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].automaticCarryForward" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.automaticCarryForward}"  />
               </td>
             </tr>
             <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.applicableProgramRegulations}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].applicableProgramRegulations" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.applicableProgramRegulations}" />
               </td>
               <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.treatmentPrgmIncomeAdditive}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].treatmentPrgmIncomeAdditive" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.treatmentPrgmIncomeAdditive}"  />
               </td>
             </tr>
             <tr>
               <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardTemplateInfoAttributes.applicableProgramRegsDate}" /></div></th>
                <td>
                   <kul:htmlControlAttribute property="document.subAwardList[0].subAwardTemplateInfo[0].applicableProgramRegsDate" readOnly="${readOnly}" attributeEntry="${subAwardTemplateInfoAttributes.applicableProgramRegsDate}"  datePicker="true"  />
                </td>
                <th colspan=2><div/></div></th>
             </tr>   
         		</table>
   			</div>
	</kul:tabTop>
	

