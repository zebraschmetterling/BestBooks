package repository;

import entity.read;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface readRepository extends JpaRepository<read, Integer> {

    @Query("SELECT r from read r where r.endDate is not NULL")
    public List<read> findCompletedBooks();

    @Query("SELECT r from read r where r.endDate is NULL")
    public List<read> findUnfinishedBooks();

}
