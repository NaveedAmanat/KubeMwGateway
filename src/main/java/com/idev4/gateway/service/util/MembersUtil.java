package com.idev4.gateway.service.util;

import com.idev4.gateway.service.dto.Members;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MembersUtil {

    // private static String USERS_FILE_PATH = File.separator + "opt" + File.separator + "tomcat" + File.separator + "webapps" +
    // File.separator
    // + "kashaf" + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "users" + File.separator + "users.csv";
    private static final String USERS_FILE_PATH = // "C:\\" + "users.csv";
            File.separator + "opt" + File.separator + "tomcat" + File.separator + "webapps" + File.separator + "kashaf" + File.separator
                    + "WEB-INF" + File.separator + "classes" + File.separator + "users" + File.separator + "users.csv";

    public static List<Members> getAllMembers() {

        List<Members> mems = new ArrayList<>();
        mems.add(new Members("admin", "admin", "admin"));

        mems.add(new Members("bdo", "admin", "bdo"));

        mems.add(new Members("bm", "admin", "bm"));

//         try {
//             mems = getMapFromCSV( USERS_FILE_PATH );
//             return mems;
//             // map.forEach( action );
//         } catch ( IOException e ) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
        return mems;
    }

    public static List<Members> getMapFromCSV(final String filePath) throws IOException {

        Stream<String> lines = Files.lines(Paths.get(filePath));
        List<Members> mems = new ArrayList<>();
        lines.forEach(line -> {
            String[] arr = line.split(",", 3);
            mems.add(new Members(arr[0], arr[1], arr[2]));
        });
        return mems;
    }
}
