package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.venta.model.MyAuthority;
import lv.venta.model.MyUser;
import lv.venta.model.Product;
import lv.venta.repo.IMyAuthorityRepo;
import lv.venta.repo.IMyUserRepo;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class Seminar5Application {

	public static void main(String[] args) {
		SpringApplication.run(Seminar5Application.class, args);
	}
	
	//@Bean //izsauksies sī funkcija automātiski līdz ko sistema būs palaista
	public CommandLineRunner testDatabaseLayer(IProductRepo productRepo,
			IMyAuthorityRepo authRepo, IMyUserRepo userRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
			
				Product p1 = new Product("Abols", "Sarkans", 0.99f, 5);
				Product p2 =new Product("Zemene", "Salda", 1.23f, 3);
				Product p3 =new Product("Arbuzs", "Roza", 3.99f, 2);
				productRepo.save(p1);
				productRepo.save(p2);
				productRepo.save(p3);
				
				
				MyAuthority a1 = new MyAuthority("ADMIN");
				MyAuthority a2 = new MyAuthority("USER");
				authRepo.save(a1);
				authRepo.save(a2);
				
				PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

				MyUser u1 = new MyUser("admin",encoder.encode("123456"), a1);
				MyUser u2 = new MyUser("karina", encoder.encode("654321"), a2);
				MyUser u3 = new MyUser("janis", encoder.encode("098765"), a1, a2);
				userRepo.save(u1);
				userRepo.save(u2);
				userRepo.save(u3);
				
				a1.addUser(u1);
				a1.addUser(u3);
				authRepo.save(a1);
				
				a2.addUser(u2);
				a2.addUser(u3);
				authRepo.save(a2);
				
				
			}
		};
	}

}
