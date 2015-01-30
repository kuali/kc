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
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.degreeCode" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.degree" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.graduationYear" />
                    <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalPersonDegree.attributes.school" />
                    <th><div align="center">Actions</div></th>
                    
                  <kra:section permission="modifyProposal">
                  <c:if test="${ not isParent }">
                  	<kra-pd:personDegreeLine proposalPerson="${proposalPerson}"  personIndex="${personIndex}"/>
                  </c:if>
                  </kra:section>

    			  <bean:define id="degrees" name="KualiForm" property="${proposalPerson}.proposalPersonDegrees"/>
				  <c:forEach items="${degrees}"  var="degree" varStatus="status">
				        <kra-pd:personDegreeLine proposalPerson="${proposalPerson}" index="${status.index}"  personIndex="${personIndex}"/>
				  </c:forEach>

                </tbody>
             <!-- </table> -->
