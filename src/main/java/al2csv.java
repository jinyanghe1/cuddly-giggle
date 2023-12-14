import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Objects;
import static java.lang.Integer.parseInt;
import java.util.regex.*;

public class al2csv {
    public static void main(String[] args) {
        String helpmsg = """
                Welcome to al2csv, a commandline tool to help manage excel items and csv,
                BASIC USAGE: al2csv [-h]elp [-rg]ex [[file] [file][...]] [-depth depth] [-r]ecursive\s
                options:
                -h: show this help message and exit.
                -ctoe, -etoc: transform csv files to excel files, or vise versa.
                -rg: enables regex pattern matching.
                -depth: assign limit to recursive searching when having subfolders.
                -r: apply recursive search to all subfolders.""";

        while(true) try {
            ArrayList<String> arguments = parseString.parse();
            /* pattern:
             *  al2csv [-h] [ctoe/etoc] [-rg] [[file] [file][...]] [-depth depth] [-r]
             *
             * */
            /*check sequence: -h? -etoc/-ctoe? -rg? how many files?(makeFileList) depth? -r? -d?
             */
            boolean help = false;
            boolean etoc = false;
            boolean regex = false;
            int depth = 1;
            boolean assignDepth = false;
            boolean recursive = false;
            if (!Objects.equals(arguments.get(0), "al2csv")) {
                parseString.syntaxError();
                continue;
            }
            arguments.remove("al2csv");
            if (arguments.contains("-h")) {
                if (arguments.size() != 2) {
                    parseString.syntaxError();
                    continue;
                }
                System.out.println(helpmsg);
                continue;
            } else if (arguments.contains("-etoc")) {
                etoc = true;
                arguments.remove("-etoc");
            }
            if (arguments.contains("-rg")) {
                regex = true;
                arguments.remove("-rg");
            }
            if (arguments.contains("-depth")) {
                assignDepth = true;
                if (!arguments.get(arguments.indexOf("-depth") + 1).matches("-?\\\\d+(\\\\.\\\\d+)?")) {
                    parseString.syntaxError("notdepth");
                    continue;
                }
                if (arguments.get(arguments.indexOf("-depth") + 1).contains(".")) {
                    parseString.syntaxError("notdepth");
                    continue;
                }
                if (parseInt(arguments.get(arguments.indexOf("-depth") + 1)) < 0) {
                    parseString.syntaxError("notdepth");
                    continue;
                }
                depth = parseInt(arguments.get(arguments.indexOf("-depth") + 1));
                arguments.remove("-depth");
                arguments.remove(Integer.toString(depth));
            }
            if (arguments.contains("-r")) {
                recursive = true;
                arguments.remove("-r");
                if (assignDepth) {
                    parseString.syntaxError("dr");
                    continue;
                }
            }
            ArrayList<File> FileList = new ArrayList<>();
            ArrayList<String> reList = new ArrayList<>();
            File curFile = new File(System.getProperty("user.dir"));
            if (regex) {
                for (int i = 0; i < arguments.size(); i++) {
                    if (Objects.equals(arguments.get(i), "-depth")) {
                        i++;
                        continue;
                    }
                    if (Objects.equals(arguments.get(i), "-r")) continue;
                    reList.add(arguments.get(i));
                }
                for (String s : reList) {
                    for (File f : Objects.requireNonNull(curFile.listFiles())) {
                        if (Pattern.compile(s).matcher(f.getName()).matches()) FileList.add(f);
                    }
                }
            } else {
                for (int i = 0; i < arguments.size(); i++) {
                    if (Objects.equals(arguments.get(i), "-depth")) {
                        i++;
                        continue;
                    }
                    if (Objects.equals(arguments.get(i), "-r")) continue;
                    FileList.add(new File(arguments.get(i)));
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
