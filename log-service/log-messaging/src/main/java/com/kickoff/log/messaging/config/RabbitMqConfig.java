package com.kickoff.log.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  public static final String LOG_ENTRY_PERSIST_QUEUE = "log_entry_persist_queue";
  public static final String LOG_ENTRY_EXCHANGE = "log_entry_exchange";

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(LOG_ENTRY_EXCHANGE, true, false);
  }

  @Bean
  public Queue logEntryQueue() {
    return new Queue(LOG_ENTRY_PERSIST_QUEUE, false);
  }

  @Bean
  public Binding logEntrybinding() {
    return BindingBuilder.bind(logEntryQueue())
      .to(directExchange())
      .with("log_entry_persist_queue")
      ;
  }
}
