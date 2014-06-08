package pl.npe.lpp.data.target;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 07.06.14
 * Time: 13:52
 */
public class UserChoiceResolver {

    private static final Logger logger = Logger.getLogger(UserChoiceResolver.class);

    private final Scanner scanner = new Scanner(System.in);

    boolean overwriteFile(){
        logger.info("Output file already exists, overwrite it? [y/n]");
        int choice = scanner.next().charAt(0);
        scanner.close();
        return decode(choice);
    }

    boolean decode(int choice){
        return choice == (int)'y' || choice == (int)'Y';
    }
}
