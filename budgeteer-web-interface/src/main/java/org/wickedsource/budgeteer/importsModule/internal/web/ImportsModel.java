package org.wickedsource.budgeteer.importsModule.internal.web;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wickedsource.budgeteer.importsModule.internal.service.Import;
import org.wickedsource.budgeteer.importsModule.ImportService;

import java.util.List;

public class ImportsModel extends LoadableDetachableModel<List<Import>> {

    @SpringBean
    private ImportService service;

    private long projectId;

    public ImportsModel(long projectId) {
        this.projectId = projectId;
        Injector.get().inject(this);
    }

    @Override
    protected List<Import> load() {
        return service.loadImports(projectId);
    }
}
