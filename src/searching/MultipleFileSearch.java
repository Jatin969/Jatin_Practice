package searching;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MultipleFileSearch {


    @Test
    public void test1(){
        long initial = System.currentTimeMillis();
        File f = new File("newFolder/books");
        String word = "go";
        List<String> list = searchInFolderParallely(f,word);
        String size = Integer.toString((list.size()));
        String time = System.currentTimeMillis() - initial + "ms in parallel";
        Logger.getAnonymousLogger().log(Level.INFO, time);
        Logger.getAnonymousLogger().log(Level.INFO, size);
    }

    @Test
    public void test2(){
        long initial = System.currentTimeMillis();
        File f = new File("newFolder/books");
        String word = "go";
        List<String> list = searchInFolder(f,word);
        String size = Integer.toString((list.size()));
        String time = System.currentTimeMillis() - initial + "ms in sequential";
        Logger.getAnonymousLogger().log(Level.INFO, time);
        Logger.getAnonymousLogger().log(Level.INFO, size);
    }




    public List<String> searchInFolder(File file, String word) {
        List<String> list = new LinkedList<>();
        if(file.isDirectory()) searchInFolderHelper(file,word,list);
        return list;
    }

    private void searchInFolderHelper(File file, String word, List<String> list){
        String[] directoryList = file.list();
        if(directoryList == null) return;
        for (int i = 0; i<directoryList.length; i++) {
            String name = directoryList[i];
            File childFile = new File(String.format("%s\\%s", file.getAbsolutePath(), name));
            if (childFile.canRead() && !childFile.isDirectory()) {
                Thread mt = new Thread(new MyThreadSequential(childFile,word,list));
                mt.start();
                try{
                    mt.join();
                }catch (InterruptedException e){
                    Logger.getAnonymousLogger().log(Level.WARNING,"Interupted!!!", e);
                    Thread.currentThread().interrupt();
                }
            }else if(childFile.isDirectory()){
                searchInFolderHelper(childFile, word, list);
            }
        }
    }

    public List<String> searchInFolderParallely(File file, String word) {
        List<String> list = new LinkedList<>();
        if(file.isDirectory()) searchInFolderHelperParallel(file,word,list);
        return list;
    }

    private void searchInFolderHelperParallel(File file, String word, List<String> list){
        String[] directoryList = file.list();
        if(directoryList == null) return;
        for (int i = 0; i<directoryList.length; i++) {
            String name = directoryList[i];
            File childFile = new File(String.format("%s\\%s", file.getAbsolutePath(), name));
            if (childFile.canRead() && !childFile.isDirectory()) {
                Thread mt = new Thread(new MyThreadParallel(childFile,word, list));
                mt.start();
                try{
                    mt.join();
                }catch (InterruptedException e){
                    Logger.getAnonymousLogger().log(Level.WARNING,"Interupted!!!", e);
                    Thread.currentThread().interrupt();
                }
            }else if(childFile.isDirectory()){
                searchInFolderHelperParallel(childFile, word, list);
            }
        }
    }

    class MyThreadSequential implements Runnable{
        File file;
        String word;
        List<String> list;
        public MyThreadSequential(File file, String word, List<String> list) {
            this.file = file;
            this.word = word;
            this.list = list;
        }

        @Override
        public void run() {
            SimpleSearch ss = new SimpleSearch();
            final Optional<List<String>> search = ss.search(file, word);
            if(search.isPresent())
                list.addAll(search.get());
        }
    }

    class MyThreadParallel implements Runnable{
        File file;
        String word;
        List<String> list;

        public MyThreadParallel(File file, String word, List<String> list) {
            this.file = file;
            this.word = word;
            this.list = list;
        }

        @Override
        public void run() {
            SimpleSearch ss = new SimpleSearch();
            final Optional<List<String>> search = ss.parallelSearch(file,word);
            if(search.isPresent()) list.addAll(search.get());
        }
    }
}
