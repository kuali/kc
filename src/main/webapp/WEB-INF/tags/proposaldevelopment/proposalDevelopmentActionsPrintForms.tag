<%--
 Copyright 2007 The Kuali Foundation.
 
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

<c:set var="s2sFormAttributes" value="${DataDictionary.S2sOppForms.attributes}" />

<table cellpadding=0 cellspacing=0 summary="">
<tr><td>	
	<kul:innerTab parentTab="Print Forms" defaultOpen="false" tabTitle="Print Grants.gov Forms(${fn:length(KualiForm.document.s2sOpportunity.s2sOppForms)})">
	<div class="innerTab-container" align="left">
		 <table class=tab cellpadding=0 cellspacing="0" summary=""> 
		 <tbody id="G1">
		    	<c:forEach var="form" items="${KualiForm.document.s2sOpportunity.s2sOppForms}" varStatus="status">
			            <tr>	                
			                <td width="50">
			                </td>
			                <td align="left" valign="middle">
			                	<kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].formName" attributeEntry="${s2sFormAttributes.formName}" readOnly="true" />
							</td>
			                <td align="center" valign="middle">
			                	<div align="center">
			                	<html:checkbox property="document.s2sOpportunity.s2sOppForms[${status.index}].selectToPrint"/>
			                	<!--   <kul:htmlControlAttribute property="document.s2sOpportunity.s2sOppForms[${status.index}].selectToPrint" attributeEntry="${s2sFormAttributes.selectToPrint}" />-->
			                	</div>
			                </td>			                
			            </tr>    	
		    	</c:forEach>
		    	<tr>
		    		<td>	
			        </td>
			    	<td>
						<div align="center">
							<html:image src="/kra-dev/kr/static/images/tinybutton-printsel.gif"  styleClass="globalbuttons" property="methodToCall.printForms" alt="Print Selected Forms"/>					
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
</td></tr>
</table>
