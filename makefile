SHELL = /bin/bash
.PHONY: ant clean

export JAVA_HOME=/opt/atseintl/adm/thirdparty/jdk1.8.0_151
export GRADLE_HOME=/project/stor06_new/CSF_project/sg500950/downloads/gradle-4.6
export PATH=$(GRADLE_HOME)/bin:$(JAVA_HOME)/bin:/usr/bin:/bin:/usr/local/bin

all: gradle 

default: gradle

run: gradle
	java -jar build/libs/javasim-1.0.0-SNAPSHOT.jar

gradle:
	gradle -Dhttp.proxyHost=localhost -Dhttp.proxyPort=3128 -Dhttps.proxyHost=localhost -Dhttps.proxyPort=3128 build

clean:
	gradle clean
