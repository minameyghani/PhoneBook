package ir.maktab.model.dao;

import ir.maktab.model.entity.Contact;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


public interface ContactDao extends JpaRepository<Contact, Integer>, JpaSpecificationExecutor<Contact> {

    List<Contact> findAllByUserId(Integer id);

    List<Contact> findByName(String name);


    static Specification<Contact> findMaxMatch(String name, String phone, String email) {
        return (Specification<Contact>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<Contact> contactCriteria = builder.createQuery(Contact.class);
            List<Predicate> predicates = new ArrayList<>();
            if (!name.isEmpty() || name != null)
                predicates.add(builder.equal(root.get("name"), name));
            if (!phone.isEmpty() || phone != null)
                predicates.add(builder.equal(root.get("phone"), phone));
            if (!email.isEmpty() || email != null)
                predicates.add(builder.equal(root.get("email"), email));
            contactCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return contactCriteria.getRestriction();
        };
    }

}

