---
layout: default.md
title: "User Guide"
pageNav: 3
---

# **TASsistant User Guide**

TASsistant is a desktop app for Teaching Assistants (TAs) in NUS to track information about the students and professors
involved in tutoring. It is optimised for users that have experience with a Command Line Interface (CLI) and aims to be
able to handle contact organisation faster than GUI apps if the user can type quickly.

<!-- @@author cbj252-reused
Table Reused from (https://github.com/AY2223S1-CS2103T-T12-4/tp/blob/master/docs/UserGuide.md)
!-->

Here are some symbols used throughout this user guide:

| Symbol               | Meaning                                  |
|----------------------|------------------------------------------|
| :information_source: | Important things you should take note of |
| :bulb:               | Useful information                       |
| :exclamation:        | Warning                                  |

<h2>Table of Contents</h2>

- **[Introduction](#tassistant-user-guide)**
- **[Quick Start](#quick-start)**
- **[Features](#features)**
- **[Command summary](#command-summary)**
- **[Known issues](#known-issues)**
- **[Glossary](#glossary)**
- **[FAQ](#faq)**

--------------------------------------------------------------------------------------------------------------------

## **Quick start**

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `TASsistant.jar` from [here]().

3. Copy the file to the folder you want to use as the _home folder_ for your TASsistant.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar TASsistant.jar` command to run the application.<br>

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `delete 3` : Deletes the 3rd contact shown in the current list.

    * `clear` : Deletes all contacts.

    * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## **Features**

**Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to TASsistant.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS s/STUDENT_NUMBER [t/TAG]…​`

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `add n/Judas p/98768432 e/judas@example.com s/A1234567J tele/@judas`
* `add n/John Doe p/98765432 e/johnd@example.com s/A0240253J tele/@john t/friends`

### Listing all persons : `list`

Shows a list of all persons in TASsistant.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/STUDENT_NUMBER] [tele/TELEGRAM_HANDLE]
[t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 3 s/A0514624K tele/@JP` Edits the student number of the 2nd person to be `A0514624K` and telegram handle to be `@JP`.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from TASsistant.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in TASsistant.

### Search : `search`

Lists out all students with the given condition.

Format: `search CONDITION1, CONDITION2, …`

* Condition is given in the format keyword: value.
* Accepted condition keywords are module, classNum, and studentName.
* Unrecognised keywords cause this method to fail.

Examples:
* `search module: CS2103T` returns all students in the module CS2103T
* `search classNum: T03` returns all students in the class T03
* `search studentName: John Doe`  returns all students with the name John Doe


### Clearing all entries : `clear`

Clears all entries from TASsistant.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TASsistant data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TASsistant data are saved automatically as a JSON file `[JAR file location]/data/TASsistant.json`. Advanced users are welcome to update data directly by editing that data file.

**Caution:**
If your changes to the data file makes its format invalid, TASsistant will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</box>

### Archiving data files `[coming in v2.0]`

### Adds a module to TASsistant: `addModule`

Adds the module to TASsistant, displaying it on the GUI.
The module name must consist of only alphanumeric characters, between 5 - 8 characters.
Modules must be added one at a time.

The output for module name inputs "cs2100" and "CS2100" will be the same as the module
shown on the GUI will always be capitalised.

Will give an error message if the module already exists in TASsistant,
further prompts are given for incorrect inputs.

Format: `addModule m/MODULE_NAME`

Example: `addModule m/CS2100`

### Deletes a module from TASsistant: `deleteModule`

Deletes the module from TASsistant and the GUI will change to reflect this.
The index refers to the position of the module displayed on the GUI.

Any related tutorials on the GUI as well as modules/tutorials on students
will also be deleted. As such, please use this command with discretion.

Will give an error message if the module does not exist in TASsistant,
further prompts are given for incorrect inputs.

Format: `deleteModule INDEX`

Example: `deleteModule 1`

### Adding a person to a given module: `addToModule`

Adds the person at the specified INDEX displayed on the GUI to a given module.
The index refers to the index number shown in the displayed user list. The index must be a positive integer.
Will give an error message if the module given has not been made.

Format: `addToModule INDEX m/MODULE`

Example: `addToModule 2 m/CS2103T` adds the person who is second from the top in the current list to the module
CS2103T, if the module CS2103T exists.

### Removes a user from a given module: `removeFromModule`

Removes the user at the specified INDEX displayed on the GUI from a given module.
The index refers to the index number shown in the displayed user list. The index must be a positive integer.

:exclamation: Doing this also removes any tutorials that the user has which are part of the given module.

Format: `removeFromModule INDEX m/MODULE`

Example: `removeFromModule 2 m/CS2103T` removes the person who is second from the top in the current list from the module
CS2103T, if the module CS2103T exists and the person who is second from the top is part of the module CS2103T.

### Adds a tutorial to a given module: `addTutorial`

Adds a tutorial with a specific tutorial name to a given module. Different modules can have different tutorials
with the same name. Will give an error message if the module does not exist or a tutorial with the same name has already
been made for the module.

Format: `addTutorial m/MODULE_NAME tn/TUTORIAL_NAME tt/TUTORIAL_TIME`

Example: `addTutorial m/CS2103T tn/T12 tt/Mon 6pm` adds a tutorial with name T12 and time "Mon 6pm" belonging to module
CS2103T, if module CS2103T exists.

### Deletes a tutorial from a given module: `deleteTutorial`

Deletes a tutorial with a specific tutorial name to a given module.
Will give an error message if the module given has not been made, or if the tutorial does not exist.

:exclamation: Doing this removes the tutorial from everyone in the address book.

Format: `deleteTutorial tn/TUTORIAL_NAME m/MODULE_NAME`

Example: `deleteTutorial tn/c12 m/CS2103T` removes a tutorial with name T12 belonging to module CS2103T,
if module CS2103T exists and the tutorial already exists.

### Adds a person to stated Tutorial: `addToTutorial`

Adds a given user to the stated tutorial.
The index refers to the position of the given person as displayed in the GUI and **must be a positive integer** 1, 2, 3. Fails if the index is not valid.

Format: `addToTutorial INDEX m/MODULE_NAME tn/TUTORIAL_NAME`

Example: `addToTutorial 1 m/CS2103T tn/T11`

### Removes a person from stated Tutorial: `removeFromTutorial`

Removes a given person from the stated tutorial. The index refers to the position of the given person as displayed in the GUI and **must be a positive integer** 1, 2, 3. Fails if the index is not valid.

Format: `removeFromTutorial INDEX m/MODULE_NAME tn/TUTORIAL_NAME`

Example: `removeFromTutorial 1 m/CS2103T tn/T11`

### Records the attendance of the student: `attn`

Records a student's attendance and displays the change by adding a tag on the GUI.
The index refers to the position of the person as displayed in the GUI.
The LESSON_NUMBER must be alphanumeric.

Will give an error message if the LESSON_NUMBER already exists on the student.
Further prompts are given for incorrect inputs.

Format: `attn INDEX ln/LESSON_NUMBER`

Example: `attn 1 ln/S1`

### Deletes the attendance of the student: `deleteAttn`

Deletes a student's attendance and displays the change by removing the tag on the GUI.
The index refers to the position of the person as displayed in the GUI.
The LESSON_NUMBER must be alphanumeric.

Only tags that were added using `attn` can be deleted by this command.

Likewise, tags that were not added through `attn` cannot be deleted by this command.
If you try to delete a tag that was not added with `attn`, it will throw an error.

Will give an error message if the LESSON_NUMBER does not exist.
Further prompts are given for incorrect inputs.

Format: `deleteAttn INDEX ln/LESSON_NUMBER`

Example: `deleteAttn 1 ln/S1`

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. To resolve this, delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                 | Format, Examples                                                                                                                                                      |
|------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/Judas p/98768432 e/judas@example.com s/A1234567J tele/@judas`                               |
| **Clear**              | `clear`                                                                                                                                                               |
| **Delete**             | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**               | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [s/STUDENT_NUMBER] [tele/TELEGRAM_HANDLE] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com` |
| **Find**               | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**               | `list`                                                                                                                                                                |
| **Search**             | `search CONDITION1, CONDITION2`<br> e.g., `search module: CS2103T`                                                                                                    |
| **Help**               | `help`                                                                                                                                                                |
| **addModule**          | `addModule m/MODULE_NAME` <br> e.g., `addModule m/CS2100`                                                                                                             |
| **deleteModule**       | `deleteModule INDEX` <br> e.g., `deleteModule 1`                                                                                                                      |
| **addToModule**        | `addToModule INDEX m/MODULE_NAME` <br> e.g., `addToModule 2 m/CS2103T`                                                                                                |
| **removeFromModule**   | `removeFromModule INDEX m/MODULE_NAME` <br> e.g., `removeFromModule 2 m/CS2103T`                                                                                      |
| **addTutorial**        | `addTutorial TUTORIALNAME m/MODULE_NAME` <br> e.g., `addTutorial tn/c12 m/CS2103T`                                                                                    |
| **deleteTutorial**     | `deleteTutorial INDEX` <br> e.g., `deleteTutorial 2`                                                                                                                  |
| **addToTutorial**      | `addToTutorial INDEX m/MODULE_NAME tn/TUTORIAL_NAME` <br> e.g., `addToTutorial 1 m/CS2103T tn/T11`                                                                    |
| **removeFromTutorial** | `removeFromTutorial INDEX m/MODULE_NAME tn/TUTORIAL_NAME` <br> e.g., `removeFromTutorial 1 m/CS2103T tn/T11`                                                          |
| **attn**               | `attn INDEX ln/LESSON_NUMBER` <br> e.g., `attn 1 ln/S1`                                                                                                               |
| **deleteAttn**         | `deleteAttn INDEX ln/LESSON_NUMBER` <br> e.g., `deleteAttn 1 ln/S1`                                                                                                   |

--------------------------------------------------------------------------------------------------------------------

## Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Teaching Assistant (TA)**: A non-professor tutor which helps the professors of a module by teaching one or more classes.
* **Module**: A unit of study in a specific field set by NUS, such as CS2103T focusing on Software Engineering.
* **Class**: Lessons that are part of a module. Includes laboratries, recitations, tutorials and any form of lesson that includes a TA.

--------------------------------------------------------------------------------------------------------------------

## **FAQ**

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app on the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous UniNurse home folder.

--------------------------------------------------------------------------------------------------------------------
