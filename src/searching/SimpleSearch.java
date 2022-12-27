package searching;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class SimpleSearch {

    /**
     * Dec 26, 2022 5:09:10 PM searching.SimpleSearch test1
     * INFO: 1354 ms
     */
    @Test
    public void test1(){
        File f = new File("./newFolder/books/Sherlock_Holmes.txt");
        String word = "go";
        long initial = System.currentTimeMillis();
        Optional<List<String>> res = search(f, word);
        List<String> result = new LinkedList<>();
        if(res.isPresent()) result = res.get();
        Logger log = Logger.getLogger(SimpleSearch.class.getName());
        String time = Long.toString(System.currentTimeMillis() - initial);
        String values = String.format("%s - %d", result.toString(), result.size());
        log.log(Level.INFO, values);
        log.log(Level.INFO, time);
    }

    /**
     * Dec 26, 2022 5:10:56 PM searching.SimpleSearch test2
     * INFO: 511 ms threads = 3
     */
    @Test
    public void test2(){
        File f = new File("./newFolder/books/Sherlock_Holmes.txt");
        String word = "go";
        long initial = System.currentTimeMillis();
        Optional<List<String>> res = parallelSearch(f, word);
        List<String> result = new LinkedList<>();
        if(res.isPresent()) result = res.get();
        Logger log = Logger.getLogger(SimpleSearch.class.getName());
        String time = Long.toString(System.currentTimeMillis() - initial);
        String values = String.format("%s - %d", result.toString(), result.size());
        log.log(Level.INFO, values);
        log.log(Level.INFO, time);
    }

    public Optional<List<String>> search(File f, String word){
        List<String> res = null;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f.getAbsolutePath())))){
            res =  this.search(br.lines(),word, f.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.of(res);
    }

    private List<String> search(Stream<String> stream, String word, String fileName) {
        List<String> res = new LinkedList<>();
        AtomicLong line = new AtomicLong(1);
        stream.forEach(string -> {
            getAllIndexes(string, word, res, line, fileName);
            line.getAndIncrement();
        });
        return res;
    }

    private void getAllIndexes(String str, String word, List<String> res, AtomicLong line, String fileName){
        int n = str.length();
        int m = word.length();
        int i = 0;
        while(i<n){
            if(compare(str.charAt(i),word.charAt(0)) && n - i >= m && stringCompare(str,word,i)){
                res.add("Line : " + line.toString() + ", index : " + i + ". " + str.substring(i,i+m) + " " + fileName + "\n");
                i += m - 1;
            }
            i++;
        }
    }

    public Optional<List<String>> parallelSearch(File f, String word){
        List<String> res = null;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f.getAbsolutePath())))){
            res =  this.parallelSearch(br.lines(),word,3, f.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.of(res);
    }

    /**
     *
     * @param stream
     * @param word
     * @param threads
     * @return
     *
     * other way
     *      List<String> result = new LinkedList<>();
     *      res.drainTo(result);
     * @return List<E> result;
     */
    private List<String> parallelSearch(Stream<String> stream, String word, int threads, String fileName) {
        LinkedTransferQueue<String> res = new LinkedTransferQueue<>();
        AtomicLong line = new AtomicLong(1);
        ForkJoinPool customThreadPool = new ForkJoinPool(threads);
        try {
            customThreadPool.submit(
                    () -> stream.parallel().forEach(string -> {
                        try {
                            getAllIndexesParallely(string, word, res, line, fileName);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                        line.getAndIncrement();
                    })).join();
        } finally {
            customThreadPool.shutdown();
        }
        List<String> result = new LinkedList<>();
        res.drainTo(result);
        return result;
    }

    private void getAllIndexesParallely(String str, String word, LinkedTransferQueue<String> res, AtomicLong line, String fileName) throws InterruptedException {
        int n = str.length();
        int m = word.length();
        int i = 0;
        while(i<n){
            if(compare(str.charAt(i),word.charAt(0)) && n - i >= m && stringCompare(str,word,i)){
                    if(res.offer("Line : " + line.toString() + ", index : " + i + ". " + str.substring(i,i+m) + " " + fileName +"\n"))
                        i += m - 1;
                    else throw new InterruptedException("Something went wrong in parallelism.");
            }
            i++;
        }
    }

    private boolean stringCompare(String str, String word, int strIdx){
        for(int j = strIdx, k = 0; k<word.length(); j++, k++) {
            if(!compare(str.charAt(j), word.charAt(k))) {return false;}
        }
        return true;
    }

    private boolean compare(char a, char b){
        String lowerCaseA = ("" + a).toLowerCase();
        String lowerCaseB = ("" + b).toLowerCase();
        return lowerCaseB.equals(lowerCaseA);
    }


}
