package uni.fmi.masters.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "recipe")
public class RecipeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title", length = 250, nullable = false)
	private String title;
	
	@Column(name = "description", length = 1000, nullable = false)
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	//@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "user_id")
	private UserBean user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	//@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "category_id")
	private CategoryBean category;

	public CategoryBean getCategory() {
		return category;
	}

	public void setCategory(CategoryBean category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
