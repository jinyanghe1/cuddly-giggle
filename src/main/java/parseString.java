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
    public static void syntaxError(String cases){
        switch (cases){
            case "ce":
            {
                System.out.println("illegal arguments. you can only choose one file transform.)");
            return;
            }
            case "dr":
            {
                System.out.println("cannot assign depth and use recursive search.");
            return;
            }
            case "notdepth":
            {
                System.out.println("depth must be positive integer.");
                return;
            }
        }

    }
    public static void syntaxError(){
        System.out.println("illegal argument. maybe you want to use -h for help?");
    }
}
