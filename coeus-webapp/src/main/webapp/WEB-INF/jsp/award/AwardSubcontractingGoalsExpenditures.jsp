<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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
