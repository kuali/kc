#!/bin/sh

if [ $# -lt 2 ]
then
	echo "Usage: $0 version revision"
	echo "   Ex: $0 3.1.1 2673"
	exit
fi
current_version="$1"
if [ ! -d "../current/${current_version}" ]
then
	echo "${current_version} directory does not exist"
	exit
fi
start_revision="$2"
converted_version=`echo ${current_version} | tr '.' '_'`
script_dir_name="KC-RELEASE-${converted_version}-SCRIPT"

last_change_revision()
{
        svn info $1 | grep 'Last Changed Rev:' | cut -d':' -f2
}


if [ ! -d "${script_dir_name}" ]
then
	mkdir "${script_dir_name}"
fi
curr_dir=`pwd`
full_zip='Full-KC-KEW.zip'
if [ -f "${full_zip}" ]
then
	rm ${full_zip}
fi
cd ../../kew/xml
zip ${curr_dir}/${full_zip} `find . -name '*.xml'`
cd "${curr_dir}"

cd "${script_dir_name}"

curr_dir=`pwd`

if [ -d /tmp/kew ]
then
	rm -fR /tmp/kew
fi
mkdir -p /tmp/kew

cd ../../../kew/xml
for this_kew in `find . -name '*.xml'`
do
	if [ `last_change_revision ${this_kew}` -ge "${start_revision}" ]
	then
		kew_dir=`dirname /tmp/kew/${this_kew}`
		if [ ! -d "${kew_dir}" ]
		then
			mkdir -p ${kew_dir}
		fi
		svn export ${this_kew} /tmp/kew/${this_kew}
	fi
done
cd /tmp/kew
zip_name="KC-Release-${converted_version}-KEW-Updates.zip"
zip ${zip_name} `find . -name '*.xml'`
mv ${zip_name} ${curr_dir}/${zip_name}


