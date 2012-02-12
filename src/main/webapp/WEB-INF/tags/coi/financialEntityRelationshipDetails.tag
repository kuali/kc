<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="prop" required="true" %>
<%@ attribute name="detailList" required="true" type="java.util.List" %>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="FinIntRelTypesAttribute" value="${DataDictionary.FinIntEntityRelType.attributes}" />
<c:set var="FinEntitiesDataMatrixAttribute" value="${DataDictionary.FinEntitiesDataMatrix.attributes}" />
<c:set var="PersonFinIntDisclDetAttribute" value="${DataDictionary.PersonFinIntDisclDet.attributes}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left"> 
            	<a href="#" id ="relationDetailControl" class="relationDetailSubpanel"><img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> Relationship Details 
            </span>
			<span class="subhead-right"> 
				<kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="financialEntityRelationshipHelp" altText="help"/>
            </span>
        </h3>
        <div id="relationDetailContent" class="relationDetailSubpanelContent">                    
                    
            <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
                <c:set var="groupId" value=""/>
                <!-- detail list is a list of FinEntityDataMatrixBeans -->
                <c:forEach var="dataRow" items="${detailList}" varStatus="groupstatus">
                    <c:if test="${dataRow.dataGroupId != groupId}">
                        <tr>
                            <td class="content_grey" style="width:250px; text-align:left; font-weight:bold; height:25px; background-color:#CCC;">                            <kul:htmlControlAttribute property="${prop}[${groupstatus.index}].dataGroupName" 
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
                            <c:set var="groupId" value="${dataRow.dataGroupId}"/>
                        </tr>
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
            </table>
        </div>
    </div>
