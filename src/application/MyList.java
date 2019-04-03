package application;

import java.io.Serializable;

public class MyList<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public MyNode<T> head = null;

    public void addElement(T b) {
        MyNode<T> mn = new MyNode<>();
        mn.setContents(b);
        mn.next = head;
        head = mn;
    }

    public void clear() {
        head = null;
    }

    public boolean contains(T b) {
        MyNode<T> temp = head;
        while (temp != null && !temp.contents.equals(b))
            temp = temp.next;

        if (temp != null) {
            return true;
        }
        return false;
    }

    public void remove(T b) {
        MyNode<T> temp = head, prev = null;
        while (temp != null && !temp.contents.equals(b)) {
            prev = temp;
            temp = temp.next;
        }
        if (temp != null) {
            if (prev == null) head = temp.next;
            else prev.next = temp.next;
        }

    }

    class MyNode<T> {
        public MyNode<T> next = null;
        private T contents;

        public T getContents() {
            return contents;
        }

        public void setContents(T c) {
            contents = c;
        }
    }

}