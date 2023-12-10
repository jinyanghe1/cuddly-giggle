import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class parseString {

    public static ArrayList<String> parse() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        //al2csv -c [-d <dir>] [-rg <regex>] [|]
        return new ArrayList<>(Arrays.asList(str.split(" ")));
    }


}
