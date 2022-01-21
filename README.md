# Selenium Script for testing

A simple java selenium testing script, using chrome webDriver

## Files
+ `driver`: storage the chrome webDriver
+ `src/main/.../config`: this script's configurations
    + `ActionKeywords.java`: some methods about UI controlling, such as input, select and click
    + `Constants.java`: the schema of `data.xlsx`
    + `OR.txt`: a properties of UI elements' xpath
+ `src/main/.../data`: the file of `data.xlsx`
+ `src/main/.../script`: the main file which can be ran
+ `src/main/.../utils`: some beneficial utility 

## Usage
### write the URL and MACHINE type in `Constants.java`
```txt
type: windows / mac_m1
url: https://www.baidu.com
```
### Write the UI elements' xpath in `OR.txt`

> schema: `PageObject = xpath`

e.g: 
```txt
loginUsernameInputbox=//*[@id='username']
```

### Write the test cases in the TestCases sheet of `data.xlsx`

**1. Test Case ID**
    
    Unique identifier of a test case
  
**2. Description**

**3. Runmode**

    Have two variable
    
    `Yes` : the test case will be running
    
    `No`  : the test case won't be running
  
**4. Results**

    The results of the test cases after running the script


### Write the test steps in the TestSteps sheet of `data.xlsx`

> In the same test case, test steps must have similar Test Case ID and be *sort* by order

**1. Test Case ID**
   
    Unique identifier of a test case. 
    
**2. TestScenario ID**
   
    No use in this script
    
**3. Description**

**4. Variable**
   
    One of the UI controlling method's arguments, can be considered the input value of `input`
    
**5. Page Object**
   
    One of the UI controlling method's arguments, which must be existed in `OR.txt`
    
**6. Action_Keyword**
   
    The UI controlling method
    
**7. Results**

    The results of the test cases after running the script
    
### Logger

    You can see the detail of the results after running this script in logfile.log