package database;

import fileio.FiltersInput;

public final class Filters {
    private Contains contains;
    private Sort sort;

    public Filters(FiltersInput filters) {
        contains = new Contains(filters.getContains());
        sort = new Sort(filters.getSort());
    }

    public Contains getContains() {
        return contains;
    }

    public void setContains(Contains contains) {
        this.contains = contains;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
