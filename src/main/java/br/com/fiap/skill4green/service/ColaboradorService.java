package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.dto.request.ColaboradorRequest;
import br.com.fiap.skill4green.dto.response.ColaboradorResponse;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.mapper.ColaboradorMapper;
import br.com.fiap.skill4green.model.Colaborador;
import br.com.fiap.skill4green.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColaboradorService {

  private final ColaboradorRepository repository;
  private final ColaboradorMapper mapper;
  private final PasswordEncoder encoder;

  @Cacheable(value = "colaboradores")
  public Page<ColaboradorResponse> listar(Pageable pageable) {
    return repository.findAll(pageable).map(mapper::toResponse);
  }

  @Cacheable(value = "colaboradores", key = "#id")
  public ColaboradorResponse buscarPorId(Long id) {
    Colaborador c = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.colaborador.nao.encontrado"));
    return mapper.toResponse(c);
  }

  @CacheEvict(value = "colaboradores", allEntries = true)
  public ColaboradorResponse salvar(ColaboradorRequest request) {
    Colaborador entity = mapper.toEntity(request);
    return mapper.toResponse(repository.save(entity));
  }

  @CacheEvict(value = "colaboradores", allEntries = true)
  public ColaboradorResponse atualizar(Long id, ColaboradorRequest request) {
    Colaborador entity = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.colaborador.nao.encontrado"));

    entity.setNome(request.getNome());
    entity.setSetor(request.getSetor());
    entity.setEmail(request.getEmail());
    entity.setPerfil(request.getPerfil());
    entity.setNivelVerde(request.getNivelVerde());
    entity.setEcoins(request.getEcoins());

    if (request.getSenha() != null && !request.getSenha().isBlank()) {
      entity.setSenha(encoder.encode(request.getSenha()));
    }

    return mapper.toResponse(repository.save(entity));
  }

  @CacheEvict(value = "colaboradores", allEntries = true)
  public void remover(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.colaborador.nao.encontrado");
    }
    repository.deleteById(id);
  }
}