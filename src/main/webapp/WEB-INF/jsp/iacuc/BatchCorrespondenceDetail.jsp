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
          docTitle="Batch Correspondence" 
          transactionalDocument="false"
          renderMultipart="true" 
          htmlFormAction="iacucBatchCorrespondenceDetail">

	<div align="left"><kul:help parameterNamespace="KC-M" parameterDetailType="Document" parameterName="batchCorrespondenceHelp" altText="help"/>
	<br />
    
    <div id="workarea">

<c:set var="parentTab" value = "IACUC Batch Correspondence" />

<kul:tab tabTitle="${parentTab}"
         defaultOpen="true"
         alwaysOpen="true"
         transparentBackground="true" 
         useCurrentTabIndexAsKey="true"
         tabErrorKey="batchCorrespondence.*,newBatchCorrespondenceDetail.*">
         
    <div class="tab-container" align="center" id="G100">
        <h3>
            <span class="subhead-left">IACUC Batch Correspondence</span>
            <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.iacuc.correspondence.IacucBatchCorrespondenceDetail" altText="help" /></span>
        </h3>
        <table cellpadding="0" cellspacing="0" border="0">
           <kra-iacuc:batchCorrespondenceDetailType />
           <c:if test="${not empty KualiForm.batchCorrespondence.batchCorrespondenceTypeCode}">
               <kra-iacuc:batchCorrespondenceDetailDetails />
           </c:if>
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

</kul:page>
