<%--
 Copyright 2008-2009 The Kuali Foundation
 
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

<%@ page session="false" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>


<kul:page docTitle="Ingester" headerMenuBar=""
	headerTitle="Ingester" transactionalDocument="" htmlFormAction="Ingester" renderMultipart="true" lookup="true"
	>

<div id="headerarea-small" class="headerarea-small">
<h1>Ingester</h1>
</div>

<div class="error">

<input class="tinybutton" name="methodToCall.search" src="images/pixel_clear.gif" type="image" border="0" height="0" width="0">

<c:if test="${messages != null}">
  <ul>
    <c:forEach var="message" items="${messages}" >
      <li>
  	    <!-- pre makes stack traces, etc. format correctly -->
        <pre><c:out value="${message}"/></pre>
      </li>
    </c:forEach>
  </ul>
</c:if>

<kul:errors displayRemaining="true"
	errorTitle="Other errors:"
	warningTitle="Other warnings:"
	infoTitle="Other informational messages:"/>

</div>
<div>
<br><br>

	<table class="datatable-center" style="margin-left: auto; margin-right: auto" cellspacing="0" cellpadding="0"
		align="center">
		<tbody>
			<tr>
				<th class="grid" width="Infinity%" align="right">
					<label>XML File:</label></th>
				<td class="grid" width="Infinity%">
				&nbsp;<html-el:file styleClass="dataCell" name="IngesterForm" size="50" property="file[0]" />&nbsp;
				</td>
			</tr>
			<tr>
				<th class="grid" width="Infinity%" align="right">
					<label>XML File:</label>
				</th>
				<td class="grid" width="Infinity%">
				&nbsp;<html-el:file styleClass="dataCell" name="IngesterForm" size="50" property="file[1]" />&nbsp;
				</td>
			</tr>
			<tr>
				<th class="grid" width="Infinity%" align="right">
					<label>XML File:</label>
				</th>
				<td class="grid" width="Infinity%">
				&nbsp;<html-el:file styleClass="dataCell" name="IngesterForm" size="50" property="file[2]" />&nbsp;
				</td>
			</tr>
			<tr>
				<th class="grid" width="Infinity%" align="right">
					<label>XML File:</label>
				</th>
				<td class="grid" width="Infinity%">
				&nbsp;<html-el:file styleClass="dataCell" name="IngesterForm" size="50" property="file[3]" />&nbsp;
				</td>
			</tr>
			<tr>
				<th class="grid" width="Infinity%" align="right">
					<label>XML File:</label>
				</th>
				<td class="grid" width="Infinity%">
				&nbsp;<html-el:file styleClass="dataCell" name="IngesterForm" size="50" property="file[4]" />&nbsp;
				</td>
			</tr>
			<tr>
				<th class="grid" width="Infinity%" align="right">
					<label>XML File:</label>
				</th>
				<td class="grid" width="Infinity%">
				&nbsp;<html-el:file styleClass="dataCell" name="IngesterForm" size="50" property="file[5]" />&nbsp;
				</td>
			</tr>
			<tr>
				<th class="grid" width="Infinity%" align="right">
					<label>XML File:</label>
				</th>
				<td class="grid" width="Infinity%">
				&nbsp;<html-el:file styleClass="dataCell" name="IngesterForm" size="50" property="file[6]" />&nbsp;
				</td>
			</tr>
			<tr>
				<th class="grid" width="Infinity%" align="right">
					<label>XML File:</label>
				</th>
				<td class="grid" width="Infinity%">
				&nbsp;<html-el:file styleClass="dataCell" name="IngesterForm" size="50" property="file[7]" />&nbsp;
				</td>
			</tr>
			<tr>
				<th class="grid" width="Infinity%" align="right">
					<label>XML File:</label>
				</th>
				<td class="grid" width="Infinity%">
				&nbsp;<html-el:file styleClass="dataCell" name="IngesterForm" size="50" property="file[8]" />&nbsp;
				</td>
			</tr>
			<tr>
				<th class="grid" width="Infinity%" align="right">
					<label>XML File:</label>
				</th>
				<td class="grid" width="Infinity%">
				&nbsp;<html-el:file styleClass="dataCell" name="IngesterForm" size="50" property="file[9]" />&nbsp;
				</td>
			</tr>
			<tr align="center">
				<td class="infoline" height="30" colspan="2">
				<div align="center"><html-el:image
					src="images/buttonsmall_uploadxml.gif" value="Upload XML data"
					border="0" styleClass="tinybutton" styleId="imageField" /></div>
				</td>
			</tr>
		</tbody>
	</table>

</div>

</kul:page>
