package javaGenerics;

import java.util.*;

public class genericHashMap <K , V>{

    static class pair<K,V>{
        K k; V v;
        pair(K k, V v){
            this.k = k;
            this.v = v;
        }

        public String toString(){
            return "("+k.toString() + "," + v.toString()+")";
        }
    }
    private List<LinkedList<pair<K,V>>> values;
    private int size;

    private final int collisionCount;

    private final float multiplier;

    public genericHashMap(){
        this(16);
    }

    public genericHashMap(int capacity){
        this(capacity,4);
    }

    public genericHashMap(int capacity, int collisionCount){
        this(capacity,collisionCount,1.6F);
    }

    public genericHashMap(int capacity, int collisionCount, float multiplier){
        this.values = new ArrayList<>();
        for(int i = 0; i<capacity; i++) this.values.add(new LinkedList<>());
        this.size = 0;
        this.collisionCount = collisionCount;
        this.multiplier = multiplier;
    }

    private void dataTransfer(){
        List<LinkedList<pair<K,V>>> list = new ArrayList<>();
        for(int i = 0; i< this.multiplier*values.size(); i++) list.add(new LinkedList<>());
        for(LinkedList<pair<K,V>> collidedList : values)
            for(pair<K,V> p : collidedList)
                list.get(getHash(p.k, list.size())).addLast(p);
        this.values = list;
    }

    public void put(K k, V v){
        LinkedList<pair<K,V>>  list = getList(k);
        if(containsKey(k)) {
            list.removeFirst();
        }else {
            this.size++;
        }
        list.addFirst( new genericHashMap.pair(k,v));
        if(this.collisionCount < list.size()) dataTransfer();

    }

    public V  remove(K k){
        if(containsKey(k)){
            LinkedList<pair<K,V>>  list = getList(k);
            this.size = this.size - 1;
            return list.removeFirst().v;
        }else return null;
    }

    public boolean containsKey(K k){
        LinkedList<pair<K,V>>  list = getList(k);
        int lengthOfList = list.size();
        while(lengthOfList-->0){
            pair<K,V> p = list.removeFirst();
            if(p.k.equals(k)){
                list.addFirst(p);
                return true;
            }
            list.addLast(p);
        }
        return false;
    }

    public V get(K k){
        if(containsKey(k)){
            LinkedList<pair<K,V>>  list = getList(k);
            return list.getFirst().v;
        }else return null;
    }

    public Set<K> getKeySet(){
        Set<K> set = new HashSet<>();
        for(LinkedList<pair<K,V>> list : values){
            for(pair<K,V> p : list)
                set.add(p.k);
        }
        return set;
    }

    public int sizeOfMapList(){
        return this.values.size();
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }


    private int getHash(K k, int lengthOfList){
        if(k == null) return "null".hashCode();
        return k.hashCode() % lengthOfList;
    }

    private LinkedList<pair<K, V>> getList(K k){
        int idx = getHash(k, this.values.size());
        return values.get(idx);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for(LinkedList<pair<K,V>> list : this.values){
            if(list.size() != 0){
                sb.append(list);
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]\n");
        String temp = "multiplier : " + this.multiplier + "\ncollisionCount : " + this.collisionCount + "\n";
        sb.append(temp);
        return sb.toString();
    }
}
