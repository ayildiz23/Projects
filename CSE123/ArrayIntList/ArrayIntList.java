// Class ArrayIntList can be used to store a list of integers.
public class ArrayIntList {
    private int[] elementData;  // list of integers
    private int size;       // current number of elements in the list

    // Identifies the index of a given value within the list
    public int indexOf(int value) {
    	for (int i = 0; i < size; i++) {
            if (elementData[i] == value) {
                return i;
            } 
        }
        return -1;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////

    public static final int DEFAULT_CAPACITY = 10;

    // constructs an empty list of default capacity
    public ArrayIntList() {
        this(DEFAULT_CAPACITY);
    }

    // constructs an empty list with the given capacity
    private ArrayIntList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be at least 0: " + capacity);
        }
        elementData = new int[capacity];
    }

    // for creating test cases more easily
    public ArrayIntList(int... elements) {
        this(Math.max(DEFAULT_CAPACITY, elements.length * 2));
        for (int n : elements) {
            add(n);
        }
    }

    // for creating test cases more easily
    public ArrayIntList(int element, boolean notCapacity) {
        this();
        add(element);
    }

    // post: appends the given value to the end of the list
    public void add(int value) {
        elementData[size] = value;
        size++;
    }

    // pre: 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: removes value at the given index, shifting subsequent values left
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("invalid index");
        }
        
    	for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
    }

    // post: returns true if o is an ArrayIntList with the same size and elements as this one
    public boolean equals(Object o) {
        if (!(o instanceof ArrayIntList)) {
            return false;
        }

        ArrayIntList other = (ArrayIntList) o;
        if (this.size != other.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (elementData[i] != other.elementData[i]) {
                return false;
            }
        }

        return true;
    }

    // post: returns the current number of elements in the list
    public int size() {
        return size;
    }

    // post: returns a comma-separated, bracketed version of the list
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i < size; i++) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }
}
