update NOTIFICATION_TYPE_RECIPIENT t1 set t1.ROLE_NAME = ( 
    select t2.NMSPC_CD || ':'|| t2.ROLE_NM from KRIM_ROLE_T t2 where t2.NMSPC_CD='KC-PD' and upper(t2.ROLE_NM) = upper('Aggregator Document Level')
  )
where t1.NOTIFICATION_TYPE_ID = (
  select t3.NOTIFICATION_TYPE_ID from NOTIFICATION_TYPE t3 where t3.MODULE_CODE=
    (select t4.MODULE_CODE from COEUS_MODULE t4 where upper( t4.DESCRIPTION )= upper('Development Proposal'))
    and t3.ACTION_CODE='103'
 )
 /
 