package br.com.ecommerce.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação customizada para auditoria de operações logísticas.
 * Visível em tempo de execução (RUNTIME) para inspeção via Reflection.
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditoriaLogistica {

    String nivelCriticidade() default "PADRAO";

    boolean registrarDataHora() default true;
}