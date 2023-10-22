import java.util.Scanner;
import java.io.File;
import java.util.HashMap;

/**
 * Class that contains helper methods for the Review Lab
 **/
public class translatorCode {

    private static HashMap<String, String> englishToSpanish = new HashMap<String, String>();
    private static HashMap<String, String> spanishToEnglish = new HashMap<String, String>();

    static {

        // read in the words in English_Spanish_Translation.txt
        try {
            Scanner input = new Scanner(new File("English_Spanish_Translation.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                String spanish = line.substring(0, line.indexOf(","));
                String english = line.substring(line.indexOf(",") + 2);

                englishToSpanish.put(english, spanish);
                spanishToEnglish.put(spanish, english);
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error reading or parsing English_Spanish_Translation.txt\n" + e);
        }
    }

    /**
     * Author: PLTW Company; Taken from activity 2.4.6
     * 
     * @returns A parsed String from a .txt file
     *          We need to make explaination of slideshow for this method explaining
     *          how they work
     */
    public static String textToString(String fileName) {
        String temp = "";
        try {
            Scanner input = new Scanner(new File(fileName));

            // add 'words' in the file to the string, separated by a single space
            while (input.hasNext()) {
                temp = temp + input.next() + " ";
            }
            input.close();

        } catch (Exception e) {
            System.out.println("Unable to locate " + fileName);
        }
        // remove any additional space that may have been added at the end of the string
        return temp.trim();
    }

    public static String englishToSpanishTranslator(String fileName) {
        String res = "";
        String sentence = textToString(fileName) + " ";
        sentence.replaceAll("\\p{Punct}", "");

        while (sentence.length() > 0) {
            String word = sentence.substring(0, sentence.indexOf(" ")).toLowerCase();

            String test = englishToSpanish.get(word);
            if (substringCheckerEnglish(word)) {

                sentence = sentence.substring(sentence.indexOf(" ") + 1);
                String new_word = word + " " + sentence.substring(0, sentence.indexOf(" ")).toLowerCase();

                if (englishToSpanish.get(new_word) == null && englishToSpanish.get(word) != null) {
                    res += englishToSpanish.get(word) + " ";
                } else if (englishToSpanish.get(new_word) != null) {
                    while (substringCheckerEnglish(new_word)) {
                        sentence = sentence.substring(sentence.indexOf(" ") + 1);
                        new_word = new_word + " " + sentence.substring(0, sentence.indexOf(" ")).toLowerCase();
                    }
                    res += englishToSpanish.get(new_word) + " ";
                    sentence = sentence.substring(sentence.indexOf(" ") + 1);
                } else {
                    res += word + " " + sentence.substring(0, sentence.indexOf(" ")).toLowerCase() + " ";
                    sentence = sentence.substring(sentence.indexOf(" ") + 1);
                }
            } else if (test != null) {
                res += test + " ";
                sentence = sentence.substring(sentence.indexOf(" ") + 1);
            } else {
                res += sentence.substring(0, sentence.indexOf(" ")).toLowerCase() + " ";
                sentence = sentence.substring(sentence.indexOf(" ") + 1);
            }
        }

        return res;
    }

    public static String spanishToEnglishTranslator(String fileName) {
        String res = "";
        String sentence = textToString(fileName) + " ";
        sentence.replaceAll("\\p{Punct}", "");

        while (sentence.length() > 0) {
            String word = sentence.substring(0, sentence.indexOf(" ")).toLowerCase();

            String test = spanishToEnglish.get(word);
            if (substringCheckerSpanish(word)) {

                sentence = sentence.substring(sentence.indexOf(" ") + 1);
                String new_word = word + " " + sentence.substring(0, sentence.indexOf(" ")).toLowerCase();

                if (spanishToEnglish.get(new_word) == null && spanishToEnglish.get(word) != null) {
                    res += spanishToEnglish.get(word) + " ";
                } else if (spanishToEnglish.get(new_word) != null) {
                    while (substringCheckerSpanish(new_word)) {
                        sentence = sentence.substring(sentence.indexOf(" ") + 1);
                        new_word = new_word + " " + sentence.substring(0, sentence.indexOf(" ")).toLowerCase();
                    }
                    res += spanishToEnglish.get(new_word) + " ";
                    sentence = sentence.substring(sentence.indexOf(" ") + 1);
                } else {
                    res += word + " " + sentence.substring(0, sentence.indexOf(" ")).toLowerCase() + " ";
                    sentence = sentence.substring(sentence.indexOf(" ") + 1);
                }
            } else if (test != null) {
                res += test + " ";
                sentence = sentence.substring(sentence.indexOf(" ") + 1);
            } else {
                res += sentence.substring(0, sentence.indexOf(" ")).toLowerCase() + " ";
                sentence = sentence.substring(sentence.indexOf(" ") + 1);
            }
        }

        return res;
    }

    public static boolean substringCheckerEnglish(String word) {
        for (String key : englishToSpanish.keySet()) {
            if (key.contains(word + " ")) {
                return true;
            }
        }
        return false;
    }

    public static boolean substringCheckerSpanish(String word) {
        for (String key : spanishToEnglish.keySet()) {
            if (key.contains(word + " ")) {
                return true;
            }
        }
        return false;
    }

}
