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

<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />

<div id="workarea">
    <kul:tab tabTitle="Print" 
             tabErrorKey="committeeHelper.reportType"
             auditCluster="requiredFieldsAuditErrors"  
             defaultOpen="false"
             useCurrentTabIndexAsKey="true"  
             transparentBackground="false">
        <div class="tab-container" align="center">
            <h3>
                <span class="subhead-left">Print</span>
            </h3>
            
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td><div align="left">Roster</div></td> 
                    <td>
                        <div align="center">
                            <kul:htmlControlAttribute property="committeeHelper.printRooster" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}"
                                                      readOnly="false" />
                        </div>
                    </td>
                </tr>     
                <tr>
                    <td><div align="left">Future Scheduled Meetings</div></td> 
                    <td>
                        <div align="center">
                            <kul:htmlControlAttribute property="committeeHelper.printFutureScheduledMeeting" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" />
                        </div>
                    </td>
                </tr>     
                <tr>
                    <td class="infoline"><div align="left">&nbsp;</div></td> 
                    <td class="infoline">
                        <div align="center">                    
                            <html:image property="methodToCall.printCommitteeDocument"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' 
                                        styleClass="tinybutton"
                                        onclick="excludeSubmitRestriction = true;" />                         
                        </div>
                    </td>
                </tr>     
            </table>
        </div> 
    </kul:tab>
    <kul:panelFooter />
</div>
