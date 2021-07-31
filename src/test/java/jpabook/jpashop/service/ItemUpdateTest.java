package jpabook.jpashop.service;

import javax.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Transactional
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1L);

        //TX
        book.setName("bookchangename");
    }
}
