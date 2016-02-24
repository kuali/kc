<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   -
   - Copyright 2005-2016 Kuali, Inc.
   -
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   -
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   -
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="shortUrl" required="true" type="java.lang.String" description="short url" %>

<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script>
    var $j = jQuery.noConflict();
    $j(function() {
        $j( "#dialog" ).dialog({
            autoOpen: false
        });

        $j( "#opener" ).click(function() {
            $j( "#dialog" ).dialog( "open" );
            $j("#shortUrlText").select();
        });
    });
</script>

<div style ="min-height:50px" id="dialog" title="Short Url">
    <input id="shortUrlText" style="width:100%" type="text" value="${shortUrl}"/>
</div>

<div style="display:inline;cursor:pointer" id="opener"><i style="font-size:15px" class="material-icons">link</i></div>