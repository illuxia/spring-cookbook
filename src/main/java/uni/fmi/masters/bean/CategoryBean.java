package uni.fmi.masters.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length = 250, nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	//@LazyCollection(LazyCollectionOption.FALSE)
	private List<RecipeBean> recipes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public List<RecipeBean> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeBean> recipes) {
		this.recipes = recipes;
	}*/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
