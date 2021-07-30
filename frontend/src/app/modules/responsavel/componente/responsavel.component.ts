import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { finalize } from 'rxjs/operators';
import { Responsavel } from 'src/app/domain/responsavel';
import { EventoAtualizarTabela, TabelaPadraoComponent } from 'src/app/shared/components/tabela-padrao/tabela-padrao.component';
import { MensagensUtil } from 'src/app/shared/enumaration/mensagens-util';
import { Filtro } from '../../../shared/model/responsavel-filtro';
import { ResponsavelService } from './../../../shared/services/responsavel.service';
import { ResponsavelUtil } from './../util/responsavel.util';

@Component({
  selector: 'app-responsavel',
  templateUrl: './responsavel.component.html',
  styleUrls: ['./responsavel.component.css']
})
export class ResponsavelComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  @ViewChild(TabelaPadraoComponent) tabelaPadrao: TabelaPadraoComponent;

  responsavel: Responsavel = new Responsavel();
  colunasTabela = ResponsavelUtil.COLUNAS_PADRAO;
  filtro: Filtro = new Filtro();
  idResponsavel: number;
  exibirModalConfirmacao: boolean;
  exibirModalFormulario: boolean;


  form: FormGroup;

  constructor(
    private responsavelService: ResponsavelService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.initFormEObj();
  }

  private initFormEObj(): void {
    this.form = this.fb.group({
      nome: [null, [Validators.required, Validators.maxLength(200)]],
      email: [null, [Validators.required, Validators.maxLength(200)]],
      dataNascimento: [null, [Validators.required]]
    });

    this.responsavel = new Responsavel();
  }

  carregarResponsavel(dados: EventoAtualizarTabela) {
    this.blockUI.start(MensagensUtil.BUSCANDO);
    console.log(dados.dadosTabela);
    this.responsavelService.filtrar(this.filtro, dados.dadosTabela)
      .pipe(
        finalize(() => this.blockUI.stop())
      ).subscribe(page => this.tabelaPadrao.resultado = page);
  }

  abrirFormulario() {
    this.exibirModalFormulario = true;
  }

  ativarEditar(id: number) {
    this.responsavelService.buscarPorId(id).pipe(
      finalize(() => this.blockUI.stop())
    ).subscribe(res => this.responsavel = res);
    this.exibirModalFormulario = true;
  }

  validarExcluir(idResponsavel: number) {
    this.idResponsavel = idResponsavel;
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
      if(this.responsavel.id) {
        this.blockUI.start(MensagensUtil.SALVANDO);
        this.responsavelService.salvar(this.responsavel).pipe(
          finalize(() => this.blockUI.stop())
        ).subscribe(() => {
          this.exibirModalFormulario = false;
          this.tabelaPadrao.tabela.reset();
        });
      }
      else {
        this.blockUI.start(MensagensUtil.EDITANDO);
        this.responsavelService.editar(this.responsavel).pipe(
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
    this.responsavelService.remover(this.idResponsavel).pipe(
      finalize(() => this.blockUI.stop())
    ).subscribe(() => {
      this.exibirModalConfirmacao = false;
      this.tabelaPadrao.tabela.reset();
    });
  }
}
