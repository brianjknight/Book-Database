package com.bookdatatbase.bdapi.enums;

import com.bookdatatbase.bdapi.search.FilterRequest;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Enum class used for returning Predicates which can be used with JPA Specification class in order to query the databases.
 * Each enum is built using a FilterRequest object.
 * See the FilterRequest class in the 'search' package.
 */

@Slf4j
public enum Operator {

    /**
     * Enum for equal.
     */
    EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = root.get(request.getKey());
            return cb.and(cb.equal(key, value), predicate);
        }
    },

    /**
     * Enum for not equal.
     */
    NOT_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Expression<?> key = root.get(request.getKey());
            return cb.and(cb.notEqual(key, value), predicate);
        }
    },

    /**
     * Enum for comparing greater than.
     */
    GREATER_THAN {
      public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
          Object value = request.getFieldType().parse(request.getValue().toString());

          if (request.getFieldType() == FieldType.DATE) {
              LocalDateTime date = (LocalDateTime) value;
              Expression<LocalDateTime> key = root.get(request.getKey());
              return cb.and(cb.and(cb.greaterThan(key, date), predicate));
          }

          if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
              Number num = (Number) value;
              Expression<Number> key = root.get(request.getKey());
              return cb.and(cb.and(cb.gt(key, num), predicate));
          }

          log.info("Can not use between for {} field type.", request.getFieldType());
          return predicate;
      }
    },

    /**
     * Enum for comparing greater than or equal to.
     */
    GREATER_THAN_OR_EQUAL_TO {
      public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
          Object value = request.getFieldType().parse(request.getValue().toString());

          if (request.getFieldType() == FieldType.DATE) {
              LocalDateTime date = (LocalDateTime) value;
              Expression<LocalDateTime> key = root.get(request.getKey());
              return cb.and(cb.and(cb.greaterThanOrEqualTo(key, date), predicate));
          }

          if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
              Number num = (Number) value;
              Expression<Number> key = root.get(request.getKey());
              return cb.and(cb.and(cb.ge(key, num), predicate));
          }

          log.info("Can not use between for {} field type.", request.getFieldType());
          return predicate;
      }
    },

    /**
     * Enum for comparing less than.
     */
    LESS_THAN {
      public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
          Object value = request.getFieldType().parse(request.getValue().toString());

          if (request.getFieldType() == FieldType.DATE) {
              LocalDateTime date = (LocalDateTime) value;
              Expression<LocalDateTime> key = root.get(request.getKey());
              return cb.and(cb.and(cb.lessThan(key, date), predicate));
          }

          if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
              Number num = (Number) value;
              Expression<Number> key = root.get(request.getKey());
              return cb.and(cb.and(cb.lt(key, num), predicate));
          }

          log.info("Can not use between for {} field type.", request.getFieldType());
          return predicate;
      }
    },

    /**
     * Enum for comparing less than or equal to.
     */
    LESS_THAN_OR_EQUAL_TO {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());

            if (request.getFieldType() == FieldType.DATE) {
                LocalDateTime date = (LocalDateTime) value;
                Expression<LocalDateTime> key = root.get(request.getKey());
                return cb.and(cb.and(cb.lessThanOrEqualTo(key, date), predicate));
            }

            if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
                Number num = (Number) value;
                Expression<Number> key = root.get(request.getKey());
                return cb.and(cb.and(cb.le(key, num), predicate));
            }

            log.info("Can not use between for {} field type.", request.getFieldType());
            return predicate;
        }
    },

    /**
     * Enum operator to compare values that match 1 to many String patterns and is NOT case-sensitive.
     */
    LIKE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            if(request.getFieldType() != FieldType.STRING) {
                log.info("Can not use LIKE_TWO operator for {} field type. Only STRING field type can be used.", request.getFieldType());
                return predicate;
            }

            Expression<String> key = root.get(request.getKey());

            Predicate[] predicatesArray = request.getValues().stream()
                                                        .map(object -> cb.like(cb.upper(key), "%" + object.toString().toUpperCase() + "%"))
                                                        .toArray(Predicate[]::new);

            return cb.and(cb.and(predicatesArray), predicate);
        }
    },

    /**
     * Enum operator to compare values that match any of the values in a given list.
     */
    IN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            List<Object> values = request.getValues();
            CriteriaBuilder.In<Object> inClause = cb.in(root.get(request.getKey()));
            for (Object value : values) {
                inClause.value(request.getFieldType().parse(value.toString()));
            }
            return cb.and(inClause, predicate);
        }
    },

    /**
     * Enum operator to match values in an inclusive range.
     */
    BETWEEN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getValue().toString());
            Object valueTo = request.getFieldType().parse(request.getValueTo().toString());
            if (request.getFieldType() == FieldType.DATE) {
                LocalDateTime startDate = (LocalDateTime) value;
                LocalDateTime endDate = (LocalDateTime) valueTo;
                Expression<LocalDateTime> key = root.get(request.getKey());
                return cb.and(cb.and(cb.greaterThanOrEqualTo(key, startDate), cb.lessThanOrEqualTo(key, endDate)), predicate);
            }

            if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
                Number start = (Number) value;
                Number end = (Number) valueTo;
                Expression<Number> key = root.get(request.getKey());
                return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate);
            }

            log.info("Can not use between for {} field type.", request.getFieldType());
            return predicate;
        }
    };

    private static <T> Predicate buildAndPredicate(Root<T> root, CriteriaBuilder cb, List<Predicate> predicates) {
        return cb.and(predicates.toArray(new Predicate[0]));
    }

    public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate);

}