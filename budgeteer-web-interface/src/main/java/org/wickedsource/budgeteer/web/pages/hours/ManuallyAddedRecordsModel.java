package org.wickedsource.budgeteer.web.pages.hours;

import org.apache.wicket.model.LoadableDetachableModel;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.wickedsource.budgeteer.service.record.ManuallyAddedRecord;
import org.wickedsource.budgeteer.service.template.Template;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManuallyAddedRecordsModel extends LoadableDetachableModel<List<ManuallyAddedRecord>> {

    @Override
    protected List<ManuallyAddedRecord> load() {
        //TODO: Add a real implementation
        List<ManuallyAddedRecord> records = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            ManuallyAddedRecord record = new ManuallyAddedRecord();
            record.setAmount(Money.of(CurrencyUnit.EUR, 100));
            record.setBudgetName("Scratch the Cat");
            record.setPersonName("Mustermann, Maxine");
            record.setDescription("Travel costs");
            record.setDate(new Date());
            records.add(record);
        }
        return records;
    }
}
