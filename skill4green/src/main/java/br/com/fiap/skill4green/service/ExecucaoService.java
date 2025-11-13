package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.dto.request.ExecucaoRequest;
import br.com.fiap.skill4green.dto.response.ExecucaoResponse;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.mapper.ExecucaoMapper;
import br.com.fiap.skill4green.messaging.TarefaProducer;
import br.com.fiap.skill4green.model.Colaborador;
import br.com.fiap.skill4green.model.Execucao;
import br.com.fiap.skill4green.model.Tarefa;
import br.com.fiap.skill4green.repository.ColaboradorRepository;
import br.com.fiap.skill4green.repository.ExecucaoRepository;
import br.com.fiap.skill4green.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExecucaoService {

  private final ExecucaoRepository repository;
  private final ColaboradorRepository colaboradorRepository;
  private final TarefaRepository tarefaRepository;
  private final ExecucaoMapper mapper;
  private final TarefaProducer producer;

  public List<ExecucaoResponse> listar() {
    return repository.findAll().stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
  }

  public ExecucaoResponse salvar(ExecucaoRequest request) {
    Colaborador colaborador = colaboradorRepository.findById(request.getIdColaborador())
      .orElseThrow(() -> new NotFoundException("erro.colaborador.nao.encontrado"));
    Tarefa tarefa = tarefaRepository.findById(request.getIdTarefa())
      .orElseThrow(() -> new NotFoundException("erro.tarefa.nao.encontrada"));

    Execucao execucao = mapper.toEntity(request, colaborador, tarefa);
    Execucao salvo = repository.save(execucao);
    ExecucaoResponse response = mapper.toResponse(salvo);

    producer.enviarExecucao(response);
    return response;
  }
}