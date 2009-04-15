<%--
 Copyright 2006-2009 The Kuali Foundation
 
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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="rows" required="true" type="java.util.List"
              description="The rows of fields that we'll iterate to display." %>
<%@ attribute name="numberOfColumns" required="false" 
              description="The # of fields in this row." %>
<%@ attribute name="skipTheOldNewBar" required="false" 
              description="boolean that indicates whether the old and new bar should be skipped" %>
<%@ attribute name="depth" required="false" 
              description="the recursion depth number" %>
<%@ attribute name="rowsHidden" required="false"
              description="boolean that indicates whether the rows should be hidden or all fields are hidden" %>
              
    <kul:rowDisplay rows="${rows}" numberOfColumns="${numberOfColumns}" skipTheOldNewBar="${skipTheOldNewBar}" depth="${depth}" rowsHidden="${rowsHidden}" />          
