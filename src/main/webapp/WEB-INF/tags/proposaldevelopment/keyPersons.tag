 <%--
 Copyright 2006-2009 The Kuali Foundation

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
<div id="workarea">
<c:set var="personIndex" value="0" />
<c:forEach items="${KualiForm.document.proposalPersons}" var="person" varStatus="status">
    <c:set var="proposalPersonProperty" value="document.proposalPersons[${status.index}]" />
    <c:set var="transparent" value="false" />
	
	<c:choose>
		<c:when test="${person.moveUpAllowed}">
    		<c:set var="moveUpBtn" value="<a name='moveUp'><input type='image' name='methodToCall.moveUp.line${status.index}' src='${ConfigProperties.kra.externalizable.images.url}upArrow.png' class='tinybutton' alt='Move ${fn:substring(person.fullName, 0, 22)} Up' title='Move ${fn:substring(person.fullName, 0, 22)} Up' /></a>" />
    	</c:when>
    	<c:otherwise>
    		<c:set var="moveUpBtn" value="" />
    	</c:otherwise>
    </c:choose>
    
    <c:choose>
		<c:when test="${person.moveDownAllowed}">
    		<c:set var="moveDownBtn" value="<a name='moveDown'><input type='image' name='methodToCall.moveDown.line${status.index}' src='${ConfigProperties.kra.externalizable.images.url}downArrow.png' class='tinybutton' alt='Move ${fn:substring(person.fullName, 0, 22)} Down' title='Move ${fn:substring(person.fullName, 0, 22)} Down'/></a>" />
    	</c:when>
    	<c:otherwise>
    		<c:set var="moveDownBtn" value="" />
    	</c:otherwise>
    </c:choose>

    <c:if test="${status.first}">
      <c:set var="transparent" value="true" />
    </c:if> 

	<c:set var="extraButtonSource" value="${moveUpBtn} ${moveDownBtn}" />

	<kul:checkErrors keyMatch="document.proposalPersons[${status.index}]*,newProposalPersonDegree[${status.index}]*" auditMatch="document.proposalPersons[${status.index}]*"/>
	<c:set var="isOpen" value="${hasErrors ? true : isOpen}"/>
	<c:choose>
		<c:when test="${KualiForm.document.nih}">
			<c:set var="nihdescription" value="${KualiForm.document.nihDescription}" />
			<c:set var="desc" value="${nihdescription[person.role.proposalPersonRoleId]}" />
			
			<kul:tab tabTitle="${fn:substring(person.fullName, 0, 22)}"
		         tabDescription="${desc}${extraButtonSource}"
		         leftSideHtmlProperty="${proposalPersonProperty}.delete" 
		         leftSideHtmlAttribute="${proposalPersonAttributes.delete}" 
		     	 leftSideHtmlDisabled="false"
		         defaultOpen="${hasErrors}" 
		         transparentBackground="${transparent}"> 
		         <kra-pd:person proposalPerson="${proposalPersonProperty}" personIndex="${status.index}"/>
		     </kul:tab>
	     </c:when>
     	<c:otherwise>
     	<c:set var="descri" value="${person.role.description}" />
			<kul:tab tabTitle="${fn:substring(person.fullName, 0, 22)}"
			         tabDescription="${descri}${extraButtonSource}"
			         leftSideHtmlProperty="${proposalPersonProperty}.delete" 
			         leftSideHtmlAttribute="${proposalPersonAttributes.delete}" 
			     	 leftSideHtmlDisabled="false" 
			          defaultOpen="${hasErrors}" 
			         transparentBackground="${transparent}"> 
			   
			    <kra-pd:person proposalPerson="${proposalPersonProperty}" personIndex="${status.index}"/>
			</kul:tab>
		</c:otherwise>
      </c:choose>
 </c:forEach>

<c:if test="${not empty KualiForm.creditSplitEnabled and KualiForm.creditSplitEnabled}">
    <kra-pd:creditSplit/>
</c:if>

<c:if test="${fn:length(KualiForm.document.proposalPersons) > 0}">
    <kul:panelFooter />
</c:if>

</div>
