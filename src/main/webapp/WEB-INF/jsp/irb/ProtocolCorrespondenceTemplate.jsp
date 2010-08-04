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
