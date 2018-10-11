package ex08_custom_list_sorter;

public class Sorter {
    public static void sort(CustomList listToSort) {
        for (int j = 0; j < listToSort.size()-1; j++) {
        boolean notSorted = true;
        //while (notSorted) {
            notSorted = false;
            for (int i = 0; i < listToSort.size() - 1; i++) {
                if (listToSort.compareElements(i, i + 1) > 0) {
                    listToSort.swap(i, i + 1);
                    notSorted = true;
                }
            }
        }
    }
}
