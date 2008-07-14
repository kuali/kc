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
								<html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif"  styleClass="globalbuttons" property="methodToCall.printSponsorForms" alt="Print Selected Forms"/>
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
