---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# AB-3 User Guide

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

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

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.
</box>

### Archiving data files `[coming in v2.0]`

### Editing a Student's name : `editName`

Changes the name of the student at the specified INDEX to NEW_NAME. INDEX specified must be within the boundaries of the
current displayed list.

Format: `editName INDEX NEW_NAME`

Example: `editName 3 Ken Masters`

### Editing a Student's student number: `editStudentNumber`

Changes the student number of the student at the specified INDEX to NEW_NUMBER.
INDEX must be within the boundaries on the current displayed list.

Format: `editStudentNumber INDEX NEW_NUMBER`

Example: `editStudentNumber 2 A0203032J`

### Editing a Student's details: `editDetail`

Allows the user to change less important details such as the telegram handle and
email to new values. For this version the details are limited to `/TELEGRAM` and `/EMAIL`.

Format: `editDetail INDEX /DETAIL NEW_DETAIL`

Example: `editDetail 1 /TELEGRAM @johannpetrovich`

### Adding a user to a given module: `addToModule`

Adds the user at the specified INDEX displayed on the GUI to a given module.
The index refers to the index number shown in the displayed user list.
Users are added with “none” status. The index must be a positive integer.
Will give an error message if the module given has not been made.

Format: `addToModule INDEX MODULE`

Example: `addToModule 2 CS2103T`

### Removes a user from a given module: `removeFromModule`

Removes the user at the specified INDEX displayed on the GUI from a given module.
The index refers to the index number shown in the displayed user list.
Users are added with “none” status. The index must be a positive integer.
Will give an error message if the module given has not been made or if the user is not part of the given module.

Format: `removeFromModule INDEX MODULE`

Example: `removeFromModule 2 CS2103T`

### Adds a tutorial to a given module: `addTutorial`
Adds a tutorial with a specific tutorial name to a given module.
Different modules can have different tutorials with the same name.
Will give an error message if the module does not exist or a tutorial with the same name
has already been made for the module.

Format: `addTutorial TUTORIALNAME MODULE`

Example: `addTutorial c12 CS2103T`

### Removes a tutorial to a given module: `removeTutorial`
Removes a tutorial with a specific tutorial name to a given module.
Will give an error message if the module given has not been made, or if the tutorial does not exist.

Format: `removeTutorial TUTORIALNAME MODULE`

Example: `removeTutorial c12 CS2103T`

### Gives a specific user a given status: `changeStatus`
Changes the status of the user at the specified INDEX for a given module,
whether they are a professor, teaching assistant, user or none, a placeholder value.
The index refers to the index number shown in the displayed user list. It must be a positive integer.
The letter represents the status to change the user to: “p” for professor, “t” for TA, “s” for student and “n” for none.

Format: `changeStatus INDEX MODULE (LETTER: p/t/s/n)`

Example: `changeStatus 2 CS2103T t`

### Adds a module to the addressBook: `addModule`
Adds the module to the addressBook and is displayed on the GUI.
The module name must consist of only alphanumeric characters, no shorter than 5 characters.
Will give an error message if the module already exists in the addressBook,
further prompts are given for incorrect inputs.

Format: `addModule m/MODULE_NAME`

Example: `addModule m/CS2100`

### Deletes a module from the addressBook: `deleteModule`
Deletes the module from the addressBook and the GUI will change to reflect this.
The index refers to the position of the module displayed on the GUI.
Will give an error message if the module does not exist in the addressBook,
further prompts are given for incorrect inputs.

Format: `deleteModule INDEX`

Example: `deleteModule 1`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**List**   | `list`
**Help**   | `help`
**addModule** | `addModule m/MODULE_NAME` <br> e.g., `addModule m/CS2100`
**deleteModule** | `deleteModule INDEX` <br> e.g., `deleteModule 1`
**addToModule** | `addToModule INDEX MODULE` <br> e.g., `addToModule 2 CS2103T`
**removeFromModule** | `removeFromModule INDEX MODULE` <br> e.g., `removeFromModule 2 CS2103T`
**addTutorial** | `addTutorial TUTORIALNAME MODULE` <br> e.g., `addTutorial c12 CS2103T`
**removeTutorial** | `removeTutorial TUTORIALNAME MODULE` <br> e.g., `removeTutorial c12 CS2103T`
**changeStatus** | `changeStatus INDEX MODULE (LETTER: p/t/s/n)` <br> e.g., `changeStatus 2 CS2103T t`
