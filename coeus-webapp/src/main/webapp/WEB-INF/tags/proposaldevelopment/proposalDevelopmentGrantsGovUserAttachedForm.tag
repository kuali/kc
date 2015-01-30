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

<c:set var="s2sUserAttachedFormAttributes" value="${DataDictionary.S2sUserAttachedForm.attributes}" />
<c:set var="s2sUserAttachedFormAttAttributes" value="${DataDictionary.S2sUserAttachedFormAtt.attributes}" />
<kul:innerTab parentTab="Opportunity Search" defaultOpen="${KualiForm.showSubmissionDetails}" tabTitle="User Attached Forms" tabErrorKey="userAttachedFormsErrors">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> S2s User Attached Form</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.s2s.bo.S2sUserAttachedForm" altText="help"/></span>
        </h3>
        
        <table cellpadding="0" cellspacing="0" summary="">
          	<%-- Header --%>
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" width="5%"/> 
          		<kul:htmlAttributeHeaderCell attributeEntry="${s2sUserAttachedFormAttributes.description}" scope="col" nowrap="false" width="15%"/>
          		<kul:htmlAttributeHeaderCell attributeEntry="${s2sUserAttachedFormAttributes.namespace}" scope="col"  nowrap="false" width="25%"/>
          		<kul:htmlAttributeHeaderCell attributeEntry="${s2sUserAttachedFormAttributes.formName}" scope="col"  nowrap="false" width="15%"/>
          		<kul:htmlAttributeHeaderCell attributeEntry="${s2sUserAttachedFormAttributes.formFileName}" scope="col"  nowrap="false" width="15%"/>
          		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"  nowrap="false" width="15%"/>
          	</tr> 
          	<%-- Header --%>
          	
             <%-- New data --%>
             <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>
                 <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newS2sUserAttachedForm.description" attributeEntry="${s2sUserAttachedFormAttributes.description}" />
                	</div>
				</td>
                <td align="left" valign="middle" class="infoline">&nbsp;</td>
                <td align="left" valign="middle" class="infoline">&nbsp;</td>
				<td valign="middle" class="infoline">
                	<div align="center">	                		                	
                		<html:file property="newS2sUserAttachedForm.newFormFile" />
                	</div>
				</td>
<%--                 <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="newS2sUserAttachedForm.xmlFile" attributeEntry="${s2sUserAttachedFormAttributes.xmlFile}" />
                	</div>
				</td>
 --%>
				<td class="infoline">
					<div align="center">
						<html:image property="methodToCall.addS2sUserAttachedForm.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
            </tr>
            <%-- New data --%>
            
            <%-- Existing data --%>
        	<c:forEach var="s2sUserAttachedForm" items="${KualiForm.document.developmentProposalList[0].s2sUserAttachedForms}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sUserAttachedForms[${status.index}].description" attributeEntry="${s2sUserAttachedFormAttributes.description}" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sUserAttachedForms[${status.index}].namespace" attributeEntry="${s2sUserAttachedFormAttributes.namespace}"  readOnly="true" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sUserAttachedForms[${status.index}].formName" attributeEntry="${s2sUserAttachedFormAttributes.formName}"  readOnly="true" />
					</div>
				  </td>
                  <td align="left" valign="middle">
                  
                  	<div align="center">
	                		<div style="display: ${(not empty KualiForm.document.developmentProposalList[0].s2sUserAttachedForms[status.index].formFileName) ? 'block' : 'none'}">
	                			<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sUserAttachedForms[${status.index}].formFileName" attributeEntry="${s2sUserAttachedFormAttributes.formFileName}" readOnly="true"/>
	                		</div>
	                	</div>
				  </td>
                  <td align="left" valign="middle">
					<div align=center>
							<html:image property="methodToCall.viewUserAttachedFormXML.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewxml.gif' styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/>
					        <html:image property="methodToCall.viewUserAttachedFormPdf.line${status.index}.anchor${currentTabIndex}"
                                  src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewpdf.gif' styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/>
							<html:image property="methodToCall.deleteUserAttachedForm.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
				  </td>
	            </tr>
            	<c:if test="${not empty KualiForm.document.developmentProposalList[0].s2sUserAttachedForms[status.index].s2sUserAttachedFormAtts}">
	            <tr>
			  		<td>&nbsp;</td>
			  		<th>Attachments:</th>
			  		<td colspan="4">
				  	<c:forEach var="s2sUserAttachedFormAtt" items="${KualiForm.document.developmentProposalList[0].s2sUserAttachedForms[status.index].s2sUserAttachedFormAtts}" varStatus="statusAtt">
				  		<table cellpadding="0" cellspacing="0">
					  	<tr>
						  	<td align="left" valign="middle" >
								<div align="left">
			                		<kul:htmlControlAttribute property="document.developmentProposalList[0].s2sUserAttachedForms[${status.index}].s2sUserAttachedFormAtts[${statusAtt.index}].fileName" attributeEntry="${s2sUserAttachedFormAttAttributes.fileName}" readOnly="true" />
			                		<html:image property="methodToCall.viewUserAttachedFormAttachment.line${status.index}.attIndex${statusAtt.index}.anchor${currentTabIndex}"
                                   src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/>
								</div>
						  	</td>
					  	</tr>
					  	</table> 
					</c:forEach>
					</td>
		  		</c:if>
        	</c:forEach> 
            <%-- Existing data --%>
        </table>

    </div>
</kul:innerTab>
