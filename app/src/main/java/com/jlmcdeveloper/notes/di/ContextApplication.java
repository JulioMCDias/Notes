package com.jlmcdeveloper.notes.di;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextApplication {
}
