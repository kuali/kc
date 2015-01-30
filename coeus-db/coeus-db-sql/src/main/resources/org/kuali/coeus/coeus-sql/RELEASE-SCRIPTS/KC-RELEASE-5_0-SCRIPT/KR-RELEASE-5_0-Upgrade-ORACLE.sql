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
spool KR-RELEASE-5_0-Upgrade-ORACLE-Install.log
@../../current/5.0/dml/KR_DML_01_KCCOI-250_B000.sql
@../../current/5.0/dml/KR_DML_01_KCIAC-10_B000.sql
@../../current/5.0/dml/KR_DML_01_KCIAC-13_B000.sql
@../../current/5.0/dml/KR_DML_01_KCIAC-183_B000.sql
@../../current/5.0/dml/KR_DML_01_KCIAC-18_B000.sql
@../../current/5.0/dml/KR_DML_01_KCIAC-37_B000.sql
@../../current/5.0/dml/KR_DML_01_KCIAC-40_B000.sql
@../../current/5.0/dml/KR_DML_01_KCIAC-45_B000.sql
@../../current/5.0/dml/KR_DML_01_KCINFR-472_B000.sql
@../../current/5.0/dml/KR_DML_01_KCINFR-553_B000.sql
@../../current/5.0/dml/KR_DML_01_KCINFR-554_B000.sql
@../../current/5.0/dml/KR_DML_01_KCINFR-558_B000.sql
@../../current/5.0/dml/KR_DML_01_KCINFR-566_B000.sql
@../../current/5.0/dml/KR_DML_01_KCINFR-567_B000.sql
@../../current/5.0/dml/KR_DML_01_KCINFR-577_B000.sql
@../../current/5.0/dml/KR_DML_01_KCINFR-580_B000.sql
@../../current/5.0/dml/KR_DML_01_KCINFR-582_B000.sql
@../../current/5.0/dml/KR_DML_01_KCINFR-611_B000.sql
@../../current/5.0/dml/KR_DML_01_KCINFR-614_B000.sql
@../../current/5.0/dml/KR_DML_01_KCIRB-1795_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-4374_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-4757_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-4760_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-5223_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-5224_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-5240_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-5253_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-5254_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-5256_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-5257_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-5326_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-5476_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-5488_B000.sql
@../../current/5.0/dml/KR_DML_01_KRACOEUS-5527_B000.sql
@../../current/5.0/dml/KR_DML_02_KCCOI-268_B000.sql
@../../current/5.0/dml/KR_DML_02_KCIAC-110_B000.sql
@../../current/5.0/dml/KR_DML_02_KCIAC-134_B000.sql
@../../current/5.0/dml/KR_DML_02_KCIAC-139_B000.sql
@../../current/5.0/dml/KR_DML_02_KCIAC-16_B000.sql
@../../current/5.0/dml/KR_DML_02_KCIAC-181_B000.sql
@../../current/5.0/dml/KR_DML_02_KCIAC-19_B000.sql
@../../current/5.0/dml/KR_DML_02_KCIAC-55_B000.sql
@../../current/5.0/dml/KR_DML_02_KCINFR-380_B000.sql
@../../current/5.0/dml/KR_DML_02_KCINFR-559_B000.sql
@../../current/5.0/dml/KR_DML_02_KCINFR-568_B000.sql
@../../current/5.0/dml/KR_DML_02_KCINFR-596_B000.sql
@../../current/5.0/dml/KR_DML_02_KCINFR-617_B000.sql
@../../current/5.0/dml/KR_DML_02_KCINFR-622_B000.sql
@../../current/5.0/dml/KR_DML_03_KCIAC-59_B000.sql
@../../current/5.0/dml/KR_DML_03_KCINFR-616_B000.sql
@../../current/5.0/dml/KR_DML_03_KRACOEUS-5493_B000.sql
@../../current/5.0/dml/KR_DML_04_KRACOEUS-5493_B000.sql
commit;
exit
