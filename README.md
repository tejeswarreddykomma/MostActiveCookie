# MostActiveCookie
Given a cookie log file in the following format: 
```
cookie,timestamp
AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00
5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00
AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-08T22:03:00+00:00
4sMM2LxV07bPJzwf,2018-12-08T21:30:00+00:00
fbcn5UAVanZf6UtG,2018-12-08T09:30:00+00:00
4sMM2LxV07bPJzwf,2018-12-07T23:30:00+00:00
```
Write a command line program to process the log file and return the most active cookie for specified day.  
The example below shows how we'll execute your program.  
Command:
```
$ ./most_active_cookie cookie_log.csv -d 2018-12-09
```
Output:
```
AtY0laUfhglK3lC7
```
We define the most active cookie as one seen in the log the most times during a given day.  
- Assumptions:  
If multiple cookies meet that criteria, please return all of them on separate lines.
```
$ ./most_active_cookie cookie_log.csv -d 2018-12-08
```
Output:
```
SAZuXPGUrfbcn5UA
4sMM2LxV07bPJzwf
fbcn5UAVanZf6UtG
```

## Tech Stack
This project is built using the following technologies:   

- Java 8 (or higher): A programming language that is widely used for building enterprise-level applications.
- Apache Maven: A build automation tool that is used to manage the project's dependencies and build process.
- Spring Boot: A Java framework that makes it easy to create stand-alone, production-grade Spring-based applications.   
   
Please note that the minimum requirement for Java version is 8 and above.

## Installation Instructions
To use this project, you'll need to have Java 8 and Apache Maven installed on your system. Here are the steps to install and set up these technologies:

1. Install Java 8:  
- On Windows:
  - Download the Java 8 installer from the official Oracle website.
  - Run the installer and follow the prompts to complete the installation.
- On Mac:
  - Open Terminal
  - Run the command "brew tap AdoptOpenJDK/openjdk"
  - Run the command "brew cask install adoptopenjdk8"
- On Linux:
  - Download the Java 8 package from the official Oracle website.
  - Install the package using the package manager of your Linux distribution.   

2. Install Apache Maven:
- On Windows:
  - Download the Apache Maven binary archive from the official Apache website.
  - Unpack the archive to a directory on your system.
  - Add the bin directory of the unpacked archive to the System Variables
- On Mac:
  - Open Terminal
  - Run the command "brew install maven"
- On Linux:
  - Download the Apache Maven binary archive from the official Apache website.
  - Unpack the archive to a directory on your system.
  - Add the bin directory of the unpacked archive to the System Variables   

After you have successfully installed both Java 8 and Apache Maven, you can proceed to run this project.
   
Please make sure that you have set the path of Java and Maven in the System Variables.

## Build and Run Instructions

To build and run this project, you'll need to have both Java and Apache Maven installed on your system. Here are the step by step instructions on how to build and run the project:   

1. Open a command line interface (CLI) of your choice (Windows Command Prompt, Mac Terminal, Linux Bash, etc.)

2. Go to the project's directory where the pom.xml file is located, for example:
```
cd demo
```
3. Build the project using Maven by running one of the following commands:
```
mvn clean install
```
or
```
mvn clean package
```

This command will use Maven to compile the source code, run any tests, and package the compiled code into a jar file.  

Please note that "clean" command will delete all the previously created target files and "package" command will package all the class files, libraries and resources into a jar file.   
   
Running "mvn clean package" will package the project and its dependencies in one executable jar file, while running "mvn clean install" will install the package to the local repository, in addition to packaging the project.   

4. The generated jar file will be located in the "target" folder in the project's directory.   
5. To run the project, use the following command:
```
java -jar target/demo-version-SNAPSHOT.jar data-file-path -d specific-date
```
Please note that you need to replace the version-SNAPSHOT and data-file-path and specific-date with the actual version of the jar file, the path of the csv file and the selected date respectively.   
   
Example:   
```
java -jar target/demo-0.0.1-SNAPSHOT.jar cookie_log.csv -d 2018-12-08
```
Please make sure that you have the correct version of Java installed and that it is set up in your system's environment variables.
