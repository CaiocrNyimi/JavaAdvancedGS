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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExecucaoService {

  private final ExecucaoRepository repository;
  private final ColaboradorRepository colaboradorRepository;
  private final TarefaRepository tarefaRepository;
  private final ExecucaoMapper mapper;
  private final TarefaProducer producer;

  @Cacheable(value = "execucoes")
  public Page<ExecucaoResponse> listar(Pageable pageable) {
    return repository.findAll(pageable).map(mapper::toResponse);
  }

  @Cacheable(value = "execucoes", key = "#id")
  public ExecucaoResponse buscarPorId(Long id) {
    Execucao execucao = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.execucao.nao.encontrada"));
    return mapper.toResponse(execucao);
  }

  @CacheEvict(value = "execucoes", allEntries = true)
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

  @CacheEvict(value = "execucoes", allEntries = true)
  public ExecucaoResponse atualizar(Long id, ExecucaoRequest request) {
    Execucao existente = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.execucao.nao.encontrada"));

    Colaborador colaborador = colaboradorRepository.findById(request.getIdColaborador())
      .orElseThrow(() -> new NotFoundException("erro.colaborador.nao.encontrado"));
    Tarefa tarefa = tarefaRepository.findById(request.getIdTarefa())
      .orElseThrow(() -> new NotFoundException("erro.tarefa.nao.encontrada"));

    existente.setColaborador(colaborador);
    existente.setTarefa(tarefa);
    existente.setData(request.getData());
    existente.setResultado(request.getResultado());

    Execucao atualizada = repository.save(existente);
    return mapper.toResponse(atualizada);
  }

  @CacheEvict(value = "execucoes", allEntries = true)
  public void remover(Long id) {
    Execucao execucao = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.execucao.nao.encontrada"));
    repository.delete(execucao);
  }
}