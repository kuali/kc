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

<kul:page lookup="true" 
          docTitle="Change Password" 
          transactionalDocument="false" 
          htmlFormAction="${htmlFormAction}">
          
    <script language="javascript" src="scripts/kuali_application.js"></script>
    <div align="center" style="margin:10px">
        <kul:tabTop defaultOpen="true" tabTitle="Change Password" tabErrorKey="changePassword*">
		    <div class="tab-container" align="center">
		        <div class="h2-container">
		            <span class="subhead-left"><h2>Change Password</h2></span>
		        </div>  
		        <table id="password-table" cellpadding="0" cellspacing="0" summary="">
		            <tr>
		                <th><div align="right">* Current Password:</div></th>
		                <td>
		                    <input id="currentPassword" type="password" name="currentPassword" value="${KualiForm.currentPassword}" maxLength="50" size="30" />
		                </td>
		            </tr>
		            <tr>
		                <th><div align="right">* New Password:</div></th>
                        <td>
                            <input id="newPassword" type="password" name="newPassword" value="${KualiForm.newPassword}" maxLength="50" size="30" />
                        </td>
		            </tr>
		            <tr>
		                <th><div align="right">* Confirm Password:</div></th>
                        <td>
                            <input id="confirmPassword" type="password" name="confirmPassword" value="${KualiForm.confirmPassword}" maxLength="50" size="30" />
                        </td>
		            </tr>
		        </table>
		    </div>
        </kul:tabTop>
        <kul:panelFooter />
                
        <div id="globalbuttons" class="globalbuttons">
            <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.updatePassword" title="save" alt="save" />   
        </div>
    </div>
</kul:page>
