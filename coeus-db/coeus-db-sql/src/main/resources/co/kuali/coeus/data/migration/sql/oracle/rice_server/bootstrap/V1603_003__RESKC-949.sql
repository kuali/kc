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
update krcr_parm_t set PARM_DESC_TXT = 'Flag to turn on/off COI disclosure status on proposal development. This is different from KC-PD ENABLE_DISCLOSURE_STATUS_FROM_COI_MODULE and both should not be enabled at the same time.' where parm_nm = 'PROP_PERSON_COI_STATUS_FLAG ' and nmspc_cd = 'KC-GEN';

update krcr_parm_t set VAL = 'N' where PARM_NM = 'ENABLE_DISCLOSURE_STATUS_FROM_COI_MODULE' and NMSPC_CD = 'KC-PD';

update krcr_parm_t set VAL = 'N' where PARM_NM = 'ENABLE_DISCLOSURE_STATUS_FROM_COI_MODULE' and NMSPC_CD = 'KC-AWARD';

update krcr_parm_t set VAL = 'N' where PARM_NM = 'ENABLE_DISCLOSURE_STATUS_FROM_COI_MODULE' and NMSPC_CD = 'KC-IP';
