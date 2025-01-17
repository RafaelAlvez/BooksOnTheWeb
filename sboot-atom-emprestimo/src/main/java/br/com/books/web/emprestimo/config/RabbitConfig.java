package br.com.books.web.emprestimo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String DEVOLUCAO_QUEUE = "queueDevolucao";
    public static final String DEVOLUCAO_EXCHANGE = "exchangeDevolucao";
    public static final String DEVOLUCAO_ROUTING_KEY = "devolucao";

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Queue queue() {
        return new Queue(DEVOLUCAO_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(DEVOLUCAO_EXCHANGE);
    }

    @Bean
    public org.springframework.amqp.core.Binding binding(Queue queue, TopicExchange exchange) {
        return org.springframework.amqp.core.BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(DEVOLUCAO_ROUTING_KEY);
    }
}

