package ir.maktab.model.dao;

import ir.maktab.model.dto.ContactDto;
import ir.maktab.model.entity.Contact;
import ir.maktab.model.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


public interface ContactDao extends JpaRepository<Contact, Integer>, JpaSpecificationExecutor<Contact> {

    List<Contact> findAllByUserId(Integer id);

    List<Contact> findByName(String name);

    static Specification<Contact> findMaxMatch(String name, String phone, String email, Integer id) {
        return (Specification<Contact>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<ContactDto> contactCriteria = builder.createQuery(ContactDto.class);
            List<Predicate> predicates = new ArrayList<>();
            Join<Contact, User> userJoin = root.join("user");
            predicates.add(builder.in(userJoin.get("id")).value(id));
            if (!name.isEmpty() && name != null)
                predicates.add(builder.equal(root.get("name"), name));
            if (!phone.isEmpty() && phone != null)
                predicates.add(builder.equal(root.get("phone"), phone));
            if (!email.isEmpty() && email != null)
                predicates.add(builder.equal(root.get("email"), email));
            contactCriteria.multiselect(
                    root.get("id"),
                    root.get("name"),
                    root.get("phone"),
                    root.get("email"),
                    root.get("address").get("city"),
                    root.get("address").get("province"),
                    root.get("address").get("street"),
                    root.get("remark"))
                    .where(predicates.toArray(new Predicate[0]));
            return contactCriteria.getRestriction();
        };
    }


}

