import { ColunaTabela } from 'src/app/shared/components/tabela-padrao/tabela-padrao.component';


export class ResponsavelUtil {
    static COLUNAS_PADRAO: ColunaTabela[] = [
        {
            cabecalho: 'Responsavel',
            parametro: 'nome'
        },
        {
            cabecalho: 'E-mail',
            parametro: 'email'
        },
        {
            cabecalho: 'Data de nascimento',
            parametro: 'dataNascimento'
        },
    ];
}