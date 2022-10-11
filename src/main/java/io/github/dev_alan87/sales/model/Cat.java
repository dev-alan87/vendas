package io.github.dev_alan87.sales.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Autowired
@Qualifier("cat")
public @interface Cat {

}
