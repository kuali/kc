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

spool 400_oracle_rice_server_upgrade.sql.log
@./rice_server/bootstrap/V400_002__KR_RICE_01_1.0.3.2-2.0.0_2010-04-15_B000.sql
@./rice_server/bootstrap/V400_003__KR_RICE_02_1.0.3.2-2.0.0_2010-05-03_B000.sql
@./rice_server/bootstrap/V400_004__KR_RICE_03_1.0.3.2-2.0.0_2010-05-12_B000.sql
@./rice_server/bootstrap/V400_005__KR_RICE_04_1.0.3.2-2.0.0_2011-03-23_B000.sql
@./rice_server/bootstrap/V400_006__KR_RICE_05_1.0.3.2-2.0.0_2011-04-28_B000.sql
@./rice_server/bootstrap/V400_007__KR_RICE_06_1.0.3.2-2.0.0_2011-05-09_B000.sql
@./rice_server/bootstrap/V400_008__KR_RICE_07_1.0.3.2-2.0.0_2011-05-13_B000.sql
@./rice_server/bootstrap/V400_009__KR_RICE_08_1.0.3.2-2.0.0_2011-06-06_B000.sql
@./rice_server/bootstrap/V400_010__KR_RICE_09_1.0.3.2-2.0.0_2011-06-08_B000.sql
@./rice_server/bootstrap/V400_011__KR_RICE_10_KRIM_PERM_T_B000.sql
@./rice_server/bootstrap/V400_012__KR_RICE_11_2.0.0-m5-2.0.0-m7_2011-06-13-m6_B000.sql
@./rice_server/bootstrap/V400_013__KR_RICE_12_2.0.0-m5-2.0.0-m7_2011-06-17-m6_B000.sql
@./rice_server/bootstrap/V400_014__KR_RICE_13_2.0.0-m5-2.0.0-m7_2011-06-21_B000.sql
@./rice_server/bootstrap/V400_015__KR_RICE_14_2.0.0-m5-2.0.0-m7_2011-06-23_B000.sql
@./rice_server/bootstrap/V400_016__KR_RICE_15_2.0.0-m5-2.0.0-m7_2011-06-29_B000.sql
@./rice_server/bootstrap/V400_017__KR_RICE_16_2.0.0-m5-2.0.0-m7_2011-07-07-m6_B000.sql
@./rice_server/bootstrap/V400_018__KR_RICE_17_2.0.0-m5-2.0.0-m7_2011-07-11-m6_B000.sql
@./rice_server/bootstrap/V400_019__KR_RICE_18_2.0.0-m5-2.0.0-m7_2011-07-13_B000.sql
@./rice_server/bootstrap/V400_020__KR_RICE_19_2.0.0-m5-2.0.0-m7_2011-07-22_B000.sql
@./rice_server/bootstrap/V400_021__KR_RICE_20_2.0.0-m5-2.0.0-m7_2011-07-24-m7_B000.sql
@./rice_server/bootstrap/V400_022__KR_RICE_21_2.0.0-m5-2.0.0-m7_2011-07-25-m7_B000.sql
@./rice_server/bootstrap/V400_023__KR_RICE_22_2.0.0-m5-2.0.0-m7_2011-07-28-m7_B000.sql
@./rice_server/bootstrap/V400_024__KR_RICE_23_2.0.0-m5-2.0.0-m7_2011-07-29-m7_B000.sql
@./rice_server/bootstrap/V400_025__KR_RICE_24_2.0.0-m5-2.0.0-m7_2011-07-29_B000.sql
@./rice_server/bootstrap/V400_026__KR_RICE_25_2.0.0-m7-2.0.0-m9_2011-08-11_B000.sql
@./rice_server/bootstrap/V400_027__KR_RICE_26_2.0.0-m7-2.0.0-m9_2011-08-17_B000.sql
@./rice_server/bootstrap/V400_028__KR_RICE_27_2.0.0-m7-2.0.0-m9_2011-08-29_B000.sql
@./rice_server/bootstrap/V400_029__KR_RICE_28_2.0.0-m7-2.0.0-m9_2011-09-07_B000.sql
@./rice_server/bootstrap/V400_030__KR_RICE_29_2.0.0-m7-2.0.0-m9_2011-09-15_B000.sql
@./rice_server/bootstrap/V400_031__KR_RICE_30_2.0.0-m7-2.0.0-m9_2011-09-16_B000.sql
@./rice_server/bootstrap/V400_032__KR_RICE_31_2.0.0-m7-2.0.0-m9_2011-09-18_B000.sql
@./rice_server/bootstrap/V400_033__KR_RICE_32_2.0.0-m7-2.0.0-m9_2011-09-26_B000.sql
@./rice_server/bootstrap/V400_034__KR_RICE_33_2.0.0-m7-2.0.0-m9_2011-09-26b_B000.sql
@./rice_server/bootstrap/V400_035__KR_RICE_34_2.0.0-m7-2.0.0-m9_2011-09-27_B000.sql
@./rice_server/bootstrap/V400_036__KR_RICE_35_2.0.0-m7-2.0.0-m9_2011-09-30_B000.sql
@./rice_server/bootstrap/V400_037__KR_RICE_36_2.0.0-m7-2.0.0-m9_2011-10-03_B000.sql
@./rice_server/bootstrap/V400_038__KR_RICE_37_2.0.0-m9-2.0.0-b2_2011-10-21_B000.sql
@./rice_server/bootstrap/V400_039__KR_RICE_38_2.0.0-m9-2.0.0-b2_2011-10-23_B000.sql
@./rice_server/bootstrap/V400_040__KR_RICE_39_2.0.0-m9-2.0.0-b2_2011-10-24_B000.sql
@./rice_server/bootstrap/V400_041__KR_RICE_40_2.0.0-m9-2.0.0-b2_2011-10-25_B000.sql
@./rice_server/bootstrap/V400_042__KR_RICE_41_2.0.0-m9-2.0.0-b2_2011-10-26_B000.sql
@./rice_server/bootstrap/V400_043__KR_RICE_42_2.0.0-m9-2.0.0-b2_2011-10-27_B000.sql
@./rice_server/bootstrap/V400_044__KR_RICE_43_2.0.0-m9-2.0.0-b2_2011-11-03_B000.sql
@./rice_server/bootstrap/V400_045__KR_RICE_44_2.0.0-m9-2.0.0-b2_2011-11-14_B000.sql
@./rice_server/bootstrap/V400_046__KR_RICE_45_2.0.0-b2-2.0.0-b4_2011-11-23_B000.sql
@./rice_server/bootstrap/V400_047__KR_RICE_46_2.0.0-b2-2.0.0-b4_2011-11-28_B000.sql
@./rice_server/bootstrap/V400_048__KR_RICE_47_2.0.0-b2-2.0.0-b4_2011-11-29_B000.sql
@./rice_server/bootstrap/V400_049__KR_RICE_48_2.0.0-b2-2.0.0-b4_2011-12-07_B000.sql
@./rice_server/bootstrap/V400_050__KR_RICE_49_2.0.0-b2-2.0.0-b4_2011-12-21_B000.sql
@./rice_server/bootstrap/V400_051__KR_RICE_50_2.0.0-b4-2.0.0-b6_2012-01-03_B000.sql
@./rice_server/bootstrap/V400_052__KR_RICE_51_2.0.0-b4-2.0.0-b6_2012-01-04_B000.sql
@./rice_server/bootstrap/V400_053__KR_RICE_52_2.0.0-b4-2.0.0-b6_2012-01-05_B000.sql
@./rice_server/bootstrap/V400_054__KR_RICE_53_2.0.0-b4-2.0.0-b6_2012-01-06_B000.sql
@./rice_server/bootstrap/V400_055__KR_RICE_54_2.0.0-b6-2.0.0-rc1_2012-01-11_B000.sql
@./rice_server/bootstrap/V400_056__KR_RICE_55_2.0.0-b6-2.0.0-rc1_2012-01-18_B000.sql
@./rice_server/bootstrap/V400_057__KR_RICE_56_2.0.0-b6-2.0.0-rc1_2012-01-19_B000.sql
@./rice_server/bootstrap/V400_058__KR_RICE_57_2.0.0-b6-2.0.0-rc1_2012-01-19b_B000.sql
@./rice_server/bootstrap/V400_059__KR_RICE_58_2.0.0-b6-2.0.0-rc1_2012-01-19c_B000.sql
@./rice_server/bootstrap/V400_060__KR_RICE_59_2.0.0-rc1-2.0.0-rc3_2012-01-24_B000.sql
@./rice_server/bootstrap/V400_061__KR_RICE_60_2.0.0-rc1-2.0.0-rc3_2012-01-31_B000.sql
@./rice_server/bootstrap/V400_062__KR_RICE_61_2.0.0-rc1-2.0.0-rc3_2012-02-03_B000.sql
@./rice_server/bootstrap/V400_063__KR_RICE_62_2.0.0-rc1-2.0.0-rc3_2012-02-08_B000.sql
@./rice_server/bootstrap/V400_064__KR_RICE_63_2.0.0-rc3-2.0.0_2012-02-22_B000.sql
@./rice_server/bootstrap/V400_065__KR_RICE_64_2.0.0-rc3-2.0.0_2012-02-23_B000.sql
@./rice_server/bootstrap/V400_066__KR_RICE_99_SEQ_RESET_BS.sql
@./rice_server/bootstrap/V400_067__KR_DML_01_KCCOI-102_B000.sql
@./rice_server/bootstrap/V400_068__KR_DML_01_KCCOI-107_B000.sql
@./rice_server/bootstrap/V400_069__KR_DML_01_KCCOI-182_B000.sql
@./rice_server/bootstrap/V400_070__KR_DML_01_KCCOI-200_B000.sql
@./rice_server/bootstrap/V400_071__KR_DML_01_KCCOI-27_B000.sql
@./rice_server/bootstrap/V400_072__KR_DML_01_KCCOI-46_B000.sql
@./rice_server/bootstrap/V400_073__KR_DML_01_KCCOI-47_B000.sql
@./rice_server/bootstrap/V400_074__KR_DML_01_KCCOI-56_B000.sql
@./rice_server/bootstrap/V400_075__KR_DML_01_KCCOI-60_B000.sql
@./rice_server/bootstrap/V400_076__KR_DML_01_KCCOI-76_B000.sql
@./rice_server/bootstrap/V400_077__KR_DML_01_KCIAC-5_B000.sql
@./rice_server/bootstrap/V400_078__KR_DML_01_KCIAC-7_B000.sql
@./rice_server/bootstrap/V400_079__KR_DML_01_KCINFR-464_B000.sql
@./rice_server/bootstrap/V400_080__KR_DML_01_KCINFR-468_B000.sql
@./rice_server/bootstrap/V400_081__KR_DML_01_KCINFR-483_B000.sql
@./rice_server/bootstrap/V400_082__KR_DML_01_KCINFR-489_B000.sql
@./rice_server/bootstrap/V400_083__KR_DML_01_KCINFR-490_B000.sql
@./rice_server/bootstrap/V400_084__KR_DML_01_KCINFR-491_B000.sql
@./rice_server/bootstrap/V400_085__KR_DML_01_KCINFR-492_B000.sql
@./rice_server/bootstrap/V400_086__KR_DML_01_KCINFR-495_B000.sql
@./rice_server/bootstrap/V400_087__KR_DML_01_KCINFR-537_B000.sql
@./rice_server/bootstrap/V400_088__KR_DML_01_KCINFR-542_B000.sql
@./rice_server/bootstrap/V400_089__KR_DML_01_KCIRB-1664_B000.sql
@./rice_server/bootstrap/V400_090__KR_DML_01_KRACOEUS-4578_B000.sql
@./rice_server/bootstrap/V400_091__KR_DML_01_KRACOEUS-5100_B000.sql
@./rice_server/bootstrap/V400_092__KR_DML_01_KRACOEUS-5101_B000.sql
@./rice_server/bootstrap/V400_093__KR_DML_01_KRACOEUS-5175_B000.sql
@./rice_server/bootstrap/V400_094__KR_DML_01_KRACOEUS-5210_B000.sql
@./rice_server/bootstrap/V400_095__KR_DML_01_KRACOEUS-5221_B000.sql
@./rice_server/bootstrap/V400_096__KR_DML_01_KRACOEUS-5222_B000.sql
@./rice_server/bootstrap/V400_097__KR_DML_01_KRACOEUS-5230_B000.sql
@./rice_server/bootstrap/V400_098__KR_DML_01_KRACOEUS-5238_B000.sql
@./rice_server/bootstrap/V400_099__KR_DML_01_KRACOEUS-5249_B000.sql
@./rice_server/bootstrap/V400_100__KR_DML_01_KRACOEUS-5251_B000.sql
@./rice_server/bootstrap/V400_101__KR_DML_01_KRACOEUS-5254_B000.sql
@./rice_server/bootstrap/V400_102__KR_DML_01_KRACOEUS-5257_B000.sql
@./rice_server/bootstrap/V400_103__KR_DML_01_KRACOEUS-5298_B000.sql
@./rice_server/bootstrap/V400_104__KR_DML_01_KRACOEUS-5311_B000.sql
@./rice_server/bootstrap/V400_105__KR_DML_01_KRACOEUS-5324_B000.sql
@./rice_server/bootstrap/V400_106__KR_DML_01_KRACOEUS-5326_B000.sql
@./rice_server/bootstrap/V400_107__KR_DML_01_KRACOEUS-5418_B000.sql
@./rice_server/bootstrap/V400_108__KR_DML_02_KCCOI-158_B000.sql
@./rice_server/bootstrap/V400_109__KR_DML_02_KCINFR-226_B000.sql
@./rice_server/bootstrap/V400_110__KR_DML_02_KCINFR-493_B000.sql
commit;
