package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Tutorial;

/**
 * Takes attendance from the person.
 */
public class AttendanceCommand extends Command {
    public static final String COMMAND_WORD = "attn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Takes attendance for the indicated person. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ATTENDANCE + "LESSON_NUMBER\n"
            + "Example: " + COMMAND_WORD + " 2 ln/S1";

    public static final String MESSAGE_SUCCESS = "Attendance successfully taken.";
    public static final String MESSAGE_DUPLICATE_ATTENDANCE = "This attendance has already been taken!";
    private final Index index;
    private final Tag toAdd;

    /**
     * @param index of the person in the filtered person list to add tag to
     */
    public AttendanceCommand(Index index, Tag toAdd) {
        requireNonNull(index);
        this.index = index;
        this.toAdd = toAdd;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        // create special tag to put in attendance list
        String studentNumber = personToEdit.getStudentNumber().toString();
        Tag specialTag = new Tag(studentNumber + toAdd.tagName);

        if (model.hasAttendanceTag(specialTag)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTENDANCE);
        }
        Person editedPerson = createEditedPerson(personToEdit);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.addAttendanceTag(specialTag);
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

        Set<Tag> updatedTags = new HashSet<>();
        for (Tag tag : personToEdit.getTags()) {
            updatedTags.add(tag);
        }
        updatedTags.add(toAdd);

        Set<Module> updatedModules = new HashSet<>(personToEdit.getModules());
        Set<Tutorial> updatedTutorials = personToEdit.getTutorials();
        StudentNumber updatedStudentNumber = personToEdit.getStudentNumber();
        Telegram updatedTelegram = personToEdit.getTelegram();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedTags, updatedModules,
                updatedTutorials, updatedStudentNumber, updatedTelegram);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AttendanceCommand)) {
            return false;
        }

        return this.index.equals(((AttendanceCommand) other).index)
                && this.toAdd.equals(((AttendanceCommand) other).toAdd);
    }
}
