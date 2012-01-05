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
<%@ attribute name="disclProject" required="true" type="org.kuali.kra.irb.Protocol" %>
<%@ attribute name="style" required="false" description="style for current project" %>

<c:set var="coiDisclProjectAttributes" value="${DataDictionary.CoiDisclProject.attributes}" />
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<c:set var="readOnly" value="${!KualiForm.disclosureHelper.canEditDisclosureFinancialEntity}" />
                <div>
    	<h3>
            <span class="subhead-left" style="${style}"> 
               Protocol Number: ${disclProject.protocolNumber}</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
        </h3>
                  
                    <table class=tab cellpadding="0" cellspacing="0" summary="">
                        <tbody>
                        <%-- Header --%>
                                 <tr>
                                    <th><div align="right">IRB Protocol Name:</div></th> 
                  <td align="left" valign="middle" colspan="3">
					<div align="left">
					${disclProject.title}
					</div>
				  </td>
                                    <th><div align="right">IRB Protocol Type:</div></th> 
                  <td align="left" valign="middle">
					<div align="left">
					${disclProject.protocolType.description}
					</div>
				  </td>
                                </tr>
                        <%-- Header --%>
                        
                         <%-- New data --%>
                        <%-- kra:permission value="${KualiForm.disclosureHelper.modifyPersonnel}" --%>
                <tr>
                                    <th><div align="right">Application Date:</div></th> 
                  <td align="left" valign="middle">
					<div align="left">
					${disclProject.applicationDate}
					    <%-- kc removed applicationdate from protocol.  not sure what to replace --%>
					</div>
				  </td>
                                    <th><div align="right">Expiration Date:</div></th> 
                  <td align="left" valign="middle">
					<div align="left">
					${disclProject.expirationDate}
					</div>
				  </td>
                                    <th><div align="right">PI Name:</div></th> 
                  <td align="left" valign="middle">
					<div align="left">
					${disclProject.principalInvestigatorName}
					</div>
				  </td>
	            </tr>

               </table>
              
        </div>