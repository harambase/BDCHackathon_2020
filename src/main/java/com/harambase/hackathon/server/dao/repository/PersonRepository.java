package com.harambase.hackathon.server.dao.repository;

import com.harambase.hackathon.server.pojo.base.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    List<Person> findByInfo(String info);

    @Query("select count(person.userId) as count from Person person where person.type like concat('%',?1,'%') and person.status=?2")
    int countByTypeAndStatus(String type, String status);

    int countByGenderAndStatus(String gender, String status);

    int countByUserId(String userId);

    Person findByUserIdAndPassword(String userId, String password);

    @Query("select person from Person person where (person.userId = ?1 or person.qq = ?1 or person.username = ?1) and person.status = '1'")
    List<Person> findByQqOrUserIdOrUsername(String keyword);

    @Query(value = "select get_name(?1)", nativeQuery = true)
    String getName(String userId);

    Person findByOpenId(String openId);
}
