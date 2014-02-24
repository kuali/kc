-- specify doc type qualifier for time and money permissions
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Create Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Modify Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Save Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_BS_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), '3', '13', 'TimeAndMoneyDocument');

COMMIT;