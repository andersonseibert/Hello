package br.org.gdt.beans;

import br.org.gdt.model.Tarefa;
import br.org.gdt.service.TarefaService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TarefaBean {

    private boolean formAtivo = false;

    private Tarefa tarefa;
    private List<Tarefa> tarefas;

    @ManagedProperty("#{tarefaService}")
    private TarefaService tarefaService;

    public TarefaBean() {
        //tarefa = new Tarefa();
    }

    public void salvar() {
        this.formAtivo = false;

        if (tarefa.getId() > 0) {
            tarefaService.update(tarefa);
        } else {
            tarefaService.save(tarefa);
        }
    }

    public void cancel() {
        this.formAtivo = false;
    }

    public void add() {
        this.formAtivo = true;
        tarefa = new Tarefa();
    }

    public List<Tarefa> getTarefas() {
        if (tarefas == null) {
            tarefas = tarefaService.findAll();
        }
        return tarefas;
    }

    public String excluir(Tarefa tarefa) {
        tarefaService.delete(tarefa.getId());
        return "tarefa_list";
    }

    public void prepareEdit(Tarefa tarefa) {
        this.formAtivo = true;
        this.tarefa = tarefa;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public TarefaService getTarefaService() {
        return tarefaService;
    }

    public void setTarefaService(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public boolean isFormAtivo() {
        return formAtivo;
    }

    public void setFormAtivo(boolean formAtivo) {
        this.formAtivo = formAtivo;
    }
}
