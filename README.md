## Jazoon 2012 Talk *On Processors, Compilers and @Configurations*  

This repository contains all examples, documents and links of the talk.

### Abstract
The Pluggable Annotation Processing API allows application developers to write customized annotation processors that can be plugged into the Java compiler to process annotated source files during compilation. The automatic generation of additional source code and customized validations of the annotated classes are the two most important purposes of custom annotation processors.

The Java Compiler API, a feature introduced with Java 6, makes it possible to run the java compiler programmatically at runtime. It provides a convenient API to set the paths and options, run the compilation and as a result thereof obtain diagnostic messages and the compiled artifacts without having to execute the Java compiler binary as an external process.

Both, the pluggable annotation processors and the compiler API, are advanced Java SE technologies that are not known by many otherwise experienced developers and most literature lacks a decent coverage of these topics.

The Java based configuration style is the rising star when it comes to Spring application context configurations and its adoption and importance increases with every of the framework's release. Fundamental to this way of context configuration are the two annotations @Bean, used on so called bean definition methods, and @Configuration, used on the classes containing these methods. Due to the nature of the inversion of control pattern and the way Spring works, there are a number of well documented constraints configuration classes and methods must adhere to. Unfortunately, non-conforming classes are only detected at runtime despite the availability of techniques to signal most of the problems much earlier during the compilation.

We will use the missing compile time validation of Spring @Configuration classes as example throughout the talk. We will show how an annotation processor can be employed to validate annotated classes at compile time and how well it can be integrated into development tools like Maven and Eclipse. Then we will discuss how most of the challenges encountered while writing unit tests for the annotation processor can be tackled with the Java compiler API. The example in this talk will be based on a simplified version of the 'Spring Configuration Validation Processor', an open source project by the speaker.


### Links
* The [Jazoon 2012](http://jazoon.com/history/2012/) conference
* The [Spring Configuration Validation Processor](https://github.com/pellaton/spring-configuration-validation-processor) is the open source project this talk is based on
* Package [javax.annotation.processing](http://docs.oracle.com/javase/7/docs/api/javax/annotation/processing/package-summary.html) API documentation (containing the annotation processor)
* Package [javax.tools](http://docs.oracle.com/javase/7/docs/api/javax/tools/package-summary.html) API documentation (containing the Java compiler)
* Spring Annotation class [org.springframework.context.annotation.Configuration](http://static.springsource.org/spring/docs/3.1.x/javadoc-api/org/springframework/context/annotation/Configuration.html) API documentation
