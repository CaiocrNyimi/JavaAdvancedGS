package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.dto.request.MissaoRequest;
import br.com.fiap.skill4green.dto.response.MissaoResponse;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.mapper.MissaoMapper;
import br.com.fiap.skill4green.model.Missao;
import br.com.fiap.skill4green.repository.MissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissaoService {

  private final MissaoRepository repository;
  private final MissaoMapper mapper;

  public List<MissaoResponse> listar() {
    return repository.findAll().stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
  }

  public MissaoResponse buscarPorId(Long id) {
    Missao m = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.missao.nao.encontrada"));
    return mapper.toResponse(m);
  }

  public MissaoResponse salvar(MissaoRequest request) {
    return mapper.toResponse(repository.save(mapper.toEntity(request)));
  }

  public MissaoResponse atualizar(Long id, MissaoRequest request) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.missao.nao.encontrada");
    }
    Missao entity = mapper.toEntity(request);
    entity.setId(id);
    return mapper.toResponse(repository.save(entity));
  }

  public void remover(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.missao.nao.encontrada");
    }
    repository.deleteById(id);
  }
}