# KInalyze

KInalyze is a code-analyzing tool, which allows its user to find out various pieces of information about their provided code. 

## What is KInalyze capable of?

Once code has been provided to the system, it will provide various useful facts about it. Some serve as interesting side-facts, while others can be quite helpful when it comes to debugging and spotting inconsistencies.

KInalyze can do the following:

### Structure and Quality

Various services look at the structure and quality of the file itself, generally looking for bad practices. Some of these services include:
- Bad initializations
	- How many variables have been initialized, but never used?
- Condition and loop structure
	- How many conditions (if-statements) and for-loops are written irregularly? 
- Complexity analyzer
	- How many functions, loops, operators and decision points are written in the code? Furthermore, what is the maximum nesting depth reached?
- Indentation analyzer
	- Are the correct indentations used?
- Line to method ratio analyzer
	- How many lines does the average method have?
- Literals and constants analyzer
	- How many literals and constants are written in the code?
- Naming convention analyzer
	- Are all variables written conventionally? Does it follow the camel case principle?
- Performance analyzer
	- How well does the code run, given the number of recursive calls, loops and depth of the code?

### File Service

This service provides the opportunity to save and get files. These files will be saved in an internal database, and can be recalled whenever needed.

## What technologies have been used?

### Frontend

The frontend is written in HTML and SCSS, some functionalities implemented with JavaScript.

### Backend

The entirety of the backend, which encompasses analyzing provided code is written in Java.
