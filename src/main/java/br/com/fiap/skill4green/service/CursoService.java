package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.dto.request.CursoRequest;
import br.com.fiap.skill4green.dto.response.CursoResponse;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.mapper.CursoMapper;
import br.com.fiap.skill4green.model.Curso;
import br.com.fiap.skill4green.repository.CursoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {

  private final CursoRepository repository;
  private final CursoMapper mapper;

  @Cacheable(value = "cursos")
  public Page<CursoResponse> listar(Pageable pageable) {
    return repository.findAll(pageable).map(mapper::toResponse);
  }

  @Cacheable(value = "cursos", key = "#id")
  public CursoResponse buscarPorId(Long id) {
    Curso curso = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.curso.nao.encontrado"));
    return mapper.toResponse(curso);
  }

  @CacheEvict(value = "cursos", allEntries = true)
  public CursoResponse salvar(CursoRequest request) {
    return mapper.toResponse(repository.save(mapper.toEntity(request)));
  }

  @CacheEvict(value = "cursos", allEntries = true)
  public CursoResponse atualizar(Long id, CursoRequest request) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.curso.nao.encontrado");
    }
    Curso entity = mapper.toEntity(request);
    entity.setId(id);
    return mapper.toResponse(repository.save(entity));
  }

  @CacheEvict(value = "cursos", allEntries = true)
  public void remover(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.curso.nao.encontrado");
    }
    repository.deleteById(id);
  }
}