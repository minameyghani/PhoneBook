package ir.maktab.model.dao;

import ir.maktab.model.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByLoginNameAndPassword(String loginName, String password);

    Integer findByLoginStatus(Integer status);

    Optional<User> findByRole(Integer id);

    Optional<User> findByPassword(String p);

    Optional<User> findByLoginName(String l);

    List<User> findByName(String name);

    @Modifying
    @Query("update User u set u.loginStatus =:newStatus where u.id =:id")
    void updateStatus(@Param("newStatus") Integer newStatus,@Param("id") Integer userId);

    static Specification<User> findMaxMatch(String name, String phone, String email) {
        return (Specification<User>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<User> userCriteria = builder.createQuery(User.class);
            List<Predicate> predicates = new ArrayList<>();
            if (!name.isEmpty() || name != null)
                predicates.add(builder.equal(root.get("name"), name));
            if (!phone.isEmpty() || phone != null)
                predicates.add(builder.equal(root.get("phone"), phone));
            if (!email.isEmpty() || email != null)
                predicates.add(builder.equal(root.get("email"), email));
            userCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return userCriteria.getRestriction();

        };
    }

}
