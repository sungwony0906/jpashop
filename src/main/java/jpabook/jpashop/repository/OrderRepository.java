package jpabook.jpashop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    //동적 쿼리 개발 (미완)
    public List<Order> findAll(OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m";
//                              " where o.orderStatus = :status " +
//                              " and m.name like :name";
        return em.createQuery(jpql, Order.class)
//                       .setParameter("status", orderSearch.getOrderStatus())
//                       .setParameter("name", orderSearch.getMemberName())
                       .setMaxResults(1000) // 최대 1000건
                       .getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class
        ).getResultList();
    }

    public List<Order> findAllWithItem() {
        return em.createQuery("select distinct o from Order o" +
                                      " join fetch o.member m" +
                                      " join fetch o.delivery d" +
                                      " join fetch o.orderItems oi" +
                                      " join fetch oi.item i", Order.class)
                       .getResultList();
    }
}
