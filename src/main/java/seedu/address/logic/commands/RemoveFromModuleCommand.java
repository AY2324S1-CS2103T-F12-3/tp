package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class RemoveFromModuleCommand extends Command {

    public static final String COMMAND_WORD = "removeFromModule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a user from a given module "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MODULE + "MODULE]\n "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE + "CS1000 ";

    public static final String MESSAGE_SUCCESS = "Edited Person: %1$s";

    private final Index index;

    private final Module moduleToRemoveFrom;

    /**
     * @param index of the person in the filtered person list to add tag to
     */
    public RemoveFromModuleCommand(Index index, Module moduleToRemoveFrom) {
        requireNonNull(index);
        requireNonNull(moduleToRemoveFrom);
        this.index = index;
        this.moduleToRemoveFrom = moduleToRemoveFrom;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!model.hasModule(moduleToRemoveFrom)) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (!personHasModule(personToEdit, this.moduleToRemoveFrom)) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE);
        }
        Person editedPerson = createEditedPerson(personToEdit);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private Person createEditedPerson(Person personToEdit) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = new HashSet<>(personToEdit.getTags());
        updatedTags.remove(new Tag(this.moduleToRemoveFrom, null));

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    /**
     * Returns if a given person is part of a specified module.
     * @param personToCheck The given person.
     * @param module The specified module.
     * @return Whether the person is part of the module.
     */
    private boolean personHasModule(Person personToCheck, Module module) {
        return personToCheck.getTags().contains(new Tag(module, null));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveFromModuleCommand)) {
            return false;
        }

        return this.index.equals(((RemoveFromModuleCommand) other).index)
                && this.moduleToRemoveFrom.equals(((RemoveFromModuleCommand) other).moduleToRemoveFrom);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }
}
