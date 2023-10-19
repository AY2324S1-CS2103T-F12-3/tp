package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchCommand;
import seedu.address.model.module.Module;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class SearchCommandParserTest {
    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_namesPresentSuccess() {
        Name expectedName = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build().getName();
        List<Name> expectedNameList = new ArrayList<>(Arrays.asList(expectedName));
        List<Tag> expectedModuleList = new ArrayList<>();
        assertParseSuccess(parser, " n/" + BOB.getName(),
                new SearchCommand(expectedNameList, expectedModuleList));
    }

    @Test
    public void parse_modulesPresentSuccess() {
        List<Name> expectedNameList = new ArrayList<>();
        Tag testModuleTag = new Tag(new Module(MODULE_CS2100));
        List<Tag> expectedModuleList = new ArrayList<>(Arrays.asList(testModuleTag));
        assertParseSuccess(parser, " m/" + MODULE_CS2100,
                new SearchCommand(expectedNameList, expectedModuleList));
    }

    @Test
    public void parse_preambleNotEmptyError() {
        assertParseFailure(parser, " dummy text", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SearchCommand.MESSAGE_USAGE));
    }
}

