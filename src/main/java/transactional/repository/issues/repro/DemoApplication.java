package transactional.repository.issues.repro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import transactional.repository.issues.repro.domain.FailingPersonRepository;
import transactional.repository.issues.repro.factory.CrudRepositoryFactoryBean;

@Controller
@SpringBootApplication
@EnableJpaRepositories(
        basePackageClasses = FailingPersonRepository.class,
        repositoryFactoryBeanClass = CrudRepositoryFactoryBean.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@ResponseBody
	@RequestMapping("/")
	public String start() {
		return "Run the test.";
	}
}
