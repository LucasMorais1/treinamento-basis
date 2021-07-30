import { Component, EventEmitter, Input, Output, Type, ViewChild } from '@angular/core';
import { Table } from 'primeng';
import { Filtro } from '../../model/responsavel-filtro';


export class EventoSituacaoTabela {
  checado: boolean;
  idItem: number;
}

export class EventoAtualizarTabela {
  dadosTabela: Table;
  filtroColuna: any;
}

export class ColunaTabela {
  cabecalho: string;
  parametro: string;
  mascara?: PipeDinamico[] = [];
}

export class PipeDinamico {
  token: Type<any>;
  argumentos: any[] = [];
}

export class Page<T> {
  content: T[] = [];
  last: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  numberOfElements: number;
  sort: string;
  first: boolean;
}


@Component({
  selector: 'app-tabela-padrao',
  templateUrl: './tabela-padrao.component.html',
  styleUrls: ['./tabela-padrao.component.scss']
})
export class TabelaPadraoComponent{

  @ViewChild('tabela') tabela: Table;
  @Input() resultado: Page<any> = new Page();

  @Input() situacaoIconeCustomizados = false;
  @Input() colunas: ColunaTabela[] = [];

  @Output() carregarItens = new EventEmitter<EventoAtualizarTabela>();
  @Output() cliqueEditar = new EventEmitter<number>();
  @Output() cliqueRemover = new EventEmitter<number>();

  nenhumRegistroCadastrado = 'Nenhum registro cadastrado.';

  filtro: Filtro = new Filtro();

  carregarTabela() {
    this.carregarItens.emit({
      dadosTabela: this.tabela ? this.tabela : null,
      filtroColuna: this.filtro
    });
  }

  emitirMudancaSituacao(evento: any, id: number) {
    const eventoSituacao = new EventoSituacaoTabela();
    eventoSituacao.checado = evento.checked;
    eventoSituacao.idItem = id;
  }

  quantidadeColunas(): number {
    return this.colunas.length + 1;
  }

  verificaColunaData(param: any): boolean {
    return param.match(/data/);
  }

}
