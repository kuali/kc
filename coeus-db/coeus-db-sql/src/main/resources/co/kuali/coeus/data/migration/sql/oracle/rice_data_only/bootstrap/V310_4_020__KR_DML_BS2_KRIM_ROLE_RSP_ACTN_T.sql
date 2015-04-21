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

update krim_role_rsp_actn_t t set frc_actn = 'Y' where role_rsp_id in 
    (select role_rsp_id from krim_role_rsp_t where role_id = (select role_id from krim_role_t where nmspc_cd = 'KC-PROTOCOL' and role_nm = 'IRB Online Reviewer') 
                                               and rsp_id = (select rsp_id from krim_rsp_t where nmspc_cd = 'KC-WKFLW' and nm = 'IRB Reviewer Approve Online Review'));
