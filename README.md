# Hello Fresh QA Task - Web and API Automation framework

This test automation framework is currently designed to automate HelloFresh QA task for Web and APIs.


# Pre-requisites

You'll need to install

    Download and install Java 8
	Download Maven 3.6.2
	Eclipse IDE
    Maven plugin in Eclipse
    TestNG plugin in Eclipse
	WebDriverManager is used to automatically download version of compatible executables
	Browser - Chrome


# How to build and execute tests

	1. Clone the project from git repo. https://github.com/rajat24487/hellofresh-task.git
	2. Import project as existing Maven Project
	3. Go to project directory and run 'cmd'
	4. TestData.json, log4j.properties, available @ "src/test/resources"
	5. To execute web and api test parallel, execute below command:
		mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
	6. To execute web test with command line arguments
		mvn clean test -Dbrowser=chrome -Durl=http://automationpractice.com/index.php -DsuiteXmlFile=testng_cmd_param.xml

	
# Test execution report
	Extent report will be available @ "hellofresh-task/target".

# Execution Logs	
	Logs will be available @ "hellofresh-task/log"