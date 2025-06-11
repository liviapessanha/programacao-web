package br.edu.iff.ccc.bsi.sgvet.utils;

import br.edu.iff.ccc.bsi.sgvet.entities.Consulta;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaUtils {
    public static List<Consulta> ordenarConsultasPorDataHora(List<Consulta> consultas) {
        if(consultas.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Lista de consulta vazia."
            );
        }
        return consultas.stream()
                .sorted(Comparator.comparing(Consulta::getDia).thenComparing(Consulta::getHora))
                .collect(Collectors.toList());
    }
}
