<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="committeeScheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />

<kul:tab defaultOpen="false" tabTitle="Correspondence"
    tabErrorKey="document.committee*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Correspondence </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.irb.correspondence.ProtocolCorrespondence" altText="help"/> </span>
    </h3>
         <table id="viewCorrespondence-table" cellpadding=0 cellspacing=0 class="datatable" summary="View Agenda Docs">
        
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="Protocol Number" scope="col" />
        		<kul:htmlAttributeHeaderCell literalLabel="Correspondence" scope="col" />
        		<kul:htmlAttributeHeaderCell literalLabel="Date Created" scope="col" />
				<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
			</tr>
        	<c:forEach var="correspondence" items="${KualiForm.meetingHelper.correspondences}" varStatus="status">
	             <tr>
					<th class="infoline" align="right">
						<c:out value="${correspondence.protocolNumber}" />
					</th>
	                <td align="left" valign="middle">
	                    <div align="left"><c:out value="${correspondence.protocolCorrespondenceType.description}" /></div>
					</td>
	                <td align="left" valign="middle">
	                    <div align="left"><fmt:formatDate value="${correspondence.updateTimestamp}" pattern="MM/dd/yyyy" /> </div>
					</td>
						<td>
							<div align="center">&nbsp;					
									<html:image property="methodToCall.viewCorrespondence.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
										alt="View Agenda" onclick="excludeSubmitRestriction = true;"/>
							</div>
		                </td>
	            </tr>
        	</c:forEach>
       </table> 	
</div>

</kul:tab>