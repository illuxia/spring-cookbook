package uni.fmi.masters.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.masters.bean.CategoryBean;
import uni.fmi.masters.bean.RecipeBean;
import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.repo.CategoryRepo;
import uni.fmi.masters.repo.RecipeRepo;

@RestController
public class RecipeController {
	
	
	RecipeRepo recipeRepo;
	CategoryRepo categoryRepo;
	
	public RecipeController(RecipeRepo recipeRepo, CategoryRepo categoryRepo) {
		this.recipeRepo = recipeRepo;
		this.categoryRepo = categoryRepo;
	}
	
	@PostMapping(path = "/recipe/add")
	public String addRecipe(
			@RequestParam(value = "title")String title,
			@RequestParam(value = "description")String description,
			@RequestParam(value = "category")int category_id,
			HttpSession session
			) {
		
		UserBean user = (UserBean)session.getAttribute("user");
		
		if(user != null) {
			
			CategoryBean category = null;
			Optional<CategoryBean> optionalCategory = categoryRepo.findById(category_id);
			if(optionalCategory.isPresent()) {
				category = optionalCategory.get();
			}
			
			RecipeBean recipeBean = new RecipeBean();
			recipeBean.setTitle(title);
			recipeBean.setDescription(description);
			recipeBean.setCategory(category);
			recipeBean.setUser(user);
			
			recipeBean = recipeRepo.saveAndFlush(recipeBean);
			
			if(recipeBean != null) {
				return String.valueOf(recipeBean.getId());
			}
			
			return "Error: insert unsuccessfull";
			
		}else {
			return "Error: not logged in";
		}		
		
	}
	
	@GetMapping(path = "/recipe/all")
	public List<RecipeBean> getAllRecipes(){
		return recipeRepo.findAll();
	}
	
	@GetMapping(path = "/recipe/user")
	public List<RecipeBean> getUserRecipes(HttpSession session){
		UserBean user = (UserBean)session.getAttribute("user");
		return recipeRepo.findByUser(user);
	}
	
	@PutMapping(path = "/recipe/update")
	public RecipeBean updateUserRecipes(@RequestParam(value = "id")int id,
								  @RequestParam(value = "title")String title,
								  @RequestParam(value = "description")String description,
								  @RequestParam(value = "category_id")int category_id){
		
		Optional<RecipeBean> optionalRecipe = recipeRepo.findById(id);
		Optional<CategoryBean> optionalCategory = categoryRepo.findById(category_id);
		if(optionalRecipe.isPresent() && optionalCategory.isPresent()) {
			RecipeBean recipe = optionalRecipe.get();
			CategoryBean category = optionalCategory.get();
			
			recipe.setTitle(title);
			recipe.setDescription(description);
			recipe.setCategory(category);
			return recipeRepo.save(recipe);
		}else {
			return null;
		}
		
	}
	
	@GetMapping(path = "/recipe/categories")
	public List<CategoryBean> getRecipeCategories(){
		return categoryRepo.findAll();
	}
	
	@DeleteMapping(path = "/recipe/delete")
	public ResponseEntity<Boolean> deleteRecipe(
			@RequestParam(value = "id")int id,
			HttpSession session
			){
		
		UserBean user = (UserBean)session.getAttribute("user");
		
		if(user == null) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}
		
		Optional<RecipeBean> optionalRecipe = recipeRepo.findById(id);
		
		if(optionalRecipe.isPresent()) {
			RecipeBean recipe = optionalRecipe.get();
			
			recipeRepo.delete(recipe);
			
			return new ResponseEntity<>(true, HttpStatus.OK);
			
			
		}else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		
	}
}
