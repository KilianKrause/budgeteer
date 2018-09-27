package org.wickedsource.budgeteer.web.pages.hours;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wickedsource.budgeteer.service.record.WorkRecordFilter;
import org.wickedsource.budgeteer.web.BudgeteerSession;
import org.wickedsource.budgeteer.web.Mount;
import org.wickedsource.budgeteer.web.components.burntable.BurnTableWithFilter;
import org.wickedsource.budgeteer.web.pages.base.basepage.BasePage;
import org.wickedsource.budgeteer.web.pages.base.basepage.breadcrumbs.BreadcrumbsModel;
import org.wickedsource.budgeteer.web.pages.dashboard.DashboardPage;
import org.wickedsource.budgeteer.web.pages.hours.addRate.AddRatePage;

@Mount("hours")
public class HoursPage extends BasePage {

    public HoursPage() {
        long projectId = BudgeteerSession.get().getProjectId();
        BurnTableWithFilter table = new BurnTableWithFilter("burnTable", new WorkRecordFilter(projectId), true, this, null);
        add(table);
        ManuallyAddedRecordsTable table2 = new ManuallyAddedRecordsTable("recordsTable", new ManuallyAddedRecordsModel());
        add(createAddRateLink("addRateLink"));
        add(table2);
    }

    @Override
    protected BreadcrumbsModel getBreadcrumbsModel() {
        return new BreadcrumbsModel(DashboardPage.class, HoursPage.class);
    }

    private Component createAddRateLink(String id) {
        return new Link(id) {
            @Override
            public void onClick() {
                setResponsePage(new AddRatePage(HoursPage.class, new PageParameters()));
            }
        };
    }
}
