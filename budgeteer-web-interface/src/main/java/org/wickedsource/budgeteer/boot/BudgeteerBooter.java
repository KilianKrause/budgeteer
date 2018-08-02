package org.wickedsource.budgeteer.boot;

import de.olivergierke.moduliths.Modulith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("org.wickedsource.budgeteer")
@EntityScan("org.wickedsource.budgeteer")
@ComponentScan("org.wickedsource.budgeteer")
@Modulith
public class BudgeteerBooter {
    public static void main(String[] args) {
        SpringApplication.run(BudgeteerBooter.class, args);
    }
}
