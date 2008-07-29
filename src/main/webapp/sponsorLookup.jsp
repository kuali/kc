 <%--
 Copyright 2006-2008 The Kuali Foundation

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

<kul:page lookup="true" 
          docTitle="Add Sponsor Hierarchy" 
          transactionalDocument="false" 
          htmlFormAction="sponsorHierarchy">

	<input type="hidden" id="mapKey"
		name="mapKey" 
		value="${SponsorHierarchyForm.mapKey}" />

 		<label>Sponsor Code Search</label>
   		<label><kul:multipleValueLookup boClassName="org.kuali.kra.bo.Sponsor" 
         	lookedUpCollectionName="newSponsors[0]" /></label><br>
         	
         	     <table>
         					<c:forEach var="sponsor" items="${SponsorHierarchyForm.newSponsors[0]}" varStatus="sponsorIdx">
         						<tr>
         							<td>
         							     ${sponsor.sponsorCode}
         							</td>
         							<td>
         							     ${sponsor.sponsorName}
         							</td>
         							<td>
       			                		<html:image src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" styleClass="globalbuttons" property="methodToCall.deleteSponsor.line${sponsorIdx.index}.anchor${status.index}" title="Delete Sponsor" alt="Delete Sponsor" />   		        			    
         							</td>
 								</tr>
 							</c:forEach>
 					  	</table>
         		<input type="hidden" id="selectedSponsors" name="selectedSponsors" value="${SponsorHierarchyForm.selectedSponsors}" />
         	
         	<p><a href="javascript:returnSponsor();window.close();"><b>return data</b></a> <a href="javascript:window.close()">Close</a></p>
         	
          	<script type="text/javascript">
          	  
          	     function returnSponsor() {
          	            var sponsors = document.getElementById("selectedSponsors").value
          	            var mapKey = document.getElementById("mapKey").value
          	         	window.opener.returnSponsor(sponsors, mapKey);
          	     
          	     }
          
            </script>
</kul:page>
