DELIMITER /
UPDATE KRIM_ROLE_T
SET DESC_TXT = 'The Negotiation Principal Investigator role'
WHERE ROLE_NM = 'PI'
AND KIM_TYP_ID = 88
/
DELIMITER ;
