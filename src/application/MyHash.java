package application;

import java.io.Serializable;

public class MyHash<T> implements Serializable { //MyHash

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private MyList<T>[] hashTable; //MyList, based on FunkyList from notes
    //E[] hashTab;
    private int count;


    @SuppressWarnings("unchecked")
    public MyHash(int size) {
        count = 0;
        hashTable = (MyList<T>[]) new MyList[size];
        //hashTab=(E[]) new Object [size];

        for (int i = 0; i < size; i++) hashTable[i] = new MyList<>();
    }

    public int hashFunction(T key) {
        return (key.hashCode() & 0x7fffffff) % hashTable.length;
    }

    public int addItem(T item) {
        int loc = hashFunction(item);
        hashTable[loc].addElement(item);
        ++count;
        return loc;
    }

    public int removeItem(T item) {
        int loc = hashFunction(item);
        if (hashTable[loc].contains(item)) {
            hashTable[loc].remove(item);
            --count;
            return loc;
        } else return -1;
    }


    public MyList<T> getDataList() {
        MyList<T> books = new MyList<>();
        for (int i = 0; i < hashTable.length; i++) {
            MyList<T>.MyNode<T> tmp = hashTable[i].head;
            while (null != tmp) {
                T book = tmp.getContents();
                books.addElement(book);
                tmp = tmp.next;
            }
        }

        return books;
    }

    public int size() {
        return count;
    }

    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i].head = null;
        }
    }

}
