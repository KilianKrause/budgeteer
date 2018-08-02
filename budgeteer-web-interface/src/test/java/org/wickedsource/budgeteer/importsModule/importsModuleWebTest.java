package org.wickedsource.budgeteer.importsModule;

import de.olivergierke.moduliths.model.test.ModuleTest;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.wickedsource.budgeteer.imports.api.ImportFile;
import org.wickedsource.budgeteer.importsModule.internal.web.fileimport.ImportFileUnzipper;
import org.wickedsource.budgeteer.importsModule.internal.web.fileimport.ImportFilesPage;
import org.wickedsource.budgeteer.web.AbstractWebTestTemplate;
import org.wickedsource.budgeteer.web.pages.dashboard.DashboardPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@ModuleTest(mode = ModuleTest.BootstrapMode.ALL_DEPENDENCIES)
public class importsModuleWebTest extends AbstractWebTestTemplate {

    @Test
    void renderOverviewPage() {
        WicketTester tester = getTester();
        tester.startPage(ImportsOverviewPage.class);
        tester.assertRenderedPage(ImportsOverviewPage.class);
    }

    @Test
    void renderImportFilesPage() {
        WicketTester tester = getTester();
        ImportFilesPage page = new ImportFilesPage(DashboardPage.class, new PageParameters());
        tester.startPage(page);
        tester.assertRenderedPage(ImportFilesPage.class);
    }

    @Test
    void testReadImportFiles() throws Exception {
        ImportFileUnzipper unzipper = new ImportFileUnzipper(getClass().getResourceAsStream("testfiles.zip"));
        List<ImportFile> files = unzipper.readImportFiles();
        Assert.assertEquals(2, files.size());

        Assert.assertEquals("test1.txt", files.get(0).getFilename());
        Assert.assertNotNull(files.get(0).getInputStream());
        Assert.assertEquals("test1", readLine(files.get(0).getInputStream()));

        Assert.assertEquals("test2.txt", files.get(1).getFilename());
        Assert.assertNotNull(files.get(1).getInputStream());
        Assert.assertEquals("test2", readLine(files.get(1).getInputStream()));
    }

    private String readLine(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.readLine();
    }

    @Override
    protected void setupTest() {

    }
}
