package eMarket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// THIS CLASS NEEDS TO BE CONFIGURED FOR OBVIOUS REASONS
 

@Configuration
public class DbConfig {


	private String USERNAME = "user";
	private String PASSWORD = "pswd"; // in ~/.my.cnf
	
	
	private String HOST = "127.0.0.1";
	private int PORT = 3307;
			
    @Bean
    public DriverManagerDataSource dataSource() {		
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://" + HOST + ":" + PORT + "/" + USERNAME );
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        return ds;
    }
}


