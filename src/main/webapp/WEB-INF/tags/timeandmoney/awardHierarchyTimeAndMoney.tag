<%--
 Copyright 2005-2010 The Kuali Foundation
 
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

<script type="text/javascript" src="scripts/awardHierarchyShared.js"></script>
<script type="text/javascript" src="scripts/awardHierarchyTimeAndMoney.js"></script>
<c:set var="awardHierarchyNodeAttributes" value="${DataDictionary.AwardHierarchyNode.attributes}" />
<c:set var="awardAmountTransactionAttributes" value="${DataDictionary.AwardAmountTransaction.attributes}" />

<link rel="stylesheet" href="css/award_hierarchy.css" type="text/css" />

<kul:tab tabTitle="Award Hierarchy" defaultOpen="true" tabErrorKey="awardHierarchyNodeItems*,document.award.timeAndMoneyAwardAmountTransaction.newAwardAmountTransaction*,document.award.timeAndMoneyAwardDates*,totals*,timeAndMoneyTransaction*,newAwardAmountTransaction.transactionTypeCode,goToAwardNumber" auditCluster="reportsAuditErrors" tabAuditKey="document.reportTermsAuditRules*" useRiceAuditMode="true">
	<div class="tab-container" align="right">
		<h3>
    		<span class="subhead-left">Award Hierarchy</span>
    		<span class="subhead-right">
    			<kul:help businessObjectClassName="org.kuali.kra.timeandmoney.AwardHierarchyNode" altText="help"/>
			</span>
        </h3>   	        
    <table cellpadding="0" cellspacing="0" summary="">
    	<tr>
    		<kul:htmlAttributeHeaderCell attributeEntry="${awardAmountTransactionAttributes.transactionTypeCode}" scope="col" />
    		<kul:htmlAttributeHeaderCell attributeEntry="${awardAmountTransactionAttributes.noticeDate}" scope="col" />
    		<kul:htmlAttributeHeaderCell attributeEntry="${awardAmountTransactionAttributes.comments}" scope="col" />
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
	<c:if test="${KualiForm.inMultipleNodeHierarchy}" >
			
  			<table cellpadding="0" cellspacing="0" summary="">	
			<tr>
				<th>
					<div align="left">
						Go To:
						<html:select property="goToAwardNumber">
		                    <c:forEach items="${krafn:getOptionList('org.kuali.kra.timeandmoney.AwardValuesFinder', null)}" var="option">
		                    <c:choose>
		                        <c:when test="${KualiForm.goToAwardNumber == option.key}">
		                        <option value="${option.key}" selected="">${option.value}</option>
		                        </c:when>
		                        <c:otherwise>
		                        <option value="${option.key}">${option.value}</option>
		                        </c:otherwise>
		                    </c:choose>
		                    </c:forEach>
						</html:select> 
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
			
		<c:choose>				
			<c:when test="${KualiForm.directIndirectViewEnabled == '1'}" >						
				<input type="hidden" id="directIndirectViewEnabled" name="directIndirectViewEnabled" value="1" />					
			</c:when>
			<c:otherwise>						
				<input type="hidden" id="directIndirectViewEnabled" name="directIndirectViewEnabled" value="0" />					
			</c:otherwise>
		</c:choose>	
		
		<c:choose>				
			<c:when test="${KualiForm.cancelOrFinalStatus}" >						
				<input type="hidden" id="cancelOrFinalStatus" name="cancelOrFinalStatus" value="1" />					
			</c:when>
			<c:otherwise>						
				<input type="hidden" id="cancelOrFinalStatus" name="cancelOrFinalStatus" value="0" />					
			</c:otherwise>
		</c:choose>					

		<c:if test="${KualiForm.inSingleNodeHierarchy}" >
			<input type="hidden" id="controlForAwardHierarchyView" name="controlForAwardHierarchyView" value="2" />
			<input type="hidden" id="inSingleNodeHierarchy" name="inSingleNodeHierarchy" value="1" />
    		<table cellpadding="0" cellspacing="0" summary="">	
			<tr>
				<th>
					&nbsp;
				</th>
			</tr>
		</table>
		
		</c:if>
    
    <div align="left">    
                                            <%-- <div id="treecontrol" style="display:inline;">
                                                &nbsp;&nbsp;&nbsp;&nbsp;<a title="Collapse the entire tree below" href="#"><img src="static/images/jquery/minus.gif" /> Collapse All</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;<a title="Expand the entire tree below" href="#"><img src="static/images/jquery/plus.gif" /> Expand All</a>
                                            </div> --%>

	<div style="position: relative; margin: 2px 0pt 0pt;">
	  <div style="position:absolute; left:0px; height=285px; width:100%; text-align: center; z-index:100; display:none;" id="loading"><img style="margin-top: 90px;" src="static/images/awardHierarchy-loading.gif" alt="loading"/><span class="statusMessage"></span>
	  </div>	
     <div id="awardHierarchyScrollable" style = "background:#e4e4e4; margin: 10px 0px 10px 0px; clear: left; height: 285px; width: 100%; overflow-y: auto; overflow-x: auto; position: relative;" >
       <html:hidden property="awardHierarchyScrollPosition"/>
     <div class="divAHT"><div>
     	<c:forEach items="${KualiForm.awardHierarchyNodeItems}" var="tempNode" varStatus="status">
			<c:set var="finalExpirationDateProperty" value="${tempNode.finalExpirationDate}" />  
			<c:set var="currentFundEffectiveDateProperty" value="${tempNode.currentFundEffectiveDate}" />
			<c:set var="obligationExpirationDateProperty" value="${tempNode.obligationExpirationDate}" />
			<c:set var="amountObligatedToDateProperty" value="${tempNode.amountObligatedToDate}" />
			<c:set var="anticipatedTotalAmountProperty" value="${tempNode.anticipatedTotalAmount}" />
			<c:set var="obligatedTotalDirectProperty" value="${tempNode.obligatedTotalDirect}" />
			<c:set var="obligatedTotalIndirectProperty" value="${tempNode.obligatedTotalIndirect}" />
			<c:set var="anticipatedTotalDirectProperty" value="${tempNode.anticipatedTotalDirect}" />
			<c:set var="anticipatedTotalIndirectProperty" value="${tempNode.anticipatedTotalIndirect}" />
			${kfunc:registerEditableProperty(KualiForm, finalExpirationDateProperty)}  
			${kfunc:registerEditableProperty(KualiForm, currentFundEffectiveDateProperty)}
			${kfunc:registerEditableProperty(KualiForm, obligationExpirationDateProperty)}  
			${kfunc:registerEditableProperty(KualiForm, amountObligatedToDateProperty)}  
			${kfunc:registerEditableProperty(KualiForm, obligationExpirationDateProperty)}  
			${kfunc:registerEditableProperty(KualiForm, obligatedTotalDirectProperty)}
			${kfunc:registerEditableProperty(KualiForm, obligatedTotalIndirectProperty)}
			${kfunc:registerEditableProperty(KualiForm, anticipatedTotalDirectProperty)}
			${kfunc:registerEditableProperty(KualiForm, anticipatedTotalIndirectProperty)}
		</c:forEach> 
		<table style="border: medium none ; width: 100%; border-collapse: collapse;">
		<tbody><tr>
		<td style="border: medium none ; border-collapse: collapse; background-color: rgb(234, 233, 234);">
		<span style="display: inline;" id="showntreecontrol">
		${fn:length(KualiForm.order)} Nodes:
		<a href="#" title="Collapse the entire tree below" id="shownCollapseLink"><img src="static/images/jquery/minus.gif" /> Collapse All</a>
		<a href="#" title="Expand the entire tree below" id="shownExpandLink"><img src="static/images/jquery/plus.gif" /> Expand All</a>
		</span>
		<span style="display: none;" id="treecontrol">
		<a href="#" title="Collapse the entire tree below">Collapse All</a>
		<a href="#" title="Expand the entire tree below">Expand All</a>
		</span>				
		</td>
		<%--<c:if test="${KualiForm.controlForAwardHierarchyView == 0}" >
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 1100px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. Start
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 1212px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. End
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 1324px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Project End
			</td>
		</c:if>--%>
		
		<c:if test="${KualiForm.controlForAwardHierarchyView == 0}" >
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px;  align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. Start
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. End
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Project End
			</td>
		</c:if>

		<c:if test="${KualiForm.controlForAwardHierarchyView == 1}" >
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 114px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. Start
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 114px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Oblg. End
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 114px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Project End
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			&nbsp;
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Obligated
			</td>
			<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
			Anticipated
			</td> 
		</c:if>
				
		
		<c:if test="${KualiForm.controlForAwardHierarchyView == 2}" >
			<c:choose>				
				<c:when test="${KualiForm.directIndirectViewEnabled == '1'}" >						
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Oblg. Start
					</td>
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Oblg. End
					</td>
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Project End
					</td>
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Oblg. Direct
					</td>
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Oblg. F&A
					</td>		
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Oblg. Total
					</td>
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Ant. Direct
					</td>
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Ant. F&A
					</td>
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Ant. Total
					</td>					
				</c:when>
				<c:otherwise>			
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Oblg. Start
					</td>
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Oblg. End
					</td>
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Project End
					</td>		
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Obligated
					</td>
					<td style="border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align: right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);">
					Anticipated
					</td>				
				</c:otherwise>
			</c:choose>		
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
	<div id="debugLog" style="position: relative; overflow-y: auto; height: 15em; display:none; text-align: left; width:100%;"><a href="javascript: $('#loading').hide(); return false;" style="position: absolute; top: 0; right: 0;">Hide Loading</a></div>
    
    <script>
      <c:forEach items="${KualiForm.awardHierarchyToggle}" var="toggleEntry">
        <c:if test="${toggleEntry.value[0] eq 'true'}">
      	 	addOpenAward('${toggleEntry.key}');
      	</c:if>
      </c:forEach>
      setScrollPosition('${KualiForm.awardHierarchyScrollPosition}');
    </script>
</kul:tab>
