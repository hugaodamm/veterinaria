package com.veterinaria.controller;

import com.veterinaria.dto.paciente.PacienteDTO;
import com.veterinaria.dto.paciente.PacienteDtoRequest;
import com.veterinaria.entity.Paciente;
import com.veterinaria.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("paciente/")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<PacienteDTO> cadastrar(@RequestBody PacienteDtoRequest pacienteDtoRequest){
        Paciente paciente = pacienteService.cadastraPaciente(pacienteDtoRequest.converte());
        return new ResponseEntity<>(PacienteDTO.converter(paciente), HttpStatus.CREATED);
    }

    @GetMapping("/obter/{nome}/{cpfProprietario}")
    public PacienteDTO obter(@PathVariable String nome, @PathVariable String cpfProprietario){
        Paciente paciente = pacienteService.obterPaciente(nome, cpfProprietario);
        return PacienteDTO.converter(paciente);
    }

    @PutMapping("/altera")
    public PacienteDTO alterar(@RequestBody PacienteDtoRequest pacienteDtoRequest){
        Paciente paciente = pacienteService.altera(pacienteDtoRequest.converte());
        return PacienteDTO.converter(paciente);
    }

    @DeleteMapping("/apagar/{nome}/{cpfProprietario}")
    public boolean apagar(@PathVariable String nome, @PathVariable String cpfProprietario){
        return pacienteService.apagar(nome, cpfProprietario);
    }

    @GetMapping("/listar")
    public List<PacienteDTO> listar(){
        List<Paciente> pacientes = pacienteService.listarPacientes();
        return PacienteDTO.coverterLista(pacientes);
    }
}
