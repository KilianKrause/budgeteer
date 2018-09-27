package org.wickedsource.budgeteer.web.pages.hours.addRate;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wickedsource.budgeteer.web.pages.base.dialogpage.DialogPageWithBacklink;

public class AddRatePage extends DialogPageWithBacklink {

    public AddRatePage(Class<? extends WebPage> backlinkPage, PageParameters backlinkParameters) {
        super(backlinkPage, backlinkParameters);
        add(new AddRateForm("addRateForm"));
        add(createBacklink("cancelButton1"));
        add(createBacklink("cancelButton2"));
    }
}
