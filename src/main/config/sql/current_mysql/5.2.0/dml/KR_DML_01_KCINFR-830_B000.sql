DELIMITER /

update KRIM_ROLE_T set KIM_TYP_ID = (select KIM_TYP_ID from KRIM_TYP_T where NM = 'Default') where ROLE_NM = 'Modify Training'
/

DELIMITER ;
