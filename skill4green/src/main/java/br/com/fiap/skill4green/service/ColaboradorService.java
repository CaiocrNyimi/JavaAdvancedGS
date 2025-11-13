package br.com.fiap.skill4green.service;

import br.com.fiap.skill4green.dto.request.ColaboradorRequest;
import br.com.fiap.skill4green.dto.response.ColaboradorResponse;
import br.com.fiap.skill4green.exception.NotFoundException;
import br.com.fiap.skill4green.mapper.ColaboradorMapper;
import br.com.fiap.skill4green.model.Colaborador;
import br.com.fiap.skill4green.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColaboradorService {

  private final ColaboradorRepository repository;
  private final ColaboradorMapper mapper;
  private final PasswordEncoder encoder;

  public List<ColaboradorResponse> listar() {
    return repository.findAll().stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
  }

  public ColaboradorResponse buscarPorId(Long id) {
    Colaborador c = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("erro.colaborador.nao.encontrado"));
    return mapper.toResponse(c);
  }

  public ColaboradorResponse salvar(ColaboradorRequest request) {
    Colaborador entity = mapper.toEntity(request);
    return mapper.toResponse(repository.save(entity));
  }

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

  public void remover(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("erro.colaborador.nao.encontrado");
    }
    repository.deleteById(id);
  }
}