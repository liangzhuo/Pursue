新的java配置支持@Configuration注解类和@Bean注解方法。

@Bean注解被用于声明一个方法实例，配置和初始化一个对象让Spring IoC容器进行管理。@Bean注解扮演了和Spring's XML配置中的
<bean/>元素一样的角色。你能使用@Bean注解方法可以用于任何Spring @Component，无论如何最常使用的还是@Configuration

使用@Configuration注解一个类声明它的主要目的是作为bean定义的开始。
而且，@Configuration类允许内部bean依赖在相同的类中简单的调用其他@Bean方法

全@Configuration 与 轻量 @Beans 模式
1.当@Bean方法在没有使用@Configuration注解的类中声明时，它们被认为在用轻模式进行处理。例如，bean方法在一个@Component
或甚至在一个解释的旧类中声明将认为是轻量模式

2.不像全@Configuraion,轻@Bean方法不能轻易的声明内部bean依赖，通常在轻模式中操作时不应该一个@Bean方法调用另一个@Bean方法

3.只要在@Configuraion类中使用了@Bean方法就很容易确定是使用full模式，这将阻止相同的@Bean方法意外地被调用多次并帮助减少
在轻模式中敏感的很难追踪的bug

AnnotationConfigApplicationContext类接受@Configuration和@Component注解类输入
AnnotationConfigApplicationContext使用register(Class<?> ...)注入

@Configuration
@Bean：方法级别注解，与<bean />方法一致，在@Component或@Configuration中使用
	bean name与方法名一致
1.声明bean
	方法参数为空
	
2.依赖bean
	方法参数依赖
	
生命周期回调
	init
	destory
		注解@Bean(destoryMethod="")覆盖原方法close 或 shutdown

	
