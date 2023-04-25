package com.bookdatatbase.bdapi.search;

import com.bookdatatbase.bdapi.entities.BookGenre;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class BookBookGenreSpecification<Book> implements Specification<Book> {

    @Serial
    private static final long serialVersionUID = 2513496916258688337L;

    private final transient SearchRequest request;

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

        Join<BookGenre, Book> bookBookGenreJoin = root.join("bookGenre");

        List<Object> genreList = request.getFilters().get(0).getValues();
        for (Object o : genreList) {
            predicate = cb.and(cb.like(bookBookGenreJoin.get("genres"),"%" + (String) o + "%"), predicate);
        }

        List<Order> orders = new ArrayList<>();
        for (SortRequest sort : this.request.getSorts()) {
            orders.add(sort.getDirection().build(root, cb, sort));
        }
        query.orderBy(orders);

        return predicate;
    }

    public static Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(Objects.requireNonNullElse(page, 0), Objects.requireNonNullElse(size, 100));
    }

}
