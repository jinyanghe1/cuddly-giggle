import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Callable;

public class fileTree {
    ArrayList<File> l;
    public fileTree(ArrayList<File> ls){
        this.l=ls;
    }
    public void visit(int depth) throws Exception {
        for(File f:this.l){
            ArrayList<File> waitingList = new ArrayList<>();
            waitingList.add(f);
            while(!waitingList.isEmpty()){
                File cur = waitingList.remove(0);
                visitFile(cur);
                if(cur.isDirectory()){
                    waitingList.addAll(Arrays.asList(Objects.requireNonNull(cur.listFiles())));
                }
            }
        }
    }
    private void visitFile(File thisfile){
        fileChange.xls(thisfile);
    }
}
