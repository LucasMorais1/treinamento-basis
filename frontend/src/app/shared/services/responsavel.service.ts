import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Table } from 'primeng/table';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page } from '../components/tabela-padrao/tabela-padrao.component';
import { RequestUtil } from '../util/request.util';
import { Responsavel } from './../../domain/responsavel';
import { Filtro } from './../model/responsavel-filtro';

@Injectable({
  providedIn: 'root'
})
export class ResponsavelService {

  resourceUrl = environment.apiUrl + '/responsaveis/';

  constructor(private httpClient: HttpClient) { }

  salvar(responsavel: Responsavel): Observable<any> {
    return this.httpClient.post(this.resourceUrl, responsavel);
  }

  editar(responsavel: Responsavel): Observable<Responsavel> {
    return this.httpClient.put<Responsavel>(this.resourceUrl, responsavel);
  }

  listar() {
    return this.httpClient.get(this.resourceUrl);
  }

  filtrar(filtro: Filtro, datatable: Table): Observable<Page<Responsavel>> {
    return this.httpClient.post<Page<Responsavel>>(`${this.resourceUrl}search`, filtro, {
      params: RequestUtil.getRequestParamsTable(datatable)
    });
  }

  buscarPorId(id: number): Observable<Responsavel> {
    return this.httpClient.get<Responsavel>(this.resourceUrl + id);
  }

  remover(id: number): Observable<any> {
    return this.httpClient.delete(this.resourceUrl + id);
  }
}
