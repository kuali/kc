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

<c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request" />

<kul:page lookup="false" 
          docTitle="Protocol Action Notification Template" 
          transactionalDocument="false"
          renderMultipart="true" 
          htmlFormAction="protocolNotificationTemplate">
          
    <script language="javascript" src="scripts/kuali_application.js"></script>
    
    <div id="workarea">

<c:set var="notificationTemplateAttributes" value="${DataDictionary.ProtocolNotificationTemplate.attributes}" />

<kul:tab tabTitle="${parentTab}"
         defaultOpen="true"
         alwaysOpen="true"
         transparentBackground="true" 
         useCurrentTabIndexAsKey="true"
         tabErrorKey="notificationTemplates[*">
         
    <div class="tab-container" align="center" id="G100">
        <h3>
            <span class="subhead-left">Protocol Action Notification Templates</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.actions.notification.ProtocolNotificationTemplate" altText="help" /></span>
        </h3>
        
            <table style="border-top-width:1px; border-top-style:solid; border-top-color:#999999;" cellpAdding="0" cellspacing="0" width="50%" align="center" >
                <tr>
                    <kul:htmlAttributeHeaderCell attributeEntry="${notificationTemplateAttributes.actionTypeCode}" scope="col" align="center" width="25%" />
                    <kul:htmlAttributeHeaderCell attributeEntry="${notificationTemplateAttributes.fileName}" scope="col" align="center" />
                    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center" width="25%" />
               </tr>
        <c:forEach items="${KualiForm.notificationTemplates}" var="notificationTemplate" varStatus="status">
               <tr>
                    <td>
                 	    ${notificationTemplate.protocolActionType.description}
                    </td>
                    <td>
                <div id="filename1-${status.index}" style="${filenameStyle}" align="center">
                    ${notificationTemplate.fileName}
                </div>
                <div id="browse1-${status.index}" style="${browseStyle}" align="center">
                    <kra:file property="notificationTemplates[${status.index}].templateFile" />
                </div>                      
                    </td>
                    <td>
                    <div id="filename2-${status.index}" style="${filenameStyle}" align="center">
                         <html:image property="methodToCall.viewNotificationTemplate.notificationTemplates[${status.index}]}" 
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" 
                            title="View Notification Template" 
                            alt="View Notification Template" 
                            styleClass="tinybutton" 
                            onclick="excludeSubmitRestriction=true" />
                    <c:if test="${not readOnly}">
                        <html:image property="methodToCall.replaceNotificationTemplate.notificationTemplates[${status.index}]"
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif" 
                            title="Replace Notification Template" 
                            alt="Replace Notification Template" 
                            styleClass="tinybutton" 
                            onclick="javascript: showHide('browse1-${status.index}','filename1-${status.index}'); javascript: showHide('browse2-${status.index}','filename2-${status.index}');return false" />
                    </c:if>
                        </div>
                    <div id="browse2-${status.index}" style="${browseStyle}" align="center">
                    <c:if test="${not readOnly}">
                        <html:image property="methodToCall.replaceNotificationTemplate.notificationTemplates[${status.index}]" 
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-upload.gif" 
                            title="Upload Notification Template" 
                            alt="Upload Notification Template" 
                            styleClass="tinybutton" />
                        <html:image onclick="javascript: showHide('filename1-${status.index}','browse1-${status.index}'); showHide('filename2-${status.index}','browse2-${status.index}'); return false"
                            src="${ConfigProperties.kra.externalizable.images.url}tinybutton-cancel.gif" 
                            title="Cancel Notification Template Upload" 
                            alt="Cancel Notification Template Upload" 
                            styleClass="tinybutton" />
                    </c:if>
                    </div>
                    </td>
               </tr>
        </c:forEach>
            </table>   
    </div> 
</kul:tab>

<kul:panelFooter />

<div id="globalbuttons" class="globalbuttons">
    <c:if test="${!readOnly}">
        <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.save" title="save" alt="save"/>
    </c:if>
    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_reload.gif" styleClass="globalbuttons" property="methodToCall.reload" title="reload" alt="reload" onclick="excludeSubmitRestriction=true"/>
    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" styleClass="globalbuttons" property="methodToCall.close" title="close" alt="close"/>
    <c:if test="${!readOnly}">
        <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons" property="methodToCall.cancel" title="cancel" alt="cancel"/>
    </c:if>
</div>
<hr>
<SCRIPT type="text/javascript">
for(var i= 0; i < 15; i++) {
    showHide('filename1-'+i,'browse1-'+i );
    showHide('filename2-'+i,'browse2-'+i );
}

</SCRIPT>

</kul:page>
