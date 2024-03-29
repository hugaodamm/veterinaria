package com.veterinaria.service;

import com.veterinaria.entity.Consulta;
import com.veterinaria.entity.Proprietario;
import com.veterinaria.persistence.ConsultaPersistence;
import org.springframework.stereotype.Service;


import java.time.LocalDate;


import java.time.LocalDate;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaPersistence consultaPersistence = new ConsultaPersistence();

    public Consulta agendarConsulta(Consulta consulta) {
        consultaPersistence.cadastrarConsulta(consulta);
        return consulta;
    }

    public Consulta alterar(Consulta consulta){
        if (consulta != null){
            return consultaPersistence.alterar(consulta);
        }
        return null;
    }


    public List<Consulta> consultasPaciente(String nomePaciente, String cpfProprietario) {
        try {
        List<Consulta> consultas = consultaPersistence.listar();
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().getNome().equals(nomePaciente) &&
                    consulta.getPaciente().getProprietario().getCpf().equals(cpfProprietario)) {
                consultas.sort(Comparator.comparing(consul -> consul.getPaciente().getProprietario().getNome()));
            }
        }
            return consultas;
     }catch (ConcurrentModificationException e){
            e.fillInStackTrace();
        }

        return null;

    }


    public List<Consulta> listarConsultaPorData(String nomePaciente, String cpfProprietario){
        List<Consulta> consultas = consultaPersistence.listar();
        for (Consulta consulta : consultas){
            if (consulta.getPaciente().getNome().equals(nomePaciente) &&
                consulta.getPaciente().getProprietario().getCpf().equals(cpfProprietario)){
                consultas.sort(Comparator.comparing(Consulta::getDataDia).reversed());
            }
        }
        return consultas;
    }

    public Integer totalConsultasMedico(Integer crm){
        int total = 0;
        List<Consulta> consultas = consultaPersistence.listar();
        for (Consulta consulta : consultas){
            if (consulta.getMedicoVeterinario().getNumeroRegistro().equals(crm)){
                total++;
            }
        }
        return total;
    }


    public List<Consulta> consultasMesmoDia(LocalDate data, String nomePaciente, String cpfProprietario){
        List<Consulta> consultas = consultaPersistence.listar();
        for (Consulta consulta : consultas){
            if (consulta.getDataDia().equals(data) && consulta.getPaciente().getNome().equals(nomePaciente) &&
                consulta.getPaciente().getProprietario().getCpf().equals(cpfProprietario)){
                consultas.sort(Comparator.comparing(Consulta::getDataDia));
            }
        }
        return consultas;
    }





}
