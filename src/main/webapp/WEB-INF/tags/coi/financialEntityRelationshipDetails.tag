<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="prop" required="true" %>
<%@ attribute name="detailList" required="true" type="java.util.List" %>
<%@ attribute name="methodtocall" required="true" %>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="FinIntRelTypesAttribute" value="${DataDictionary.FinIntEntityRelType.attributes}" />
<c:set var="FinEntitiesDataMatrixAttribute" value="${DataDictionary.FinEntitiesDataMatrix.attributes}" />
<c:set var="PersonFinIntDisclDetAttribute" value="${DataDictionary.PersonFinIntDisclDet.attributes}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left"> 
            <a href="#" id ="relationDetailControl" class="relationDetailSubpanel"><img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> Relationship Details </span>
            <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.coi.personfinancialentity.FinIntEntityStatus" altText="help"/> </span>
        </h3>
      <div id="relationDetailContent" class="relationDetailSubpanelContent">                    
                    
        <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
 <%--
            <tr>
                <th align="middle" valign="middle" >
                    &nbsp;
                </th>
            <c:forEach var="relationshipType" items="${KualiForm.financialEntityHelper.finEntityRelationshipTypes}" varStatus="status">
                <th align="middle" valign="middle">


                    <kul:htmlControlAttribute property="financialEntityHelper.finEntityRelationshipTypes[${status.index}].description" 
                                              attributeEntry="${FinIntRelTypesAttribute.description}" readOnly="true"/> 
                </th>
             </c:forEach> 
                <th align="middle" valign="middle" >
                    Comment
                </th>
            </tr>
        --%>    
            <c:set var="groupId" value=""/>
            <c:forEach var="dataRow" items="${detailList}" varStatus="groupstatus">
                 <c:if test="${dataRow.dataGroupId != groupId}">
                  <tr>
                    <td class="content_grey" style="width:250px; text-align:left; font-weight:bold; height:25px; background-color:#CCC;">                        <kul:htmlControlAttribute property="${prop}[${groupstatus.index}].dataGroupName" 
                                              attributeEntry="${FinEntitiesDataMatrixAttribute.columnLabel}" readOnly="true"/> 
                    </td>
            <c:forEach var="relationshipType" items="${KualiForm.financialEntityHelper.finEntityRelationshipTypes}" varStatus="status">
               <td class="content_grey fineprint" style="width:100px; text-align:center; color:#000; vertical-align:bottom; background-color:#CCC;">
                    <B>
                    <kul:htmlControlAttribute property="financialEntityHelper.finEntityRelationshipTypes[${status.index}].description" 
                                              attributeEntry="${FinIntRelTypesAttribute.description}" readOnly="true"/> 
                   </B>                           
                </td>
             </c:forEach> 
                 <td class="content_grey fineprint" style="text-align:center; color:#000; vertical-align:bottom; background-color:#CCC;">
                               <B> Comment </B>
                  </td>
                <%--
                    <td colspan="${fn:length(KualiForm.financialEntityHelper.finEntityRelationshipTypes) + 1}"> 
                        &nbsp;
                    </td>
                  --%>  
                    <c:set var="groupId" value="${dataRow.dataGroupId}"/>
                   <tr>
                 </c:if>
                 <tr>
                    <th align="right" valign="middle">
                        <kul:htmlControlAttribute property="${prop}[${groupstatus.index}].columnLabel" 
                                              attributeEntry="${FinEntitiesDataMatrixAttribute.columnLabel}" readOnly="true"/>: 
                    </th>
                <c:forEach var="relationship" items="${dataRow.relationshipTypeBeans}" varStatus="status">
                     <td align="middle" valign="middle">
        <c:choose>
            <%-- c:when test = "${dataRow.guiType == 'DROPDOWN' and not empty dataRow.lookupArgument}" --%>
            <c:when test = "${dataRow.argValueLookup}">
                <kra-coi:argValueLookupData  valueIndex="${status.index}" bean="${dataRow}" 
                    property="${prop}[${groupstatus.index}].relationshipTypeBeans[${status.index}].stringValue" />
            </c:when>
            <c:otherwise>
                                                 <kul:htmlControlAttribute property="${prop}[${groupstatus.index}].relationshipTypeBeans[${status.index}].booleanValue" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" />
                        <%--                              
                        <kul:htmlControlAttribute property="${prop}[${groupstatus.index}].relationshipTypeBeans[${status.index}].booleanValue" 
                                              attributeEntry="${PersonFinIntDisclDetAttribute.columnValue}"/>
                         --%>                      
            </c:otherwise>
        </c:choose>
                    </td>
                 </c:forEach> 
                      <td align="middle" valign="middle">
                        <kul:htmlControlAttribute property="${prop}[${groupstatus.index}].comments" 
                                              attributeEntry="${PersonFinIntDisclDetAttribute.comments}"/> 
                    </td>
                 </tr>
             </c:forEach> 
            </tr>    
            <%--        
                <tr>
                    <td align="center" colspan="${fn:length(KualiForm.financialEntityHelper.finEntityRelationshipTypes) + 2}">
                        <div align="center">
                           <c:if test="${fn:contains(methodtocall, 'line')}">
                            <html:image property="${fn:replace(methodtocall,'submit','save')}.anchor${tabKey}"
                                        src='${ConfigProperties.kew.url}/images/tinybutton-save.gif' styleClass="tinybutton"/>
                            </c:if>            
                            <html:image property="${methodtocall}.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
             --%>
        </table>
      </div>
    </div>
