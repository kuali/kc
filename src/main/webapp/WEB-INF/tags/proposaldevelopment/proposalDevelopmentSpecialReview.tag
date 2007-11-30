<%--
 Copyright 2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="proSpecialAttriburesAttributes" value="${DataDictionary.ProposalSpecialReview.attributes}" />
<c:set var="action" value="proposalDevelopmentSpecialReview" />
<kul:tabTop tabTitle="Special Review" defaultOpen="true" tabErrorKey="document.propSpecialReview*,newPropSpecialReview*">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Special Review</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        
        <table cellpadding=0 cellspacing=0 summary="">
          	<tr>
          		<th><div align="left">&nbsp</div></th> 
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.specialReviewCode}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.approvalTypeCode}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.protocolNumber}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.applicationDate}" noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.approvalDate}"noColon="true" /></div></th>
          		<th><div align="left"><kul:htmlAttributeLabel attributeEntry="${proSpecialAttriburesAttributes.comments}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
          	
          	</tr>        
             <tr>
                <c:set var="textAreaFieldName" value="newPropSpecialReview.comments" />
				<th class="infoline">
					<c:out value="Add:" />
				</th>

                <td align="left" valign="middle" class="infoline">
                
                	<kul:htmlControlAttribute property="newPropSpecialReview.specialReviewCode" attributeEntry="${proSpecialAttriburesAttributes.specialReviewCode}" styleClass="fixed-size-select"/>
	            
				</td>
                <td class="infoline">
                	<kul:htmlControlAttribute property="newPropSpecialReview.approvalTypeCode" attributeEntry="${proSpecialAttriburesAttributes.approvalTypeCode}" />
                </td>
                <td class="infoline">                	
                  <kul:htmlControlAttribute property="newPropSpecialReview.protocolNumber" attributeEntry="${proSpecialAttriburesAttributes.protocolNumber}" />
                    <!-- <img class="nobord" src="kr/static/images/searchicon.gif" alt="lookup">  
                    <img class="nobord" src="kr/static/images/book_open.png" alt="inquiry"> -->
                 <!--  <kul:lookup boClassName="org.kuali.kra.bo.Protocol" 
                    fieldConversions="protocolNumber:newPropSpecialReview.protocolNumber" anchor="${currentTabIndex}" /> --> 
				</td>
                <td align="left" valign="middle" class="infoline">
                	<kul:htmlControlAttribute property="newPropSpecialReview.applicationDate" attributeEntry="${proSpecialAttriburesAttributes.applicationDate}" datePicker="true"/>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<kul:htmlControlAttribute property="newPropSpecialReview.approvalDate" attributeEntry="${proSpecialAttriburesAttributes.approvalDate}" datePicker="true"/>
                </td>
                <td align="left" valign="middle" class="infoline">
                	<kul:htmlControlAttribute property="newPropSpecialReview.comments" attributeEntry="${proSpecialAttriburesAttributes.comments}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${proSpecialAttriburesAttributes.comments.label}" />
                </td>
				<td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addSpecialReview.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' />
					</div>
                </td>
            </tr>
            
        	<c:forEach var="specialReview" items="${KualiForm.document.propSpecialReviews}" varStatus="status">
	             <tr>
	                <c:set var="textAreaFieldName" value="document.propSpecialReview[${status.index}].comments" />
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].specialReviewCode" attributeEntry="${proSpecialAttriburesAttributes.specialReviewCode}"  styleClass="fixed-size-select"/>
					</td>
	                <td>
	                	<kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].approvalTypeCode" attributeEntry="${proSpecialAttriburesAttributes.approvalTypeCode}" />
	                </td>
	                <td>                	
	                  <kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].protocolNumber" attributeEntry="${proSpecialAttriburesAttributes.protocolNumber}" />
                    <!-- <input type="image" class="nobord" src="kr/static/images/searchicon.gif" alt="lookup">  
                    <input type="image" class="nobord" src="kr/static/images/book_open.png" alt="inquiry">-->
	                <!--  <kul:lookup boClassName="org.kuali.kra.bo.Protocol" 
	                    fieldConversions="protocolNumber:document.propSpecialReview[${status.index}].protocolNumber"  anchor="${tabKey}"/> --> 
					</td>
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].applicationDate" attributeEntry="${proSpecialAttriburesAttributes.applicationDate}" datePicker="true"/>
	                </td>
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].approvalDate" attributeEntry="${proSpecialAttriburesAttributes.approvalDate}" datePicker="true"/>
	                </td>
	                <td align="left" valign="middle">
	                	<kul:htmlControlAttribute property="document.propSpecialReview[${status.index}].comments" attributeEntry="${proSpecialAttriburesAttributes.comments}" />
                        <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${proSpecialAttriburesAttributes.comments.label}" />
	                </td>
					<td>
					<div align=center>
						<html:image property="methodToCall.deleteSpecialReview.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' />
					</div>
	                </td>
	            </tr>
        	</c:forEach>        

            
        </table>
    </div> 
</kul:tabTop>
