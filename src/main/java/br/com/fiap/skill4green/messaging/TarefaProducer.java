package br.com.fiap.skill4green.messaging;

import br.com.fiap.skill4green.config.RabbitMQConfig;
import br.com.fiap.skill4green.dto.response.ExecucaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TarefaProducer {

  private final RabbitTemplate rabbitTemplate;

  public void enviarExecucao(ExecucaoResponse execucao) {
    rabbitTemplate.convertAndSend(
      RabbitMQConfig.EXCHANGE,
      RabbitMQConfig.ROUTING_KEY,
      execucao
    );
  }
}