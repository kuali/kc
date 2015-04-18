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

set define off
set sqlblanklines on
spool KR-RELEASE-6_0_0-Upgrade-ORACLE-Install.log
@./kc/6_0_0/dml/KR_DML_01_KCINFR-979_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KFSMI-11381_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KFSMI-11382_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KFSMI-11535_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-6294_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-6954_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-6956_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-7109_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-7110_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-7274_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-7336_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-7492_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-7512_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-7589_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-7856_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-8001_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-8181_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-8300_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-8380_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS_8550_B000.sql
@./kc/6_0_0/dml/KR_DML_01_KRACOEUS-8732_B000.sql
@./kc/6_0_0/dml/KR_DML_02_KRACOEUS-6294_B000.sql
@./kc/6_0_0/dml/KR_DML_02_KRACOEUS-8716_B000.sql
@./kc/6_0_0/dml/KR_DML_03_KCINFR-979_B000.sql
@./kc/6_0_0/dml/KR_DML_03_KRACOEUS-7014_B000.sql
@./kc/6_0_0/dml/KR_DML_03_KRACOEUS-8105_B000.sql
@./kc/6_0_0/dml/KR_DML_04_KRACOEUS-8105_B000.sql
commit;
exit
