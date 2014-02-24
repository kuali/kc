DELIMITER /
--
--

UPDATE KRIM_ROLE_T
SET DESC_TXT = 'The Negotiation Principal Investigator role'
WHERE ROLE_NM = 'PI'
AND KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Derived Role: Protocol Affiliate Type')
/

DELIMITER ;
