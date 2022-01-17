package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GraficoSalario implements Serializable {

    private static final long serialVersionUID = 1L;

    List<String> perfis = new ArrayList<>();
    List<Double> salarios = new ArrayList<>();

    public GraficoSalario() {
    }

    public GraficoSalario(List<String> perfis, List<Double> salarios) {

        this.perfis = perfis;
        this.salarios = salarios;
    }

    public List<String> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<String> perfis) {
        this.perfis = perfis;
    }

    public List<Double> getSalarios() {
        return salarios;
    }

    public void setSalarios(List<Double> salarios) {
        this.salarios = salarios;
    }

}
