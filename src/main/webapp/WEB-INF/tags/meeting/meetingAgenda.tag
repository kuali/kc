<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="committeeScheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />

<kul:tabTop defaultOpen="false" tabTitle="Agenda" tabErrorKey="document.committee*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Generate Agenda </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.committee.bo.CommitteeSchedule" altText="help"/> </span>
    </h3>
        <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
            <tr>
                <th align="right" valign="middle" width="145">
                    Generate Agenda
                </th>
                <td align="left" valign="middle">
					    <div align="left">
					        <html:image property="methodToCall.generateAgenda.anchor${tabKey}"
						    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
					    </div>
                </td>
             </tr>
         </table>       
   <h3>
        <span class="subhead-left"> View Agenda </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.committee.bo.CommitteeSchedule" altText="help"/> </span>
    </h3>
        <table id="viewAgenda-table" cellpadding=0 cellspacing=0 class="datatable" summary="View Agenda Docs">
        
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="Version" scope="col" />
        		<kul:htmlAttributeHeaderCell literalLabel="Date Created" scope="col" />
				<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
			</tr>
        	<c:forEach var="agenda" items="${KualiForm.meetingHelper.scheduleAgendas}" varStatus="status">
	             <tr>
					<th class="infoline" align="right">
						<c:out value="${agenda.agendaNumber}" />
					</th>
	                <td align="left" valign="middle">
	                    <div align="left"><fmt:formatDate value="${agenda.createTimestamp}" pattern="MM/dd/yyyy" /> </div>
					</td>
						<td>
							<div align="center">&nbsp;					
								<%-- <html:image property="methodToCall.viewAgenda.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
										alt="View Agenda" onclick="excludeSubmitRestriction = true;"/> --%>
				                  <input type="image" alt="View Agenda" class="tinybutton" onclick="excludeSubmitRestriction = true;" id="viewAgenda${status.index+1}"
				                  src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" name="methodToCall.viewAgenda.line${status.index}.anchor${currentTabIndex}">
							</div>
		                </td>
	            </tr>
        	</c:forEach>
       </table> 	
</div>
</kul:tabTop>