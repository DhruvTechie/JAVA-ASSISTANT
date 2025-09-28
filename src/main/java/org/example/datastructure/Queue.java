package org.example.datastructure;

public class Queue {
    int[] queue;
    int front;
    int rear;
    int maxSize;
    int count;

    public Queue(int size) {
        queue = new int[size];
        maxSize = size;
        front = 0;
        rear = -1;
        count = 0;
    }
    public void enqueue(int item) {
        if (count == maxSize) {
            //System.out.println("Queue is full");
            dequeue();
            enqueue(item);
            return;
        }
        rear = (rear + 1) % maxSize;
        queue[rear] = item;
        count++;
    }
    public int dequeue() {
        if (count == 0) {
            System.out.println("Queue is empty");
            return -1;
        }
        int item = queue[front];
        front = (front + 1) % maxSize;
        count--;
        return item;
    }
    public int peek() {
        if (count == 0) {
            System.out.println("Queue is empty");
            return -1;
        }
        return queue[front];
    }
}

