--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

SET foreign_key_checks = 0;

-- Institutional Proposal
update krms_typ_t set typ_id = 'RES-BOOT10002' where typ_id = 'KC10002';
update krms_func_t set typ_id = 'RES-BOOT10002' where func_id = 'KC10016';
update krms_func_t set func_id = 'RES-BOOT10016' where func_id = 'KC10016';
update krms_func_parm_t set func_id = 'RES-BOOT10016' where func_parm_id = 'KC10026';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10026' where func_parm_id = 'KC10026';
update krms_term_spec_t set nm = 'RES-BOOT10016' where term_spec_id = 'KC2093';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2093' where term_spec_id = 'KC2093';
update krms_term_t set term_spec_id = 'RES-BOOT2093' where term_id = 'KC10014';
update krms_term_t set term_id = 'RES-BOOT10014' where term_id = 'KC10014';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2093' where term_rslvr_id = 'KC2089';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2089' where term_rslvr_id = 'KC2089';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2093' where cntxt_term_spec_prereq_id = 'KC1033';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1033' where cntxt_term_spec_prereq_id = 'KC1033';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2093' where term_spec_id = 'KC2093' and ctgry_id = 'KC1016';

-- hasSpecialReviewOfType
update krms_func_t set typ_id = 'RES-BOOT10002' where func_id = 'KC10017';
update krms_func_t set func_id = 'RES-BOOT10017' where func_id = 'KC10017';
update krms_func_parm_t set func_id = 'RES-BOOT10017' where func_parm_id = 'KC10027';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10027' where func_parm_id = 'KC10027';
update krms_func_parm_t set func_id = 'RES-BOOT10017' where func_parm_id = 'KC10028';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10028' where func_parm_id = 'KC10028';
update krms_term_spec_t set nm = 'RES-BOOT10017' where term_spec_id = 'KC2094';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2094' where term_spec_id = 'KC2094';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2094' where term_rslvr_id = 'KC2090';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2090' where term_rslvr_id = 'KC2090';
update krms_term_rslvr_parm_spec_t set term_rslvr_id = 'RES-BOOT2090' where term_rslvr_parm_spec_id = 'KC2073';
update krms_term_rslvr_parm_spec_t set term_rslvr_parm_spec_id = 'RES-BOOT2073' where term_rslvr_parm_spec_id = 'KC2073';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1034' where cntxt_term_spec_prereq_id = 'KC1034';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2094' where cntxt_term_spec_prereq_id = 'RES-BOOT1034';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2094' where term_spec_id = 'KC2094' and ctgry_id = 'KC1016';

-- isCurrentFiscalMonth
update krms_func_t set typ_id = 'RES-BOOT10002' where func_id = 'KC10018';
update krms_func_t set func_id = 'RES-BOOT10018' where func_id = 'KC10018';
update krms_func_parm_t set func_id = 'RES-BOOT10018' where func_parm_id = 'KC10029';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10029' where func_parm_id = 'KC10029';
update krms_term_spec_t set nm = 'RES-BOOT10018' where term_spec_id = 'KC2095';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2095' where term_spec_id = 'KC2095';
update krms_term_t set term_spec_id = 'RES-BOOT2095' where term_id = 'KC10015';
update krms_term_t set term_id = 'RES-BOOT10015' where term_id = 'KC10015';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2095' where term_rslvr_id = 'KC2091';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2091' where term_rslvr_id = 'KC2091';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1035' where cntxt_term_spec_prereq_id = 'KC1035';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2095' where cntxt_term_spec_prereq_id = 'RES-BOOT1035';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2095' where term_spec_id = 'KC2095' and ctgry_id = 'KC1016';

-- Award
-- do sponsor and prime sponsor match

update krms_func_t set func_id = 'RES-BOOT10019' where func_id = 'KC10019';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10030' where func_parm_id = 'KC10030';
update krms_func_parm_t set func_id = 'RES-BOOT10019' where func_parm_id = 'RES-BOOT10030';
update krms_term_spec_t set nm = 'RES-BOOT10019' where term_spec_id = 'KC2096';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2096' where term_spec_id = 'KC2096';
update krms_term_t set term_id = 'RES-BOOT10016' where term_id = 'KC10016';
update krms_term_t set term_spec_id = 'RES-BOOT2096' where term_id = 'RES-BOOT10016';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2092' where term_rslvr_id = 'KC2092';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2096' where term_rslvr_id = 'RES-BOOT2092';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1036' where cntxt_term_spec_prereq_id = 'KC1036';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2096' where cntxt_term_spec_prereq_id = 'RES-BOOT1036';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2096' where term_spec_id = 'KC2096' and ctgry_id = 'KC1010';

-- check prop value for previous version
update krms_func_t set func_id = 'RES-BOOT10020' where func_id = 'KC10020';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10031' where func_parm_id = 'KC10031';
update krms_func_parm_t set func_id = 'RES-BOOT10020' where func_parm_id = 'RES-BOOT10031';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10032' where func_parm_id = 'KC10032';
update krms_func_parm_t set func_id = 'RES-BOOT10020' where func_parm_id = 'RES-BOOT10032';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10033' where func_parm_id = 'KC10033';
update krms_func_parm_t set func_id = 'RES-BOOT10020' where func_parm_id = 'RES-BOOT10033';
update krms_term_spec_t set nm = 'RES-BOOT10020' where term_spec_id = 'KC2097';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2097' where term_spec_id = 'KC2097';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2093' where term_rslvr_id = 'KC2093';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2097' where term_rslvr_id = 'RES-BOOT2093';
update krms_term_rslvr_parm_spec_t set term_rslvr_id = 'RES-BOOT2093' where term_rslvr_parm_spec_id = 'KC2074';
update krms_term_rslvr_parm_spec_t set term_rslvr_parm_spec_id = 'RES-BOOT2074' where term_rslvr_parm_spec_id = 'KC2074';
update krms_term_rslvr_parm_spec_t set term_rslvr_id = 'RES-BOOT2093' where term_rslvr_parm_spec_id = 'KC2075';
update krms_term_rslvr_parm_spec_t set term_rslvr_parm_spec_id = 'RES-BOOT2075' where term_rslvr_parm_spec_id = 'KC2075';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1037' where cntxt_term_spec_prereq_id = 'KC1037';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2097' where cntxt_term_spec_prereq_id = 'RES-BOOT1037';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2097' where term_spec_id = 'KC2097' and ctgry_id = 'KC1010';

-- hasPropertyChangedThisVersion
update krms_func_t set func_id = 'RES-BOOT10021' where func_id = 'KC10021';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10034' where func_parm_id = 'KC10034';
update krms_func_parm_t set func_id = 'RES-BOOT10021' where func_parm_id = 'RES-BOOT10034';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10035' where func_parm_id = 'KC10035';
update krms_func_parm_t set func_id = 'RES-BOOT10021' where func_parm_id = 'RES-BOOT10035';
update krms_term_spec_t set nm = 'RES-BOOT10021' where term_spec_id = 'KC2098';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2098' where term_spec_id = 'KC2098';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2094' where term_rslvr_id = 'KC2094';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2098' where term_rslvr_id = 'RES-BOOT2094';
update krms_term_rslvr_parm_spec_t set term_rslvr_id = 'RES-BOOT2094' where term_rslvr_parm_spec_id = 'KC2076';
update krms_term_rslvr_parm_spec_t set term_rslvr_parm_spec_id = 'RES-BOOT2076' where term_rslvr_parm_spec_id = 'KC2076';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1038' where cntxt_term_spec_prereq_id = 'KC1038';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2098' where cntxt_term_spec_prereq_id = 'RES-BOOT1038';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2098' where term_spec_id = 'KC2098' and ctgry_id = 'KC1010';

-- hasSpecialReviewOfType
update krms_func_t set func_id = 'RES-BOOT10022' where func_id = 'KC10022';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10036' where func_parm_id = 'KC10036';
update krms_func_parm_t set func_id = 'RES-BOOT10022' where func_parm_id = 'RES-BOOT10036';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10037' where func_parm_id = 'KC10037';
update krms_func_parm_t set func_id = 'RES-BOOT10022' where func_parm_id = 'RES-BOOT10037';
update krms_term_spec_t set nm = 'RES-BOOT10022' where term_spec_id = 'KC2099';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2099' where term_spec_id = 'KC2099';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2095' where term_rslvr_id = 'KC2095';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2099' where term_rslvr_id = 'RES-BOOT2095';
update krms_term_rslvr_parm_spec_t set term_rslvr_id = 'RES-BOOT2095' where term_rslvr_parm_spec_id = 'KC2077';
update krms_term_rslvr_parm_spec_t set term_rslvr_parm_spec_id = 'RES-BOOT2077' where term_rslvr_parm_spec_id = 'KC2077';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1039' where cntxt_term_spec_prereq_id = 'KC1039';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2099' where cntxt_term_spec_prereq_id = 'RES-BOOT1039';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2099' where term_spec_id = 'KC2099' and ctgry_id = 'KC1010';

-- Proposal Development
update krms_func_t set func_id = 'RES-BOOT10023' where func_id = 'KC10023';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10038' where func_parm_id = 'KC10038';
update krms_func_parm_t set func_id = 'RES-BOOT10023' where func_parm_id = 'RES-BOOT10038';
update krms_term_spec_t set nm = 'RES-BOOT10023' where term_spec_id = 'KC2100';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2100' where term_spec_id = 'KC2100';
update krms_term_t set term_spec_id = 'RES-BOOT2100' where term_id = 'KC10017';
update krms_term_t set term_id = 'RES-BOOT10017' where term_id = 'KC10017';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2096' where term_rslvr_id = 'KC2096';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2100' where term_rslvr_id = 'RES-BOOT2096';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1040' where cntxt_term_spec_prereq_id = 'KC1040';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2100' where cntxt_term_spec_prereq_id = 'RES-BOOT1040';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2100' where term_spec_id = 'KC2100' and ctgry_id = 'KC1001';

-- IRB hasProtocolContainsAmendRenewModule

update krms_func_t set func_id = 'RES-BOOT10024' where func_id = 'KC10024';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10039' where func_parm_id = 'KC10039';
update krms_func_parm_t set func_id = 'RES-BOOT10024' where func_parm_id = 'RES-BOOT10039';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10040' where func_parm_id = 'KC10040';
update krms_func_parm_t set func_id = 'RES-BOOT10024' where func_parm_id = 'RES-BOOT10040';
update krms_term_spec_t set nm = 'RES-BOOT10024' where term_spec_id = 'KC2101';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2101' where term_spec_id = 'KC2101';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2097' where term_rslvr_id = 'KC2097';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2101' where term_rslvr_id = 'RES-BOOT2097';
update krms_term_rslvr_parm_spec_t set term_rslvr_id = 'RES-BOOT2097' where term_rslvr_parm_spec_id = 'KC2078';
update krms_term_rslvr_parm_spec_t set term_rslvr_parm_spec_id = 'RES-BOOT2078' where term_rslvr_parm_spec_id = 'KC2078';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1041' where cntxt_term_spec_prereq_id = 'KC1041';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2101' where cntxt_term_spec_prereq_id = 'RES-BOOT1041';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2101' where term_spec_id = 'KC2101' and ctgry_id = 'KC1004';

-- getProtocolParticipantTypeCount
update krms_func_t set func_id = 'RES-BOOT10025' where func_id = 'KC10025';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10041' where func_parm_id = 'KC10041';
update krms_func_parm_t set func_id = 'RES-BOOT10025' where func_parm_id = 'RES-BOOT10041';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10042' where func_parm_id = 'KC10042';
update krms_func_parm_t set func_id = 'RES-BOOT10025' where func_parm_id = 'RES-BOOT10042';
update krms_term_spec_t set nm = 'RES-BOOT10025' where term_spec_id = 'KC2102';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2102' where term_spec_id = 'KC2102';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2098' where term_rslvr_id = 'KC2098';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2102' where term_rslvr_id = 'RES-BOOT2098';
update krms_term_rslvr_parm_spec_t set term_rslvr_id = 'RES-BOOT2098' where term_rslvr_parm_spec_id = 'KC2079';
update krms_term_rslvr_parm_spec_t set term_rslvr_parm_spec_id = 'RES-BOOT2079' where term_rslvr_parm_spec_id = 'KC2079';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1042' where cntxt_term_spec_prereq_id = 'KC1042';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2102' where cntxt_term_spec_prereq_id = 'RES-BOOT1042';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2102' where term_spec_id = 'KC2102' and ctgry_id = 'KC1004';

-- hasProtocolContainsSponsorType
update krms_func_t set func_id = 'RES-BOOT10026' where func_id = 'KC10026';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10043' where func_parm_id = 'KC10043';
update krms_func_parm_t set func_id = 'RES-BOOT10026' where func_parm_id = 'RES-BOOT10043';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10044' where func_parm_id = 'KC10044';
update krms_func_parm_t set func_id = 'RES-BOOT10026' where func_parm_id = 'RES-BOOT10044';
update krms_term_spec_t set nm = 'RES-BOOT10026' where term_spec_id = 'KC2103';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2103' where term_spec_id = 'KC2103';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2099' where term_rslvr_id = 'KC2099';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2103' where term_rslvr_id = 'RES-BOOT2099';
update krms_term_rslvr_parm_spec_t set term_rslvr_id = 'RES-BOOT2099' where term_rslvr_parm_spec_id = 'KC2080';
update krms_term_rslvr_parm_spec_t set term_rslvr_parm_spec_id = 'RES-BOOT2080' where term_rslvr_parm_spec_id = 'KC2080';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1043' where cntxt_term_spec_prereq_id = 'KC1043';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2103' where cntxt_term_spec_prereq_id = 'RES-BOOT1043';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2103' where term_spec_id = 'KC2103' and ctgry_id = 'KC1004';

-- hasBaseProtocolHasLastApprovalDate
update krms_func_t set func_id = 'RES-BOOT10027' where func_id = 'KC10027';
update krms_func_parm_t set func_parm_id = 'RES-BOOT10045' where func_parm_id = 'KC10045';
update krms_func_parm_t set func_id = 'RES-BOOT10027' where func_parm_id = 'RES-BOOT10045';
update krms_term_spec_t set nm = 'RES-BOOT10027' where term_spec_id = 'KC2104';
update krms_term_spec_t set term_spec_id = 'RES-BOOT2104' where term_spec_id = 'KC2104';
update krms_term_t set term_spec_id = 'RES-BOOT2104' where term_id = 'KC10018';
update krms_term_t set term_id = 'RES-BOOT10018' where term_id = 'KC10018';
update krms_term_rslvr_t set term_rslvr_id = 'RES-BOOT2100' where term_rslvr_id = 'KC2100';
update krms_term_rslvr_t set output_term_spec_id = 'RES-BOOT2104' where term_rslvr_id = 'RES-BOOT2100';
update krms_cntxt_vld_term_spec_t set cntxt_term_spec_prereq_id = 'RES-BOOT1044' where cntxt_term_spec_prereq_id = 'KC1044';
update krms_cntxt_vld_term_spec_t set term_spec_id = 'RES-BOOT2104' where cntxt_term_spec_prereq_id = 'RES-BOOT1044';
update krms_term_spec_ctgry_t set term_spec_id = 'RES-BOOT2104' where term_spec_id = 'KC2104' and ctgry_id = 'KC1004';

SET foreign_key_checks = 1;
