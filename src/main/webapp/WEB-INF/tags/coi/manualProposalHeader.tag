<%--
 Copyright 2005-2010 The Kuali Foundation

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
<%@ attribute name="idx" required="true" description="Coi disl project list index" %>
<%@ attribute name="style" required="false" description="style for current project" %>
<%@ attribute name="disclProject" required="true" type="org.kuali.kra.coi.CoiDisclProject" %>

<c:set var="coiDisclProjectAttributes" value="${DataDictionary.CoiDisclProject.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
                <div>
                <h3>
    		        <span class="subhead-left" style="${style}">Proposal Number: ${disclProject.shortTextField1}</span>
    		        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
                </h3>
              
                    <table class=tab cellpadding="0" cellspacing="0" summary="">
                        <tbody>
                        <%-- Header --%>
                                 <tr>
                                    <th><div align="right">Proposal Name</div></th> 
                  <td align="left" valign="middle">
					<div align="left">
					${disclProject.longTextField1}
					</div>
				  </td>
                                    <th><div align="right">Sponsor</div></th> 
                  <td align="left" valign="middle">
					<div align="left">
					${disclProject.longTextField2}
					</div>
				  </td>
                                  </tr>
                                  <tr>
                                    <th><div align="right">start Date</div></th> 
                  <td align="left" valign="middle">
					<div align="left">
					${disclProject.dateField1}
					</div>
				  </td>
                                    <th><div align="right">End Date</div></th> 
                 <td align="left" valign="middle">
					<div align="left">
					${disclProject.dateField1}
					</div>
				  </td>
	            </tr>

               </table>
               </div>