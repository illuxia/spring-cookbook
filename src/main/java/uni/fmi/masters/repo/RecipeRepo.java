package uni.fmi.masters.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.bean.RecipeBean;
import uni.fmi.masters.bean.UserBean;

@Repository
public interface RecipeRepo extends JpaRepository<RecipeBean, Integer>{
	
	List<RecipeBean> findByUser(UserBean user);
	
}
