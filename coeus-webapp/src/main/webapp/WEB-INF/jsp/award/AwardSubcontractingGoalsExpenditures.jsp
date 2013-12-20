<%--
 Copyright 2005-2013 The Kuali Foundation

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

<c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request" />


<kul:page lookup="false" 
          docTitle="Award Subcontracting Goals and Expenditures" 
          transactionalDocument="false"
          renderMultipart="false" 
          htmlFormAction="awardSubcontractingGoalsExpenditures">

 <div id="workarea">

	<kul:tab tabTitle="Subcontracting Goals"
	         defaultOpen="true"
	         alwaysOpen="true"
	         transparentBackground="true" 
	         useCurrentTabIndexAsKey="true"
	         tabErrorKey="awardSubcontractingBudgetedGoals.*, awardNumber*">
	             
			<kra-a:awardSubcontractingAwardNumberLookup />
		    
		    <c:if test="${KualiForm.displayGoalsExpendituresDetails}">
			    <kra-a:awardSubcontractingGoalsExpenses />
		    </c:if>    
	</kul:tab>

	<kul:panelFooter />
	
	<div id="globalbuttons" class="globalbuttons">
		<c:if test="${KualiForm.displayGoalsExpendituresDetails}">
	    	<c:if test="${!readOnly}">
	        	<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.save" styleId="save" title="save" alt="save"/>
	    	</c:if>
	    	<c:choose>
    	       	<c:when test="${KualiForm.awardSubcontractingBudgetedGoals.fresh}">
    	      		<c:if test="${!readOnly}">
	        			<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_clear.gif" styleClass="globalbuttons" property="methodToCall.clear" title="clear" alt="clear" onclick="excludeSubmitRestriction=true"/>
	    			</c:if>
	    	    </c:when>		
				<c:otherwise>    	       	       
	    			<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_reload.gif" styleClass="globalbuttons" property="methodToCall.reload" title="reload" alt="reload" onclick="excludeSubmitRestriction=true"/>
	    		</c:otherwise>
	    	</c:choose>
	    </c:if>
	    
	    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" styleClass="globalbuttons" property="methodToCall.close" title="close" alt="close" onclick="excludeSubmitRestriction=true"/>
	    
	</div>
	<hr>
	
 </div>	

</kul:page>
