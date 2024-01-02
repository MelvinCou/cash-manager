package com.cashmanager.server.shop_database.config;


import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class FlywayConfiguration {


    @PostConstruct
    public void migrateFlyway(){
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/shop_db","shop_user","shop_password")
                .load();

        flyway.migrate();

    }
}
