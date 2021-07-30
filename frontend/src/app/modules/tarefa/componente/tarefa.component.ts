import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { finalize } from 'rxjs/operators';
import { Tarefa } from 'src/app/domain/tarefa';
import { EventoAtualizarTabela, TabelaPadraoComponent } from 'src/app/shared/components/tabela-padrao/tabela-padrao.component';
import { MensagensUtil } from 'src/app/shared/enumaration/mensagens-util';
import { TarefaFiltro } from 'src/app/shared/model/tarefa-filtro';
import { TarefaService } from 'src/app/shared/services/tarefa.service';
import { TarefaUtil } from '../util/tarefa.util';

@Component({
  selector: 'app-tarefa',
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css']
})
export class TarefaComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  @ViewChild(TabelaPadraoComponent) tabelaPadrao: TabelaPadraoComponent;

  tarefa: Tarefa = new Tarefa();
  colunasTabela = TarefaUtil.COLUNAS_PADRAO;
  filtro: TarefaFiltro = new TarefaFiltro();
  idTarefa: number;
  exibirModalConfirmacao: boolean;
  exibirModalFormulario: boolean;

  form: FormGroup;

  constructor(
    private tarefaService: TarefaService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.initFormEObj();
  }

  private initFormEObj(): void {
    this.form = this.fb.group({
      nome: [null, [Validators.required, Validators.maxLength(200)]],
      status: [null, [Validators.required, Validators.maxLength(200)]],
      dataInicio: [null, [Validators.required]],
      dataConclusao: [null, [Validators.required]],
      ResponsavelId: [null, [Validators.required]]
    });

    this.tarefa = new Tarefa();
  }

  carregarTarefa(dados: EventoAtualizarTabela) {
    this.blockUI.start(MensagensUtil.BUSCANDO);
    console.log(dados.dadosTabela);
    this.tarefaService.filtrar(this.filtro, dados.dadosTabela)
      .pipe(
        finalize(() => this.blockUI.stop())
      ).subscribe(page => this.tabelaPadrao.resultado = page);
  }

  abrirFormulario() {
    this.exibirModalFormulario = true;
  }

  ativarEditar(id: number) {
    console.log('Editar');
    this.tarefaService.buscarPorId(id).pipe(
      finalize(() => this.blockUI.stop())
    ).subscribe(res => this.tarefa = res);
    this.exibirModalFormulario = true;
  }

  validarExcluir(idTarefa: number) {
    this.idTarefa = idTarefa;
    this.exibirModalConfirmacao = true;
  }

  fecharModalFormulario() {
    this.exibirModalFormulario = false;
  }

  fecharModalConfirmacao() {
    this.exibirModalConfirmacao = false;
  }

  confirmarSalvar() {
    if (this.form.valid) {
      if(this.tarefa.id) {
        this.blockUI.start(MensagensUtil.SALVANDO);
        this.tarefaService.salvar(this.tarefa).pipe(
          finalize(() => this.blockUI.stop())
        ).subscribe(() => {
          this.exibirModalFormulario = false;
          this.tabelaPadrao.tabela.reset();
        });
      }
      else {
        this.blockUI.start(MensagensUtil.EDITANDO);
        this.tarefaService.editar(this.tarefa).pipe(
          finalize(() => this.blockUI.stop())
        ).subscribe(() => {
          this.exibirModalFormulario = false;
          this.tabelaPadrao.tabela.reset();
        });
      }
      this.initFormEObj();
      this.form.reset();
    }
  }

  confirmarExcluir() {
    this.blockUI.start(MensagensUtil.BUSCANDO);
    this.tarefaService.remover(this.idTarefa).pipe(
      finalize(() => this.blockUI.stop())
    ).subscribe(() => {
      this.exibirModalConfirmacao = false;
      this.tabelaPadrao.tabela.reset();
    });
  }

}
