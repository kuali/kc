<%--
 Copyright 2005-2006 The Kuali Foundation.
 
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>


<%@ attribute name="attributeEntry" required="true" type="java.util.Map" %>
<%@ attribute name="property" required="true" %>


<kul:checkErrors keyMatch="${property}"/>
<c:if test="${hasErrors==true}">
  <c:set var="textStyle" value="border-color: red"/>
</c:if>

<c:if test="${attributeEntry.control.text != true}">
  <font color="red">The given attributeEntry does not specify a text control</font>
</c:if>
<c:if test="${attributeEntry.control.text == true}">
    <html:text property="${property}" styleId="${property}" size="${attributeEntry.control.size}" maxlength="${attributeEntry.maxLength}" style="${textStyle}"/>
    <img src="${ConfigProperties.kr.externalizable.images.url}cal.gif" id="${property}_datepicker" style="cursor: pointer;" alt="Date selector" title="Date selector" onmouseover="this.style.background='red';" onmouseout="this.style.background='';" />    
    <script type="text/javascript">
      Calendar.setup(
      {
         inputField : "${property}", // ID of the input field
         ifFormat : "%m/%d/%Y", // the date format
         button : "${property}_datepicker" // ID of the button
      }
      );
    </script>
</c:if>
