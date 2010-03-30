delete from eps_prop_pers_ynq where QUESTION_ID in ('H4', 'P004', 'P005', 'P006', 'P6', 'Z1', 'Z2', 'Z3');
DELETE FROM YNQ WHERE QUESTION_TYPE ='I' AND QUESTION_ID IN ('H4', 'P004', 'P005', 'P006', 'P6', 'Z1', 'Z2', 'Z3');
commit;