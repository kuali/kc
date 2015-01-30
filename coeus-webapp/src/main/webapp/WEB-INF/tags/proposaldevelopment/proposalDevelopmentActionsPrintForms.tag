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

<c:set var="s2sFormAttributes" value="${DataDictionary.S2sOppForms.attributes}" />

<table cellpadding=0 cellspacing=0 summary="">
<tr><td>

	<c:set var="noOfForms" value="0" />
	<c:set var="availForms" value="0" />
	<c:if test="${!empty KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms && fn:length(KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms)>0}" >
		<c:forEach var="form" items="${KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms}" varStatus="status">
		     <c:if test="${form.available == 'true'}">
		   		<c:set var="availForms" value="${availForms + 1}"/>
		   	</c:if>
		</c:forEach>
		<c:if test="${availForms > 0}">
			<c:set var="noOfForms" value="${availForms}" />
		</c:if>
    </c:if>
	<c:choose>				
	<c:when test="${not empty noOfForms}" >	
	<kul:innerTab parentTab="Print Forms" defaultOpen="false" tabTitle="Print Grants.gov Forms (${noOfForms})">
	<div class="innerTab-container" align="left">
		 <table class=tab cellpadding=0 cellspacing="0" summary=""> 
		 <tbody id="G1">
		    	<c:forEach var="form" items="${KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms}" varStatus="status">
		    		<c:if test="${form.available == 'true'}">
			            <tr>	                
			                <td width="50">
			                </td>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].formName" attributeEntry="${s2sFormAttributes.formName}" readOnly="true" />
							</td>
			                <td align="center" valign="middle">
			                	<div align="center">			                	
			                	<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].selectToPrint" attributeEntry="${s2sFormAttributes.selectToPrint}" readOnly="false"/>			                	
			                	</div>
			                </td>			                
			            </tr>    
			         </c:if>   	
		    	</c:forEach>		    	
			    	<tr>
			    		<td>	
				        </td>
				    	<td>
							<div align="center">
								<html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif"  styleClass="globalbuttons" property="methodToCall.printForms" alt="Print Selected Forms" onclick="excludeSubmitRestriction=true"/>
							</div>	    	
				    	</td>			
						<td>
							<div align="center">
							Select (<html:link href="#" onclick="javascript: selectAllGGForms(document);return false">all</html:link> | <html:link href="#" onclick="javascript: unselectAllGGForms(document);return false">none</html:link>)
							</div>						
						</td>
			    	</tr>
		    	
			   </tbody>
		</table></div>    
	</kul:innerTab>
	</c:when>
	<c:when test="${empty KualiForm.document.developmentProposalList[0].s2sOpportunity}" >
		No Grants.gov opportunity has been selected 	
	</c:when>
	<c:when test="${empty KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms}" >
		No forms are currently available for the Grants.gov opportunity selected. 
	</c:when>	
	</c:choose>
</td></tr>
</table>
