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

<c:set var="awardHierarchyNodeAttributes" value="${DataDictionary.AwardHierarchyNode.attributes}" />
<c:set var="awardAmountTransactionAttributes" value="${DataDictionary.AwardAmountTransaction.attributes}" />

<kul:tab tabTitle="Award Hierarchy" defaultOpen="true" tabErrorKey="awardHierarchyNodeItems*,document.award.timeAndMoneyAwardAmountTransaction.newAwardAmountTransaction*,document.award.timeAndMoneyAwardDates*" auditCluster="reportsAuditErrors" tabAuditKey="document.reportTermsAuditRules*" useRiceAuditMode="true">
	<div class="tab-container" align="right">
		<h3>
    		<span class="subhead-left">Award Hierarchy</span>
    		<span class="subhead-right">
    			<kul:help businessObjectClassName="org.kuali.kra.timeandmoney.AwardHierarchyNode" altText="help"/>
			</span>
        </h3>    	        
    <table cellpadding="0" cellspacing="0" summary="">
    	<tr>
    		<kul:htmlAttributeHeaderCell attributeEntry="${awardAmountTransactionAttributes.transactionTypeCode}" scope="col" /></div></th>
    		<kul:htmlAttributeHeaderCell attributeEntry="${awardAmountTransactionAttributes.noticeDate}" scope="col" /></div></th>
    		<kul:htmlAttributeHeaderCell attributeEntry="${awardAmountTransactionAttributes.comments}" scope="col" /></div></th>
    	</tr>
    	<tr>
    		<c:choose>
    			<c:when test="${fn:length(KualiForm.document.awardAmountTransactions) > 0}" >
    				<td align="left" valign="middle">					
		    		<div align="center">            
		            	<kul:htmlControlAttribute property="document.awardAmountTransactions[0].transactionTypeCode" readOnlyAlternateDisplay="${KualiForm.document.awardAmountTransactions[0].awardTransactionType.description}" attributeEntry="${awardAmountTransactionAttributes.transactionTypeCode}" />
					</div>
					</td>
					<td align="left" valign="middle">					
		    		<div align="center">            
		            	<kul:htmlControlAttribute property="document.awardAmountTransactions[0].noticeDate" attributeEntry="${awardAmountTransactionAttributes.noticeDate}"  />
					</div>
					</td>
					<td align="left" valign="middle">					
		    		<div align="center">
		            	<kul:htmlControlAttribute property="document.awardAmountTransactions[0].comments" attributeEntry="${awardAmountTransactionAttributes.comments}" />
					</div>
					</td>
    			</c:when>
    			<c:otherwise>
    				<td align="left" valign="middle">					
		    		<div align="center">            
		            	<kul:htmlControlAttribute property="document.newAwardAmountTransaction.transactionTypeCode" attributeEntry="${awardAmountTransactionAttributes.transactionTypeCode}" />
					</div>
					</td>
					<td align="left" valign="middle">					
		    		<div align="center">            
		            	<kul:htmlControlAttribute property="document.newAwardAmountTransaction.noticeDate" attributeEntry="${awardAmountTransactionAttributes.noticeDate}"  />
					</div>
					</td>
					<td align="left" valign="middle">					
		    		<div align="center">
		            	<kul:htmlControlAttribute property="document.newAwardAmountTransaction.comments" attributeEntry="${awardAmountTransactionAttributes.comments}" />
					</div>
					</td>    			
    			</c:otherwise>
    		</c:choose>
    		
    	</tr>
    </table>
 	<div>
	<c:if test="${KualiForm.inMultipleNodeHierarchy}" >
			
  			<table cellpadding="0" cellspacing="0" summary="">	
			<tr>
				<th>
					<div align="left">
						Go To:
						<html:text property="goToAwardNumber" size="12" maxlength="12" />
						<html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-go.gif" styleClass="globalbuttons" alt="Go" property="methodToCall.switchAward" />
					</div>
				</th>
				<td style="text-align: center; background-color: rgb(195, 195, 195); font-weight: bold; width: 170px;">
		
					<c:choose>				
						<c:when test="${KualiForm.currentOrPendingView == '0'}" >						
							<input class="nobord" type="radio" value="0" name="currentOrPendingView" checked="true" />
								current
							<input class="nobord" type="radio" value="1" name="currentOrPendingView" />
								pending						
						</c:when>
						<c:when test="${KualiForm.currentOrPendingView == '1'}" >						
							<input class="nobord" type="radio" value="0" name="currentOrPendingView"  />
								current
							<input class="nobord" type="radio" value="1" name="currentOrPendingView" checked="true" />
					pending						
						</c:when>
						<c:otherwise>						
							<input class="nobord" type="radio" value="0" name="currentOrPendingView" />
								current
							<input class="nobord" type="radio" value="1" name="currentOrPendingView" />
								pending					
						</c:otherwise>
					</c:choose>				
				</td>
				<td style="text-align: center; background-color: rgb(195, 195, 195); font-weight: bold; width: 185px;">
					<select id="controlForAwardHierarchyView" name="controlForAwardHierarchyView" >
						<option ${KualiForm.controlForAwardHierarchyView eq 0 ? 'selected="selected"' : ''} value="0">Dates Only</option>
						<option ${KualiForm.controlForAwardHierarchyView eq 1 ? 'selected="selected"' : ''} value="1">Distributed/Distributable</option>
						<option ${KualiForm.controlForAwardHierarchyView eq 2 ? 'selected="selected"' : ''} value="2">Totals</option>
					</select>
				</td>
				<td style="text-align: center; background-color: rgb(195, 195, 195); width: 60px;">				
					<html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif" styleClass="tinybutton" alt="Refresh" property="methodToCall.refreshView" />
				</td>			
			</tr>
		</table>
		
		</c:if>
	
		<c:if test="${KualiForm.inSingleNodeHierarchy}" >
	
    			<table cellpadding="0" cellspacing="0" summary="">	
    			<input type="hidden" id="controlForAwardHierarchyView" name="controlForAwardHierarchyView" value="2" />
			<tr>
				<th>
					&nbsp;
				</th>
				<td style="text-align: center; background-color: rgb(195, 195, 195); font-weight: bold; width: 170px;">
		
					<c:choose>				
						<c:when test="${KualiForm.currentOrPendingView == '0'}" >						
							<input class="nobord" type="radio" value="0" name="currentOrPendingView" checked="true" />
								current
							<input class="nobord" type="radio" value="1" name="currentOrPendingView" />
								pending						
						</c:when>
						<c:when test="${KualiForm.currentOrPendingView == '1'}" >						
							<input class="nobord" type="radio" value="0" name="currentOrPendingView"  />
								current
							<input class="nobord" type="radio" value="1" name="currentOrPendingView" checked="true" />
					pending						
						</c:when>
						<c:otherwise>						
							<input class="nobord" type="radio" value="0" name="currentOrPendingView" />
								current
							<input class="nobord" type="radio" value="1" name="currentOrPendingView" />
								pending					
						</c:otherwise>
					</c:choose>				
				</td>
				<td style="text-align: center; background-color: rgb(195, 195, 195); width: 60px;">				
					<html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif" styleClass="tinybutton" alt="Refresh" property="methodToCall.refreshView" />
				</td>			
			</tr>
		</table>
		
		</c:if>
		</div>
    
    <div align="left">    
                                            <%-- <div id="treecontrol" style="display:inline;">
                                                &nbsp;&nbsp;&nbsp;&nbsp;<a title="Collapse the entire tree below" href="#"><img src="static/images/jquery/minus.gif" /> Collapse All</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;<a title="Expand the entire tree below" href="#"><img src="static/images/jquery/plus.gif" /> Expand All</a>
                                            </div> --%>

	
     <div style = "background:#e4e4e4; margin: 10px 0pt 0pt; clear: left; height: 285px; overflow-y: scroll; overflow-x: scroll; position: relative;" >
     
     <div class="divAHT"><div style="padding-right: 47px;">
		<table style="border: medium none ; width: 100%; border-collapse: collapse;">
		<tbody><tr>
		<td style="border: medium none ; border-collapse: collapse; background-color: rgb(234, 233, 234);">
		<span style="display: inline;" id="treecontrol">
		${fn:length(KualiForm.order)} Nodes: 
		<a href="#" title="Collapse the entire tree below"><img src="static/images/jquery/minus.gif" /> Collapse All</a>
		<a href="#" title="Expand the entire tree below"><img src="static/images/jquery/plus.gif" /> Expand All</a>
		</span>
		</td>
		<c:if test="${KualiForm.controlForAwardHierarchyView == 0}" >
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 800px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. Start
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 912px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. End
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 1024px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Project End
			</td>
		</c:if>

		<c:if test="${KualiForm.controlForAwardHierarchyView == 1}" >
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 114px; position: absolute; left: 465px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. Start
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 114px; position: absolute; left: 582px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. End
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 114px; position: absolute; left: 699px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Project End
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 816px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			&nbsp;
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; position: absolute; left: 932px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Obligated
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; position: absolute; left: 1044px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Anticipated
			</td> 
		</c:if>
		
		<c:if test="${KualiForm.controlForAwardHierarchyView == 2}" >
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 885px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. Start
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 997px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. End
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; position: absolute; left: 1109px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Project End
			</td>		
			<td style="border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; position: absolute; left: 1221px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Obligated
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: right; width: 110px; position: absolute; left: 1335px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Anticipated
			</td>
		</c:if>
	</tr>
	</tbody></table></div>
	</div>
	     
  <ul id="awardhierarchy" class="filetree stripeli treeview"  >
        <%-- <li><span class="folder">00000</span>
        </li> --%>
    </ul>
   </div> 
    </div>  
    
    <input type="hidden" id = "document.rootAwardNumber" name="document.rootAwardNumber" value="${KualiForm.document.rootAwardNumber}">
 
 	<c:choose>
	    <c:when test="${KualiForm.document.award != null}">
	    	<input type="hidden" id = "currentAwardNumber" name="currentAwardNumber" value="${KualiForm.document.award.awardNumber}">
	    	<input type="hidden" id = "currentSeqNumber" name="currentSeqNumber" value="${KualiForm.document.award.sequenceNumber}">
		</c:when>
		<c:otherwise>
		   	<input type="hidden" id = "currentAwardNumber" name="currentAwardNumber" value="" >
		   	<input type="hidden" id = "currentSeqNumber" name="currentSeqNumber" value="" >
		</c:otherwise>
	</c:choose>
    </div>
</kul:tab>
