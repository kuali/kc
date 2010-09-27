<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
${kfunc:registerEditableProperty(KualiForm, "actionHelper.selectedHistoryItem")}
<c:set var="submissionDocAttributes" value="${DataDictionary.ProtocolSubmissionDoc.attributes}" />

<kul:innerTab tabTitle="History" parentTab="" defaultOpen="false" tabErrorKey="actionHelper.filteredHistory*">

    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
            <tr>
                <th class="infoline"><nobr>&nbsp;&nbsp;&nbsp;&nbsp;View Action Date Range:</nobr></th>
                <td class="infoline"> 
                    <nobr>&nbsp;Beginning On&nbsp;
                       <kul:htmlControlAttribute property="actionHelper.filteredHistoryStartDate" 
                                                 attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" />
                	   &nbsp;
                	</nobr>
                </td>
                <td class="infoline"> 
                    <nobr>&nbsp;Ending On&nbsp;
                       <kul:htmlControlAttribute property="actionHelper.filteredHistoryEndDate" 
                                                 attributeEntry="${kraAttributeReferenceDummyAttributes.genericDate}" />
                		&nbsp;
                	</nobr>
                </td>
                <td class="infoline" style="width:90%;text-align:left;">&nbsp;
                    <html:image property="methodToCall.filterHistory.line${ctr}.anchor${currentTabIndex}"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-filter.gif' 
                                styleClass="tinybutton"/>
                    <html:image property="methodToCall.resetHistory.anchor${tabKey}"
                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-clear1.gif' 
                                styleClass="tinybutton"/>                      
                </td>
            </tr>
            </tbody>
        </table>
        
        <table id="historyTable" class="tab" cellpadding="0" cellspacing="0" summary="">
            <tbody>
                <tr>
	                <td class="infoline">&nbsp;</td>
	                <th>Description</th>
	                <th>Date</th>
	                <th><nobr>Action Date</nobr></th>
	                <th style="width:50%;">Comments</th>
                </tr>
                <c:forEach items="${KualiForm.document.protocol.protocolActions}" var="protocolAction" varStatus="status">
                    <c:if test="${protocolAction.isInFilterView}">
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
	            		        <c:choose>
	            		            <c:when test="${fn:length(protocolAction.comments) > 0}">
	                                    <kra:truncateComment textAreaFieldName="document.protocol.protocolActions[${status.index}].comments" 
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
	            		
	            		<c:if test="${fn:length(protocolAction.protocolCorrespondences) > 0}">
	            			<tr>
	            				<td class="infoline">&nbsp;</td>
	            		        <td colspan="4">
	            		        	<kul:innerTab tabTitle="Correspondences" tabItemCount="${fn:length(protocolAction.protocolCorrespondences)}" parentTab="attachment${status.index}" defaultOpen="false" tabErrorKey="">
	            		        		<div class="innerTab-container" align="left">
		                                    <table class="tab" cellpadding="0" cellspacing="0" summary="">
		                                        <tbody>
		                                            <tr>
		                                               <%--<th style="text-align:left;width:10%">File Name</th> --%>
		                                               <th style="text-align:center">Description</th>
		                                               <th style="text-align:center">Actions</th>
		                                            </tr>
		           		                            <c:forEach items="${protocolAction.protocolCorrespondences}" var="correspondence" varStatus="attachmentStatus">
		           		    	                        <tr>
		           		    	                            <td>${correspondence.protocolCorrespondenceType.description}</td>
		           		 					                <td align="center" valign="middle">
                                                                <div align="center">
                                                                    <html:image property="methodToCall.viewActionCorrespondence.line${status.index}.attachment${attachmentStatus.index}.anchor${currentTabIndex}"
										                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' 
										                                        alt="View Correspondence" onclick="excludeSubmitRestriction = true;"
										                                        styleClass="tinybutton"/>
								                               </div>
							                                 </td>
		           		    	                        </tr>
		           		                            </c:forEach>
		            		                    </tbody>
		            		                </table>
	            		                </div>
	            		        	</kul:innerTab>
	            		        </td>            		        
	            			</tr>
	            		</c:if>
	
	            		<c:if test="${fn:length(protocolAction.protocolSubmissionDocs) > 0}">
	            			<tr>
	            				<td class="infoline">&nbsp;</td>
	            		        <td colspan="4">
	            		        	<kul:innerTab tabTitle="Actions Attachments" tabItemCount="${fn:length(protocolAction.protocolSubmissionDocs)}" parentTab="attachment${status.index}" defaultOpen="false" tabErrorKey="">
	            		        		<div class="innerTab-container" align="left">
		                                    <table class="tab" cellpadding="0" cellspacing="0" summary="">
		                                        <tbody>
		                                            <tr>
		                                               <%--<th style="text-align:left;width:10%">File Name</th> --%>
		                                               <th style="text-align:center">File Name</th>
		                                               <th style="text-align:center">Description</th>
		                                               <th style="text-align:center">Actions</th>
		                                            </tr>
		           		                            <c:forEach items="${protocolAction.protocolSubmissionDocs}" var="submissionDoc" varStatus="attachmentStatus">
		           		    	                        <tr>
		           		    	                            <td><div align="left">${submissionDoc.fileName}</div></td>
		           		    	                            <td><div align="left">
	                                                            <kul:htmlControlAttribute property="document.protocol.protocolActions[${status.index}].protocolSubmissionDocs[${attachmentStatus.index}].description" attributeEntry="${submissionDocAttributes.description}" readOnly="true"/>
		           		    	                            </div></td>
		           		 					                <td align="center" valign="middle">
	                                                            <div align="center">
									                            <html:image property="methodToCall.viewSubmissionDoc.line${status.index}.attachment${attachmentStatus.index}.anchor${currentTabIndex}"
										                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif'
										                                    alt="View Action Attachment" onclick="excludeSubmitRestriction = true;"
										                                    styleClass="tinybutton"/>
								                               </div>
							                               </td>
		           		    	                        </tr>
		           		                            </c:forEach>
		            		                    </tbody>
		            		                </table>
	            		                
	            		                </div>
	            		        	</kul:innerTab>
	            		        </td>            		        
	            			</tr>
	            		</c:if>
            		</c:if>
            	</c:forEach>
            	
            	<tr>
            	    <td class="infoline" colspan="5">
            	        <html:image property="methodToCall.loadProtocolSummary.line${ctr}.anchor${currentTabIndex}"
                                    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-load.gif' 
                                    styleClass="tinybutton" style="vertical-align:bottom"/>  
            	        Load selected node into Summary View, above
            	    </td>
            	</tr>
            </tbody>
        </table>
    </div>
    			
</kul:innerTab>