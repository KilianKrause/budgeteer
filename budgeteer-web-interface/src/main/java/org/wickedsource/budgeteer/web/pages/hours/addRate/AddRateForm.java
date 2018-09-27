package org.wickedsource.budgeteer.web.pages.hours.addRate;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wickedsource.budgeteer.service.budget.BudgetBaseData;
import org.wickedsource.budgeteer.service.budget.BudgetService;
import org.wickedsource.budgeteer.service.person.PersonBaseData;
import org.wickedsource.budgeteer.service.person.PersonService;
import org.wickedsource.budgeteer.web.BudgeteerSession;
import org.wickedsource.budgeteer.web.components.budget.BudgetBaseDataChoiceRenderer;
import org.wickedsource.budgeteer.web.components.daterange.DateInputField;
import org.wickedsource.budgeteer.web.components.daterange.DateRangeInputField;
import org.wickedsource.budgeteer.web.components.money.MoneyTextField;
import org.wickedsource.budgeteer.web.components.multiselect.MultiselectBehavior;
import org.wickedsource.budgeteer.web.components.person.PersonBaseDataChoiceRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

public class AddRateForm extends Form {

    @SpringBean
    private PersonService personService;

    @SpringBean
    private BudgetService budgetService;

    AddRateForm(String id) {
        super(id);
        add(createPersonList("personSelect"));
        add(createBudgetList("budgetList"));
        add(new TextField<String>("description"));
        add(new MoneyTextField("amount", new Model<>()));
        add(new DateRangeInputField("dateRangeInput", new Model<>(), DateRangeInputField.DROP_LOCATION.DOWN));
    }

    private Component createPersonList(String id) {
        List<PersonBaseData> chosenPersons = new ArrayList<>();
        List<PersonBaseData> possiblePersons = personService.loadPeopleBaseData(BudgeteerSession.get().getProjectId());
        ListMultipleChoice<PersonBaseData> selectedPersons =
                new ListMultipleChoice<>(id, model(from(chosenPersons)),
                        possiblePersons, new PersonBaseDataChoiceRenderer());

        selectedPersons.setRequired(false);
        HashMap<String, String> options = MultiselectBehavior.getRecommendedOptions();
        options.put("buttonWidth", "'250px'");
        options.remove("buttonClass");
        selectedPersons.add(new MultiselectBehavior(options));
        return selectedPersons;
    }

    private Component createBudgetList(String id) {
        List<BudgetBaseData> possibleBudgets = budgetService.loadBudgetBaseDataForProject(BudgeteerSession.get().getProjectId());
        List<BudgetBaseData> chosenBudgets = new ArrayList<>();
        ListMultipleChoice<BudgetBaseData> selectedBudgets =
                new ListMultipleChoice<>(id, model(from(chosenBudgets)), possibleBudgets, new BudgetBaseDataChoiceRenderer());

        HashMap<String, String> options = MultiselectBehavior.getRecommendedOptions();
        options.put("buttonWidth", "'250px'");
        options.remove("buttonClass");
        selectedBudgets.add(new MultiselectBehavior(options));
        selectedBudgets.setRequired(false);
        return selectedBudgets;
    }

    @Override
    protected void onSubmit() {
        super.onSubmit();
        this.success("Rate(s) successfully added!");
    }

}
