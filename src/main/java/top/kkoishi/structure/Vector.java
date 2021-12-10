package top.kkoishi.structure;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

public class Vector<V> extends AbstractList<V> implements Serializable {
    @Serial
    private static final long serialVersionUID = -1145141919810L;

    protected Object[] datas;
    private int size;

    public Vector () {
        size = 0;
    }

    private void expandSize () {
        size++;
        if (datas == null) {
            datas = new Object[size];
        } else {
            Object[] temp = datas;
            datas = new Object[size];
            System.arraycopy(temp, 0, datas, 0, size - 1);
        }
    }

    private void offer (V value) {
        expandSize();
        datas[size - 1] = value;
    }

    @Override
    public boolean add (V value) {
        offer(value);
        return true;
    }

    @Override
    public Object[] toArray () {
        return datas;
    }

    @SuppressWarnings("unchecked")
    public V[] getArray (Class<?> type) {
        V[] array = (V[]) Array.newInstance(type, size);
        for (int i = 0; i < size; i++) {
            array[i] = (V) datas[i];
        }
        return array;
    }

    @SuppressWarnings("all")
    public java.util.Vector toUtilVector () {
        java.util.Vector vector = new java.util.Vector<>();
        for (Object val : datas) {
            vector.add(val);
        }
        return vector;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get (int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (V) datas[index];
    }

    @Override
    public V set (int index, V value) {
        V temp = get(index);
        datas[index] = value;
        return temp;
    }

    @Override
    public boolean addAll (Collection<? extends V> collection) {
        for (V value : collection) {
            add(value);
        }
        return true;
    }

    @Override
    public int size () {
        return 0;
    }

    @Override
    public String toString () {
        return Arrays.toString(datas);
    }
}

class VectorTest {
    public static void main (String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(114514);
        vector.add(0);
        System.out.println(vector);
    }
}
