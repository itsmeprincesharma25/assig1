import java.util.Random;

public class QuickSort {
    private static final Random RNG = new Random();

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length <= 1) return;
        Utils.shuffle(a); // simple randomization
        quickSort(a, 0, a.length - 1, m);
    }

    private static void quickSort(int[] a, int lo, int hi, Metrics m) {
        while (lo < hi) {
            m.enter();
            try {
                int p = partition(a, lo, hi, m);
                // recurse on smaller side
                if (p - lo < hi - p) {
                    quickSort(a, lo, p - 1, m);
                    lo = p + 1;
                } else {
                    quickSort(a, p + 1, hi, m);
                    hi = p - 1;
                }
            } finally { m.leave(); }
        }
    }

    private static int partition(int[] a, int lo, int hi, Metrics m) {
        int pivotIndex = lo + RNG.nextInt(hi - lo + 1);
        int pivot = a[pivotIndex];
        Utils.swap(a, pivotIndex, hi);
        int store = lo;
        for (int i = lo; i < hi; i++) {
            m.countComparison(1);
            if (a[i] < pivot) {
                Utils.swap(a, i, store);
                store++;
            }
        }
        Utils.swap(a, store, hi);
        return store;
    }
}
