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
update KRMS_TERM_SPEC_T set NMSPC_CD='KC-B',TYP='java.math.BigDecimal' where NM='totalCost'
/
update KRMS_TERM_SPEC_T set NMSPC_CD='KC-B',TYP='java.math.BigDecimal' where NM='totalDirectCost'
/
update KRMS_TERM_SPEC_T set NMSPC_CD='KC-B',TYP='java.math.BigDecimal' where NM='totalIndirectCost'
/
update KRMS_TERM_SPEC_T set NMSPC_CD='KC-B',TYP='java.math.BigDecimal' where NM='costShareAmount'
/
update KRMS_TERM_SPEC_T set NMSPC_CD='KC-B',TYP='java.math.BigDecimal' where NM='underrecoveryAmount'
/
update KRMS_TERM_SPEC_T set NMSPC_CD='KC-B',TYP='java.math.BigDecimal' where NM='totalCostInitial'
/
update KRMS_TERM_SPEC_T set NMSPC_CD='KC-B',TYP='java.math.BigDecimal' where NM='totalDirectCostLimit'
/
update KRMS_TERM_SPEC_T set NM='programAnnouncementNumber' where NM='opportunityId'
/
update KRMS_TERM_SPEC_T set NM='activityTypeCode' where NM='activityType'
/
update KRMS_TERM_SPEC_T set NM='ownedByUnitNumber' where NM='leadUnit'
/
update KRMS_TERM_SPEC_T set NM='proposalTypeCode' where NM='proposalType'
/
update KRMS_TERM_SPEC_T set NM='anticipatedAwardTypeCode' where NM='anticipatedAwardType'
/
update KRMS_TERM_SPEC_T set NM='referenceNumber1' where NM='protocolRefNum1'
/
update KRMS_TERM_SPEC_T set NM='referenceNumber2' where NM='protocolRefNum2'
/
update KRMS_TERM_SPEC_T set NM='referenceNumber1' where NM='iacucRefNum1'
/
update KRMS_TERM_SPEC_T set NM='referenceNumber2' where NM='iacucRefNum2'
/

DELIMITER ;
