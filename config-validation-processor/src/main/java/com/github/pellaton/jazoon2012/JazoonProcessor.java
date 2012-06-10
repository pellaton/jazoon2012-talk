/*
 * *****************************************************************************************************************
 * Copyright 2012 Michael Pellaton
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 *
 * Contributors:
 *   Michael Pellaton
 *   
 *   
 * This code is derived from the following project published under the same license by the same author:
 * - http://code.google.com/p/spring-configuration-validation-processor
 * 
 * The purpose of this slimmed-down version is to serve as example for a conference presentation.
 * *****************************************************************************************************************
 */
package com.github.pellaton.jazoon2012;

import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic.Kind;

/**
 * Java 6 annotation processor that processes classes annotated with
 * {@code @Configuration}. This processor is meant for source validation during
 * compilation only and does not write any output besides compiler messages.
 * 
 * <p>
 * This example processor checks for two conditions:
 * </p>
 * <ul>
 *   <li>@Configuration annotated classes must not be final</li>
 *   <li>@Bean annotated methods must not be private</li>
 * </ul>
 */
 @SupportedSourceVersion(SourceVersion.RELEASE_7)
 @SupportedAnnotationTypes(value = "org.springframework.context.annotation.Configuration")
public class JazoonProcessor extends AbstractProcessor {

  private Elements elementUtils;
  private Messager messager;
  
  private TypeElement configurationTypeElement;


  /**
   * Mandatory no-argument constructor.
   */
  public JazoonProcessor() {
    super();
  }

  
  /**
   * @see AbstractProcessor#init(ProcessingEnvironment)
   */
  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    this.elementUtils = processingEnv.getElementUtils();
    this.messager = processingEnv.getMessager();
    
    this.configurationTypeElement = this.elementUtils.getTypeElement("org.springframework.context.annotation.Configuration");
  }

  
  /**
   * @see AbstractProcessor#process(Set, RoundEnvironment)
   */
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (!roundEnv.errorRaised() && !roundEnv.processingOver()) {
      processRound(annotations, roundEnv);
    }
    return false;
  }


  /**
   * Process one round.
   * 
   * @see AbstractProcessor#process(Set, RoundEnvironment)
   */
  private void processRound(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for (TypeElement annotation : annotations) {
      for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
        if (element instanceof TypeElement) {
          processElement((TypeElement) element);
        }
      }
    }    
  }


  private void processElement(TypeElement typeElement) {
    for (AnnotationMirror annotation : typeElement.getAnnotationMirrors()) {
      Element annotationTypeElement = annotation.getAnnotationType().asElement();
      if (annotationTypeElement.equals(this.configurationTypeElement)) {
        List<? extends Element> enclosedElements = typeElement.getEnclosedElements();

        processClass(typeElement, ElementFilter.constructorsIn(enclosedElements));

        for (ExecutableElement method : ElementFilter.methodsIn(enclosedElements)) {
          processMethod(typeElement, method);
        }
      }
    }
    
  }


  private void processClass(TypeElement typeElement, List<ExecutableElement> constructorsIn) {
    if (typeElement.getModifiers().contains(Modifier.FINAL)) {
      this.messager.printMessage(Kind.ERROR, "@Configuration classes must not be final.", typeElement);
    }
  }
  
  private void processMethod(TypeElement typeElement, ExecutableElement method) {
    if (method.getModifiers().contains(Modifier.PRIVATE)) {
      this.messager.printMessage(Kind.ERROR, "@bean methods must not be private.", method);
    }
  }

}
