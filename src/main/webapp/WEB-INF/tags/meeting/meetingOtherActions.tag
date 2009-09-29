<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="commScheduleActItemAttributes" value="${DataDictionary.CommScheduleActItem.attributes}" />

<kul:tab defaultOpen="false" tabTitle="Other Actions"
    tabErrorKey="meetingHelper.newOtherAction.*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Other Actions </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.meeting.CommScheduleActItem" altText="help"/> </span>
    </h3>
    
        <table id="otehrActions-table" cellpadding=0 cellspacing=0 class="datatable" summary="View/edit protocol participants">
        
        	<%-- Header --%>
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${commScheduleActItemAttributes.scheduleActItemTypeCode}" scope="col" />
				<kul:htmlAttributeHeaderCell attributeEntry="${commScheduleActItemAttributes.itemDesctiption}" scope="col" />
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
			</tr>
			<%-- Header --%>
			
            <%-- New data --%>
        	<%-- <kra:permission value="${KualiForm.meetingHelper.modifySubjects}">--%>
	            <tr>
				<th class="infoline" width="5%">
					<c:out value="Add:" />
				</th>
	
	            <td align="left" valign="middle" class="infoline" width="20%">
	               	<div align="center">
	               		<kul:htmlControlAttribute property="meetingHelper.newOtherAction.scheduleActItemTypeCode" attributeEntry="${commScheduleActItemAttributes.scheduleActItemTypeCode}" readOnly="false" />
	            	</div>
				</td>
			
	            <td align="left" valign="middle" class="infoline" width="65%">
	               	<div align="left">
	               	    <kul:htmlControlAttribute property="meetingHelper.newOtherAction.itemDesctiption" attributeEntry="${commScheduleActItemAttributes.itemDesctiption}" readOnly="false" />
                        <kra:expandedTextArea textAreaFieldName="meetingHelper.newOtherAction.itemDesctiption" action="meetingManagement" textAreaLabel="${commScheduleActItemAttributes.itemDesctiption.label}" />
	               	</div>
	            </td>
	
				<td align="left" valign="middle" class="infoline" width="10%">
					<div align=center>
					    <html:image property="methodToCall.addOtherAction.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
	               </td>
	            </tr>
            <%--</kra:permission> --%>
			<%-- New data --%>
			
			<%-- Existing data --%>
        	<c:forEach var="otherAction" items="${KualiForm.meetingHelper.committeeSchedule.commScheduleActItems}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                    <div align="left"> ${otherAction.scheduleActItemType.description} </div>
					</td>
	                <td align="left" valign="middle">
		                <kra:truncateComment textAreaFieldName="meetingHelper.committeeSchedule.commScheduleActItems[${status.index}].itemDesctiption" action="meetingManagement" textAreaLabel="${commScheduleActItemAttributes.itemDesctiption.label}"  textValue="${otherAction.itemDesctiption}" displaySize="200"/>
					</td>
                   <c:if test="${!readOnly}">
						<td>
							<div align=center>&nbsp;					
								<html:image property="methodToCall.deleteOtherAction.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</div>
		                </td>
		            </c:if>
	            </tr>
        	</c:forEach>
			<%-- Existing data --%>
			        				
        </table>
</div>

</kul:tab>