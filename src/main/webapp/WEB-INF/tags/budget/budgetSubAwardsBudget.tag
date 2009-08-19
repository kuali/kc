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
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyBudgets']}" scope="request" />
<c:set var="budgetSubAwardsAttributes" value="${DataDictionary.BudgetSubAwards.attributes}" />

<kul:tab tabTitle="Sub Award Budget" defaultOpen="false" tabErrorKey="budgetSubAwards.*,newSubAward.*">
 <div class="tab-container" align="center">
     <h3>Sub Award Budget</h3>
     <div align="center">
     	<table border="0" cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th width="5%"><div align="center">&nbsp</div></th> 
          		<th width="20%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.organizationName}" noColon="true" /></div></th>
          		<th width="23%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.comments}" noColon="true" /></div></th>
          		<th width="23%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.subAwardXfdFileName}" noColon="true" /></div></th>          		
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</tr>
			<c:if test="${!readOnly}" >
          	<tr>
					<th class="infoline">
						<c:out value="Add:" />
					</th>
					<td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newSubAward.organizationName" attributeEntry="${budgetSubAwardsAttributes.organizationName}" />
	                	<kul:lookup boClassName="org.kuali.kra.bo.Organization" fieldConversions="organizationName:newSubAward.organizationName" anchor="${tabKey}" lookupParameters="newSubAward.organizationName:organizationName"/>	                	                	
	                	<kul:directInquiry boClassName="org.kuali.kra.bo.Organization" inquiryParameters="newSubAward.organizationName:organizationName" anchor="${tabKey}"/>
	                	</div>
					</td>
					<td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newSubAward.comments" attributeEntry="${budgetSubAwardsAttributes.comments}" />
	                	</div>
					</td>
	                <td valign="middle" class="infoline">
	                	<div align="center">	                		                	
	                	<html:file property="subAwardFile" />						
	                	</div>
					</td>
					<td class="infoline">
						<div align=center>
							<html:image property="methodToCall.translateXFD.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-extractxml.gif' styleClass="tinybutton"/>
						</div>
	                </td>
            </tr>
			</c:if>
			
			<c:forEach var="budgetSubAwards" items="${KualiForm.document.budget.budgetSubAwards}" varStatus="status">
		    	<tr>
    				<th width="5%" rowspan=2 class="infoline">
						<c:out value="${status.index + 1}" />
					</th>
					<td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].organizationName" attributeEntry="${budgetSubAwardsAttributes.organizationName}" readOnly="true"/>
	                	<%-- <kul:lookup boClassName="org.kuali.kra.bo.Organization" fieldConversions="organizationName:document.budgetSubAwards[${status.index}].organizationName" anchor="${tabKey}" lookupParameters="newSubAward.organizationName:organizationName"/>	                	                	
	                	<kul:directInquiry boClassName="org.kuali.kra.bo.Organization" inquiryParameters="document.budget.budgetSubAwards[${status.index}].organizationName:organizationName" anchor="${tabKey}"/>
	                	--%>
	                	</div>
					</td>
					<td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].comments" attributeEntry="${budgetSubAwardsAttributes.comments}" readOnly="true"/>
	                	</div>
					</td>
	                <td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].subAwardXfdFileName" attributeEntry="${budgetSubAwardsAttributes.subAwardXfdFileName}" readOnly="true"/>	                		                	
	                	<%--<html:file property="document.budget.budgetSubAwards[${status.index}].subAwardXfdFile" />--%>						
	                	</div>
					</td>
					<td valign="middle" class="infoline">
						<div align=center>
							<html:image property="methodToCall.viewXFD.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewpdf.gif' styleClass="tinybutton"/>
							<html:image property="methodToCall.viewXML.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewxml.gif' styleClass="tinybutton"/>
							<html:image property="methodToCall.delete.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						</div>
	                </td>
	             </tr>
	             <tr>	             	
	        		<td colspan = "4">
	             	<kul:innerTab parentTab="budgetSubAwards" defaultOpen="false" tabTitle="Details" tabErrorKey="" useCurrentTabIndexAsKey="true">
	             		<div>
    						<table cellpadding=0 cellspacing=0 summary="">
    							<tr>		        					
					        		<th width="25%"><div align="right">Attachments:</div></th>
					        		<td><div align="left"><bean:write name="budgetSubAwards" property="attachmentContentIds" filter="false" /></div></td>
			        			</tr>
			        			<tr>		        					
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.xfdUpdateTimestamp}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].xfdUpdateTimestamp" attributeEntry="${budgetSubAwardsAttributes.xfdUpdateTimestamp}" readOnly="true"/></div></td>
			        			</tr>
			        			<tr>		        					
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.xmlUpdateTimestamp}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].xmlUpdateTimestamp" attributeEntry="${budgetSubAwardsAttributes.xmlUpdateTimestamp}" readOnly="true"/></div></td>
			        			</tr>
			        			<tr>		        					
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.subAwardStatusCode}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].subAwardStatusCode" attributeEntry="${budgetSubAwardsAttributes.subAwardStatusCode}" readOnly="true"/></div></td>
			        			</tr>
			        		</table>
			        	</div>		
	             	</kul:innerTab>
	             	</td>
	             </tr>   						 		
			</c:forEach>
			    	

          </table>
       </div>                  
   </div>
</kul:tab>
