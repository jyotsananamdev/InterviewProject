#Proejct Details

Folder Structure: 

sr > test >
> BaseTest : Create object of all pages in this class and extend this from every test class

> CommonTest : All common methods like senKeys, click, select are here. Add new common moethods hee if required.

> PreprocessorTest : This is where we are creating driver and closing driver. Also reading properties file. 

> drivers package :
	>>impl package (Add new browser implementation here)
		>> ChromeDriver : Creating chrome driver here if required we can add desired capabilities here.
		>> Firefox : Creating firefox driver here if required we can add desired capabilities here.
	>>WebDriverProvide(Interface)

> pages package : Add new pages under this package
	>> FlightPage : All the web elements and functions related to flight page are here.
	
> tests package : Add new test classes here.
	>> FlightBookingTest : All the test cases related to flight booking create in this class.

src > main >
> resources :
	>> Environment.properties : Keep you common information(data) here.

##Project Exceution


> Supported WebBrowser :
1. Chrome 
2. Firefox 

>Porject configurations and test data:
1. It is maven based and TestNG integrated project.
2. Go to testng.xml and add browser name
3. Go to Envirnoment.properties file and enter url and flying from and flying to city.

##Project reporting
Go to targer/surefire-reports folder and open index.html in web browser.

##Note 
Used Sleep as Ajax calls directly bind to UI elements which selenium wait is not handling. 



 



