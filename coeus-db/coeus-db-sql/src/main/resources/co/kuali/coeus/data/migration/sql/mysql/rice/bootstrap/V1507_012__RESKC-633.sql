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

insert into krms_cntxt_vld_actn_typ_s values(null);

insert into krms_cntxt_vld_actn_typ_t (cntxt_vld_actn_id,cntxt_id,actn_typ_id,ver_nbr)
  values (concat('KC',(select max(id) from krms_cntxt_vld_actn_typ_s)),'KC-PD-CONTEXT','1000',1);

insert into krms_cntxt_vld_actn_typ_s values(null);

insert into krms_cntxt_vld_actn_typ_t (cntxt_vld_actn_id,cntxt_id,actn_typ_id,ver_nbr)
  values (concat('KC',(select max(id) from krms_cntxt_vld_actn_typ_s)),'KC-PD-CONTEXT','1001',1);

insert into krms_cntxt_vld_actn_typ_s values(null);

insert into krms_cntxt_vld_actn_typ_t (cntxt_vld_actn_id,cntxt_id,actn_typ_id,ver_nbr)
  values (concat('KC',(select max(id) from krms_cntxt_vld_actn_typ_s)),'KC-PD-CONTEXT','KC1009',1);

insert into krms_cntxt_vld_actn_typ_s values(null);

insert into krms_cntxt_vld_actn_typ_t (cntxt_vld_actn_id,cntxt_id,actn_typ_id,ver_nbr)
  values (concat('KC',(select max(id) from krms_cntxt_vld_actn_typ_s)),'KC-BUDGET-CONTEXT','1000',1);

insert into krms_cntxt_vld_actn_typ_s values(null);

insert into krms_cntxt_vld_actn_typ_t (cntxt_vld_actn_id,cntxt_id,actn_typ_id,ver_nbr)
  values (concat('KC',(select max(id) from krms_cntxt_vld_actn_typ_s)),'KC-BUDGET-CONTEXT','1001',1);