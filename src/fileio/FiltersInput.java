package fileio;

public class FiltersInput {
    private SortInput sort;
    private ContainsInput contains;

    public SortInput getSort() {
        return sort;
    }

    public void setSort(SortInput sort) {
        this.sort = sort;
    }

    public ContainsInput getContains() {
        return contains;
    }

    public void setContains(ContainsInput contains) {
        this.contains = contains;
    }

    @Override
    public String toString() {
        return "FiltersInput{" +
                "sort=" + sort +
                ", contains=" + contains +
                '}';
    }
}
