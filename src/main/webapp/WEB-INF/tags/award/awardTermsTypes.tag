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

<kra:innerTab parentTab="Award Terms" tabItemCount="${tabItemCount}" defaultOpen="false" tabTitle="${sponsorTermTypeLabel}" tabErrorKey="newAwardSponsorTerm[${sponsorTermTypeKey}]*" >
	<table border="0" cellpadding="0" cellspacing="0" summary="">
        <tr>
            <th width="6%"><div align="center">&nbsp;</div></th>          		
          	<th width="5%"><div align="left"><kul:htmlAttributeLabel attributeEntry="${sponsorTermAttributes.sponsorTermCode}" noColon="true" /></div></th>
          	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
        </tr>
        <tr>
        	<th width="6%" class="infoline">
			    <c:out value="Add:" />
			</th>
			<td width="90%" valign="left" class="infoline">
				<div align="left"> 
					<kul:htmlControlAttribute property="sponsorTermFormHelper.newSponsorTerms[${index}].sponsorTermCode" attributeEntry="${sponsorTermAttributes.sponsorTermCode}"/>
					<input type="hidden" name="document.keyValue${index}" value="${sponsorTermTypeKey}">
					<kul:multipleValueLookup boClassName="org.kuali.kra.bo.SponsorTerm" 
					lookedUpCollectionName="newSponsorTerms"
              		lookupParameters="document.keyValue${index}:sponsorTermTypeCode"
              		anchor="${tabKey}"/>
				</div>
			</td>
			<td class="infoline">
			<div align="center">
			    <html:image property="methodToCall.addAwardSponsorTerm.sponsorTermType${sponsorTermTypeKey}.sponsorTermTypeIndex${index}.anchor${tabKey}"
			        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
			</div>
            </td>
        </tr>
        <c:set var="termIndex" value="1" />   <%-- index of the term within the subpanel --%>
        <c:forEach var="awardSponsorTerm" items="${KualiForm.document.awardList[0].awardSponsorTerms}" varStatus="status">              	
	        <c:choose>                    	
	            <c:when test="${KualiForm.document.awardList[0].awardSponsorTerms[status.index].sponsorTermTypeCode == sponsorTermTypeKey}">
	              <tr>
					<th width="5%" class="infoline">
						${termIndex}
						<c:set var="termIndex" value="${termIndex+1}" />
					</th>
	                <td width="10%" valign="left">
						${awardSponsorTerm.sponsorTermCode}: ${awardSponsorTerm.description}
					</td>
					<td width="10%">
					<div align="center">&nbsp;
						<html:image property="methodToCall.deleteAwardSponsorTerm.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
	                </td>
	          	  </tr>
	            </c:when>
	        </c:choose>                    
	  	</c:forEach>
	</table>
</kra:innerTab>	
