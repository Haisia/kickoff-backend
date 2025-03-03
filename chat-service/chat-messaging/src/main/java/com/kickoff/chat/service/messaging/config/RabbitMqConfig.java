package com.kickoff.chat.service.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  public static final String FIXTURE_LIVE_CHAT_QUEUE = "fixture_live_chat_queue";
  public static final String FIXTURE_LIVE_CHAT_EXCHANGE = "fixture_live_chat_exchange";
  public static final String GENERAL_LIVE_CHAT_QUEUE = "general_live_chat_queue";
  public static final String GENERAL_LIVE_CHAT_EXCHANGE = "general_live_chat_exchange";

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(FIXTURE_LIVE_CHAT_EXCHANGE, true, false);
  }

  @Bean
  public DirectExchange generalLiveChatExchange() {
    return new DirectExchange(GENERAL_LIVE_CHAT_EXCHANGE, true, false);
  }

  @Bean
  public Queue fixtureLiveChatQueue() {
    return new Queue(FIXTURE_LIVE_CHAT_QUEUE, false);
  }

  @Bean
  public Queue generalLiveChatQueue() {
    return new Queue(GENERAL_LIVE_CHAT_QUEUE, false);
  }

  @Bean
  public Binding logEntrybinding() {
    return BindingBuilder.bind(fixtureLiveChatQueue())
      .to(directExchange())
      .with("fixture_live_chat_queue");
  }

  @Bean
  public Binding generalLiveChatBinding() {
    return BindingBuilder.bind(generalLiveChatQueue())
      .to(generalLiveChatExchange())
      .with("general_live_chat_queue");
  }
}