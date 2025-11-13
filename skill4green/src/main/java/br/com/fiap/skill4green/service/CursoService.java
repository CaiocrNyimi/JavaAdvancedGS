package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.dto.request.CursoRequest;
import br.com.fiap.skill4green.dto.response.CursoResponse;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.mapper.CursoMapper;
import br.com.fiap.skill4green.model.Curso;
import br.com.fiap.skill4green.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {

  private final CursoRepository repository;
  private final CursoMapper mapper;

  public List<CursoResponse> listar() {
    return repository.findAll().stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
  }

  public CursoResponse buscarPorId(Long id) {
    Curso curso = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.curso.nao.encontrado"));
    return mapper.toResponse(curso);
  }

  public CursoResponse salvar(CursoRequest request) {
    return mapper.toResponse(repository.save(mapper.toEntity(request)));
  }

  public CursoResponse atualizar(Long id, CursoRequest request) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.curso.nao.encontrado");
    }
    Curso entity = mapper.toEntity(request);
    entity.setId(id);
    return mapper.toResponse(repository.save(entity));
  }

  public void remover(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.curso.nao.encontrado");
    }
    repository.deleteById(id);
  }
}