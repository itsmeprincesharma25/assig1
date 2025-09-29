import java.util.Arrays;

public class MergeSort {
    private static final int INSERTION_CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length <= 1) return;
        int[] buf = new int[a.length];
        m.countAllocation(a.length);
        mergeSort(a, buf, 0, a.length - 1, m);
    }

    private static void mergeSort(int[] a, int[] buf, int lo, int hi, Metrics m) {
        m.enter();
        try {
            if (hi - lo + 1 <= INSERTION_CUTOFF) {
                insertion(a, lo, hi, m);
                return;
            }
            int mid = (lo + hi) >>> 1;
            mergeSort(a, buf, lo, mid, m);
            mergeSort(a, buf, mid + 1, hi, m);
            merge(a, buf, lo, mid, hi, m);
        } finally { m.leave(); }
    }

    private static void insertion(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.countComparison(1);
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else break;
            }
            a[j + 1] = key;
        }
    }

    private static void merge(int[] a, int[] buf, int lo, int mid, int hi, Metrics m) {
        // copy to buffer
        System.arraycopy(a, lo, buf, lo, hi - lo + 1);
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            m.countComparison(1);
            if (buf[i] <= buf[j]) a[k++] = buf[i++];
            else a[k++] = buf[j++];
        }
        while (i <= mid) a[k++] = buf[i++];
        while (j <= hi) a[k++] = buf[j++];
    }
}
