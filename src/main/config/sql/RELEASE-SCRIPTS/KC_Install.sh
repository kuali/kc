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

version=`getChoice 'Enter Version' NEW 2.0 3.0 3.0.1`

un=`getAnswer 'Enter KC Database Username'`

pw=`getAnswer 'Enter KC Database Password'`

if [ "${dbtype}" = "ORACLE" ]
then
	DBSvrNm=`getAnswer 'Enter KC Database TNS Name'`
fi

if [ "${mode}" = "EMBED" ]
then
	Riceun=`getAnswer 'Enter Rice Database Username'`
	Ricepw=`getAnswer 'Enter Rice Database Password'`
	if [ "${dbtype}" = "ORACLE" ]
	then
		RiceDBSvrNm=`getAnswer 'Enter Rice Database TNS Name'`
	fi
fi

if [ "${DBSvrNm}" = "_" ]
then
	DBSvrNm=''
else
	DBSvrNm="@${DBSvrNm}"
fi

if [ "${RiceDBSvrNm}" = "_" ]
then
	RiceDBSvrNm=''
else
	RiceDBSvrNm="@${RiceDBSvrNm}"
fi

case "${dbtype}" in
	"ORACLE")
		if [ "${version}" = "NEW" ]
		then
			cd KC-RELEASE-2_0-SCRIPT
			if [ "${mode}" = "BUNDLE" ]
			then
				sqlplus "${un}"/"${pw}${DBSvrNm}" < KR-Release-1_0_2-Server-Oracle-Install.sql
				sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-Release-2_0-Base-Bundled-Oracle-Install.sql
				sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-Release-2_0-Base-Rice-Oracle-Install.sql
			else
				if [ "${mode}" = "EMBED" ]
				then
					if [ "${InstRice}" = "Y" ]
					then
						sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-Release-1_0_2-Server-Oracle-Install.sql
					fi
					sqlplus "${un}"/"${pw}${DBSvrNm}" < KR-Release-1_0_2-Client-Oracle-Install.sql
					sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-Release-2_0-Base-Bundled-Oracle-Install.sql
					sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KC-Release-2_0-Base-Rice-Oracle-Install.sql
				fi
			fi
			mv *.log ../LOGS/
			cd ..
		fi

		if [ "${version}" = "2.0" ] || [ "${version}" = "NEW" ]
		then
			cd KC-RELEASE-3_0-SCRIPT
			if [ "${mode}" = "BUNDLE" ]
			then
				sqlplus "${un}"/"${pw}${DBSvrNm}" < RICE-1_0_2-1_0_3/update_final_oracle.sql
				sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-Release-2_0-3_0-Upgrade-Oracle-Install.sql
				sqlplus "${un}"/"${pw}${DBSvrNm}" < KR-Release-2_0-3_0-Upgrade-Oracle-Install.sql
			else 
				if [ "${mode}" = "EMBED" ]
				then
					if [ "${InstRice}" = "Y" ]
					then
						sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < RICE-1_0_2-1_0_3/update_final_oracle.sql
					fi
					sqlplus "${un}"/"${pw}${DBSvrNm}" < RICE-1_0_2-1_0_3/update_client_final_oracle.sql
					sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-Release-2_0-3_0-Upgrade-Oracle-Install.sql
					sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-Release-2_0-3_0-Upgrade-Oracle-Install.sql
				fi
			fi
			mv *.log ../LOGS/
			cd ..
		fi
		if [ "${version}" = "3.0" ] || [ "${version}" = "NEW" ]
		then
			cd KC-RELEASE-3_0_1-SCRIPT
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-Release-3_0-3_0_1-Upgrade-Oracle-Install.sql
			mv *.log ../LOGS/
			cd .. 
		fi
		cd POST-RELEASE-3_0_1-SCRIPT
		if [ "${mode}" = "BUNDLE" ]
		then
			sqlplus "${un}"/"${pw}${DBSvrNm}" < POST-3_0_1-KR-2011-01-26-ORACLE.sql
		else
			if [ "${mode}" = "EMBED" ] && [ "${InstRice}" = "Y" ]
			then
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < POST-3_0_1-KR-2011-01-26-ORACLE.sql
			fi
		fi
		cd .. ;;
	"MYSQL")
		if [ "${version}" = "NEW" ]
		then
			cd KC-RELEASE-2_0-SCRIPT
			if [ "${mode}" = "BUNDLE" ]
			then
				mysql -u ${un} -p${pw} -D ${un} -s -f < KR-Release-1_0_2-Server-MySql-Install.sql > KC-Release-1_0_2-Server-MySql-Install.log 2>&1
				mysql -u ${un} -p${pw} -D ${un} -s -f < KC-Release-2_0-Base-Bundled-MySql-Install.sql > KC-Release-2_0-Base-Bundled-MySql-Install.log 2>&1
				mysql -u ${un} -p${pw} -D ${un} -s -f < KC-Release-2_0-Base-Rice-MySql-Install.sql > KC-Release-2_0-Base-Rice-MySql-Install.log 2>&1
			else 
				if [ "${mode}" = "EMBED" ]
				then
					if [ "${InstRice}" = "Y" ]
					then
						mysql -u ${Riceun} -p${Ricepw} -D ${Riceun} -s < KR-Release-1_0_2-Server-MySql-Install.sql > KR-Release-1_0_2-Server-MySql-Install.log 2>&1
					fi
					mysql -u ${un} -p${pw} -D ${un} -s -f < KR-Release-1_0_2-Client-MySql-Install.sql > KR-Release-1_0_2-Client-MySql-Install.log 2>&1
					mysql -u ${un} -p${pw} -D ${un} -s -f < KC-Release-2_0-Base-Bundled-MySql-Install.sql > KC-Release-2_0-Base-Bundled-MySql-Install.log 2>&1
					mysql -u ${Riceun} -p${Ricepw} -D ${Riceun} -s -f < KC-Release-2_0-Base-Rice-MySql-Install.sql > KC-Release-2_0-Base-Rice-MySql-Install.log 2>&1
				fi
			fi
			mv *.log ../LOGS/
			cd ..
		fi

		if [ "${version}" = "2.0" ] || [ "${version}" = "NEW" ]
		then
			cd KC-RELEASE-3_0-SCRIPT
			if [ "${mode}" = "BUNDLE" ]
			then
				mysql -u ${un} -p${pw} -D ${un} -s -f < RICE-1_0_2-1_0_3/update_final_mysql.sql > update_final_mysql.log 2>&1
				mysql -u ${un} -p${pw} -D ${un} -s -f < KC-Release-2_0-3_0-Upgrade-MySql-Install.sql > KC-Release-2_0-3_0-Upgrade-MySql-Install.log 2>&1
				mysql -u ${un} -p${pw} -D ${un} -s -f < KR-Release-2_0-3_0-Upgrade-MySql-Install.sql > KR-Release-2_0-3_0-Upgrade-MySql-Install.log 2>&1
			else
				if [ "${mode}" = "EMBED" ]
				then
					if [ "${InstRice}" = "Y" ]
					then
						mysql -u ${Riceun} -p${Ricepw} -D ${Riceun} -s -f < RICE-1_0_2-1_0_3/update_final_mysql.sql > update_final_mysql.log 2>&1
					fi
					mysql -u ${un} -p${pw} -D ${un} -s -f < RICE-1_0_2-1_0_3/update_client_final_mysql.sql > update_client_final_mysql.sql 2>&1
					mysql -u ${un} -p${pw} -D ${un} -s -f < KC-Release-2_0-3_0-Upgrade-MySql-Install.sql > KC-Release-2_0-3_0-Upgrade-MySql-Install.log 2>&1
					mysql -u ${Riceun} -p${Ricepw} -D ${Riceun} -s -f < KR-Release-2_0-3_0-Upgrade-MySql-Install.sql > KC-Release-2_0-3_0-Upgrade-MySql-Install.log 2>&1
				fi
			fi
			mv *.log ../LOGS/
			cd ..
		fi
		cd KC-RELEASE-3_0_1-SCRIPT
		mysql -u ${un} -p${pw} -D ${un} -s -f < KC-Release-3_0-3_0_1-Upgrade-MySql-Install.sql > KC-Release-3_0-3_0_1-Upgrade-MySql-Install.log 2>&1
		mv *.log ../LOGS/
		cd .. ;;
esac

cd LOGS
echo 'Review log files for errors during database install.'
ls *.log



