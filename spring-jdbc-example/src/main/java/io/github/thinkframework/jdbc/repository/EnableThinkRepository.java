package io.github.thinkframework.jdbc.repository;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @author lixiaobin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ThinkRepositoryImportBeanDefinitionRegistrar.class)
public @interface EnableThinkRepository {
}
