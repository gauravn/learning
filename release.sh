#!/bin/sh
#
# test script
#
filename="falcon.release"
release="" 
oldRelease=""
releaseSource ()
{
	git status
	echo "Please make sure you are on right branch for making this release"
	echo "Are you on correct branch?(y) "
	read response
	if [ "$response" = "y" ];then
		echo "Releasing New version : $release"
		echo $release>>$filename
		echo "Enter Release Message"
		read tagMessage
		git commit -m "v$release" -a
		git push origin master
		git tag -a v$release -m "$tagMessage"
		git push origin v$release
		echo "Version Release Finished!!"
		echo "Now new version of app is :$release"
	else
		echo "you choose to exit!"
	fi
	return
}

if [ ! -f $fileName ]; then
	echo "Filename $fileName does not exists"
	exit 1
else 
	release=$(tail -n 1 $filename)
	oldRelease=$release
	#release="0.0"
	majorNo=$(echo $release | cut -d'.' -f1)
	echo "major	:"$majorNo
	minorNo=$(echo $release | cut -d'.' -f2)
	echo "minor	:"$minorNo
	revisionNo=$(echo $release | cut -d'.' -f3)
	echo "revision:"$revisionNo

	case $1 in
	   "release") case $2 in
			   "major") majorNo=`expr $majorNo + 1`
				    release=$majorNo.0.0
				    echo "new major release : $release"
				    releaseSource ;;
			   "minor") minorNo=`expr $minorNo + 1`
				    release=$majorNo.$minorNo.0
				    echo "new minor release : $release"
				    releaseSource ;;
			   "revision") 	revisionNo=`expr $revisionNo + 1`
					release=$majorNo.$minorNo.$revisionNo
					echo "new revision release : $release"
					releaseSource ;;
			   *) echo "$2 : Not a release Type" ;;
			esac;;
	   "goto") 	git checkout v$2
			echo "jump to version: $2";;
	   *) echo "Sorry, $1 Not a valid argument";;
	esac
fi
