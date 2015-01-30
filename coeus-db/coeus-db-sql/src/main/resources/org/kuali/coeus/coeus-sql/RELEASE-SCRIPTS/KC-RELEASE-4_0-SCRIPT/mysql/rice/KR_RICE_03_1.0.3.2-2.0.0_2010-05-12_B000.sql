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

alter table kren_chnl_subscrp_t add OBJ_ID varchar(36)
/
alter table kren_cntnt_typ_t add OBJ_ID varchar(36) 
/
alter table kren_chnl_t add OBJ_ID varchar(36) 
/
alter table kren_ntfctn_msg_deliv_t add OBJ_ID varchar(36) 
/
alter table kren_ntfctn_t add OBJ_ID varchar(36) 
/
alter table kren_prio_t add OBJ_ID varchar(36) 
/
alter table kren_prodcr_t add OBJ_ID varchar(36) 
/
alter table kren_recip_list_t add OBJ_ID varchar(36)
/
alter table kren_sndr_t add OBJ_ID varchar(36)
/
alter table kren_recip_t add OBJ_ID varchar(36) 
/
alter table kren_rvwer_t add OBJ_ID varchar(36) 
/
alter table kren_chnl_subscrp_t add ver_nbr decimal(8)
/
alter table kren_recip_list_t add ver_nbr decimal(8)
/
alter table kren_sndr_t add ver_nbr decimal(8)
/
alter table kren_recip_t add ver_nbr decimal(8)
/
DELIMITER ;
