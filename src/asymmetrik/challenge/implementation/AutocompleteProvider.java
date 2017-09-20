package asymmetrik.challenge.implementation;

import java.util.ArrayList;
import java.util.List;
import asymmetrik.challenge.implementation.dataStructures.SuggestTrie;


/**
 * A class to train, store, and query an autocomplete model for english word fragments
 */
public class AutocompleteProvider {

    /**
     * Takes a string fragment and gives a list of potential candidates
     * @param fragment the string fragment
     * @return a list of candidates, sorted by decreasing confidence
     */
    public List<Candidate> getWords(String fragment) {
        List<Candidate> result = new ArrayList<>();
        return result;
    }

    /**
     * Adds new data to this model to improve its suggestion capabilties.
     * Multiple calls will not delete old data, but add to it.
     * @param passage the text to train the model on.
     */
    void train(String passage) {

    }
}
