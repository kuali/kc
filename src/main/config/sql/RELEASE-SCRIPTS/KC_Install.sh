#!/bin/sh

if [ ! -d LOGS ]
then
	mkdir LOGS
fi

getChoice() {
	prompt="$1"
	shift
	choices=$*
	chosen=0
	while [ -z "${chosen}" ] || [ "${chosen}" -lt 1 ] || [ "${chosen}" -gt $# ]
	do
		echo "${prompt}" 1>&2
		choiceNum=0
		for thisChoice in ${choices}
		do
			choiceNum=`expr ${choiceNum} + 1`
			echo "${choiceNum}. ${thisChoice}" 1>&2
		done
		printf 'Choice: ' 1>&2
		read chosen
	done
	echo "$choices" | cut -d' ' -f${chosen}
	echo 1>&2
	}

getAnswer() {
	answer=''
	while [ -z "${answer}" ]
	do
		printf "$1: " 1>&2
		read answer
	done
	echo "${answer}"
	echo 1>&2
	}	

mode=`getChoice 'Enter Rice Mode' BUNDLE EMBED`

if [ "${mode}" = "EMBED" ]
then
	InstRice=`getChoice 'Install/Upgrade Embedded Rice Server Side' Y N`
fi

dbtype=`getChoice 'Enter Database Type' ORACLE MYSQL`

version=`getChoice 'Enter Version' NEW 3.0 3.0.1 3.1 3.1.1`

un=`getAnswer 'Enter KC Database Username'`

pw=`getAnswer 'Enter KC Database Password'`

if [ "${dbtype}" = "ORACLE" ]
then
	DBSvrNm=`getAnswer 'Enter KC Database TNS Name'`
	if [ "${DBSvrNm}" = "_" ]
	then
		DBSvrNm=''
	else
		DBSvrNm="@${DBSvrNm}"
	fi
else
	DBSvrNm=`getAnswer 'Enter KC Schema Name'`
	if [ "${DBSvrNm}" = "_" ]
	then
		DBSvrNm="${un}"
	fi
fi

if [ "${mode}" = "EMBED" ]
then
	Riceun=`getAnswer 'Enter Rice Database Username'`
	Ricepw=`getAnswer 'Enter Rice Database Password'`
	if [ "${dbtype}" = "ORACLE" ]
	then
		RiceDBSvrNm=`getAnswer 'Enter Rice Database TNS Name'`
		if [ "${RiceDBSvrNm}" = "_" ]
		then
			RiceDBSvrNm=''
		else
			RiceDBSvrNm="@${RiceDBSvrNm}"
		fi
	else
		RiceDBSvrNm=`getAnswer 'Enter Rice Schema Name'`
		if [ "${RiceDBSvrNm}" = "_" ]
		then
			RiceDBSvrNm="${Riceun}"
		fi
	fi
else
	Riceun="${un}"
	Ricepw="${pw}"
	RiceDBSvrNm="${DBSvrNm}"
fi

case "${dbtype}" in
	"ORACLE")
        cd KC-RELEASE-3_0-CLEAN/oracle
		
		if [ "${version}" = "NEW" ]
		then
			if [ "${mode}" = "BUNDLE" ]
			then
				sqlplus "${un}"/"${pw}${DBSvrNm}" < oracle_server_rice.sql
			else
				if [ "${mode}" = "EMBED" ]
				then
					if [ "${InstRice}" = "Y" ]
					then
						sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < oracle_server_rice.sql
					fi
				fi
			fi
		fi
		
		sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < krrelease/datasql/KR_00_SEQ_BS.sql
	    
	    if [ "${version}" = "NEW" ]
        then
    		sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < oracle_server.sql
            sqlplus "${un}"/"${pw}${DBSvrNm}" < oracle_client.sql
        fi
        
        mv *.log ../../LOGS/
        cd ../..

		if [ "${version}" = "3.0" ] || [ "${version}" = "NEW" ]
		then
			cd KC-RELEASE-3_0_1-SCRIPT
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-Release-3_0-3_0_1-Upgrade-Oracle-Install.sql
			sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-Release-3_0-3_0_1-Upgrade-Oracle-Install.sql
			mv *.log ../LOGS/
			cd .. 
		fi
		
		if [ "${version}" = "3.0.1" ] || [ "${version}" = "3.0" ] || [ "${version}" = "NEW" ]
		then
			cd KC-RELEASE-3_1_SP1-SCRIPT
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-Release-3_0_1-3_1_S1-Upgrade-Oracle-Install.sql
			sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-Release-3_0_1-3_1_S1-Upgrade-Oracle-Install.sql
			if [ "${InstRice}" = "Y" ] || [ "${mode}" = "BUNDLE" ]
			then
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-Server-Release-1_0_3-1_0_3_1-Upgrade-Oracle-Install.sql
			fi
			mv *.log ../LOGS/
			cd ..
			
			cd KC-RELEASE-3_1_SP2-SCRIPT
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC-RELEASE-3_1_SP2-Upgrade-ORACLE.sql
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-3_1_SP2-Upgrade-ORACLE.sql
			sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_SP2-Upgrade-ORACLE.sql
			mv *.log ../LOGS/
			cd ..

			cd KC-RELEASE-3_1_SP3-SCRIPT
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC-RELEASE-3_1_SP3-Upgrade-ORACLE.sql
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-3_1_SP3-Upgrade-ORACLE.sql
			sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_SP3-Upgrade-ORACLE.sql
			mv *.log ../LOGS/
			cd ..
			
			cd KC-RELEASE-3_1_SP4-SCRIPT
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC-RELEASE-3_1_SP4-Upgrade-ORACLE.sql
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-3_1_SP4-Upgrade-ORACLE.sql
            sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_SP4-Upgrade-ORACLE.sql
            if [ "${InstRice}" = "Y" ] || [ "${mode}" = "BUNDLE" ]
            then
                sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < RICE-1_0_3_1-1_0_3_2-Upgrade-ORACLE.sql
            fi
            mv *.log ../LOGS/
            cd ..
        fi

		if [ "${version}" = "3.1"] || [ "${version}" = "3.0.1" ] || [ "${version}" = "3.0" ] || [ "${version}" = "NEW" ]
		then
			cd KC-RELEASE-3_1_1-SCRIPT
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-3_1_1-Upgrade-ORACLE.sql
			sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_1-Upgrade-ORACLE.sql
			mv *.log ../LOGS/
			cd ..
		fi
		
		if [ "${version}" = "3.1.1"] || [ "${version}" = "3.1"] || [ "${version}" = "3.0.1" ] || [ "${version}" = "3.0" ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-4_0-SCRIPT
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-4_0-Upgrade-ORACLE.sql
            sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-4_0-Upgrade-ORACLE.sql
            mv *.log ../LOGS/
            cd ..
        fi  
		
		cd KC-RELEASE-3_0-CLEAN/oracle
		sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < krrelease/datasql/KR_00_CLEAN_SEQ_BS.sql
        mv *.log ../../LOGS/
        cd ../.. ;;
		
	"MYSQL")
        cd KC-RELEASE-3_0-CLEAN/mysql
        
		if [ "${version}" = "NEW" ]
		then
			if [ "${mode}" = "BUNDLE" ]
			then
				mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < mysql_server_rice.sql > KC-Release-3_0-Clean-Server-Rice-Mysql-Install.log 2>&1
			else 
				if [ "${mode}" = "EMBED" ]
				then
					if [ "${InstRice}" = "Y" ]
					then
						mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < mysql_server_rice.sql > KC-Release-3_0-Clean-Server-Rice-Mysql-Install.log 2>&1
					fi
				fi
			fi
		fi
		
        mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < krrelease/datasql/KR_00_SEQ_BS.sql > KR_SEQ_BS-Mysql-Install.log 2>&1
        
        if [ "${version}" = "NEW" ]
        then
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < mysql_server.sql > KC-Release-3_0-Clean-Server-Mysql-Install.log 2>&1
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < mysql_client.sql > KC-Release-3_0-Clean-Client-Mysql-Install.log 2>&1
        fi
        
        mv *.log ../../LOGS/
        cd ../..
		
		if [ "${version}" = "3.0" ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-3_0_1-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-Release-3_0-3_0_1-Upgrade-Mysql-Install.sql > KC-Release-3_0-3_0_1-Upgrade-Mysql-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-Release-3_0-3_0_1-Upgrade-Mysql-Install.sql > KR-Release-3_0-3_0_1-Upgrade-Mysql-Install.log 2>&1
            mv *.log ../LOGS/
            cd .. 
        fi

		if [ "${version}" = "3.0.1" ] || [ "${version}" = "3.0" ] || [ "${version}" = "NEW" ]
		then
			cd KC-RELEASE-3_1_SP1-SCRIPT
			mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.sql > KC-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.log 2>&1
			mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.sql > KR-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.log 2>&1
			if [ "${InstRice}" = "Y" ] || [ "${mode}" = "BUNDLE" ]
			then
                mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-Server-Release-1_0_3-1_0_3_1-Upgrade-Mysql-Install.sql > KR-Server-Release-1_0_3_1-Upgrade-Mysql-Install.log 2>&1
			fi
            mv *.log ../LOGS/
            cd ..
            
            cd KC-RELEASE-3_1_SP2-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC-RELEASE-3_1_SP2-Upgrade-MYSQL.sql > KRC-RELEASE-3_1_SP2-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-3_1_SP2-Upgrade-MYSQL.sql > KC-RELEASE-3_1_SP2-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_SP2-Upgrade-MYSQL.sql > KR-RELEASE-3_1_SP2-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..

            cd KC-RELEASE-3_1_SP3-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC-RELEASE-3_1_SP3-Upgrade-MYSQL.sql > KRC-RELEASE-3_1_SP3-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-3_1_SP3-Upgrade-MYSQL.sql > KC-RELEASE-3_1_SP3-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_SP3-Upgrade-MYSQL.sql > KR-RELEASE-3_1_SP3-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..

		    cd KC-RELEASE-3_1_SP4-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC-RELEASE-3_1_SP4-Upgrade-MYSQL.sql > KRC-RELEASE-3_1_SP4-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-3_1_SP4-Upgrade-MYSQL.sql  > KC-RELEASE-3_1_SP4-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_SP4-Upgrade-MYSQL.sql > KR-RELEASE-3_1_SP4-Upgrade-MYSQL-Install.log 2>&1
            if [ "${InstRice}" = "Y" ] || [ "${mode}" = "BUNDLE" ]
            then
                mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < RICE-1_0_3_1-1_0_3_2-Upgrade-MYSQL.sql > RICE-1_0_3_1-1_0_3_2-Upgrade-MYSQL-Install.log 2>&1
            fi
            mv *.log ../LOGS/
            cd ..
        fi

		if [ "${version}" = "3.1" ] || [ "${version}" = "3.0.1" ] || [ "${version}" = "3.0" ] || [ "${version}" = "NEW" ]
		then
            cd KC-RELEASE-3_1_1-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-3_1_1-Upgrade-MYSQL.sql > KC-RELEASE-3_1_1-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_1-Upgrade-MYSQL.sql > KR-RELEASE-3_1_1-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..
		fi
		
		if [ "${version}" = "3.1.1" ] || [ "${version}" = "3.1" ] || [ "${version}" = "3.0.1" ] || [ "${version}" = "3.0" ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-4_0-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-4_0-Upgrade-MYSQL.sql > KC-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-4_0-Upgrade-MYSQL.sql > KR-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..
        fi
		
        cd KC-RELEASE-3_0-CLEAN/mysql
        mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < krrelease/datasql/KR_00_CLEAN_SEQ_BS.sql > KR_CLEAN_SEQ_BS-Mysql-Install.log 2>&1
        mv *.log ../../LOGS/
        cd ../.. ;;
esac

cd LOGS
echo 'Review log files for errors during database install.'
ls *.log



