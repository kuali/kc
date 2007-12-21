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

<c:set var="proposalYnqAttributes" value="${DataDictionary.ProposalYnq.attributes}" />
<c:set var="questionIdAttribute" value="${DataDictionary.ProposalPersonYnq.attributes.questionId}" />
<c:set var="questionAttribute" value="${DataDictionary.Ynq.attributes.description}" />

<c:set var="questionIdAttribute" value="${DataDictionary.ProposalYnq.attributes.questionId}" />
<c:set var="noOfAnswersAttibute" value="${DataDictionary.Ynq.attributes.noOfAnswers}" />
<c:set var="answerAttribute" value="${DataDictionary.ProposalYnq.attributes.answer}" />
<c:set var="dummyAnswerAttribute" value="${DataDictionary.ProposalYnq.attributes.dummyAnswer}" />
<c:set var="reviewDateAttribute" value="${DataDictionary.ProposalYnq.attributes.reviewDate}" />
<c:set var="explanationAttribute" value="${DataDictionary.ProposalYnq.attributes.explanation}" />
<c:set var="previousGroupName" value="" />
<c:set var="action" value="proposalDevelopmentQuestions" />

<c:set var="answerYesNo" value="${KualiForm.answerYesNo}" /> 
<c:set var="answerYesNoNa" value="${KualiForm.answerYesNoNA}" /> 
<div id="workarea">
<c:forEach items="${KualiForm.document.ynqGroupNames}" var="groups" varStatus="gps">
<bean:define id="groupName" name="KualiForm" property="document.ynqGroupNames[${gps.index}].groupName"/>
    <c:if test="${gps.first}">
      <c:set var="transparent" value="true" />
    </c:if> 
<bean:define id="trunGroupName" name="KualiForm" property="document.ynqGroupNames[${gps.index}].truncGroupName"/>
<kul:tab tabTitle="${trunGroupName}" defaultOpen="false" tabErrorKey="document.proposalYnq[${groupName}]*" transparentBackground="${transparent}">
<c:set var="tabErrorKey" value="document.proposalYnq[${gps.index}]"/>
    <c:set var="proposalYnq" value="document.proposalYnqs[${gps.index}]" /> 
    <c:set var="transparent" value="false" />
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<h2><span class="subhead-left">${trunGroupName}</span></h2>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
    	<div align="left" style="padding:12px;">
    		<strong>Full Group Name:</strong> 
        	<bean:write name="KualiForm" property="${proposalYnq}.ynq.groupName"/>
        </div>
        <table cellpadding=0 cellspacing="0"  class="result-table" summary="">
            <th width="10%">&nbsp;</th>
            <kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.Ynq.attributes.description" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalYnq.attributes.answer" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalYnq.attributes.reviewDate" />
	    	<kul:htmlAttributeHeaderCell attributeEntryName="DataDictionary.ProposalYnq.attributes.explanation" />
            <th width="10%" class="infoline">Actions</th>
			<c:set var="rowIndex" value="1" />
			<c:forEach items="${KualiForm.document.proposalYnqs}" var="ynqs" varStatus="status">
    			  <c:set var="iproposalYnq" value="document.proposalYnq[${status.index}]" /> 
				  <bean:define id="igroupName" name="KualiForm" property="${iproposalYnq}.ynq.groupName"/>
    			  <c:if test="${igroupName == groupName}">
                  <tr>
                    <th scope="row" align="center">${rowIndex}</th>
                    <td width="50%" class="${tdClass}"><div align=left><span class="copy">
                    <bean:write name="KualiForm" property="${iproposalYnq}.ynq.description"/>
                    <span class="fineprint"><br><br>
                    <bean:write name="KualiForm" property="${iproposalYnq}.reviewDateRequiredDescription"/>
                    <br>
                    <bean:write name="KualiForm" property="${iproposalYnq}.explanationRequiredDescription"/>
                     </span> </span></div>
                        <span class="fineprint"></span> </td>
					<bean:define id="noOfAnswers" name="KualiForm" property="${iproposalYnq}.ynq.noOfAnswers" />
                    <td width="15%" class="${tdClass}"><div align=left><span class="copy">
					<c:choose>
					<c:when test="${noOfAnswers == answerYesNo}">
	                    <kul:htmlControlAttribute property="${iproposalYnq}.answer" attributeEntry="${answerAttribute}" /> 
					</c:when>
					<c:when test="${noOfAnswers == answerYesNoNa}">
	                    <kul:htmlControlAttribute property="${iproposalYnq}.answer" attributeEntry="${dummyAnswerAttribute}" /> 
					</c:when>
					</c:choose>
                      </span></div>
                        <span class="fineprint"></span> </td>
                    <td width="10%" class="${tdClass}"><div align=left><span class="copy">
					<bean:define id="dateRequired" name="KualiForm" property="${iproposalYnq}.reviewDateRequired" />
					<c:choose>
					<c:when test="${dateRequired == 'No'}">
                    	<kul:htmlControlAttribute property="${iproposalYnq}.reviewDate" attributeEntry="${reviewDateAttribute}" disabled="true"/>
      					<img class="nobord" src='${ConfigProperties.kra.externalizable.images.url}cal1.gif' />
					</c:when>
					<c:when test="${dateRequired == 'Yes'}">
						<c:set var="styleClass" value=""/>
						<kul:checkErrors keyMatch="document.proposalYnq[${groupName}][${status.index}].reviewDate"/>
	                	<c:if test="${hasErrors}">
	                    	<c:set var="styleClass" value="errorField"/>
	                	</c:if>
                    	<kul:htmlControlAttribute property="${iproposalYnq}.reviewDate" attributeEntry="${reviewDateAttribute}" datePicker="true" styleClass="${styleClass}" />
					</c:when>
					</c:choose>
                      </span></div>
                        <span class="fineprint"></span> </td>
					<bean:define id="explanationRequired" name="KualiForm" property="${iproposalYnq}.explanationRequried" />
      				<c:set var="disableExplanationRequired" value="false" />
    				<c:if test="${explanationRequired == 'No'}">
      					<c:set var="disableExplanationRequired" value="true" />
    				</c:if> 
					<c:set var="styleClass" value=""/>
					<kul:checkErrors keyMatch="document.proposalYnq[${groupName}][${status.index}].explanation"/>
                	<c:if test="${hasErrors}">
                    	<c:set var="styleClass" value="errorField"/>
                	</c:if>
					<c:set var="textAreaFieldName" value="${iproposalYnq}.explanation" />
                    <td width="15%" class="${tdClass}"><div align=left><span class="copy">
                    <kul:htmlControlAttribute property="${iproposalYnq}.explanation" attributeEntry="${explanationAttribute}" disabled="${disableExplanationRequired}" styleClass="${styleClass}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${DataDictionary.ProposalYnq.attributes.explanation.label}" disabled="${disableExplanationRequired}" />
                      </span></div>
                        <span class="fineprint"></span> </td>
                    <td width="10%" class="${tdClass}"><div align=center><span class="copy">
            			<!-- 
            			<kul:directInquiry boClassName="org.kuali.kra.bo.Ynq" inquiryParameters="${iproposalYnq}.questionId:questionId" anchor="${tabKey}"/>
            			 -->
							<bean:define id= "questionId" name="KualiForm" property="${iproposalYnq}.questionId" />
							<kul:inquiry boClassName="org.kuali.kra.bo.Ynq" keyValues="questionId=${questionId}" render="true">
								<image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' alt="inquiry"/>
							</kul:inquiry>
                      </span></div>
                        <span class="fineprint"></span> </td>
                  </tr>
				  <c:set var="rowIndex" value="${rowIndex+1}" />
                  </c:if>
		</c:forEach>
    </table>
    </div>    
</kul:tab>
</c:forEach>
<kul:panelFooter />
</div>
