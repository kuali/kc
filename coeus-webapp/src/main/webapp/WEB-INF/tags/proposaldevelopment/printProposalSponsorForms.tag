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

<div align="center">
<table cellpadding="0" cellspacing="0" summary="">
<tr>
<td>
<kul:innerTab parentTab="Print Forms" tabTitle="${KualiForm.proposalFormTabTitle}" defaultOpen="false" tabErrorKey="">
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
</kul:innerTab>
</td>
</tr>
</table>
</div>  
