package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.dto.request.TarefaRequest;
import br.com.fiap.skill4green.dto.response.TarefaResponse;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.mapper.TarefaMapper;
import br.com.fiap.skill4green.model.Tarefa;
import br.com.fiap.skill4green.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

  private final TarefaRepository repository;
  private final TarefaMapper mapper;

  @Cacheable(value = "tarefas")
  public Page<TarefaResponse> listar(Pageable pageable) {
    return repository.findAll(pageable).map(mapper::toResponse);
  }

  @Cacheable(value = "tarefas", key = "#id")
  public TarefaResponse buscarPorId(Long id) {
    Tarefa t = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.tarefa.nao.encontrada"));
    return mapper.toResponse(t);
  }

  @CacheEvict(value = "tarefas", allEntries = true)
  public TarefaResponse salvar(TarefaRequest request) {
    return mapper.toResponse(repository.save(mapper.toEntity(request)));
  }

  @CacheEvict(value = "tarefas", allEntries = true)
  public TarefaResponse atualizar(Long id, TarefaRequest request) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.tarefa.nao.encontrada");
    }
    Tarefa entity = mapper.toEntity(request);
    entity.setId(id);
    return mapper.toResponse(repository.save(entity));
  }

  @CacheEvict(value = "tarefas", allEntries = true)
  public void remover(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.tarefa.nao.encontrada");
    }
    repository.deleteById(id);
  }
}