import { ColunaTabela } from 'src/app/shared/components/tabela-padrao/tabela-padrao.component';


export class TarefaUtil {
    static COLUNAS_PADRAO: ColunaTabela[] = [
        {
            cabecalho: 'Tarefa',
            parametro: 'nome'
        },
        {
            cabecalho: 'Data de Inicio',
            parametro: 'dataInicio'
        },
        {
            cabecalho: 'Data de conclusão',
            parametro: 'dataConclusao'
        },
        {
            cabecalho: 'Status',
            parametro: 'status'
        },
        {
            cabecalho: 'Responsavel',
            parametro: 'nomeResponsavel'
        },
        {
            cabecalho: 'Anexo',
            parametro: 'nomeAnexos'
        }
    ];
}
