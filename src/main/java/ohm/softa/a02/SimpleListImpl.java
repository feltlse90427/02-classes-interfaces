package ohm.softa.a02;

import java.util.Iterator;

public class SimpleListImpl implements SimpleList, Iterable<Object> {

    private Element head;
    private int size;

    public SimpleListImpl() {
        head = null;
    }

    @Override
    public void add(Object item) {
        if (head == null) {
            head = new Element(item);
        }
        else {
            Element current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Element(item));
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList result = new SimpleListImpl();
        for (Object o : this) {
            if (filter.include(o)) {
                result.add(o);
            }
        }
        return result;
    }

    @Override
    public Iterator<Object> iterator() {
        return new SimpleIterator();
    }

    private static class Element {
        private Object item;
        private Element next;

        Element(Object item) {
            this.item = item;
            this.next = null;
        }
        public Object getItem() { return item; }
        public Element getNext() { return next; }

        public void setNext(Element next) { this.next = next; }
    }

    private class SimpleIterator implements Iterator<Object> {
        private Element current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object tmp = current.getItem();
            current = current.getNext();
            return tmp;
        }
    }
	// TODO: Implement the required methods.

}
