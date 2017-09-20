package asymmetrik.challenge.implementation;

import asymmetrik.challenge.implementation.Candidate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A data structure to store a suggest trie.
 */
class SuggestTrie {

    private TrieNode root;

    SuggestTrie() {
        this.root = new TrieNode('\0'); // Create root node with null char, so we can't accidentally use it
    }

    /**
     * An internal structure to actually represent the tree nodes.  These nodes are character based, while the
     * outer class deals with strings.
     */
    private class TrieNode {
        private char character;                     // The character this node represents in a string
        private Map<Character, TrieNode> children;  // Maps the next char in the string to children
        private int count;                          // How many times this node forms the end of a word

        private TrieNode(char character) {
            this.character = character;
            this.count = 0;
            this.children = new HashMap<>();
        }

        /**
         * Adds a new child to this node with the specified character.  Does nothing if the child exists
         * @param character the character this child should represent
         * @return the child node that was created
         */
        private TrieNode addChild(char character) {
            if (this.children.containsKey(character)) {
                return children.get(character);
            } else {
                TrieNode child = new TrieNode(character);
                this.children.put(character, child);
                return child;
            }
        }

        /**
         * Checks if the given character is a child of this tree node
         * @param character the character to check
         * @return whether the node has the child
         */
        private boolean hasChild(char character) {
            return this.children.containsKey(character);
        }

        /**
         * gets the child node of this node that represents the given character
         * @param character the node to get
         * @return the node, or null if the child does not exist
         */
        private TrieNode getChild(char character) {
            return this.children.get(character);
        }

        /**
         * Checks if this node terminates a word
         * @return true if this node terminates a word, false otherwise
         */
        private boolean isWord() {
            return this.count > 0;
        }

        /**
         * gets the number of times the word this node terminates occured
         * @return the count
         */
        private int getCount() {
            return this.count;
        }

        /**
         * updates this node so that it increases the count of the word that it terminates
         */
        private void terminateWord() {
            this.count ++;
        }

        /**
         * getter for character field
         * @return the character
         */
        private char getCharacter() {
            return this.character;
        }
    }

    /**
     * Checks if the given node completes a word, and if it does it adds it to the list
     * @param node the node to check
     * @param soFar the string that lead up to this node
     * @param candidateList the list of all candidates
     */
    private void recursiveLookup(TrieNode node, String soFar, List<Candidate> candidateList) {
        // Check if this node completes a word, if it is then add it to the candidate list
        if (node.isWord()) {
            candidateList.add(
                    new Candidate(soFar + node.getCharacter(), node.getCount())
            );
        }
        // Now iterate over its children and recursive call
        for(Entry<Character, TrieNode> childEntry : node.children.entrySet()) {
            recursiveLookup(childEntry.getValue(), soFar + node.getCharacter(), candidateList);
        }
        // Recursion will terminate when it reaches a node with no children
    }

    /**
     * gets all the candidates that match the fragment and stores it in the candidate list
     * @param fragment the string to complete
     * @param candidateList a list to be filled with matched candidates
     */
    void lookup(String fragment, List<Candidate> candidateList) {
        char [] start = fragment.toLowerCase().toCharArray();
        //get root of subtree we must search
        TrieNode searchRoot = this.root;
        for (char character : start) {
            if (searchRoot.hasChild(character)) {
                searchRoot = searchRoot.getChild(character);
            } else {
                // If we get here, that means that we do not have any subtrees that match this fragment, so just return
                return;
            }
        }
        // Do a recursive lookup from the search root
        recursiveLookup(searchRoot, fragment.substring(0, fragment.length()-1), candidateList);
    }

    /**
     * Stores the given word into the structure
     * @param word the word to store
     */
     void store(String word) {
        char [] chars = word.toLowerCase().toCharArray();
        TrieNode curr = this.root;
        for (char character : chars) {
            // If this node already has that character, get the one that already exists
            if (curr.hasChild(character)) {
                curr = curr.getChild(character);
            } else {
            // Otherwise just create a new child
                curr = curr.addChild(character);
            }
        }
        // curr should be the node representing the terminator for this word
        curr.terminateWord();
    }


}
