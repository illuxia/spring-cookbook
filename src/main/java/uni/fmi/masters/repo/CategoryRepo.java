package uni.fmi.masters.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.bean.CategoryBean;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryBean, Integer>{

}
