<%--
 Copyright 2006-2007 The Kuali Foundation.
 
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

<%@ attribute name="attributeEntry" required="false" type="java.util.Map"
              description="The entry of an attribute in the DataDictionary to use to generate the label." %>
<%@ attribute name="attributeEntryName" required="false"
              description="The full name of the DataDictionary entry to use as the attributeEntry.
              E.g., 'DataDictionary.Budget.attributes.budgetProjectDirectorUniversalIdentifier'." %>
<%@ attribute name="literalLabel" required="false"
              description="A String to use for the label instead of the DataDictionary." %>

<%@ attribute name="horizontal" required="false" description="boolean to orient this header horizontally" %>
<%@ attribute name="align" required="false" description="overrides horizontal alignment" %>
<%@ attribute name="rowspan" required="false" description="defaults to 1" %>
<%@ attribute name="colspan" required="false" description="defaults to 1" %>
<%@ attribute name="scope" required="false" description="defaults to no scope attribute" %>
<%@ attribute name="width" required="false" description="defaults to no width attribute" %>
<%@ attribute name="labelFor" required="false" description="relates the label to an input control" %>
<%@ attribute name="forceRequired" required="false" description="indicate the field is required despite the DataDictionary" %>
<%@ attribute name="hideRequiredAsterisk" required="false" description="if set to true, this will hide the required asterisk symbol under all situations" %>
<%@ attribute name="anchor" required="false" description="adds a named anchor inside the header cell" %>
 
<c:set var="scopeAttribute" value='scope="${scope}"'/>  <%-- this works for HTML output (but not for JSP execution) --%>
<c:set var="alignAttribute" value='align="${align}"'/>
<c:set var="widthAttribute" value='width="${width}"'/>

<th
    rowspan="${empty rowspan ? 1 : rowspan}"
    colspan="${empty colspan ? 1 : colspan}"
    ${empty scope ? '' : scopeAttribute}
    ${empty align ? (!horizontal ? '' : 'align="right"') : alignAttribute}
    ${empty width ? '' : widthAttribute}
    >&nbsp;
    <c:if test="${not empty anchor}">
    	<a name="${anchor}"></a>
    </c:if>
    <%-- Why does the alignment seem to default to center instead of left?  I don't see it in kuali.css.
        Should this be done with a CSS style instead?  --%>
    <c:choose>
        <c:when test="${empty attributeEntry && empty attributeEntryName}">
            ${literalLabel}
        </c:when>
        <c:otherwise>
            <kul:htmlAttributeLabel
                attributeEntry="${attributeEntry}"
                attributeEntryName="${attributeEntryName}"
                useShortLabel="true"
                noColon="${!horizontal}"
                labelFor="${labelFor}"
                forceRequired="${forceRequired}" 
                readOnly="${hideRequiredAsterisk}" 
                />
        </c:otherwise>
    </c:choose>
    <jsp:doBody/>  <%-- accountingLineRow.tag puts hidden fields inside the <th> here to keep the HTML valid. --%>
</th>
