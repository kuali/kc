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

 <%@ attribute name="proposalPerson" description="The ProposalPerson which this is for." required="true" %>
 <%@ attribute name="index" description="Index of the property for a ProposalPerson" required="false" %>
 <%@ attribute name="personIndex" description="Index of a ProposalPerson" required="true" %>

 <c:set var="proposalPersonAttributes" value="${DataDictionary.ProposalPerson.attributes}" />
 <c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" />
 <c:set var="isParent" value="${KualiForm.document.developmentProposalList[0].parent}" />

 <!-- <table cellpadding=0 cellspacing="0" summary=""> -->
                <tbody id="G4">
                  <tr>
                    <th width="10%">&nbsp;</th>
                    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.Ynq.attributes.description" />
                    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonYnq.attributes.answer" />
	<bean:define id="proposalPersonYnqs" name="KualiForm" property="${proposalPerson}.proposalPersonYnqs" />
    <c:set var="questionIdAttribute" value="${DataDictionary.ProposalPersonYnq.attributes.questionId}" />
    <c:set var="questionAttribute" value="${DataDictionary.Ynq.attributes.description}" />
    
    <c:set var="noOfAnswersAttibute" value="${DataDictionary.Ynq.attributes.noOfAnswers}" />
    <c:set var="answerAttribute" value="${DataDictionary.ProposalPersonYnq.attributes.answer}" />
    <c:set var="dummyAnswerAttribute" value="${DataDictionary.ProposalPersonYnq.attributes.dummyAnswer}" />
	<c:set var="answerYesNo" value="${KualiForm.answerYesNo}" /> 
	<c:set var="answerYesNoNa" value="${KualiForm.answerYesNoNA}" /> 
    <c:forEach items="${proposalPersonYnqs}"  var="ynqs" varStatus="status">
  		<!--  <c:set var="ynqs" value="${proposalPerson}.proposalPersonYnqs[${status.index}]" /> --> 
                  <tr>
                    <th scope="row" align="center">${status.index+1}</th>

                    <td width="70%" class="${tdClass}"><div align=left><span class="copy">
                    <bean:write name="KualiForm" property="${proposalPerson}.proposalPersonYnqs[${status.index}].ynq.description"/>
                    </span></div>
                    <span class="fineprint"></span> </td>
					<bean:define id="noOfAnswers" name="KualiForm" property="${proposalPerson}.proposalPersonYnqs[${status.index}].ynq.noOfAnswers" />
                    <td width="20%" class="${tdClass}"><div align=left><span class="copy">
					
							<c:choose>
	                            <c:when test="${noOfAnswers == answerYesNo}">
	                                <kul:htmlControlAttribute readOnly="true" property="${proposalPerson}.proposalPersonYnqs[${status.index}].answer" attributeEntry="${answerAttribute}" /> 
	                            </c:when>
	                            <c:when test="${noOfAnswers == answerYesNoNa}">
	                                <kul:htmlControlAttribute readOnly="true" property="${proposalPerson}.proposalPersonYnqs[${status.index}].answer" attributeEntry="${dummyAnswerAttribute}" /> 
	                            </c:when>
	                        </c:choose>
					
                    </span></div>
                    <span class="fineprint"></span> </td>
                  </tr>
    </c:forEach>
					<tr>
						<th colspan="3" align="center">
							<html:image property="methodToCall.printCertification.${proposalPerson}.line${personIndex}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif" title="Print Certification" alt="Print Certification" styleClass="tinybutton" onclick="excludeSubmitRestriction=true" />
						</th>
					</tr>
                </tbody>
