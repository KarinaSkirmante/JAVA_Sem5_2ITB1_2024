package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@Column(name = "Idp")
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
	
	
	public MyUser (String username, String password) {
		setUsername(username);
	}
	
}
