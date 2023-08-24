package org.example;

import java.util.NoSuchElementException;

public class QueueResizingArray<Item> {

    Item[] array;
    int size;
    int first;
    int last;

    public QueueResizingArray() {
        array = (Item[]) new Object[1];
        size = 0;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public void enqueue(Item item)
    {

        if(size == array.length)
            resize(size * 2); // double the capacity
        array[last++] = item;
        size++;
        if(last == array.length)
            last = 0;  // to use the first array cells that can be empty after multiple dequeues
    }
    public Item dequeue()
    {
        if(size <= 0 )
            throw new NoSuchElementException();
        if(size == array.length/4)
            resize(array.length/2);
        Item returnedValue = array[first];
        array[first++] = null;
        size--;
        return returnedValue;
    }

    private void resize(int capacity)
    {
        Item[] arrayTemp = (Item[]) new Object[capacity];
        for (int i = 0 ; i < size ; i++)
        {
            arrayTemp[i] = array[(first+i)%array.length];
        }
        array = arrayTemp;
        first=0;
        last=size;
    }

    public static void main(String[] args) {
        QueueResizingArray queue = new QueueResizingArray<Integer>();

        queue.enqueue(5);
        queue.enqueue(9);
        queue.enqueue(12);
        System.out.println(queue.dequeue());
        queue.enqueue(15);
        queue.enqueue(18); // must be added to array[0]

        queue.enqueue(20); // must trigger a resize to 8

    }

}
