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

<channel:portalChannelTop channelTitle="Miscellaneous"/>
<div class="body">

   <ul class="chan">
      <li><a href="${ConfigProperties.application.url}/coiProject.do?methodToCall=publishAll" title="Project Push To COI">Project Push To COI</a></li>
      <li><a href="${ConfigProperties.application.url}/authServicePush.do?methodToCall=pushAll" title="Push Users to the Core Auth Service">Push Users to the Core Auth Service</a></li>
      <li><a href="${ConfigProperties.krad.url}/core/admin/cache?viewId=CacheAdmin-view1&methodToCall=start" title="Cache Admin">Cache Admin</a></li>
   </ul>

</div>
<channel:portalChannelBottom/>
