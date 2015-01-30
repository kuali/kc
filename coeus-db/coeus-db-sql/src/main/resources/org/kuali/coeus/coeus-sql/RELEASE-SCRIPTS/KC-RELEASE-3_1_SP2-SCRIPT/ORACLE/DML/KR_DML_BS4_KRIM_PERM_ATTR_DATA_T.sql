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

-- specify doc type qualifier for time and money permissions
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Create Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Modify Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Save Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');

COMMIT;
