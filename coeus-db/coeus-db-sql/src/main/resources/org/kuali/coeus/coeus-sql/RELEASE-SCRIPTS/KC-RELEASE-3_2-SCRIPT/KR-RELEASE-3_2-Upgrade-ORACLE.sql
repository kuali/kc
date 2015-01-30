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
spool KR-RELEASE-3_2-Upgrade-ORACLE-Install.log
@oracle/dml/KR_DML_01_KCCOI-13_B000.sql
@oracle/dml/KR_DML_01_KCCOI-2_B000.sql
@oracle/dml/KR_DML_01_KCINFR-419_B000.sql
@oracle/dml/KR_DML_01_KCINFR-433_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1299_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1475_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1476_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1484_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1514_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1517_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1531_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1572_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1574_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1575_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1577_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1578_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1622_B000.sql
@oracle/dml/KR_DML_01_KCIRB-1630_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-4936_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-4962_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-4976_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-4979_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-4988_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-5002_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-5004_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-5043_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-5050_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-5058_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-5067_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-5086_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-5087_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-5088_B000.sql
@oracle/dml/KR_DML_01_KRACOEUS-5123_B000.sql
@oracle/dml/KR_DML_02_KCCOI-24_B000.sql
@oracle/dml/KR_DML_02_KCCOI-26_B000.sql
@oracle/dml/KR_DML_02_KCCOI-36_B000.sql
@oracle/dml/KR_DML_02_KCCOI-37_B000.sql
@oracle/dml/KR_DML_02_KCCOI-38_B000.sql
@oracle/dml/KR_DML_02_KCIRB-1567_B000.sql
@oracle/dml/KR_DML_03_KCIRB-1568_B000.sql
@oracle/dml/KR_DML_99_KCINFR-399_B000.sql
commit;
exit
