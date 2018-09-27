package org.wickedsource.budgeteer.service.record;

import lombok.Data;
import org.joda.money.Money;

import java.util.Date;

@Data
public class ManuallyAddedRecord {
    private long id;
    private String budgetName;
    private String personName;
    private String description;
    private Date date;
    private Money amount;
}
