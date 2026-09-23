package br.com.ecommerce.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import br.com.ecommerce.anotacoes.AuditoriaLogistica;

/**
 * Auditoria dinâmica de metadados via Reflection (java.lang.Class,
 * Method e Field) para garantir conformidade de padrões corporativos.
 */
public class AuditorMetadadosReflection {

    public static void inspecionarClasse(Class<?> clazz) {
        System.out.println("  Nome completo (getName): " + clazz.getName());
        System.out.println("  Superclasse direta: " + clazz.getSuperclass().getName());

        if (clazz.isAnnotationPresent(AuditoriaLogistica.class)) {
            AuditoriaLogistica anotacao = clazz.getAnnotation(AuditoriaLogistica.class);
            System.out.println("  @AuditoriaLogistica PRESENTE -> nivelCriticidade: "
                    + anotacao.nivelCriticidade() + " | registrarDataHora: " + anotacao.registrarDataHora());
        } else {
            System.out.println("  @AuditoriaLogistica ausente na classe.");
        }

        System.out.println("  Atributos declarados (getDeclaredFields):");
        for (Field campo : clazz.getDeclaredFields()) {
            System.out.println("    " + campo.getName() + " : " + campo.getType().getName());
        }

        System.out.println("  Métodos públicos (getMethods) pertencentes APENAS à própria classe:");
        int totalProprios = 0;
        for (Method metodo : clazz.getMethods()) {
            if (metodo.getDeclaringClass().equals(clazz)) {
                totalProprios++;
                System.out.println("    " + metodo.getName() + "(" + metodo.getParameterCount() + " param(s))");
            }
        }
        System.out.println("  Total de métodos próprios: " + totalProprios);
    }
}