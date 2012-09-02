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
<%@ attribute name="businessObjectClassName" required="true" 
              description="The specific per-module business class to use for the help pages" %>
<%@ attribute name="attributes" required="true" type="java.util.Map"
              description="The Data Dictionary reference to the Special Review attributes" %>
<%@ attribute name="exemptionAttributes" required="true" type="java.util.Map"
              description="The Data Dictionary reference to the Special Review Exemption attributes" %>
<%@ attribute name="collectionReference" required="true" type="java.util.List" 
              description="The object reference to the collection that holds all the current Special Reviews" %>
<%@ attribute name="collectionProperty" required="true" 
              description="The property name of the collection that holds all the current Special Reviews" %>
<%@ attribute name="action" required="true" 
              description="The name of the action class" %>

<c:set var="canModify" value="${KualiForm.specialReviewHelper.canModifySpecialReview}"/>
<c:set var="enableIrbProtocolLinking" value="${KualiForm.specialReviewHelper.isIrbProtocolLinkingEnabled}" />
<c:set var="enableIacucProtocolLinking" value="${KualiForm.specialReviewHelper.isIacucProtocolLinkingEnabled}" />
<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.SPECIAL_REVIEW_COMMENT_LENGTH%>" />
<c:set var="canCreateIrbProtocol" value="false" />
<c:set var="canCreateIacucProtocol" value="false" />
<c:if test="${enableIrbProtocolLinking}">
	<c:set var="canCreateIrbProtocol" value="${KualiForm.specialReviewHelper.canCreateIrbProtocol}" />
</c:if>
<c:if test="${enableIacucProtocolLinking}">
	<c:set var="canCreateIacucProtocol" value="${KualiForm.specialReviewHelper.canCreateIacucProtocol}" />
</c:if>

<c:set var="buttonStyle" value="display:none"/>
<c:if test="${canCreateIrbProtocol && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '1'}">
	<c:set var="buttonStyle" value="display:inline"/>
</c:if>
<c:if test="${canCreateIacucProtocol && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '2'}">
	<c:set var="buttonStyle" value="display:inline"/>
</c:if>

<kul:tab tabTitle="Special Review" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="specialReviewHelper.newSpecialReview*,${collectionProperty}*">
    <div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Special Review</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="${businessObjectClassName}" altText="help"/></span>
        </h3>
        
        <table id="specialReviewTableId" cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.specialReviewTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.approvalTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.protocolNumber}" noColon="true" /></nobr></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.applicationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.approvalDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${attributes.expirationDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${exemptionAttributes.exemptionTypeCode}" noColon="true" /></div></th>
              	<c:if test="${canModify}"> 
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    </c:if>
          	</tr>     

            <c:if test="${canModify}"> 
                <tr>
                    <c:set var="protocolLinkingReadOnly" value="${(enableIrbProtocolLinking && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '1') || (enableIacucProtocolLinking && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '2')}" />
                    <c:choose>
                       <c:when test="${enableIrbProtocolLinking && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '1'}">
                           <c:set var="initialStyleIrb" value="display:inline"/>
                       </c:when>
                       <c:otherwise>
                          <c:set var="initialStyleIrb" value="display:none"/>
                       </c:otherwise>
                    </c:choose>
                    <c:choose>
                       <c:when test="${enableIacucProtocolLinking && KualiForm.specialReviewHelper.newSpecialReview.specialReviewTypeCode == '2'}">
                           <c:set var="initialStyleIacuc" value="display:inline"/>
                       </c:when>
                       <c:otherwise>
                          <c:set var="initialStyleIacuc" value="display:none"/>
                       </c:otherwise>
                    </c:choose>
                    
	                <c:set var="textAreaFieldName" value="specialReviewHelper.newSpecialReview.comments" />
					<th class="infoline" rowspan="2">
						Add:
					</th>
	                <td align="left" valign="middle" class="infoline"><div align="center">
	                   <kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.specialReviewTypeCode" 
		                                         attributeEntry="${attributes.specialReviewTypeCode}"
		                                         styleClass="fixed-size-200-select"
		                                         onchange="showHideSpecialReviewProtocolLink(this, 'specialReviewHelper.newSpecialReview', '${canCreateIrbProtocol}', '${canCreateIacucProtocol}','${enableIrbProtocolLinking}','${enableIacucProtocolLinking}');return false"/>
					</div></td>
	                <td class="infoline"><div align="center">
	                   <kra:dynamicHtmlControlAttribute property="specialReviewHelper.newSpecialReview.approvalTypeCode" 
		                                                attributeEntry="${attributes.approvalTypeCode}" 
		                                                initialReadOnly="${protocolLinkingReadOnly}"
		                                                readOnlyBody="true"
		                                                staticOnly="false">
                           ${KualiForm.specialReviewHelper.newSpecialReview.protocolStatus}
		               </kra:dynamicHtmlControlAttribute>
	                </div></td>
	                <td class="infoline"><div align="center">
                        <kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.protocolNumber" 
		                                          attributeEntry="${attributes.protocolNumber}" />
                            <span id="specialReviewHelper.newSpecialReview.protocolNumber.irb.link.div" style="${initialStyleIrb}">
                                <kul:lookup boClassName="org.kuali.kra.irb.Protocol" 
                                            fieldConversions="protocolNumber:specialReviewHelper.newSpecialReview.protocolNumber" />
                            </span>
                            <span id="specialReviewHelper.newSpecialReview.protocolNumber.iacuc.link.div" style="${initialStyleIacuc}">
                                <kul:lookup boClassName="org.kuali.kra.iacuc.IacucProtocol" 
                                            fieldConversions="protocolNumber:specialReviewHelper.newSpecialReview.protocolNumber" />
                            </span>
					</div></td>
	                <td align="left" valign="middle" class="infoline"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="specialReviewHelper.newSpecialReview.applicationDate" 
                                                         attributeEntry="${attributes.applicationDate}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         staticOnly="false" />
	                </div></td>
                    <td align="left" valign="middle" class="infoline"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="specialReviewHelper.newSpecialReview.approvalDate" 
                                                         attributeEntry="${attributes.approvalDate}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         staticOnly="false" />
	                </div></td>
	                <td align="left" valign="middle" class="infoline"><div align="center">
	                	<kra:dynamicHtmlControlAttribute property="specialReviewHelper.newSpecialReview.expirationDate" 
	                	                                 attributeEntry="${attributes.expirationDate}" 
	                	                                 initialReadOnly="${protocolLinkingReadOnly}"
	                	                                 staticOnly="false" />
	                </div></td>
	                <td align="left" valign="middle" class="infoline"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="specialReviewHelper.newSpecialReview.exemptionTypeCodes" 
                                                         attributeEntry="${exemptionAttributes.exemptionTypeCode}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}" 
                                                         staticOnly="false" />  			
	                </div></td>
					<td class="infoline" rowspan="1"><div align="center">
						<html:image property="methodToCall.addSpecialReview.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton"/>

					            <c:if test="${canCreateIrbProtocol || canCreateIacucProtocol}">

		                            <span id="specialReviewHelper.newSpecialReview.startprotocol.image.div" style="${buttonStyle}">
					                            <html:image property="methodToCall.createProtocol.anchor${tabKey}"
					                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-startprotocol.gif' 
				    	                        title="Create Protocol"
				        	                    styleClass="tinybutton"/>
		        	                </span>
	        	                </c:if>
	                </div></td>
	            </tr>
	            
	            <tr>
	            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${attributes.comments}" noColon="false" /></div></th>
	            	<td colspan="7">
	            		<kul:htmlControlAttribute property="specialReviewHelper.newSpecialReview.comments" 
	            		                          attributeEntry="${attributes.comments}" />
	            	</td>  
	            </tr>      
            </c:if>
            
        	<c:forEach var="specialReview" items="${collectionReference}" varStatus="status">
                <tr>
                    <c:set var="protocolLinkingReadOnly" value="${((enableIrbProtocolLinking and collectionReference[status.index].specialReviewTypeCode == '1' ) || (enableIacucProtocolLinking and collectionReference[status.index].specialReviewTypeCode == '2' )) }" />
                    <c:choose>
                       <c:when test="${enableIrbProtocolLinking && collectionReference[status.index].specialReviewTypeCode == '1'}">
                           <c:set var="initialStyleIrb" value="display:inline"/>
                       </c:when>
                       <c:otherwise>
                          <c:set var="initialStyleIrb" value="display:none"/>
                       </c:otherwise>
                    </c:choose>
                    <c:choose>
                       <c:when test="${enableIacucProtocolLinking && collectionReference[status.index].specialReviewTypeCode == '2'}">
                           <c:set var="initialStyleIacuc" value="display:inline"/>
                       </c:when>
                       <c:otherwise>
                          <c:set var="initialStyleIacuc" value="display:none"/>
                       </c:otherwise>
                    </c:choose>

	                <c:set var="textAreaFieldName" value="${collectionProperty}[${status.index}].comments" />
					<th class="infoline" rowspan="2">
					   <c:out value="${status.index+1}" />
					</th>
                    <td align="left" valign="middle"><div align="center">
                        <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].specialReviewTypeCode" 
	                                              attributeEntry="${attributes.specialReviewTypeCode}"  
	                                              readOnly="${not canModify}"
	                                              styleClass="fixed-size-200-select"
	                                              readOnlyAlternateDisplay="${specialReview.specialReviewType.description}" 
	                                              onchange="showHideSpecialReviewProtocolLink(this, '${collectionProperty}[${status.index}]', '${canCreateIrbProtocol}', '${canCreateIacucProtocol}','${enableIrbProtocolLinking}','${enableIacucProtocolLinking}');return false" />
					</div></td>
                    <td><div align="center">
                        <kra:dynamicHtmlControlAttribute property="${collectionProperty}[${status.index}].approvalTypeCode" 
	                                                     attributeEntry="${attributes.approvalTypeCode}" 
	                                                     initialReadOnly="${protocolLinkingReadOnly}"
                                                         readOnly="${not canModify}"
                                                         readOnlyBody="true" 
                                                         staticOnly="false">
                            <c:choose>
	                            <c:when test="${protocolLinkingReadOnly}">
	                                ${collectionReference[status.index].protocolStatus}
	                            </c:when>
	                            <c:otherwise>
	                                ${collectionReference[status.index].approvalType.description}
	                            </c:otherwise>
                            </c:choose>
                        </kra:dynamicHtmlControlAttribute>
	                </div></td>
                    <td><div align="center">
                        <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].protocolNumber" 
                                                  attributeEntry="${attributes.protocolNumber}" 
                                                  readOnly="${not canModify}" />
                            <span id="${collectionProperty}[${status.index}].protocolNumber.irb.link.div" style="${initialStyleIrb}">
	                            <kul:lookup boClassName="org.kuali.kra.irb.Protocol" 
		                                    fieldConversions="protocolNumber:${collectionProperty}[${status.index}].protocolNumber" />
                            </span>
                            <span id="${collectionProperty}[${status.index}].protocolNumber.iacuc.link.div" style="${initialStyleIacuc}">
	                            <kul:lookup boClassName="org.kuali.kra.iacuc.IacucProtocol" 
		                                    fieldConversions="protocolNumber:${collectionProperty}[${status.index}].protocolNumber" />
                            </span>
					</div></td>
                    <td align="left" valign="middle"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="${collectionProperty}[${status.index}].applicationDate" 
                                                         attributeEntry="${attributes.applicationDate}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         readOnly="${not canModify}"
                                                         staticOnly="false" />
	                </div></td>
	                <td align="left" valign="middle"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="${collectionProperty}[${status.index}].approvalDate" 
                                                         attributeEntry="${attributes.approvalDate}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         readOnly="${not canModify}"
                                                         staticOnly="false" />
	                </div></td>
	                <td align="left" valign="middle"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="${collectionProperty}[${status.index}].expirationDate" 
                                                         attributeEntry="${attributes.expirationDate}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         readOnly="${not canModify}"
                                                         staticOnly="false"/>
	                </div></td>
	                <td align="left" valign="middle"><div align="center">
                        <kra:dynamicHtmlControlAttribute property="${collectionProperty}[${status.index}].exemptionTypeCodes" 
                                                         attributeEntry="${exemptionAttributes.exemptionTypeCode}" 
                                                         initialReadOnly="${protocolLinkingReadOnly}"
                                                         readOnly="${not canModify}"
                                                         staticOnly="false"/>
	                </div></td>
					<td rowspan="1"><div align=center>
                        <c:if test="${canModify}">
                            <html:image property="methodToCall.deleteSpecialReview.line${status.index}.anchor${currentTabIndex}.validate0"
									    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                        </c:if>
                        <c:if test="${protocolLinkingReadOnly and not empty collectionReference[status.index].protocolNumber}">
                            <html:image property="methodToCall.viewSpecialReviewProtocolLink"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' alt="View Protocol" styleClass="tinybutton"
                                        onclick="javascript: specialReviewProtocolPop(${KualiForm.document.sessionDocument}, '${action}', 'viewSpecialReviewProtocolLink', ${status.index}, ${KualiForm.formKey});return false" />
                        </c:if>
	                </div></td>
	            </tr>
	            <tr>
	            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${attributes.comments}" noColon="false" /></div></th>
	            	<td colspan="7">
		            	<nobr>
	                        <c:choose>
	                            <c:when test="${canModify}">
	                                <kul:htmlControlAttribute property="${collectionProperty}[${status.index}].comments" 
	                                                          attributeEntry="${attributes.comments}"/>
	                            </c:when>
	                            <c:otherwise>
			            		    <kra:truncateComment textAreaFieldName="${collectionProperty}[${status.index}].comments" action="${action}" 
		                                                 textAreaLabel="${attributes.comments.label}" textValue="${specialReview.comments}"  
		                                                 displaySize="${commentDisplayLength}"/>
	                            </c:otherwise>
	                        </c:choose>
	                    </nobr>
	            	</td> 
	            </tr>
        	</c:forEach>
        </table>
    </div> 
</kul:tab>