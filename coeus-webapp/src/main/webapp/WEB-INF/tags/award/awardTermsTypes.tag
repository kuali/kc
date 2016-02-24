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
<%@ attribute name="index" description="Index" required="true" %>
<%@ attribute name="sponsorTermTypeKey" description="Sponsor Term Type Key" required="true" %>
<%@ attribute name="sponsorTermTypeLabel" description="Sponsor Term Type Label" required="true" %>

<c:set var="awardSponsorTermAttributes" value="${DataDictionary.AwardSponsorTerm.attributes}" />
<c:set var="sponsorTermAttributes" value="${DataDictionary.SponsorTerm.attributes}" />

<c:set var="tabItemCount" value="0" />
<c:forEach var="awardSponsorTerm" items="${KualiForm.document.awardList[0].awardSponsorTerms}" varStatus="status">               
    <c:if test="${awardSponsorTerm.sponsorTermTypeCode == sponsorTermTypeKey }" >
        <c:set var="tabItemCount" value="${tabItemCount+1}" />
    </c:if>
</c:forEach>

<kul:innerTab parentTab="Award Terms" tabItemCount="${tabItemCount}" defaultOpen="false" tabTitle="${sponsorTermTypeLabel}" tabErrorKey="newAwardSponsorTerm[${sponsorTermTypeKey}]*" >
	<table border="0" cellpadding="0" cellspacing="0" summary="">
        <tr>
            <th width="6%"><div align="center">&nbsp;</div></th>          		
          	<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${sponsorTermAttributes.sponsorTermCode}" noColon="true" /></div></th>
          	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
        </tr>
        <c:if test="${!readOnly}">
        <tbody class="addline">
        <tr>
        	<th width="6%" class="infoline">
			    <c:out value="Add:" />
			</th>
			<td valign="left" class="infoline">
				<div align="left"> 
					<kul:htmlControlAttribute property="sponsorTermFormHelper.newSponsorTerms[${index}].sponsorTermCode" attributeEntry="${sponsorTermAttributes.sponsorTermCode}"/>
					<input type="hidden" name="document.keyValue${index}" value="${sponsorTermTypeKey}">
					<kul:multipleValueLookup boClassName="org.kuali.coeus.common.framework.sponsor.term.SponsorTerm" 
					lookedUpCollectionName="newSponsorTerms"
              		lookupParameters="document.keyValue${index}:sponsorTermTypeCode"
              		anchor="${tabKey}"/>
				</div>
			</td>
			<td width="10%" class="infoline">
			<div align="center">
			    <html:image property="methodToCall.addAwardSponsorTerm.sponsorTermType${sponsorTermTypeKey}.sponsorTermTypeIndex${index}.anchor${tabKey}"
			        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
			</div>
            </td>
        </tr>
        </tbody>
        </c:if>
        <c:set var="termIndex" value="1" />   <%-- index of the term within the subpanel --%>
        <c:forEach var="awardSponsorTerm" items="${KualiForm.document.awardList[0].awardSponsorTerms}" varStatus="status">              	
	        <c:choose>                    	
	            <c:when test="${KualiForm.document.awardList[0].awardSponsorTerms[status.index].sponsorTermTypeCode == sponsorTermTypeKey}">
	              <tr>
					<th class="infoline">
						${termIndex}
						<c:set var="termIndex" value="${termIndex+1}" />
					</th>
	                <td valign="left">
						${awardSponsorTerm.sponsorTermCode}: ${awardSponsorTerm.description}
					</td>
					<td width="10%">
					<div align="center">
					   <c:choose><c:when test="${!readOnly}">
						<html:image property="methodToCall.deleteAwardSponsorTerm.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						<c:if test="${KualiForm.syncMode}">
		 					<html:image property="methodToCall.syncSponsorTerm.line${status.index}.anchor${currentTabIndex}"
 								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-sync.gif' alt="sync" styleClass="tinybutton" disabled="${readOnly}"/>
						</c:if>				        
					   </c:when><c:otherwise>&nbsp;</c:otherwise></c:choose>
					</div>
	                </td>
	          	  </tr>
	            </c:when>
	        </c:choose>                    
	  	</c:forEach>
	</table>
</kul:innerTab>	
