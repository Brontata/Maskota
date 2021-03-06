package project.petme.springboot.tg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan
public class Application {

	/*TODO
	* 	- Edição de   PET
	*	- Deleção de  PET
	*	- Curtidas no PET
	*	- Arrumar os campos
	*	- Arrumar as imagens do PET
	* 	- Edição de usuário também que não tá 100%
	* */

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
