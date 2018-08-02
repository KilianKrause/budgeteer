package org.wickedsource.budgeteer.importsModule.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.wickedsource.budgeteer.importsModule.internal.service.ImporterRegistry;

class ImporterRegistryTest {

    @Test
    void testRegistry() {
        ImporterRegistry registry = new ImporterRegistry();
        Assertions.assertEquals(2, registry.getWorkingRecordsImporters().size());
    }
}
