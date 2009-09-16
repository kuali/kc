<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="dateRangeFilterAttributes" value="${DataDictionary.DateRangeFilter.attributes}" />

<kra:innerTab tabTitle="History" parentTab="" defaultOpen="false">

    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
            <tr>
                <th class="infoline"><nobr>&nbsp;&nbsp;&nbsp;&nbsp;View Action Date Range:</nobr></th>
                <td class="infoline"> 
                    <nobr>&nbsp;Beginning On&nbsp;
                       <kul:htmlControlAttribute property="actionHelper.historyDateRangeFilter.beginningOn" attributeEntry="${dateRangeFilterAttributes.beginningOn}" datePicker="true" />
                	   &nbsp;
                	</nobr>
                </td>
                <td class="infoline"> 
                    <nobr>&nbsp;Ending On&nbsp;
                       <kul:htmlControlAttribute property="actionHelper.historyDateRangeFilter.endingOn" attributeEntry="${dateRangeFilterAttributes.endingOn}" datePicker="true" />
                		&nbsp;
                	</nobr>
                </td>
                <td class="infoline" style="width:90%;text-align:left;">&nbsp;
                    <html:image property="methodToCall.filterHistory.line${ctr}.anchor${currentTabIndex}"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-filter.gif' 
                                styleClass="tinybutton"/>                         
                </td>
            </tr>
            </tbody>
        </table>
        
        <table class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                <tr>
	                <td class="infoline">&nbsp;</td>
	                <th>Description</th>
	                <th>Date</th>
	                <th><nobr>Action Date</nobr></th>
	                <th><nobr>Submission Status</nobr></th>
	                <th style="width:50%;">Comments</th>
                </tr>
                <c:forEach items="${KualiForm.actionHelper.filteredProtocolActions}" var="protocolAction" varStatus="status">
                    
            		<tr>
            		    <td class="infoline" style="text-align:center;">
            		        <c:choose>
            		            <c:when test="${protocolAction.submissionNumber == null}">
            		                &nbsp;
            		            </c:when>
            		            <c:otherwise>
            		                <input name="actionHelper.selectedHistoryItem" type="radio" class="nobord" value="${protocolAction.protocolActionId}">
            		            </c:otherwise>
            		        </c:choose>
            		    </td>
            		    <td class="infoline">
            		        <nobr><b>${protocolAction.protocolActionType.description}</b></nobr>
            		    </th>
            		    <td class="infoline">
            		        <nobr>${protocolAction.actualActionDateString}</nobr>
            		    </td>
            		    <td class="infoline">
            		        <nobr>${protocolAction.actionDateString}</nobr>
            		    </td>
            		    <td class="infoline">
            		        <nobr>${protocolAction.submissionStatusString}&nbsp;</nobr>
            		    </td>
            		    <td class="infoline">
            		        <c:choose>
            		            <c:when test="${fn:length(protocolAction.comments) > 0}">
                                    <kra:truncateComment textAreaFieldName="actionHelper.filteredProtocolActions[${status.index}].comments" 
                                                         action="protocolProtocolActions" 
                                                         textAreaLabel="Action Comment" 
                                                         textValue="${protocolAction.comments}" 
                                                         displaySize="100"/>
                                </c:when>
                                <c:otherwise>
                                      &nbsp;
                                </c:otherwise>
                            </c:choose>
                        </td>
            		</tr>
            		
            		<c:if test="${protocolAction.submissionNumber != null && fn:length(protocolAction.protocol.attachmentProtocols) > 0}">
            		    <tr>
            		        <td class="infoline">&nbsp;</td>
            		        <td colspan="5">
            		        <kra:innerTab tabTitle="Attachments" tabItemCount="${fn:length(protocolAction.protocol.attachmentProtocols)}" parentTab="" defaultOpen="false" tabErrorKey="">
    
                                <div class="innerTab-container" align="left">
                                
                                    <table class="tab" cellpadding="0" cellspacing="0" summary="">
                                        <tbody>
                                            <tr>
                                               <th style="text-align:left;width:10%">File Name</th>
                                               <th style="text-align:left">Description</th>
                                            </tr>
            		                            <c:forEach items="${protocolAction.protocol.attachmentProtocols}" var="attachment" varStatus="status">
            		    	                        <tr>
            		    	                            <td>${attachment.file.name}</td>
            		    	                            <td>
            		    	                                <c:choose>
            		                                            <c:when test="${fn:length(attachment.description) > 0}">
                                                                    <kra:truncateComment textAreaFieldName="document.protocolList[0].attachmentProtocols[${status.index}].description" 
                                                                                         action="protocolProtocolActions" 
                                                                                         textAreaLabel="Attachment Description" 
                                                                                         textValue="${attachment.description}" 
                                                                                         displaySize="250"/>
                                                               </c:when>
                                                               <c:otherwise>
                                                                   &nbsp;
                                                               </c:otherwise>
                                                           </c:choose>
                                                       </td>
            		    	                        </tr>
            		                            </c:forEach>
            		                    </tbody>
            		                </table>
            		            </div>
            		        </kra:innerTab>
            		        </td>
            		    </tr>
            		</c:if>
            	</c:forEach>
            	<tr>
            	    <td class="infoline" colspan="6">
            	        <html:image property="methodToCall.loadProtocolSummary.line${ctr}.anchor${currentTabIndex}"
                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-load.gif' 
                                    styleClass="tinybutton" style="vertical-align:bottom"/>  
            	        Load selected node into Summary View, above
            	    </td>
            	</tr>
            </tbody>
        </table>
    </div>
    			
</kra:innerTab>