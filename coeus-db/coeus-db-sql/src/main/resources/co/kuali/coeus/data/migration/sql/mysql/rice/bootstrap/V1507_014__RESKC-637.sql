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

insert into krim_role_perm_id_S values(null);

insert into krim_role_perm_t (role_perm_id,obj_id,ver_nbr,role_id,perm_id,actv_ind)
  values (concat('KC',(select max(id) from krim_role_perm_id_s)),uuid(),1,(select role_id from krim_role_t where role_nm = 'IACUC Administrator '),(select perm_id from krim_perm_t where nm = 'Create IACUC Amendment'),'Y');