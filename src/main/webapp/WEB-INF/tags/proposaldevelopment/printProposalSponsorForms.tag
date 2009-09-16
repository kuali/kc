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

<div align="center">
<table cellpadding="0" cellspacing="0" summary="">
<tr>
<td>
<kra:innerTab parentTab="Print Forms" tabTitle="${KualiForm.proposalFormTabTitle}" defaultOpen="false" tabErrorKey="">
	<div class="innerTab-container" align="center" >
        <table align="right" cellpadding="0" cellspacing="0" summary="">
        <tbody>    
		    	<c:forEach var="form" items="${KualiForm.sponsorFormTemplates}" varStatus="status">
		            <tr>	                
		                <td width="50">
		                </td>
		                <td align="left" valign="middle">
		                	<c:out value="${KualiForm.sponsorFormTemplates[status.index].sponsorForms.packageName}"/>
						</td>
		                <td align="left" valign="middle">
		                	<c:out value="${KualiForm.sponsorFormTemplates[status.index].pageDescription}"/>
						</td>
		                <td align="center" valign="middle">
		                	<div align="center">
                            	<html:checkbox property="sponsorFormTemplates[${status.index}].selectToPrint"/>                         
		                	</div>
		                </td>			       
		            </tr>    	
		    	</c:forEach>		    	
			    	<tr>
			    		<td>	
				        </td>
			    		<td class="infoline">	
		                &nbsp;
				        </td>
				    	<td class="infoline">
							<div align="center">
								<html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif" styleClass="tinybutton" property="methodToCall.printSponsorForms" alt="Print Selected Forms" onclick="excludeSubmitRestriction=true"/>
							</div>	    	
				    	</td>			
						<td class="infoline">
							<div align="center">
							Select (<html:link href="#" onclick="javascript:selectAllSponsorForms(document);return false">all</html:link> | <html:link href="#" onclick="javascript:unselectAllSponsorForms(document);return false">none</html:link>)
							</div>						
						</td>
			    	</tr>
		    	
			</tbody>
        </table>
	</div>
</kra:innerTab>
</td>
</tr>
</table>
</div>  
