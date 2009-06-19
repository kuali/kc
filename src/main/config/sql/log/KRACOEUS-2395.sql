DELETE FROM UNIT_ACL T1 WHERE T1.PERSON_ID IN (SELECT PERSON_ID FROM PERSON T WHERE T.USER_NAME IN ('sbear', 'ffrog', 'bbunny', 'vviewer', 'mmodify', 'aagreg', 'ssubmit', 'pcreate', 'aadmin', 'aalters'));
DELETE FROM PERSON T WHERE T.USER_NAME IN ('sbear', 'ffrog', 'bbunny', 'vviewer', 'mmodify', 'aagreg', 'ssubmit', 'pcreate', 'aadmin', 'aalters');
DELETE FROM KIM_PERSON_ATTRIBUTES_T T1 WHERE T1.PERSON_ID IN (SELECT ID FROM KIM_PERSONS_T T WHERE T.USERNAME IN ('sbear', 'ffrog', 'bbunny', 'vviewer', 'mmodify', 'aagreg', 'ssubmit', 'pcreate', 'aadmin', 'aalters')); 
DELETE 
  FROM KIM_PERSON_QUAL_ATTR_T T2
 WHERE T2.ROLE_PERSON_ID IN
       (SELECT ID
          FROM kim_roles_persons_qual_t T1
         WHERE T1.PERSON_ID IN
               (SELECT ID
                  FROM KIM_PERSONS_T T
                 WHERE T.USERNAME IN
                       ('sbear', 'ffrog', 'bbunny', 'vviewer', 'mmodify',
                        'aagreg', 'ssubmit', 'pcreate', 'aadmin', 'aalters')));
DELETE FROM KIM_ROLES_PERSONS_QUAL_T T1 WHERE T1.PERSON_ID IN (SELECT ID FROM KIM_PERSONS_T T WHERE T.USERNAME IN ('sbear', 'ffrog', 'bbunny', 'vviewer', 'mmodify', 'aagreg', 'ssubmit', 'pcreate', 'aadmin', 'aalters')); 
DELETE FROM KIM_PERSONS_T T WHERE T.USERNAME IN ('sbear', 'ffrog', 'bbunny', 'vviewer', 'mmodify', 'aagreg', 'ssubmit', 'pcreate', 'aadmin', 'aalters'); 
DELETE FROM KIM_ROLES_PERMISSIONS_T T1 WHERE T1.ROLE_ID IN (SELECT ID FROM KIM_ROLES_T T WHERE T.NAME LIKE 'TEST%'); 
DELETE FROM KIM_ROLES_T T WHERE T.NAME LIKE 'TEST%';
COMMIT;