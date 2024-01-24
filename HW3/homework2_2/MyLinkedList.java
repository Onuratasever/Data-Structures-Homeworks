package homework2_2;

import java.util.AbstractList;

import java.util.Iterator;
import java.util.List;

public class MyLinkedList<E> extends AbstractList<E> implements List<E> {

    private Node<E> head;
    private int size;

    public MyLinkedList()
    {
        head = new Node<>(null);
        size = 0;
    }

    @Override
    public E get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head.next;
        int i = 0;
        while (current != null)
        {
            if (!current.deleted)
            {
                if (i == index)
                {
                    return current.data;
                }
                i++;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean add(E element)
    {
        Node<E> current = head;
        while (current.next != null)
        {
            current = current.next;
        }
        current.next = new Node<>(element);
        size++;
        return true;
    }

    @Override
    public void add(int index, E element)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head;
        int i = 0;
        while (current != null)
        {
            if (i == index)
            {
                Node<E> newNode = new Node<>(element, current.next);
                current.next = newNode;
                size++;
                return;
            }
            current = current.next;
            i++;
        }
    }

    @Override
    public E remove(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head;
        int i = 0;
        while (current != null)
        {
            if (i == index)
            {
                if (!current.next.deleted)
                {
                    E data = current.next.data;
                    current.next.deleted = true;
                    size--;
                    return data;
                }
            }
            current = current.next;
            i++;
        }
        return null;
    }

    /*
    @Override
    public Iterator<E> iterator()
    {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node<E> current = head.next;

        @Override
        public boolean hasNext()
        {
            while (current != null && current.deleted)
            {
                current = current.next;
            }
            return current != null;
        }

        @Override
        public E next() {
            E data = current.data;
            current = current.next;
            return data;
        }
    }*/

    private static class Node<E> {
        E data;
        Node<E> next;
        boolean deleted;

        Node(E data)
        {
            this(data, null);
        }

        Node(E data, Node<E> next)
        {
            this.data = data;
            this.next = next;
            this.deleted = false;
        }
    }
}
