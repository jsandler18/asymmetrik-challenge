package asymmetrik.challenge.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import asymmetrik.challenge.implementation.dataStructures.SuggestTrie;


/**
 * A class to train, store, and query an autocomplete model for english word fragments
 */
public class AutocompleteProvider {
    private SuggestTrie trie;


    public AutocompleteProvider() {
        this.trie = new SuggestTrie();
    }

    /**
     * Takes a string fragment and gives a list of potential candidates
     * @param fragment the string fragment
     * @return a list of candidates, sorted by decreasing confidence
     */
    public List<Candidate> getWords(String fragment) {
        List<Candidate> result = new ArrayList<>();
        trie.lookup(fragment, result);
        Collections.sort(result);
        return result;
    }

    /**
     * Adds new data to this model to improve its suggestion capabilties.
     * Multiple calls will not delete old data, but add to it.
     * @param passage the text to train the model on.
     */
    void train(String passage) {
        // Split the passage based on anything that isn't letters, apostrophe, or hyphen
        String [] words = passage.split("[^A-Za-z'-]");
        for (String word: words) {
            trie.store(word);
        }
    }
}
