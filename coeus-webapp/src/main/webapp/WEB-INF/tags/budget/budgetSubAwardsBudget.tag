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
<script language="javascript" src="dwr/interface/OrganizationService.js"></script>
<c:set var="readOnly" value="${not KualiForm.editingMode['modifyBudgets']}" scope="request" />
<c:set var="budgetSubAwardsAttributes" value="${DataDictionary.BudgetSubAwards.attributes}" />
<c:set var="periodDetailAttributes" value="${DataDictionary.BudgetSubAwardPeriodDetail.attributes}" />

<kul:tab tabTitle="Subaward Budget" defaultOpen="false" tabErrorKey="document.budget.budgetSubAwards*,newSubAward.*">
 <div class="tab-container" align="center">
     <h3>Subaward Budget
         <span class="subhead-right"><kul:help parameterNamespace="KC-B" parameterDetailType="Document" parameterName="budgetActionSubAwardHelpUrl" altText="help"/></span>
     </h3>
     <div align="center">
     	<table border="0" cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th width="5%"><div align="center">&nbsp;</div></th> 
          		<th width="15%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.organizationName}" noColon="true" /></div></th>
          		<th width="15%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.comments}" noColon="true" /></div></th>
          		<th width="15%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.formName}" noColon="true" /></div></th>
          		<th width="15%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.subAwardXfdFileName}" noColon="true" /></div></th>          		
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</tr>
			<c:if test="${!readOnly}" >
          	<tr class="addline">
					<th class="infoline">
						<c:out value="Add:" />
					</th>
					<td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newSubAward.organizationId" attributeEntry="${budgetSubAwardsAttributes.organizationId}" onblur="loadOrganizationName('newSubAward.organizationId', 'newSubAward.organizationName')" readOnly="${readOnly}"/>
	                		<kul:lookup boClassName="org.kuali.coeus.common.framework.org.Organization" fieldConversions="organizationId:newSubAward.organizationId,organizationName:newSubAward.organizationName" anchor="${tabKey}" lookupParameters="newSubAward.organizationId:organizationId"/>
	                	${kfunc:registerEditableProperty(KualiForm, 'newSubAward.organizationName')}
	                	<html:hidden styleId="newSubAward.organizationName" property="newSubAward.organizationName"/><div class="changedClearOnReset" id="newSubAward.organizationName.div"></div>	                	                	
	                	</div>
					</td>
					<td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="newSubAward.comments" attributeEntry="${budgetSubAwardsAttributes.comments}" />
	                	</div>
					</td>
					<td valign="middle" class="infoline">&nbsp;</td>					
	                <td valign="middle" class="infoline">
	                	<div align="center">	                		                	
	                		<html:file property="newSubAward.newSubAwardFile" />
	                		<br/>
	                		<c:if test="${newSubAward.newSubAwardFileError}">
                                <kul:fieldShowErrorIcon />
                            </c:if>						
	                	</div>
					</td>
					<td class="infoline">
						<div align=center>
							<html:image property="methodToCall.addSubAward.anchor${tabKey}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
						</div>
	                </td>
            </tr>
			</c:if>
			
			<c:forEach var="budgetSubAwards" items="${KualiForm.document.budget.budgetSubAwards}" varStatus="status">
		    	<tr>
    				<th width="5%" rowspan=3 class="infoline">
						<c:out value="${status.index + 1}" />
					</th>
					<td valign="middle" class="infoline">
	                	<div align="center">
	                		<kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].organizationId" attributeEntry="${budgetSubAwardsAttributes.organizationId}" onblur="loadOrganizationName('document.budget.budgetSubAwards[${status.index}].organizationId', 'document.budget.budgetSubAwards[${status.index}].organizationName')"/>
	                		<kul:lookup boClassName="org.kuali.coeus.common.framework.org.Organization" fieldConversions="organizationId:document.budget.budgetSubAwards[${status.index}].organizationId,organizationName:document.budget.budgetSubAwards[${status.index}].organizationName" anchor="${tabKey}" lookupParameters="document.budget.budgetSubAwards[${status.index}].organizationId:organizationId"/>
		                	<kul:directInquiry boClassName="org.kuali.coeus.common.framework.org.Organization" inquiryParameters="document.budget.budgetSubAwards[${status.index}].organizationId:organizationId" anchor="${tabKey}"/>
		                	<c:set var="organizationName" value="document.budget.budgetSubAwards[${status.index}].organizationName"/>
		                	${kfunc:registerEditableProperty(KualiForm, organizationName)}
		                	<html:hidden styleId="document.budget.budgetSubAwards[${status.index}].organizationName" property="document.budget.budgetSubAwards[${status.index}].organizationName"/><div id="document.budget.budgetSubAwards[${status.index}].organizationName.div"><c:out value="${budgetSubAwards.organizationName}"/></div>
	                	</div>
					</td>
					<td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].comments" attributeEntry="${budgetSubAwardsAttributes.comments}" readOnly="${readOnly}"/>
	                	</div>
					</td>					
					<td valign="middle" class="infoline">
	                	<div align="center">
	                	<kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].formName" attributeEntry="${budgetSubAwardsAttributes.formName}" readOnly="true"/>
	                	</div>
					</td>
	                <td valign="middle" class="infoline">
	                	<div align="center">
	                		<div style="display: ${(!KualiForm.document.budget.budgetSubAwards[status.index].newSubAwardFileError and not empty KualiForm.document.budget.budgetSubAwards[status.index].subAwardXfdFileName) ? 'block' : 'none'}">
	                			<kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].subAwardXfdFileName" attributeEntry="${budgetSubAwardsAttributes.subAwardXfdFileName}" readOnly="true"/>
	                			<br/><a href="#" onclick="jQuery(this).parent().hide(); jQuery(this).parent().siblings('div').show(); return false;"><img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif" styleClass="tinybutton"/></a>
	                			<html:image property="methodToCall.deleteSubAwardAttachment.line${status.index}.anchor${currentTabIndex}" 
	                				src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" styleClass="tinybutton" />
	                		</div>
	                		<div style="display: ${KualiForm.document.budget.budgetSubAwards[status.index].newSubAwardFileError or empty KualiForm.document.budget.budgetSubAwards[status.index].subAwardXfdFileName ? 'block' : 'none'}">
		                		<html:file property="document.budget.budgetSubAwards[${status.index}].newSubAwardFile" /> 
		                		<c:if test="${KualiForm.document.budget.budgetSubAwards[status.index].newSubAwardFileError}">
	                                <kul:fieldShowErrorIcon />
	                            </c:if>
	                            <c:if test="${not empty KualiForm.document.budget.budgetSubAwards[status.index].subAwardXfdFileName }">
	                            	<a href="#" onclick="jQuery(this).parent().hide(); jQuery(this).parent().siblings('div').show(); return false;"><img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-cancel.gif" styleClass="tinybutton" /></a>
	                            </c:if>
	                			<html:image property="methodToCall.updateBudgetAttachment.line${status.index}.anchor${currentTabIndex}" 
	                				src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton" />	                            
	                        </div>
	                	</div>
					</td>
					<td valign="middle" class="infoline">
						<div align=center>
						    <c:choose>
	                            <c:when test="${not empty KualiForm.document.budget.budgetSubAwards[status.index].subAwardXmlFileData}" >
									<html:image property="methodToCall.viewXFD.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewpdf.gif' styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/>
									<html:image property="methodToCall.viewXML.line${status.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-viewxml.gif' styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/>
									<html:image property="methodToCall.syncFromBudgetAttachment.line${status.index}.anchor${tabKey}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-syncfromPDF.gif' styleClass="tinybutton"/>									
							    </c:when>
							    <c:when test="${not empty KualiForm.document.budget.budgetSubAwards[status.index].subAwardXfdFileData}">
							        <html:image property="methodToCall.viewXFD.line${status.index}.anchor${currentTabIndex}"
                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/>
                                </c:when>
                            </c:choose>
							<html:image property="methodToCall.delete.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						</div>
	                </td>
	             </tr>
	             <tr>	             	
	        		<td colspan = "5">
	             	<kul:innerTab parentTab="budgetSubAwards" defaultOpen="false" tabTitle="Attachment Details" tabErrorKey="" useCurrentTabIndexAsKey="true">
	             		<div>
    						<table cellpadding=0 cellspacing=0 summary="">
    							<tr>		        					
					        		<th width="25%"><div align="right">Attachments:</div></th>
					        		<td><div align="left"><bean:write name="budgetSubAwards" property="attachmentNames" filter="false" /></div></td>
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
			        			<tr>		        					
					        		<th width="25%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetSubAwardsAttributes.namespace}" noColon="true" /></div></th>
					        		<td><div align="left"><kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].namespace" attributeEntry="${budgetSubAwardsAttributes.namespace}" readOnly="true"/></div></td>
			        			</tr>
			        		</table>
			        	</div>		
	             	</kul:innerTab>
	             	</td>
	             </tr>
	             <tr>
	             	<td colspan="5">
	             		 <kul:innerTab parentTab="budgetSubAwards" defaultOpen="false" tabTitle="Details" tabErrorKey="" useCurrentTabIndexAsKey="true"> 
	             		 	<table cellpadding=0 cellspacing=0 summary="">
	             		 		<tr>
	             		 			<th>&nbsp;</th>
	             		 			<th style="text-align: center;"><kul:htmlAttributeLabel attributeEntry="${periodDetailAttributes.directCost}" noColon="true" /></th>
	             		 			<th style="text-align: center;"><kul:htmlAttributeLabel attributeEntry="${periodDetailAttributes.indirectCost}" noColon="true" /></th>
	             		 			<th style="text-align: center;"><kul:htmlAttributeLabel attributeEntry="${periodDetailAttributes.costShare}" noColon="true" /></th>
	             		 			<th style="text-align: center;"><kul:htmlAttributeLabel attributeEntry="${periodDetailAttributes.totalCost}" noColon="true" /></th>
	             		 		</tr>
	             		 		<c:forEach items="${budgetSubAwards.budgetSubAwardPeriodDetails}" var="periodDetails" varStatus="periodStatus">
	             		 			<tr>
	             		 				<th class="infoline"><c:out value="${periodDetails.budgetPeriod}"/></th>
	             		 				<td class="infoline" style="text-align: center;"><kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].budgetSubAwardPeriodDetails[${periodStatus.index}].directCost" 
	             		 				attributeEntry="${periodDetailAttributes.directCost}" styleClass="amount calculateTotal" readOnly="${readOnly}"/></td>
	             		 				<td class="infoline" style="text-align: center;"><kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].budgetSubAwardPeriodDetails[${periodStatus.index}].indirectCost" 
	             		 				attributeEntry="${periodDetailAttributes.indirectCost}" styleClass="amount calculateTotal" readOnly="${readOnly}"/></td>
	             		 				<td class="infoline" style="text-align: center;"><kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].budgetSubAwardPeriodDetails[${periodStatus.index}].costShare" attributeEntry="${periodDetailAttributes.costShare}" styleClass="amount calculateTotal" readOnly="${readOnly}"/></td>
	             		 				<td class="infoline" style="text-align: right;"><span class="totalCost"><kul:htmlControlAttribute property="document.budget.budgetSubAwards[${status.index}].budgetSubAwardPeriodDetails[${periodStatus.index}].totalCost" attributeEntry="${periodDetailAttributes.totalCost}" styleClass="amount" readOnly="true"/></span></td>
	             		 			</tr>
	             		 		</c:forEach>
	             		 	</table>	
	             		 </kul:innerTab>
	             	</td>
	             </tr>					 		
			</c:forEach>
			    	

          </table>
       </div>                  
   </div>
</kul:tab>
<script type="text/javascript">
	var Global = (function($) {
		Number.prototype.formatMoney = function(c, d, t){
			var n = this, c = isNaN(c = Math.abs(c)) ? 2 : c, d = d == undefined ? "," : d, t = t == undefined ? "." : t, s = n < 0 ? "-" : "", i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "", j = (j = i.length) > 3 ? j % 3 : 0;
			   return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
		};
		var Global = {};
		Global.calculateTotal = function(inputField) {
			var total = 0;
			$(inputField).parents('tr').first().find('input.calculateTotal').each(function() {
				var fieldValue = $(this).val().replace(/[$,]/g, '');
				if (fieldValue == '') fieldValue = 0;
				total = total + parseFloat(fieldValue);
			});
			if (!isNaN(total)) {
				var formattedTotal = new Number(total).formatMoney(2, '.', ',');
				var amountSpan = $(inputField).parents('tr').first().find('.totalCost');
				if (amountSpan.children('input').length > 0) {
					amountSpan.children('input').first().val(formattedTotal);
				} else {
					amountSpan.html(formattedTotal);
				}
			}
		}
		$('input.calculateTotal').change(function() { Global.calculateTotal(this); });
		return Global;
	})(jQuery);
</script>
