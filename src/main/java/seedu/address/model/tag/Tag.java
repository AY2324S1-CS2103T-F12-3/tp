package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.module.Module;
import seedu.address.model.tutorial.Tutorial;

/**
 * Represents a Tag in the address book.
 */
public class Tag {

    private final String NO_TUTORIAL_MESSAGE = "no tutorial yet";

    public final Module module;

    public final Tutorial tutorial;

    /**
     * Constructs a {@code Tag}.
     * @param module A module of a student.
     * @param tutorial A tutorial of a student.
     */
    public Tag(Module module, Tutorial tutorial) {
        requireNonNull(module);
        this.module = module;
        this.tutorial = tutorial;
    }

    /**
     * @param other the other object to be compared.
     * @return true if the Module is same only!
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return module.equals(otherTag.module);
    }

    /**
     * justify if the Module and the Tutorial of both Tags are same.
     * @param other the other object to be compared.
     * @return true if the Module and Tutorial are same!
     */
    public boolean isSame(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return module.equals(otherTag.module) && tutorial.equals(otherTag.tutorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(module, tutorial);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        String tutorialName = (tutorial == null) ? NO_TUTORIAL_MESSAGE : tutorial.toString();
        return "[Module: " + module + ", Tutorial: " + tutorial + "]";
    }

}
