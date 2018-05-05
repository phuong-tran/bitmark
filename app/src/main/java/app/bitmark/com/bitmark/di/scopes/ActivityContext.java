package app.bitmark.com.bitmark.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface  ActivityContext {

}