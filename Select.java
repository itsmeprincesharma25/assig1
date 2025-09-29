// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.util.Arrays;

class DeterministicSelectSimple {
   DeterministicSelectSimple() {
   }

   public static int select(int[] var0, int var1) {
      return select(var0, 0, var0.length - 1, var1);
   }

   private static int select(int[] var0, int var1, int var2, int var3) {
      if (var1 == var2) {
         return var0[var1];
      } else {
         int var4 = medianOfMedians(var0, var1, var2);
         int var5 = partition(var0, var1, var2, var4);
         if (var3 == var5) {
            return var0[var5];
         } else {
            return var3 < var5 ? select(var0, var1, var5 - 1, var3) : select(var0, var5 + 1, var2, var3);
         }
      }
   }

   private static int partition(int[] var0, int var1, int var2, int var3) {
      int var4 = var1;
      int var5 = var2;

      while(var4 <= var5) {
         while(var0[var4] < var3) {
            ++var4;
         }

         while(var0[var5] > var3) {
            --var5;
         }

         if (var4 <= var5) {
            swap(var0, var4, var5);
            ++var4;
            --var5;
         }
      }

      return var4 - 1;
   }

   private static int medianOfMedians(int[] var0, int var1, int var2) {
      int var3 = var2 - var1 + 1;
      if (var3 <= 5) {
         Arrays.sort(var0, var1, var2 + 1);
         return var0[var1 + var3 / 2];
      } else {
         int var4 = 0;

         for(int var5 = var1; var5 <= var2; var5 += 5) {
            int var6 = Math.min(var5 + 4, var2);
            Arrays.sort(var0, var5, var6 + 1);
            int var7 = var0[var5 + (var6 - var5) / 2];
            var0[var1 + var4] = var7;
            ++var4;
         }

         return medianOfMedians(var0, var1, var1 + var4 - 1);
      }
   }

   private static void swap(int[] var0, int var1, int var2) {
      int var3 = var0[var1];
      var0[var1] = var0[var2];
      var0[var2] = var3;
   }
}
