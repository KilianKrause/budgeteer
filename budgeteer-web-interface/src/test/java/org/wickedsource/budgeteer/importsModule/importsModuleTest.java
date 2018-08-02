package org.wickedsource.budgeteer.importsModule;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import de.olivergierke.moduliths.model.test.ModuleTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.wickedsource.budgeteer.IntegrationTestConfiguration;
import org.wickedsource.budgeteer.importsModule.internal.persistence.ImportEntity;

import javax.transaction.Transactional;
import java.util.List;


@ModuleTest(verifyAutomatically = false)
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {IntegrationTestConfiguration.class})
@Transactional
@TestExecutionListeners({DbUnitTestExecutionListener.class, DirtiesContextTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class importsModuleTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    private ImportRepository repository;

    @Test
    @DatabaseSetup("findByProjectId.xml")
    @DatabaseTearDown(value = "findByProjectId.xml", type = DatabaseOperation.DELETE_ALL)
    void testFindByProjectId() {
        List<ImportEntity> imports = repository.findByProjectId(1L);
        Assertions.assertEquals(2, imports.size());
    }
}
