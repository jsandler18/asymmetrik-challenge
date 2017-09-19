package asymmetrik.challenge.implimentation;

/**
 * A class to represent an autocomplete candidate word.
 */
public class Candidate implements Comparable<Candidate>{
    private String word;
    private int confidence;

    /**
     * Create a Candidate with a word and confidence.
     * @param word the word being suggested
     * @param confidence the confidence this is the desired word
     */
    protected Candidate (String word, int confidence) {
        this.word = word;
        this.confidence = confidence;
    }

    /**
     * Gets the confidence that this candidate is desired
     * @return the confidence
     */
    public int getConfidence() {
        return confidence;
    }

    /**
     * Gets the word being suggested
     * @return the word
     */
    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(Candidate o) {
        //we want to sort by confidence only
        return this.confidence - o.confidence;
    }
}
