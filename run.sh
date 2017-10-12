
#Parse Input Options
while test $# -gt 0; do
	case "$1" in
		-b|--build)
			echo "Notice: This will rebuild your image using current code"
			build=true
			shift
			;;
		-d|--driver)
			if [ -z "$2" ]
				then
					echo "No driver specified. Using default as virtualbox"
			else
				driver="$2"
			fi
			shift
			shift
			;;
		-c|--clean)
			echo "Warning: This will remove all docker images and remove docker machine"
			echo "Notice: This will rebuild your image using current code"
			clean_flag=true
			unset run_docker
			shift
			;;
		-cr|--clean-run)
			echo "Warning: This will remove all docker images and remove docker machine"
			echo "Notice: This will rebuild your image using current code"
			clean_flag=true
			run_docker=true
			shift
			;;
		-nr|--no-report)
			echo "Warning: Current run will not fetch report from containers"
			unset get_report
			shift
			;;
		-h|--help)
			echo ""
			echo "NYU Sakai Test Suite Help"
			echo ""
			echo "Options:"
			echo "-d  --driver <driver>      Run docker with specified driver, defaults to virtualbox"
			echo "-c  --clean                Cleans the docker-machine by deleting and recreating"
			echo "-cr --clean-run            Cleans and runs the docker-machine"
			echo "-b  --build                Rebuilds and runs docker images without cleaning"
			echo "-nr --no-report            Runs docker image without fetching container report"
			echo "-h  --help                 Prints this thing again"
			shift
			exit 1
			;;
		*)
			echo "Unknown option $1"
			exit 1		
			;;
	esac
done

#!/bin/bash

echo "#########################################"
echo "Starting Automated Testing using Cucumber"
echo "#########################################"
#Initialize default variables
driver="virtualbox"
unset clean_flag
unset build
run_docker=true
get_report=true

#Cleaning Phase
if [[ "$clean_flag" = true ]]; then
	docker-machine start cucumber
	eval $(docker-machine env cucumber)
	docker images -qa | xargs docker rmi -f
	docker-compose rm -fs
	docker-machine stop cucumber
	docker-machine rm -y cucumber
fi 

echo "Using $driver driver to run the docker machine"

#All the magic is here
docker-machine create --driver "$driver" cucumber
if [ $? -ne 0 ]; then
	docker-machine start cucumber
fi
eval $(docker-machine env cucumber)
if [[ "$clean_flag" = true ]]; then	
	docker-compose build
	if  [ -z "$run_docker" ]; then
		docker-machine stop cucumber
		exit 0;
	fi
else
	if [[ "$build" = true ]]; then
		docker-compose build
	fi 
fi
docker-compose up

#Get HTML reports from docker containers
if [[ "$get_report" = true ]]; then
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