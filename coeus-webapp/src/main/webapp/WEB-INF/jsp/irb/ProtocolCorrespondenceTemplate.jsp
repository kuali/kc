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
          docTitle="Correspondence Template" 
          transactionalDocument="false"
          renderMultipart="true" 
          htmlFormAction="protocolCorrespondenceTemplate">
          
    <script language="javascript" src="scripts/kuali_application.js"></script>
    
    <div id="workarea">

<c:set var="parentTab" value = "Correspondence Templates" />

<kul:tab tabTitle="${parentTab}"
         defaultOpen="true"
         alwaysOpen="true"
         transparentBackground="true" 
         useCurrentTabIndexAsKey="true"
         tabErrorKey="">
         
    <div class="tab-container" align="center" id="G100">
        <h3>
            <span class="subhead-left">Correspondence Templates</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate" altText="help" /></span>
        </h3>
        
        <c:forEach items="${KualiForm.correspondenceTypes}" var="correspondenceType" varStatus="status">
            <c:set var="tabKey" value="${kfunc:generateTabKey(parentTab)}:${kfunc:generateTabKey(tabTitle)}" />
            <c:set var="isOpen" value="false"/>
            <c:if test="${KualiForm.tabStates[tabKey] == 'OPEN'}">
                <c:set var="isOpen" value="true"/>
            </c:if>

            <kul:innerTab tabTitle="${correspondenceType.description}" 
                          parentTab="${parentTab}" 
                          defaultOpen="${isOpen}"
                          tabErrorKey="newDefaultCorrespondenceTemplates[${status.index}].*,newCorrespondenceTemplates[${status.index}].*,correspondenceTypes[${status.index}].*,replaceCorrespondenceTemplates[${status.index}].*">
                <kra-irb:correspondenceDefaultTemplate index="${status.index}" />
                <kra-irb:correspondenceCommitteeTemplate index="${status.index}" />                
            </kul:innerTab>
        </c:forEach>
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

</kul:page>
