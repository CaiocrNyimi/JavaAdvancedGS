package br.com.fiap.skill4green.messaging;

import br.com.fiap.skill4green.dto.response.ExecucaoResponse;
import br.com.fiap.skill4green.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TarefaConsumer {

  @RabbitListener(queues = RabbitMQConfig.QUEUE)
  public void receberExecucao(ExecucaoResponse execucao) {
    log.info("Execução recebida da fila: {}", execucao);
  }
}