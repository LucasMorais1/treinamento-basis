import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Table } from 'primeng/table';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page } from '../components/tabela-padrao/tabela-padrao.component';
import { RequestUtil } from '../util/request.util';
import { Tarefa } from './../../domain/tarefa';
import { TarefaFiltro } from './../model/tarefa-filtro';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {

  resourceUrl = environment.apiUrl + '/tarefas/';

  constructor(private httpClient: HttpClient) { }

  salvar(tarefa: Tarefa): Observable<any> {
    return this.httpClient.post(this.resourceUrl, tarefa);
  }

  editar(tarefa: Tarefa): Observable<Tarefa> {
    return this.httpClient.put<Tarefa>(this.resourceUrl, tarefa);
  }

  listar() {
    return this.httpClient.get(this.resourceUrl);
  }

  filtrar(filtro: TarefaFiltro, datatable: Table): Observable<Page<Tarefa>> {
    return this.httpClient.post<Page<Tarefa>>(`${this.resourceUrl}search`, filtro, {
      params: RequestUtil.getRequestParamsTable(datatable)
    });
  }

  buscarPorId(id: number): Observable<Tarefa> {
    return this.httpClient.get<Tarefa>(this.resourceUrl + id);
  }

  remover(id: number): Observable<any> {
    return this.httpClient.delete(this.resourceUrl + id);
  }
}
