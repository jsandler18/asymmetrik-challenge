package asymmetrik.challenge.test;

import asymmetrik.challenge.implementation.AutocompleteProvider;
import asymmetrik.challenge.implementation.Candidate;

public class Test {
    public static void main(String [] args) {
        AutocompleteProvider ac = new AutocompleteProvider();

        // Use example to show it works
        String shortPassage = "The third thing that I need to tell you is that this thing does not think thoroughly.";
        System.out.println("First Training passage:\n");
        System.out.println(shortPassage+"\n");
        ac.train(shortPassage);
        System.out.println("thi:");
        for (Candidate c :ac.getWords("thi")) {
            System.out.println(c.toString());
        }
        System.out.println("\nnee:");
        for (Candidate c :ac.getWords("nee")) {
            System.out.println(c.toString());
        }
        System.out.println("\nth:");
        for (Candidate c :ac.getWords("th")) {
            System.out.println(c.toString());
        }

        // Here is an example passage I pulled off the internet, with punctuation
        String longPassage = "Dolphins are regarded as the friendliest creatures in the sea and stories of them helping drowning sailors" +
                " have been common since Roman times. The more we learn about dolphins, the more we realize that" +
                " their society is more complex than people previously imagined. They look after other dolphins when " +
                "they are ill, care for pregnant mothers and protect the weakest in the community, as we do. Some " +
                "scientists have suggested that dolphins have a language but it is much more probable that they " +
                "communicate with each other without needing words. Could any of these mammals be more intelligent " +
                "than man? Certainly the most common argument in favor of man's superiority over them that we can kill" +
                " them more easily than they can kill us is the least satisfactory. On the contrary, the more we discover" +
                " about these remarkable creatures, the less we appear superior when we destroy them.";
        System.out.println("\nSecond Training passage:\n");
        System.out.println(longPassage+"\n");
        ac.train(longPassage);
        // If we search for th- words again, we see that the most common english words like "the" or "that" at the top
        System.out.println("\nth:");
        for (Candidate c :ac.getWords("th")) {
            System.out.println(c.toString());
        }

        String longPassage2 = "Naval architects never claim that a ship is unsinkable, but the sinking of the " +
                "passenger-and-car ferry Estonia in the Baltic surely should have never have happened. It was well " +
                "designed and carefully maintained. It carried the proper number of lifeboats. It had been thoroughly " +
                "inspected the day of its fatal voyage. Yet hours later, the Estonia rolled over and sank in a cold, " +
                "stormy night. It went down so quickly that most of those on board, caught in their dark, " +
                "flooding cabins, had no chance to save themselves: Of those who managed to scramble overboard, " +
                "only 139 survived. The rest died of hypothermia before the rescuers could pluck them from the cold " +
                "sea. The final death toll amounted to 912 souls. However, there were an unpleasant number of " +
                "questions about why the Estonia sank and why so many survivors were men in the prime of life," +
                " while most of the dead were women, children and the elderly.";

        System.out.println("\nThird Training passage:\n");
        System.out.println(longPassage2+"\n");
        // Again, common words like "in" and "is" show up the most.
        ac.train(longPassage2);
        System.out.println("\ni:");
        for (Candidate c :ac.getWords("i")) {
            System.out.println(c.toString());
        }
        System.out.println("\nw:");
        for (Candidate c :ac.getWords("w")) {
            System.out.println(c.toString());
        }
    }
}
