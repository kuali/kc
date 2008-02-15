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
<%@ include file="/WEB-INF/jsp/proposaldevelopment/proposalPerson.jsp"%>

              <!-- <table cellpadding=0 cellspacing="0" summary=""> -->
                <tbody id="G4">
                  <tr>
                    <th width="10%">&nbsp;</th>
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.degreeCode" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.degree" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.graduationYear" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.school" />
                    <th><div align="center">Actions</div></th>
                  <kra-pd:personDegreeLine proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>

    <bean:define id="degrees" name="KualiForm" property="${proposalPerson}.proposalPersonDegrees"/>
    <c:forEach items="${degrees}"  var="degree" varStatus="status">
                  <kra-pd:personDegreeLine proposalPerson="${proposalPerson}" index="${status.index}"  personIndex="${personIndex}"/>
    </c:forEach>

                </tbody>
             <!-- </table> -->
