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
<%@ include file="/WEB-INF/jsp/proposalPerson.jsp"%>

              <!-- <table cellpadding=0 cellspacing="0" summary=""> -->
                <tbody id="G5">
                  <tr>
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonYesNoQuestion.questionId" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.Ynq.description" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonYesNoQuestion.answer" />
                  </tr>
    <bean:define id="questions" name="KualiForm" property="${proposalPerson}.questions"/>
    <c:forEach items="${questions}" var="question" varStatus="status">
                  <kra-pd:personCertifyLine proposalPerson="${proposalPerson}" index="${status.index}" personIndex="${personIndex}"/>
    </c:forEach>
               </tbody>
             <!-- </table> -->
