package br.com.fiap.skill4green.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitMQConfig {

  public static final String QUEUE = "tarefas.concluidas";
  public static final String EXCHANGE = "skill4green.exchange";
  public static final String ROUTING_KEY = "tarefa.concluida";

  @Bean
  public Queue queue() {
    return new Queue(QUEUE, true);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(EXCHANGE);
  }

  @Bean
  public Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
  }
}