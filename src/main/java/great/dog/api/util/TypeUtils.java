package great.dog.api.util;

import java.sql.Timestamp;

public class TypeUtils {

    public static Timestamp StringToTimestamp(String s){
        return Timestamp.valueOf(s);
    }
}
