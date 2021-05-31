package com.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.assignment.diet.dao.BatchDao;
import com.assignment.diet.dao.ChallengerDao;
import com.assignment.diet.dao.DietUserDao;
import com.assignment.diet.entity.Batch;
import com.assignment.diet.entity.Challenger;
import com.assignment.diet.entity.DietUser;
import com.assignment.diet.vo.Role;

@SpringBootApplication
// @ImportResource("classpath:app-config.xml")
public class DemoApplication /*extends SpringBootServletInitializer*/ implements CommandLineRunner {

	@Autowired
	private DietUserDao dietUserDao;

	@Autowired
	private BatchDao batchDao;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private ChallengerDao challengerDao;

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(DemoApplication.class);
//	}
	


	public static void main(String[] args) {
//		ConfigurableApplicationContext context = 
				SpringApplication.run(DemoApplication.class, args);
		// DemoApplication application = context.getBean(DemoApplication.class);
//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		
//		args = applicationContext.getBeanDefinitionNames();
		

	}

	@Override
	public void run(String... args) throws Exception {
//		List<Employee> l = new ArrayList<>();
//		Employee e = new Employee();
//		e.setName("Ram");
//		e.setLocation("Bangalore");
//		e.setEmail("ram@mail.com");
//		e.setMobile("9867512345");
//		l.add(e);
//		e = new Employee();
//		e.setName("Raj");
//		e.setLocation("Chennai");
//		e.setEmail("raj@mail.com");
//		e.setMobile("7867534521");
//		l.add(e);
//		e = new Employee();
//		e.setName("Vinay");
//		e.setLocation("Pune");
//		e.setEmail("vinay@mail.com");
//		e.setMobile("9975287450");
//		l.add(e);
//		employeeDao.saveAll(l);
//
//		List<Credit> lc = new ArrayList<>();
//		lc.add(new Credit("AXSSP1122H", 3.10));
//		lc.add(new Credit("APPSA3355P", 8.50));
//		lc.add(new Credit("APPXA2244X", 9.50));
//		lc.add(new Credit("AXVPS7766V", 1.50));
//		lc.add(new Credit("ASXPS2121S", 5.10));
//		
//		creditDao.saveAll(lc);
		
		DietUser user = new DietUser();
		user.setFullName("Roger");
		user.setUserName("admin@mydiet.com");
		user.setPassword(bcryptEncoder.encode("adminpassword123"));
		user.setEmail("roger@gmail.com");
		user.setRole(Role.ADMIN);
		dietUserDao.save(user);
		
		user = new DietUser();
		user.setFullName("Rajeev Jain");
		user.setUserName("ra53315247@mydiet.com");
		user.setPassword(bcryptEncoder.encode("password123"));
		user.setEmail("ra53315247@mydiet.com");
		user.setRole(Role.CHALLENGER);
		dietUserDao.save(user);
				
		Batch batch = new Batch();
		batch.setName("BelowBMI25");
		batchDao.save(batch);
		
		Batch childBatch = new Batch();
		childBatch.setName("veg-only");
		childBatch.setParentBatchId(batch.getBatchId());
		batchDao.save(childBatch);
		
		Challenger c = new Challenger();
		c.setReferralId(user.getReferralId());
		c.setBatch(childBatch);
		
		challengerDao.save(c);
		
		batch = new Batch();
		batch.setName("AboveBMI25");
		batchDao.save(batch);
		
		childBatch = new Batch();
		childBatch.setName("nonveg-only");
		childBatch.setParentBatchId(batch.getBatchId());
		batchDao.save(childBatch);
	}

}
