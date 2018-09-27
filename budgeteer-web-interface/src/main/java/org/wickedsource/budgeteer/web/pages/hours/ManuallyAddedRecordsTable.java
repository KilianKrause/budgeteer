package org.wickedsource.budgeteer.web.pages.hours;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wickedsource.budgeteer.service.record.ManuallyAddedRecord;
import org.wickedsource.budgeteer.web.ClassAwareWrappingModel;
import org.wickedsource.budgeteer.web.components.dataTable.DataTableBehavior;
import org.wickedsource.budgeteer.web.components.money.MoneyLabel;

import java.util.List;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

public class ManuallyAddedRecordsTable extends Panel {

    private ListView<ManuallyAddedRecord> rows;
    private WebMarkupContainer table;

    public ManuallyAddedRecordsTable(String id, ManuallyAddedRecordsModel model) {
        super(id);
        table = new WebMarkupContainer("table");
        table.add(new DataTableBehavior(DataTableBehavior.getRecommendedOptions()));
        rows = createList("recordsList", model, table);
        table.add(rows);
        add(table);
        this.setOutputMarkupId(true);
    }

    private ListView<ManuallyAddedRecord> createList(String id, final IModel<List<ManuallyAddedRecord>> model, final WebMarkupContainer table) {
        return new ListView<ManuallyAddedRecord>(id, model) {
            @Override
            protected void populateItem(final ListItem<ManuallyAddedRecord> item) {
                item.setOutputMarkupId(true);
                item.add(new Label("budget", model(from(item.getModel()).getBudgetName())));
                item.add(new Label("name", model(from(item.getModel()).getPersonName())));
                item.add(new Label("description", model(from(item.getModel()).getDescription())));
                item.add(new MoneyLabel("amount", model(from(item.getModel()).getAmount())));
                item.add(new Label("date", model(from(item.getModel()).getDate())));
                item.add(new Link("editPage") {
                    @Override
                    public void onClick() {

                    }
                });}

            @Override
            protected ListItem<ManuallyAddedRecord> newItem(int index, IModel<ManuallyAddedRecord> itemModel) {
                // wrap model to work with LazyModel
                return super.newItem(index, new ClassAwareWrappingModel<>(itemModel, ManuallyAddedRecord.class));
            }
        };
    }
}
