insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
values ('KC', 'KR-WKFLW', 'ActionList', 'ACTION_LIST_DOCUMENT_POPUP_IND', SYS_GUID(), 1, 'CONFG', 'N', 'Flag to specify if clicking on a Document ID from the Action List will load the Document in a new window.', 'A');

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
values ('KC', 'KR-WKFLW', 'ActionList', 'ACTION_LIST_ROUTE_LOG_POPUP_IND', SYS_GUID(), 1, 'CONFG', 'N', 'Flag to specify if clicking on a Route Log from the Action List will load the Route Log in a new window.', 'A');

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
values ('KC', 'KR-WKFLW', 'DocSearchCriteriaDTO', 'DOCUMENT_SEARCH_POPUP_IND', SYS_GUID(), 1, 'CONFG', 'N', 'Flag to specify if clicking on a Document ID from Document Search will load the Document in a new window.', 'A');

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
values ('KC', 'KR-WKFLW', 'DocSearchCriteriaDTO', 'DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND', SYS_GUID(), 1, 'CONFG', 'N', 'Flag to specify if clicking on a Route Log from Document Search will load the Route Log in a new window.', 'A');

commit;