package database;

import fileio.FiltersInput;

public final class Filters {
    private Contains contains;
    private Sort sort;

    public Filters(final FiltersInput filters) {
        if (filters.getContains() != null) {
            contains = new Contains(filters.getContains());
        }
        if (filters.getSort() != null) {
            sort = new Sort(filters.getSort());
        }
    }

    public Contains getContains() {
        return contains;
    }

    public void setContains(final Contains contains) {
        this.contains = contains;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(final Sort sort) {
        this.sort = sort;
    }
}
