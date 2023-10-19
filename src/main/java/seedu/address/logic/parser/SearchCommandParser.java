package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new SearchCommand object
 */
public class SearchCommandParser implements Parser<SearchCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SearchCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE);
        if (!argMultimap.getPreamble().isEmpty() || argMultimap.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }

        final List<Name> personNameList = argMultimap.getAllValues(PREFIX_NAME)
                .stream()
                .map(nameString -> new Name(nameString))
                .collect(Collectors.toList());
        final List<Tag> moduleTagList = argMultimap.getAllValues(PREFIX_MODULE)
                .stream()
                .map(moduleCode -> new Tag(new Module(moduleCode), null)) // may be changed
                .collect(Collectors.toList());
        return new SearchCommand(personNameList, moduleTagList);
    }
}
