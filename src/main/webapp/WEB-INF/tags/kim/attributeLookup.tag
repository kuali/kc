<%--
 Copyright 2005-2009 The Kuali Foundation
 
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="attributeDefinitions" required="true" type="org.kuali.rice.kim.bo.types.dto.AttributeDefinitionMap" description="The map of KIM Attribute Definition names to data dictionary attribute definitions." %>
<%@ attribute name="pathPrefix" required="true" description="The prefix of values that are returned.  For instance, if the pathPrefix is \"document\" and the lookup is on organizationCode, the lookup will populate document.organizationCode in the form." %>
<%@ attribute name="attr" required="true" type="org.kuali.rice.kns.datadictionary.KimDataDictionaryAttributeDefinition" description="The data dictionary definition of the attribute this lookup will help populate." %>
     		         <%--  handling multiple lookup --%>
     		          <c:set var="fieldConversion" value=""/>
     		          <c:set var="params" value=""/>
       		  <c:forEach var="attrDefn" items="${attributeDefinitions}" varStatus="defidx">
       		    <c:set var="searchStr" value="${attrDefn.value.name}" />
        		   <c:forEach items="${attr.lookupReturnPropertyConversions}" var="lookupReturn" varStatus="lookupIdx">
        		    <c:if test="${lookupReturn.key == searchStr}">
      		           <c:set var="fieldConversion" value="${fieldConversion},${searchStr}:${pathPrefix}.qualifiers[${defidx.index}].attrVal"/>        		    
        			</c:if>    
        		    <c:if test="${lookupReturn.key != searchStr and lookupReturn.value == searchStr}">
      		           <c:set var="fieldConversion" value="${fieldConversion},${lookupReturn.key}:${pathPrefix}.qualifiers[${defidx.index}].attrVal"/>        		    
        			</c:if>    
        		   </c:forEach>
     
        		   <c:forEach items="${attr.lookupInputPropertyConversions}" var="lookupInput" varStatus="lookupIdx">
        		    <c:if test="${lookupInput.key == searchStr}">
        		   <%-- TODO : replace searstr with fielname for testing --%> 

      		          <c:set var="params" value="${params},${pathPrefix}.qualifiers[${defidx.index}].attrVal:${lookupInput.value}"/>
        		    
        			</c:if>    
        		   </c:forEach>
       		   </c:forEach>
     		          <c:set var="fieldConversion" value="${fn:substringAfter(fieldConversion, ',')}"/>
     		          <c:set var="params" value="${fn:substringAfter(params, ',')}"/>
            <c:if test="${attr.hasLookupBoDefinition}">      
    			<kul:lookup boClassName="${attr.lookupBoClass}" 
    			     fieldConversions="${fieldConversion}"
    			     lookupParameters="${params}" />
            </c:if>
