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
update krim_role_mbr_attr_data_t set kim_typ_id = '10001', KIM_ATTR_DEFN_ID = '10000' where kim_typ_id = '69' and KIM_ATTR_DEFN_ID = '47' and ROLE_MBR_ID =
    (select ROLE_MBR_ID from krim_role_mbr_t where ROLE_ID =
      (select role_id from krim_role_t where NMSPC_CD = 'KC-UNT' and ROLE_NM = 'Sponsor Maintainer')
      and MBR_TYP_CD = 'P' and MBR_ID =
      (select PRNCPL_ID from krim_prncpl_t where PRNCPL_NM = 'quickstart'));

update krim_role_mbr_attr_data_t set kim_typ_id = '10001', KIM_ATTR_DEFN_ID = '10001' where kim_typ_id = '69' and KIM_ATTR_DEFN_ID = '48' and ROLE_MBR_ID =
    (select ROLE_MBR_ID from krim_role_mbr_t where ROLE_ID =
      (select role_id from krim_role_t where NMSPC_CD = 'KC-UNT' and ROLE_NM = 'Sponsor Maintainer')
      and MBR_TYP_CD = 'P' and MBR_ID =
      (select PRNCPL_ID from krim_prncpl_t where PRNCPL_NM = 'quickstart'));