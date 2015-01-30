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
spool KR_RICE-RELEASE-4_0-Upgrade-ORACLE-Install.log
@oracle/rice/KR_RICE_01_1.0.3.2-2.0.0_2010-04-15_B000.sql
@oracle/rice/KR_RICE_02_1.0.3.2-2.0.0_2010-05-03_B000.sql
@oracle/rice/KR_RICE_03_1.0.3.2-2.0.0_2010-05-12_B000.sql
@oracle/rice/KR_RICE_04_1.0.3.2-2.0.0_2011-03-23_B000.sql
@oracle/rice/KR_RICE_05_1.0.3.2-2.0.0_2011-04-28_B000.sql
@oracle/rice/KR_RICE_06_1.0.3.2-2.0.0_2011-05-09_B000.sql
@oracle/rice/KR_RICE_07_1.0.3.2-2.0.0_2011-05-13_B000.sql
@oracle/rice/KR_RICE_08_1.0.3.2-2.0.0_2011-06-06_B000.sql
@oracle/rice/KR_RICE_09_1.0.3.2-2.0.0_2011-06-08_B000.sql
@oracle/rice/KR_RICE_10_KRIM_PERM_T_B000.sql
@oracle/rice/KR_RICE_11_2.0.0-m5-2.0.0-m7_2011-06-13-m6_B000.sql
@oracle/rice/KR_RICE_12_2.0.0-m5-2.0.0-m7_2011-06-17-m6_B000.sql
@oracle/rice/KR_RICE_13_2.0.0-m5-2.0.0-m7_2011-06-21_B000.sql
@oracle/rice/KR_RICE_14_2.0.0-m5-2.0.0-m7_2011-06-23_B000.sql
@oracle/rice/KR_RICE_15_2.0.0-m5-2.0.0-m7_2011-06-29_B000.sql
@oracle/rice/KR_RICE_16_2.0.0-m5-2.0.0-m7_2011-07-07-m6_B000.sql
@oracle/rice/KR_RICE_17_2.0.0-m5-2.0.0-m7_2011-07-11-m6_B000.sql
@oracle/rice/KR_RICE_18_2.0.0-m5-2.0.0-m7_2011-07-13_B000.sql
@oracle/rice/KR_RICE_19_2.0.0-m5-2.0.0-m7_2011-07-22_B000.sql
@oracle/rice/KR_RICE_20_2.0.0-m5-2.0.0-m7_2011-07-24-m7_B000.sql
@oracle/rice/KR_RICE_21_2.0.0-m5-2.0.0-m7_2011-07-25-m7_B000.sql
@oracle/rice/KR_RICE_22_2.0.0-m5-2.0.0-m7_2011-07-28-m7_B000.sql
@oracle/rice/KR_RICE_23_2.0.0-m5-2.0.0-m7_2011-07-29-m7_B000.sql
@oracle/rice/KR_RICE_24_2.0.0-m5-2.0.0-m7_2011-07-29_B000.sql
@oracle/rice/KR_RICE_25_2.0.0-m7-2.0.0-m9_2011-08-11_B000.sql
@oracle/rice/KR_RICE_26_2.0.0-m7-2.0.0-m9_2011-08-17_B000.sql
@oracle/rice/KR_RICE_27_2.0.0-m7-2.0.0-m9_2011-08-29_B000.sql
@oracle/rice/KR_RICE_28_2.0.0-m7-2.0.0-m9_2011-09-07_B000.sql
@oracle/rice/KR_RICE_29_2.0.0-m7-2.0.0-m9_2011-09-15_B000.sql
@oracle/rice/KR_RICE_30_2.0.0-m7-2.0.0-m9_2011-09-16_B000.sql
@oracle/rice/KR_RICE_31_2.0.0-m7-2.0.0-m9_2011-09-18_B000.sql
@oracle/rice/KR_RICE_32_2.0.0-m7-2.0.0-m9_2011-09-26_B000.sql
@oracle/rice/KR_RICE_33_2.0.0-m7-2.0.0-m9_2011-09-26b_B000.sql
@oracle/rice/KR_RICE_34_2.0.0-m7-2.0.0-m9_2011-09-27_B000.sql
@oracle/rice/KR_RICE_35_2.0.0-m7-2.0.0-m9_2011-09-30_B000.sql
@oracle/rice/KR_RICE_36_2.0.0-m7-2.0.0-m9_2011-10-03_B000.sql
@oracle/rice/KR_RICE_37_2.0.0-m9-2.0.0-b2_2011-10-21_B000.sql
@oracle/rice/KR_RICE_38_2.0.0-m9-2.0.0-b2_2011-10-23_B000.sql
@oracle/rice/KR_RICE_39_2.0.0-m9-2.0.0-b2_2011-10-24_B000.sql
@oracle/rice/KR_RICE_40_2.0.0-m9-2.0.0-b2_2011-10-25_B000.sql
@oracle/rice/KR_RICE_41_2.0.0-m9-2.0.0-b2_2011-10-26_B000.sql
@oracle/rice/KR_RICE_42_2.0.0-m9-2.0.0-b2_2011-10-27_B000.sql
@oracle/rice/KR_RICE_43_2.0.0-m9-2.0.0-b2_2011-11-03_B000.sql
@oracle/rice/KR_RICE_44_2.0.0-m9-2.0.0-b2_2011-11-14_B000.sql
@oracle/rice/KR_RICE_45_2.0.0-b2-2.0.0-b4_2011-11-23_B000.sql
@oracle/rice/KR_RICE_46_2.0.0-b2-2.0.0-b4_2011-11-28_B000.sql
@oracle/rice/KR_RICE_47_2.0.0-b2-2.0.0-b4_2011-11-29_B000.sql
@oracle/rice/KR_RICE_48_2.0.0-b2-2.0.0-b4_2011-12-07_B000.sql
@oracle/rice/KR_RICE_49_2.0.0-b2-2.0.0-b4_2011-12-21_B000.sql
@oracle/rice/KR_RICE_50_2.0.0-b4-2.0.0-b6_2012-01-03_B000.sql
@oracle/rice/KR_RICE_51_2.0.0-b4-2.0.0-b6_2012-01-04_B000.sql
@oracle/rice/KR_RICE_52_2.0.0-b4-2.0.0-b6_2012-01-05_B000.sql
@oracle/rice/KR_RICE_53_2.0.0-b4-2.0.0-b6_2012-01-06_B000.sql
@oracle/rice/KR_RICE_54_2.0.0-b6-2.0.0-rc1_2012-01-11_B000.sql
@oracle/rice/KR_RICE_55_2.0.0-b6-2.0.0-rc1_2012-01-18_B000.sql
@oracle/rice/KR_RICE_56_2.0.0-b6-2.0.0-rc1_2012-01-19_B000.sql
@oracle/rice/KR_RICE_57_2.0.0-b6-2.0.0-rc1_2012-01-19b_B000.sql
@oracle/rice/KR_RICE_58_2.0.0-b6-2.0.0-rc1_2012-01-19c_B000.sql
@oracle/rice/KR_RICE_59_2.0.0-rc1-2.0.0-rc3_2012-01-24_B000.sql
@oracle/rice/KR_RICE_60_2.0.0-rc1-2.0.0-rc3_2012-01-31_B000.sql
@oracle/rice/KR_RICE_61_2.0.0-rc1-2.0.0-rc3_2012-02-03_B000.sql
@oracle/rice/KR_RICE_62_2.0.0-rc1-2.0.0-rc3_2012-02-08_B000.sql
@oracle/rice/KR_RICE_63_2.0.0-rc3-2.0.0_2012-02-22_B000.sql
@oracle/rice/KR_RICE_64_2.0.0-rc3-2.0.0_2012-02-23_B000.sql
@oracle/rice/KR_RICE_99_SEQ_RESET_BS.sql
commit;
exit
