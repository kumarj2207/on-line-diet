package com.example.configuration;

import java.sql.SQLException;

import javax.annotation.PreDestroy;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan(basePackages = { "com.packtpub.springsecurity.dataaccess", "com.packtpub.springsecurity.service" })
//@EnableTransactionManagement
public class DataSourceConfig {
	private EmbeddedDatabase database = null;

//	@Bean
//	public DataSource dataSource() {
//		final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//		database = builder.setType(EmbeddedDatabaseType.H2).setName("dataSource").ignoreFailedDrops(true)
//				.continueOnError(false).addScript("classpath:database/h2/calendar-schema.sql")
//				.addScript("classpath:database/h2/calendar-data.sql").build();
//		return database;
//	}

	/*
	 * @Bean
	 * 
	 * @Autowired public PlatformTransactionManager transactionManager(final
	 * DataSource dataSource) { return new DataSourceTransactionManager(dataSource);
	 * }
	 * 
	 * @Bean
	 * 
	 * @Autowired public JdbcTemplate jdbcOperations(final DataSource dataSource) {
	 * return new JdbcTemplate(dataSource); }
	 */

	/**
	 * Used for JSR-303 Validation TODO: look into JSR-349 Validation
	 */
	/*
	 * @Bean public LocalValidatorFactoryBean validatorFactoryBean() { return new
	 * LocalValidatorFactoryBean(); }
	 */

	/*
	 * @Bean public MethodValidationPostProcessor methodValidationPostProcessor() {
	 * final MethodValidationPostProcessor methodValidationPostProcessor = new
	 * MethodValidationPostProcessor(); //
	 * methodValidationPostProcessor.setValidator(validatorFactoryBean()); return
	 * methodValidationPostProcessor; }
	 */

	/**
	 * DataSource PostConstruct call-back
	 * 
	 * @throws SQLException
	 */
	/*
	 * @PostConstruct public void dataSourceInitialization() {
	 * 
	 * // h2 admin via hsql Database Manager // DatabaseManagerSwing.main(new
	 * String[] { "--url", "jdbc:h2:mem:dataSource", "--user", "sa", "--password",
	 * "" }); }
	 */

	/**
	 * DataSource PreDestroy call-back
	 * 
	 * @throws SQLException
	 */
	@PreDestroy()
	public void dataSourceDestroy() throws SQLException {
		if (database != null) {
			database.shutdown();
		}
	}

} // The End...
