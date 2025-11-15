package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.dto.request.MissaoRequest;
import br.com.fiap.skill4green.dto.response.MissaoResponse;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.mapper.MissaoMapper;
import br.com.fiap.skill4green.model.Missao;
import br.com.fiap.skill4green.repository.MissaoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissaoService {

  private final MissaoRepository repository;
  private final MissaoMapper mapper;

  @Cacheable(value = "missoes")
  public Page<MissaoResponse> listar(Pageable pageable) {
    return repository.findAll(pageable).map(mapper::toResponse);
  }

  @Cacheable(value = "missoes", key = "#id")
  public MissaoResponse buscarPorId(Long id) {
    Missao m = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.missao.nao.encontrada"));
    return mapper.toResponse(m);
  }

  @CacheEvict(value = "missoes", allEntries = true)
  public MissaoResponse salvar(MissaoRequest request) {
    return mapper.toResponse(repository.save(mapper.toEntity(request)));
  }

  @CacheEvict(value = "missoes", allEntries = true)
  public MissaoResponse atualizar(Long id, MissaoRequest request) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.missao.nao.encontrada");
    }
    Missao entity = mapper.toEntity(request);
    entity.setId(id);
    return mapper.toResponse(repository.save(entity));
  }

  @CacheEvict(value = "missoes", allEntries = true)
  public void remover(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.missao.nao.encontrada");
    }
    repository.deleteById(id);
  }
}