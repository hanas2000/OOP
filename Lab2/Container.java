package com.company;

import java.io.*;
import java.util.Iterator;

public class Container implements Iterable<String>, Serializable{

    private String[] data;
    private int currentLength = 0;

    Container(int index)
    {
        if(index>0)
        {
            data=new String[index];
        }
        else
        {
            System.out.println("Error.Please enter the data again.");
        }
    }
    public String[] toArray()
    {
        if(currentLength==0)
        {
            System.out.println("Error.Array is empty");
            return null;
        }
        String[] newArray = new String[currentLength];
        System.arraycopy(data,0, newArray, 0, currentLength );
        return newArray;
    }

    public void task(char a,String c)
    {
        StringBuilder string2=new StringBuilder();
        StringBuilder string3=new StringBuilder();
        for(int i=0;i<currentLength;i++)
        {
            string2.append(data[i]+" ");
        }

        for (int i = 0; i < string2.length()-1; i++) {

            if (string2.charAt(i) == a && string2.charAt(i + 1) == ' ') {
                string3.append(string2.charAt(i)).append(c);

            } else {
                string3.append(string2.charAt(i));
            }
        }
        System.out.println(string3);
        System.out.println("Done");
    }

    boolean contains(String string) {
        for (int i = 0; i < currentLength; i++)
            if (string.equals(data[i]))
                return true;
        return false;
    }

    public String toString()
    {
        StringBuilder string1=new StringBuilder();
        for(int i=0;i<currentLength;i++)
        {
            string1.append(data[i]+" ");
        }
        return new String(string1);
    }

    public void add(String newOne)
    {
        data[currentLength]=newOne;
        currentLength++;
    }

    public String get(int index)
    {
        if(index>currentLength) return null;
        else return data[index];
    }

    boolean containsAll(Container strings) {
        boolean result = false;
        for (String i : strings) {
            for (int j = 0; j < currentLength; j++) {
                if (i.equals(data[j])) {
                    result = true;
                    break;
                }
            }
            if (!result)
                return false;
            result = false;
        }
        return true;
    }

    public int indexOf(String index)
    {
            for(int i=0;i<currentLength;i++)
            {
                if(data[i].equals(index)) return i;
            }
            System.out.println("Error.String not found.");
            return -1;
    }

    public int size() {
        return currentLength;
    }

    public void clear()
    {
        for(int i=0;i<currentLength;i++)
        {
            data[i]=null;
        }
        currentLength=0;
    }



    boolean remove(String string)
    {
        for(int i=0;i<currentLength;i++)
        {
            if(data[i].equals(string))
            {
                currentLength--;
                if (currentLength - i >= 0)
                    System.arraycopy(data, i + 1, data, i, currentLength - i);
                data[currentLength] = null;
                return true;
            }
        }
        return false;
    }


    boolean compare(int index1,int index2)
    {
        int length1=data[index1].length();
        int length2=data[index2].length();

            for(int i=0;i < length1 && i < length2;i++)
            {
                int ch1 = (int)data[index1].charAt(i);
                int ch2 = (int)data[index2].charAt(i);
                if(ch1>ch2)return true;
                else if(ch1<ch2) return false;
            }
            if(length1<length2)
            {
                return false;
            }
        return true;

    }

    void sort() {
        int x = 0;
        for (int i = currentLength - 1; i > x; i--)
        {
            for (int j = currentLength - 1; j > x; j--)    {
                if (!compare(j,j-1)){
                    String tmp = data[j];
                    data[j] = data[j-1];
                    data[j-1] = tmp;
                }
            }
            x++;
        }

    }

    boolean serialize(String filename) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return false;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            System.out.println("smth goes wrong with IO");
            return false;
        }
    }

    boolean deserialize(String filename) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return false;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            Container temp = (Container) ois.readObject();
            if (temp.currentLength > this.data.length) {
                System.out.println("too large container");
                return false;
            }
            this.data = temp.data;
            this.currentLength = temp.currentLength;
            return true;
        } catch (IOException e) {
            System.out.println("smth goes wrong with IO");
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("wrong file format");
            return false;
        }
    }

    int maxSize() {
        return data.length;
    }

    @Override
    public Iterator<String> iterator() {
        return new MyIterator(data, currentLength);
    }
}
