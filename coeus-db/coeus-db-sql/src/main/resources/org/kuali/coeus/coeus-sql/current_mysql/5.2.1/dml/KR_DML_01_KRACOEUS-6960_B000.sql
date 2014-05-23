DELIMITER /

update KRMS_TERM_SPEC_T set DESC_TXT = 'Check cost element version limit' where DESC_TXT = 'Check specified narrative type' and NMSPC_CD = 'KC-PD'
    and NM = (select FUNC_ID from KRMS_FUNC_T where NM = 'costElementVersionLimit')
/

update KRMS_TERM_SPEC_T set DESC_TXT = 'Is fellowship' where DESC_TXT = 'Check specified narrative type' and NMSPC_CD = 'KC-PD'
    and NM = (select FUNC_ID from KRMS_FUNC_T where NM = 'divisionCodeIsFellowship');
/

DELIMITER ;
