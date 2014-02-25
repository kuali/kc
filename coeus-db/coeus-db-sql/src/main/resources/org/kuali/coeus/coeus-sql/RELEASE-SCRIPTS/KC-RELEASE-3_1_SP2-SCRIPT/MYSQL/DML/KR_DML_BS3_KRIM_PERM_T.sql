-- add standard permissions for time and money document
INSERT INTO KRIM_PERM_ID_BS_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_BS_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'), 'KC-T', 'Create Time And Money Document', 'Initiate a new Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_BS_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_BS_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Edit Document'), 'KC-T', 'Modify Time And Money Document', 'Modify a Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_BS_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_BS_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Save Document'), 'KC-T', 'Save Time And Money Document', 'Save a Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_BS_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_BS_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Route Document'), 'KC-T', 'Submit Time And Money Document', 'Submit a Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_BS_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_BS_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'), 'KC-T', 'Open Time And Money Document', 'Open a Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_BS_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_BS_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Cancel Document'), 'KC-T', 'Cancel Time And Money Document', 'Cancel a Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_BS_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_BS_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'), 'KC-T', 'View Time And Money Document', 'View a Time and Money Document', 'Y');

COMMIT;