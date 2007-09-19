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

              <table cellpadding=0 cellspacing="0" summary="">
                <tr>
                  <td colspan="15" nowrap class="tab-subhead1"><a href="#" id="A4" onclick="rend(this, false)"><img src="kr/static/images/tinybutton-hide.gif" alt="show/hide this panel" width=45 height=15  border=0 align="absmiddle" id="F4"></a> Degrees</td>

                </tr>
                <tbody id="G4">
                  <tr>
                    <th width="10%">&nbsp;</th>
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.degreeCode" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.degree" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.graduationDate" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.school" />
                  <kra-pd:personDegreeLine proposalPerson="${proposalPerson}"/>

    <bean:define id="degrees" name="KualiForm" property="${proposalPerson}.degrees"/>
    <c:forEach items="${degrees}"  var="degree" varStatus="status">
                  <kra-pd:personDegreeLine proposalPerson="${proposalPerson}" index="${status.index}" />
    </c:forEach>

                </tbody>
              </table>
