#!/bin/bash

#Initialize default variables
clean=false
driver='virtualbox'
run_docker=true
build=false
report=true

usage () { 
	echo ""
	echo "NYU Sakai Test Suite Help"
	echo ""
	echo "Options:"
	echo "-b                  Rebuilds the docker-machine"
	echo "-c                  Cleans the docker-machine by deleting and recreating"
	echo "-d  <driver>        Run docker with specified driver, defaults to virtualbox"
	echo "-cr                 Cleans and runs the docker-machine"
	echo "-br                 Rebuilds and runs docker images without cleaning"
	echo "-p                  Runs docker image without fetching container report"
	echo "-h                  Prints this thing again"
 }

options=':cd:rbph'
while getopts $options option
do
    case $option in
        c  )
			echo "Warning: This will remove all docker images and remove docker machine"
			echo "Notice: This will rebuild your image using current code"
			#Clean will not automatically run unless r flag is specified
			clean=true
			run_docker=false
			;;
        d  ) driver="$OPTARG" ;;
        r  ) 
			if [[ "$clean" = true ]]; then
				echo "Warning: This will remove all docker images and remove docker machine"
				run_docker=true
			else 
				if [[ "$build" = true ]]; then
					echo "Notice: This will rebuild your image using current code"
					run_docker=true
				else
					echo "Run flag must be used as -cr (clean and run) or -br (build and run)"
					exit
				fi
			fi
			;;
        b  )
			echo "Notice: This will rebuild your image using current code"
			#Build will not automatically run unless r flag is specified
			build=true
			run_docker=false
			;;
        p  ) 
			report=false
			;;
        h  ) usage; exit;;
        \? ) echo "Error: Unknown option: -$OPTARG" >&2; exit 1;;
        :  ) echo "Error: Missing option argument for -$OPTARG" >&2; exit 1;;
        *  ) echo "Error: Unimplemented option: -$OPTARG" >&2; exit 1;;
    esac
done

shift $(($OPTIND - 1))

echo "#########################################"
echo "Starting Automated Testing using Cucumber"
echo "#########################################"

#Cleaning Phase
if [[ "$clean" = true ]]; then
	docker-machine start cucumber
	eval $(docker-machine env cucumber)
	docker images -qa | xargs docker rmi -f
	docker-compose rm -fs
	docker-machine stop cucumber
	docker-machine rm -y cucumber
fi 

echo "Using $driver driver to run the docker machine"

#Create docker machine with specified driver
docker-machine create --driver "$driver" cucumber
if [ $? -ne 0 ]; then
	docker-machine start cucumber
fi
eval $(docker-machine env cucumber)

#If machine was cleaned, rebuild image
if [[ "$clean" = true ]]; then
	docker-compose build
else
	#If machine was not cleaned but build is specified, rebuild
	if [[ "$build" = true ]]; then
		docker-compose build
	fi 
fi

#If run flag is not specified, then stop
if  [ -z "$run_docker" ]; then
	docker-machine stop cucumber
	exit 0;
fi

docker-compose up

#Get HTML reports from docker containers
if [[ "$report" = true ]] && [[ "$run_docker" = true ]]; then
	echo "Deleting old chrome test reports"
	rm -rf "report-chrome"
	echo "Deleting old firefox test reports"
	rm -rf "report-firefox"
	echo "Retrieving new chrome test reports"
	docker cp nyuclassestestsuite_cucumber_chrome_1:/test/target/cucumber-html-reports ./report-chrome
	echo "Retrieving new firefox test reports"
	docker cp nyuclassestestsuite_cucumber_firefox_1:/test/target/cucumber-html-reports ./report-firefox
fi 

docker-machine stop cucumber
eval $(docker-machine env)			#Resume to default machine and exit