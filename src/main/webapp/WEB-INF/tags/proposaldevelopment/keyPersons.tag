 <%--
 Copyright 2005-2010 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
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
