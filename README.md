# DecisionApp
A Kotlin based Android app that makes a decision given some choices. 

The app makes use of a variety of features with ranging complexities, ranging from simple textViews to ListViews with input functionality.

The final render of the app will consist of 3 activities:

1. Main Activity => the user will enter the number of options they would like a decision to be made from, then click the next button

 - the user is now redirected to the next activity, the quantity of options is passed in the intent in order to generate the correct number of input editTexts in a ListView 
 
2. Input Activity => the user is presented with a mutable ListView prompting them to insert the options to be chosen between, once they are finished inserting their choices, the user should click the choose button (see commits for a more detailed description of the process here)

 - the user is once again redirected to the next activity, the entire list of options will be passed in the intent so that it can be displayed
 
3. Choice Activity => the user is presented with a spinner wheel displaying the options they inputed on the previous screen, the spinner will rotate and land on some randomly selected option, making the decision for them. 

 - Idealy this portion of the app will enhance the UI by fetching the logos of the options, storing them preferably as svgs and displaying them on the spinner or as the background once the choice has been made

Created by Nicholas Makharinets on Jan 5 2020
