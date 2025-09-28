package org.example.datastructure;

public class Stack {
    int maxSize;
    int top;
    String[] stackArray;


    public Stack(int size) {
        maxSize = size;
        stackArray = new String[maxSize];
        top = -1;
    }


    public void push(String value) {
        if (top == maxSize - 1) {
            System.out.println("Stack is full can not push " + value);
        } else {
            stackArray[++top] = value;
        }
    }


    public String pop() {
        if (top == -1) {
            System.out.println("Stack is empty, can not pop the element");
            return null;
        } else {
            String poppedValue = stackArray[top--];
            return poppedValue;
        }
    }


    public String peek() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return stackArray[top];
        }
    }

    public void display() {
        if (top == -1) {
            System.out.println("Stack is empty");
        }
        else {
            int tampTop = top;
            for (int i = top; i >= 0; i--) {
                System.out.println(stackArray[i]);
            }
        }
    }
}

