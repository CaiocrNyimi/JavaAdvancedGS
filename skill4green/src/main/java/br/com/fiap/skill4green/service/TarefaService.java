package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.dto.request.TarefaRequest;
import br.com.fiap.skill4green.dto.response.TarefaResponse;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.mapper.TarefaMapper;
import br.com.fiap.skill4green.model.Tarefa;
import br.com.fiap.skill4green.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarefaService {

  private final TarefaRepository repository;
  private final TarefaMapper mapper;

  public List<TarefaResponse> listar() {
    return repository.findAll().stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
  }

  public TarefaResponse buscarPorId(Long id) {
    Tarefa t = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.tarefa.nao.encontrada"));
    return mapper.toResponse(t);
  }

  public TarefaResponse salvar(TarefaRequest request) {
    return mapper.toResponse(repository.save(mapper.toEntity(request)));
  }

  public TarefaResponse atualizar(Long id, TarefaRequest request) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.tarefa.nao.encontrada");
    }
    Tarefa entity = mapper.toEntity(request);
    entity.setId(id);
    return mapper.toResponse(repository.save(entity));
  }

  public void remover(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.tarefa.nao.encontrada");
    }
    repository.deleteById(id);
  }
}