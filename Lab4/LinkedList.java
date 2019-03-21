package com.company;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>, Serializable {

    private class Node implements Serializable{
        private T date;
        private Node next;

        private void setDate(T date)
        {
            this.date=date;
        }

        private void setNext(Node next)
        {
            this.next=next;
        }

        private T getDate()
        {
            return date;
        }

        private Node getNext()
        {
            return next;
        }

    }

    private Node root;

    public LinkedList() {
        root = new Node();
    }

    public void addNode(T date)
    {
        Node last=root;
        while(last.getNext()!=null)
        {
            last=last.getNext();
        }
        last.setNext(new Node());
        last.getNext().setDate(date);
    }

    public T get(int index)
    {
        Node result=root;
        for(int i=0;i<=index;i++)
        {
            if(result.getNext()==null)
            {
                return null;
            }else
            {
                result=result.getNext();
            }
        }
        return result.getDate();
    }

    public void clear() {
        root = new Node();
    }

    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        Node result=root;
        while(result!=null)
        {
            sb.append(result.getDate()).append(" ");
            result=result.getNext();
        }
        return new String(sb);
    }


    public int size()
    {
        Node result=root;
        int count=0;
        while(result.getNext()!=null)
        {
            count++;
            result=result.getNext();
        }
        return count;
    }

    public void remove(int index)
    {
        Node result=root;
        int i=0;
        Node prev=null;
        while(result!=null)
        {
            if(index==i)
            {
                if(prev==null)
                {
                    root=result.getNext();
                }
                else if(result.getNext()==null)
                {
                    prev.setNext(null);
                }else
                {
                    prev.setNext(result.getNext());
                }
                break;
            }
            prev=result;
            result=result.getNext();
            i++;
        }

    }



    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            int current;

            {
                current=0;
            }


            @Override
            public boolean hasNext() {
                return (current < size());
            }

            @Override
            public T next() {
                return get(current++);
            }
        };
    }
}
