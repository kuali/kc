<%--
 Copyright 2005-2013 The Kuali Foundation

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
<tr><td colspan="3">
<div align="center" style="padding: 20px">
<table cellspacing="0" cellpadding="0" border="0" style="width: auto; border-top: 1px solid rgb(153, 153, 153);">
    <tr>
        <td>
                <div align="left" style="font-weight:bold;">
                   Correspondence Type:
                </div>
                <kul:htmlControlAttribute property="batchCorrespondence.batchCorrespondenceTypeCode"
                                          attributeEntry="${DataDictionary.BatchCorrespondenceDetail.attributes.batchCorrespondenceTypeCode}" 
                                          readOnly="false" />          
        </td>
    </tr>
    <tr>
        <td colspan="2" class="infoline nobord" style="padding:4px">
            <div align="center">
            <html:image property="methodToCall.start" 
                 src="${ConfigProperties.kra.externalizable.images.url}tinybutton-refresh.gif" 
                 title="refresh" 
                 alt="refresh" 
                 styleClass="tinybutton"/>
            </div>
        </td>
    </tr>
</table>
</div>
</td></tr>
