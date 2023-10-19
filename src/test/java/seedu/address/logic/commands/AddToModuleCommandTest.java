package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class AddToModuleCommandTest {
    @Test
    public void execute_indexTooBig_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandFailure(new AddToModuleCommand(Index.fromZeroBased(999999),
                new Module("CS1000")), model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
    @Test
    public void execute_moduleNotFound_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandFailure(new AddToModuleCommand(INDEX_FIRST_PERSON, new Module("CS1000")), model,
                Messages.MESSAGE_INVALID_MODULE);
    }

    @Test
    public void execute_validIndexAndModule_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Module testModule = new Module("CS1000");
        model.addModule(testModule);

        Person originalPerson = model.getAddressBook().getPersonList().get(0);
        Set<Tag> updatedTags = new HashSet<>(originalPerson.getTags());
        // may be changed later
        updatedTags.add(new Tag(new Module("CS1000")));
        Person editedPerson = new Person(originalPerson.getName(), originalPerson.getPhone(),
                originalPerson.getEmail(), originalPerson.getAddress(), updatedTags);
        AddToModuleCommand editCommand = new AddToModuleCommand(INDEX_FIRST_PERSON, testModule);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.addModule(testModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Module test1 = new Module("CS1000");
        Module test2 = new Module("CS2000");
        AddToModuleCommand addToModuleCommand = new AddToModuleCommand(INDEX_FIRST_PERSON,
                test1);
        AddToModuleCommand addToModuleSecondCommand = new AddToModuleCommand(INDEX_SECOND_PERSON,
                test2);

        // same object -> returns true
        assertTrue(addToModuleCommand.equals(addToModuleCommand));

        // same values -> returns true
        AddToModuleCommand addToModuleCommandCopy = new AddToModuleCommand(INDEX_FIRST_PERSON,
                test1);
        assertTrue(addToModuleCommand.equals(addToModuleCommandCopy));

        // different types -> returns false
        assertFalse(addToModuleCommand.equals(1));

        // null -> returns false
        assertFalse(addToModuleCommand.equals(null));

        // different person -> returns false
        assertFalse(addToModuleCommand.equals(addToModuleSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        AddToModuleCommand addToModuleCommand = new AddToModuleCommand(INDEX_FIRST_PERSON,
                new Module("CS1000"));
        String expected = AddToModuleCommand.class.getCanonicalName() + "{index=" + targetIndex + "}";
        assertEquals(expected, addToModuleCommand.toString());
    }
}
