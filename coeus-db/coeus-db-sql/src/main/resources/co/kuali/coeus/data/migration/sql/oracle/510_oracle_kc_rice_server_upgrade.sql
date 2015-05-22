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

spool 510_oracle_kc_rice_server_upgrade.sql.log
@./rice/bootstrap/V510_015__KR_DML_01_KCCOI-268_B000.sql
@./rice/bootstrap/V510_016__KR_DML_01_KCIAC-448_B000.sql
@./rice/bootstrap/V510_017__KR_DML_01_KCINFR-686_B000.sql
@./rice/bootstrap/V510_018__KR_DML_01_KCINFR-694_B000.sql
@./rice/bootstrap/V510_019__KR_DML_01_KCINFR-757_B000.sql
@./rice/bootstrap/V510_020__KR_DML_01_KRACOEUS-4686_B000.sql
@./rice/bootstrap/V510_021__KR_DML_01_KRACOEUS-5599_B000.sql
@./rice/bootstrap/V510_022__KR_DML_01_KRACOEUS-5612_B000.sql
@./rice/bootstrap/V510_023__KR_DML_01_KRACOEUS-5618_B000.sql
@./rice/bootstrap/V510_024__KR_DML_01_KRACOEUS-5663_B000.sql
@./rice/bootstrap/V510_025__KR_DML_01_KRACOEUS-5664_B000.sql
@./rice/bootstrap/V510_026__KR_DML_01_KRACOEUS-5762_B000.sql
@./rice/bootstrap/V510_027__KR_DML_01_KRACOEUS-5851_B000.sql
@./rice/bootstrap/V510_028__KR_DML_01_KRACOEUS-5873_B000.sql
@./rice/bootstrap/V510_029__KR_DML_01_KRACOEUS-5879_B000.sql
@./rice/bootstrap/V510_030__KR_DML_01_KRACOEUS-5881_B000.sql
@./rice/bootstrap/V510_031__KR_DML_01_KRACOEUS-5882_B000.sql
@./rice/bootstrap/V510_032__KR_DML_01_KRACOEUS-6001_B000.sql
@./rice/bootstrap/V510_033__KR_DML_01_KRACOEUS-6031_B000.sql
@./rice/bootstrap/V510_034__KR_DML_01_KRACOEUS-6059_B000.sql
@./rice/bootstrap/V510_035__KR_DML_01_KRACOEUS-6060_B000.sql
@./rice/bootstrap/V510_036__KR_DML_01_KRACOEUS-6078_B000.sql
@./rice/bootstrap/V510_037__KR_DML_01_KRACOEUS-6108_B000.sql
@./rice/bootstrap/V510_038__KR_DML_01_KRACOEUS-6115_B000.sql
@./rice/bootstrap/V510_039__KR_DML_01_KRACOEUS-6129_B000.sql
@./rice/bootstrap/V510_040__KR_DML_01_KRACOEUS-6178_B000.sql
@./rice/bootstrap/V510_041__KR_DML_01_KRACOEUS-6201_B000.sql
@./rice/bootstrap/V510_042__KR_DML_01_KRACOEUS-6207_B000.sql
@./rice/bootstrap/V510_043__KR_DML_01_KRACOEUS-6220_B000.sql
@./rice/bootstrap/V510_044__KR_DML_01_KRACOEUS-6232_B000.sql
@./rice/bootstrap/V510_045__KR_DML_01_KRACOEUS-6237_B000.sql
@./rice/bootstrap/V510_046__KR_DML_01_KRACOEUS-6242_B000.sql
@./rice/bootstrap/V510_047__KR_DML_01_KRACOEUS-6360_B000.sql
@./rice/bootstrap/V510_048__KR_DML_01_KRACOEUS-6363_B000.sql
@./rice/bootstrap/V510_049__KR_DML_02_KCINFR-597_B000.sql
@./rice/bootstrap/V510_050__KR_DML_02_KRACOEUS-5873_B000.sql
@./rice/bootstrap/V510_051__KR_DML_02_KRACOEUS-6232_B000.sql
@./rice/bootstrap/V510_052__KR_DML_04_KRACOEUS-5873_B000.sql
@./rice/bootstrap/V510_053__KR_DML_04_KRACOEUS-6232_B000.sql
commit;
