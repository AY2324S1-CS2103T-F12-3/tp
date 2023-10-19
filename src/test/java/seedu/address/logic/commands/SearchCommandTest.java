package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SearchCommand.
 */
public class SearchCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_searchByName() {
        Name nameToSearch = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getName();
        List<Name> nameList = new ArrayList<>(Arrays.asList(nameToSearch));
        List<Tag> moduleList = new ArrayList<>();

        expectedModel.updateFilteredPersonList(person -> person.getName().equals(nameToSearch));
        assertCommandSuccess(new SearchCommand(nameList, moduleList), model,
                SearchCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_searchByModule() {
        // may be changed later
        Tag module = new Tag(new Module(MODULE_CS2100));
        List<Name> nameList = new ArrayList<>();
        List<Tag> moduleList = new ArrayList<>(Arrays.asList(module));

        expectedModel.updateFilteredPersonList(person -> person.getTags().contains(module));
        assertCommandSuccess(new SearchCommand(nameList, moduleList), model,
                SearchCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        List<Name> aliceNameList = new ArrayList<>(Arrays.asList(new Name("Alice")));
        List<Name> bobNameList = new ArrayList<>(Arrays.asList(new Name("Bob")));

        // may be changed later
        List<Tag> cs2100ModuleList = new ArrayList<>(Arrays.asList(new Tag(new Module("CS2100"))));
        List<Tag> cs2040ModuleList = new ArrayList<>(Arrays.asList(new Tag(new Module("CS2040"))));

        SearchCommand baseSearchCommand = new SearchCommand(aliceNameList, cs2100ModuleList);
        SearchCommand baseSearchCommandAlt = new SearchCommand(aliceNameList, cs2100ModuleList);
        SearchCommand diffNameSearchCommand = new SearchCommand(bobNameList, cs2100ModuleList);
        SearchCommand diffModSearchCommand = new SearchCommand(aliceNameList, cs2040ModuleList);
        SearchCommand diffNameDiffModSearchCommand = new SearchCommand(bobNameList, cs2040ModuleList);

        // same object -> returns true
        assertTrue(baseSearchCommand.equals(baseSearchCommand));

        // same values -> returns true
        assertTrue(baseSearchCommand.equals(baseSearchCommandAlt));

        // different types -> returns false
        assertFalse(baseSearchCommand.equals(1));

        // null -> returns false
        assertFalse(baseSearchCommand.equals(null));

        // different name or mod
        assertFalse(baseSearchCommand.equals(diffNameSearchCommand));
        assertFalse(baseSearchCommand.equals(diffModSearchCommand));
        assertFalse(baseSearchCommand.equals(diffNameDiffModSearchCommand));
    }
}
