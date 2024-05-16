package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class MyUser {
	
	@Column(name = "Idu")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idu;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z.]+")
	@Size(min = 3, max = 20)
	@Column(name = "Username")
	private String username;
	
	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "Password")
	private String password;
	
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private Collection<MyAuthority> authorities = new ArrayList<MyAuthority>();
	
	
	public MyUser (String username, String password, MyAuthority ... auths) {
		setUsername(username);
		setPassword(password);
		for(MyAuthority tempA: auths)
			addAuthority(tempA);
	}
	
	
	public void addAuthority(MyAuthority authority) {
		if(!authorities.contains(authority))
			authorities.add(authority);
	}
	
	public void removeAuthority(MyAuthority authority) {
		if(authorities.contains(authority))
			authorities.remove(authority);
	}
	
	
	
	
	
	
}