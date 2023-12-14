import java.io.File;
import java.util.*;
import java.util.concurrent.Callable;

public class fileTree {
    File thisFile;
    public void visit(int depth) throws Exception {
        LinkedList<File> waitingList = new LinkedList<File>();
        waitingList.addFirst(thisFile);
        File dir = thisFile;
        while(!waitingList.isEmpty()){
            File cur = waitingList.removeFirst();
            visitFile(cur);
            if(cur.isDirectory()){
                for(File f: Objects.requireNonNull(cur.listFiles()))waitingList.addFirst(f);
            }
        }
    }
    private void visitFile(File thisfile){
        fileChange.xls(thisfile);
    }
}
