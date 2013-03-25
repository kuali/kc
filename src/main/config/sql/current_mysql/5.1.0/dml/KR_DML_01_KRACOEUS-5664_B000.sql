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
