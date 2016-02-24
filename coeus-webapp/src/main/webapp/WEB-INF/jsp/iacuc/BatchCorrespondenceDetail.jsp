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
          docTitle="Batch Correspondence" 
          transactionalDocument="false"
          renderMultipart="true" 
          htmlFormAction="iacucBatchCorrespondenceDetail">

	<div align="left"><kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucBatchCorrespondenceHelpUrl" altText="help"/>
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
