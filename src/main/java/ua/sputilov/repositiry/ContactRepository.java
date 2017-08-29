package ua.sputilov.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import ua.sputilov.model.Contact;

import javax.persistence.QueryHint;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

//    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "" + 1))
    @Query("select con from Contact con")
    Stream<Contact> findAllAndStream();

    @Query("select count(con) from Contact con")
    Integer countContacts();

}