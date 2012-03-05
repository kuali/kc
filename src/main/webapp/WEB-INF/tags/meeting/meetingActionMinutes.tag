<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="committeeScheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />
<c:set var="notificationSentMessage" value="${KualiForm.meetingHelper.minutesSentMessage}" />

<kul:tab defaultOpen="false" tabTitle="Minutes"
    tabErrorKey="meetingHelper.meetingMinute">

<div class="tab-container" align="center">
	<c:if test = "${KualiForm.meetingHelper.canModifySchedule}">
    	<h3>
        	<span class="subhead-left"> Minutes </span>
        	<span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.meeting.CommScheduleMinuteDoc" altText="help"/> </span>
    	</h3>
    
        	<table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
            	<tr>
                	<th align="right" valign="middle" width="145">
                    	Generate Minutes
                	</th>
                	<td align="left" valign="middle">
						    <div align="left">
					    	    <html:image property="methodToCall.generateMinutes.anchor${tabKey}"
							    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
						    </div>
        	        </td>
            	 </tr>
         	</table>
   </c:if>       
   <h3>
        <span class="subhead-left"> View Minutes </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.meeting.CommScheduleMinuteDoc" altText="help"/> </span>
    </h3>
        <table id="viewMinutes-table" cellpadding=0 cellspacing=0 class="datatable" summary="View Minutes Docs">
        
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="Version" scope="col" />
        		<kul:htmlAttributeHeaderCell literalLabel="Date Created" scope="col" />
				<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
			</tr>
        	<c:forEach var="minute" items="${KualiForm.meetingHelper.minuteDocs}" varStatus="status">
	             <tr>
					<th class="infoline" align="right">
						<c:out value="${minute.minuteNumber}" />
					</th>
	                <td align="left" valign="middle">
	                    <div align="left"><fmt:formatDate type="both" value="${minute.createTimestamp}" /> </div>
					</td>
						<td>
							<div align=center>&nbsp;					
				                <input type="image" alt="Send Minutes" class="tinybutton" onclick="alert('${notificationSentMessage}');return true;" id="sendMinute${status.index+1}"
				                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-sendminutes.gif" name="methodToCall.sendMinutesNotification.line${status.index}.anchor${currentTabIndex}">
								&nbsp;
				                <input type="image" alt="View Minutes" class="tinybutton" onclick="openNewWindow('meetingActions','viewMinute','${status.index}',0,'false'); return false;" id="viewMinute${status.index+1}"
				                    src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" name="methodToCall.viewMinute.line${status.index}.anchor${currentTabIndex}">
				             </div>
		                </td>
	            </tr>
        	</c:forEach>
       </table> 	
</div>

</kul:tab>