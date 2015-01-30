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
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal']}" scope="request" /> 
<div id="workarea">
<c:set var="personIndex" value="0" />
<c:forEach items="${KualiForm.document.developmentProposalList[0].proposalPersons}" var="person" varStatus="status">
    <c:set var="proposalPersonProperty" value="document.developmentProposalList[0].proposalPersons[${status.index}]" />
    <c:if test="${not readOnly}">
        <c:set var="leftSideHtmlProperty" value="${proposalPersonProperty}.delete" />
    </c:if>
    <c:set var="transparent" value="false" />
	
	<c:choose>
		<c:when test="${person.moveUpAllowed}">
			<c:set var="mtc" value="methodToCall.moveUp.line${status.index}" />
    		<c:set var="moveUpBtn" value="<a name='moveUp'><input type='image' name='${ mtc }' src='${ConfigProperties.kra.externalizable.images.url}upArrow.png' class='tinybutton' alt='Move ${fn:substring(person.fullName, 0, 22)} Up' title='Move ${fn:substring(person.fullName, 0, 22)} Up' /></a>" />
    		${kfunc:registerEditableProperty(KualiForm, mtc)}
    	</c:when>
    	<c:otherwise>
    		<c:set var="moveUpBtn" value="" />
    	</c:otherwise>
    </c:choose>
    
    <c:choose>
		<c:when test="${person.moveDownAllowed}">
			<c:set var="mtc" value="methodToCall.moveDown.line${status.index}" />
    		<c:set var="moveDownBtn" value="<a name='moveDown'><input type='image' name='${ mtc }' src='${ConfigProperties.kra.externalizable.images.url}downArrow.png' class='tinybutton' alt='Move ${fn:substring(person.fullName, 0, 22)} Down' title='Move ${fn:substring(person.fullName, 0, 22)} Down'/></a>" />
    		${kfunc:registerEditableProperty(KualiForm, mtc)}
    	</c:when>
    	<c:otherwise>
    		<c:set var="moveDownBtn" value="" />
    	</c:otherwise>
    </c:choose>

    <c:if test="${status.first}">
      <c:set var="transparent" value="true" />
    </c:if> 

	<c:set var="extraButtonSource" value="${moveUpBtn} ${moveDownBtn}" />

	<kul:checkErrors 
		keyMatch="document.developmentProposalList[0].proposalPersons[${status.index}]*,newProposalPersonDegree[${status.index}]*,proposalPersonQuestionnaireHelpers[${status.index}].answerHeaders[0].answers[0].answer" 
		auditMatch="document.developmentProposalList[0].proposalPersons[${status.index}]*"/>
	<c:set var="isOpen" value="${hasErrors ? true : isOpen}"/>
			
	<kul:tab tabTitle="${fn:substring(person.fullName, 0, 22)}"
         tabDescription="${person.investigatorRoleDescription}${extraButtonSource}"
         leftSideHtmlProperty="${leftSideHtmlProperty}" 
         leftSideHtmlAttribute="${proposalPersonAttributes.delete}" 
     	 leftSideHtmlDisabled="false"
         defaultOpen="${hasErrors}" 
         transparentBackground="${transparent}"
         useCurrentTabIndexAsKey="${true }"> 
         <kra-pd:person proposalPerson="${proposalPersonProperty}" personIndex="${status.index}"/>
     </kul:tab>
 </c:forEach>

<c:if test="${not empty KualiForm.creditSplitEnabled and KualiForm.creditSplitEnabled}">
    <kra-pd:creditSplit/>
</c:if>

<c:if test="${fn:length(KualiForm.document.developmentProposalList[0].proposalPersons) > 0}">
    <kul:panelFooter />
</c:if>

</div>
