<%--
 Copyright 2007-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>

   <ul>
        <div><strong>Id: ${messageDeliveryId}</strong></div>
		<div><strong>From: </strong>
		<c:set var="sender_counter" value="0" />
		<c:forEach var="sender" items="${senders}">
			<c:if test="${sender_counter > 0}">, <c:out value="${sender.senderName}"/></c:if>
			<c:if test="${sender_counter == 0}"><c:out value="${sender.senderName}"/></c:if>
			<c:set var="sender_counter" value="${sender_counter + 1}" />
		</c:forEach></div>
		<div><strong>Recipients: </strong>
		<c:set var="recip_counter" value="0" />
		<c:forEach var="recipient" items="${recipients}">
			<c:if test="${recip_counter > 0}">
				, <c:out value="${recipient.recipientId}" />
			</c:if>
			<c:if test="${recip_counter == 0}">
				<c:out value="${recipient.recipientId}" />
			</c:if>
			<c:set var="recip_counter" value="${recip_counter + 1}" />
		</c:forEach>
		</div>
		<div><strong>Channel: </strong><c:out
			value="${notification.channel.name}" default="none" /></div>
		<div><strong>Producer: </strong><c:out
			value="${notification.producer.name}" default="none" /></div>
		<div><strong>Type: </strong><c:out
			value="${notification.deliveryType}" default="none" /></div>
		<div><strong>Priority: </strong><c:out
			value="${notification.priority.name}" default="none" /></div>
               <div><strong>Send Date: </strong><c:out
                   value="${notification.sendDateTime}" default="none" /></div>
               <div><strong>Removal Date: </strong><c:out
                   value="${notification.autoRemoveDateTime}" default="none" /></div>
		<br />
		<br />
        <div><strong>Title: </strong><c:out value="${notification.title}" escapeXml="false" /></div>
		<div><strong>Content:</strong></div>
		<div style="padding: 3px; border-style: double;">
			<c:out value="${contenthtml}" escapeXml="false" />
		</div>
		<div style="padding: 3px; align=center;">
        <%-- first test whether this notification is actionable by the current user --%>
        <c:if test="${actionable}">
            <c:if test="${notification.deliveryType == 'ACK' && ! empty messageDeliveryId}">
             
    			   <c:if test="${empty message}">
    			   <a href="<c:url value='DismissMessage.form'>
                              <c:param name='messageDeliveryId' value='${messageDeliveryId}'/>
                              <c:param name='notifId' value='${notification.id}'/>
                              <c:param name='command' value='${command}'/>
                              <c:param name='action' value='ack'/>
                              <c:param name='standaloneWindow' value='${standaloneWindow}'/>
                              </c:url>"><img
    					src="images/buttonsmall_acknowledge.gif" border="0"
    					alt="acknowledge" /></a>
    			   </c:if>
    			   <c:if test="${! empty message}">
    				<strong><c:out value="${message}" /></strong>
    			   </c:if>
    		 </c:if>
             
             <c:if test="${notification.deliveryType == 'FYI' && ! empty messageDeliveryId}">
             
                <c:if test="${empty message}">
                <a href="<c:url value='DismissMessage.form'>
                              <c:param name='messageDeliveryId' value='${messageDeliveryId}'/>
                              <c:param name='notifId' value='${notification.id}'/>
                              <c:param name='command' value='${command}'/>
                              <c:param name='action' value='fyi'/>
                              <c:param name='standaloneWindow' value='${standaloneWindow}'/>
                              </c:url>"><img
                   src="images/buttonsmall_fyi.gif" border="0"
                   alt="fyi" /></a>
                </c:if>
                <c:if test="${! empty message}">
                <strong><c:out value="${message}" /></strong>
                </c:if>
             </c:if>
         </c:if>
         
         <c:if test="${standaloneWindow eq 'true'}">
         	<a href="javascript:self.close()">
	         	<img src="images/buttonsmall_close.gif" border="0" alt="close" />
            </a>

            <a href="${ConfigProperties.workflow.url}/ActionList.do">
                <button onClick="window.location='${ConfigProperties.workflow.url}/ActionList.do'">Back to Action List</button>
            </a>
         </c:if>
        </div>
	</ul>
