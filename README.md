Computer_usage-Tracker
======================

Computer_usage Tracker

Deepti Verma

This code  will run only on mac. To get the process id of running firefox .I am using ps aux which does not run on windows machine .

Functionality of the application-
It has two parts-

1)	Browser tracker-
User sets the time for how long maximum he wants the user to run. Then the applications keeps running in the background and anytime when the application is running if a browser is opened it is closed after the amount of time set by the user. This application only works on firefox.
2)	Rest Tab-
User sets the time and checks one of the options-take a break or exercise
Repeatedly after this much amount of time a dialog box opens.
If take a break was chosen user can set alarm according to how long break he wants.
If exercise was chosen then the dialog box tells the user which exercises to do step by step.

Note:
1 and 2 are not using the same logic.
For 1st if the time is set to 2 hrs then the browser does not close when the app has been running for two hours but when the browser has been running for 2 hrs.Therefore you do not see the countdown on the browser tab.

For 2nd if the time is set to 2 hrs then the dialog pops up 2 hrs after application has been running and then after doing the exercise or taking the break it will again wait for next 2 hrs before the dialog pops up.Thus you will see a timer on Rest tab.
