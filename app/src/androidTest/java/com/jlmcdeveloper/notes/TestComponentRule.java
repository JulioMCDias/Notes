package com.jlmcdeveloper.notes;

import android.content.Context;

import com.jlmcdeveloper.notes.di.component.DaggerTestComponent;
import com.jlmcdeveloper.notes.di.component.TestComponent;
import com.jlmcdeveloper.notes.di.module.ApplicationTestModule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TestComponentRule implements TestRule {

    private Context context;
    private TestComponent testComponent;


    public TestComponentRule(Context context){
        this.context=context;
    }

    public Context getContext() {
        return context;
    }

    public TestComponent getTestComponent() {
        return testComponent;
    }



    private void setupDaggerTestComponentInApplication() {
        AndroidApplication application = ((AndroidApplication) context.getApplicationContext());
        testComponent = DaggerTestComponent.builder()
                .applicationTestModule(new ApplicationTestModule(application))
                .build();
        application.setComponent(testComponent);
    }


    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    setupDaggerTestComponentInApplication();
                    base.evaluate();
                }finally {
                    testComponent =null;
                }
            }
        };
    }
}
