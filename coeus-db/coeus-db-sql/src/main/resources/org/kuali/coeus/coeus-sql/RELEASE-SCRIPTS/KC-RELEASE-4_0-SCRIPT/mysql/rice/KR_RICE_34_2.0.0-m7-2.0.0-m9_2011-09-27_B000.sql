--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

DELIMITER /

-- Notification PeopleFlowActionType

insert into krms_typ_t
(typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr)
values ('1000', 'notificationPeopleFlowActionType', 'KRMS', 'notificationPeopleFlowActionTypeService', 'Y', 1)
/

-- Approval PeopleFlowActionType

insert into krms_typ_t
(typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr)
values ('1001', 'approvalPeopleFlowActionType', 'KRMS_TEST', 'approvalPeopleFlowActionTypeService', 'Y', 1)
/
DELIMITER ;
