package com.bookdatatbase.bdapi.models;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Shelf {
    private String count;
    private String name;

    public Shelf() {}

    public Shelf(String count, String name) {
        this.count = count;
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelf = (Shelf) o;
        return Objects.equals(count, shelf.count) && Objects.equals(name, shelf.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, name);
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "count='" + count + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
