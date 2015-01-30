<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<%-- member of awardSpecialApproval.tag --%>

<%@ include file="/WEB-INF/jsp/award/awardTldHeader.jsp"%>
 
<kul:innerTab parentTab="Special Approval" tabItemCount="${formAward.approvedForeignTravelTripCount}" defaultOpen="false" 
				tabTitle="Approved Foreign Travel" tabErrorKey="approvedForeignTravelBean.newApprovedForeignTravel*,${docAward}.approvedForeignTravelTrips*" >
				
	<c:set var="approvedForeignTravelAttributes" value="${DataDictionary.AwardApprovedForeignTravel.attributes}" />
			
    <table id="approved-foreignTravel-table" cellpadding="0" cellspacing="0" summary="Approved Foreign Travel">
		<tr>
			<th scope="row">&nbsp;</th>
			<th><kul:htmlAttributeLabel attributeEntry="${approvedForeignTravelAttributes.travelerName}" useShortLabel="true" noColon="true" /></th>
			<th><kul:htmlAttributeLabel attributeEntry="${approvedForeignTravelAttributes.destination}" useShortLabel="true" noColon="true" /></th>
			<th><kul:htmlAttributeLabel attributeEntry="${approvedForeignTravelAttributes.startDate}" useShortLabel="true" noColon="true"/></th>
			<th><kul:htmlAttributeLabel attributeEntry="${approvedForeignTravelAttributes.endDate}" useShortLabel="true" noColon="true"/></th>
			<th><kul:htmlAttributeLabel attributeEntry="${approvedForeignTravelAttributes.amount}" useShortLabel="true" noColon="true"/></th>
			<th><div align="center">Actions</div></th>
		</tr>
		
		<c:if test="${!readOnly}">
		<tbody class="addline">
		<tr>
        	<th width="50" align="center" scope="row"><div align="right">Add:</div></th>
        	<td class="infoline">
        	  	<div align="left">
        	  		<c:set var="contactId" value="${KualiForm.approvedForeignTravelBean.newApprovedForeignTravel.contactId}" />
                    <div style="float: left; margin-top: 5; margin-right:20">
                        <span id="ApprovedForeignTravel_TravelerName"><kul:htmlControlAttribute property="approvedForeignTravelBean.newApprovedForeignTravel.travelerName"
        	  	 				                			attributeEntry="${approvedForeignTravelAttributes.travelerName}" readOnly="true" /></span><br/>
                        <html:select property="approvedForeignTravelBean.selectedTravelerId" style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size:11;"
                                     onchange="clearApprovedForeignTravelerTravelerName();">
                            <html:option value="" style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size:11;">--Select--</html:option>
                            <html:optionsCollection property="approvedForeignTravelBean.knownTravelers" value="key" label="value" style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size:11;"/>
                        </html:select>

                    </div>
                    <div>
                        <div align="left">
                            <kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson"
                                fieldConversions="personId:approvedForeignTravelBean.newApprovedForeignTravel.personId,fullName:approvedForeignTravelBean.newApprovedForeignTravel.travelerName"
                                lookupParameters="approvedForeignTravelBean.newApprovedForeignTravel.personId:contactIdId"
                                anchor="${tabKey}" /> Employee Lookup
                        </div>
                        <div align="left">
                            <kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex"
                                fieldConversions="rolodexId:approvedForeignTravelBean.newApprovedForeignTravel.rolodexId,fullName:approvedForeignTravelBean.newApprovedForeignTravel.travelerName"
                                lookupParameters="approvedForeignTravelBean.newApprovedForeignTravel.rolodexId:contactId"
                                anchor="${tabKey}" /> Non-employee Lookup
                        </div>
                    </div>
        	 	</div>
        	</td>
            <td class="infoline">
              	<div align="center">
            		<kul:htmlControlAttribute property="approvedForeignTravelBean.newApprovedForeignTravel.destination" attributeEntry="${approvedForeignTravelAttributes.destination}" />
              	</div>
            </td>
            <td class="infoline">
            	<div align="center">
        	    	<kul:htmlControlAttribute property="approvedForeignTravelBean.newApprovedForeignTravel.startDate" 
        	    								attributeEntry="${approvedForeignTravelAttributes.startDate}" />
        	  	</div>
            </td>
            <td class="infoline">
            	<div align="center">
        	    	<kul:htmlControlAttribute property="approvedForeignTravelBean.newApprovedForeignTravel.endDate" 
        	    								attributeEntry="${approvedForeignTravelAttributes.endDate}" />
        	  	</div>
            </td>
            <td class="infoline">
            	<div align="center">
        	    	<kul:htmlControlAttribute property="approvedForeignTravelBean.newApprovedForeignTravel.amount" attributeEntry="${approvedForeignTravelAttributes.amount}" styleClass="amount"/>
        	  	</div>
            </td>
            <td class="infoline">
            	<div align="center">
					<html:image property="methodToCall.addApprovedForeignTravel.anchor${tabKey}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
				</div>
            </td>
      	</tr>
      	</tbody>
      	</c:if>

        <c:forEach var="approvedForeignTravelTrip" items="${formAward.approvedForeignTravelTrips}" varStatus="status">
             <tr>
				<th width="10%" class="infoline">
					<c:out value="${status.index+1}" />
				</th>
                <td width="15%" valign="middle">
                	<div align="center">
                		<kul:htmlControlAttribute property="${docAward}.approvedForeignTravelTrips[${status.index}].travelerName" 
                								attributeEntry="${approvedForeignTravelAttributes.travelerName}" readOnly="true"/>
                        <c:if test="${formAward.approvedForeignTravelTrips[status.index].employee}">
                		    <kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson"
                			    	fieldConversions="personId:${docAward}.approvedForeignTravelTrips[${status.index}].travelerId" anchor="${tabKey}"
        	  	 				    lookupParameters="${docAward}.approvedForeignTravelTrips[${status.index}].travelerId:personId" />
                        </c:if>
                        <c:if test="${formAward.approvedForeignTravelTrips[status.index].nonemployee}">
                		    <kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex"
                			    	fieldConversions="rolodexId:${docAward}.approvedForeignTravelTrips[${status.index}].rolodexId" anchor="${tabKey}"
        	  	 				    lookupParameters="${docAward}.approvedForeignTravelTrips[${status.index}].rolodexId:rolodexId" />
                        </c:if>
					</div>
				</td>
                <td width="15%" valign="middle">
                	<div align="center">
						<kul:htmlControlAttribute property="${docAward}.approvedForeignTravelTrips[${status.index}].destination" attributeEntry="${approvedForeignTravelAttributes.destination}"/>
					</div>
				</td>
                <td width="15%" valign="middle">
                	<div align="center">                	
						<kul:htmlControlAttribute property="${docAward}.approvedForeignTravelTrips[${status.index}].startDate" attributeEntry="${approvedForeignTravelAttributes.startDate}"/>
					</div> 
				</td>
				<td width="15%" valign="middle">
                	<div align="center">                	
						<kul:htmlControlAttribute property="${docAward}.approvedForeignTravelTrips[${status.index}].endDate" attributeEntry="${approvedForeignTravelAttributes.endDate}"/>
					</div> 
				</td>
                <td width="15%" valign="middle">
                	<div align="center">                	
						<kul:htmlControlAttribute property="${docAward}.approvedForeignTravelTrips[${status.index}].amount" 
								attributeEntry="${approvedForeignTravelAttributes.amount}" styleClass="amount" />
					</div> 
				</td>
                
				<td width="15%" class="infoline">
					<div align="center">
					   <c:if test="${!readOnly}">
						<html:image property="methodToCall.deleteApprovedForeignTravelTrip.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					   </c:if>
					   <c:if test="${readOnly}">&nbsp;</c:if>
					</div>
                </td>
            </tr>
    	</c:forEach>
    	<tr>
      		<th colspan="5" align="right" scope="row"><div>Totals:</div></th>
      		<th align="right">
      			<div align="right">
      				<fmt:formatNumber type="CURRENCY" value="${formAward.totalApprovedApprovedForeignTravelAmount}" />
                </div>
         	</th>
         	<th scope="row">
         		<div align="center">
         		 <c:if test="${!readOnly}">
					<html:image property="methodToCall.recalculateSpecialApprovalTotals.anchor${tabKey}" 
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-recalculate.gif' styleClass="tinybutton" />
				    </c:if>
				    <c:if test="${!readOnly}">&nbsp;</c:if>
				</div>
			</th>
		</tr>
		
    </table>
</kul:innerTab>
