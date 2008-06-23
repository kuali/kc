<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="budgetSubAwardsAttributes" value="${DataDictionary.BudgetSubAwards.attributes}" />

<kul:tab tabTitle="Sub Award Budget" defaultOpen="true" tabErrorKey="budgetJustificationWrapper.*">
 <div class="tab-container" align="center">
     <div class="h2-container">
         <span class="subhead-left"><h2>Sub Award Budget</h2></span>
         <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.budget.bo.BudgetSubAwards" altText="help"/></span>
     </div>
     <div align="center">
     	<table border="0" cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th width="5%"><div align="center">&nbsp</div></th> 
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.organizationName}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.comments}" noColon="true" /></div></th>
          		<th width="10%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.subAwardXfdFileName}" noColon="true" /></div></th>          		
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</tr>
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
	                	<html:file property="newSubAward.subAwardXfdFile" />						
	                	</div>
					</td>
					<td class="infoline">
						<div align=center>
							<html:image property="methodToCall.translateXFD.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-translatexfd.gif' />
						</div>
	                </td>
            </tr>
			
			<c:forEach var="budgetSubAwards" items="${KualiForm.document.budgetSubAwards}" varStatus="status">
		    	<tr>
    				<th width="5%" rowspan=2 class="infoline">
						<c:out value="${status.index + 1}" />
					</th>
					<td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.budgetSubAwards[${status.index}].organizationName" attributeEntry="${budgetSubAwardsAttributes.organizationName}" readOnly="true"/>
	                	<kul:lookup boClassName="org.kuali.kra.bo.Organization" fieldConversions="organizationName:document.budgetSubAwards[${status.index}].organizationName" anchor="${tabKey}" lookupParameters="newSubAward.organizationName:organizationName"/>	                	                	
	                	<kul:directInquiry boClassName="org.kuali.kra.bo.Organization" inquiryParameters="document.budgetSubAwards[${status.index}].organizationName:organizationName" anchor="${tabKey}"/>
	                	</div>
					</td>
					<td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.budgetSubAwards[${status.index}].comments" attributeEntry="${budgetSubAwardsAttributes.comments}" readOnly="true"/>
	                	</div>
					</td>
	                <td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.budgetSubAwards[${status.index}].subAwardXfdFileName" attributeEntry="${budgetSubAwardsAttributes.subAwardXfdFileName}" readOnly="true"/>	                		                	
	                	<!--<html:file property="document.budgetSubAwards[${status.index}].subAwardXfdFile" />-->						
	                	</div>
					</td>
					<td class="infoline">
						<div align=center>
							<html:image property="methodToCall.viewXFD.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewxfd.gif' />
							<html:image property="methodToCall.viewXML.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewxml.gif' />
							<html:image property="methodToCall.delete.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
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
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetSubAwards[${status.index}].xfdUpdateTimestamp" attributeEntry="${budgetSubAwardsAttributes.xfdUpdateTimestamp}" readOnly="true"/></div></td>
			        			</tr>
			        			<tr>		        					
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.xmlUpdateTimestamp}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetSubAwards[${status.index}].xmlUpdateTimestamp" attributeEntry="${budgetSubAwardsAttributes.xmlUpdateTimestamp}" readOnly="true"/></div></td>
			        			</tr>
			        			<tr>		        					
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.subAwardStatusCode}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budgetSubAwards[${status.index}].subAwardStatusCode" attributeEntry="${budgetSubAwardsAttributes.subAwardStatusCode}" readOnly="true"/></div></td>
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
