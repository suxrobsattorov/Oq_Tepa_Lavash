package ok.suxrob.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogService {

    public static void log(String first_name, String last_name, Long user_id, String txt) {
        try {
            System.out.println("\n --------------------------------------------------------");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            System.out.println(dateFormat.format(LocalDateTime.now()));
            System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
