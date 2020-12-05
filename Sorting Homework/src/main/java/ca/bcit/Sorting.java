package ca.bcit;
import edu.princeton.cs.algs4.Stopwatch;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Homework - Sorting
 * Sort the list of doubles in the fastest possible way.
 * The only method you can change is the sort() method.
 * You can add additional methods if needed, without changing the load() and test() methods.
 */
public class Sorting {

    protected List list = new ArrayList<Integer>();

    /**
     * Loading the text files with double numbers
     */
    protected void load(){
        try (Stream<String> stream = Files.lines(Paths.get("numbers.txt"))) {
            stream.forEach(x -> list.add(Integer.parseInt(x)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing of your solution, using 100 shuffled examples
     * @return execution time
     */
    protected double test(){
        Stopwatch watch = new Stopwatch();
        for (int i=0; i<100; i++){
            Collections.shuffle(list, new Random(100));
            sort(list);
        }
        return watch.elapsedTime();
    }





        // Partitions arr[0..n-1] around [lowVal..highVal]
        public void threeWayPartition( int lowVal, int highVal)
        {

            int  n =(int) list.size();

            // Initialize ext available positions for
            // smaller (than range) and greater lements
            int start = 0, end = n-1;

            // Traverse elements from left
            for(int i = 0; i <= end;)
            {

                // If current element is smaller than
                // range, put it on next available smaller
                // position.

                if((int)list.get(i)< lowVal)
                {

                    int temp = (int)list.get(start);
                    list.set(start,(int)list.get(i));
                    list.set(i,temp);
                    start++;
                    i++;

                }

                // If current element is greater than
                // range, put it on next available greater
                // position.
                else if((int)list.get(i) > highVal)
                {

                    int temp = (int)list.get(end);
                    list.set(end,(int)list.get(i));
                    list.set(i,temp);
                    end--;

                }

                else
                    i++;
            }
        }



        public void quickSort(int start,int end){
        int j;
        if(start<end){
            j = partition(start, end);
            quickSort(start, j);
            quickSort(j+1, end);
        }
    }



    int partition(int start,int end){
        int i = start;
        int j = end;

        Random r = new Random();

       // int pivotIndex = start;
       // int pivotIndex = j-1 ;
       int pivotIndex = nextIntInRange(i,j,r);
       // int pivotIndex = (i+j) /2;

        int pivot = (int) list.get(pivotIndex);



        while(true){
            while((int) list.get(j)>pivot && j>i){
                j--;
            }

            while((int) list.get(i)<pivot && i<j){
                i++;
            }

            if(i<j){
                //swap
                int temp;
                temp = (int) list.get(i);
                list.set(i,list.get(j));
                list.set(j,temp);
                j--;
                i++;

            }
            else{

                return j;
            }
        }

    }

    // Below method is to just find random integer from given range
    static int nextIntInRange(int min, int max, Random rng) {
        if (min > max) {
            throw new IllegalArgumentException("Cannot draw random int from invalid range [" + min + ", " + max + "].");
        }
        int diff = max - min;
        if (diff >= 0 && diff != Integer.MAX_VALUE) {
            return (min + rng.nextInt(diff + 1));
        }
        int i;
        do {
            i = rng.nextInt();
        } while (i < min || i > max);
        return i;
    }




        // Merges two subarrays of arr[].
        // First subarray is arr[l..m]
            // Second subarray is arr[m+1..r]
        void merge( int l, int m, int r)
        {
            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            /* Create temp arrays */
            int L[] = new int[n1];
            int R[] = new int[n2];

            /*Copy data to temp arrays*/
            for (int i = 0; i < n1; ++i)
                L[i] = (int) list.get(l+i);
            for (int j = 0; j < n2; ++j)
                R[j] = (int) list.get(m+1+j);

            /* Merge the temp arrays */

            // Initial indexes of first and second subarrays
            int i = 0, j = 0;

            // Initial index of merged subarry array
            int k = l;
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    list.set(k,L[i]);
                    i++;
                }
                else {
                    list.set(k,R[j]);
                    j++;
                }
                k++;
            }

            /* Copy remaining elements of L[] if any */
            while (i < n1) {
                list.set(k,L[i]);
                i++;
                k++;
            }

            /* Copy remaining elements of R[] if any */
            while (j < n2) {
                list.set(k,R[j]);
                j++;
                k++;
            }
        }

        // Main function that sorts arr[l..r] using
        // mergeSort()
        void mergeSort( int l, int r)
        {
            if (l < r) {
                // Find the middle point
                int m = (l + r) / 2;

                    // Sort first and second halves
                mergeSort( l, m);
                mergeSort(m + 1, r);

                // Merge the sorted halves
                merge( l, m, r);
            }
        }

 // /////////////////////////////////////////////////////
 static int MIN_MERGE = 32;

    public int minRunLength(int n)
    {
        assert n >= 0;

        // Becomes 1 if any 1 bits are shifted off
        int r = 0;
        while (n >= MIN_MERGE)
        {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    // This function sorts array from left index to
    // to right index which is of size atmost RUN
    public  void insertionSort( int left, int right)
    {
        for (int i = left+1; i <= right; i++)
        {
            int temp = (int) list.get(i);
            int j = i - 1;
            while (j >= left && (int) list.get(j) > temp)
            {
                list.set(j+1,j);
                 j--;
            }
            list.set(j+1,j);
        }
    }

    // Merge function merges the sorted runs
    public void mergett( int l, int m, int r)
    {
        // Original array is broken in two parts
        // left and right array
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (int x = 0; x < len1; x++)
        {
            left[x] = (int) list.get(l+x);
        }
        for (int x = 0; x < len2; x++)
        {
            right[x] =  (int) list.get(m+1+x);
        }

        int i = 0;
        int j = 0;
        int k = l;

        // After comparing, we merge those two array
        // in larger sub array
        while (i < len1 && j < len2)
        {
            if (left[i] <= right[j])
            {
                list.set(k,left[i]);
                i++;
            }
            else {
               list.set(k,right[j]);
                j++;
            }
            k++;
        }

        // Copy remaining elements
        // of left, if any
        while (i < len1)
        {
            list.set(k,left[i]);
            k++;
            i++;
        }

        // Copy remaining element
        // of right, if any
        while (j < len2)
        {
            list.set(k,right[j]);
            k++;
            j++;
        }
    }

    // Iterative Timsort function to sort the
    // array[0...n-1] (similar to merge sort)
    public void timSort(int n)
    {
        int minRun = minRunLength(MIN_MERGE);

        // Sort individual subarrays of size RUN
        for (int i = 0; i < n; i += minRun)
        {
            insertionSort( i,
                    Math.min((i + 31), (n - 1)));
        }

        // Start merging from size
        // RUN (or 32). It will
        // merge to form size 64,
        // then 128, 256 and so on
        // ....
        for (int size = minRun; size < n; size = 2 * size)
        {

            // Pick starting point
            // of left sub array. We
            // are going to merge
            // arr[left..left+size-1]
            // and arr[left+size, left+2*size-1]
            // After every merge, we
            // increase left by 2*size
            for (int left = 0; left < n;
                 left += 2 * size)
            {

                // Find ending point of left sub array
                // mid+1 is starting point of right sub
                // array
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1),
                        (n - 1));

                // Merge sub array arr[left.....mid] &
                // arr[mid+1....right]
                mergett(left, mid, right);
            }
        }
    }


    public void quickSortwithInsertion( int start, int end) {
        if (start < end) {

            if ((end - start) < 2) {
                insertionSort(start, end);
            } else {
                int part = partition(start, end);
                quickSortwithInsertion(start, part);
                quickSortwithInsertion(part + 1, end);
            }
        }
    }

    private static void selectionSort(List list) {
        for (int top = list.size()-1; top > 0; top-- ) {
            int maxloc = 0;
            for (int i = 1; i <= top; i++) {
                if ((int) list.get(i) > (int)list.get(maxloc))
                    maxloc = i;
            }
            int temp = (int) list.get(top);
            list.set(top,maxloc);
            list.set(maxloc,temp);
        }
    }


    public void heap()
    {
        int n = list.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) //from largest non-leaves up to index 0
            heapify( n, i);

        // One by one extract the final element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = (int) list.get(0);
            list.set(0,i);
            list.set(i,temp);

            // call max heapify on the reduced heap
            heapify( i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify( int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (l < n && (int) list.get(l) > (int) list.get(largest))
            largest = l;

        // If right child is larger than largest so far
        if (r < n && (int) list.get(r) > (int) list.get(largest))
            largest = r;

        // If largest is not root change it with the largest changes before
        if (largest != i)
        {
            int swap = (int) list.get(i); //get root
            list.set(i,largest);
            list.set(largest,swap);

            // Recursively heapify the affected sub-tree
            heapify( n, largest);
        }
    }


    private void sort(List list){
            threeWayPartition(0,list.size()-1);
         //   heap();
         // selectionSort(list);
        //quickSortwithInsertion(0,list.size()-1);
       // insertionSort(0,10);
       // timSort(list.size());
       // mergeSort(0,list.size()-1);
       //quickSort(0,list.size()-1);
        //Collections.sort(list);
    }
}