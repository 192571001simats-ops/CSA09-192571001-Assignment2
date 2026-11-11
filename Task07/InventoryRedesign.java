import java.util.*;

public class InventoryRedesign {
  record Medicine(String code, int stock) {}

  static class Inventory {
    private final LinkedHashMap<String, Medicine> records = new LinkedHashMap<>();
    private List<Medicine> codeCache = List.of(), arrivalCache = List.of();
    private boolean dirty = true;

    void put(Medicine m) {
      records.put(m.code(), m);
      dirty = true;
    }

    void remove(String code) {
      records.remove(code);
      dirty = true;
    }

    List<Medicine> original(boolean code) {
      var rows = new ArrayList<>(records.values());
      if (code) rows.sort(Comparator.comparing(Medicine::code));
      return rows;
    }

    List<Medicine> cached(boolean code) {
      if (dirty) {
        arrivalCache = List.copyOf(records.values());
        var sorted = new ArrayList<>(arrivalCache);
        sorted.sort(Comparator.comparing(Medicine::code));
        codeCache = List.copyOf(sorted);
        dirty = false;
      }
      return code ? codeCache : arrivalCache;
    }
  }

  public static void main(String[] args) {
    for (int n : new int[] {100, 1000, 10000}) {
      Inventory inventory = new Inventory();
      for (int i = n - 1; i >= 0; i--) inventory.put(new Medicine(String.format("M%06d", i), i));
      Bench.require(
          inventory.original(true).equals(inventory.cached(true))
              && inventory.original(false).equals(inventory.cached(false)),
          "both cached orders match original size " + n);
      Bench.measure(
          "original_100_reports",
          n,
          () -> {
            long sum = 0;
            for (int r = 0; r < 100; r++)
              for (var m : inventory.original(r % 2 == 0)) sum += m.stock();
            Bench.sink = sum;
          });
      Bench.measure(
          "cached_100_reports",
          n,
          () -> {
            long sum = 0;
            for (int r = 0; r < 100; r++)
              for (var m : inventory.cached(r % 2 == 0)) sum += m.stock();
            Bench.sink = sum;
          });
      inventory.put(new Medicine("M000001", 999));
      Bench.require(
          inventory.original(true).equals(inventory.cached(true)),
          "stock update invalidates cached objects");
      inventory.remove("M000000");
      Bench.require(
          inventory.original(false).equals(inventory.cached(false)),
          "deletion invalidates both report orders");
    }
    System.out.println(
        "Prediction: remove repeated sorting and list allocation for unchanged inventory; traversal"
            + " and text output remain O(n)");
  }
}
