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

<c:set var="s2sFormAttributes" value="${DataDictionary.S2sOppForms.attributes}" />

<table cellpadding=0 cellspacing=0 summary="">
<tr><td>

	<c:set var="noOfForms" value="" />
	<c:set var="availForms" value="" />
	<c:if test="${!empty KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms && fn:length(KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms)>0}" >
		<c:forEach var="form" items="${KualiForm.document.developmentProposalList[0].s2sOpportunity.s2sOppForms}" varStatus="status">
		     <c:if test="${form.available == 'true'}">
		   		<c:set var="availForms" value="${availForms + 1}"/>
		   	</c:if>
		</c:forEach>
		<c:if test="${availForms > 0}">
			<c:set var="noOfForms" value=" (${availForms})" />
		</c:if>
    </c:if>
	<c:choose>				
	<c:when test="${not empty noOfForms}" >	
	<kul:innerTab parentTab="Print Forms" defaultOpen="false" tabTitle="Print Grants.gov Forms${noOfForms}">
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
			                	<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sOpportunity.s2sOppForms[${status.index}].selectToPrint" attributeEntry="${s2sFormAttributes.selectToPrint}" />			                	
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
