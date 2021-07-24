import { Injector, Pipe, PipeTransform, Type } from '@angular/core';

export class PipeDinamico {
  token: Type<any>;
  argumentos: any[] = [];
}

@Pipe({
  name: 'dinamico'
})
export class DinamicoPipe implements PipeTransform {

  public constructor(private injetor: Injector) {}

  transform(valor: any, pipeDinamico: PipeDinamico[]): any {
    if (!pipeDinamico) {
      return valor;
    }

    return this.transformarValor(valor, pipeDinamico.slice().reverse());
  }

  private transformarValor(valor: any, pipeDinamico: PipeDinamico[]) {
    if (pipeDinamico.length === 0) {
      return valor;
    }

    const valorPipe = pipeDinamico.pop();
    const pipeService = this.injetor.get<any>(valorPipe.token);
    const novoValor = pipeService.transform(valor, ...valorPipe.argumentos);

    return this.transformarValor(novoValor, pipeDinamico);
  }

}
