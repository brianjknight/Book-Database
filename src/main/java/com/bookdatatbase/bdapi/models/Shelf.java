package com.bookdatatbase.bdapi.models;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * POJO for a Shelf object which is an embedded type within the Book class.
 */

@Embeddable
public class Shelf {
    private Integer count;
    private String name;

    public Shelf() {}

    public Shelf(Integer count, String name) {
        this.count = count;
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
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
